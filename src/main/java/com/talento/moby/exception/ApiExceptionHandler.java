package com.talento.moby.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ResponseMessage> resourceNotFoundException(ResourceNotFoundException exception) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ExceptionMessage apiException = new ExceptionMessage(
                notFound,
                exception.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ResponseMessage> badRequestException(BadRequestException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionMessage apiException = new ExceptionMessage(
                badRequest,
                exception.getMessage(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
