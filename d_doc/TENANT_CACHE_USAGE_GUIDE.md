# 租户缓存功能使用指南

## 概述

本文档介绍如何使用增强后的Redis列表缓存功能，该功能现在支持租户隔离，确保不同租户的数据在缓存中完全隔离。

## 主要功能

### 1. 租户感知的缓存
- 缓存键自动包含租户信息
- 不同租户的数据完全隔离
- 支持租户上下文自动获取

### 2. 灵活的租户ID获取
- 支持请求参数传递租户ID
- 支持从租户上下文自动获取
- 优先级：请求参数 > 上下文 > null

### 3. 完整的CRUD操作
- 查询：支持分页、过滤、缓存
- 创建：单个和批量创建
- 更新：自动清除相关缓存
- 删除：自动清除相关缓存

## API接口说明

### 基础查询接口

#### 1. 查询测试用例列表（带缓存）
```http
GET /api/cache-test/test-cases?tenantId=tenant001&status=active&priority=high&page=0&size=10
```

**参数说明：**
- `tenantId`（可选）：租户ID，不传则使用当前上下文租户
- `status`（可选）：状态过滤
- `priority`（可选）：优先级过滤
- `page`：页码（从0开始）
- `size`：每页大小

#### 2. 查询测试用例列表（不使用缓存）
```http
GET /api/cache-test/test-cases/no-cache?tenantId=tenant001
```

### 数据操作接口

#### 3. 创建测试用例
```http
POST /api/cache-test/test-cases?tenantId=tenant001
Content-Type: application/json

{
    "name": "测试用例1",
    "description": "这是一个测试用例",
    "status": "active",
    "priority": "high"
}
```

#### 4. 批量创建测试用例
```http
POST /api/cache-test/test-cases/batch?tenantId=tenant001
Content-Type: application/json

[
    {
        "name": "测试用例1",
        "description": "这是一个测试用例",
        "status": "active",
        "priority": "high"
    },
    {
        "name": "测试用例2",
        "description": "这是另一个测试用例",
        "status": "inactive",
        "priority": "medium"
    }
]
```

### 租户管理接口

#### 5. 设置租户上下文
```http
POST /api/cache-test/tenant/context?tenantId=tenant001
```

#### 6. 获取租户上下文
```http
GET /api/cache-test/tenant/context
```

#### 7. 清除租户上下文
```http
DELETE /api/cache-test/tenant/context
```

#### 8. 获取租户统计信息
```http
GET /api/cache-test/tenant/stats?tenantId=tenant001
```

### 缓存管理接口

#### 9. 获取缓存统计信息
```http
GET /api/cache-test/cache/stats
```

#### 10. 清除指定租户的缓存
```http
DELETE /api/cache-test/cache/tenant/tenant001
```

#### 11. 预热缓存
```http
POST /api/cache-test/cache/warmup?tenantId=tenant001&pages=5
```

## 演示接口

### 租户缓存演示
```http
POST /api/tenant-cache-demo/demo?tenantId=tenant001
```

这个接口会执行完整的演示流程：
1. 设置租户上下文
2. 创建测试数据
3. 执行多次查询测试缓存效果
4. 获取统计信息
5. 清除缓存并重新测试

### 多租户并发测试
```http
POST /api/tenant-cache-demo/multi-tenant-test?tenantIds=tenant001,tenant002,tenant003
```

### 缓存性能测试
```http
POST /api/tenant-cache-demo/performance-test?tenantId=tenant001&iterations=10
```

## 使用示例

### Java代码示例

```java
@RestController
public class MyController {
    
    @Autowired
    private RedisListCacheTestController cacheController;
    
    public void example() {
        // 方式1：通过参数传递租户ID
        Page<TestCase> result1 = cacheController.getTestCases("tenant001", null, null, 
            PageRequest.of(0, 10));
        
        // 方式2：通过上下文设置租户ID
        TenantContextHolder.setTenantId("tenant001");
        Page<TestCase> result2 = cacheController.getTestCases(null, null, null, 
            PageRequest.of(0, 10));
        TenantContextHolder.clear();
    }
}
```

### 缓存配置说明

控制器使用了以下缓存配置：
```java
@CacheConfig(
    cacheNames = "test_cases", 
    keyPrefix = "cache_test", 
    expireTime = 600,  // 10分钟过期
    maxCachePages = 5, // 最多缓存5页
    tenantAware = true // 启用租户感知
)
```

### 缓存键格式

缓存键的格式为：`{keyPrefix}:{cacheName}:tenant:{tenantId}:{queryParams}:{pageInfo}`

例如：`cache_test:test_cases_list:tenant:tenant001:status=active:page=0:size=10`

## 最佳实践

### 1. 租户ID管理
- 在请求开始时设置租户上下文
- 在请求结束时清除租户上下文
- 使用拦截器或过滤器自动管理租户上下文

### 2. 缓存策略
- 只缓存前几页数据（通过maxCachePages控制）
- 设置合适的过期时间
- 在数据变更时及时清除相关缓存

### 3. 性能优化
- 使用批量操作减少数据库访问
- 合理设置缓存大小和过期时间
- 监控缓存命中率和性能指标

### 4. 错误处理
- 处理租户ID为空的情况
- 处理缓存服务不可用的情况
- 提供降级方案

## 监控和运维

### 缓存监控指标
- 缓存命中率
- 查询响应时间
- 缓存大小和内存使用
- 错误率和降级次数

### 运维操作
- 定期清理过期缓存
- 监控Redis连接状态
- 备份重要的缓存配置
- 性能调优和容量规划

## 注意事项

1. **数据一致性**：缓存更新策略要确保数据一致性
2. **内存管理**：合理设置缓存大小，避免内存溢出
3. **租户隔离**：确保不同租户的数据完全隔离
4. **性能影响**：缓存操作本身也有开销，要权衡利弊
5. **故障恢复**：Redis故障时要有降级方案

## 故障排查

### 常见问题

1. **缓存未命中**
   - 检查租户ID是否正确
   - 检查缓存键格式
   - 检查缓存过期时间

2. **数据不一致**
   - 检查缓存清除逻辑
   - 检查数据更新流程
   - 检查并发访问控制

3. **性能问题**
   - 检查缓存命中率
   - 检查Redis连接状态
   - 检查查询复杂度

### 调试工具

- 使用`/api/cache-test/cache/stats`查看缓存统计
- 使用`/api/cache-test/cache/keys`查看缓存键
- 使用`/api/cache-test/cache/test-connection`测试连接
- 使用演示接口进行功能验证