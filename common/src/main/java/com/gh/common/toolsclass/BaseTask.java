package com.gh.common.toolsclass;

import org.springframework.aop.framework.AopContext;

import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/9 18:27
 */
public abstract class BaseTask implements Runnable {

    /**
     * 调度任务入参
     */
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void run() {
        getBaseTask().start(this.params);
    }

    /**
     * 手动添加代理，否则AOP拦截不到
     * @return BaseTask
     */
    private BaseTask getBaseTask() {
        return AopContext.currentProxy() != null ? (BaseTask) AopContext.currentProxy() : this;
    }

    /**
     * 自定义启动方法
     * 继承当前BaseTask类之后，实现该start抽象方法，在该方法里编写所需定时执行的方法
     * @param params Map<String, Object> SysTaskJobPlan实体类的taskCustomParameters元素
     */
    public abstract void start(Map<String, Object> params);
}

