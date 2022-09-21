package com.github.entropyfeng.apartment.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
   private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext =applicationContext;
    }

    public static Object registerSingletonBean(String beanName,Object singletonObject){

        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;

        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getAutowireCapableBeanFactory();

        //动态注册bean.
        defaultListableBeanFactory.registerSingleton(beanName,singletonObject);

        //获取动态注册的bean.
        return configurableApplicationContext.getBean(beanName);
    }

    public static Object getBeanByName(String beanName){
        return applicationContext.getBean(beanName);
    }
}
