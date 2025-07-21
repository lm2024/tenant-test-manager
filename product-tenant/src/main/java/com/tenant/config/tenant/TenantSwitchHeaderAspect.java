package com.tenant.config.tenant;

import com.tenant.config.dynamic.DataSourceContextHolder;
import com.tenant.config.dynamic.DynamicDataSourceManager;
import com.tenant.entity.TenantDbInfo;
import com.tenant.repository.TenantDbInfoRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 租户切换切面 - 从请求头获取租户ID
 */
@Aspect
@Component
public class TenantSwitchHeaderAspect {

    @Autowired
    private TenantDbInfoRepository tenantDbInfoRepository;
    @Autowired
    private DynamicDataSourceManager dynamicDataSourceManager;

    @Around("@annotation(tenantSwitchHeader)")
    public Object around(ProceedingJoinPoint joinPoint, TenantSwitchHeader tenantSwitchHeader) throws Throwable {
        // 1. 获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("无法获取当前请求上下文");
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 2. 从请求头获取租户ID
        String tenantId = getTenantIdFromHeader(request, tenantSwitchHeader.headerName());
        
        // 3. 验证租户ID
        if (tenantId == null || tenantId.trim().isEmpty()) {
            if (tenantSwitchHeader.required()) {
                throw new RuntimeException("请求头缺少租户ID: " + tenantSwitchHeader.headerName());
            } else {
                // 如果不是必需的，直接执行原方法
                return joinPoint.proceed();
            }
        }
        
        // 4. 查询租户信息
        TenantDbInfo dbInfo = tenantDbInfoRepository.findByTenantId(tenantId.trim())
                .orElseThrow(() -> new RuntimeException("租户不存在: " + tenantId));
        
        // 5. 注册数据源
        dynamicDataSourceManager.getOrCreateDataSource(dbInfo);
        
        // 6. 切换上下文
        DataSourceContextHolder.set(tenantId.trim());
        
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

    /**
     * 从请求头获取租户ID
     */
    private String getTenantIdFromHeader(HttpServletRequest request, String headerName) {
        // 尝试多种常见的租户ID请求头名称
        String tenantId = request.getHeader(headerName);
        
        if (tenantId == null) {
            // 如果指定的headerName没有找到，尝试其他常见的名称
            String[] commonHeaderNames = {
                "X-Tenant-ID", "X-TenantId", "X-Tenant", 
                "tenant-id", "tenantId", "tenant",
                "X-User-Tenant", "User-Tenant"
            };
            
            for (String name : commonHeaderNames) {
                if (!name.equals(headerName)) { // 避免重复检查
                    tenantId = request.getHeader(name);
                    if (tenantId != null) {
                        break;
                    }
                }
            }
        }
        
        return tenantId;
    }
} 