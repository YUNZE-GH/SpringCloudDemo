package com.gh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigApplication {

	@Value("${foo}")
	private String foo;

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

	@RequestMapping(value = "get")
	public String get(){
		return foo;
	}
}
