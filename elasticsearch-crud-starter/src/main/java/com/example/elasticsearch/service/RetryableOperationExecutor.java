package com.example.elasticsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * 可重试操作执行器
 * 提供统一的重试机制
 * 
 * @author Kiro
 */
@Slf4j
@Component
public class RetryableOperationExecutor {

    /**
     * 执行可重试的操作
     * 
     * @param operation 要执行的操作
     * @param <T> 返回类型
     * @return 操作结果
     */
    @Retryable(
            value = {ElasticsearchException.class, RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public <T> T executeWithRetry(Supplier<T> operation) {
        try {
            log.debug("执行可重试操作");
            return operation.get();
        } catch (Exception e) {
            log.warn("操作执行失败，将进行重试: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * 执行可重试的无返回值操作
     * 
     * @param operation 要执行的操作
     */
    @Retryable(
            value = {ElasticsearchException.class, RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void executeWithRetry(Runnable operation) {
        try {
            log.debug("执行可重试操作（无返回值）");
            operation.run();
        } catch (Exception e) {
            log.warn("操作执行失败，将进行重试: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * 执行自定义重试次数的操作
     * 
     * @param operation 要执行的操作
     * @param maxAttempts 最大重试次数
     * @param <T> 返回类型
     * @return 操作结果
     */
    public <T> T executeWithCustomRetry(Supplier<T> operation, int maxAttempts) {
        Exception lastException = null;
        
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                log.debug("执行操作，第{}次尝试", attempt);
                return operation.get();
            } catch (Exception e) {
                lastException = e;
                log.warn("第{}次尝试失败: {}", attempt, e.getMessage());
                
                if (attempt < maxAttempts) {
                    try {
                        // 指数退避策略
                        long delay = 1000L * (1L << (attempt - 1));
                        Thread.sleep(Math.min(delay, 10000L)); // 最大延迟10秒
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("操作被中断", ie);
                    }
                }
            }
        }
        
        log.error("操作在{}次尝试后仍然失败", maxAttempts);
        throw new RuntimeException("操作重试失败", lastException);
    }
}