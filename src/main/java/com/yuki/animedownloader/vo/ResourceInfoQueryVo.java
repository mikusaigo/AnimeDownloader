package com.yuki.animedownloader.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceInfoQueryVo {

    // 资源类型
    private String resourceType;

    // 资源名称
    private String resourceName;

    // 请求时间
    private Date postedTime;

    // 上传人/组织
    private String uploader;

    // 资源大小下限
    private String minResourceSize;

    // 资源大小上限
    private String maxResourceSize;

    // 资源语言
    private String language;

    // 是否保存资源
    private String isSaveResource;

}
