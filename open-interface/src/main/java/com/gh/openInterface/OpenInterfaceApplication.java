package com.gh.openInterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.gh.*.modular.*.mapper")
@ComponentScans(value = {
        @ComponentScan(value = "com.gh.redis.*")
})
@EnableScheduling // 开启定时任务注解
public class OpenInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenInterfaceApplication.class, args);
    }

}
