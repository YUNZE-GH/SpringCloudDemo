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
public class LocalDateTimeUtilsImpl<T> implements LocalDateTimeUtils<T> {

    @Override
    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(FinalProperties.FORMAT_DATETIME);
    }

    @Override
    public DateTimeFormatter getDateTimeFormatter(String format) {
        return DateTimeFormatter.ofPattern(format);
    }

    @Override
    public String localDateTimeToString(LocalDateTime date, String format) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        return getDateTimeFormatter(format).format(date);
    }

    @Override
    public LocalDateTime stringToLocalDateTime(String date, String format) throws Exception {
        if (StringUtils.isEmpty(date)) {
            throw new Exception("date不能为空！");
        }
        return LocalDateTime.parse(date, this.getDateTimeFormatter(format));
    }

    public <T> T theBeginOfTheDay(LocalDateTime date, Class<T> clazz){
        LocalDateTime dateTime = LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);

        return null;
    }

    private <T> T localDateTimeToType(LocalDateTime date, Class<T> clazz) {
        if (clazz == LocalDateTime.class) {
            return (T) date;
        } else if (clazz == String.class) {
            return (T) this.localDateTimeToString(date, FinalProperties.FORMAT_DATETIME);
        } else if (clazz == Long.class) {
            return (T) date.toEpochSecond(ZoneOffset.of("+8"));
        }
        return null;
    }

}
