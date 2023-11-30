package com.yuki.animedownloader.controller;

import cn.hutool.core.text.CharSequenceUtil;
import com.yuki.animedownloader.config.ResourceSearchConfig;
import com.yuki.animedownloader.service.ResourceInfoService;
import com.yuki.animedownloader.vo.ResourceInfoQueryVo;
import com.yuki.animedownloader.vo.ResourceInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceInfoController {

    @Autowired
    ResourceInfoService resourceInfoService;

    @GetMapping("/download/comicat")
    public List<ResourceInfoVo> comicatDownload(ResourceInfoQueryVo queryVo){
        init(queryVo);
        return resourceInfoService.comicatDownload(queryVo);
    }

    public void init(ResourceInfoQueryVo queryVo){
        if (!ResourceSearchConfig.useDefaultConfig){
            return;
        }
        if (CharSequenceUtil.isEmpty(queryVo.getMaxResourceSize())){
            queryVo.setMaxResourceSize(ResourceSearchConfig.DEFAULT_TOTAL_FILE_MAX_SIZE);
        }
        if (CharSequenceUtil.isEmpty(queryVo.getMinResourceSize())){
            queryVo.setMinResourceSize(ResourceSearchConfig.DEFAULT_TOTAL_FILE_MIN_SIZE);
        }
        if (CharSequenceUtil.isEmpty(queryVo.getUploader())){
            queryVo.setUploader(ResourceSearchConfig.DEFAULT_UPLOADER);
        }
        if (null == queryVo.getPostedTime()){
            queryVo.setPostedTime(ResourceSearchConfig.DEFAULT_POST_DATE);
        }
    }
}
