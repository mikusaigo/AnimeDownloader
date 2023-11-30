package com.yuki.animedownloader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuki.animedownloader.model.ResourceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

@Mapper
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {

    void insertOrUpdate(@Param("dataList") Collection<ResourceInfo> dataList);
}
