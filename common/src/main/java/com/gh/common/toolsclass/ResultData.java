package com.gh.common.toolsclass;

import java.io.Serializable;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/11/5 23:42
 */
public class ResultData implements Serializable {

    private int code;
    private String datetime;
    private String message;
    private int total;
    private Object data;

    public ResultData() {

    }

    public ResultData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultData(int code, int total, Object data) {
        this.code = code;
        this.total = total;
        this.data = data;
    }

    public ResultData(int code, Object data, String message, String datetime) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.datetime = datetime;
    }

    public ResultData(int code, int total, Object data, String message, String datetime) {
        this.code = code;
        this.total = total;
        this.data = data;
        this.message = message;
        this.datetime = datetime;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"datetime\":\"")
                .append(datetime).append('\"');
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append(",\"total\":")
                .append(total);
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
