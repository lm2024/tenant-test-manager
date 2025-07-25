package com.tenant.routing.interceptor;

import com.tenant.routing.annotation.TenantSwitchHeader;
import com.tenant.routing.core.TenantContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 租户切换切面
 * 处理带有TenantSwitchHeader注解的方法
 */
@Aspect
@Component
public class TenantSwitchHeaderAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(TenantSwitchHeaderAspect.class);
    
    /**
     * 处理带有TenantSwitchHeader注解的方法
     * 
     * @param joinPoint 连接点
     * @param tenantSwitchHeader 注解
     * @return 方法执行结果
     * @throws Throwable 如果方法执行失败
     */
    @Around("@annotation(tenantSwitchHeader)")
    public Object around(ProceedingJoinPoint joinPoint, TenantSwitchHeader tenantSwitchHeader) throws Throwable {
        // 获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 从请求头获取租户ID
        String tenantId = request.getHeader(tenantSwitchHeader.headerName());
        
        // 验证租户ID
        if (tenantId == null || tenantId.trim().isEmpty()) {
            if (tenantSwitchHeader.required()) {
                throw new RuntimeException("请求头缺少租户ID: " + tenantSwitchHeader.headerName());
            } else {
                // 如果不是必需的，直接执行原方法
                return joinPoint.proceed();
            }
        }
        
        try {
            // 设置租户上下文
            logger.debug("设置租户上下文: {}", tenantId);
            TenantContextHolder.setTenantId(tenantId);
            
            // 执行方法
            return joinPoint.proceed();
        } finally {
            // 清理租户上下文
            logger.debug("清理租户上下文");
            TenantContextHolder.clear();
        }
    }
}