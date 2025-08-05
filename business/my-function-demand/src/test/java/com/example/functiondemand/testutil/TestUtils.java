package com.example.functiondemand.testutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 测试工具类
 * 提供测试中常用的工具方法
 */
public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 从MvcResult中提取响应内容
     */
    public static String getResponseContent(MvcResult result) {
        try {
            return result.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to get response content", e);
        }
    }

    /**
     * 将对象转换为JSON字符串
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    /**
     * 执行并发测试
     */
    public static void executeConcurrentTest(int threadCount, Runnable task) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        try {
            CompletableFuture<?>[] futures = new CompletableFuture[threadCount];
            
            for (int i = 0; i < threadCount; i++) {
                futures[i] = CompletableFuture.runAsync(task, executor);
            }
            
            CompletableFuture.allOf(futures).get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Concurrent test execution failed", e);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 执行性能测试
     */
    public static PerformanceResult executePerformanceTest(Runnable task) {
        return executePerformanceTest(task, 1);
    }

    /**
     * 执行性能测试（指定执行次数）
     */
    public static PerformanceResult executePerformanceTest(Runnable task, int iterations) {
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;
        
        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();
            task.run();
            long endTime = System.currentTimeMillis();
            
            long duration = endTime - startTime;
            totalTime += duration;
            minTime = Math.min(minTime, duration);
            maxTime = Math.max(maxTime, duration);
        }
        
        return new PerformanceResult(
            iterations,
            totalTime,
            totalTime / iterations,
            minTime,
            maxTime
        );
    }

    /**
     * 执行带返回值的性能测试
     */
    public static <T> PerformanceResultWithValue<T> executePerformanceTest(Supplier<T> task) {
        long startTime = System.currentTimeMillis();
        T result = task.get();
        long endTime = System.currentTimeMillis();
        
        return new PerformanceResultWithValue<>(
            result,
            endTime - startTime
        );
    }

    /**
     * 等待指定时间
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }

    /**
     * 重试执行任务
     */
    public static void retryTask(Runnable task, int maxRetries, long delayMs) {
        Exception lastException = null;
        
        for (int i = 0; i <= maxRetries; i++) {
            try {
                task.run();
                return; // 成功执行，退出
            } catch (Exception e) {
                lastException = e;
                if (i < maxRetries) {
                    sleep(delayMs);
                }
            }
        }
        
        throw new RuntimeException("Task failed after " + maxRetries + " retries", lastException);
    }

    /**
     * 重试执行带返回值的任务
     */
    public static <T> T retryTask(Supplier<T> task, int maxRetries, long delayMs) {
        Exception lastException = null;
        
        for (int i = 0; i <= maxRetries; i++) {
            try {
                return task.get();
            } catch (Exception e) {
                lastException = e;
                if (i < maxRetries) {
                    sleep(delayMs);
                }
            }
        }
        
        throw new RuntimeException("Task failed after " + maxRetries + " retries", lastException);
    }

    /**
     * 验证字符串是否为有效的JSON
     */
    public static boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成测试用的租户ID
     */
    public static String generateTenantId(int index) {
        return "test-tenant-" + index;
    }

    /**
     * 生成测试用的任务ID
     */
    public static String generateTaskId() {
        return "test-task-" + System.currentTimeMillis() + "-" + Thread.currentThread().getId();
    }

    /**
     * 性能测试结果
     */
    public static class PerformanceResult {
        private final int iterations;
        private final long totalTime;
        private final long averageTime;
        private final long minTime;
        private final long maxTime;

        public PerformanceResult(int iterations, long totalTime, long averageTime, long minTime, long maxTime) {
            this.iterations = iterations;
            this.totalTime = totalTime;
            this.averageTime = averageTime;
            this.minTime = minTime;
            this.maxTime = maxTime;
        }

        public int getIterations() { return iterations; }
        public long getTotalTime() { return totalTime; }
        public long getAverageTime() { return averageTime; }
        public long getMinTime() { return minTime; }
        public long getMaxTime() { return maxTime; }

        @Override
        public String toString() {
            return String.format(
                "PerformanceResult{iterations=%d, totalTime=%dms, averageTime=%dms, minTime=%dms, maxTime=%dms}",
                iterations, totalTime, averageTime, minTime, maxTime
            );
        }
    }

    /**
     * 带返回值的性能测试结果
     */
    public static class PerformanceResultWithValue<T> {
        private final T value;
        private final long executionTime;

        public PerformanceResultWithValue(T value, long executionTime) {
            this.value = value;
            this.executionTime = executionTime;
        }

        public T getValue() { return value; }
        public long getExecutionTime() { return executionTime; }

        @Override
        public String toString() {
            return String.format(
                "PerformanceResultWithValue{value=%s, executionTime=%dms}",
                value, executionTime
            );
        }
    }
}