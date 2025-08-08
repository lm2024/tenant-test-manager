package com.common.redis.cache.service;

import com.common.redis.cache.model.ListCacheData;
import com.common.redis.cache.model.PageInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 缓存序列化器单元测试
 * 
 * @author Kiro
 * @version 1.0.0
 */
class CacheSerializerTest {
    
    private CacheSerializer cacheSerializer;
    
    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        cacheSerializer = new CacheSerializer(objectMapper);
    }
    
    @Test
    void testSerializeAndDeserialize_ListCacheData() {
        // 准备测试数据
        List<String> content = Arrays.asList("item1", "item2", "item3");
        PageInfo pageInfo = PageInfo.of(0, 10, 100);
        
        ListCacheData<String> originalData = ListCacheData.<String>builder()
                .content(content)
                .pageInfo(pageInfo)
                .cacheTime(LocalDateTime.now())
                .tenantId("tenant123")
                .cacheKey("test:cache:key")
                .version("v1.0")
                .build();
        
        // 执行序列化
        byte[] serialized = cacheSerializer.serializeToBytes(originalData);
        
        // 验证序列化结果
        assertNotNull(serialized);
        assertTrue(serialized.length > 0);
        
        // 执行反序列化
        TypeReference<ListCacheData<String>> typeRef = new TypeReference<ListCacheData<String>>() {};
        ListCacheData<String> deserializedData = cacheSerializer.deserializeFromBytes(serialized, typeRef);
        
        // 验证反序列化结果
        assertNotNull(deserializedData);
        assertEquals(originalData.getContent().size(), deserializedData.getContent().size());
        assertEquals(originalData.getTenantId(), deserializedData.getTenantId());
        assertEquals(originalData.getCacheKey(), deserializedData.getCacheKey());
        assertEquals(originalData.getVersion(), deserializedData.getVersion());
        
        // 验证分页信息
        PageInfo deserializedPageInfo = deserializedData.getPageInfo();
        assertEquals(pageInfo.getPageNumber(), deserializedPageInfo.getPageNumber());
        assertEquals(pageInfo.getPageSize(), deserializedPageInfo.getPageSize());
        assertEquals(pageInfo.getTotalElements(), deserializedPageInfo.getTotalElements());
        
        System.out.println("Serialized size: " + serialized.length + " bytes");
    }
    
    @Test
    void testSerializeObject_SimpleObject() {
        // 准备测试数据
        TestObject testObject = new TestObject("test", 123, true);
        
        // 执行序列化
        byte[] serialized = cacheSerializer.serializeToBytes(testObject);
        
        // 验证结果
        assertNotNull(serialized);
        assertTrue(serialized.length > 0);
        
        // 执行反序列化
        TestObject deserialized = cacheSerializer.deserializeFromBytes(serialized, TestObject.class);
        
        // 验证结果
        assertNotNull(deserialized);
        assertEquals(testObject.getName(), deserialized.getName());
        assertEquals(testObject.getValue(), deserialized.getValue());
        assertEquals(testObject.isActive(), deserialized.isActive());
    }
    
    @Test
    void testToJsonAndFromJson() {
        // 准备测试数据
        TestObject testObject = new TestObject("test", 123, true);
        
        // 执行JSON序列化
        String json = cacheSerializer.serialize(testObject);
        
        // 验证JSON结果
        assertNotNull(json);
        assertTrue(json.contains("\"name\":\"test\""));
        assertTrue(json.contains("\"value\":123"));
        assertTrue(json.contains("\"active\":true"));
        
        // 执行JSON反序列化
        TestObject deserialized = cacheSerializer.deserialize(json, TestObject.class);
        
        // 验证结果
        assertNotNull(deserialized);
        assertEquals(testObject.getName(), deserialized.getName());
        assertEquals(testObject.getValue(), deserialized.getValue());
        assertEquals(testObject.isActive(), deserialized.isActive());
        
        System.out.println("JSON: " + json);
    }
    
    @Test
    void testSerialize_NullData() {
        // 执行测试
        byte[] result = cacheSerializer.serializeToBytes(null);
        
        // 验证结果
        assertNull(result);
    }
    
    @Test
    void testDeserialize_NullData() {
        // 执行测试
        TypeReference<ListCacheData<String>> typeRef = new TypeReference<ListCacheData<String>>() {};
        ListCacheData<String> result = cacheSerializer.deserializeFromBytes(null, typeRef);
        
        // 验证结果
        assertNull(result);
    }
    
    @Test
    void testDeserialize_EmptyData() {
        // 执行测试
        TypeReference<ListCacheData<String>> typeRef = new TypeReference<ListCacheData<String>>() {};
        ListCacheData<String> result = cacheSerializer.deserializeFromBytes(new byte[0], typeRef);
        
        // 验证结果
        assertNull(result);
    }
    
    @Test
    void testSerialize_InvalidData() {
        // 准备无法序列化的对象
        Object invalidObject = new Object() {
            @SuppressWarnings("unused")
            private final Object circular = this; // 循环引用
        };
        
        // 执行测试并验证异常
        assertThrows(RuntimeException.class, () -> {
            cacheSerializer.serializeToBytes(invalidObject);
        });
    }
    
    @Test
    void testDeserialize_InvalidData() {
        // 准备无效的JSON数据
        byte[] invalidData = "invalid json".getBytes();
        
        // 执行测试并验证异常
        assertThrows(RuntimeException.class, () -> {
            cacheSerializer.deserializeFromBytes(invalidData, TestObject.class);
        });
    }
    
    @Test
    void testSerializationRoundTrip() {
        // 测试序列化往返
        TestObject testObject = new TestObject("test", 123, true);
        
        // 字节序列化往返
        byte[] bytes = cacheSerializer.serializeToBytes(testObject);
        TestObject fromBytes = cacheSerializer.deserializeFromBytes(bytes, TestObject.class);
        
        assertEquals(testObject.getName(), fromBytes.getName());
        assertEquals(testObject.getValue(), fromBytes.getValue());
        assertEquals(testObject.isActive(), fromBytes.isActive());
        
        // JSON序列化往返
        String json = cacheSerializer.serialize(testObject);
        TestObject fromJson = cacheSerializer.deserialize(json, TestObject.class);
        
        assertEquals(testObject.getName(), fromJson.getName());
        assertEquals(testObject.getValue(), fromJson.getValue());
        assertEquals(testObject.isActive(), fromJson.isActive());
    }
    
    @Test
    void testCreateListCacheDataTypeReference() {
        // 执行测试
        TypeReference<ListCacheData<String>> typeRef = CacheSerializer.createListCacheDataTypeReference();
        
        // 验证结果
        assertNotNull(typeRef);
        assertNotNull(typeRef.getType());
    }
    
    /**
     * 测试对象类
     */
    public static class TestObject {
        private String name;
        private int value;
        private boolean active;
        
        public TestObject() {
        }
        
        public TestObject(String name, int value, boolean active) {
            this.name = name;
            this.value = value;
            this.active = active;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getValue() {
            return value;
        }
        
        public void setValue(int value) {
            this.value = value;
        }
        
        public boolean isActive() {
            return active;
        }
        
        public void setActive(boolean active) {
            this.active = active;
        }
    }
}