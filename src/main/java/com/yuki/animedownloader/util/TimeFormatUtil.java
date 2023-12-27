package com.yuki.animedownloader.util;

import java.util.Calendar;

public class TimeFormatUtil {

    /**
     * 将毫秒数转化为天 时 分 秒 ，毫秒数部分向上取整
     * 如123456700 转化后为 1天10小时17分37秒
     * 12345670 转化后为 3小时25分47秒
     *
     * @param milliseconds 要转化的毫秒数
     * @return 转化后的格式
     */
    public static String toNormalTime(long milliseconds){
        long second = (long) Math.ceil((double) milliseconds / 1000);
        if (second > 60){
            long minute = second / 60;
            long otherS = second % 60;
            if (minute > 60){
                long hour = minute / 60;
                long otherM = minute % 60;
                if (hour > 24){
                    long day = hour / 24;
                    if (day > Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR)){
                        return "超过一年";
                    }
                    long otherH = hour % 24;
                    return day + "天" + otherH + "小时" + otherM + "分" + otherS + "秒";
                }
                return hour + "小时" + otherM + "分" + otherS + "秒";
            }
            return minute + "分" + otherS + "秒";
        }
        return second + "秒";
    }

}
