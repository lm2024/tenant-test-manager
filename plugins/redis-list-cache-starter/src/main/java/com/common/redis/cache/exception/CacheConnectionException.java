package com.common.redis.cache.exception;

/**
 * 缓存连接异常
 * 
 * <p>当Redis连接失败或超时时抛出此异常。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public class CacheConnectionException extends CacheException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     */
    public CacheConnectionException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     * @param cause 原因异常
     */
    public CacheConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造函数
     * 
     * @param cause 原因异常
     */
    public CacheConnectionException(Throwable cause) {
        super(cause);
    }
}