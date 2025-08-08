package com.tenant.test.controller;

import com.common.redis.cache.annotation.CacheConfig;
import com.common.redis.cache.annotation.CacheEvict;
import com.common.redis.cache.annotation.ListCache;
import com.common.redis.cache.manager.ListCacheManager;
import com.common.redis.cache.model.CacheStats;
import com.common.redis.cache.service.CacheMetrics;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.test.entity.TestCase;
import com.tenant.test.repository.TestCaseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Redis列表缓存测试控制器
 * 
 * <p>用于测试和演示Redis列表缓存功能的控制器。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/cache-test")
@Tag(name = "Redis列表缓存测试", description = "Redis列表缓存功能测试和演示接口")
@CacheConfig(
    cacheNames = "test_cases", 
    keyPrefix = "cache_test", 
    expireTime = 600,  // 10分钟
    maxCachePages = 5,
    tenantAware = true
)
public class RedisListCacheTestController {
    
    @Autowired
    private TestCaseRepository testCaseRepository;
    
    @Autowired
    private ListCacheManager cacheManager;
    
    @Autowired
    private CacheMetrics cacheMetrics;
    
    /**
     * 测试列表查询缓存（支持租户）
     */
    @GetMapping("/test-cases")
    @Operation(summary = "查询测试用例列表", description = "支持缓存的分页查询测试用例，前5页会被缓存，支持租户隔离")
    @ListCache(
        value = "test_cases_list",
        expireTime = 600,
        maxCachePages = 5,
        condition = "#pageable.pageNumber < 5",
        tenantAware = true
    )
    public ResponseEntity<Page<TestCase>> getTestCases(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId,
            @Parameter(description = "状态过滤") @RequestParam(required = false) String status,
            @Parameter(description = "优先级过滤") @RequestParam(required = false) String priority,
            @Parameter(description = "分页参数") @PageableDefault(size = 10) Pageable pageable) {
        
        Page<TestCase> result = getTestCasesInternal(tenantId, status, priority, pageable);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 查询指定租户的测试用例列表（不使用缓存）
     */
    @GetMapping("/test-cases/no-cache")
    @Operation(summary = "查询测试用例列表（不使用缓存）", description = "直接从数据库查询测试用例，支持租户隔离")
    public ResponseEntity<Page<TestCase>> getTestCasesNoCache(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId,
            @Parameter(description = "状态过滤") @RequestParam(required = false) String status,
            @Parameter(description = "优先级过滤") @RequestParam(required = false) String priority,
            @Parameter(description = "分页参数") @PageableDefault(size = 10) Pageable pageable) {
        
        Page<TestCase> result = getTestCasesInternal(tenantId, status, priority, pageable);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 内部方法：实际的数据查询逻辑（不使用缓存，支持租户）
     */
    public Page<TestCase> getTestCasesInternal(String tenantId, String status, String priority, Pageable pageable) {
        // 获取有效的租户ID
        String effectiveTenantId = getEffectiveTenantId(tenantId);
        
        log.info("Querying test cases for tenant: {}, status: {}, priority: {}, page: {}", 
                effectiveTenantId, status, priority, pageable.getPageNumber());
        
        Page<TestCase> result;
        
        if (effectiveTenantId != null) {
            // 使用租户相关的查询方法
            result = testCaseRepository.findByTenantIdWithFilters(effectiveTenantId, status, priority, pageable);
        } else {
            // 如果没有租户ID，查询所有数据（兼容性处理）
            log.warn("No tenant ID provided, querying all test cases");
            List<TestCase> allTestCases = testCaseRepository.findAll();
            
            // 简单过滤逻辑
            List<TestCase> filteredCases = new ArrayList<>();
            for (TestCase testCase : allTestCases) {
                boolean matches = true;
                
                if (status != null && !status.equals(testCase.getStatus())) {
                    matches = false;
                }
                
                if (priority != null && !priority.equals(testCase.getPriority())) {
                    matches = false;
                }
                
                if (matches) {
                    filteredCases.add(testCase);
                }
            }
            
            // 模拟分页
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), filteredCases.size());
            List<TestCase> pageContent = start < filteredCases.size() ? 
                    filteredCases.subList(start, end) : new ArrayList<>();
            
            result = new PageImpl<>(pageContent, pageable, filteredCases.size());
        }
        
        log.info("Query completed for tenant: {}, returned {} items from page {}", 
                effectiveTenantId, result.getContent().size(), pageable.getPageNumber());
        
        return result;
    }
    
