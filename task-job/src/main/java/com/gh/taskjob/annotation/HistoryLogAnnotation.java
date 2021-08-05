package com.gh.taskjob.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务运行日志记录自定义注解
 * @author gaohan
 * @version 1.0
 * @date 2021/8/5 23:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HistoryLogAnnotation {
}
