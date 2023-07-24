package com.williams.identityverification.advice;

import com.williams.identityverification.exception.ConflictException;
import com.williams.identityverification.exception.IllegalArgumentException;
import com.williams.identityverification.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handlerInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public Map<String,String> handlerConflict(ConflictException ex){
        Map<String,String> errorMap= new HashMap<>();
            errorMap.put("message",ex.getMessage());
            errorMap.put("code", ex.getCode());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String,String> handlerConflict(NotFoundException ex){
        Map<String,String> errorMap= new HashMap<>();
        errorMap.put("message",ex.getMessage());
        errorMap.put("code", ex.getCode());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String,String> handlerConflict(IllegalArgumentException ex){
        Map<String,String> errorMap= new HashMap<>();
        errorMap.put("message",ex.getMessage());
        errorMap.put("code", ex.getCode());
        return errorMap;
    }

}
