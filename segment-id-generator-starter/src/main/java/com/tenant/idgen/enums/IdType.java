package com.tenant.idgen.enums;

/**
 * ID生成类型枚举
 * 
 * @author system
 * @since 1.0.0
 */
public enum IdType {
    
    /**
     * 纯数字自增：00000001~99999999
     */
    NUMERIC,
    
    /**
     * 前缀+数字：PREFIX00000001
     */
    PREFIX_NUMERIC,
    
    /**
     * 数字+后缀：00000001SUFFIX
     */
    NUMERIC_SUFFIX,
    
    /**
     * 业务自定义长度数字
     */
    CUSTOM_LENGTH
}