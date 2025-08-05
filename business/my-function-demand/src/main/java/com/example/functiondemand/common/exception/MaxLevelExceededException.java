package com.example.functiondemand.common.exception;

public class MaxLevelExceededException extends RuntimeException {
    public MaxLevelExceededException(String message) {
        super(message);
    }

    public MaxLevelExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}