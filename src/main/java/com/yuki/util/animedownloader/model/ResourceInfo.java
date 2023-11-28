package com.yuki.util.animedownloader.model;

import lombok.Data;

import java.util.Date;

/**
 * 资源信息
 * @author yukino
 */
@Data
public class ResourceInfo {

    // 主键
    private String uuid;

    // 资源简名
    private String resourceSimpleName;

    // 资源名称
    private String resourceName;

    // 资源明细地址
    private String resourceDetailUrl;

    // 资源磁力链接
    private String resourceMagnetUri;

    // 资源大小
    private String resourceSize;

    // 资源类型
    private String resourceType;

    // 资源上传者
    private String resourceUploader;

    // 发表日期
    private Date postedDate;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;
}
