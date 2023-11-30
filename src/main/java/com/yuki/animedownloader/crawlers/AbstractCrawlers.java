package com.yuki.animedownloader.crawlers;

import lombok.Data;

@Data
public abstract class AbstractCrawlers{

    protected String targetUrl;

    protected AbstractCrawlers(){

    }

    protected AbstractCrawlers(String targetUrl){
        this.targetUrl = targetUrl;
    }

    protected abstract String crawl();

    protected abstract String getBaseUrl();

    protected abstract void buildUrl(String suffixUrl);

}
