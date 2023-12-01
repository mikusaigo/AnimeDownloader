package com.yuki.animedownloader.enums;

/**
 * 资源类型枚举
 * @author yukino
 */
public enum SourceTypeEnum {

    ANIME("ANIME", "动画"),

    PERIPHERALS("PERIPHERALS", "周边"),

    MUSIC("MUSIC", "音乐"),

    CARTOON("CARTOON", "漫画"),

    UNKNOWN("UNKNOWN", "未知");

    final String type;
    final String name;

    SourceTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameToCode(String name){
        SourceTypeEnum[] values = SourceTypeEnum.values();
        for (SourceTypeEnum value : values) {
            if (value.getName().equals(name)){
                return value.getType();
            }
        }
        return UNKNOWN.getType();
    }
}
