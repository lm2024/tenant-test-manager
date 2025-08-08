package com.common.redis.cache.exception;

/**
 * 缓存序列化异常
 * 
 * <p>当缓存数据序列化或反序列化失败时抛出此异常。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public class CacheSerializationException extends CacheException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     */
    public CacheSerializationException(String message) {
        super(message);
    }
    
    /**
     * 构造函数
     * 
     * @param message 异常消息
     * @param cause 原因异常
     */
    public CacheSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造函数
     * 
     * @param cause 原因异常
     */
    public CacheSerializationException(Throwable cause) {
        super(cause);
    }
}