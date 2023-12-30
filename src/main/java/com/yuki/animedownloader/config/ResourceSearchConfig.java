package com.yuki.animedownloader.config;

import cn.hutool.core.date.DateUtil;

import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class ResourceSearchConfig {

    public static boolean useDefaultConfig = false;

    // 默认查询大小
    public static final Integer DEFAULT_SIZE = 12;

    // 默认资源文件大小下限
    public static final String DEFAULT_TOTAL_FILE_MIN_SIZE = "0KB";

    // 默认资源文件大小上限
    public static final String DEFAULT_TOTAL_FILE_MAX_SIZE = getUnallocatedSpace("E:\\");

    // 默认up主
    public static final String DEFAULT_UPLOADER = "北宇治字幕组";

    public static final Date DEFAULT_POST_DATE = DateUtil.date();

    public static String getUnallocatedSpace(String drive){
        try {
            // 获取 E 盘的路径
            Path eDrive = FileSystems.getDefault().getPath(drive);

            // 获取文件存储信息
            FileStore fileStore = Files.getFileStore(eDrive);

            long unallocatedSpace = fileStore.getUnallocatedSpace();

            return (unallocatedSpace / 1024 / 1024 / 1024 / 8) * 10 + "GB";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0KB";
    }
}
