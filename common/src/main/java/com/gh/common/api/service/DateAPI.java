package com.gh.common.api.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 日期时间API
 */
public interface DateAPI<T> {

    /**
     * 获取指定格式的当前日期时间
     * @param format 格式，例：yyyy-MM-dd
     * @return 2020-12-31
     */
    String getDateTime(String format);

    /**
     * 根据指定日期时间和指定格式获取日期时间
     * @param datetime 指定日期时间，支持Date、String、Long类型
     * @param format 日期格式 例：yyyy-MM-dd HH:mm:ss
     * @return 2020-12-31 00:00:00
     * @throws ParseException 日期格式转换异常
     */
    public String getFormattedDateTime(T datetime, String format) throws ParseException;

    /**
     * 获取当前日期
     * @return 2020-12-31
     */
    String getDate();

    /**
     * 获取当前时间
     * @return 12:00:00
     */
    public String getTime();

    /**
     * 获取当前日期时间
     * @return 2020-12-31 00:00:00
     */
    public String getDatetime();

    /**
     * 获取当前年份
     * @return 2020
     */
    public String getYear();

    /**
     * 获取指定日期所在的年份
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 2020
     * @throws ParseException 日期格式转换异常
     */
    public String getYear(T datetime) throws ParseException;

    /**
     * 获取当前月份
     * @return 12
     */
    public String getMonth();

    /**
     * 获取指定日期所在的月份
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 12
     * @throws ParseException 日期格式转换异常
     */
    public String getMonth(T datetime) throws ParseException;

    /**
     * 获取当前日
     * @return 31
     */
    public String getDay();

    /**
     * 获取指定日期为多少号
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 31
     * @throws ParseException 日期格式转换异常
     */
    public String getDay(T datetime) throws ParseException;

    /**
     * 获取当前日期为星期几
     * @return 星期一
     */
    public String getWeek();

    /**
     * 获取指定日期为星期几
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return
     */
    public String getWeek(T datetime) throws ParseException;

    /**
     * 获取当前月第一天的日期
     * @return 2020-12-01
     */
    public String getMonthBegin();

    /**
     * 获取当月最后一天的日期
     * @return 2020-12-31
     */
    public String getMonthEnd();

    /**
     * 获取某月第一天的日期
     * @param amount 当amount为0时，为本月，-1时为上月，1时为下月
     * @return 例：2020-12-01
     */
    public String getMonthBegin(int amount) throws ParseException;

}
