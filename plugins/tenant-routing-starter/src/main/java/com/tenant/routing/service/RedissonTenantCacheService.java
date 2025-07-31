package com.tenant.routing.service;

import com.tenant.routing.entity.TenantDbInfo;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redisson租户缓存服务
 * 针对4000+租户场景优化，支持租户预加载
 * 使用Redisson直接操作Redis，支持Redis集群
 */
public class RedissonTenantCacheService {
    
    private static final Logger logger = LoggerFactory.getLogger(RedissonTenantCacheService.class);
    
    private static final String TENANT_CACHE_KEY_PREFIX = "tenant:db:";
    private static final String TENANT_IDS_KEY = "tenant:all:ids";
    private static final String TENANT_LOADED_FLAG = "tenant:preload:completed";
    
    @Autowired
    private RedissonClient redissonClient;
    
    // 使用ApplicationContext延迟加载TenantDbInfoService，避免循环依赖
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private com.tenant.routing.config.TenantRoutingProperties properties;
    
    // 延迟获取TenantDbInfoService
    private TenantDbInfoService getTenantDbInfoService() {
        return applicationContext.getBean(TenantDbInfoService.class);
    }
    
    // 移除未使用的ObjectMapper
    
    // 从配置中获取缓存过期时间和批量操作大小
    private long getCacheExpireTime() {
        return properties.getCacheExpireTime();
    }
    
    private int getBatchSize() {
        return properties.getBatchSize();
    }
    
    /**
     * 应用启动时预加载租户信息
     */
    @PostConstruct
    public void preloadTenants() {
        // 检查是否已经预加载过
        RBucket<String> loadedFlag = redissonClient.getBucket(TENANT_LOADED_FLAG);
        if (loadedFlag.isExists()) {
            logger.info("租户信息已预加载，跳过预加载过程");
            return;
        }
        
        logger.info("开始预加载租户信息到Redis缓存...");
        CompletableFuture.runAsync(this::loadAllTenantsToCache);
    }
    
    /**
     * 加载所有租户信息到缓存
     */
    private void loadAllTenantsToCache() {
        try {
            List<TenantDbInfo> allTenants = getTenantDbInfoService().findAll();
            if (allTenants.isEmpty()) {
                logger.warn("未找到租户信息，预加载跳过");
                return;
            }
            
            logger.info("开始预加载 {} 个租户信息到Redis", allTenants.size());
            
            // 分批处理，避免一次性处理过多数据
            int totalTenants = allTenants.size();
            for (int i = 0; i < totalTenants; i += getBatchSize()) {
                int endIndex = Math.min(i + getBatchSize(), totalTenants);
                List<TenantDbInfo> batch = allTenants.subList(i, endIndex);
                cacheTenantsBatch(batch);
                logger.debug("已预加载 {}/{} 个租户信息", endIndex, totalTenants);
            }
            
            // 设置预加载完成标志，一周内不再重复预加载
            RBucket<String> loadedFlag = redissonClient.getBucket(TENANT_LOADED_FLAG);
            loadedFlag.set("true", getCacheExpireTime(), TimeUnit.SECONDS);
            
            logger.info("租户信息预加载完成，共 {} 个租户", totalTenants);
        } catch (Exception e) {
            logger.error("租户信息预加载失败", e);
        }
    }
    
    /**
     * 缓存单个租户信息
     */
    public void cacheTenant(TenantDbInfo tenantDbInfo) {
        if (tenantDbInfo == null) {
            return;
        }
        
        try {
            String key = TENANT_CACHE_KEY_PREFIX + tenantDbInfo.getTenantId();
            RBucket<TenantDbInfo> bucket = redissonClient.getBucket(key);
            bucket.set(tenantDbInfo, getCacheExpireTime(), TimeUnit.SECONDS);
            
            RSet<String> tenantIdsSet = redissonClient.getSet(TENANT_IDS_KEY);
            tenantIdsSet.add(tenantDbInfo.getTenantId());
            
            logger.debug("已缓存租户信息: {}", tenantDbInfo.getTenantId());
        } catch (Exception e) {
            logger.error("缓存租户信息失败: " + tenantDbInfo.getTenantId(), e);
        }
    }
    
