package com.tenant.config.log;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.tenant.config.dynamic.DataSourceContextHolder;

/**
 * 租户感知的日志模式布局
 * 在SQL日志中自动拼接租户信息
 */
public class TenantAwarePatternLayout extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        String originalMessage = super.doLayout(event);
        
        // 只对SQL相关的日志进行处理
        if (isSqlLog(event)) {
            String tenantId = getCurrentTenantId();
            if (tenantId != null && !tenantId.isEmpty()) {
                // 在日志消息前添加租户信息
                return originalMessage.replaceFirst("^(.*?) - ", "$1 [Tenant: " + tenantId + "] - ");
            }
        }
        
        return originalMessage;
    }

    /**
     * 判断是否为SQL日志
     */
    private boolean isSqlLog(ILoggingEvent event) {
        String loggerName = event.getLoggerName();
        String message = event.getMessage();
        
        // 检查是否为Hibernate SQL日志
        return loggerName.contains("org.hibernate.SQL") ||
               loggerName.contains("org.hibernate.type.descriptor.sql") ||
               loggerName.contains("org.hibernate.type") ||
               (message != null && message.toLowerCase().contains("select") ||
                message.toLowerCase().contains("insert") ||
                message.toLowerCase().contains("update") ||
                message.toLowerCase().contains("delete"));
    }

    /**
     * 获取当前租户ID
     */
    private String getCurrentTenantId() {
        try {
            return DataSourceContextHolder.get();
        } catch (Exception e) {
            return null;
        }
    }
} 