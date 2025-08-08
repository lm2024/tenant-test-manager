package com.common.redis.cache.annotation;

import java.lang.annotation.*;

/**
 * 缓存配置注解
 * 
 * <p>用于在类级别定义缓存的全局配置，可以被方法级别的注解覆盖。</p>
 * 
 * <p>使用示例：</p>
 * <pre>
 * {@code
 * @RestController
 * @CacheConfig(
 *     cacheNames = "users", 
 *     keyPrefix = "user_service", 
 *     expireTime = 1800,
 *     tenantAware = true
 * )
 * public class UserController {
 *     
 *     @GetMapping("/users")
 *     @ListCache  // 继承类级别配置
 *     public Page<User> findUsers(Pageable pageable) {
 *         // 查询逻辑
 *     }
 *     
 *     @GetMapping("/users/active")
 *     @ListCache(expireTime = 900)  // 覆盖类级别的过期时间
 *     public Page<User> findActiveUsers(Pageable pageable) {
 *         // 查询逻辑
 *     }
 * }
 * }
 * </pre>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheConfig {
    
    /**
     * 默认缓存名称数组
     * 
     * <p>当方法级别的注解未指定缓存名称时，将使用此配置。
     * 支持指定多个缓存名称。</p>
     * 
     * @return 默认缓存名称数组
     */
    String[] cacheNames() default {};
    
    /**
     * 默认缓存键前缀
     * 
     * <p>用于区分不同服务或模块的缓存，避免键冲突。
     * 建议使用服务名或模块名作为前缀。</p>
     * 
     * @return 默认缓存键前缀
     */
    String keyPrefix() default "";
    
    /**
     * 默认过期时间（秒）
     * 
     * <p>当方法级别的注解未指定过期时间时，将使用此配置。</p>
     * 
     * @return 默认过期时间（秒）
     */
    long expireTime() default 1800;
    
    /**
     * 默认最大缓存页数
     * 
     * <p>当方法级别的注解未指定最大缓存页数时，将使用此配置。</p>
     * 
     * @return 默认最大缓存页数
     */
    int maxCachePages() default 5;
    
    /**
     * 默认是否启用租户隔离
     * 
     * <p>当方法级别的注解未指定租户隔离设置时，将使用此配置。</p>
     * 
     * @return 默认是否启用租户隔离
     */
    boolean tenantAware() default true;
    
    /**
     * 默认缓存条件SpEL表达式
     * 
     * <p>当方法级别的注解未指定缓存条件时，将使用此配置。</p>
     * 
     * @return 默认缓存条件SpEL表达式
     */
    String condition() default "";
    
    /**
     * 默认排除缓存条件SpEL表达式
     * 
     * <p>当方法级别的注解未指定排除条件时，将使用此配置。</p>
     * 
     * @return 默认排除缓存条件SpEL表达式
     */
    String unless() default "";
    
    /**
     * 默认是否同步执行缓存操作
     * 
     * <p>当方法级别的注解未指定同步设置时，将使用此配置。</p>
     * 
     * @return 默认是否同步执行
     */
    boolean sync() default false;
    
    /**
     * 默认清除时机
     * 
     * <p>当方法级别的@CacheEvict注解未指定清除时机时，将使用此配置。</p>
     * 
     * @return 默认清除时机
     */
    CacheEvict.EvictTiming evictTiming() default CacheEvict.EvictTiming.AFTER;
    
    /**
     * 默认是否忽略清除异常
     * 
     * <p>当方法级别的@CacheEvict注解未指定异常处理时，将使用此配置。</p>
     * 
     * @return 默认是否忽略清除异常
     */
    boolean ignoreEvictExceptions() default true;
}