package com.tenant.routing.service;

import com.tenant.routing.entity.TenantDbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis租户缓存服务
 * 针对4000+租户场景优化，支持租户预加载
 */
public class RedisTenantCacheService {
    
    private static final Logger logger = LoggerFactory.getLogger(RedisTenantCacheService.class);
    
    private static final String TENANT_CACHE_KEY_PREFIX = "tenant:db:";
    private static final String TENANT_IDS_KEY = "tenant:all:ids";
    private static final String TENANT_LOADED_FLAG = "tenant:preload:completed";
    private static final long CACHE_EXPIRE_TIME = 7 * 24 * 60 * 60; // 7天，减少频繁刷新
    private static final int BATCH_SIZE = 100; // 批量操作大小
    
    @Autowired
    private RedisTemplate<String, TenantDbInfo> tenantRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    
    @Autowired
    private TenantDbInfoService tenantDbInfoService;
    
    /**
     * 应用启动时预加载租户信息
     */
    @PostConstruct
    public void preloadTenants() {
        // 检查是否已经预加载过
        Boolean isLoaded = stringRedisTemplate.hasKey(TENANT_LOADED_FLAG);
        if (Boolean.TRUE.equals(isLoaded)) {
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
            List<TenantDbInfo> allTenants = tenantDbInfoService.findAll();
            if (allTenants.isEmpty()) {
                logger.warn("未找到租户信息，预加载跳过");
                return;
            }
            
            logger.info("开始预加载 {} 个租户信息到Redis", allTenants.size());
            
            // 分批处理，避免一次性处理过多数据
            int totalTenants = allTenants.size();
            for (int i = 0; i < totalTenants; i += BATCH_SIZE) {
                int endIndex = Math.min(i + BATCH_SIZE, totalTenants);
                List<TenantDbInfo> batch = allTenants.subList(i, endIndex);
                cacheTenantsBatch(batch);
                logger.debug("已预加载 {}/{} 个租户信息", endIndex, totalTenants);
            }
            
            // 设置预加载完成标志，一周内不再重复预加载
            stringRedisTemplate.opsForValue().set(TENANT_LOADED_FLAG, "true", CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
            logger.info("租户信息预加载完成，共 {} 个租户", totalTenants);
        } catch (Exception e) {
            logger.error("租户信息预加载失败", e);
        }
    }
    
    /**
     * 缓存单个租户信息
     */
    public void cacheTenant(TenantDbInfo tenantDbInfo) {
        String key = TENANT_CACHE_KEY_PREFIX + tenantDbInfo.getTenantId();
        tenantRedisTemplate.opsForValue().set(key, tenantDbInfo, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
        stringRedisTemplate.opsForSet().add(TENANT_IDS_KEY, tenantDbInfo.getTenantId());
        logger.debug("已缓存租户信息: {}", tenantDbInfo.getTenantId());
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
        for (int i = 0; i < totalTenants; i += BATCH_SIZE) {
            int endIndex = Math.min(i + BATCH_SIZE, totalTenants);
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
        
        // 使用管道批量操作提高性能
        tenantRedisTemplate.executePipelined((connection) -> {
            for (TenantDbInfo tenantDbInfo : tenantDbInfos) {
                String key = TENANT_CACHE_KEY_PREFIX + tenantDbInfo.getTenantId();
                tenantRedisTemplate.opsForValue().set(key, tenantDbInfo, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
                stringRedisTemplate.opsForSet().add(TENANT_IDS_KEY, tenantDbInfo.getTenantId());
            }
            return null;
        });
    }
    
    /**
     * 从缓存获取租户信息
     */
    public TenantDbInfo getTenant(String tenantId) {
        String key = TENANT_CACHE_KEY_PREFIX + tenantId;
        TenantDbInfo tenantDbInfo = tenantRedisTemplate.opsForValue().get(key);
        
        // 异步刷新过期时间，避免阻塞主流程
        if (tenantDbInfo != null) {
            CompletableFuture.runAsync(() -> {
                tenantRedisTemplate.expire(key, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
            });
        }
        
        return tenantDbInfo;
    }
    
    /**
     * 从缓存删除租户信息
     */
    public void removeTenant(String tenantId) {
        String key = TENANT_CACHE_KEY_PREFIX + tenantId;
        tenantRedisTemplate.delete(key);
        stringRedisTemplate.opsForSet().remove(TENANT_IDS_KEY, tenantId);
        logger.debug("已从缓存移除租户: {}", tenantId);
    }
    
    /**
     * 获取所有租户ID
     */
    public Set<String> getAllTenantIds() {
        return stringRedisTemplate.opsForSet().members(TENANT_IDS_KEY);
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
            for (int i = 0; i < totalKeys; i += BATCH_SIZE) {
                int endIndex = Math.min(i + BATCH_SIZE, totalKeys);
                List<String> batchKeys = allKeys.subList(i, endIndex);
                tenantRedisTemplate.delete(batchKeys);
            }
            
            stringRedisTemplate.delete(TENANT_IDS_KEY);
            stringRedisTemplate.delete(TENANT_LOADED_FLAG);
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
            List<TenantDbInfo> dbTenants = tenantDbInfoService.findAll();
            Set<String> dbTenantIds = dbTenants.stream()
                    .map(TenantDbInfo::getTenantId)
                    .collect(Collectors.toSet());
            
            // 获取缓存中的所有租户ID
            Set<String> cachedTenantIds = getAllTenantIds();
            
            // 需要新增的租户
            if (cachedTenantIds == null) {
                cachedTenantIds = Collections.emptySet();
            }
            
            List<TenantDbInfo> tenantsToAdd = dbTenants.stream()
                    .filter(tenant -> !cachedTenantIds.contains(tenant.getTenantId()))
                    .collect(Collectors.toList());
            
            // 需要删除的租户
            List<String> tenantsToRemove = new ArrayList<>();
            if (cachedTenantIds != null) {
                tenantsToRemove = cachedTenantIds.stream()
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
        
        // 使用Lua脚本批量刷新过期时间，提高性能
        String script = "for _, key in ipairs(KEYS) do redis.call('EXPIRE', key, ARGV[1]) end; return #KEYS";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        
        // 分批处理
        List<String> allKeys = tenantIds.stream()
                .map(id -> TENANT_CACHE_KEY_PREFIX + id)
                .collect(Collectors.toList());
        
        int totalKeys = allKeys.size();
        for (int i = 0; i < totalKeys; i += BATCH_SIZE) {
            int endIndex = Math.min(i + BATCH_SIZE, totalKeys);
            List<String> batchKeys = allKeys.subList(i, endIndex);
            
            try {
                Long result = tenantRedisTemplate.execute(redisScript, batchKeys, String.valueOf(CACHE_EXPIRE_TIME));
                logger.debug("已刷新 {} 个租户的过期时间", result);
            } catch (Exception e) {
                logger.error("批量刷新租户过期时间失败", e);
            }
        }
        
        // 刷新租户ID集合的过期时间
        stringRedisTemplate.expire(TENANT_IDS_KEY, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
        stringRedisTemplate.expire(TENANT_LOADED_FLAG, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
    }
}