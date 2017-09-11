package com.spring.ioc;

public interface Config {

    BeanDefinition[] EMPTY_BEAN_DEFINITION = new BeanDefinition[0];

    BeanDefinition[] beanDefinitions();
}
