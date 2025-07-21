package com.tenant.cvs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class CvsThreadPoolConfig {
    @Value("${com.tenant.cvs.import.concurrent:1}")
    private int importConcurrent;
    @Value("${com.tenant.cvs.export.concurrent:10}")
    private int exportConcurrent;

    @Bean(name = "cvsImportExecutor")
    public ThreadPoolTaskExecutor cvsImportExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(importConcurrent);
        executor.setMaxPoolSize(importConcurrent);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("com.tenant.cvs-import-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

    @Bean(name = "cvsExportExecutor")
    public ThreadPoolTaskExecutor cvsExportExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(exportConcurrent);
        executor.setMaxPoolSize(exportConcurrent);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("com.tenant.cvs-export-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
} 