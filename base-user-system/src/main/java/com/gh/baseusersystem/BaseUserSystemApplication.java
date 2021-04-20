package com.gh.baseusersystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gh.baseusersystem.modular.*.mapper")
public class BaseUserSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseUserSystemApplication.class, args);
	}

}
