package com.yuki.animedownloader.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import com.yuki.animedownloader.crawlers.ComicatCrawlers;
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

    @Override
    public List<ResourceInfoVo> comicatDownload(ResourceInfoQueryVo queryVo) {
        ComicatCrawlers comicatCrawlers = new ComicatCrawlers();
        comicatCrawlers.buildUrl("/search.php?keyword=" + queryVo.getResourceName() + " " + queryVo.getUploader() + " " + queryVo.getLanguage());
        log.info("请求的地址：{}", comicatCrawlers.getTargetUrl());
        String html = comicatCrawlers.crawl();
        HtmlParser htmlParser = new HtmlParser();
        List<ResourceInfo> result = htmlParser.parse(html);
        resourceInfoRepository.insertOrUpdate(result);
        MagnetAnalyzeUtil.downloadFilesFromMagnet("adacae4f42d69e0e952a08181b6ded4c30a7daa5", FileUtil.file("C:\\Users\\袁超\\Desktop\\"));
        return BeanUtil.copyToList(result, ResourceInfoVo.class);
    }
}
