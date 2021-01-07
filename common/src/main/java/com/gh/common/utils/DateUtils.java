package com.gh.common.utils;

import java.text.ParseException;
import java.util.Map;

/**
 * 日期时间API
 */
public interface DateUtils<T> {

    /**
     * 获取指定格式的当前日期时间
     *
     * @param format 格式，例：yyyy-MM-dd
     * @return 2020-12-31
     */
    String getDateTime(String format);

    /**
     * 根据指定日期时间和指定格式获取日期时间
     *
     * @param datetime 指定日期时间，支持Date、String、Long类型
     * @param format   日期格式 例：yyyy-MM-dd HH:mm:ss
     * @return 2020-12-31 00:00:00
     * @throws ParseException 日期格式转换异常
     */
    String getFormattedDateTime(T datetime, String format) throws ParseException;

    /**
     * 获取当前日期
     *
     * @return 2020-12-31
     */
    String getDate();

    /**
     * 获取当前时间
     *
     * @return 12:00:00
     */
    String getTime();

    /**
     * 获取当前日期时间
     *
     * @return 2020-12-31 00:00:00
     */
    String getDatetime();

    /**
     * 获取当前年份
     *
     * @return 2020
     */
    String getYear();

    /**
     * 获取指定日期所在的年份
     *
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 2020
     * @throws ParseException 日期格式转换异常
     */
    String getYear(T datetime) throws ParseException;

    /**
     * 获取当前月份
     *
     * @return 12
     */
    String getMonth();

    /**
     * 获取指定日期所在的月份
     *
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 12
     * @throws ParseException 日期格式转换异常
     */
    String getMonth(T datetime) throws ParseException;

    /**
     * 获取当前日
     *
     * @return 31
     */
    String getDay();

    /**
     * 获取指定日期为多少号
     *
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 31
     * @throws ParseException 日期格式转换异常
     */
    String getDay(T datetime) throws ParseException;

    /**
     * 获取当前日期为星期几
     *
     * @return 星期一
     */
    String getWeek();

    /**
     * 获取指定日期为星期几
     *
     * @param datetime 指定日期，支持Date、String、Long类型
     * @return 星期一
     */
    String getWeek(T datetime) throws ParseException;

    /**
     * 获取当前月第一天的日期
     *
     * @return 2020-12-01
     */
    String getMonthBegin();

    /**
     * 获取某月第一天的日期
     *
     * @param amount 当amount为0时，为本月，-1时为上月，1时为下月
     * @return 例：2020-12-01
     */
    String getMonthBegin(int amount) throws ParseException;

    /**
     * 获取当月最后一天的日期
     *
     * @return 2020-12-31
     */
    String getMonthEnd();

    /**
     * 获取某月最后一天的日期
     *
     * @param amount 当amount为0时，为本月，-1时为上月，1时为下月
     * @return 例：2020-12-31
     */
    String getMonthEnd(int amount) throws ParseException;

    /**
     * 获取几几年的第几周的第一天日期和最后一天日期
     *
     * @param year 几几年，例：2020
     * @param week 第几个星期，例：1
     * @return 例：{
     * "beginDate": "2019-12-30",
     * "endDate": "2020-01-05"
     * }
     */
    Map<String, String> getWeekDatesByYearAndWeek(Integer year, Integer week) throws ParseException;

    /**
     * 获取指定日期日期是几几年的第几周,及该周的第一天日期和最后一天日期
     *
     * @param datetime 指定日期时间，支持Date、String、Long类型
     * @return 例：{
     * "beginDate": "2020-12-28",
     * "week": 1,
     * "year": 2021,
     * "endDate": "2021-01-03"
     * }
     */
    Map<String, Object> getWeekDatesByDatetime(T datetime) throws ParseException;

    /**
     * 获取指定日期处于所在年份的第几周
     *
     * @param datetime 指定日期时间，支持Date、String、Long类型
     * @return 例：1
     */
    int getWeekNumberByDatetime(T datetime) throws ParseException;

    /**
     * 数字日期转中文日期
     *
     * @param datetime datetime 指定日期时间，支持Date、String、Long类型
     * @return 例：二零二一年一月三日
     * @throws ParseException
     */
    String dateToCNDate(T datetime) throws ParseException;

    /**
     * 计算两个日期之间的相差多长时间
     * @param beginDatetime 开始时间 例：1609837425000l
     * @param endDatetime 结束时间 例：1609923825000l
     * @param unit 计算结果单位，0：年，1：月，2：天，3：小时，4：分钟，5：秒，6：毫秒
     * @return 1.0（天）
     */
    float datetimeDiff(long beginDatetime, long endDatetime, int unit);
}
