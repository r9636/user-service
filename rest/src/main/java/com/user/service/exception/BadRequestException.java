package com.user.service.exception;

public class BadRequestException extends RuntimeException{
    private String message;
    public BadRequestException(String message){
        super(message);
    }
}
