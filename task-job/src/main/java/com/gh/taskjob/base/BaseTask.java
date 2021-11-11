package com.gh.taskjob.base;

import com.alibaba.fastjson.JSONObject;
import com.gh.taskjob.modular.SysTaskJobPlan.entity.SysTaskJobPlan;
import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/9 18:27
 */
public abstract class BaseTask implements Runnable {

    @Autowired
    private SysTaskJobPlanService planService;

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

        if (!StringUtils.isEmpty(this.params) && this.params.containsKey("taskCustomParameters") && !StringUtils.isEmpty(this.params.get("taskCustomParameters"))) {
            JSONObject json = JSONObject.parseObject(this.params.get("taskCustomParameters").toString());
            this.params.putAll(json);
        }

        SysTaskJobPlan bo = planService.getById(this.params.get("id").toString());
        /*if (bo == null || (0 == bo.getStatus() && planService.getFutureMap().containsKey(this.params.get("taskId").toString()))) {
            params.put("special", "定时任务调度信息与正在执行任务线程不一致（任务正在执行，但是任务管理信息不存在了，或状态变为了停止）");
        }*/

        getBaseTask().start(this.params);

        /*if (bo == null || 0 == bo.getStatus()) {
            planService.stop(this.params.get("id").toString());
        }*/
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

