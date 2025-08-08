package com.common.redis.cache.service;

import com.common.redis.cache.exception.CacheConnectionException;
import com.common.redis.cache.exception.CacheException;
import com.common.redis.cache.exception.CacheKeyGenerationException;
import com.common.redis.cache.exception.CacheSerializationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * 缓存异常处理器
 * 
 * <p>统一处理缓存操作中的各种异常，提供优雅的降级机制。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@Component
public class CacheExceptionHandler {
    
    /**
     * 连接超时阈值（毫秒）
     */
    private static final long CONNECTION_TIMEOUT_THRESHOLD = 200;
    
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY_COUNT = 3;
    
    /**
     * 重试间隔（毫秒）
     */
    private static final long RETRY_INTERVAL = 100;
    
    /**
     * 处理缓存异常并提供降级方案
     * 
     * @param exception 异常对象
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    public <T> T handleCacheException(Exception exception, Supplier<T> fallback, String operation) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 分类处理不同类型的异常
            if (exception instanceof CacheConnectionException) {
                return handleConnectionException((CacheConnectionException) exception, fallback, operation);
            } else if (exception instanceof CacheSerializationException) {
                return handleSerializationException((CacheSerializationException) exception, fallback, operation);
            } else if (exception instanceof CacheKeyGenerationException) {
                return handleKeyGenerationException((CacheKeyGenerationException) exception, fallback, operation);
            } else if (exception instanceof TimeoutException) {
                return handleTimeoutException((TimeoutException) exception, fallback, operation);
            } else {
                return handleGenericException(exception, fallback, operation);
            }
            
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            log.debug("Exception handling completed for operation: {}, duration: {}ms", operation, duration);
        }
    }
    
    /**
     * 处理连接异常
     * 
     * @param exception 连接异常
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    private <T> T handleConnectionException(CacheConnectionException exception, Supplier<T> fallback, String operation) {
        log.warn("Redis连接异常，降级到数据库查询 - Operation: {}, Error: {}", operation, exception.getMessage());
        
        // 记录连接异常指标
        recordConnectionFailure(operation);
        
        // 立即降级，不进行重试
        return executeFallback(fallback, operation);
    }
    
    /**
     * 处理序列化异常
     * 
     * @param exception 序列化异常
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    private <T> T handleSerializationException(CacheSerializationException exception, Supplier<T> fallback, String operation) {
        log.error("缓存序列化异常，降级到数据库查询 - Operation: {}, Error: {}", operation, exception.getMessage());
        
        // 记录序列化异常指标
        recordSerializationFailure(operation);
        
        // 序列化异常通常是数据问题，直接降级
        return executeFallback(fallback, operation);
    }
    
    /**
     * 处理键生成异常
     * 
     * @param exception 键生成异常
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    private <T> T handleKeyGenerationException(CacheKeyGenerationException exception, Supplier<T> fallback, String operation) {
        log.error("缓存键生成异常，降级到数据库查询 - Operation: {}, Error: {}", operation, exception.getMessage());
        
        // 记录键生成异常指标
        recordKeyGenerationFailure(operation);
        
        // 键生成异常通常是配置问题，直接降级
        return executeFallback(fallback, operation);
    }
    
    /**
     * 处理超时异常
     * 
     * @param exception 超时异常
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    private <T> T handleTimeoutException(TimeoutException exception, Supplier<T> fallback, String operation) {
        log.warn("缓存操作超时，降级到数据库查询 - Operation: {}, Error: {}", operation, exception.getMessage());
        
        // 记录超时异常指标
        recordTimeoutFailure(operation);
        
        // 超时异常可能是临时的，但为了保证响应时间，直接降级
        return executeFallback(fallback, operation);
    }
    
    /**
     * 处理通用异常
     * 
     * @param exception 异常对象
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 处理结果
     */
    private <T> T handleGenericException(Exception exception, Supplier<T> fallback, String operation) {
        log.error("缓存操作异常，降级到数据库查询 - Operation: {}, Error: {}", operation, exception.getMessage(), exception);
        
        // 记录通用异常指标
        recordGenericFailure(operation);
        
        // 对于未知异常，直接降级
        return executeFallback(fallback, operation);
    }
    
    /**
     * 执行降级方案
     * 
     * @param fallback 降级方案
     * @param operation 操作描述
     * @param <T> 返回类型
     * @return 降级结果
     */
    private <T> T executeFallback(Supplier<T> fallback, String operation) {
        long startTime = System.currentTimeMillis();
        
        try {
            T result = fallback.get();
            
            long duration = System.currentTimeMillis() - startTime;
            log.debug("Fallback executed successfully for operation: {}, duration: {}ms", operation, duration);
            
            // 记录降级成功指标
            recordFallbackSuccess(operation, duration);
            
            return result;
            
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("Fallback execution failed for operation: {}, duration: {}ms", operation, duration, e);
            
            // 记录降级失败指标
            recordFallbackFailure(operation, duration);
            
            // 降级也失败了，抛出原始异常
            throw new CacheException("Both cache operation and fallback failed for operation: " + operation, e);
        }
    }
    
