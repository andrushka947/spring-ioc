package ua.allugard.ioc;

public interface Context {
    Object getBean(String beanName);
    String[] getBeanDefinitionNames();
}
