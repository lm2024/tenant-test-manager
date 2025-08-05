package com.example.functiondemand.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 性能监控配置类
 * 配置性能监控、指标收集和异步处理
 */
@Configuration
@EnableAsync
@ConditionalOnProperty(name = "performance.monitoring.enabled", havingValue = "true", matchIfMissing = true)
public class PerformanceConfig {

    @Value("${performance.monitoring.slow-query-threshold:1000}")
    private long slowQueryThreshold;

    @Value("${performance.monitoring.batch-operation.threshold:5000}")
    private long batchOperationThreshold;

    @Value("${file.import-export.thread-pool.core-size:5}")
    private int corePoolSize;

    @Value("${file.import-export.thread-pool.max-size:20}")
    private int maxPoolSize;

    @Value("${file.import-export.thread-pool.queue-capacity:1000}")
    private int queueCapacity;

    @Value("${file.import-export.thread-pool.keep-alive-seconds:60}")
    private int keepAliveSeconds;

    /**
     * 自定义指标注册器
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> {
            registry.config()
                    .commonTags("application", "function-demand")
                    .commonTags("service", "requirement-management");
        };
    }

    /**
     * 异步任务执行器 - 用于导入导出
     */
    @Bean("importExportExecutor")
    public Executor importExportExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("ImportExport-");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务执行器 - 用于批量操作
     */
    @Bean("batchOperationExecutor")
    public Executor batchOperationExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("BatchOperation-");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务执行器 - 用于缓存操作
     */
    @Bean("cacheOperationExecutor")
    public Executor cacheOperationExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("CacheOperation-");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 性能监控组件
     */
    @Bean
    public PerformanceMonitor performanceMonitor(MeterRegistry meterRegistry) {
        return new PerformanceMonitor(meterRegistry, slowQueryThreshold, batchOperationThreshold);
    }

    /**
     * 性能监控实现类
     */
    public static class PerformanceMonitor {
        private final MeterRegistry meterRegistry;
        private final long slowQueryThreshold;
        private final long batchOperationThreshold;

        public PerformanceMonitor(MeterRegistry meterRegistry, long slowQueryThreshold, long batchOperationThreshold) {
            this.meterRegistry = meterRegistry;
            this.slowQueryThreshold = slowQueryThreshold;
            this.batchOperationThreshold = batchOperationThreshold;
        }

        /**
         * 记录方法执行时间
         */
        public void recordMethodExecution(String methodName, long executionTime) {
            Timer.Sample sample = Timer.start(meterRegistry);
            sample.stop(Timer.builder("method.execution.time")
                    .description("Method execution time")
                    .tag("method", methodName)
                    .register(meterRegistry));

            // 如果执行时间超过阈值，记录慢查询
            if (executionTime > slowQueryThreshold) {
                meterRegistry.counter("slow.query.count",
                        "method", methodName,
                        "threshold", String.valueOf(slowQueryThreshold))
                        .increment();
            }
        }

        /**
         * 记录批量操作性能
         */
        public void recordBatchOperation(String operationType, int batchSize, long executionTime) {
            meterRegistry.timer("batch.operation.time",
                    "operation", operationType,
                    "size", String.valueOf(batchSize))
                    .record(executionTime, java.util.concurrent.TimeUnit.MILLISECONDS);

            // 如果执行时间超过阈值，记录慢批量操作
            if (executionTime > batchOperationThreshold) {
                meterRegistry.counter("slow.batch.operation.count",
                        "operation", operationType,
                        "size", String.valueOf(batchSize))
                        .increment();
            }
        }

        /**
         * 记录数据库连接池使用情况
         */
        public void recordConnectionPoolUsage(int activeConnections, int totalConnections) {
            meterRegistry.gauge("datasource.active.connections", activeConnections);
            meterRegistry.gauge("datasource.total.connections", totalConnections);
        }

        /**
         * 记录缓存命中率
         */
        public void recordCacheHit(String cacheName, boolean hit) {
            meterRegistry.counter("cache.requests",
                    "cache", cacheName,
                    "result", hit ? "hit" : "miss")
                    .increment();
        }

        /**
         * 记录业务指标
         */
        public void recordBusinessMetric(String metricName, String operation, double value) {
            meterRegistry.gauge("business.metric",
                    "name", metricName,
                    "operation", operation,
                    value);
        }

        /**
         * 记录错误计数
         */
        public void recordError(String errorType, String operation) {
            meterRegistry.counter("error.count",
                    "type", errorType,
                    "operation", operation)
                    .increment();
        }

        /**
         * 记录内存使用情况
         */
        public void recordMemoryUsage() {
            Runtime runtime = Runtime.getRuntime();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            long maxMemory = runtime.maxMemory();

            meterRegistry.gauge("jvm.memory.used", usedMemory);
            meterRegistry.gauge("jvm.memory.free", freeMemory);
            meterRegistry.gauge("jvm.memory.total", totalMemory);
            meterRegistry.gauge("jvm.memory.max", maxMemory);
            meterRegistry.gauge("jvm.memory.usage.percentage", (double) usedMemory / maxMemory * 100);
        }

        /**
         * 记录线程池使用情况
         */
        public void recordThreadPoolUsage(String poolName, int activeThreads, int poolSize, int queueSize) {
            meterRegistry.gauge("thread.pool.active",
                    "pool", poolName,
                    activeThreads);
            meterRegistry.gauge("thread.pool.size",
                    "pool", poolName,
                    poolSize);
            meterRegistry.gauge("thread.pool.queue.size",
                    "pool", poolName,
                    queueSize);
        }
    }
}