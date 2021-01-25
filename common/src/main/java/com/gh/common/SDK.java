package com.gh.common;

import com.gh.common.toolsclass.FinalProperties;
import com.gh.common.service.DateUtils;
import com.gh.common.service.impl.Datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class SDK {

    /**
     * 日期时间操作工具
     * @return DateUtils
     */
    public static DateUtils getDateUtils() {
        return new Datetime<Date>();
    }

    public static void main(String[] args) throws ParseException {
        /*SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.DAY.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.HOUR.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.MINUTE.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.SECOND.get());*/
//        System.out.println(SDK.getDateUtils().getDatetimeAddOrMinus(System.currentTimeMillis(), 1, FinalProperties.FORMAT_DATETIME));

//        String timeStampToString = SDK.getDateUtils().getTimeStampToString(System.currentTimeMillis(), FinalProperties.FORMAT_DATETIME);
//        System.err.println(timeStampToString);

//        long stringToTimeStamp = SDK.getDateUtils().getStringToTimeStamp("2021-01-25 09:00:00", FinalProperties.FORMAT_DATE);
//        System.err.println(stringToTimeStamp);

        Date stringToDate = SDK.getDateUtils().getStringToDate("2021-01-25 09:00:00", FinalProperties.FORMAT_DATETIME);
        System.err.println(stringToDate);
    }
}
