package com.common.redis.cache.manager;

import com.common.redis.cache.model.CacheStats;
import com.common.redis.cache.model.ListCacheData;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Set;

/**
 * 列表缓存管理器接口
 * 
 * <p>定义缓存的核心操作接口，包括增删改查、批量操作和统计功能。</p>
 * 
 * @author Kiro
 * @version 1.0.0
 */
public interface ListCacheManager {
    
    /**
     * 获取缓存数据
     * 
     * @param key 缓存键
     * @param valueType 值类型引用
     * @param <T> 数据类型
     * @return 缓存数据，不存在时返回null
     */
    <T> ListCacheData<T> get(String key, TypeReference<ListCacheData<T>> valueType);
    
    /**
     * 设置缓存数据
     * 
     * @param key 缓存键
     * @param value 缓存值
     * @param expireTime 过期时间（秒）
     * @param <T> 数据类型
     */
    <T> void set(String key, ListCacheData<T> value, long expireTime);
    
    /**
     * 设置缓存数据（使用默认过期时间）
     * 
     * @param key 缓存键
     * @param value 缓存值
     * @param <T> 数据类型
     */
    <T> void set(String key, ListCacheData<T> value);
    
    /**
     * 删除缓存
     * 
     * @param key 缓存键
     * @return 是否删除成功
     */
    boolean delete(String key);
    
    /**
     * 批量删除缓存
     * 
     * @param keys 缓存键列表
     * @return 删除成功的数量
     */
    long delete(List<String> keys);
    
    /**
     * 根据模式删除缓存
     * 
     * @param pattern 键模式，支持通配符
     * @return 删除成功的数量
     */
    long deleteByPattern(String pattern);
    
    /**
     * 检查缓存是否存在
     * 
     * @param key 缓存键
     * @return 是否存在
     */
    boolean exists(String key);
    
    /**
     * 批量检查缓存是否存在
     * 
     * @param keys 缓存键列表
     * @return 存在的键数量
     */
    long exists(List<String> keys);
    
    /**
     * 设置缓存过期时间
     * 
     * @param key 缓存键
     * @param expireTime 过期时间（秒）
     * @return 是否设置成功
     */
    boolean expire(String key, long expireTime);
    
    /**
     * 获取缓存剩余过期时间
     * 
     * @param key 缓存键
     * @return 剩余时间（秒），-1表示永不过期，-2表示键不存在
     */
    long getExpire(String key);
    
    /**
     * 根据模式查找缓存键
     * 
     * @param pattern 键模式，支持通配符
     * @return 匹配的键集合
     */
    Set<String> keys(String pattern);
    
    /**
     * 根据模式查找缓存键（限制数量）
     * 
     * @param pattern 键模式，支持通配符
     * @param limit 最大返回数量
     * @return 匹配的键集合
     */
    Set<String> keys(String pattern, int limit);
    
    /**
     * 获取缓存大小（键的数量）
     * 
     * @return 缓存大小
     */
    long size();
    
    /**
     * 获取指定模式的缓存大小
     * 
     * @param pattern 键模式
     * @return 匹配的缓存数量
     */
    long size(String pattern);
    
    /**
     * 清空所有缓存
     * 
     * @return 是否清空成功
     */
    boolean clear();
    
    /**
     * 清空指定模式的缓存
     * 
     * @param pattern 键模式
     * @return 清空的缓存数量
     */
    long clear(String pattern);
    
    /**
     * 获取缓存统计信息
     * 
     * @return 统计信息
     */
    CacheStats getStats();
    
    /**
     * 重置统计信息
     */
    void resetStats();
    
    /**
     * 检查缓存健康状态
     * 
     * @return 是否健康
     */
    boolean isHealthy();
    
    /**
     * 获取缓存连接信息
     * 
     * @return 连接信息
     */
    String getConnectionInfo();
    
    /**
     * 预热缓存
     * 
     * @param keys 要预热的键列表
     */
    void warmUp(List<String> keys);
    
    /**
     * 获取缓存内存使用情况
     * 
     * @return 内存使用信息
     */
    String getMemoryInfo();
    
    /**
     * 执行缓存维护操作
     * 
     * @return 维护结果信息
     */
    String maintenance();
    
    /**
     * 获取缓存配置信息
     * 
     * @return 配置信息
     */
    String getConfigInfo();
    
    /**
     * 测试缓存连接
     * 
     * @return 连接测试结果
     */
    boolean testConnection();
    
    /**
     * 获取缓存版本信息
     * 
     * @return 版本信息
     */
    String getVersion();
    
    /**
     * 导出缓存数据
     * 
     * @param pattern 键模式
     * @return 导出的数据
     */
    List<String> exportData(String pattern);
    
    /**
     * 导入缓存数据
     * 
     * @param data 要导入的数据
     * @return 导入成功的数量
     */
    long importData(List<String> data);
    
    /**
     * 获取缓存键的详细信息
     * 
     * @param key 缓存键
     * @return 键的详细信息
     */
    String getKeyInfo(String key);
    
    /**
     * 重命名缓存键
     * 
     * @param oldKey 旧键名
     * @param newKey 新键名
     * @return 是否重命名成功
     */
    boolean rename(String oldKey, String newKey);
    
    /**
     * 复制缓存数据
     * 
     * @param sourceKey 源键名
     * @param targetKey 目标键名
     * @return 是否复制成功
     */
    boolean copy(String sourceKey, String targetKey);
    
    /**
     * 获取缓存数据的原始字节大小
     * 
     * @param key 缓存键
     * @return 字节大小，-1表示键不存在
     */
    long getDataSize(String key);
    
    /**
     * 压缩缓存数据
     * 
     * @param key 缓存键
     * @return 是否压缩成功
     */
    boolean compress(String key);
    
    /**
     * 解压缓存数据
     * 
     * @param key 缓存键
     * @return 是否解压成功
     */
    boolean decompress(String key);
}