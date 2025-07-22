package com.tenant.routing.interceptor;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.TenantContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租户请求头拦截器
 */
public class TenantHeaderInterceptor implements HandlerInterceptor {
    
    @Autowired(required = false)
    private TenantRoutingProperties properties;
    
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果配置为空或未启用，直接通过
        if (properties == null || !properties.isEnabled()) {
            return true;
        }
        
        String requestPath = request.getRequestURI();
        
        // 检查是否为排除路径
        for (String excludePath : properties.getExcludePaths()) {
            if (pathMatcher.match(excludePath, requestPath)) {
                return true;
            }
        }
        
        // 尝试从多个请求头获取租户ID
        String tenantId = getTenantIdFromHeaders(request);
        
        if (tenantId != null && !tenantId.trim().isEmpty()) {
            TenantContextHolder.setTenantId(tenantId.trim());
        } else if (properties.isRequired()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Missing tenant ID in request header\"}");
            return false;
        }
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TenantContextHolder.clear();
    }
    
    private String getTenantIdFromHeaders(HttpServletRequest request) {
        // 尝试多种请求头名称
        String[] headerNames = {
            properties != null ? properties.getHeaderName() : "X-Tenant-ID",
            "X-Tenant-ID", "X-TenantId", "X-Tenant",
            "tenant-id", "tenantId", "tenant",
            "X-User-Tenant", "User-Tenant"
        };
        
        for (String headerName : headerNames) {
            if (headerName != null) {
                String tenantId = request.getHeader(headerName);
                if (tenantId != null && !tenantId.trim().isEmpty()) {
                    return tenantId;
                }
            }
        }
        
        return null;
    }
}