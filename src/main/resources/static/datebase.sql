create table resource_info
(
    uuid            varchar(36)                               not null comment '主键',
    simple_name     varchar(50)                               null comment '资源简名',
    name            varchar(200)                               not null comment '资源名称',
    detail_url      varchar(100)                              not null comment '资源明细地址',
    magnet_uri      varchar(40)                               not null comment '资源磁力链接',
    total_file_size varchar(10)                               null comment '资源大小',
    total_file_num  int                                       null comment '资源文件总数',
    type            varchar(10)                               null comment '资源类型',
    uploader        varchar(20) default '热心市民'            not null comment '资源上传者',
    resolution      varchar(10)                               null comment '资源分辨率',
    posted_time     datetime    default '1970-01-01 00:00:00' null comment '发表时间',
    create_time     datetime    default current_timestamp     not null comment '创建时间',
    update_time     datetime    default current_timestamp     not null on update CURRENT_TIMESTAMP() comment '更新时间',
    remark          varchar(255)                              null comment '备注',
    constraint resource_info_pk
        primary key (uuid)
)
    comment '资源信息表';

