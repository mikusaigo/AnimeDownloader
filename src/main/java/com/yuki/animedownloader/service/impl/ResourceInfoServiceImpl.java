package com.yuki.animedownloader.service.impl;

import ch.qos.logback.classic.Level;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.acgist.snail.context.exception.DownloadException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.animedownloader.config.DownloadConfig;
import com.yuki.animedownloader.constants.ResourceConstants;
import com.yuki.animedownloader.crawlers.ComicatCrawlers;
import com.yuki.animedownloader.enums.SourceTypeEnum;
import com.yuki.animedownloader.mapper.ResourceInfoMapper;
import com.yuki.animedownloader.model.ResourceInfo;
import com.yuki.animedownloader.parser.HtmlParser;
import com.yuki.animedownloader.repository.ResourceInfoRepository;
import com.yuki.animedownloader.service.ResourceInfoService;
import com.yuki.animedownloader.util.MagnetAnalyzeUtil;
import com.yuki.animedownloader.vo.ResourceInfoQueryVo;
import com.yuki.animedownloader.vo.ResourceInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Scope(value = "prototype")
public class ResourceInfoServiceImpl implements ResourceInfoService {

    @Autowired
    ResourceInfoRepository resourceInfoRepository;

    @Autowired
    ResourceInfoMapper resourceInfoMapper;

    @Autowired
    DownloadConfig downloadConfig;

    static {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.INFO);
    }

    @Override
    public List<ResourceInfoVo> comicatCrawls(ResourceInfoQueryVo queryVo) {
        ComicatCrawlers comicatCrawlers = new ComicatCrawlers();
        comicatCrawlers.buildUrl("/search.php?keyword=" + comicatCrawlers.buildQueryParams(queryVo));
        log.info("请求的地址：{}", comicatCrawlers.getTargetUrl());
        String html = comicatCrawlers.crawl();
        HtmlParser htmlParser = new HtmlParser();
        List<ResourceInfo> result = htmlParser.parse(html);
        if (CollUtil.isEmpty(result)) {
            return null;
        }
        if ("1".equals(queryVo.getIsSaveResource())) {
            resourceInfoRepository.insertOrUpdate(result);
        }
        return BeanUtil.copyToList(result, ResourceInfoVo.class);
    }

    @Override
    public List<ResourceInfoVo> download() {
        List<ResourceInfo> resourceInfos = resourceInfoMapper.selectList(Wrappers.<ResourceInfo>lambdaQuery()
                .eq(ResourceInfo::getType, SourceTypeEnum.MUSIC.getType())
                .eq(ResourceInfo::getUploader, "天使动漫论坛")
                .like(ResourceInfo::getName, "Hands Up to the Sky"));
        if (CollUtil.isEmpty(resourceInfos)) {
            throw new RuntimeException("没有找到可下载的数据");
        }
        ResourceInfo resourceInfo = resourceInfos.get(1);
        try {
            MagnetAnalyzeUtil.downloadFilesFromMagnet(ResourceConstants.BASE_MAGNET_URL + resourceInfo.getMagnetUri(), downloadConfig.getPath(resourceInfo.getType()));
        } catch (DownloadException e) {
            log.debug(e.getMessage());
        }
        return CollUtil.toList(BeanUtil.toBean(resourceInfo, ResourceInfoVo.class));
    }
}
