package com.common.fileio.config;

import com.tenant.routing.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 租户上下文过滤器
 * 处理所有请求的租户切换
 */
@Component
@Order(1)
@Slf4j
public class TenantContextFilter extends OncePerRequestFilter {
    
    /**
     * 租户ID请求头
     */
    private static final String TENANT_HEADER = "X-Tenant-ID";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 获取租户ID
        String tenantId = request.getHeader(TENANT_HEADER);
        
        try {
            if (tenantId != null && !tenantId.isEmpty()) {
                // 设置租户上下文
                log.debug("设置租户上下文: {}", tenantId);
                TenantContextHolder.setTenantId(tenantId);
            }
            
            // 继续过滤链
            filterChain.doFilter(request, response);
        } finally {
            // 清理租户上下文
            log.debug("清理租户上下文");
            TenantContextHolder.clear();
        }
    }
}