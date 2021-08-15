package com.gh.taskjob.config;

import com.gh.common.SDK;
import com.gh.common.toolsclass.FinalProperties;
import com.gh.taskjob.modular.SysTaskJobHistory.entity.SysTaskJobHistory;
import com.gh.taskjob.modular.SysTaskJobHistory.service.SysTaskJobHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 定时任务调度日志记录切面
 *
 * @author gaohan
 * @version 1.0
 * @date 2021/8/5 23:40
 * <p>
 * ###  方法注解  ###
 * @Before：前置通知，即在某个连接点之前执行通知；
 * @AfterReturning：后置通知，即在某个连接点正常完成后执行通知，通常在一个匹配的方法返回的时候执行；
 * @AfterThrowing：异常通知，即在方法抛出异常退出时执行通知；
 * @After：最终通知，即某个连接点退出时执行通知；
 * @Around：环绕通知，它是最强大也是最麻烦的通知，它可以在方法调用前后完成自定义的行为，它可以自己选择是否继续执行连接点或者直接返回或者抛出异常来结束执行；
 */
@Component  // 注入到spring ioc容器
@Aspect // 声明为切面
@Slf4j
public class TaskAspect {

    @Autowired
    private SysTaskJobHistoryService taskHistoryService;

    private SysTaskJobHistory bo = new SysTaskJobHistory();

    private Integer status;

    private String logMessage;


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
    public void beforeRun(JoinPoint joinPoint) {
        bo = new SysTaskJobHistory();
        status = null;
        logMessage = "";

        System.err.println("注解之前执行------------------" + LocalDateTime.now());
        bo.setTaskStartTime(LocalDateTime.now());   // 任务开始时间
    }


    @After("asAnnotation()")
    public void after(JoinPoint joinPoint) {
        System.err.println("注解之后执行------------------" + LocalDateTime.now());
    }


    /**
     * 在HistoryLogAnnotation注解的方法执行完之后要处理的
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "asAnnotation()")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        HashMap<String, Object> params = (HashMap<String, Object>) joinPoint.getArgs()[0];
        bo.setTaskEndTime(LocalDateTime.now()); // 任务结束时间
        bo.setTaskId(params.get("taskId").toString());
        bo.setTaskTimeConsuming(Duration.between(bo.getTaskStartTime(), bo.getTaskEndTime()).toMillis());   // 耗时
        bo.setTaskExecutionDate(SDK.getLocalDateTimeUtils().localDateTimeToString(bo.getTaskStartTime(), FinalProperties.FORMAT_DATE));

        bo.setStatus(0);    // 执行状态：0-成功；1-失败
        bo.setLog("执行成功！");
        taskHistoryService.add(bo);
    }


    @AfterThrowing(throwing = "e", pointcut = "asAnnotation()")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        HashMap<String, Object> params = (HashMap<String, Object>) joinPoint.getArgs()[0];
        bo.setTaskEndTime(LocalDateTime.now()); // 任务结束时间
        bo.setTaskId(params.get("taskId").toString());
        bo.setStatus(1);    // 执行状态：0-成功；1-失败
        bo.setTaskTimeConsuming(Duration.between(bo.getTaskStartTime(), bo.getTaskEndTime()).toMillis());   // 耗时
        bo.setTaskExecutionDate(SDK.getLocalDateTimeUtils().localDateTimeToString(bo.getTaskStartTime(), FinalProperties.FORMAT_DATE));

        StringBuilder str = new StringBuilder();
        str.append(e.getClass() + ":" + e.getMessage() + "\n");
        for (int i = 0; i < e.getStackTrace().length; i++) {
            str.append(e.getStackTrace()[i].toString() + "\n");
            if (i == 3) {
                break;
            }
        }

        bo.setLog(str.toString());
        taskHistoryService.add(bo);
    }

}
