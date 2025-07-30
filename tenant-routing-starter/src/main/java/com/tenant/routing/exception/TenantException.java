package com.tenant.routing.exception;

/**
 * 租户异常基类
 * 
 * @author system
 * @since 1.0.0
 */
public class TenantException extends RuntimeException {
    
    private final String errorCode;
    
    public TenantException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public TenantException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}