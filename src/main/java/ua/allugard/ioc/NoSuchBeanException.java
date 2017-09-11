package com.epam.ioc;

public class NoSuchBeanException extends RuntimeException {
    public NoSuchBeanException() {
        super("NoSuchBean");
    }
}
