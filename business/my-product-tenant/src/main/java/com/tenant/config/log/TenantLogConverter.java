package com.tenant.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.tenant.routing.core.TenantContextHolder;

/**
 * 租户日志转换器
 * 用于在日志中显示当前租户ID
 */
public class TenantLogConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            return "[NO_TENANT]";
        }
        return "[" + tenantId + "]";
    }
} 