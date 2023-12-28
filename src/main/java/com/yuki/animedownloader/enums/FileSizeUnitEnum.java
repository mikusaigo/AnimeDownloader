package com.yuki.animedownloader.enums;

import lombok.Getter;

@Getter
public enum FileSizeUnitEnum {

    KB("KB"),
    MB("MB"),
    GB("GB"),
    TB("TB");

    private final String unit;

    FileSizeUnitEnum(String unit) {
        this.unit = unit;
    }

    public static FileSizeUnitEnum of(String unit){
        return FileSizeUnitEnum.valueOf(unit);
    }

}
