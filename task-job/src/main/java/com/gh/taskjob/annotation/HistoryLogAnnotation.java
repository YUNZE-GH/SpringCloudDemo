package com.gh.taskjob.annotation;

import java.lang.annotation.*;

/**
 * 任务运行日志记录自定义注解
 * @author gaohan
 * @version 1.0
 * @date 2021/8/5 23:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HistoryLogAnnotation {
}
