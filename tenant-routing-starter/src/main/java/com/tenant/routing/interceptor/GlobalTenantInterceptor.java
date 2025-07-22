package com.tenant.routing.interceptor;

import com.tenant.routing.config.TenantRoutingProperties;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.routing.entity.TenantDbInfo;
import com.tenant.routing.service.TenantDbInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 全局租户拦截器
 */
public class GlobalTenantInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalTenantInterceptor.class);
    
    @Autowired(required = false)
    private TenantRoutingProperties properties;
    
    @Autowired
    private TenantDbInfoService tenantDbInfoService;
    
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
        
        // 尝试获取租户ID
        String tenantId = getTenantId(request);
        
        if (tenantId != null && !tenantId.trim().isEmpty()) {
            // 从数据库查询租户信息
            TenantDbInfo tenantDbInfo = tenantDbInfoService.findByTenantId(tenantId.trim());
            
            if (tenantDbInfo != null) {
                // 设置租户上下文
                TenantContextHolder.setTenantId(tenantId.trim());
                // 可以在这里将租户信息存储在请求属性中，方便后续使用
                request.setAttribute("TENANT_DB_INFO", tenantDbInfo);
                logger.debug("Set tenant context: {}", tenantId.trim());
            } else {
                logger.warn("Tenant not found: {}", tenantId.trim());
                if (properties.isRequired()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"Tenant not found: " + tenantId.trim() + "\"}");
                    return false;
                }
            }
        } else if (properties.isRequired()) {
            logger.warn("Missing tenant ID in request");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Missing tenant ID in request header\"}");
            return false;
        }
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TenantContextHolder.clear();
        logger.debug("Cleared tenant context");
    }
    
    /**
     * 获取租户ID
     * 优先级：请求头 > 请求参数 > 会话
     */
    private String getTenantId(HttpServletRequest request) {
        // 1. 尝试从请求头获取
        String tenantId = getTenantIdFromHeaders(request);
        if (tenantId != null) {
            return tenantId;
        }
        
        // 2. 尝试从请求参数获取
        tenantId = request.getParameter("tenantId");
        if (tenantId != null && !tenantId.trim().isEmpty()) {
            return tenantId;
        }
        
        // 3. 尝试从会话获取
        HttpSession session = request.getSession(false);
        if (session != null) {
            tenantId = (String) session.getAttribute("TENANT_ID");
            if (tenantId != null && !tenantId.trim().isEmpty()) {
                return tenantId;
            }
        }
        
        return null;
    }
    
    /**
     * 从请求头获取租户ID
     */
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