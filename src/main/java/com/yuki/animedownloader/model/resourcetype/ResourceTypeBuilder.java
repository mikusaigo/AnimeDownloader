package com.yuki.animedownloader.model.resourcetype;

import com.yuki.animedownloader.enums.SourceTypeEnum;

public class ResourceTypeBuilder {

    public static ResourceType build(String type){
        if (SourceTypeEnum.MUSIC.getType().equals(type)){
            return new Music();
        }
        if (SourceTypeEnum.ANIME.getType().equals(type)){
            return new Anime();
        }
        return null;
    }
}
