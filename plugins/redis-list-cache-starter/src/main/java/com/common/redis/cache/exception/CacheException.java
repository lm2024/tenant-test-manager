package com.common.redis.cache.exception;

/**
 * 缓存操作基础异常
 * 
 * <p>所有缓存相关异常的基类。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public class CacheException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     */
    public CacheException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     * @param cause 原因异常
     */
    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造函数
     * 
     * @param cause 原因异常
     */
    public CacheException(Throwable cause) {
        super(cause);
    }
}