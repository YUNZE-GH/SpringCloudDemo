package com.gh.common;

import com.gh.common.service.DateUtils;
import com.gh.common.service.EncryptionUtils;
import com.gh.common.service.LocalDateTimeUtils;
import com.gh.common.service.impl.DatetimeUtilsImpl;
import com.gh.common.service.impl.EncryptionUtilsImpl;
import com.gh.common.service.impl.LocalDateTimeUtilsImpl;

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
    public static LocalDateTimeUtils getLocalDateTimeUtils(){return new LocalDateTimeUtilsImpl();
    }

    /**
     * 加密工具类
     * @return EncryptionUtils
     */
    public static EncryptionUtils encryptionUtils(){return new EncryptionUtilsImpl();
    }
}
