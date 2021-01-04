package com.gh.common.enums;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/11/10 0:08
 */
public enum CodeEnum {
    SUCCESS(0),
    BUSINESS_ERROR(1),      // 业务异常
    AUTHENTICATION_ERROR(2),// 权限异常
    SYSTEM_ERROR(3),        // 系统异常
    PERMISSION_ERROR(4),    // 允许异常
    PARAMS_ERROR(5),        // 参数异常
    FORMAT_ERROR(6),        // 格式化异常
    OTHER_ERROR(9);         // 其他异常

    private int value;

    CodeEnum(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }
}
