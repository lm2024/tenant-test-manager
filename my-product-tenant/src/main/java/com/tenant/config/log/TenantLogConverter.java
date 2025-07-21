package com.tenant.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.tenant.config.dynamic.DataSourceContextHolder;

/**
 * 租户信息转换器
 * 用于在Logback模式中显示当前租户ID
 * 使用方式：%tenant
 */
public class TenantLogConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            String tenantId = DataSourceContextHolder.get();
            if (tenantId != null && !tenantId.isEmpty()) {
                return "[Tenant:" + tenantId + "]";
            }
        } catch (Exception e) {
            // 忽略异常，返回空字符串
        }
        return "";
    }
} 