    /**
     * 获取有效的租户ID
     */
    private String getEffectiveTenantId(String requestTenantId) {
        // 优先使用请求参数中的租户ID
        if (StringUtils.hasText(requestTenantId)) {
            return requestTenantId;
        }
        
        // 其次使用上下文中的租户ID
        String contextTenantId = TenantContextHolder.getTenantId();
        if (StringUtils.hasText(contextTenantId)) {
            return contextTenantId;
        }
        
        // 都没有则返回null
        return null;
    }
    
    /**
     * 创建测试用例（会清除缓存，支持租户）
     */
    @PostMapping("/test-cases")
    @Operation(summary = "创建测试用例", description = "创建新的测试用例，会自动清除相关缓存，支持租户隔离")
    @CacheEvict(
        value = "test_cases_list",
        keyPattern = "test_cases:*",
        timing = CacheEvict.EvictTiming.AFTER
    )
    public ResponseEntity<TestCase> createTestCase(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId,
            @RequestBody TestCase testCase) {
        
        // 获取有效的租户ID
        String effectiveTenantId = getEffectiveTenantId(tenantId);
        
        log.info("Creating test case: {} for tenant: {}", testCase.getName(), effectiveTenantId);
        
        // 设置租户ID
        testCase.setTenantId(effectiveTenantId);
        
        // 设置创建时间
        testCase.setCreateTime(new Date());
        testCase.setUpdateTime(new Date());
        
        TestCase saved = testCaseRepository.save(testCase);
        
        log.info("Test case created with ID: {} for tenant: {}", saved.getId(), effectiveTenantId);
        return ResponseEntity.ok(saved);
    }
    
    /**
     * 更新测试用例（会清除缓存）
     */
    @PutMapping("/test-cases/{id}")
    @Operation(summary = "更新测试用例", description = "更新测试用例信息，会自动清除相关缓存")
    @CacheEvict(
        value = "test_cases_list",
        keyPattern = "test_cases:*",
        condition = "#result != null"
    )
    public ResponseEntity<TestCase> updateTestCase(
            @Parameter(description = "测试用例ID") @PathVariable Long id,
            @RequestBody TestCase testCase) {
        
        log.info("Updating test case: {}", id);
        
        Optional<TestCase> existing = testCaseRepository.findById(id);
        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        TestCase existingCase = existing.get();
        existingCase.setName(testCase.getName());
        existingCase.setDescription(testCase.getDescription());
        existingCase.setStatus(testCase.getStatus());
        existingCase.setPriority(testCase.getPriority());
        existingCase.setUpdateTime(new Date());
        
        TestCase updated = testCaseRepository.save(existingCase);
        
        log.info("Test case updated: {}", updated.getId());
        return ResponseEntity.ok(updated);
    }
    
    /**
     * 删除测试用例（会清除缓存）
     */
    @DeleteMapping("/test-cases/{id}")
    @Operation(summary = "删除测试用例", description = "删除测试用例，会自动清除相关缓存")
    @CacheEvict(
        value = "test_cases_list",
        allEntries = true,
        timing = CacheEvict.EvictTiming.AFTER
    )
    public ResponseEntity<Void> deleteTestCase(
            @Parameter(description = "测试用例ID") @PathVariable Long id) {
        
        log.info("Deleting test case: {}", id);
        
        if (!testCaseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        testCaseRepository.deleteById(id);
        
        log.info("Test case deleted: {}", id);
        return ResponseEntity.ok().build();
    }
    
    /**
     * 批量创建测试用例（会清除缓存，支持租户）
     */
    @PostMapping("/test-cases/batch")
    @Operation(summary = "批量创建测试用例", description = "批量创建测试用例，会自动清除相关缓存，支持租户隔离")
    @CacheEvict(
        value = "test_cases_list",
        allEntries = true
    )
    public ResponseEntity<List<TestCase>> batchCreateTestCases(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId,
            @RequestBody List<TestCase> testCases) {
        
        // 获取有效的租户ID
        String effectiveTenantId = getEffectiveTenantId(tenantId);
        
        log.info("Batch creating {} test cases for tenant: {}", testCases.size(), effectiveTenantId);
        
        Date now = new Date();
        for (TestCase testCase : testCases) {
            testCase.setTenantId(effectiveTenantId);
            testCase.setCreateTime(now);
            testCase.setUpdateTime(now);
        }
        
        List<TestCase> saved = testCaseRepository.saveAll(testCases);
        
        log.info("Batch created {} test cases for tenant: {}", saved.size(), effectiveTenantId);
        return ResponseEntity.ok(saved);
    }
    
    /**
     * 获取缓存统计信息
     */
    @GetMapping("/cache/stats")
    @Operation(summary = "获取缓存统计信息", description = "获取当前缓存的统计信息和性能指标")
    public ResponseEntity<Map<String, Object>> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 全局统计
        CacheStats globalStats = cacheManager.getStats();
        stats.put("global", globalStats.getSnapshot());
        
        // 监控指标
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("summary", cacheMetrics.getMetricsSummary());
        metrics.put("slowQueries", cacheMetrics.getSlowQueryCount());
        metrics.put("errors", cacheMetrics.getErrorCount());
        metrics.put("fallbacks", cacheMetrics.getFallbackCount());
        stats.put("metrics", metrics);
        
        // 缓存健康状态
        Map<String, Object> health = new HashMap<>();
        health.put("healthy", cacheManager.isHealthy());
        health.put("connectionInfo", cacheManager.getConnectionInfo());
        health.put("memoryInfo", cacheManager.getMemoryInfo());
        stats.put("health", health);
        
        return ResponseEntity.ok(stats);
    }
    
