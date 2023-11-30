package com.yuki.animedownloader.crawlers;

public class ComicatCrawlers extends HtmlCrawlers{

    @Override
    protected String getBaseUrl() {
        return "http://www.comicat.org";
    }

    @Override
    public void buildUrl(String targetUrl) {
        this.targetUrl = this.getBaseUrl() + targetUrl;
    }
}
