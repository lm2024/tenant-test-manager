package com.common.redis.cache.annotation;

import java.lang.annotation.*;

/**
 * 缓存失效注解
 * 
 * <p>用于标记需要清除缓存的方法，支持多种清除策略和条件控制。</p>
 * 
 * <p>使用示例：</p>
 * <pre>
 * {@code
 * @PostMapping("/users")
 * @CacheEvict(value = "users", keyPattern = "users:*")
 * public User createUser(@RequestBody User user) {
 *     // 创建逻辑
 * }
 * 
 * @DeleteMapping("/users/{id}")
 * @CacheEvict(value = {"users", "userRoles"}, allEntries = true)
 * public void deleteUser(@PathVariable String id) {
 *     // 删除逻辑
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
public @interface CacheEvict {
    
    /**
     * 要清除的缓存名称数组
     * 
     * <p>支持指定多个缓存名称，将同时清除这些缓存。
     * 如果为空，则使用方法所在类的默认缓存名称。</p>
     * 
     * @return 缓存名称数组
     */
    String[] value() default {};
    
    /**
     * 缓存键模式，支持通配符匹配
     * 
     * <p>支持的通配符：</p>
     * <ul>
     *   <li>* - 匹配任意字符</li>
     *   <li>? - 匹配单个字符</li>
     *   <li>[abc] - 匹配指定字符集合中的任意一个</li>
     * </ul>
     * 
     * <p>示例：</p>
     * <pre>
     * keyPattern = "users:*"           // 清除所有以users:开头的缓存
     * keyPattern = "user:?:profile"    // 清除如user:1:profile的缓存
     * keyPattern = "cache:[0-9]*"      // 清除以cache:数字开头的缓存
     * </pre>
     * 
     * @return 缓存键模式
     */
    String keyPattern() default "";
    
    /**
     * 是否清除所有相关缓存，默认false
     * 
     * <p>设置为true时，将清除指定缓存名称下的所有缓存项；
     * 设置为false时，只清除匹配keyPattern的缓存项。</p>
     * 
     * @return 是否清除所有缓存
     */
    boolean allEntries() default false;
    
    /**
     * 清除条件SpEL表达式，默认无条件清除
     * 
     * <p>支持的变量：</p>
     * <ul>
     *   <li>#args - 方法参数数组</li>
     *   <li>#result - 方法返回值</li>
     *   <li>#root.method - 当前方法</li>
     *   <li>#root.target - 目标对象</li>
     * </ul>
     * 
     * <p>示例：</p>
     * <pre>
     * condition = "#result != null"        // 只有操作成功时才清除缓存
     * condition = "#args[0].id != null"    // 只有ID不为空时才清除缓存
     * condition = "#root.method.name.startsWith('delete')"  // 只有删除方法才清除缓存
     * </pre>
     * 
     * @return 清除条件SpEL表达式
     */
    String condition() default "";
    
    /**
     * 清除时机，默认方法执行后清除
     * 
     * <p>可选值：</p>
     * <ul>
     *   <li>AFTER - 方法执行成功后清除（默认）</li>
     *   <li>BEFORE - 方法执行前清除</li>
     *   <li>BOTH - 方法执行前后都清除</li>
     * </ul>
     * 
     * @return 清除时机
     */
    EvictTiming timing() default EvictTiming.AFTER;
    
    /**
     * 是否忽略清除异常，默认true
     * 
     * <p>设置为true时，缓存清除失败不会影响主业务流程；
     * 设置为false时，缓存清除失败会抛出异常。</p>
     * 
     * @return 是否忽略清除异常
     */
    boolean ignoreExceptions() default true;
    
    /**
     * 清除超时时间（毫秒），默认100毫秒
     * 
     * <p>超过此时间的清除操作将被中断，避免影响主业务性能。</p>
     * 
     * @return 清除超时时间（毫秒）
     */
    long timeout() default 100;
    
    /**
     * 缓存清除时机枚举
     */
    enum EvictTiming {
        /**
         * 方法执行前清除
         */
        BEFORE,
        
        /**
         * 方法执行成功后清除（默认）
         */
        AFTER,
        
        /**
         * 方法执行前后都清除
         */
        BOTH
    }
}