    /**
     * 手动清除缓存
     */
    @DeleteMapping("/cache/clear")
    @Operation(summary = "清除缓存", description = "手动清除指定模式的缓存")
    public ResponseEntity<Map<String, Object>> clearCache(
            @Parameter(description = "缓存键模式，支持通配符") @RequestParam(required = false) String pattern) {
        
        Map<String, Object> result = new HashMap<>();
        
        if (pattern != null && !pattern.trim().isEmpty()) {
            long cleared = cacheManager.deleteByPattern(pattern);
            result.put("message", "Pattern cache cleared");
            result.put("pattern", pattern);
            result.put("clearedCount", cleared);
            
            log.info("Cache cleared by pattern: {}, count: {}", pattern, cleared);
        } else {
            boolean cleared = cacheManager.clear();
            result.put("message", cleared ? "All cache cleared" : "Cache clear failed");
            result.put("success", cleared);
            
            log.info("All cache cleared: {}", cleared);
        }
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 重置缓存统计
     */
    @PostMapping("/cache/reset-stats")
    @Operation(summary = "重置缓存统计", description = "重置所有缓存统计信息")
    public ResponseEntity<Map<String, Object>> resetCacheStats() {
        cacheManager.resetStats();
        cacheMetrics.resetAllStats();
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Cache statistics reset successfully");
        result.put("timestamp", new Date());
        
        log.info("Cache statistics reset");
        return ResponseEntity.ok(result);
    }
    
    /**
     * 测试缓存连接
     */
    @GetMapping("/cache/test-connection")
    @Operation(summary = "测试缓存连接", description = "测试Redis连接状态")
    public ResponseEntity<Map<String, Object>> testCacheConnection() {
        boolean healthy = cacheManager.testConnection();
        
        Map<String, Object> result = new HashMap<>();
        result.put("healthy", healthy);
        result.put("message", healthy ? "Cache connection is healthy" : "Cache connection failed");
        result.put("connectionInfo", cacheManager.getConnectionInfo());
        result.put("timestamp", new Date());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取缓存键信息
     */
    @GetMapping("/cache/keys")
    @Operation(summary = "获取缓存键信息", description = "获取匹配模式的缓存键列表")
    public ResponseEntity<Map<String, Object>> getCacheKeys(
            @Parameter(description = "键模式，支持通配符") @RequestParam(defaultValue = "*") String pattern,
            @Parameter(description = "最大返回数量") @RequestParam(defaultValue = "100") int limit) {
        
        Set<String> keys = cacheManager.keys(pattern, limit);
        
        Map<String, Object> result = new HashMap<>();
        result.put("pattern", pattern);
        result.put("limit", limit);
        result.put("count", keys.size());
        result.put("keys", keys);
        result.put("timestamp", new Date());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 预热缓存（支持租户）
     */
    @PostMapping("/cache/warmup")
    @Operation(summary = "预热缓存", description = "预热指定的缓存数据，支持租户隔离")
    public ResponseEntity<Map<String, Object>> warmupCache(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId,
            @Parameter(description = "要预热的页数") @RequestParam(defaultValue = "3") int pages) {
        
        String effectiveTenantId = getEffectiveTenantId(tenantId);
        log.info("Starting cache warmup for {} pages, tenant: {}", pages, effectiveTenantId);
        
        int warmedPages = 0;
        for (int page = 0; page < pages; page++) {
            try {
                // 触发缓存加载
                getTestCasesInternal(effectiveTenantId, null, null, 
                    org.springframework.data.domain.PageRequest.of(page, 10));
                warmedPages++;
                
                // 避免过快请求
                Thread.sleep(100);
            } catch (Exception e) {
                log.warn("Failed to warm up page {} for tenant {}: {}", page, effectiveTenantId, e.getMessage());
                break;
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Cache warmup completed");
        result.put("tenantId", effectiveTenantId);
        result.put("requestedPages", pages);
        result.put("warmedPages", warmedPages);
        result.put("timestamp", new Date());
        
        log.info("Cache warmup completed for tenant {}: {}/{} pages", effectiveTenantId, warmedPages, pages);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取租户统计信息
     */
    @GetMapping("/tenant/stats")
    @Operation(summary = "获取租户统计信息", description = "获取指定租户的测试用例统计信息")
    public ResponseEntity<Map<String, Object>> getTenantStats(
            @Parameter(description = "租户ID（可选，不传则使用当前上下文租户）") @RequestParam(required = false) String tenantId) {
        
        String effectiveTenantId = getEffectiveTenantId(tenantId);
        
        if (effectiveTenantId == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "No tenant ID provided");
            return ResponseEntity.badRequest().body(error);
        }
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("tenantId", effectiveTenantId);
        stats.put("totalCount", testCaseRepository.countByTenantId(effectiveTenantId));
        
        // 按状态统计
        Map<String, Long> statusStats = new HashMap<>();
        statusStats.put("active", testCaseRepository.countByTenantIdAndStatus(effectiveTenantId, "active"));
        statusStats.put("inactive", testCaseRepository.countByTenantIdAndStatus(effectiveTenantId, "inactive"));
        statusStats.put("pending", testCaseRepository.countByTenantIdAndStatus(effectiveTenantId, "pending"));
        stats.put("statusStats", statusStats);
        
        stats.put("timestamp", new Date());
        
        return ResponseEntity.ok(stats);
    }
    
    /**
     * 设置当前租户上下文
     */
    @PostMapping("/tenant/context")
    @Operation(summary = "设置租户上下文", description = "设置当前线程的租户上下文")
    public ResponseEntity<Map<String, Object>> setTenantContext(
            @Parameter(description = "租户ID") @RequestParam String tenantId) {
        
        TenantContextHolder.setTenantId(tenantId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Tenant context set successfully");
        result.put("tenantId", tenantId);
        result.put("stackSize", TenantContextHolder.getStackSize());
        result.put("timestamp", new Date());
        
        log.info("Tenant context set to: {}", tenantId);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取当前租户上下文
     */
    @GetMapping("/tenant/context")
    @Operation(summary = "获取租户上下文", description = "获取当前线程的租户上下文信息")
    public ResponseEntity<Map<String, Object>> getTenantContext() {
        String currentTenantId = TenantContextHolder.getTenantId();
        
        Map<String, Object> result = new HashMap<>();
        result.put("tenantId", currentTenantId);
        result.put("hasTenant", TenantContextHolder.hasTenant());
        result.put("stackSize", TenantContextHolder.getStackSize());
        result.put("timestamp", new Date());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 清除租户上下文
     */
    @DeleteMapping("/tenant/context")
    @Operation(summary = "清除租户上下文", description = "清除当前线程的租户上下文")
    public ResponseEntity<Map<String, Object>> clearTenantContext() {
        String previousTenantId = TenantContextHolder.getTenantId();
        TenantContextHolder.clear();
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Tenant context cleared successfully");
        result.put("previousTenantId", previousTenantId);
        result.put("timestamp", new Date());
        
        log.info("Tenant context cleared, previous tenant: {}", previousTenantId);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 清除指定租户的缓存
     */
    @DeleteMapping("/cache/tenant/{tenantId}")
    @Operation(summary = "清除租户缓存", description = "清除指定租户的所有缓存数据")
    public ResponseEntity<Map<String, Object>> clearTenantCache(
            @Parameter(description = "租户ID") @PathVariable String tenantId) {
        
        String pattern = "*tenant:" + tenantId + "*";
        long cleared = cacheManager.deleteByPattern(pattern);
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Tenant cache cleared");
        result.put("tenantId", tenantId);
        result.put("pattern", pattern);
        result.put("clearedCount", cleared);
        result.put("timestamp", new Date());
        
        log.info("Cache cleared for tenant: {}, count: {}", tenantId, cleared);
        return ResponseEntity.ok(result);
    }
}