package com.cathy.cathyblog.common.exceptions;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super("Service exception!");
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
