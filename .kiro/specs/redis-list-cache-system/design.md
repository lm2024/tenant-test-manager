# 设计文档

## 概述

本设计文档描述了一个基于Redis的列表查询缓存系统，该系统通过AOP切面和注解的方式，为控制器的列表查询方法提供透明的缓存功能。系统设计为可复用的Spring Boot Starter，命名为 `redis-list-cache-starter`，能够轻松集成到任何使用Spring Boot + Redis的项目中。

## 组件命名

基于你现有的插件命名规范：
- `tenant-routing-starter` - 租户路由组件
- `file-import-export-starter` - 文件导入导出组件  
- `segment-id-generator-starter` - 号段ID生成组件
- `elasticsearch-crud-starter` - ES增删改查组件

新的Redis列表缓存组件命名为：**`redis-list-cache-starter`**

## 架构设计

### 整体架构

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │    │  Cache Aspect   │    │  Redis Cluster  │
│                 │    │                 │    │                 │
│ @ListCache      │───▶│ - 缓存拦截      │───▶│ - 数据存储      │
│ @CacheEvict     │    │ - 键生成        │    │ - 过期管理      │
│                 │    │ - 序列化        │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Service       │    │ Cache Manager   │    │ Fallback DB     │
│                 │    │                 │    │                 │
│ - 业务逻辑      │    │ - 缓存策略      │    │ - 数据库查询    │
│ - 数据查询      │    │ - 失效策略      │    │ - 容错处理      │
│                 │    │ - 监控统计      │    │                 │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 核心组件

#### 1. 注解定义
- `@ListCache` - 列表查询缓存注解
- `@CacheEvict` - 缓存失效注解
- `@CacheConfig` - 缓存配置注解

#### 2. 切面处理器
- `ListCacheAspect` - 列表缓存切面
- `CacheEvictAspect` - 缓存失效切面

#### 3. 缓存管理器
- `ListCacheManager` - 缓存管理核心类
- `CacheKeyGenerator` - 缓存键生成器
- `CacheSerializer` - 缓存序列化器

#### 4. 配置管理
- `ListCacheProperties` - 配置属性类
- `ListCacheAutoConfiguration` - 自动配置类

## 组件和接口设计

### 1. 注解设计

#### @ListCache 注解
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListCache {
    /**
     * 缓存名称，默认使用类名+方法名
     */
    String value() default "";
    
    /**
     * 缓存键前缀
     */
    String keyPrefix() default "";
    
    /**
     * 过期时间（秒），默认30分钟
     */
    long expireTime() default 1800;
    
    /**
     * 最大缓存页数，默认5页
     */
    int maxCachePages() default 5;
    
    /**
     * 是否启用租户隔离
     */
    boolean tenantAware() default true;
    
    /**
     * 缓存条件SpEL表达式
     */
    String condition() default "";
}
```

#### @CacheEvict 注解
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEvict {
    /**
     * 要清除的缓存名称
     */
    String[] value() default {};
    
    /**
     * 缓存键模式，支持通配符
     */
    String keyPattern() default "";
    
    /**
     * 是否清除所有相关缓存
     */
    boolean allEntries() default false;
    
    /**
     * 清除条件SpEL表达式
     */
    String condition() default "";
}
```

### 2. 缓存键设计

#### 键命名规范
```
{tenant_id}:list_cache:{class_name}:{method_name}:{param_hash}:page_{page_num}
```

#### 键生成策略
- **租户隔离**: 键前缀包含租户ID
- **方法唯一性**: 类名+方法名确保唯一性
- **参数区分**: 参数哈希值区分不同查询条件
- **分页标识**: 页码后缀区分不同页面

### 3. 缓存管理器设计

#### ListCacheManager 接口
```java
public interface ListCacheManager {
    /**
     * 获取缓存数据
     */
    <T> T get(String key, Class<T> type);
    
    /**
     * 设置缓存数据
     */
    void set(String key, Object value, long expireTime);
    
    /**
     * 删除缓存
     */
    void delete(String key);
    
    /**
     * 批量删除缓存
     */
    void deleteByPattern(String pattern);
    
    /**
     * 检查缓存是否存在
     */
    boolean exists(String key);
    
    /**
     * 获取缓存统计信息
     */
    CacheStats getStats();
}
```

#### RedisListCacheManager 实现
```java
@Component
public class RedisListCacheManager implements ListCacheManager {
    private final RedissonClient redissonClient;
    private final CacheSerializer serializer;
    private final CacheMetrics metrics;
    
    // 实现缓存操作方法
}
```

### 4. 切面处理器设计

#### ListCacheAspect
```java
@Aspect
@Component
public class ListCacheAspect {
    
    @Around("@annotation(listCache)")
    public Object handleListCache(ProceedingJoinPoint joinPoint, ListCache listCache) {
        // 1. 生成缓存键
        // 2. 检查分页参数
        // 3. 尝试从缓存获取数据
        // 4. 缓存未命中时执行原方法
        // 5. 将结果存入缓存
        // 6. 更新统计指标
    }
}
```

