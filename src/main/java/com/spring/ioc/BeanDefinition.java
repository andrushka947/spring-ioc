package com.spring.ioc;

public interface BeanDefinition {
    String getBeanName();
    Class<?> getBeanType();

    boolean isPrototype();
}
