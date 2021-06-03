package com.gh.common;

import com.gh.common.service.DateUtils;
import com.gh.common.service.EncryptionUtils;
import com.gh.common.service.LocalDateTimeUtils;
import com.gh.common.service.impl.DatetimeUtilsImpl;
import com.gh.common.service.impl.EncryptionUtilsImpl;
import com.gh.common.service.impl.LocalDateTimeUtilsImpl;
import com.gh.common.toolsclass.FinalProperties;

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
    public static EncryptionUtils encryptionUtils(){return new EncryptionUtilsImpl();}

    public static void main(String[] args) throws Exception {
        /*LocalDateTime date = SDK.getLocalDateTimeUtils().stringToLocalDateTime("2021-04-29 00:00:00", FinalProperties.FORMAT_DATETIME);

        System.err.println(SDK.getLocalDateTimeUtils().getMonthBegin(date, LocalDateTime.class));
        System.err.println(SDK.getLocalDateTimeUtils().getMonthEnd(date, LocalDateTime.class));*/
    }
}
