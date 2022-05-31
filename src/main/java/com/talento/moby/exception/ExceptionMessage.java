package com.talento.moby.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ExceptionMessage implements ResponseMessage {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ExceptionMessage(HttpStatus httpStatus, String message, ZonedDateTime timestamp) {
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.message = message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
