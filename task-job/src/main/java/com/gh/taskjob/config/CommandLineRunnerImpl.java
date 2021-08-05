package com.gh.taskjob.config;

import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动项目后执行任务
 * blog-cloud
 *
 * @author 3hgh
 * @version 1.0
 * @date 2021-08-05 17:49
 **/
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private SysTaskJobPlanService planService;

    @Override
    public void run(String... args) throws Exception {
        // 执行启动状态的定时任务
        planService.startedTask();
    }
}
