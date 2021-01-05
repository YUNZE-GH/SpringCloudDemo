package com.gh.common.enums;

public enum TimeUnitEnum {
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
