# Redis List Cache Starter

Redis列表查询缓存组件，提供基于注解的透明缓存功能。

## 功能特性

- 🚀 **零侵入集成** - 只需添加注解即可启用缓存
- 📄 **智能分页缓存** - 只缓存前5页数据，提升性能
- 🏢 **租户数据隔离** - 完美集成多租户架构
- 🛡️ **优雅降级处理** - Redis故障时自动回退到数据库
- 📊 **丰富监控指标** - 缓存命中率、响应时间等统计
- ⚡ **高性能设计** - 基于Redisson，支持集群部署
- 🔑 **统一键管理** - 集中管理所有Redis缓存键，避免散落各处
- 🎯 **智能键生成** - 自动生成规范化的缓存键，支持参数哈希
- 🔍 **键查询监控** - 提供键的查找、统计、清理等管理功能
- 🏗️ **模式匹配** - 支持通配符模式的批量键操作

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.tenant</groupId>
    <artifactId>redis-list-cache-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2. 配置Redis

```yaml
redis:
  list:
    cache:
      enabled: true
      default-expire-time: 1800  # 30分钟
      max-cache-pages: 5
      tenant-aware: true
      monitor:
        enabled: true
        verbose-logging: false
      fallback:
        enabled: true
        timeout: 200
```

### 3. 使用统一键管理

```java
@Service
public class TestCaseService {
    
    @Autowired
    private RedisKeyManager redisKeyManager;
    
    public void cacheTestCases(List<TestCase> testCases, int pageNum, int pageSize) {
        // 生成标准化的缓存键
        String paramHash = redisKeyManager.generateParameterHash("status", "active");
        String cacheKey = redisKeyManager.generateTestCasesListKey(paramHash, pageNum, pageSize);
        
        // 存储到Redis
        // redisTemplate.opsForValue().set(cacheKey, testCases, Duration.ofMinutes(30));
    }
    
    public void clearTestCasesCache() {
        // 使用预定义的失效模式清理缓存
        String pattern = redisKeyManager.generateEvictTestCasesPattern();
        redisKeyManager.deleteKeysByPattern(pattern);
    }
}
```

### 4. 使用注解

```java
@RestController
@CacheConfig(cacheNames = "users", keyPrefix = "user_service")
public class UserController {
    
    @GetMapping("/users")
    @ListCache(expireTime = 1800, maxCachePages = 5)
    public Page<User> findUsers(Pageable pageable) {
        // 查询逻辑 - 前5页会被自动缓存
        return userService.findAll(pageable);
    }
    
    @PostMapping("/users")
    @CacheEvict(keyPattern = "users:*")
    public User createUser(@RequestBody User user) {
        // 创建逻辑 - 会自动清除相关缓存
        return userService.create(user);
    }
    
    @PutMapping("/users/{id}")
    @CacheEvict(keyPattern = "users:*", condition = "#result != null")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // 更新逻辑 - 成功时清除缓存
        return userService.update(id, user);
    }
    
    @DeleteMapping("/users/{id}")
    @CacheEvict(allEntries = true)
    public void deleteUser(@PathVariable Long id) {
        // 删除逻辑 - 清除所有相关缓存
        userService.delete(id);
    }
}
```

## 详细配置

### 基础配置

```yaml
redis:
  list:
    cache:
      # 基础设置
      enabled: true                    # 是否启用缓存
      default-expire-time: 1800        # 默认过期时间（秒）
      max-cache-pages: 5               # 最大缓存页数
      key-prefix: "list_cache"         # 缓存键前缀
      tenant-aware: true               # 是否启用租户隔离
      serialization-type: JSON         # 序列化方式
```

### 监控配置

```yaml
redis:
  list:
    cache:
      monitor:
        enabled: true                  # 启用监控
        metrics-interval: 60           # 指标收集间隔（秒）
        verbose-logging: false         # 详细日志
        slow-query-threshold: 1000     # 慢查询阈值（毫秒）
        jmx-enabled: false            # JMX监控
```

### 降级配置

```yaml
redis:
  list:
    cache:
      fallback:
        enabled: true                  # 启用降级
        timeout: 200                   # 超时时间（毫秒）
        max-retries: 3                # 最大重试次数
        retry-interval: 100           # 重试间隔（毫秒）
        circuit-breaker:
          enabled: true               # 启用熔断器
          failure-threshold: 5        # 失败阈值
          recovery-time: 60          # 恢复时间（秒）
```

### 性能配置

```yaml
redis:
  list:
    cache:
      performance:
        async-enabled: false          # 异步缓存
        async-thread-pool-size: 5     # 异步线程池大小
        batch-size: 100              # 批量操作大小
        compression-enabled: false    # 启用压缩
        compression-threshold: 1024   # 压缩阈值（字节）
```

## 注解详解

### @ListCache 注解

用于标记需要缓存的列表查询方法。

