package com.gh.taskjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.gh.*.modular.*.mapper")
@ComponentScans(value = {
        @ComponentScan(value = "com.gh.common.*")
        ,@ComponentScan(value = "com.gh.redis.*")
})
@EnableAspectJAutoProxy(exposeProxy = true)
public class TaskJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskJobApplication.class, args);
    }

}
