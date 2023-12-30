package com.yuki.animedownloader.util;

import cn.hutool.core.util.NumberUtil;
import com.yuki.animedownloader.enums.FileSizeUnitEnum;
import com.yuki.animedownloader.model.FileSize;

public class FileUtils {

    public static FileSize parseToKB(FileSize source){
        return parse(source, FileSizeUnitEnum.KB);
    }

    public static FileSize parseToMB(FileSize source){
        return parse(source, FileSizeUnitEnum.KB);
    }

    public static FileSize parseToGB(FileSize source){
        return parse(source, FileSizeUnitEnum.KB);
    }

    public static FileSize parse(FileSize source, FileSizeUnitEnum sizeUnit){
        if (null == source
                || null == source.getUnit()
                || null == sizeUnit){
            return null;
        }
        double size = source.getSize();

        if (size < 0 ){
            return FileSize.of(0, sizeUnit);
        }

        if (source.getUnit().equals(sizeUnit)){
            return source;
        }
        StringBuilder equation = new StringBuilder(size + "* ");
        if (source.getUnit().equals(FileSizeUnitEnum.KB)){
            switch (sizeUnit){
                case MB -> equation.append("1 / 1024");
                case GB -> equation.append("1 / (1024 * 1024)");
                case TB -> equation.append("1 / (1024 * 1024 * 1024)");
            }
        }
        if (source.getUnit().equals(FileSizeUnitEnum.MB)){
            switch (sizeUnit){
                case KB -> equation.append("1024L");
                case GB -> equation.append("1 / 1024");
                case TB -> equation.append("1 / (1024 * 1024)");
            }
        }
        if (source.getUnit().equals(FileSizeUnitEnum.GB)){
            switch (sizeUnit){
                case KB -> equation.append("1024 * 1024");
                case MB -> equation.append("1024L");
                case TB -> equation.append("1 / 1024");
            }
        }
        if (source.getUnit().equals(FileSizeUnitEnum.TB)){
            switch (sizeUnit){
                case KB -> equation.append("1024 * 1024 * 1024");
                case MB -> equation.append("1024 * 1024");
                case GB -> equation.append("1024");
            }
        }
        double result = NumberUtil.calculate(equation.toString());
        return FileSize.of(result, sizeUnit);
    }
}
