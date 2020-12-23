package com.gh.common.api.service;

import java.util.Map;

/**
 * 日期时间API
 */
public interface DateAPI {

    /*获取当前日期*/
    String getDate();

    /*获取当前日期规定格式*/
    String getDate(String str);

    /*获取当前时间*/
    String getTime();

    /*获取当前日期时间*/
    String getDatetime();

    /*获取当前年份*/
    String getYear();

    /*获取当前月份*/
    String getMonth();

    /*获取当前日*/
    String getDay();

    /*获取当前星期*/
    String getWeekDay();

    /*获取指定日期的星期*/
    String getWeekDay(long timestamp);

    /*获取当前月第一天的日期*/
    String getMonthBegin();

    /*获取当前月第一天的日期*/
    String getMonthBegin(int amount);

    /*获取当月最后一天的日期*/
    String getMonthEnd();

    /*获取当月最后一天的日期*/
    String getMonthEnd(int amount);

    /*获取几几年的第几周的第一天日期和最后一天日期*/
    Map<String,String> getWeekDaysByYearAndWeek(Integer year, Integer week);

    /*获取某一日期是几几年的第几周,及本周第一天日期和最后一天日期*/
    Map<String,Object> getNumberWeek(long timestamp);

    /*将日期转为中文日期*/
    String getDateChangeaChinese(String date);

    /*计算两个日期时间之间相差的毫秒*/
    long getDatetimeDiffMillisecond(long beginTime, long endTime);

    /*计算两个日期时间之间相差的秒*/
    long getDatetimeDiffSecond(long beginTime, long endTime);

    /*计算两个日期时间之间相差的分钟*/
    long getDatetimeDiffMinute(long beginTime, long endTime);

    /*计算两个日期时间之间相差的小时*/
    long getDatetimeDiffHour(long beginTime, long endTime);

    /*计算两个日期时间之间相差的天数*/
    long getDatetimeDiffDay(long beginTime, long endTime);

    /*计算日期加减几天之后的日期*/
    String getDatetimeAddOrMinus(long timestamp,int day);

    /*将时间戳转为日期格式*/
    String getTimeStampChangeDateTime(long timestamp);

}
