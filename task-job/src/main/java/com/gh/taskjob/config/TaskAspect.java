package com.gh.taskjob.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author gaohan
 * @version 1.0
 * @date 2021/8/5 23:40
 */
@Component  // 注入到spring ioc容器
@Aspect // 声明为切面
@Slf4j
public class TaskAspect {

    /**
     * 切入点
     * 方法用途（切入点表达式可以用&&,||,!来组合使用）:
     * execution: 匹配连接点：execution(* com.example.demo.*(..))--com.example.demo包下所有类的方法
     * within: 某个类里面
     * this: 指定AOP代理类的类型
     * target:指定目标对象的类型
     * args: 指定参数的类型
     * bean:指定特定的bean名称，可以使用通配符（Spring自带的）
     *
     * @target： 带有指定注解的类型
     * @args: 指定运行时传的参数带有指定的注解
     * @within: 匹配使用指定注解的类
     * @annotation:指定方法所应用的注解
     */
    @Pointcut("@annotation(com.gh.taskjob.annotation.HistoryLogAnnotation)")
    public void asAnnotation() {

    }

    /**
     * 在HistoryLogAnnotation注解之前执行
     */
    @Before("asAnnotation()")
    public void beforeRun() {
        System.out.println("在HistoryLogAnnotation注解之前执行------------------" + LocalDateTime.now());
    }

    /**
     * 在HistoryLogAnnotation注解的方法执行完之后要处理的
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "asAnnotation()")
    public void after(Object result) {
        System.out.println("在HistoryLogAnnotation注解之后执行------------------" + LocalDateTime.now());
        log.info("执行成功，Result：{}", JSONObject.toJSONString(result));
    }
}
