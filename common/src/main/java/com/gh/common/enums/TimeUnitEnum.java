package com.gh.common.enums;

public enum TimeUnitEnum {
    // 0：年，1：月，2：天，3：小时，4：分钟，5：秒，6：毫秒
    YEAR(0),
    MONTH(1),
    DAY(2),
    HOUR(3),
    MINUTE(4),
    SECOND(5),
    MILLISECOND(6);

    private int value;

    TimeUnitEnum(int value){
        this.value = value;
    }

    public int get(){
        return this.value;
    }
}
