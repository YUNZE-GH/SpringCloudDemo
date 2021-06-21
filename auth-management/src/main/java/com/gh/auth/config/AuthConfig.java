package com.gh.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/6/20 23:22
 */
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor getAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(this.getAuthInterceptor());

        // 所有路径都会被拦截
        interceptorRegistration.addPathPatterns("/**");

        // 添加不拦截的路径
        interceptorRegistration.excludePathPatterns("/auth/**");
    }
}
