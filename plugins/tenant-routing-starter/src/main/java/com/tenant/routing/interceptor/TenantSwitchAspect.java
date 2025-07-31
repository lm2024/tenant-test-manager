package com.tenant.routing.interceptor;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 租户切换切面
 * 使用栈式管理支持嵌套租户切换
 */
@Aspect
public class TenantSwitchAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantSwitchAspect.class);
    
    @Around("@annotation(tenantSwitch)")
    public Object around(ProceedingJoinPoint joinPoint, TenantSwitch tenantSwitch) throws Throwable {
        String originalTenantId = TenantContextHolder.getTenantId();
        String targetTenantId = tenantSwitch.value();
        boolean switched = false;
        
        logger.debug("TenantSwitch: original={}, target={}, stackSize={}", 
                    originalTenantId, targetTenantId, TenantContextHolder.getStackSize());
        
        try {
            // 如果注解指定了租户ID，则使用指定的
            if (!targetTenantId.isEmpty()) {
                TenantContextHolder.setTenantId(targetTenantId);
                switched = true;
                logger.debug("Pushed tenant to stack: {}, stackSize={}", 
                           targetTenantId, TenantContextHolder.getStackSize());
            } else if (tenantSwitch.required() && !TenantContextHolder.hasTenant()) {
                throw new RuntimeException("Tenant ID is required but not found");
            }
            
            return joinPoint.proceed();
        } finally {
            // 如果进行了切换，则弹出当前租户，恢复到上一个租户
            if (switched) {
                String poppedTenantId = TenantContextHolder.popTenantId();
                String currentTenantId = TenantContextHolder.getTenantId();
                logger.debug("Popped tenant from stack: {}, current={}, stackSize={}", 
                           poppedTenantId, currentTenantId, TenantContextHolder.getStackSize());
            }
        }
    }
}