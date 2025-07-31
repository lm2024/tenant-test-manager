package com.example.elasticsearch.service;

import org.springframework.stereotype.Component;

/**
 * 重试操作执行器
 * 
 * @author Kiro
 */
@Component
public class RetryableOperationExecutor {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RetryableOperationExecutor.class);
    
    /**
     * 执行重试操作
     * 
     * @param operation 操作
     * @param maxRetries 最大重试次数
     * @return 操作结果
     */
    public <T> T executeWithRetry(RetryableOperation<T> operation, int maxRetries) {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                return operation.execute();
            } catch (Exception e) {
                attempts++;
                log.warn("操作执行失败，第 {} 次重试", attempts, e);
                if (attempts >= maxRetries) {
                    throw new RuntimeException("操作执行失败，已达到最大重试次数", e);
                }
            }
        }
        throw new RuntimeException("操作执行失败");
    }
    
    /**
     * 重试操作接口
     */
    @FunctionalInterface
    public interface RetryableOperation<T> {
        T execute() throws Exception;
    }
}