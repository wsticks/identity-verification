package com.williams.identityverification.exception;

public class BadRequestException extends AbstractException {

    public BadRequestException(String code, String message) {
        super(code,message);
    }

    public BadRequestException( String message) {
        super(message);
    }
}