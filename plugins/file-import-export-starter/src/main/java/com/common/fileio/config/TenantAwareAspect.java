package com.common.fileio.config;

import com.tenant.routing.core.TenantContextHolder;
import com.tenant.routing.annotation.TenantSwitchHeader;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 租户感知切面
 * 自动处理租户切换
 */
@Aspect
@Component
@Slf4j
public class TenantAwareAspect {
    
    /**
     * 处理带有TenantSwitchHeader注解的方法
     * 
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 如果方法执行失败
     */
    @Around("@annotation(com.tenant.routing.annotation.TenantSwitchHeader)")
    public Object aroundTenantSwitchHeader(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 获取注解
        TenantSwitchHeader annotation = method.getAnnotation(TenantSwitchHeader.class);
        if (annotation == null) {
            return joinPoint.proceed();
        }
        
        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 获取租户ID
        String headerName = annotation.headerName();
        String tenantId = request.getHeader(headerName);
        
        if (tenantId == null || tenantId.isEmpty()) {
            log.warn("租户ID为空，使用默认租户");
            return joinPoint.proceed();
        }
        
        try {
            // 设置租户上下文
            log.debug("设置租户上下文: {}", tenantId);
            TenantContextHolder.setTenantId(tenantId);
            
            // 执行方法
            return joinPoint.proceed();
        } finally {
            // 清理租户上下文
            log.debug("清理租户上下文");
            TenantContextHolder.clear();
        }
    }
}