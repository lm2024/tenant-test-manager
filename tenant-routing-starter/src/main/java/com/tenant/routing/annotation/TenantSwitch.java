package com.tenant.routing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 租户切换注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantSwitch {
    
    /**
     * 指定租户ID，如果为空则从请求头获取
     */
    String value() default "";
    
    /**
     * 是否必需租户ID
     */
    boolean required() default true;
}