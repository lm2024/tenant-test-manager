package com.tenant.config.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    public static void set(String tenantId) {
        System.out.println("[DataSourceContextHolder] set tenantId: " + tenantId + ", 线程: " + Thread.currentThread().getName());
        CONTEXT.set(tenantId);
        logger.info("[DataSourceContextHolder] Set tenantId: {}", tenantId);
    }

    public static String get() {
        String tenantId = CONTEXT.get();
        System.out.println("[DataSourceContextHolder] get tenantId: " + tenantId + ", 线程: " + Thread.currentThread().getName());
        logger.info("[DataSourceContextHolder] Get tenantId: {}", tenantId);
        return tenantId;
    }

    public static void clear() {
        System.out.println("[DataSourceContextHolder] clear tenantId, 线程: " + Thread.currentThread().getName());
        logger.info("[DataSourceContextHolder] Clear tenantId: {}", CONTEXT.get());
        CONTEXT.remove();
    }
} 