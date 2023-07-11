package com.williams.identityverification.exception;

public class AbstractException extends RuntimeException {

    public AbstractException(String code, String message) {

        super(message);
        this.setCode(code);
    }

    public AbstractException(String message) {

        super(message);
    }


    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
