# 实现计划

- [x] 1. 分析RedissonTenantCacheService类中的过时API调用


  - 确认所有使用过时expire()方法的位置
  - 确定最合适的替代方法
  - _需求: 1.1, 1.2, 1.3_

- [x] 2. 修复过时的Redisson API调用


  - [x] 2.1 导入java.time.Duration类


    - 在RedissonTenantCacheService类中添加Duration导入
    - _需求: 1.1, 1.3_
  
  - [x] 2.2 替换preloadTenants方法中的过时expire调用


    - 将`loadedFlag.set("true", getCacheExpireTime(), TimeUnit.SECONDS);`中的过期时间设置修改为使用Duration
    - _需求: 1.1, 1.2_
  
  - [x] 2.3 替换getTenant方法中的过时expire调用


    - 将`bucket.expire(getCacheExpireTime(), TimeUnit.SECONDS);`修改为使用Duration
    - _需求: 1.1, 1.2_
  
  - [x] 2.4 替换refreshAllTenantsExpiration方法中的过时expire调用


    - 将所有`bucket.expire(getCacheExpireTime(), TimeUnit.SECONDS);`修改为使用Duration
    - 将`redissonClient.getBucket(TENANT_IDS_KEY).expire`和`redissonClient.getBucket(TENANT_LOADED_FLAG).expire`修改为使用Duration
    - _需求: 1.1, 1.2_
  
  - [x] 2.5 替换refreshAllTenantCache方法中的过时expire调用


    - 将`loadedFlag.set("true", getCacheExpireTime(), TimeUnit.SECONDS);`中的过期时间设置修改为使用Duration
    - _需求: 1.1, 1.2_

- [x] 3. 分析tenantDbInfoService变量引用问题


  - 确认所有直接使用tenantDbInfoService变量的位置
  - 确认getTenantDbInfoService()方法的实现是否正确
  - _需求: 2.1, 2.2, 2.3_

- [x] 4. 修复tenantDbInfoService变量引用


  - [x] 4.1 修复loadAllTenantsToCache方法中的引用


    - 将`tenantDbInfoService.findAll()`替换为`getTenantDbInfoService().findAll()`
    - _需求: 2.1, 2.2_
  
  - [x] 4.2 修复refreshTenantCache方法中的引用


    - 将`tenantDbInfoService.findByTenantId(tenantId)`替换为`getTenantDbInfoService().findByTenantId(tenantId)`
    - _需求: 2.1, 2.2_
  
  - [x] 4.3 修复refreshTenantCache和refreshAllTenantCache方法中的引用


    - 将所有`tenantDbInfoService`引用替换为`getTenantDbInfoService()`调用
    - _需求: 2.1, 2.2_




- [ ] 5. 编译和测试修复
  - [ ] 5.1 编译项目验证修复
    - 确保没有编译错误和警告
    - _需求: 1.2, 2.2, 3.1_
  
  - [ ] 5.2 编写单元测试验证功能
    - 测试缓存操作是否正常工作
    - 测试过期时间设置是否正确
    - _需求: 3.1, 3.2, 3.3, 3.4_