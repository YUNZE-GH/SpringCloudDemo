package com.gh.provider.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * hhh-concrete
 *
 * @author 3hgh
 * @version 1.0
 * @date 2021-03-16 11:29
 **/

@Aspect
@Component
public class AopAspectConfig {

    /**
     * AOP的切入点
     */
    @Pointcut("execution(public * com.gh.provider.modular.*.controller..*.*(..))")
    public void executePackage() {

    }

    // 在切入点的方法执行之前要处理的
    @Before("executePackage()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.err.println("------------------" + signature.getName() + "方法执行前-------------------");
    }

    // 方法执行完之后要处理的
    @AfterReturning(pointcut = "executePackage()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.err.println("------------------" + signature.getName() + "方法执行后-------------------");

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求详细信息
        String url = request.getRequestURL().toString();
        System.err.println("URL : " + url);

        System.err.println("返回信息:" + result);

    }
}