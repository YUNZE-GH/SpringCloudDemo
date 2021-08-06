package com.gh.taskjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.gh.*.modular.*.mapper")
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class TaskJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskJobApplication.class, args);
    }

}
