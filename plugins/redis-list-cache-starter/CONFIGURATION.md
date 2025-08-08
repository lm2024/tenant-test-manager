# Redis List Cache Starter 配置参数说明

本文档详细说明了Redis列表缓存组件的所有配置参数，包括默认值、使用场景和最佳实践建议。

## 配置结构

所有配置都在 `redis.list.cache` 前缀下：

```yaml
redis:
  list:
    cache:
      # 基础配置
      enabled: true
      default-expire-time: 1800
      max-cache-pages: 5
      key-prefix: "list_cache"
      tenant-aware: true
      serialization-type: JSON
      
      # 监控配置
      monitor:
        enabled: true
        metrics-interval: 60
        verbose-logging: false
        slow-query-threshold: 1000
        jmx-enabled: false
        stats-retention-time: 86400
      
      # 降级配置
      fallback:
        enabled: true
        timeout: 200
        max-retries: 3
        retry-interval: 100
        circuit-breaker:
          enabled: true
          failure-threshold: 5
          recovery-time: 60
          half-open-max-calls: 3
      
      # 性能配置
      performance:
        async-enabled: false
        async-thread-pool-size: 5
        batch-size: 100
        compression-enabled: false
        compression-threshold: 1024
        warm-up:
          enabled: false
          delay: 30
          key-patterns: []
          concurrency: 3
```

## 基础配置

### enabled
- **类型**: `boolean`
- **默认值**: `true`
- **说明**: 是否启用Redis列表缓存功能
- **使用场景**: 
  - 开发环境可设置为 `false` 进行调试
  - 生产环境通常设置为 `true`
- **示例**:
  ```yaml
  redis.list.cache.enabled: true
  ```

### default-expire-time
- **类型**: `long`
- **默认值**: `1800` (30分钟)
- **单位**: 秒
- **说明**: 缓存的默认过期时间
- **建议值**:
  - 高频更新数据: `300-900` (5-15分钟)
  - 中频更新数据: `1800` (30分钟)
  - 低频更新数据: `3600-7200` (1-2小时)
- **示例**:
  ```yaml
  redis.list.cache.default-expire-time: 1800
  ```

### max-cache-pages
- **类型**: `int`
- **默认值**: `5`
- **说明**: 最大缓存页数，超过此页数的查询将不会被缓存
- **建议值**: 
  - 小型应用: `3-5`
  - 中型应用: `5-10`
  - 大型应用: `10-20`
- **注意**: 页数过多会占用大量内存
- **示例**:
  ```yaml
  redis.list.cache.max-cache-pages: 5
  ```

### key-prefix
- **类型**: `string`
- **默认值**: `"list_cache"`
- **说明**: 缓存键的前缀，用于区分不同的缓存类型
- **建议**: 使用有意义的前缀，如 `user_cache`、`order_cache`
- **示例**:
  ```yaml
  redis.list.cache.key-prefix: "list_cache"
  ```

### tenant-aware
- **类型**: `boolean`
- **默认值**: `true`
- **说明**: 是否启用租户隔离，启用后不同租户的缓存数据完全隔离
- **使用场景**:
  - 多租户应用: 设置为 `true`
  - 单租户应用: 可设置为 `false` 以简化键结构
- **示例**:
  ```yaml
  redis.list.cache.tenant-aware: true
  ```

### serialization-type
- **类型**: `enum`
- **默认值**: `JSON`
- **可选值**: `JSON`, `JAVA`, `KRYO`
- **说明**: 缓存数据的序列化方式
- **性能对比**:
  - `JSON`: 可读性好，跨语言兼容，性能中等
  - `JAVA`: Java原生序列化，性能较差，体积大
  - `KRYO`: 高性能二进制序列化，体积小，速度快
- **建议**: 生产环境推荐使用 `JSON` 或 `KRYO`
- **示例**:
  ```yaml
  redis.list.cache.serialization-type: JSON
  ```

## 监控配置

### monitor.enabled
- **类型**: `boolean`
- **默认值**: `true`
- **说明**: 是否启用缓存监控功能
- **建议**: 生产环境建议启用以便监控缓存性能

### monitor.metrics-interval
- **类型**: `int`
- **默认值**: `60`
- **单位**: 秒
- **说明**: 指标收集和报告的间隔时间
- **建议值**:
  - 开发环境: `30-60`
  - 生产环境: `60-300`

### monitor.verbose-logging
- **类型**: `boolean`
- **默认值**: `false`
- **说明**: 是否启用详细日志记录
- **注意**: 启用后会产生大量日志，建议仅在调试时使用

