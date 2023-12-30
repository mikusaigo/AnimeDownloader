package com.yuki.animedownloader.crawlers;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpUtil;
import com.yuki.animedownloader.vo.ResourceInfoQueryVo;

public abstract class HtmlCrawlers extends AbstractCrawlers{

    @Override
    public String crawl() {
        return HttpUtil.get(this.getTargetUrl());
    }

    @Override
    public <T extends ResourceInfoQueryVo> String buildQueryParams(T queryEntity) {
        StringBuilder sb = new StringBuilder();
        if (CharSequenceUtil.isNotBlank(queryEntity.getResourceName())){
            sb.append(queryEntity.getResourceName());
        }
        if (CharSequenceUtil.isNotBlank(queryEntity.getUploader())){
            sb.append(" ").append(queryEntity.getUploader());
        }
        if (CharSequenceUtil.isNotBlank(queryEntity.getLanguage())){
            sb.append(" ").append(queryEntity.getLanguage());
        }
        return sb.toString();
    }
}
