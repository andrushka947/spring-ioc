package com.epam.ioc;

public interface BeanDefinition {
    String getBeanName();
    Class<?> getBeanType();

    boolean isPrototype();
}
