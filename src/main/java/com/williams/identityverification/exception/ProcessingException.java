package com.williams.identityverification.exception;


/**
 *
 * This is an exception class
 */
public class ProcessingException extends ApiException {
    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
