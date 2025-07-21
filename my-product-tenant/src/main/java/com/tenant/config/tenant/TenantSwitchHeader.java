package com.tenant.config.tenant;

import java.lang.annotation.*;

/**
 * 租户切换注解 - 从请求头获取租户ID
 * 使用方式：@TenantSwitchHeader(headerName = "X-Tenant-ID")
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantSwitchHeader {
    /**
     * 请求头名称，默认为 "X-Tenant-ID"
     */
    String headerName() default "X-Tenant-ID";
    
    /**
     * 是否必需，默认为true
     */
    boolean required() default true;
} 