    /**
     * 批量缓存租户信息
     */
    public void cacheTenants(List<TenantDbInfo> tenantDbInfos) {
        if (tenantDbInfos == null || tenantDbInfos.isEmpty()) {
            return;
        }
        
        // 对于大量租户，分批处理
        int totalTenants = tenantDbInfos.size();
        for (int i = 0; i < totalTenants; i += getBatchSize()) {
            int endIndex = Math.min(i + getBatchSize(), totalTenants);
            List<TenantDbInfo> batch = tenantDbInfos.subList(i, endIndex);
            cacheTenantsBatch(batch);
        }
        
        logger.info("已批量缓存 {} 个租户信息", tenantDbInfos.size());
    }
    
    /**
     * 批量缓存租户信息（内部方法）
     */
    private void cacheTenantsBatch(List<TenantDbInfo> tenantDbInfos) {
        if (tenantDbInfos == null || tenantDbInfos.isEmpty()) {
            return;
        }
        
        try {
            for (TenantDbInfo tenantDbInfo : tenantDbInfos) {
                cacheTenant(tenantDbInfo);
            }
        } catch (Exception e) {
            logger.error("批量缓存租户信息失败", e);
        }
    }
    
    /**
     * 从缓存获取租户信息
     */
    public TenantDbInfo getTenant(String tenantId) {
        if (tenantId == null || tenantId.isEmpty()) {
            return null;
        }
        
        try {
            final String key = TENANT_CACHE_KEY_PREFIX + tenantId;
            RBucket<TenantDbInfo> bucket = redissonClient.getBucket(key);
            TenantDbInfo tenantDbInfo = bucket.get();
            
            // 异步刷新过期时间，避免阻塞主流程
            if (tenantDbInfo != null) {
                CompletableFuture.runAsync(() -> {
                    try {
                        bucket.expire(Duration.ofSeconds(getCacheExpireTime()));
                    } catch (Exception e) {
                        logger.error("刷新租户缓存过期时间失败: " + tenantId, e);
                    }
                });
            }
            
            return tenantDbInfo;
        } catch (Exception e) {
            logger.error("获取租户信息失败: " + tenantId, e);
            return null;
        }
    }
    
    /**
     * 从缓存删除租户信息
     */
    public void removeTenant(String tenantId) {
        if (tenantId == null || tenantId.isEmpty()) {
            return;
        }
        
        try {
            String key = TENANT_CACHE_KEY_PREFIX + tenantId;
            redissonClient.getBucket(key).delete();
            redissonClient.getSet(TENANT_IDS_KEY).remove(tenantId);
            logger.debug("已从缓存移除租户: {}", tenantId);
        } catch (Exception e) {
            logger.error("移除租户缓存失败: " + tenantId, e);
        }
    }
    
    /**
     * 获取所有租户ID
     */
    public Set<String> getAllTenantIds() {
        try {
            RSet<String> tenantIdsSet = redissonClient.getSet(TENANT_IDS_KEY);
            return new HashSet<>(tenantIdsSet.readAll());
        } catch (Exception e) {
            logger.error("获取所有租户ID失败", e);
            return new HashSet<>();
        }
    }
    
    /**
     * 清除所有租户缓存
     */
    public void clearAllTenants() {
        Set<String> tenantIds = getAllTenantIds();
        if (tenantIds != null && !tenantIds.isEmpty()) {
            // 分批删除，避免一次性删除过多键
            List<String> allKeys = tenantIds.stream()
                    .map(id -> TENANT_CACHE_KEY_PREFIX + id)
                    .collect(Collectors.toList());
            
            int totalKeys = allKeys.size();
            for (int i = 0; i < totalKeys; i += getBatchSize()) {
                int endIndex = Math.min(i + getBatchSize(), totalKeys);
                List<String> batchKeys = allKeys.subList(i, endIndex);
                
                for (String key : batchKeys) {
                    redissonClient.getBucket(key).delete();
                }
            }
            
            redissonClient.getBucket(TENANT_IDS_KEY).delete();
            redissonClient.getBucket(TENANT_LOADED_FLAG).delete();
            logger.info("已清除所有租户缓存，共 {} 个", tenantIds.size());
        }
    }
    
