package com.gh.common;

import java.io.Serializable;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/11/5 23:42
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID=1L;

    private int code;
    private String message;
    private Object data;

    public ResultData() {

    }

    public ResultData(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResultData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
