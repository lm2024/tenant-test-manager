package com.example.functiondemand.common.util;

import cn.hutool.core.util.IdUtil;

public class IdGenerator {
    
    public static String generateId() {
        return IdUtil.fastSimpleUUID();
    }
    
    public static String generateRequirementId() {
        return "REQ_" + IdUtil.fastSimpleUUID();
    }
    
    public static String generateFunctionId() {
        return "FUNC_" + IdUtil.fastSimpleUUID();
    }
    
    public static String generateCategoryId() {
        return "CAT_" + IdUtil.fastSimpleUUID();
    }
    
    public static String generateRelationId() {
        return "REL_" + IdUtil.fastSimpleUUID();
    }
}