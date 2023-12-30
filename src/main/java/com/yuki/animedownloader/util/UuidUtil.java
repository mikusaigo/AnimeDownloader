package com.yuki.animedownloader.util;

import cn.hutool.core.text.StrPool;

import java.util.UUID;

public class UuidUtil {

    /**
     * 根据参数生成UUID
     * @param params 参数
     * @return
     */
    public static String buildUuidByParams(Object ... params){
        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
        }
        return UUID.nameUUIDFromBytes(sb.toString().getBytes()).toString().replace(StrPool.DASHED, "");
    }
}
