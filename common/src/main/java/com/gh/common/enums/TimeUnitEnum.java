package com.gh.common.enums;

public enum TimeUnitEnum {
    YEAR(0),
    HOUR(1);

    private int value;

    TimeUnitEnum(int value){
        this.value = value;
    }

    private int get(){
        return this.value;
    }
}
