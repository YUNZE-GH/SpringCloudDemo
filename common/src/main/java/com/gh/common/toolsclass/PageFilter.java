package com.gh.common.toolsclass;

import java.time.LocalDateTime;

/**
 * 数据过滤扩展
 * @author gaohan
 * @version 1.0
 * @date 2021/8/1 15:51
 */
public class PageFilter<T> {
    /**
     * 业务表过滤数据
     */
    private T data;

    /**
     * 页数
     */
    private Integer page = 0;

    /**
     * 每页信息数
     */
    private Integer limit = 0;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
