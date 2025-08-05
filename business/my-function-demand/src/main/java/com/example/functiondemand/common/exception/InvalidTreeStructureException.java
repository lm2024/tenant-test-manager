package com.example.functiondemand.common.exception;

public class InvalidTreeStructureException extends RuntimeException {
    public InvalidTreeStructureException(String message) {
        super(message);
    }

    public InvalidTreeStructureException(String message, Throwable cause) {
        super(message, cause);
    }
}