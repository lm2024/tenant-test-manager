package com.example.functiondemand.common.exception;

public class CircularReferenceException extends RuntimeException {
    public CircularReferenceException(String message) {
        super(message);
    }

    public CircularReferenceException(String message, Throwable cause) {
        super(message, cause);
    }
}