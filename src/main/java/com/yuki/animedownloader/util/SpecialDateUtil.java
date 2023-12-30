package com.yuki.animedownloader.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialDateUtil {

    public static final Date DAY_BEFORE_YTD = DateUtil.offsetDay(new Date(), -2);

    public static final Date YESTERDAY = DateUtil.yesterday();

    public static final Date TODAY = DateUtil.date();

    public static Date toNormalDate(String dateStr) {
        dateStr = dateStr.trim();
        String patternStr = "(今天|昨天|前天)\\s+(\\d{2}:\\d{2})";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(dateStr);
        if (matcher.find()) {
            String date = switch (matcher.group(1)) {
                case "今天" -> DateUtil.formatDate(TODAY);
                case "昨天" -> DateUtil.formatDate(YESTERDAY);
                case "前天" -> DateUtil.formatDate(DAY_BEFORE_YTD);
                default -> "1970-01-01";
            };
            String hm = CharSequenceUtil.isBlank(matcher.group(2)) ? "00:00" : matcher.group(2);
            return DateUtil.parseDateTime(date + " " + hm + ":00");
        }else {
            return DateUtil.parseDate(dateStr);
        }
    }
}
