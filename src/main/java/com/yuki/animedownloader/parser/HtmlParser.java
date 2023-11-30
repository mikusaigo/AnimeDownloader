package com.yuki.animedownloader.parser;

import com.yuki.animedownloader.enums.SourceTypeEnum;
import com.yuki.animedownloader.model.ResourceInfo;
import com.yuki.animedownloader.util.ResourceUtil;
import com.yuki.animedownloader.util.SpecialDateUtil;
import com.yuki.animedownloader.util.UuidUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlParser extends AbstractParser<String, ResourceInfo> {

    public static final String BASE_URL = "https://www.comicat.org/";

    public List<ResourceInfo> parse(String htmlContent) {
        Document document = Jsoup.parse(htmlContent);
        Elements rows = document.select("#data_list tr");
        List<ResourceInfo> dataList = new ArrayList<>();
        for (Element row : rows) {
            Elements tds = row.select("td");
            ResourceInfo resourceInfo = new ResourceInfo();
            // 资源发表日期
            resourceInfo.setPostedTime(SpecialDateUtil.toNormalDate(tds.get(0).text()));
            // 资源类型
            resourceInfo.setType(SourceTypeEnum.nameToCode(tds.get(1).text()));

            Element td2 = tds.get(2);
            // 资源名称
            resourceInfo.setName(td2.text());
            resourceInfo.setResolution(ResourceUtil.getDPI(resourceInfo.getName()));
            Elements td1_a = td2.select(">a");
            String href = td1_a.attr("href");
            // 资源明细地址
            resourceInfo.setDetailUrl(href);
            resourceInfo.setMagnetUri(ResourceUtil.getSourceMagnet(resourceInfo.getDetailUrl()));

            // 文件总大小
            resourceInfo.setTotalFileSize(tds.get(3).text());

            // 上传人或组织
            resourceInfo.setUploader(tds.get(7).text());

            resourceInfo.setUuid(UuidUtil.buildUuidByParams(
                    resourceInfo.getName(),
                    resourceInfo.getType(),
                    resourceInfo.getResolution(),
                    resourceInfo.getMagnetUri(),
                    resourceInfo.getUploader(),
                    resourceInfo.getLanguage()
            ));
            dataList.add(resourceInfo);
        }
        return dataList;
    }
}
