package com.gh.openInterface.modular.test;

import com.gh.openInterface.modular.test.LoggerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/24 16:34
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class InterceptorRegister implements WebMvcConfigurer {

    @Bean
    public LoggerInterceptor loggerInterceptor() {
        return new LoggerInterceptor();
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor());
        registry.addInterceptor(authInterceptor());
    }
}
