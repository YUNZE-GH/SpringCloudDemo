package com.gh.common.toolsclass;

import com.gh.common.enums.CodeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2020/11/5 23:42
 */
public class ResultData<T> implements Serializable {

    private int code;
    private String datetime = LocalDateTime.now().toString();
    private String message = "成功";
    private int total;
    private T data;

    public ResultData() {

    }

    /**
     * @param code 返回编码
     * @param data 返回数据
     */
    public ResultData(int code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * @param code    返回编码
     * @param data    返回数据
     * @param message 备注
     */
    public ResultData(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * @param code     返回编码
     * @param data     返回数据
     * @param message  备注
     * @param datetime 返回时间
     */
    public ResultData(int code, T data, String message, String datetime) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.datetime = datetime;
    }

    /**
     * @param code  返回编码
     * @param total 数据量
     * @param data  返回数据
     */
    public ResultData(int code, int total, T data) {
        this.code = code;
        this.total = total;
        this.data = data;
    }

    /**
     * @param code    返回编码
     * @param total   数据量
     * @param data    返回数据
     * @param message 备注
     */
    public ResultData(int code, int total, T data, String message) {
        this.code = code;
        this.total = total;
        this.data = data;
        this.message = message;
    }

    /**
     * @param code     返回编码
     * @param total    数据量
     * @param data     返回数据
     * @param message  备注
     * @param datetime 返回时间
     */
    public ResultData(int code, int total, T data, String message, String datetime) {
        this.code = code;
        this.total = total;
        this.data = data;
        this.message = message;
        this.datetime = datetime;
    }

    /**
     * 成功
     *
     * @return ResultData
     */
    public static ResultData success() {
        return new ResultData(CodeEnum.SUCCESS.get(), null);
    }

    /**
     * 成功
     *
     * @param data 成功返回数据
     * @return ResultData
     */
    public static ResultData success(Object data) {
        return new ResultData(CodeEnum.SUCCESS.get(), data);
    }

    /**
     * 成功
     *
     * @param total 数据量
     * @param data  成功返回数据
     * @return ResultData
     */
    public static ResultData success(Integer total, Object data) {
        return new ResultData(CodeEnum.SUCCESS.get(), total, data);
    }

    /**
     * 失败
     *
     * @param msg 失败提示
     * @return ResultData
     */
    public static ResultData error(String msg) {
        return new ResultData(CodeEnum.BUSINESS_ERROR.get(), null, msg);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
