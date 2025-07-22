package com.tenant.routing.interceptor;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 租户切换切面
 */
@Aspect
public class TenantSwitchAspect {
    
    @Around("@annotation(tenantSwitch)")
    public Object around(ProceedingJoinPoint joinPoint, TenantSwitch tenantSwitch) throws Throwable {
        String originalTenantId = TenantContextHolder.getTenantId();
        
        try {
            // 如果注解指定了租户ID，则使用指定的
            if (!tenantSwitch.value().isEmpty()) {
                TenantContextHolder.setTenantId(tenantSwitch.value());
            } else if (tenantSwitch.required() && !TenantContextHolder.hasTenant()) {
                throw new RuntimeException("Tenant ID is required but not found");
            }
            
            return joinPoint.proceed();
        } finally {
            // 恢复原始租户ID
            if (originalTenantId != null) {
                TenantContextHolder.setTenantId(originalTenantId);
            } else {
                TenantContextHolder.clear();
            }
        }
    }
}