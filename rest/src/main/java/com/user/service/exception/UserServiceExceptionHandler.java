package com.user.service.exception;

import com.user.service.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException exception){
        String message = exception.getLocalizedMessage();
        Error error = Error.builder().zonedDateTime(DateUtils.getInstance().getCurrentTime())
                .message(message).status(HttpStatus.NOT_FOUND.name()).code(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException exception){
        String message = exception.getLocalizedMessage();
        Error error = Error.builder().zonedDateTime(DateUtils.getInstance().getCurrentTime())
                .message(message).status(HttpStatus.BAD_REQUEST.name()).code(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception){
        String message = exception.getLocalizedMessage();
        if(Optional.ofNullable(message).isEmpty()){
            message = exception.getCause().toString();
        }
        Error error = Error.builder().zonedDateTime(DateUtils.getInstance().getCurrentTime())
                .message(message).status(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
