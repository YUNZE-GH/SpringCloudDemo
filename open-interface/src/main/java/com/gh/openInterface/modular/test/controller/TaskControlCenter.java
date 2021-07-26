package com.gh.openInterface.modular.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.job.MyRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/22 23:28
 */
@RestController
@RequestMapping("/quartz/task")
public class TaskControlCenter {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private Map<String, ScheduledFuture> futureMap = new HashMap<>();

    @PostMapping(value = "/startTask")
    public ResultData startTask(@RequestBody JSONObject jsonObject) throws Exception {
        boolean type = jsonObject.getBoolean("type");
        String classPath = jsonObject.getString("classPath");
        String uuid = UUID.randomUUID().toString();

        Class clz = Class.forName(classPath);

        Constructor<?> cons[] = clz.getConstructors();
        Object obj = cons[0].newInstance(uuid);
        Runnable instance = (Runnable) obj;

        ScheduledFuture future = threadPoolTaskScheduler.schedule(instance, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String cron = "0/5 * * * * ?";
                long fixedRate = 2000l;
                if (type) {
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                } else {
                    return new PeriodicTrigger(fixedRate).nextExecutionTime(triggerContext);
                }
            }
        });
        futureMap.put(uuid, future);
        return ResultData.success(uuid);
    }

    @PostMapping(value = "/endTask/{futureId}")
    public ResultData endTask(@PathVariable("futureId") String futureId) {
        if (futureMap.containsKey(futureId) && futureMap.get(futureId) != null) {
            System.err.println("停止定时器:" + futureId);
            futureMap.get(futureId).cancel(true);
            futureMap.remove(futureId);
        }
        return ResultData.success();
    }

    @PostMapping(value = "/jobList")
    public ResultData jobList() {
        List<String> list = new ArrayList<>(this.futureMap.keySet());
        return ResultData.success(list);
    }
}
