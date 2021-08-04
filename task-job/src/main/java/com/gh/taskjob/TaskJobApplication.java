package com.gh.taskjob;

import com.gh.taskjob.modular.SysTaskJobPlan.service.SysTaskJobPlanService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gh.*.modular.*.mapper")
public class TaskJobApplication {

    @Autowired
    private SysTaskJobPlanService planService;

    public static void main(String[] args) {
        SpringApplication.run(TaskJobApplication.class, args);
    }

    private void startTask() {

    }

}
