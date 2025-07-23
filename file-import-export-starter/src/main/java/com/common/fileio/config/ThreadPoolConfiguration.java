package com.common.fileio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * 为导入和导出任务配置独立的线程池
 */
@Configuration
public class ThreadPoolConfiguration {
    
    @Autowired
    private FileImportExportProperties properties;
    
    /**
     * 导入任务线程池
     * 
     * @return ThreadPoolTaskExecutor
     */
    @Bean(name = "importTaskExecutor")
    public ThreadPoolTaskExecutor importTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getImportConfig().getConcurrent());
        executor.setMaxPoolSize(properties.getImportConfig().getConcurrent());
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("import-task-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
    
    /**
     * 导出任务线程池
     * 
     * @return ThreadPoolTaskExecutor
     */
    @Bean(name = "exportTaskExecutor")
    public ThreadPoolTaskExecutor exportTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getExportConfig().getConcurrent());
        executor.setMaxPoolSize(properties.getExportConfig().getConcurrent());
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("export-task-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}