#### CacheEvictAspect
```java
@Aspect
@Component
public class CacheEvictAspect {
    
    @After("@annotation(cacheEvict)")
    public void handleCacheEvict(JoinPoint joinPoint, CacheEvict cacheEvict) {
        // 1. 解析清除规则
        // 2. 生成清除键模式
        // 3. 批量删除相关缓存
        // 4. 记录清除日志
    }
}
```

## 数据模型

### 1. 缓存数据结构

#### 列表缓存对象
```java
@Data
public class ListCacheData<T> implements Serializable {
    /**
     * 缓存的数据列表
     */
    private List<T> content;
    
    /**
     * 分页信息
     */
    private PageInfo pageInfo;
    
    /**
     * 缓存时间戳
     */
    private long cacheTime;
    
    /**
     * 数据版本号
     */
    private String version;
}
```

#### 分页信息
```java
@Data
public class PageInfo implements Serializable {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}
```

### 2. 配置数据模型

#### 缓存配置属性
```java
@Data
@ConfigurationProperties(prefix = "redis.list.cache")
public class ListCacheProperties {
    /**
     * 是否启用列表缓存
     */
    private boolean enabled = true;
    
    /**
     * 默认过期时间（秒）
     */
    private long defaultExpireTime = 1800;
    
    /**
     * 最大缓存页数
     */
    private int maxCachePages = 5;
    
    /**
     * 缓存键前缀
     */
    private String keyPrefix = "list_cache";
    
    /**
     * 是否启用租户隔离
     */
    private boolean tenantAware = true;
    
    /**
     * 序列化方式
     */
    private SerializationType serializationType = SerializationType.JSON;
    
    /**
     * 监控配置
     */
    private MonitorConfig monitor = new MonitorConfig();
    
    /**
     * 降级配置
     */
    private FallbackConfig fallback = new FallbackConfig();
}
```

### 3. 监控数据模型

#### 缓存统计信息
```java
@Data
public class CacheStats {
    /**
     * 缓存命中次数
     */
    private long hitCount;
    
    /**
     * 缓存未命中次数
     */
    private long missCount;
    
    /**
     * 缓存命中率
     */
    private double hitRate;
    
    /**
     * 平均响应时间（毫秒）
     */
    private double avgResponseTime;
    
    /**
     * 缓存大小
     */
    private long cacheSize;
    
    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;
}
```

## 错误处理

### 1. 异常分类

#### 缓存异常
- `CacheException` - 缓存操作基础异常
- `CacheSerializationException` - 序列化异常
- `CacheKeyGenerationException` - 键生成异常
- `CacheConnectionException` - Redis连接异常

#### 处理策略
```java
@Component
public class CacheExceptionHandler {
    
    public <T> T handleCacheException(Exception e, Supplier<T> fallback) {
        if (e instanceof CacheConnectionException) {
            // Redis连接异常，直接降级
            log.warn("Redis连接异常，降级到数据库查询", e);
            return fallback.get();
        } else if (e instanceof CacheSerializationException) {
            // 序列化异常，记录错误并降级
            log.error("缓存序列化异常", e);
            return fallback.get();
        } else {
            // 其他异常，记录错误并降级
            log.error("缓存操作异常", e);
            return fallback.get();
        }
    }
}
```

### 2. 降级机制

#### 自动降级触发条件
- Redis连接超时（200ms）
- Redis操作异常
- 序列化/反序列化失败
- 缓存数据损坏

#### 降级处理流程
1. 检测异常类型
2. 记录降级日志
3. 更新降级指标
4. 执行原始数据库查询
5. 尝试恢复缓存连接

## 测试策略

### 1. 单元测试

#### 测试覆盖范围
- 注解解析测试
- 缓存键生成测试
- 序列化/反序列化测试
- 切面拦截测试
- 异常处理测试

#### 测试工具
- JUnit 5
- Mockito
- Testcontainers (Redis)
- Spring Boot Test

### 2. 集成测试

#### 测试场景
- 完整缓存流程测试
- 多租户隔离测试
- 并发访问测试
- 缓存失效测试
- 性能压力测试

#### 测试环境
- 嵌入式Redis
- 测试数据库
- 模拟多租户环境

### 3. 性能测试

#### 性能指标
- 缓存命中率 > 80%
- 缓存响应时间 < 50ms
- 数据库降级响应时间 < 200ms
- 并发处理能力 > 1000 QPS

## 部署和运维

### 1. 配置管理

#### application.yml 配置示例
```yaml
redis:
  list:
    cache:
      enabled: true
      default-expire-time: 1800
      max-cache-pages: 5
      key-prefix: "list_cache"
      tenant-aware: true
      serialization-type: JSON
      monitor:
        enabled: true
        metrics-interval: 60
      fallback:
        enabled: true
        timeout: 200
```

### 2. 监控告警

#### 监控指标
- 缓存命中率
- 响应时间分布
- 错误率统计
- Redis连接状态
- 内存使用情况

#### 告警规则
- 缓存命中率 < 70%
- 平均响应时间 > 100ms
- 错误率 > 5%
- Redis连接失败

### 3. 运维工具

#### 管理接口
- 缓存统计查询
- 缓存手动清除
- 配置动态更新
- 健康状态检查

#### 日志管理
- 结构化日志输出
- 错误日志聚合
- 性能日志分析
- 审计日志记录