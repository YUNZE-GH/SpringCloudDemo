package com.gh.common.toolsclass;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/9 18:27
 */
public abstract class BaseTask implements Runnable {

    private Map<String, ?> params;

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    @Override
    public void run() {
        System.err.println(JSON.toJSONString(this.getParams()));
        this.saveParamsToSession();
    }

    public void saveParamsToSession() {
        System.err.println(Thread.currentThread().getName());
    }
}