    /**
     * 带重试的缓存操作执行
     * 
     * @param operation 缓存操作
     * @param fallback 降级方案
     * @param operationName 操作名称
     * @param <T> 返回类型
     * @return 执行结果
     */
    public <T> T executeWithRetry(Supplier<T> operation, Supplier<T> fallback, String operationName) {
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= MAX_RETRY_COUNT; attempt++) {
            try {
                return operation.get();
                
            } catch (Exception e) {
                lastException = e;
                
                // 判断是否应该重试
                if (!shouldRetry(e, attempt)) {
                    break;
                }
                
                log.debug("Cache operation failed, attempt {}/{} for operation: {}, error: {}", 
                        attempt, MAX_RETRY_COUNT, operationName, e.getMessage());
                
                // 等待重试间隔
                if (attempt < MAX_RETRY_COUNT) {
                    try {
                        Thread.sleep(RETRY_INTERVAL * attempt); // 递增重试间隔
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        
        // 所有重试都失败了，执行降级
        log.warn("All retry attempts failed for operation: {}, executing fallback", operationName);
        return handleCacheException(lastException, fallback, operationName);
    }
    
    /**
     * 判断是否应该重试
     * 
     * @param exception 异常对象
     * @param attempt 当前尝试次数
     * @return 是否应该重试
     */
    private boolean shouldRetry(Exception exception, int attempt) {
        // 已达到最大重试次数
        if (attempt >= MAX_RETRY_COUNT) {
            return false;
        }
        
        // 序列化异常和键生成异常不重试
        if (exception instanceof CacheSerializationException || 
            exception instanceof CacheKeyGenerationException) {
            return false;
        }
        
        // 连接异常可以重试
        if (exception instanceof CacheConnectionException) {
            return true;
        }
        
        // 超时异常可以重试
        if (exception instanceof TimeoutException) {
            return true;
        }
        
        // 其他异常根据错误消息判断
        String message = exception.getMessage();
        if (message != null) {
            String lowerMessage = message.toLowerCase();
            return lowerMessage.contains("timeout") || 
                   lowerMessage.contains("connection") ||
                   lowerMessage.contains("network");
        }
        
        return false;
    }
    
    /**
     * 检查异常是否为临时性异常
     * 
     * @param exception 异常对象
     * @return 是否为临时性异常
     */
    public boolean isTemporaryException(Exception exception) {
        if (exception instanceof CacheConnectionException || 
            exception instanceof TimeoutException) {
            return true;
        }
        
        String message = exception.getMessage();
        if (message != null) {
            String lowerMessage = message.toLowerCase();
            return lowerMessage.contains("timeout") || 
                   lowerMessage.contains("connection refused") ||
                   lowerMessage.contains("network unreachable");
        }
        
        return false;
    }
    
    /**
     * 记录连接失败指标
     */
    private void recordConnectionFailure(String operation) {
        // 这里可以集成监控系统，记录连接失败指标
        log.debug("Recording connection failure for operation: {}", operation);
    }
    
    /**
     * 记录序列化失败指标
     */
    private void recordSerializationFailure(String operation) {
        // 这里可以集成监控系统，记录序列化失败指标
        log.debug("Recording serialization failure for operation: {}", operation);
    }
    
    /**
     * 记录键生成失败指标
     */
    private void recordKeyGenerationFailure(String operation) {
        // 这里可以集成监控系统，记录键生成失败指标
        log.debug("Recording key generation failure for operation: {}", operation);
    }
    
    /**
     * 记录超时失败指标
     */
    private void recordTimeoutFailure(String operation) {
        // 这里可以集成监控系统，记录超时失败指标
        log.debug("Recording timeout failure for operation: {}", operation);
    }
    
    /**
     * 记录通用失败指标
     */
    private void recordGenericFailure(String operation) {
        // 这里可以集成监控系统，记录通用失败指标
        log.debug("Recording generic failure for operation: {}", operation);
    }
    
    /**
     * 记录降级成功指标
     */
    private void recordFallbackSuccess(String operation, long duration) {
        // 这里可以集成监控系统，记录降级成功指标
        log.debug("Recording fallback success for operation: {}, duration: {}ms", operation, duration);
    }
    
    /**
     * 记录降级失败指标
     */
    private void recordFallbackFailure(String operation, long duration) {
        // 这里可以集成监控系统，记录降级失败指标
        log.debug("Recording fallback failure for operation: {}, duration: {}ms", operation, duration);
    }
}