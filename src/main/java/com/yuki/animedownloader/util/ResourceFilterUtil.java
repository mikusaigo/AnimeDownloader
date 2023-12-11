package com.yuki.animedownloader.util;

import cn.hutool.core.collection.CollUtil;
import com.yuki.animedownloader.model.ResourceInfo;
import com.yuki.animedownloader.vo.ResourceInfoQueryVo;

import java.util.ArrayList;
import java.util.List;

public class ResourceFilterUtil {

    public static <S extends ResourceInfo, Q extends ResourceInfoQueryVo> List<S> filterResource(List<S> sourceList, Q queryEntity){
        if (CollUtil.isEmpty(sourceList)){
            return new ArrayList<>();
        }
        if (null == queryEntity){
            return sourceList;
        }
        return null;

    }
}
