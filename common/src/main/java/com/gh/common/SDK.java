package com.gh.common;

import com.gh.common.service.DateUtils;
import com.gh.common.service.EncryptionUtils;
import com.gh.common.service.JwtUtils;
import com.gh.common.service.LocalDateTimeUtils;
import com.gh.common.service.impl.DatetimeUtilsImpl;
import com.gh.common.service.impl.EncryptionUtilsImpl;
import com.gh.common.service.impl.JwtUtilsImpl;
import com.gh.common.service.impl.LocalDateTimeUtilsImpl;
import com.gh.common.toolsclass.FinalProperties;
import sun.security.provider.MD5;

import java.time.LocalDateTime;
import java.util.Date;

public class SDK {

    /**
     * Date类型日期时间操作工具
     * @return DateUtils
     */
    public static DateUtils getDateUtils() {
        return new DatetimeUtilsImpl<Date>();
    }

    /**
     * LocalDateTime数据类型日期时间操作工具
     * @return LocalDateTimeUtils
     */
    public static LocalDateTimeUtils getLocalDateTimeUtils(){return new LocalDateTimeUtilsImpl();}

    /**
     * 加密工具类
     * @return EncryptionUtils
     */
    public static EncryptionUtils encryptionUtils() throws Exception {return new EncryptionUtilsImpl();}

    /**
     * JSON WEB TOKEN工具
     * @return JwtUtilsImpl
     */
    public static JwtUtils JWT() throws Exception {return new JwtUtilsImpl();}

    public static void main(String[] args) throws Exception {
        /*LocalDateTime date = SDK.getLocalDateTimeUtils().stringToLocalDateTime("2021-04-29 00:00:00", FinalProperties.FORMAT_DATETIME);

        System.err.println(SDK.getLocalDateTimeUtils().getMonthBegin(date, LocalDateTime.class));
        System.err.println(SDK.getLocalDateTimeUtils().getMonthEnd(date, LocalDateTime.class));*/
        String s = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyQWNjb3VudCI6ImFkbWluIiwiZXhwIjoxNjIzMzgyNTg3LCJ1c2VySWQiOiIxMDAxIn0.h2KTO4AG0Vw8gPTNCWyYvF6Ew4KC_nIq6kTXkru-s_Y";
        String aes = SDK.encryptionUtils().encryptionAES(s);
        System.err.println(aes);

    }
}
