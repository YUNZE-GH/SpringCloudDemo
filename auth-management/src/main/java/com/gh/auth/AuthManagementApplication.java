package com.gh.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.gh.auth.modular.user.mapper")
@ComponentScans(value = {
		@ComponentScan(value = "com.gh.redis.*")
})
public class AuthManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthManagementApplication.class, args);
	}

}
