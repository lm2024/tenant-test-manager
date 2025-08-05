package com.example.functiondemand.common.exception;

public class DuplicateRelationException extends RuntimeException {
    public DuplicateRelationException(String message) {
        super(message);
    }

    public DuplicateRelationException(String message, Throwable cause) {
        super(message, cause);
    }
}