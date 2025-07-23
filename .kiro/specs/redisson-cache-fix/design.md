# 设计文档

## 概述

本设计文档描述了修复RedissonTenantCacheService类中编译错误的方案。主要解决两类问题：
1. 修复使用过时的Redisson API `expire(long, TimeUnit)` 方法
2. 解决缺少 `tenantDbInfoService` 变量引用的问题

## 架构

RedissonTenantCacheService是租户路由模块中的一个关键服务，负责管理租户信息的缓存。它使用Redisson客户端与Redis交互，存储和检索租户数据库信息。该服务需要与TenantDbInfoService交互以获取租户数据库信息。

### 当前架构问题

1. **过时API调用**：Redisson API中的`expire(long, TimeUnit)`方法已被标记为过时，需要替换为新的推荐方法。
2. **缺少服务引用**：代码中直接引用了`tenantDbInfoService`变量，但该变量未在类中定义或注入。

## 组件和接口

### 需要修改的组件

1. **RedissonTenantCacheService**
   - 修复过时的`expire()`方法调用
   - 添加对`TenantDbInfoService`的正确引用

### 接口

不需要修改任何接口，我们只需确保RedissonTenantCacheService正确实现其现有功能。

## 数据模型

不需要修改任何数据模型。

## 解决方案设计

### 1. 修复过时的Redisson API调用

根据Redisson的最新API文档，`expire(long, TimeUnit)`方法已被弃用，应替换为`expireAsync(long, TimeUnit)`或`expire(Duration)`方法。考虑到代码的异步性质和现代Java API的使用，我们将使用`expire(Duration)`方法作为替代。

需要修改的代码位置：
- 第185行：`bucket.expire(getCacheExpireTime(), TimeUnit.SECONDS);`
- 第336行：`bucket.expire(getCacheExpireTime(), TimeUnit.SECONDS);`
- 第342行：`redissonClient.getBucket(TENANT_IDS_KEY).expire(getCacheExpireTime(), TimeUnit.SECONDS);`
- 第343行：`redissonClient.getBucket(TENANT_LOADED_FLAG).expire(getCacheExpireTime(), TimeUnit.SECONDS);`

### 2. 解决缺少tenantDbInfoService变量引用的问题

当前代码使用了ApplicationContext来延迟加载TenantDbInfoService以避免循环依赖，但在调用`tenantDbInfoService`时直接使用了变量而不是通过getter方法。我们需要修改这些调用，使用`getTenantDbInfoService()`方法来获取服务实例。

需要修改的代码位置：
- 第83行：`List<TenantDbInfo> allTenants = tenantDbInfoService.findAll();`
- 第267行：`List<TenantDbInfo> dbTenants = tenantDbInfoService.findAll();`
- 第355行：`TenantDbInfo tenantDbInfo = tenantDbInfoService.findByTenantId(tenantId);`
- 第370行：`List<TenantDbInfo> allTenants = tenantDbInfoService.findAll();`

## 错误处理

不需要修改现有的错误处理逻辑，因为我们只是修复编译错误，不改变功能行为。

## 测试策略

1. **单元测试**：
   - 测试修改后的RedissonTenantCacheService是否能正确编译
   - 测试所有使用过时API的方法是否正常工作
   - 测试所有使用tenantDbInfoService的方法是否正常工作

2. **集成测试**：
   - 测试RedissonTenantCacheService与TenantDbInfoService的交互
   - 测试缓存操作是否正确执行
   - 测试过期时间设置是否正确

## 实现注意事项

1. 需要导入`java.time.Duration`类以使用新的API
2. 确保所有对`tenantDbInfoService`的直接引用都替换为`getTenantDbInfoService()`方法调用
3. 保持代码的异步特性，确保性能不受影响