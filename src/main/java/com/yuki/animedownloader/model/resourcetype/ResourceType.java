package com.yuki.animedownloader.model.resourcetype;

public abstract class ResourceType {

    protected String code;

    protected String name;

    protected ResourceType(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getSavePath(){
        return this.name;
    };
}
