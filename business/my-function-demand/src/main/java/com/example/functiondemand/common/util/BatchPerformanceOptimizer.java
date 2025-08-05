package com.example.functiondemand.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class BatchPerformanceOptimizer {
    
    private static final int DEFAULT_BATCH_SIZE = 100;
    private static final int LARGE_BATCH_THRESHOLD = 500;
    
    /**
     * 批量保存优化
     */
    @Transactional
    public <T> List<T> batchSave(List<T> entities, JpaRepository<T, String> repository) {
        return batchSave(entities, repository, DEFAULT_BATCH_SIZE);
    }
    
    /**
     * 批量保存优化（指定批次大小）
     */
    @Transactional
    public <T> List<T> batchSave(List<T> entities, JpaRepository<T, String> repository, int batchSize) {
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        List<T> savedEntities = new ArrayList<>();
        
        log.debug("开始批量保存，总数: {}, 批次大小: {}", entities.size(), batchSize);
        
        for (int i = 0; i < entities.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, entities.size());
            List<T> batch = entities.subList(i, endIndex);
            
            try {
                List<T> batchResult = repository.saveAll(batch);
                savedEntities.addAll(batchResult);
                
                // 大批量操作时强制刷新
                if (entities.size() > LARGE_BATCH_THRESHOLD && (i + batchSize) % (batchSize * 5) == 0) {
                    repository.flush();
                    log.debug("批量保存进度: {}/{}", i + batchSize, entities.size());
                }
                
            } catch (Exception e) {
                log.error("批量保存失败，批次: {}-{}", i, endIndex, e);
                throw e;
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("批量保存完成，总数: {}, 耗时: {}ms, 平均: {}ms/条", 
            entities.size(), duration, duration / entities.size());
        
        return savedEntities;
    }
    
    /**
     * 批量删除优化
     */
    @Transactional
    public <T> void batchDelete(List<String> ids, JpaRepository<T, String> repository) {
        batchDelete(ids, repository, DEFAULT_BATCH_SIZE);
    }
    
    /**
     * 批量删除优化（指定批次大小）
     */
    @Transactional
    public <T> void batchDelete(List<String> ids, JpaRepository<T, String> repository, int batchSize) {
        if (ids.isEmpty()) {
            return;
        }
        
        long startTime = System.currentTimeMillis();
        
        log.debug("开始批量删除，总数: {}, 批次大小: {}", ids.size(), batchSize);
        
        for (int i = 0; i < ids.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, ids.size());
            List<String> batch = ids.subList(i, endIndex);
            
            try {
                repository.deleteAllById(batch);
                
                // 大批量操作时强制刷新
                if (ids.size() > LARGE_BATCH_THRESHOLD && (i + batchSize) % (batchSize * 5) == 0) {
                    repository.flush();
                    log.debug("批量删除进度: {}/{}", i + batchSize, ids.size());
                }
                
            } catch (Exception e) {
                log.error("批量删除失败，批次: {}-{}", i, endIndex, e);
                throw e;
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("批量删除完成，总数: {}, 耗时: {}ms, 平均: {}ms/条", 
            ids.size(), duration, duration / ids.size());
    }
    
    /**
     * 批量更新优化
     */
    @Transactional
    public <T> List<T> batchUpdate(List<T> entities, JpaRepository<T, String> repository) {
        return batchUpdate(entities, repository, DEFAULT_BATCH_SIZE);
    }
    
    /**
     * 批量更新优化（指定批次大小）
     */
    @Transactional
    public <T> List<T> batchUpdate(List<T> entities, JpaRepository<T, String> repository, int batchSize) {
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        List<T> updatedEntities = new ArrayList<>();
        
        log.debug("开始批量更新，总数: {}, 批次大小: {}", entities.size(), batchSize);
        
        for (int i = 0; i < entities.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, entities.size());
            List<T> batch = entities.subList(i, endIndex);
            
            try {
                List<T> batchResult = repository.saveAll(batch);
                updatedEntities.addAll(batchResult);
                
                // 大批量操作时强制刷新
                if (entities.size() > LARGE_BATCH_THRESHOLD && (i + batchSize) % (batchSize * 5) == 0) {
                    repository.flush();
                    log.debug("批量更新进度: {}/{}", i + batchSize, entities.size());
                }
                
            } catch (Exception e) {
                log.error("批量更新失败，批次: {}-{}", i, endIndex, e);
                throw e;
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("批量更新完成，总数: {}, 耗时: {}ms, 平均: {}ms/条", 
            entities.size(), duration, duration / entities.size());
        
        return updatedEntities;
    }
    
    /**
     * 批量查询优化
     */
    public <T> List<T> batchFind(List<String> ids, JpaRepository<T, String> repository) {
        return batchFind(ids, repository, DEFAULT_BATCH_SIZE);
    }
    
    /**
     * 批量查询优化（指定批次大小）
     */
    public <T> List<T> batchFind(List<String> ids, JpaRepository<T, String> repository, int batchSize) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        List<T> results = new ArrayList<>();
        
        log.debug("开始批量查询，总数: {}, 批次大小: {}", ids.size(), batchSize);
        
        for (int i = 0; i < ids.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, ids.size());
            List<String> batch = ids.subList(i, endIndex);
            
            try {
                List<T> batchResult = repository.findAllById(batch);
                results.addAll(batchResult);
                
            } catch (Exception e) {
                log.error("批量查询失败，批次: {}-{}", i, endIndex, e);
                throw e;
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("批量查询完成，查询数: {}, 结果数: {}, 耗时: {}ms", 
            ids.size(), results.size(), duration);
        
        return results;
    }
    
    /**
     * 批量处理通用方法
     */
    public <T, R> List<R> batchProcess(List<T> items, Function<List<T>, List<R>> processor, int batchSize) {
        if (items.isEmpty()) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        List<R> results = new ArrayList<>();
        
        log.debug("开始批量处理，总数: {}, 批次大小: {}", items.size(), batchSize);
        
        for (int i = 0; i < items.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, items.size());
            List<T> batch = items.subList(i, endIndex);
            
            try {
                List<R> batchResult = processor.apply(batch);
                results.addAll(batchResult);
                
            } catch (Exception e) {
                log.error("批量处理失败，批次: {}-{}", i, endIndex, e);
                throw e;
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("批量处理完成，处理数: {}, 结果数: {}, 耗时: {}ms", 
            items.size(), results.size(), duration);
        
        return results;
    }
    
    /**
     * 计算最优批次大小
     */
    public int calculateOptimalBatchSize(int totalItems) {
        if (totalItems <= 100) {
            return totalItems;
        } else if (totalItems <= 1000) {
            return 100;
        } else if (totalItems <= 10000) {
            return 200;
        } else {
            return 500;
        }
    }
}