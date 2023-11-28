package com.yuki.animedownloader.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceUtil {

    /**
     * 从资源名称中获取资源分辨率
     * @param resourceName 资源名称
     * @return 资源分辨率
     */
    public static String getDPI(String resourceName){
        String patternStr = "\\d{3,4}[p|P]";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(resourceName);
        if (matcher.find()){
            String dpi = matcher.group();
            dpi = dpi.replace('p', 'P');
            return dpi;
        }
        return "unknown";
    }

    public static String getFileTotalNum(String resourceName){
        return get(resourceName, "文件大小[:|：]\\s*(\\d+)");
    }

    public static String getSourceMagnet(String resourceName){
        return get(resourceName, "show-(\\w{40}).html");
    }

    private static String get(String target, String patternStr){
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(target);
        if (matcher.find()){
            return matcher.group(1);
        }
        return "unknown";
    }
}
