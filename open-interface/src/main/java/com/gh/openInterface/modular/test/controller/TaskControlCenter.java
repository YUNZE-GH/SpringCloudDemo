package com.gh.openInterface.modular.test.controller;

import com.gh.common.toolsclass.ResultData;
import com.gh.openInterface.job.MyRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    private ScheduledFuture<?> future;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    @PostMapping(value = "/startTask")
    public ResultData startTask(){
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String cron = "0/5 * * * * ?";
                return new CronTrigger(cron).nextExecutionTime(triggerContext);
            }
        });
        return ResultData.success();
    }

    @PostMapping(value = "/endTask")
    public ResultData endTask(){
        if (future != null) {
            future.cancel(true);
        }
        return ResultData.success();
    }
}
