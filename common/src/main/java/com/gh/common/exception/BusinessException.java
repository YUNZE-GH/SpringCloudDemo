package com.gh.common.exception;

import com.gh.common.enums.CodeEnum;

/**
 * 自定义异常类-业务异常
 * @author gaohan
 * @version 1.0
 * @date 2021/8/1 20:16
 */
public class BusinessException extends RuntimeException {

    private final Integer code = CodeEnum.BUSINESS_ERROR.get();

    private final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
