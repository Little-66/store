package com.cy.store.service.Exception;

public class RemoveException extends ServiceException {
    public RemoveException() {
        super();
    }

    public RemoveException(String message) {
        super(message);
    }

    public RemoveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveException(Throwable cause) {
        super(cause);
    }

    protected RemoveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
