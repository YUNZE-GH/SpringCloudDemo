package com.gh.openInterface.modular.test;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/7/24 16:32
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //获取当前方法上的指定注解
        LoggerAnnotation loggerAnnotation = method.getAnnotation(LoggerAnnotation.class);
        //判断当前注解是否存在
        if (loggerAnnotation != null) {
            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);
            System.err.println("进入" + method.getName() + "方法的时间是：" + startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //获取当前方法上的指定注解
        LoggerAnnotation loggerAnnotation = method.getAnnotation(LoggerAnnotation.class);
        //判断当前注解是否存在
        if (loggerAnnotation != null) {
            long endTime = System.currentTimeMillis();
            long startTime = (Long) request.getAttribute("startTime");
            long periodTime = endTime - startTime;
            System.err.println("离开" + method.getName() + "方法的时间是：" + endTime);
            System.err.println("在" + method.getName() + "方法的时长是：" + periodTime);
        }
    }

}
