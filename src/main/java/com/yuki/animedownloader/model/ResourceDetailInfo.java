package com.yuki.animedownloader.model;

import lombok.Data;

import java.util.Date;

@Data
public class ResourceDetailInfo {

    // 主键
    private String detailUuid;

    // 文件名称
    private String fileName;

    // 文件大小
    private String fileSize;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 备注
    private String remark;
}
