package com.yuki.animedownloader.model;

import cn.hutool.core.text.CharSequenceUtil;
import com.yuki.animedownloader.constants.FileSizeUnitConstants;
import lombok.Data;

@Data
public class FileSize {

    private int size;

    private String unit;

    public FileSize(int size, String unit){
        this.size = size;
        this.unit = unit;
    }

    public static FileSize of(String fileSizeStr){
        if (CharSequenceUtil.isBlank(fileSizeStr)){
            return new FileSize(0, FileSizeUnitConstants.MB);
        }
        return null;
    }
}
