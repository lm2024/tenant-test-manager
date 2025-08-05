package com.example.functiondemand.common.util;

import cn.hutool.core.util.StrUtil;
import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantValidationUtil {
    
    public static void validateTenantContext() {
        String tenantId = TenantContextHolder.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("租户上下文未设置，请确保请求头包含X-Tenant-ID");
        }
        log.debug("当前租户上下文: {}", tenantId);
    }
    
    public static String getCurrentTenantId() {
        String tenantId = TenantContextHolder.getTenantId();
        if (StrUtil.isBlank(tenantId)) {
            throw new IllegalStateException("租户上下文未设置");
        }
        return tenantId;
    }
    
    public static boolean hasTenantContext() {
        return TenantContextHolder.hasTenant();
    }
    
    public static void logTenantOperation(String operation, String entityId) {
        String tenantId = TenantContextHolder.getTenantId();
        log.info("租户操作 - 租户: {}, 操作: {}, 实体ID: {}", tenantId, operation, entityId);
    }
}