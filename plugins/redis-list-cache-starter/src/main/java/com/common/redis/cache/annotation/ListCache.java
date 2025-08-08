package com.common.redis.cache.annotation;

import java.lang.annotation.*;

/**
 * 列表缓存注解
 * 
 * <p>用于标记需要缓存的列表查询方法，支持智能分页缓存和租户隔离。</p>
 * 
 * <p>使用示例：</p>
 * <pre>
 * {@code
 * @GetMapping("/users")
 * @ListCache(value = "users", expireTime = 1800, maxCachePages = 5)
 * public Page<User> findUsers(Pageable pageable) {
 *     // 查询逻辑
 * }
 * }
 * </pre>
 * 
 * @author Kiro
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListCache {
    
    /**
     * 缓存名称，默认使用类名+方法名
     * 
     * @return 缓存名称
     */
    String value() default "";
    
    /**
     * 缓存键前缀，用于区分不同业务的缓存
     * 
     * @return 缓存键前缀
     */
    String keyPrefix() default "";
    
    /**
     * 过期时间（秒），默认30分钟
     * 
     * <p>建议设置：</p>
     * <ul>
     *   <li>高频数据：300-900秒（5-15分钟）</li>
     *   <li>中频数据：1800秒（30分钟）</li>
     *   <li>低频数据：3600-7200秒（1-2小时）</li>
     * </ul>
     * 
     * @return 过期时间（秒）
     */
    long expireTime() default 1800;
    
    /**
     * 最大缓存页数，默认5页
     * 
     * <p>只有页码小于等于此值的查询结果才会被缓存，
     * 超过此页数的查询将直接访问数据库。</p>
     * 
     * @return 最大缓存页数
     */
    int maxCachePages() default 5;
    
    /**
     * 是否启用租户隔离，默认启用
     * 
     * <p>启用后，不同租户的缓存数据将完全隔离，
     * 缓存键会自动包含租户ID前缀。</p>
     * 
     * @return 是否启用租户隔离
     */
    boolean tenantAware() default true;
    
    /**
     * 缓存条件SpEL表达式，默认无条件缓存
     * 
     * <p>支持的变量：</p>
     * <ul>
     *   <li>#args - 方法参数数组</li>
     *   <li>#result - 方法返回值（仅在unless中可用）</li>
     *   <li>#root.method - 当前方法</li>
     *   <li>#root.target - 目标对象</li>
     * </ul>
     * 
     * <p>示例：</p>
     * <pre>
     * condition = "#args[0].pageNumber < 5"  // 只缓存前5页
     * condition = "#result != null"          // 只缓存非空结果
     * </pre>
     * 
     * @return 缓存条件SpEL表达式
     */
    String condition() default "";
    
    /**
     * 排除缓存条件SpEL表达式，默认无排除条件
     * 
     * <p>当此表达式为true时，不进行缓存操作。
     * 与condition的区别是unless可以访问方法返回值。</p>
     * 
     * @return 排除缓存条件SpEL表达式
     */
    String unless() default "";
    
    /**
     * 是否同步执行缓存操作，默认false
     * 
     * <p>设置为true时，缓存操作将在同一线程中同步执行；
     * 设置为false时，缓存操作将异步执行，不影响主流程性能。</p>
     * 
     * @return 是否同步执行
     */
    boolean sync() default false;
}