package com.gh.provider.utils;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/11/10 0:08
 */
public enum CodeEnum {
    SUCCESS(0),
    BUSINESS_ERROR(1),
    AUTHENTICATION_ERROR(2),
    SYSTEM_ERROR(3),
    PERMISSION_ERROR(4),
    PARAMS_ERROR(10),
    EXIT_ERROR(11),
    NO_EXIT_ERROR(12),
    FORMAT_ERROR(13),
    RELATED_ERROR(14),
    OTHER_ERROR(99);

    private int value;

    private CodeEnum(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }
}
