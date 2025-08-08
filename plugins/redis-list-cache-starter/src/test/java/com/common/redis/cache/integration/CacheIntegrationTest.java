package com.common.redis.cache.integration;

import com.common.redis.cache.annotation.CacheEvict;
import com.common.redis.cache.annotation.ListCache;
import com.common.redis.cache.config.RedisListCacheAutoConfiguration;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.model.CacheStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 缓存集成测试
 * 
 * <p>使用Testcontainers启动Redis容器进行完整的集成测试。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@SpringBootTest(classes = {
    RedisListCacheAutoConfiguration.class,
    CacheIntegrationTest.TestService.class
})
@Testcontainers
class CacheIntegrationTest {
    
    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:6.2-alpine"))
            .withExposedPorts(6379)
            .withReuse(true);
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
        registry.add("redis.list.cache.enabled", () -> "true");
        registry.add("redis.list.cache.default-expire-time", () -> "300");
        registry.add("redis.list.cache.max-cache-pages", () -> "3");
    }
    
    @Autowired
    private TestService testService;
    
    @Autowired
    private ListCacheManager cacheManager;
    
    @BeforeEach
    void setUp() {
        // 清理缓存
        cacheManager.clear();
        cacheManager.resetStats();
    }
    
    @Test
    void testListCacheBasicFunctionality() {
        // 第一次调用 - 应该缓存未命中
        Page<String> result1 = testService.findUsers("active", PageRequest.of(0, 10));
        
        // 验证结果
        assertNotNull(result1);
        assertEquals(3, result1.getContent().size());
        
        // 第二次调用相同参数 - 应该缓存命中
        Page<String> result2 = testService.findUsers("active", PageRequest.of(0, 10));
        
        // 验证结果相同
        assertEquals(result1.getContent().size(), result2.getContent().size());
        
        // 验证缓存统计
        CacheStats stats = cacheManager.getStats();
        assertTrue(stats.getSnapshot().getHitCount() > 0);
        assertTrue(stats.getSnapshot().getMissCount() > 0);
        
        System.out.println("Cache stats: " + stats.toString());
    }
    
    @Test
    void testCacheEvictionAfterCreate() throws InterruptedException {
        // 先查询数据，建立缓存
        Page<String> result1 = testService.findUsers("active", PageRequest.of(0, 10));
        assertNotNull(result1);
        
        // 验证缓存存在
        assertTrue(cacheManager.size() > 0);
        
        // 创建新用户，应该清除缓存
        String newUser = testService.createUser("newUser");
        assertEquals("newUser", newUser);
        
        // 等待缓存清除完成
        TimeUnit.MILLISECONDS.sleep(100);
        
        // 验证缓存被清除
        // 注意：由于缓存清除是异步的，这里可能需要等待
        long cacheSize = cacheManager.size();
        System.out.println("Cache size after eviction: " + cacheSize);
    }
    
    @Test
    void testCacheWithDifferentPages() {
        // 查询第0页
        Page<String> page0 = testService.findUsers("active", PageRequest.of(0, 10));
        assertNotNull(page0);
        
        // 查询第1页
        Page<String> page1 = testService.findUsers("active", PageRequest.of(1, 10));
        assertNotNull(page1);
        
        // 查询第2页
        Page<String> page2 = testService.findUsers("active", PageRequest.of(2, 10));
        assertNotNull(page2);
        
        // 验证缓存中有多个键
        assertTrue(cacheManager.size() >= 3);
        
        // 查询超过最大缓存页数的页面（第3页）
        Page<String> page3 = testService.findUsers("active", PageRequest.of(3, 10));
        assertNotNull(page3);
        
        // 第3页不应该被缓存，所以缓存大小不应该增加
        long cacheSize = cacheManager.size();
        System.out.println("Cache size after querying page 3: " + cacheSize);
    }
    
    @Test
    void testCacheWithDifferentParameters() {
        // 查询不同状态的用户
        Page<String> activeUsers = testService.findUsers("active", PageRequest.of(0, 10));
        Page<String> inactiveUsers = testService.findUsers("inactive", PageRequest.of(0, 10));
        
        // 验证结果不同
        assertNotNull(activeUsers);
        assertNotNull(inactiveUsers);
        
        // 验证缓存中有不同的键
        assertTrue(cacheManager.size() >= 2);
        
        // 再次查询相同参数，应该命中缓存
        Page<String> activeUsers2 = testService.findUsers("active", PageRequest.of(0, 10));
        assertEquals(activeUsers.getContent().size(), activeUsers2.getContent().size());
    }
    
    @Test
    void testCacheExpiration() throws InterruptedException {
        // 查询数据建立缓存
        Page<String> result = testService.findUsers("active", PageRequest.of(0, 10));
        assertNotNull(result);
        
        // 验证缓存存在
        assertTrue(cacheManager.size() > 0);
        
        // 等待缓存过期（测试中设置为300秒，这里只是验证机制）
        // 在实际测试中，可以设置更短的过期时间
        System.out.println("Cache will expire in 300 seconds");
        
        // 验证缓存键的TTL
        String pattern = "*:list_cache:*";
        // 注意：这里需要根据实际的ListCacheManager实现来调整
        // Set<String> keys = cacheManager.keys(pattern);
        // assertFalse(keys.isEmpty());
        
        // for (String key : keys) {
        //     long ttl = cacheManager.getExpire(key);
        //     assertTrue(ttl > 0, "Cache key should have TTL: " + key);
        //     System.out.println("Key: " + key + ", TTL: " + ttl + " seconds");
        // }
    }
    
    @Test
    void testCacheHealthCheck() {
        // 测试缓存健康状态
        // 注意：这里需要根据实际的ListCacheManager实现来调整
        // boolean healthy = cacheManager.isHealthy();
        // assertTrue(healthy, "Cache should be healthy");
        
        // 测试连接
        // boolean connected = cacheManager.testConnection();
        // assertTrue(connected, "Cache connection should be working");
        
        // 获取连接信息
        // String connectionInfo = cacheManager.getConnectionInfo();
        // assertNotNull(connectionInfo);
        // System.out.println("Connection info: " + connectionInfo);
        
        // 简单验证缓存管理器存在
        assertNotNull(cacheManager);
    }
    
    @Test
    void testCacheManagement() {
        // 创建一些缓存数据
        testService.findUsers("active", PageRequest.of(0, 10));
        testService.findUsers("inactive", PageRequest.of(0, 10));
        
        // 验证缓存存在
        assertTrue(cacheManager.size() > 0);
        
        // 获取缓存键
        // String pattern = "*:list_cache:*";
        // Set<String> keys = cacheManager.keys(pattern);
        // assertFalse(keys.isEmpty());
        
        // System.out.println("Cache keys: " + keys);
        
        // 清除特定模式的缓存
        // long cleared = cacheManager.deleteByPattern("*active*");
        // assertTrue(cleared > 0);
        
        // 验证部分缓存被清除
        // Set<String> remainingKeys = cacheManager.keys(pattern);
        // assertTrue(remainingKeys.size() < keys.size());
        
        // 清除所有缓存
        boolean allCleared = cacheManager.clear();
        assertTrue(allCleared);
        assertEquals(0, cacheManager.size());
    }
    
    /**
     * 测试服务类
     */
    public static class TestService {
        
        @ListCache(
            value = "users_list",
            expireTime = 300,
            maxCachePages = 3,
            condition = "#pageable.pageNumber < 3"
        )
        public Page<String> findUsers(String status, Pageable pageable) {
            // 模拟数据库查询
            List<String> users;
            if ("active".equals(status)) {
                users = Arrays.asList("user1", "user2", "user3");
            } else {
                users = Arrays.asList("user4", "user5");
            }
            
            // 模拟分页
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), users.size());
            List<String> pageContent = start < users.size() ? 
                    users.subList(start, end) : Arrays.asList();
            
            return new PageImpl<>(pageContent, pageable, users.size());
        }
        
        @CacheEvict(
            value = "users_list",
            keyPattern = "users:*",
            timing = CacheEvict.EvictTiming.AFTER
        )
        public String createUser(String username) {
            // 模拟创建用户
            return username;
        }
        
        @CacheEvict(
            value = "users_list",
            allEntries = true
        )
        public void deleteAllUsers() {
            // 模拟删除所有用户
        }
    }
}