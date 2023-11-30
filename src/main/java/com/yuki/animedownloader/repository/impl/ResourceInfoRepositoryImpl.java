package com.yuki.animedownloader.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.animedownloader.mapper.ResourceInfoMapper;
import com.yuki.animedownloader.model.ResourceInfo;
import com.yuki.animedownloader.repository.ResourceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ResourceInfoRepositoryImpl extends ServiceImpl<ResourceInfoMapper, ResourceInfo> implements ResourceInfoRepository {

    @Autowired
    ResourceInfoMapper resourceInfoMapper;

    @Override
    public void insertOrUpdate(Collection<ResourceInfo> dataList) {
        resourceInfoMapper.insertOrUpdate(dataList);
    }
}
