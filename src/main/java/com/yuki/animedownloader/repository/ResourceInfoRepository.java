package com.yuki.animedownloader.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuki.animedownloader.model.ResourceInfo;

import java.util.Collection;

public interface ResourceInfoRepository extends IService<ResourceInfo> {

    void insertOrUpdate(Collection<ResourceInfo> dataList);
}
