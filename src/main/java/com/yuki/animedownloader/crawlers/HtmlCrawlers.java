package com.yuki.animedownloader.crawlers;

import cn.hutool.http.HttpUtil;

public abstract class HtmlCrawlers extends AbstractCrawlers{

    @Override
    public String crawl() {
        return HttpUtil.get(this.getTargetUrl());
    }
}
