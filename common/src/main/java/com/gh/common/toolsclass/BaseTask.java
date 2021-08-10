package com.gh.common.toolsclass;

import org.springframework.aop.framework.AopContext;

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
        getBaseTask().start(this.params);
    }

    private BaseTask getBaseTask() {
        return AopContext.currentProxy() != null ? (BaseTask) AopContext.currentProxy() : this;
    }

    public abstract void start(Map<String, ?> params);
}

