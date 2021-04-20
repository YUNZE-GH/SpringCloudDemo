package com.gh.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface LocalDateTimeUtils {

    /**
     * 获取DateTimeFormatter格式化对象,默认格式yyyy-MM-dd HH:mm:ss
     *
     * @return DateTimeFormatter
     */
    DateTimeFormatter getDateTimeFormatter();

    /**
     * 获取指定格式的DateTimeFormatter格式化对象
     *
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return DateTimeFormatter
     */
    DateTimeFormatter getDateTimeFormatter(String format);

    /**
     * 将LocalDateTime日期转为字符串
     *
     * @param date LocalDateTime日期数据
     * @return String
     */
    String localDateTimeToString(LocalDateTime date) throws Exception;

    /**
     * 将LocalDateTime日期转为指定格式的字符串
     *
     * @param date   LocalDateTime日期数据
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return String
     */
    String localDateTimeToString(LocalDateTime date, String format);

    /**
     * 将LocalDateTime类型日期转换为指定格式为clazz的日期
     *
     * @param date  日期数据
     * @param clazz 指定转换数据类型
     * @param <T>   返回数据类型为指定数据类型
     * @return <T>
     * @throws Exception 只支持转换为LocalDateTime、LocalDate、toLocalTime、String、Long
     */
    <T> T localDateTimeToType(LocalDateTime date, Class<T> clazz) throws Exception;

    /**
     * 将指定格式的日期字符串转为LocalDateTime类型的日期
     *
     * @param date   日期字符串
     * @param format 日期字符串的格式，yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return LocalDateTime
     */
    LocalDateTime stringToLocalDateTime(String date, String format) throws Exception;

    /**
     * 获取指定日期的一天的开始时间
     * 例：传入2021-04-16 10:00:00,则返回2021-04-16 00:00:00
     *
     * @param date  指定日期 designated date
     * @param clazz 指定返回类型 designate result type
     */
    <T> T beginOfTheDay(LocalDateTime date, Class<T> clazz) throws Exception;

    /**
     * 获取指定日期加上多少天之后的一天的开始时间
     * 例：传入2021-04-16 10:00:00,plusDays为1,则返回2021-04-17 00:00:00
     *
     * @param date     指定日期 designated date
     * @param plusDays 加上多少天
     * @param clazz    指定返回类型 designate result type
     */
    <T> T beginOfTheDay(LocalDateTime date, Integer plusDays, Class<T> clazz) throws Exception;

    /**
     * 获取指定日期的一天的结束时间
     * 例：传入2021-04-16 10:00:00,则返回2021-04-16 23:59:59
     *
     * @param date  指定日期 designated date
     * @param clazz 指定返回类型 designate result type
     */
    <T> T endOfTheDay(LocalDateTime date, Class<T> clazz) throws Exception;

    /**
     * 获取指定日期加上多少天之后的一天的结束时间
     * 例：传入2021-04-16 10:00:00,plusDays为1,则返回2021-04-17 23:59:59
     *
     * @param date     指定日期 designated date
     * @param plusDays 加上多少天
     * @param clazz    指定返回类型 designate result type
     */
    <T> T endOfTheDay(LocalDateTime date, Integer plusDays, Class<T> clazz) throws Exception;

}
