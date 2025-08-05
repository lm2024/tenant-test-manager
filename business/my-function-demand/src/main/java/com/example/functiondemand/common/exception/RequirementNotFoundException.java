package com.example.functiondemand.common.exception;

public class RequirementNotFoundException extends RuntimeException {
    public RequirementNotFoundException(String message) {
        super(message);
    }

    public RequirementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}