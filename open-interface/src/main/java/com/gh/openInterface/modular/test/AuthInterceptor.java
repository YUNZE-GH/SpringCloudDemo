package com.gh.openInterface.modular.test;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/24 16:32
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取当前方法上的指定注解
        AuthFilter authFilter = method.getAnnotation(AuthFilter.class);
        // 判断当前注解是否存在
        if (authFilter != null) {
            System.err.println("进入权限过滤:" + LocalDateTime.now());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取当前方法上的指定注解
        AuthFilter authFilter = method.getAnnotation(AuthFilter.class);
        // 判断当前注解是否存在
        if (authFilter != null) {
            System.err.println("离开权限过滤:" + LocalDateTime.now());
        }
    }

}
