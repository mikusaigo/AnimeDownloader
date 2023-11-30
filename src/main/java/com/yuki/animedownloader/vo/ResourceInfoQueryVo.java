package com.yuki.animedownloader.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceInfoQueryVo {


    private String resourceName;

    private Date postedTime;

    private String uploader;

    private String minResourceSize;

    private String maxResourceSize;

    private String language;

}
