package com.gh.baseusersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.gh.baseusersystem.modular.*.mapper")
@ComponentScans(value = {
		@ComponentScan(value = "com.gh.redis.*"),
		@ComponentScan(value = "com.gh.auth.*")
})
public class BaseUserSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseUserSystemApplication.class, args);
	}

}
