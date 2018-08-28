package com.cathy.cathyblog.common.exceptions;


public class RepositoryException extends Exception {
    private static final long serialVersionUID = 1L;

    public RepositoryException() {
        super("Repository exception!");
    }

    public RepositoryException(Throwable throwable) {
        super(throwable);
    }

    public RepositoryException(String msg) {
        super(msg);
    }
}
