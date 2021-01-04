package com.gh.common;

import com.gh.common.enums.CodeEnum;
import com.gh.common.utils.Datetime;
import com.gh.common.utils.DateUtils;

import java.util.Date;

public class SDK {

    public static DateUtils getDateUtils() {
        return new Datetime<Date>();
    }

    public static void main(String[] args) {
        System.err.println(SDK.getDateUtils().getDatetime());
        System.err.println(CodeEnum.SUCCESS.get());
    }
}
