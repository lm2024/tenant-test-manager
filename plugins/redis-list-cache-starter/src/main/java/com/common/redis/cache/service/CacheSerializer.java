package com.common.redis.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 缓存序列化服务
 * 负责将对象序列化为JSON字符串以及反序列化
 * 
 * @author Kiro
 * @since 1.0.0
 */
@Slf4j
@Component
public class CacheSerializer {
    
    private final ObjectMapper objectMapper;
    
    public CacheSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    /**
     * 将对象序列化为JSON字符串
     * 
     * @param object 要序列化的对象
     * @return JSON字符串
     */
    public String serialize(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("序列化对象失败: {}", object.getClass().getName(), e);
            throw new RuntimeException("序列化失败", e);
        }
    }
    
    /**
     * 将对象序列化为字节数组
     * 
     * @param object 要序列化的对象
     * @return 字节数组
     */
    public byte[] serializeToBytes(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.error("序列化对象为字节数组失败: {}", object.getClass().getName(), e);
            throw new RuntimeException("序列化失败", e);
        }
    }
    
    /**
     * 将字节数组反序列化为指定类型的对象
     * 
     * @param bytes 字节数组
     * @param clazz 目标类型
     * @param <T> 泛型类型
     * @return 反序列化后的对象
     */
    public <T> T deserializeFromBytes(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            log.error("反序列化字节数组失败, 目标类型: {}", clazz.getName(), e);
            throw new RuntimeException("反序列化失败", e);
        }
    }
    
    /**
     * 将字节数组反序列化为指定类型的对象（支持泛型）
     * 
     * @param bytes 字节数组
     * @param typeReference 类型引用
     * @param <T> 泛型类型
     * @return 反序列化后的对象
     */
    public <T> T deserializeFromBytes(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        
        try {
            return objectMapper.readValue(bytes, typeReference);
        } catch (Exception e) {
            log.error("反序列化字节数组失败, 目标类型: {}", typeReference.getType(), e);
            throw new RuntimeException("反序列化失败", e);
        }
    }
    
    /**
     * 将JSON字符串反序列化为指定类型的对象
     * 
     * @param json JSON字符串
     * @param clazz 目标类型
     * @param <T> 泛型类型
     * @return 反序列化后的对象
     */
    public <T> T deserialize(String json, Class<T> clazz) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("反序列化JSON失败: {}, 目标类型: {}", json, clazz.getName(), e);
            throw new RuntimeException("反序列化失败", e);
        }
    }
    
    /**
     * 将JSON字符串反序列化为指定类型的对象（支持泛型）
     * 
     * @param json JSON字符串
     * @param typeReference 类型引用
     * @param <T> 泛型类型
     * @return 反序列化后的对象
     */
    public <T> T deserialize(String json, TypeReference<T> typeReference) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("反序列化JSON失败: {}, 目标类型: {}", json, typeReference.getType(), e);
            throw new RuntimeException("反序列化失败", e);
        }
    }
    
    /**
     * 创建ListCacheData的TypeReference
     * 
     * @param <T> 数据类型
     * @return TypeReference实例
     */
    public static <T> TypeReference<com.common.redis.cache.model.ListCacheData<T>> createListCacheDataTypeReference() {
        return new TypeReference<com.common.redis.cache.model.ListCacheData<T>>() {};
    }
}