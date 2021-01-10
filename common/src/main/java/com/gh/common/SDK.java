package com.gh.common;

import com.gh.common.enums.TimeUnitEnum;
import com.gh.common.toolsclass.FinalProperties;
import com.gh.common.utils.DateUtils;
import com.gh.common.utils.Datetime;

import java.util.Date;

public class SDK {

    public static DateUtils getDateUtils() {
        return new Datetime<Date>();
    }

    public static void main(String[] args) {
        /*SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.DAY.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.HOUR.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.MINUTE.get());
        SDK.getDateUtils().datetimeDiff(1609837425000l, 1609923825000l, TimeUnitEnum.SECOND.get());*/
        System.out.println(SDK.getDateUtils().getDatetimeAddOrMinus(System.currentTimeMillis(), 1, FinalProperties.FORMAT_DATETIME));
    }
}
