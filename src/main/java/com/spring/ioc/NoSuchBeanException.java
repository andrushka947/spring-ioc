package ua.allugard.ioc;

public class NoSuchBeanException extends RuntimeException {
    public NoSuchBeanException() {
        super("NoSuchBean");
    }
}