### monitor.slow-query-threshold
- **类型**: `long`
- **默认值**: `1000`
- **单位**: 毫秒
- **说明**: 慢查询的阈值，超过此时间的查询会被记录为慢查询
- **建议值**: `500-2000`

### monitor.jmx-enabled
- **类型**: `boolean`
- **默认值**: `false`
- **说明**: 是否启用JMX监控，启用后可通过JMX客户端查看指标
- **使用场景**: 需要通过JMX监控工具查看缓存指标时启用

### monitor.stats-retention-time
- **类型**: `long`
- **默认值**: `86400` (24小时)
- **单位**: 秒
- **说明**: 统计信息的保留时间

## 降级配置

### fallback.enabled
- **类型**: `boolean`
- **默认值**: `true`
- **说明**: 是否启用降级功能，当Redis不可用时自动回退到数据库查询
- **建议**: 生产环境强烈建议启用

### fallback.timeout
- **类型**: `long`
- **默认值**: `200`
- **单位**: 毫秒
- **说明**: Redis操作的超时时间，超过此时间将触发降级
- **建议值**: `100-500`

### fallback.max-retries
- **类型**: `int`
- **默认值**: `3`
- **说明**: Redis操作失败时的最大重试次数
- **建议值**: `2-5`

### fallback.retry-interval
- **类型**: `long`
- **默认值**: `100`
- **单位**: 毫秒
- **说明**: 重试之间的间隔时间

### fallback.circuit-breaker.enabled
- **类型**: `boolean`
- **默认值**: `true`
- **说明**: 是否启用熔断器，防止Redis故障时的雪崩效应

### fallback.circuit-breaker.failure-threshold
- **类型**: `int`
- **默认值**: `5`
- **说明**: 熔断器的失败阈值，连续失败次数达到此值时触发熔断

### fallback.circuit-breaker.recovery-time
- **类型**: `long`
- **默认值**: `60`
- **单位**: 秒
- **说明**: 熔断器的恢复时间，熔断后等待此时间再尝试恢复

### fallback.circuit-breaker.half-open-max-calls
- **类型**: `int`
- **默认值**: `3`
- **说明**: 半开状态下的最大测试请求数

## 性能配置

### performance.async-enabled
- **类型**: `boolean`
- **默认值**: `false`
- **说明**: 是否启用异步缓存操作
- **注意**: 启用后缓存操作不会阻塞主线程，但可能影响数据一致性

### performance.async-thread-pool-size
- **类型**: `int`
- **默认值**: `5`
- **说明**: 异步操作的线程池大小
- **建议值**: CPU核心数的1-2倍

### performance.batch-size
- **类型**: `int`
- **默认值**: `100`
- **说明**: 批量操作的大小
- **建议值**: `50-200`

### performance.compression-enabled
- **类型**: `boolean`
- **默认值**: `false`
- **说明**: 是否启用缓存数据压缩
- **使用场景**: 缓存数据较大时可启用以节省内存

### performance.compression-threshold
- **类型**: `int`
- **默认值**: `1024`
- **单位**: 字节
- **说明**: 压缩阈值，超过此大小的数据才会被压缩

### performance.warm-up.enabled
- **类型**: `boolean`
- **默认值**: `false`
- **说明**: 是否启用缓存预热功能

### performance.warm-up.delay
- **类型**: `long`
- **默认值**: `30`
- **单位**: 秒
- **说明**: 应用启动后延迟多长时间开始预热

### performance.warm-up.key-patterns
- **类型**: `string[]`
- **默认值**: `[]`
- **说明**: 需要预热的缓存键模式列表

### performance.warm-up.concurrency
- **类型**: `int`
- **默认值**: `3`
- **说明**: 预热操作的并发数

## 环境特定配置

### 开发环境配置

```yaml
redis:
  list:
    cache:
      enabled: true
      default-expire-time: 300  # 5分钟，便于测试
      max-cache-pages: 3
      monitor:
        enabled: true
        verbose-logging: true    # 启用详细日志
        metrics-interval: 30
      fallback:
        enabled: false          # 开发时可禁用降级
```

### 测试环境配置

```yaml
redis:
  list:
    cache:
      enabled: true
      default-expire-time: 600  # 10分钟
      max-cache-pages: 5
      monitor:
        enabled: true
        verbose-logging: false
        metrics-interval: 60
      fallback:
        enabled: true
        timeout: 500            # 测试环境网络可能较慢
```

