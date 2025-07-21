package com.tenant.config;

import com.tenant.config.dynamic.DataSourceContextHolder;
import com.tenant.config.dynamic.DynamicDataSourceManager;
import com.tenant.entity.TenantDbInfo;
import com.tenant.repository.TenantDbInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局租户拦截器
 * 自动从请求头获取租户ID并切换数据源
 */
@Component
public class GlobalTenantInterceptor implements HandlerInterceptor {

    @Autowired
    private TenantDbInfoRepository tenantDbInfoRepository;
    @Autowired
    private DynamicDataSourceManager dynamicDataSourceManager;

    // 配置项：是否启用全局租户拦截器
    @Value("${tenant.global-interceptor.enabled:false}")
    private boolean globalInterceptorEnabled;

    // 配置项：租户ID请求头名称
    @Value("${tenant.global-interceptor.header-name:X-Tenant-ID}")
    private String headerName;

    // 配置项：是否必需租户ID
    @Value("${tenant.global-interceptor.required:true}")
    private boolean required;

    // 配置项：排除的路径
    @Value("${tenant.global-interceptor.exclude-paths:/api/tenant/**,/swagger-ui.html,/doc.html,/druid/**}")
    private String excludePaths;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 检查是否启用全局拦截器
        if (!globalInterceptorEnabled) {
            return true;
        }

        // 2. 检查是否在排除路径中
        String requestURI = request.getRequestURI();
        if (isExcludedPath(requestURI)) {
            return true;
        }

        // 3. 从请求头获取租户ID
        String tenantId = getTenantIdFromHeader(request);

        // 4. 验证租户ID
        if (tenantId == null || tenantId.trim().isEmpty()) {
            if (required) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\":\"请求头缺少租户ID: " + headerName + "\"}");
                return false;
            } else {
                // 如果不是必需的，继续执行
                return true;
            }
        }

        // 5. 查询租户信息
        try {
            TenantDbInfo dbInfo = tenantDbInfoRepository.findByTenantId(tenantId.trim())
                    .orElseThrow(() -> new RuntimeException("租户不存在: " + tenantId));

            // 6. 注册数据源
            dynamicDataSourceManager.getOrCreateDataSource(dbInfo);

            // 7. 切换上下文
            DataSourceContextHolder.set(tenantId.trim());
        } catch (Exception e) {
            if (required) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
                return false;
            }
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理租户上下文
        DataSourceContextHolder.clear();
    }

    /**
     * 从请求头获取租户ID
     */
    private String getTenantIdFromHeader(HttpServletRequest request) {
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

    /**
     * 检查是否在排除路径中
     */
    private boolean isExcludedPath(String requestURI) {
        if (excludePaths == null || excludePaths.trim().isEmpty()) {
            return false;
        }

        String[] paths = excludePaths.split(",");
        for (String path : paths) {
            path = path.trim();
            if (path.startsWith("/")) {
                if (requestURI.startsWith(path)) {
                    return true;
                }
            } else {
                if (requestURI.contains(path)) {
                    return true;
                }
            }
        }
        return false;
    }
} 