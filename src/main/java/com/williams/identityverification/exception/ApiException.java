package com.williams.identityverification.exception;


/**
 *
 * This is an exception class
 */
public class ApiException extends RuntimeException{
    ApiException(String message) {
        super(message);
    }

    ApiException(String message, Throwable cause) {
        super(message, cause);
        if (this.getCause() == null && cause != null) {
            this.initCause(cause);
        }
    }
}
