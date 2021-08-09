package com.gh.common.toolsclass;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public SpringContextHolder() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }

    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(String beanId, Class<T> tClass) throws BeansException {
        return applicationContext.getBean(beanId, tClass);
    }

    public static String[] getBeanNames() {
        return applicationContext.getBeanDefinitionNames();
    }

    public static boolean containsBean(String id) {
        return applicationContext.containsBean(id);
    }

    public static boolean isSingleton(String id) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(id);
    }

    public static Class getType(String id) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(id);
    }

    public static String[] getAliases(String id) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(id);
    }
}