    /**
     * 定时刷新租户缓存（每天凌晨2点执行）
     * 避免缓存过期导致的性能问题
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void refreshTenantCache() {
        logger.info("开始定时刷新租户缓存...");
        
        try {
            // 获取数据库中的所有租户
            List<TenantDbInfo> dbTenants = getTenantDbInfoService().findAll();
            Set<String> dbTenantIds = dbTenants.stream()
                    .map(TenantDbInfo::getTenantId)
                    .collect(Collectors.toSet());
            
            // 获取缓存中的所有租户ID
            Set<String> cachedTenantIds = getAllTenantIds();
            
            // 需要新增的租户
            final Set<String> finalCachedTenantIds = cachedTenantIds != null ? cachedTenantIds : Collections.emptySet();
            
            List<TenantDbInfo> tenantsToAdd = dbTenants.stream()
                    .filter(tenant -> !finalCachedTenantIds.contains(tenant.getTenantId()))
                    .collect(Collectors.toList());
            
            // 需要删除的租户
            List<String> tenantsToRemove = new ArrayList<>();
            if (finalCachedTenantIds != null && !finalCachedTenantIds.isEmpty()) {
                tenantsToRemove = finalCachedTenantIds.stream()
                        .filter(id -> !dbTenantIds.contains(id))
                        .collect(Collectors.toList());
            }
            
            // 批量添加新租户
            if (!tenantsToAdd.isEmpty()) {
                cacheTenants(tenantsToAdd);
                logger.info("已添加 {} 个新租户到缓存", tenantsToAdd.size());
            }
            
            // 批量删除过期租户
            for (String tenantId : tenantsToRemove) {
                removeTenant(tenantId);
            }
            if (!tenantsToRemove.isEmpty()) {
                logger.info("已从缓存中删除 {} 个过期租户", tenantsToRemove.size());
            }
            
            // 刷新所有租户的过期时间
            refreshAllTenantsExpiration();
            
            logger.info("租户缓存刷新完成，当前共有 {} 个租户", dbTenants.size());
        } catch (Exception e) {
            logger.error("租户缓存刷新失败", e);
        }
    }
    
    /**
     * 刷新所有租户的过期时间
     */
    private void refreshAllTenantsExpiration() {
        Set<String> tenantIds = getAllTenantIds();
        if (tenantIds == null || tenantIds.isEmpty()) {
            return;
        }
        
        try {
            // 分批处理
            List<String> allKeys = tenantIds.stream()
                    .map(id -> TENANT_CACHE_KEY_PREFIX + id)
                    .collect(Collectors.toList());
            
            int totalKeys = allKeys.size();
            for (int i = 0; i < totalKeys; i += getBatchSize()) {
                int endIndex = Math.min(i + getBatchSize(), totalKeys);
                List<String> batchKeys = allKeys.subList(i, endIndex);
                
                for (String key : batchKeys) {
                    RBucket<TenantDbInfo> bucket = redissonClient.getBucket(key);
                    if (bucket.isExists()) {
                        bucket.expire(Duration.ofSeconds(getCacheExpireTime()));
                    }
                }
            }
            
            // 刷新租户ID集合的过期时间
            redissonClient.getBucket(TENANT_IDS_KEY).expire(Duration.ofSeconds(getCacheExpireTime()));
            redissonClient.getBucket(TENANT_LOADED_FLAG).expire(Duration.ofSeconds(getCacheExpireTime()));
            
            logger.debug("已刷新 {} 个租户的过期时间", totalKeys);
        } catch (Exception e) {
            logger.error("批量刷新租户过期时间失败", e);
        }
    }

    /**
     * 刷新单个租户缓存（用于租户信息变更时）
     */
    public void refreshTenantCache(String tenantId) {
        TenantDbInfo tenantDbInfo = getTenantDbInfoService().findByTenantId(tenantId);
        if (tenantDbInfo != null) {
            cacheTenant(tenantDbInfo);
            logger.info("已刷新租户缓存: {}", tenantId);
        } else {
            // 租户已删除，移除缓存
            removeTenant(tenantId);
            logger.info("已移除租户缓存: {}", tenantId);
        }
    }

    /**
     * 刷新所有租户缓存（用于批量变更或手动触发）
     */
    public void refreshAllTenantCache() {
        List<TenantDbInfo> allTenants = getTenantDbInfoService().findAll();
        if (allTenants.isEmpty()) {
            logger.warn("无租户信息可刷新");
            return;
        }
        
        logger.info("开始刷新所有租户缓存，共 {} 个租户", allTenants.size());
        
        // 先清空所有缓存
        clearAllTenants();
        
        // 重新批量缓存
        cacheTenants(allTenants);
        
        // 设置预加载完成标志
        RBucket<String> loadedFlag = redissonClient.getBucket(TENANT_LOADED_FLAG);
        loadedFlag.set("true", getCacheExpireTime(), TimeUnit.SECONDS);
        
        logger.info("已完成所有租户缓存刷新, 共 {} 个租户", allTenants.size());
    }
}