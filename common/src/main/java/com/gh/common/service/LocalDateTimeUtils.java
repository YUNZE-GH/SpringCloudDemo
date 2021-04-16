package com.gh.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface LocalDateTimeUtils<T> {

    /**
     * 获取DateTimeFormatter格式化对象,默认格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    DateTimeFormatter getDateTimeFormatter();

    /**
     * 获取指定格式的DateTimeFormatter格式化对象
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return
     */
    DateTimeFormatter getDateTimeFormatter(String format);

    /**
     * 将LocalDateTime日期转为指定格式的字符串
     * @param date LocalDateTime日期数据
     * @param format yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return
     * @throws Exception
     */
    String localDateTimeToString(LocalDateTime date, String format);

    /**
     * 将指定格式的日期字符串转为LocalDateTime类型的日期
     * @param date 日期字符串
     * @param format 日期字符串的格式，yyyy-MM-dd HH:mm:ss或yyyy-MM-dd
     * @return
     */
    LocalDateTime stringToLocalDateTime(String date, String format) throws Exception;

}
