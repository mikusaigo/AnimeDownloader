package com.yuki.animedownloader.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.acgist.snail.context.exception.DownloadException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yuki.animedownloader.constants.ResourceConstants;
import com.yuki.animedownloader.crawlers.ComicatCrawlers;
import com.yuki.animedownloader.mapper.ResourceInfoMapper;
import com.yuki.animedownloader.model.ResourceInfo;
import com.yuki.animedownloader.parser.HtmlParser;
import com.yuki.animedownloader.repository.ResourceInfoRepository;
import com.yuki.animedownloader.service.ResourceInfoService;
import com.yuki.animedownloader.util.MagnetAnalyzeUtil;
import com.yuki.animedownloader.vo.ResourceInfoQueryVo;
import com.yuki.animedownloader.vo.ResourceInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {

    @Autowired
    ResourceInfoRepository resourceInfoRepository;

    @Autowired
    ResourceInfoMapper resourceInfoMapper;

    @Override
    public List<ResourceInfoVo> comicatCrawls(ResourceInfoQueryVo queryVo) {
        ComicatCrawlers comicatCrawlers = new ComicatCrawlers();
        comicatCrawlers.buildUrl("/search.php?keyword=" + queryVo.getResourceName() + " " + queryVo.getUploader() + " " + queryVo.getLanguage());
        log.info("请求的地址：{}", comicatCrawlers.getTargetUrl());
        String html = comicatCrawlers.crawl();
        HtmlParser htmlParser = new HtmlParser();
        List<ResourceInfo> result = htmlParser.parse(html);
        resourceInfoRepository.insertOrUpdate(result);
        return BeanUtil.copyToList(result, ResourceInfoVo.class);
    }

    @Override
    public List<ResourceInfoVo> download() {
        List<ResourceInfo> resourceInfos = resourceInfoMapper.selectList(Wrappers.<ResourceInfo>lambdaQuery()
                .eq(ResourceInfo::getLanguage, "简日")
                .eq(ResourceInfo::getUploader, "北宇治字幕组")
                .like(ResourceInfo::getName, "芙莉莲"));
        if (CollUtil.isEmpty(resourceInfos)) {
            throw new RuntimeException("没有找到可下载的的数据");
        }
        ResourceInfo resourceInfo = resourceInfos.stream().filter(ri -> ri.getName().contains("[02]")).findFirst().get();
        try {
            MagnetAnalyzeUtil.downloadFilesFromMagnet(ResourceConstants.BASE_MAGNET_URL + resourceInfo.getMagnetUri());
        } catch (DownloadException e) {
            log.debug(e.getMessage());
        }
        return CollUtil.toList(BeanUtil.toBean(resourceInfo, ResourceInfoVo.class));
    }
}
