package com.gh.common.service.impl;

import com.gh.common.service.LocalDateTimeUtils;
import com.gh.common.toolsclass.FinalProperties;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * blog-cloud
 *
 * @author 3hgh
 * @version 1.0
 * @date 2021-04-08 12:01
 **/
public class LocalDateTimeUtilsImpl implements LocalDateTimeUtils {

    @Override
    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(FinalProperties.FORMAT_DATETIME);
    }

    @Override
    public DateTimeFormatter getDateTimeFormatter(String format) {
        return DateTimeFormatter.ofPattern(format);
    }

    @Override
    public String localDateTimeToString(LocalDateTime date) throws Exception {
        return this.localDateTimeToType(date, String.class);
    }

    @Override
    public String localDateTimeToString(LocalDateTime date, String format) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        return getDateTimeFormatter(format).format(date);
    }

    @Override
    public <T> T localDateTimeToType(LocalDateTime date, Class<T> clazz) throws Exception {
        if (clazz == LocalDateTime.class) {
            return (T) date;
        } else if (clazz == LocalDate.class) {
            return (T) date.toLocalDate();
        } else if (clazz == LocalTime.class) {
            return (T) date.toLocalTime();
        } else if (clazz == String.class) {
            return (T) this.localDateTimeToString(date, FinalProperties.FORMAT_DATETIME);
        } else if (clazz == Long.class) {
            Long time = (Long) date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
            return (T) time;
        } else {
            throw new Exception("不支持转换为" + clazz.getName() + "类型！");
        }
    }

    @Override
    public LocalDateTime stringToLocalDateTime(String date, String format) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new Exception("date不能为空！");
        }
        return LocalDateTime.parse(date, this.getDateTimeFormatter(format));
    }

    @Override
    public <T> T beginOfTheDay(LocalDateTime date, Class<T> clazz) throws Exception {
        return this.beginOfTheDay(date, 0, clazz);
    }

    @Override
    public <T> T beginOfTheDay(LocalDateTime date, Integer plusDays, Class<T> clazz) throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
        dateTime = dateTime.plusDays(plusDays);
        return this.localDateTimeToType(dateTime, clazz);
    }

    @Override
    public <T> T endOfTheDay(LocalDateTime date, Class<T> clazz) throws Exception {
        return this.endOfTheDay(date, 0, clazz);
    }

    @Override
    public <T> T endOfTheDay(LocalDateTime date, Integer plusDays, Class<T> clazz) throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
        dateTime = dateTime.plusDays(plusDays);
        return this.localDateTimeToType(dateTime, clazz);
    }
}
