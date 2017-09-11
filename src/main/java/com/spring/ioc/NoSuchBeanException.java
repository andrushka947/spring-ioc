package com.spring.ioc;

public class NoSuchBeanException extends RuntimeException {
    public NoSuchBeanException() {
        super("NoSuchBean");
    }
}
