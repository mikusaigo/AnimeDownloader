package com.yuki.animedownloader.config;

import cn.hutool.core.text.CharSequenceUtil;
import com.yuki.animedownloader.model.resourcetype.ResourceType;
import com.yuki.animedownloader.model.resourcetype.ResourceTypeBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Data
@ConfigurationProperties(prefix = "download")
public class DownloadConfig {

    private String basePath;

    public DownloadConfig() {
    }

    public String getPath(String type){
        ResourceType resourceType = ResourceTypeBuilder.build(type);
        if (null == resourceType){
            throw new RuntimeException("资源类型错误");
        }
        return CharSequenceUtil.join(File.separator, this.basePath, resourceType.getSavePath());
    }
}
