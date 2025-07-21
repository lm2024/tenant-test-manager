package com.tenant.config.tenant;

import com.tenant.config.dynamic.DataSourceContextHolder;
import com.tenant.config.dynamic.DynamicDataSourceManager;
import com.tenant.entity.TenantDbInfo;
import com.tenant.repository.TenantDbInfoRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TenantSwitchAspect {

    @Autowired
    private TenantDbInfoRepository tenantDbInfoRepository;
    @Autowired
    private DynamicDataSourceManager dynamicDataSourceManager;

    @Around("@annotation(tenantSwitch)")
    public Object around(ProceedingJoinPoint joinPoint, TenantSwitch tenantSwitch) throws Throwable {
        // 1. 获取方法参数
        String tenantId = getTenantIdFromArgs(joinPoint, tenantSwitch.param());
        if (tenantId == null) {
            throw new RuntimeException("请求缺少tenantId参数");
        }
        // 2. 查租户信息
        TenantDbInfo dbInfo = tenantDbInfoRepository.findByTenantId(tenantId)
                .orElseThrow(() -> new RuntimeException("租户不存在: " + tenantId));
        // 3. 注册数据源
        dynamicDataSourceManager.getOrCreateDataSource(dbInfo);
        // 4. 切换上下文
        DataSourceContextHolder.set(tenantId);
        try {
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }
    }

    private String getTenantIdFromArgs(ProceedingJoinPoint joinPoint, String paramName) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            // 1. 直接参数
            if (paramName.equals(paramNames[i]) && args[i] instanceof String) {
                return (String) args[i];
            }
            // 2. 对象参数，尝试反射getTenantId
            if (args[i] != null) {
                try {
                    java.lang.reflect.Method m = args[i].getClass().getMethod("getTenantId");
                    Object value = m.invoke(args[i]);
                    if (value instanceof String) {
                        return (String) value;
                    }
                } catch (Exception ignore) {}
            }
        }
        return null;
    }
} 