### 生产环境配置

```yaml
redis:
  list:
    cache:
      enabled: true
      default-expire-time: 1800  # 30分钟
      max-cache-pages: 10
      serialization-type: KRYO   # 高性能序列化
      monitor:
        enabled: true
        verbose-logging: false
        metrics-interval: 300    # 5分钟
        jmx-enabled: true       # 启用JMX监控
      fallback:
        enabled: true
        timeout: 200
        circuit-breaker:
          enabled: true
      performance:
        async-enabled: true      # 启用异步操作
        compression-enabled: true # 启用压缩
        warm-up:
          enabled: true         # 启用预热
          delay: 60
```

## 配置验证

组件会在启动时验证配置参数的有效性：

```java
// 验证规则
if (defaultExpireTime <= 0) {
    throw new IllegalArgumentException("Default expire time must be positive");
}

if (maxCachePages <= 0) {
    throw new IllegalArgumentException("Max cache pages must be positive");
}

if (keyPrefix == null || keyPrefix.trim().isEmpty()) {
    throw new IllegalArgumentException("Key prefix cannot be empty");
}
```

## 配置优先级

配置的优先级顺序（从高到低）：

1. **方法级注解参数** - `@ListCache(expireTime = 600)`
2. **类级注解参数** - `@CacheConfig(expireTime = 1200)`
3. **应用配置文件** - `application.yml`
4. **默认值** - 组件内置默认值

## 动态配置

某些配置支持运行时动态修改：

```java
@Autowired
private RedisListCacheProperties properties;

// 动态修改默认过期时间
properties.setDefaultExpireTime(3600);

// 动态启用/禁用监控
properties.getMonitor().setEnabled(false);
```

## 配置最佳实践

### 1. 根据业务特点调整过期时间

```yaml
# 用户信息 - 变更频率低
user-cache:
  default-expire-time: 3600  # 1小时

# 商品信息 - 变更频率中等
product-cache:
  default-expire-time: 1800  # 30分钟

# 库存信息 - 变更频率高
inventory-cache:
  default-expire-time: 300   # 5分钟
```

### 2. 根据系统负载调整缓存页数

```yaml
# 高负载系统
redis.list.cache.max-cache-pages: 3

# 中等负载系统
redis.list.cache.max-cache-pages: 5

# 低负载系统
redis.list.cache.max-cache-pages: 10
```

### 3. 根据网络环境调整超时时间

```yaml
# 内网环境
fallback.timeout: 100

# 公网环境
fallback.timeout: 500

# 跨地域环境
fallback.timeout: 1000
```

### 4. 根据数据大小启用压缩

```yaml
# 数据较小（< 1KB）
performance.compression-enabled: false

# 数据较大（> 1KB）
performance:
  compression-enabled: true
  compression-threshold: 1024
```

## 故障排查配置

当遇到问题时，可以临时调整以下配置进行排查：

```yaml
# 启用详细日志
monitor:
  verbose-logging: true
  slow-query-threshold: 100  # 降低慢查询阈值

# 禁用降级以观察Redis行为
fallback:
  enabled: false

# 缩短过期时间以快速验证缓存行为
default-expire-time: 60
```

## 配置模板

### 微服务配置模板

```yaml
redis:
  list:
    cache:
      enabled: true
      key-prefix: "${spring.application.name}_cache"
      tenant-aware: true
      monitor:
        enabled: true
        metrics-interval: 180
      fallback:
        enabled: true
        timeout: 200
```

### 高并发配置模板

```yaml
redis:
  list:
    cache:
      enabled: true
      max-cache-pages: 20
      serialization-type: KRYO
      performance:
        async-enabled: true
        async-thread-pool-size: 10
        batch-size: 200
        compression-enabled: true
      fallback:
        circuit-breaker:
          failure-threshold: 10
```

## 配置检查清单

部署前请检查以下配置项：

- [ ] `enabled` 是否正确设置
- [ ] `default-expire-time` 是否符合业务需求
- [ ] `max-cache-pages` 是否合理
- [ ] `tenant-aware` 是否符合应用架构
- [ ] `fallback.enabled` 在生产环境是否启用
- [ ] `monitor.enabled` 是否启用
- [ ] 序列化方式是否适合数据类型
- [ ] 超时时间是否符合网络环境
- [ ] 线程池大小是否合理

通过合理的配置，可以最大化Redis列表缓存组件的性能和稳定性。