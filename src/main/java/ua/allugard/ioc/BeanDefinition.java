package ua.allugard.ioc;

public interface BeanDefinition {
    String getBeanName();
    Class<?> getBeanType();

    boolean isPrototype();
}
