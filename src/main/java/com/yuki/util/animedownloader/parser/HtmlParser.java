package com.yuki.util.animedownloader.parser;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.yuki.util.animedownloader.enums.SourceTypeEnum;
import com.yuki.util.animedownloader.model.ResourceInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser extends AbstractParser{

    public static final String BASE_URL = "https://www.comicat.org/";

    public static void main(String[] args) {
        String respHtml = HttpUtil.get("http://www.comicat.org/2.html");
        Document document = Jsoup.parse(respHtml);
        Element dataList = document.getElementById("data_list");
        Elements rows = dataList.select("tr");
        for (Element row : rows) {
            Elements tds = row.select("td");
            ResourceInfo resourceInfo = new ResourceInfo();
            resourceInfo.setUuid(UUID.randomUUID().toString());
            // 资源发表日期
//            resourceInfo.setPostedDate(DateUtil.parse(tds.get(0).text()));
            // 资源类型
            resourceInfo.setResourceType(SourceTypeEnum.nameToCode(tds.get(1).text()));

            Element td2 = tds.get(2);
            // 资源名称
            resourceInfo.setResourceName(td2.text());
            Elements td1_a = td2.select(">a");
            String href = td1_a.attr("href");
            // 资源明细地址
            resourceInfo.setResourceDetailUrl(href);
            System.out.println(resourceInfo.getResourceName() + "-------" + resourceInfo.getResourceDetailUrl());

            // 资源明细页面显示
            String detailRespHtml = HttpUtil.get(BASE_URL + href);
            Document detail = Jsoup.parse(detailRespHtml);
            detail.setBaseUri("");

        }
    }
}
