package com.tenant.config.tenant;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantSwitch {
    String param() default "tenantId"; // 入参名
} 