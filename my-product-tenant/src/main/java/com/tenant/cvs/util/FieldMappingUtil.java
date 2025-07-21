package com.tenant.cvs.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldMappingUtil {
    // 通过实体Class和中文名Map自动映射字段
    public static Map<String, String> buildFieldMap(Class<?> clazz, Map<String, String> cnToField) {
        Map<String, String> map = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            String cn = cnToField.getOrDefault(fieldName, null);
            if (cn != null) {
                map.put(cn, fieldName);
            }
        }
        return map;
    }
    // 反向映射：字段名->中文名
    public static Map<String, String> buildReverseFieldMap(Map<String, String> cnToField) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> e : cnToField.entrySet()) {
            map.put(e.getValue(), e.getKey());
        }
        return map;
    }
} 