```java
@ListCache(
    value = "users",                    // 缓存名称
    keyPrefix = "user_service",         // 键前缀
    expireTime = 1800,                 // 过期时间（秒）
    maxCachePages = 5,                 // 最大缓存页数
    tenantAware = true,                // 租户隔离
    condition = "#pageable.pageNumber < 5",  // 缓存条件
    unless = "#result.empty",          // 排除条件
    sync = false                       // 是否同步执行
)
```

**参数说明：**
- `value`: 缓存名称，默认使用类名+方法名
- `keyPrefix`: 缓存键前缀，用于区分不同业务
- `expireTime`: 过期时间（秒），默认30分钟
- `maxCachePages`: 最大缓存页数，默认5页
- `tenantAware`: 是否启用租户隔离，默认true
- `condition`: 缓存条件SpEL表达式
- `unless`: 排除缓存条件SpEL表达式
- `sync`: 是否同步执行缓存操作

### @CacheEvict 注解

用于标记需要清除缓存的方法。

```java
@CacheEvict(
    value = {"users", "roles"},         // 要清除的缓存名称
    keyPattern = "users:*",            // 键模式匹配
    allEntries = false,                // 是否清除所有
    condition = "#result != null",     // 清除条件
    timing = EvictTiming.AFTER,        // 清除时机
    ignoreExceptions = true,           // 忽略异常
    timeout = 100                      // 超时时间（毫秒）
)
```

**参数说明：**
- `value`: 要清除的缓存名称数组
- `keyPattern`: 缓存键模式，支持通配符
- `allEntries`: 是否清除所有相关缓存
- `condition`: 清除条件SpEL表达式
- `timing`: 清除时机（BEFORE/AFTER/BOTH）
- `ignoreExceptions`: 是否忽略清除异常
- `timeout`: 清除超时时间

### @CacheConfig 注解

用于在类级别定义缓存的全局配置。

```java
@CacheConfig(
    cacheNames = {"users", "roles"},    // 默认缓存名称
    keyPrefix = "user_service",         // 默认键前缀
    expireTime = 1800,                 // 默认过期时间
    maxCachePages = 5,                 // 默认最大缓存页数
    tenantAware = true                 // 默认租户隔离设置
)
```

## Redis Key 统一管理

### RedisKeyConstants - 统一键管理

所有Redis缓存键都定义在 `RedisKeyConstants` 类中，避免散落各处：

```java
public final class RedisKeyConstants {
    
    // 列表缓存相关
    public static final String TEST_CASES_LIST = "test_cases_list";
    public static final String BUGS_LIST = "bugs_list";
    public static final String USERS_LIST = "users_list";
    
    // 任务进度相关
    public static final String TASK_PROGRESS = "task:progress";
    
    // 文件导入导出相关
    public static final String IMPORT_EXPORT_QUEUE = "import_export:queue";
    public static final String FILE_TEMP = "file:temp";
    
    // 序号生成器相关
    public static final String SEQUENCE_GENERATOR = "sequence:generator";
    
    // 用户会话相关
    public static final String USER_SESSION = "user:session";
    public static final String USER_PERMISSIONS = "user:permissions";
    
    // 工具方法
    public static String buildKey(String tenant, String prefix, String suffix) {
        if (tenant == null || tenant.trim().isEmpty()) {
            tenant = "default";
        }
        return tenant + ":" + prefix + ":" + suffix;
    }
    
    public static String buildListCacheKey(String tenant, String listType, String params, int page, int size) {
        return buildKey(tenant, "cache_test", listType + ":" + params + ":page_" + page + "_size_" + size);
    }
    
    public static String buildTaskProgressKey(String tenant, String taskId) {
        return buildKey(tenant, TASK_PROGRESS, taskId);
    }
}
```

### 使用示例

```java
// 生成列表缓存键
String key = RedisKeyConstants.buildListCacheKey("tenant123", "test_cases_list", "no_params", 0, 10);
// 结果: tenant123:cache_test:test_cases_list:no_params:page_0_size_10

// 生成任务进度键  
String progressKey = RedisKeyConstants.buildTaskProgressKey("csv", "005601c4-b087-4375-924e-a68d83a9de77");
// 结果: csv:task:progress:005601c4-b087-4375-924e-a68d83a9de77

// 生成自定义键
String customKey = RedisKeyConstants.buildKey("tenant123", "user:session", "user456");
// 结果: tenant123:user:session:user456
```

## 监控和管理

### 获取缓存统计

```java
@Autowired
private ListCacheManager cacheManager;

@GetMapping("/cache/stats")
public CacheStats getCacheStats() {
    return cacheManager.getStats();
}
```

### 手动清除缓存

```java
// 清除指定模式的缓存
long cleared = cacheManager.deleteByPattern("users:*");

// 清除所有缓存
boolean success = cacheManager.clear();
```

### 健康检查

```java
// 检查缓存健康状态
boolean healthy = cacheManager.isHealthy();

// 测试连接
boolean connected = cacheManager.testConnection();
```

## 最佳实践

### 1. 过期时间设置

根据数据更新频率设置合适的过期时间：

