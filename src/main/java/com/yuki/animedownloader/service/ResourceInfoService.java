package com.yuki.animedownloader.service;

import com.yuki.animedownloader.vo.ResourceInfoQueryVo;
import com.yuki.animedownloader.vo.ResourceInfoVo;

import java.util.List;

public interface ResourceInfoService {

    List<ResourceInfoVo> comicatDownload(ResourceInfoQueryVo queryVo);
}