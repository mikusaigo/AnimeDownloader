package com.yuki.animedownloader.model;

import cn.hutool.core.text.CharSequenceUtil;
import com.yuki.animedownloader.enums.FileSizeUnitEnum;
import lombok.Data;

@Data
public class FileSize {

    private double size;

    private FileSizeUnitEnum unit;

    private FileSize(double size, FileSizeUnitEnum unit){
        this.size = size;
        this.unit = unit;
    }

    public static FileSize of(String fileSizeStr){
        if (CharSequenceUtil.isBlank(fileSizeStr)){
            return FileSize.of(0, FileSizeUnitEnum.MB);
        }
        return null;
    }

    public static FileSize of(double size, FileSizeUnitEnum unit){
        return new FileSize(0, FileSizeUnitEnum.MB);
    }

    @Override
    public String toString() {
        return size + unit.getUnit();
    }
}
