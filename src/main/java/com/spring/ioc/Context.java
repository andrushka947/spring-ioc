package com.spring.ioc;

public interface Context {
    Object getBean(String beanName);
    String[] getBeanDefinitionNames();
}
