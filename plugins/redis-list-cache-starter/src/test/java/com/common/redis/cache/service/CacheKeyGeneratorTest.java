package com.common.redis.cache.service;

import com.common.redis.cache.annotation.ListCache;
import com.common.redis.cache.model.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 缓存键生成器单元测试
 * 
 * @author Kiro
 * @version 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class CacheKeyGeneratorTest {
    
    @InjectMocks
    private CacheKeyGenerator cacheKeyGenerator;
    
    private Method testMethod;
    private ListCache listCache;
    
    @BeforeEach
    void setUp() throws Exception {
        // 获取测试方法
        testMethod = TestService.class.getMethod("findUsers", String.class, Pageable.class);
        
        // 模拟ListCache注解
        listCache = mock(ListCache.class);
        when(listCache.value()).thenReturn("users");
        when(listCache.keyPrefix()).thenReturn("test_service");
        when(listCache.tenantAware()).thenReturn(true);
    }
    
    @Test
    void testGenerateListCacheKey_WithBasicParameters() {
        // 准备测试数据
        Object[] args = {"active", PageRequest.of(0, 10)};
        PageInfo pageInfo = PageInfo.of(0, 10);
        
        // 执行测试
        String cacheKey = cacheKeyGenerator.generateListCacheKey(testMethod, args, listCache, pageInfo);
        
        // 验证结果
        assertNotNull(cacheKey);
        assertTrue(cacheKey.contains("test_service"));
        assertTrue(cacheKey.contains("users"));
        assertTrue(cacheKey.contains("page_0"));
        assertTrue(cacheKey.contains("size_10"));
        
        System.out.println("Generated cache key: " + cacheKey);
    }
    
    @Test
    void testGenerateListCacheKey_WithSortParameters() {
        // 准备测试数据
        Pageable pageable = PageRequest.of(1, 20, Sort.by("name").ascending());
        Object[] args = {"active", pageable};
        PageInfo pageInfo = PageInfo.fromPageable(pageable);
        
        // 执行测试
        String cacheKey = cacheKeyGenerator.generateListCacheKey(testMethod, args, listCache, pageInfo);
        
        // 验证结果
        assertNotNull(cacheKey);
        assertTrue(cacheKey.contains("page_1"));
        assertTrue(cacheKey.contains("size_20"));
        assertTrue(cacheKey.contains("sort_")); // 排序信息的哈希
        
        System.out.println("Generated cache key with sort: " + cacheKey);
    }
    
    @Test
    void testGenerateListCacheKey_WithoutTenantAware() {
        // 设置不启用租户隔离
        when(listCache.tenantAware()).thenReturn(false);
        
        Object[] args = {"active", PageRequest.of(0, 10)};
        PageInfo pageInfo = PageInfo.of(0, 10);
        
        // 执行测试
        String cacheKey = cacheKeyGenerator.generateListCacheKey(testMethod, args, listCache, pageInfo);
        
        // 验证结果
        assertNotNull(cacheKey);
        assertFalse(cacheKey.startsWith("default:")); // 不应该包含租户前缀
        assertTrue(cacheKey.startsWith("test_service"));
        
        System.out.println("Generated cache key without tenant: " + cacheKey);
    }
    
    @Test
    void testGenerateListCacheKey_WithCustomKeyPrefix() {
        // 设置自定义键前缀
        when(listCache.keyPrefix()).thenReturn("custom_prefix");
        
        Object[] args = {"active", PageRequest.of(0, 10)};
        PageInfo pageInfo = PageInfo.of(0, 10);
        
        // 执行测试
        String cacheKey = cacheKeyGenerator.generateListCacheKey(testMethod, args, listCache, pageInfo);
        
        // 验证结果
        assertNotNull(cacheKey);
        assertTrue(cacheKey.contains("custom_prefix"));
        
        System.out.println("Generated cache key with custom prefix: " + cacheKey);
    }
    
    @Test
    void testGenerateEvictKeyPattern_WithCacheName() {
        // 执行测试
        String pattern = cacheKeyGenerator.generateEvictKeyPattern(testMethod, "users", "", true);
        
        // 验证结果
        assertNotNull(pattern);
        assertTrue(pattern.contains("users"));
        assertTrue(pattern.endsWith("*"));
        
        System.out.println("Generated evict pattern: " + pattern);
    }
    
    @Test
    void testGenerateEvictKeyPattern_WithKeyPattern() {
        // 执行测试
        String pattern = cacheKeyGenerator.generateEvictKeyPattern(testMethod, "", "user:*:profile", true);
        
        // 验证结果
        assertNotNull(pattern);
        assertTrue(pattern.contains("user:*:profile"));
        
        System.out.println("Generated evict pattern with key pattern: " + pattern);
    }
    
    @Test
    void testExtractTenantId_ValidKey() {
        // 准备测试数据
        String cacheKey = "tenant123:list_cache:users:param_hash:page_0";
        
        // 执行测试
        String tenantId = cacheKeyGenerator.extractTenantId(cacheKey);
        
        // 验证结果
        assertEquals("tenant123", tenantId);
    }
    
    @Test
    void testExtractTenantId_InvalidKey() {
        // 准备测试数据
        String cacheKey = "list_cache:users:param_hash:page_0";
        
        // 执行测试
        String tenantId = cacheKeyGenerator.extractTenantId(cacheKey);
        
        // 验证结果
        assertNull(tenantId);
    }
    
    @Test
    void testMatchesPattern_SimplePattern() {
        // 准备测试数据
        String cacheKey = "tenant123:list_cache:users:abc123:page_0";
        String pattern = "tenant123:list_cache:users:*";
        
        // 执行测试
        boolean matches = cacheKeyGenerator.matchesPattern(cacheKey, pattern);
        
        // 验证结果
        assertTrue(matches);
    }
    
    @Test
    void testMatchesPattern_ComplexPattern() {
        // 准备测试数据
        String cacheKey = "tenant123:list_cache:users:abc123:page_1";
        String pattern = "*:list_cache:users:*:page_?";
        
        // 执行测试
        boolean matches = cacheKeyGenerator.matchesPattern(cacheKey, pattern);
        
        // 验证结果
        assertTrue(matches);
    }
    
    @Test
    void testIsValidCacheKey_ValidKey() {
        // 准备测试数据
        String cacheKey = "tenant123:list_cache:users:abc123:page_0";
        
        // 执行测试
        boolean valid = cacheKeyGenerator.isValidCacheKey(cacheKey);
        
        // 验证结果
        assertTrue(valid);
    }
    
    @Test
    void testIsValidCacheKey_InvalidKey() {
        // 准备测试数据
        String cacheKey = "invalid:key";
        
        // 执行测试
        boolean valid = cacheKeyGenerator.isValidCacheKey(cacheKey);
        
        // 验证结果
        assertFalse(valid);
    }
    
    @Test
    void testGetCacheKeyDebugInfo() {
        // 准备测试数据
        String cacheKey = "tenant123:list_cache:users:abc123:page_0_size_10";
        
        // 执行测试
        String debugInfo = cacheKeyGenerator.getCacheKeyDebugInfo(cacheKey);
        
        // 验证结果
        assertNotNull(debugInfo);
        assertTrue(debugInfo.contains("tenant=tenant123"));
        assertTrue(debugInfo.contains("prefix=list_cache"));
        assertTrue(debugInfo.contains("name=users"));
        
        System.out.println("Debug info: " + debugInfo);
    }
    
    @Test
    void testGenerateSimpleCacheKey() {
        // 执行测试
        String cacheKey = cacheKeyGenerator.generateSimpleCacheKey("test_cache", true);
        
        // 验证结果
        assertNotNull(cacheKey);
        assertTrue(cacheKey.contains("list_cache"));
        assertTrue(cacheKey.contains("test_cache"));
        
        System.out.println("Simple cache key: " + cacheKey);
    }
    
    /**
     * 测试服务类
     */
    public static class TestService {
        public void findUsers(String status, Pageable pageable) {
            // 测试方法
        }
    }
}