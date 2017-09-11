package com.epam.ioc;

import java.lang.reflect.Constructor;
import java.util.*;

public class ApplicationContext implements Context {

    private List<BeanDefinition> beanDefinitions;
    private Map<String , Object> beans = new HashMap<>();

    public ApplicationContext() {
        beanDefinitions = Arrays.asList(Config.EMPTY_BEAN_DEFINITION);
    }

    public ApplicationContext(Config config) {
        beanDefinitions = Arrays.asList(config.beanDefinitions());
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);
        Object bean = beans.get(beanName);
        if (bean == null) {
            bean = createNewBean(beanDefinition);
            if (!beanDefinition.isPrototype()) {
                beans.put(beanName, bean);
            }
        }
        return bean;
    }

    private Object createNewBean(BeanDefinition beanDefinition) {
        Object bean = createNewBeanInstance(beanDefinition);
        return bean;
    }

    private BeanDefinition getBeanDefinitionByName(String beanName) {
        return beanDefinitions.stream()
            .filter(bd -> Objects.equals(bd.getBeanName(), beanName))
            .findAny()
            .orElseThrow(NoSuchBeanException::new);
    }

    private Object createNewBeanInstance(BeanDefinition bd) {
        Class<?> type = bd.getBeanType();
        Constructor<?> constructor = type.getDeclaredConstructors()[0];
        Object newBean;
        if (constructor.getParameterCount() == 0) {
            newBean = createBeanWithDefaultConstructor(type);
        } else {
            newBean = createBeanConstructorWithParams(type);
        }
        return newBean;
    }

    private Object createBeanConstructorWithParams(Class<?> type) {
        Constructor<?> constructor = type.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        List<Object> params = new ArrayList<>();

        for (Class<?> clazz : parameterTypes) {
            params.add(getBean(convertClassNameToBeanName(clazz.getSimpleName())));
        }
        Object newBean;
        try {
            newBean= constructor.newInstance(params.toArray());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return newBean;
    }
    private String convertClassNameToBeanName(String className) {
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }

    private Object createBeanWithDefaultConstructor(Class<?> type) {
        Object newBean;
        try {
            newBean = type.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return newBean;
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitions.
                stream().
                map(BeanDefinition::getBeanName).
                toArray(String[]::new);
    }

}