```java
// 高频更新数据 - 5-15分钟
@ListCache(expireTime = 900)  // 15分钟

// 中频更新数据 - 30分钟
@ListCache(expireTime = 1800) // 30分钟

// 低频更新数据 - 1-2小时
@ListCache(expireTime = 7200) // 2小时
```

### 2. 缓存页数控制

只缓存用户最常访问的页面：

```java
@ListCache(
    maxCachePages = 5,  // 只缓存前5页
    condition = "#pageable.pageNumber < 5"
)
```

### 3. 智能缓存清除

根据业务逻辑选择合适的清除策略：

```java
// 创建操作 - 清除列表缓存
@CacheEvict(keyPattern = "users:*")

// 更新操作 - 条件清除
@CacheEvict(keyPattern = "users:*", condition = "#result != null")

// 删除操作 - 清除所有相关缓存
@CacheEvict(allEntries = true)

// 批量操作 - 清除所有
@CacheEvict(allEntries = true, timing = EvictTiming.AFTER)
```

### 4. 租户隔离

在多租户环境中确保数据隔离：

```java
@CacheConfig(tenantAware = true)  // 类级别启用
@ListCache(tenantAware = true)    // 方法级别启用
```

### 5. 条件缓存

使用SpEL表达式实现智能缓存：

```java
// 只缓存前5页
@ListCache(condition = "#pageable.pageNumber < 5")

// 不缓存空结果
@ListCache(unless = "#result.empty")

// 只在特定条件下清除缓存
@CacheEvict(condition = "#result.success")
```

## 性能优化

### 1. 缓存预热

```java
@PostConstruct
public void warmUpCache() {
    // 预热常用查询
    for (int page = 0; page < 3; page++) {
        findUsers(PageRequest.of(page, 20));
    }
}
```

### 2. 批量操作优化

```java
@CacheEvict(allEntries = true)
public void batchUpdateUsers(List<User> users) {
    // 批量操作后清除所有缓存
    userRepository.saveAll(users);
}
```

### 3. 异步缓存

```yaml
redis:
  list:
    cache:
      performance:
        async-enabled: true
        async-thread-pool-size: 5
```

## 故障排查

### 常见问题

#### 1. 缓存未生效

**症状：** 每次查询都访问数据库，缓存命中率为0

**排查步骤：**
```bash
# 检查Redis连接
redis-cli ping

# 查看缓存键
redis-cli keys "*list_cache*"

# 检查应用日志
grep "Cache" application.log
```

**解决方案：**
- 检查Redis连接配置
- 确认注解是否正确添加
- 验证AOP是否生效

#### 2. 内存占用过高

**症状：** Redis内存使用率持续上升

**排查步骤：**
```bash
# 查看内存使用情况
redis-cli info memory

# 查看键的数量
redis-cli dbsize

# 查看键的TTL
redis-cli ttl "your_cache_key"
```

**解决方案：**
- 减少缓存页数
- 缩短过期时间
- 及时清理无用缓存

#### 3. 性能问题

**症状：** 缓存响应时间过长

**排查步骤：**
```java
// 查看缓存统计
CacheStats stats = cacheManager.getStats();
System.out.println("Hit rate: " + stats.getHitRate());
System.out.println("Avg response time: " + stats.getAverageResponseTime());
```

**解决方案：**
- 优化缓存键设计
- 调整序列化方式
- 使用Redis集群

### 日志配置

```yaml
logging:
  level:
    com.common.redis.cache: DEBUG
    org.redisson: INFO
```

### 监控告警

建议监控以下指标：
- 缓存命中率 < 70%
- 平均响应时间 > 100ms
- 错误率 > 5%
- Redis连接失败

## 使用方式

直接使用 `RedisKeyConstants` 类中的常量和工具方法即可：

```java
@Service
public class YourService {
    
    public void cacheTestCases() {
        // 生成测试用例列表缓存键
        String key = RedisKeyConstants.buildListCacheKey("tenant123", 
                RedisKeyConstants.TEST_CASES_LIST, "no_params", 0, 10);
        
        // 存储到Redis
        // redisTemplate.opsForValue().set(key, data, Duration.ofMinutes(30));
    }
    
    public void trackTaskProgress(String taskId) {
        // 生成任务进度键
        String progressKey = RedisKeyConstants.buildTaskProgressKey("csv", taskId);
        
        // 更新进度
        // redisTemplate.opsForValue().set(progressKey, progress, Duration.ofHours(1));
    }
}

## 版本历史

### v1.0.0 (2024-01-01)
- 初始版本发布
- 支持基础的列表缓存功能
- 支持租户隔离
- 支持自动缓存失效
- 支持监控和统计
- 新增Redis Key统一管理 - RedisKeyConstants类

## 许可证

Apache License 2.0

## 贡献

欢迎提交Issue和Pull Request来改进这个项目。

## 联系方式

如有问题，请通过以下方式联系：
- 提交Issue: [GitHub Issues](https://github.com/your-repo/redis-list-cache-starter/issues)
- 邮箱: your-email@example.com