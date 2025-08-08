package com.tenant.test.controller;

import com.tenant.routing.core.TenantContextHolder;
import com.tenant.test.entity.TestCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 租户缓存测试控制器
 * 
 * <p>用于演示和测试租户相关的缓存功能。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/tenant-cache-demo")
@Tag(name = "租户缓存测试", description = "租户缓存功能演示和测试接口")
public class TenantCacheTestController {
    
    @Autowired
    private RedisListCacheTestController cacheTestController;
    
    /**
     * 演示租户缓存功能的完整流程
     */
    @PostMapping("/demo")
    @Operation(summary = "租户缓存演示", description = "演示租户缓存功能的完整流程")
    public ResponseEntity<Map<String, Object>> tenantCacheDemo(
            @Parameter(description = "租户ID") @RequestParam String tenantId) {
        
        Map<String, Object> result = new HashMap<>();
        List<String> steps = new ArrayList<>();
        
        try {
            // 步骤1：设置租户上下文
            steps.add("1. 设置租户上下文: " + tenantId);
            TenantContextHolder.setTenantId(tenantId);
            
            // 步骤2：创建测试数据
            steps.add("2. 创建测试数据");
            List<TestCase> testCases = createSampleTestCases(tenantId);
            cacheTestController.batchCreateTestCases(null, testCases);
            
            // 步骤3：第一次查询（会缓存）
            steps.add("3. 第一次查询（会缓存）");
            long startTime = System.currentTimeMillis();
            ResponseEntity<Page<TestCase>> firstQueryResponse = cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
            Page<TestCase> firstQuery = firstQueryResponse.getBody();
            long firstQueryTime = System.currentTimeMillis() - startTime;
            
            // 步骤4：第二次查询（从缓存读取）
            steps.add("4. 第二次查询（从缓存读取）");
            startTime = System.currentTimeMillis();
            ResponseEntity<Page<TestCase>> secondQueryResponse = cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
            Page<TestCase> secondQuery = secondQueryResponse.getBody();
            long secondQueryTime = System.currentTimeMillis() - startTime;
            
            // 步骤5：获取缓存统计
            steps.add("5. 获取缓存统计");
            ResponseEntity<Map<String, Object>> cacheStats = cacheTestController.getCacheStats();
            
            // 步骤6：获取租户统计
            steps.add("6. 获取租户统计");
            ResponseEntity<Map<String, Object>> tenantStats = cacheTestController.getTenantStats(null);
            
            // 步骤7：清除租户缓存
            steps.add("7. 清除租户缓存");
            cacheTestController.clearTenantCache(tenantId);
            
            // 步骤8：第三次查询（缓存已清除，重新查询数据库）
            steps.add("8. 第三次查询（缓存已清除）");
            startTime = System.currentTimeMillis();
            ResponseEntity<Page<TestCase>> thirdQueryResponse = cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
            Page<TestCase> thirdQuery = thirdQueryResponse.getBody();
            long thirdQueryTime = System.currentTimeMillis() - startTime;
            
            // 组装结果
            result.put("success", true);
            result.put("tenantId", tenantId);
            result.put("steps", steps);
            Map<String, String> queryTimes = new HashMap<>();
            queryTimes.put("firstQuery", firstQueryTime + "ms");
            queryTimes.put("secondQuery", secondQueryTime + "ms");
            queryTimes.put("thirdQuery", thirdQueryTime + "ms");
            result.put("queryTimes", queryTimes);
            result.put("dataCount", firstQuery != null ? firstQuery.getTotalElements() : 0);
            result.put("cacheStats", cacheStats.getBody());
            result.put("tenantStats", tenantStats.getBody());
            result.put("timestamp", new Date());
            
            log.info("Tenant cache demo completed for tenant: {}", tenantId);
            
        } catch (Exception e) {
            log.error("Error in tenant cache demo for tenant: {}", tenantId, e);
            result.put("success", false);
            result.put("error", e.getMessage());
            result.put("steps", steps);
        } finally {
            // 清除租户上下文
            TenantContextHolder.clear();
        }
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 多租户并发测试
     */
    @PostMapping("/multi-tenant-test")
    @Operation(summary = "多租户并发测试", description = "测试多个租户的缓存隔离性")
    public ResponseEntity<Map<String, Object>> multiTenantTest(
            @Parameter(description = "租户ID列表") @RequestParam List<String> tenantIds) {
        
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> tenantResults = new HashMap<>();
        
        for (String tenantId : tenantIds) {
            try {
                // 设置租户上下文
                TenantContextHolder.setTenantId(tenantId);
                
                // 创建租户特定的测试数据
                List<TestCase> testCases = createSampleTestCases(tenantId);
                cacheTestController.batchCreateTestCases(null, testCases);
                
                // 查询数据
                ResponseEntity<Page<TestCase>> queryResponse = cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
                Page<TestCase> queryResult = queryResponse.getBody();
                
                // 获取租户统计
                ResponseEntity<Map<String, Object>> tenantStats = cacheTestController.getTenantStats(null);
                
                Map<String, Object> tenantResult = new HashMap<>();
                tenantResult.put("dataCount", queryResult != null ? queryResult.getTotalElements() : 0);
                tenantResult.put("stats", tenantStats.getBody());
                tenantResult.put("success", true);
                
                tenantResults.put(tenantId, tenantResult);
                
            } catch (Exception e) {
                log.error("Error testing tenant: {}", tenantId, e);
                Map<String, Object> tenantResult = new HashMap<>();
                tenantResult.put("success", false);
                tenantResult.put("error", e.getMessage());
                tenantResults.put(tenantId, tenantResult);
            } finally {
                TenantContextHolder.clear();
            }
        }
        
        result.put("success", true);
        result.put("testedTenants", tenantIds.size());
        result.put("results", tenantResults);
        result.put("timestamp", new Date());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 缓存性能测试
     */
    @PostMapping("/performance-test")
    @Operation(summary = "缓存性能测试", description = "测试缓存的性能提升效果")
    public ResponseEntity<Map<String, Object>> performanceTest(
            @Parameter(description = "租户ID") @RequestParam String tenantId,
            @Parameter(description = "测试次数") @RequestParam(defaultValue = "10") int iterations) {
        
        Map<String, Object> result = new HashMap<>();
        List<Long> cachedTimes = new ArrayList<>();
        List<Long> noCacheTimes = new ArrayList<>();
        
        try {
            // 设置租户上下文
            TenantContextHolder.setTenantId(tenantId);
            
            // 创建测试数据
            List<TestCase> testCases = createSampleTestCases(tenantId);
            cacheTestController.batchCreateTestCases(null, testCases);
            
            // 预热缓存
            cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
            
            // 测试缓存查询性能
            for (int i = 0; i < iterations; i++) {
                long startTime = System.currentTimeMillis();
                cacheTestController.getTestCases(null, null, null, PageRequest.of(0, 10));
                long endTime = System.currentTimeMillis();
                cachedTimes.add(endTime - startTime);
                
                Thread.sleep(10); // 避免过快请求
            }
            
            // 测试无缓存查询性能
            for (int i = 0; i < iterations; i++) {
                long startTime = System.currentTimeMillis();
                cacheTestController.getTestCasesNoCache(null, null, null, PageRequest.of(0, 10));
                long endTime = System.currentTimeMillis();
                noCacheTimes.add(endTime - startTime);
                
                Thread.sleep(10); // 避免过快请求
            }
            
            // 计算统计信息
            double avgCachedTime = cachedTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
            double avgNoCacheTime = noCacheTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
            double improvement = avgNoCacheTime > 0 ? ((avgNoCacheTime - avgCachedTime) / avgNoCacheTime) * 100 : 0;
            
            result.put("success", true);
            result.put("tenantId", tenantId);
            result.put("iterations", iterations);
            result.put("avgCachedTime", String.format("%.2f ms", avgCachedTime));
            result.put("avgNoCacheTime", String.format("%.2f ms", avgNoCacheTime));
            result.put("performanceImprovement", String.format("%.2f%%", improvement));
            result.put("cachedTimes", cachedTimes);
            result.put("noCacheTimes", noCacheTimes);
            result.put("timestamp", new Date());
            
        } catch (Exception e) {
            log.error("Error in performance test for tenant: {}", tenantId, e);
            result.put("success", false);
            result.put("error", e.getMessage());
        } finally {
            TenantContextHolder.clear();
        }
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 创建示例测试用例
     */
    private List<TestCase> createSampleTestCases(String tenantId) {
        List<TestCase> testCases = new ArrayList<>();
        String[] statuses = {"active", "inactive", "pending"};
        String[] priorities = {"high", "medium", "low"};
        
        for (int i = 1; i <= 20; i++) {
            TestCase testCase = new TestCase();
            testCase.setName("测试用例-" + tenantId + "-" + i);
            testCase.setDescription("这是租户 " + tenantId + " 的第 " + i + " 个测试用例");
            testCase.setStatus(statuses[i % statuses.length]);
            testCase.setPriority(priorities[i % priorities.length]);
            testCase.setTenantId(tenantId);
            testCase.setCreateTime(new Date());
            testCase.setUpdateTime(new Date());
            testCases.add(testCase);
        }
        
        return testCases;
    }
}