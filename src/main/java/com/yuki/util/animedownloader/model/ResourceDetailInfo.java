package com.yuki.util.animedownloader.model;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceDetailInfo {

    private String detailUuid;

    private String fileName;

    private String fileSize;

    private Date createTime;

    private Date updateTime;

    private String remark;
}
