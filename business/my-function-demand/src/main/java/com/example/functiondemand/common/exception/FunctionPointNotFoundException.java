package com.example.functiondemand.common.exception;

public class FunctionPointNotFoundException extends RuntimeException {
    public FunctionPointNotFoundException(String message) {
        super(message);
    }

    public FunctionPointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}