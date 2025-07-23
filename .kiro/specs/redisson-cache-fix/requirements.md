# 需求文档

## 介绍

tenant-routing-starter模块中的RedissonTenantCacheService类存在编译错误，需要修复。这些错误包括对Redisson的expire()方法的过时调用以及缺少对tenantDbInfoService变量的引用。此功能旨在修复这些问题，以确保租户缓存服务正常工作并遵循最佳实践。

## 需求

### 需求1

**用户故事:** 作为开发人员，我希望修复过时的Redisson API调用，以便代码使用最新推荐的方法并避免过时警告。

#### 验收标准

1. 当RedissonTenantCacheService调用RExpirable对象上的expire()方法时，系统应使用非过时的替代方法。
2. 当代码编译时，系统不应产生与Redisson API调用相关的任何过时警告。
3. 如果Redisson API发生重大变化，系统应适应使用适当的替代方法。

### 需求2

**用户故事:** 作为开发人员，我希望修复缺少的tenantDbInfoService变量引用，以便RedissonTenantCacheService可以正确地与租户数据库信息交互。

#### 验收标准

1. 当RedissonTenantCacheService需要访问TenantDbInfoService时，系统应正确注入和引用该服务。
2. 当代码编译时，系统不应产生与tenantDbInfoService相关的任何"找不到变量"错误。
3. 如果存在循环依赖问题，系统应使用适当的依赖注入技术来解决它们。

### 需求3

**用户故事:** 作为开发人员，我希望确保在修复问题的同时RedissonTenantCacheService保持其功能，以便租户缓存继续正确工作。

#### 验收标准

1. 当使用RedissonTenantCacheService时，系统应保持所有现有功能。
2. 当缓存租户信息时，系统应正确存储和检索信息。
3. 当刷新租户信息时，系统应正确更新缓存。
4. 如果需要设置缓存过期时间，系统应使用正确的方法来设置它。