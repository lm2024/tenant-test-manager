package com.common.redis.cache.exception;

/**
 * 缓存键生成异常
 * 
 * <p>当缓存键生成失败时抛出此异常。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public class CacheKeyGenerationException extends CacheException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     */
    public CacheKeyGenerationException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     * @param cause 原因异常
     */
    public CacheKeyGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造函数
     * 
     * @param cause 原因异常
     */
    public CacheKeyGenerationException(Throwable cause) {
        super(cause);
    }
}