package com.epam.ioc;

public interface Context {
    Object getBean(String beanName);
    String[] getBeanDefinitionNames();
}
