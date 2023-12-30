package com.yuki.animedownloader.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 资源信息
 * @author yukino
 */
@Data
public class ResourceInfo {

    // 主键
    @TableId(type = IdType.INPUT)
    private String uuid;

    // 资源简名
    private String simpleName;

    // 资源名称
    private String name;

    // 资源明细地址
    private String detailUrl;

    // 资源磁力链接
    private String magnetUri;

    // 资源大小
    private String totalFileSize;

    // 资源文件总数
    private String totalFileNum;

    // 资源类型
    private String type;

    // 资源语言
    private String language;

    // 资源上传者
    private String uploader;

    // 资源分辨率
    private String resolution;

    // 发表时间
    private Date postedTime;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 备注
    private String remark;
}
