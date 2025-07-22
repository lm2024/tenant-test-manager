# 多微服务租户路由集成指南

## 1. 架构概述

本方案通过共享组件库 `tenant-routing-starter` 实现多微服务的租户动态路由，主要特点：

- 统一的租户识别和路由逻辑
- 自动从请求头获取租户ID
- 支持注解方式指定租户
- 支持跨服务调用时传递租户上下文
- 自动加载和管理租户数据源

## 2. 组件安装

### 2.1 编译安装共享组件
```bash
cd tenant-routing-starter
mvn clean install
```

### 2.2 在各微服务中添加依赖
在每个微服务的pom.xml中添加：
```xml
<dependency>
    <groupId>com.tenant</groupId>
    <artifactId>tenant-routing-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 3. 微服务配置

### 3.1 my-bugs 微服务配置
```yaml
# my-bugs/src/main/resources/application.yml
server:
  port: 8081

spring:
  application:
    name: my-bugs-service

tenant:
  routing:
    enabled: true
    header-name: X-Tenant-ID
    required: true
    exclude-paths: 
      - /swagger-ui.html
      - /doc.html
      - /actuator/**
    tenant-center:
      url: jdbc:mysql://localhost:3306/tenant_center
      username: root
      password: root
```

### 3.2 my-test-execute 微服务配置
```yaml
# my-test-execute/src/main/resources/application.yml
server:
  port: 8082

spring:
  application:
    name: my-test-execute-service

tenant:
  routing:
    enabled: true
    header-name: X-Tenant-ID
    required: true
    exclude-paths: 
      - /swagger-ui.html
      - /doc.html
      - /actuator/**
    tenant-center:
      url: jdbc:mysql://localhost:3306/tenant_center
      username: root
      password: root
```

### 3.3 my-function-demand 微服务配置
```yaml
# my-function-demand/src/main/resources/application.yml
server:
  port: 8083

spring:
  application:
    name: my-function-demand-service

tenant:
  routing:
    enabled: true
    header-name: X-Tenant-ID
    required: true
    exclude-paths: 
      - /swagger-ui.html
      - /doc.html
      - /actuator/**
    tenant-center:
      url: jdbc:mysql://localhost:3306/tenant_center
      username: root
      password: root
```

### 3.4 my-product-tenant 微服务配置
```yaml
# my-product-tenant/src/main/resources/application.yml
server:
  port: 8084

spring:
  application:
    name: my-product-tenant-service

tenant:
  routing:
    enabled: true
    header-name: X-Tenant-ID
    required: false  # 租户管理服务可以不强制要求租户ID
    exclude-paths: 
      - /swagger-ui.html
      - /doc.html
      - /actuator/**
      - /api/tenant/**  # 租户管理接口排除
    tenant-center:
      url: jdbc:mysql://localhost:3306/tenant_center
      username: root
      password: root
```

## 4. 使用示例

### 4.1 Controller示例
```java
@RestController
@RequestMapping("/api/bugs")
public class BugController {
    
    @Autowired
    private BugService bugService;
    
    // 自动从请求头获取租户ID进行路由
    @GetMapping("/list")
    public List<Bug> getBugs() {
        return bugService.findAll();
    }
    
    // 使用注解指定特定租户
    @TenantSwitch("tenant001")
    @GetMapping("/admin/stats")
    public BugStats getAdminStats() {
        return bugService.getStats();
    }
}
```

### 4.2 Service示例
```java
@Service
public class BugService {
    
    @Autowired
    private BugMapper bugMapper;
    
    // 会自动使用当前租户的数据源
    public List<Bug> findAll() {
        return bugMapper.selectAll();
    }
    
    // 可以在Service层也使用租户切换
    @TenantSwitch("system")
    public void syncSystemData() {
        // 这里会切换到system租户执行
        bugMapper.syncData();
    }
}
```

### 4.3 Feign客户端配置
```java
@FeignClient(name = "my-bugs-service", configuration = TenantFeignConfig.class)
public interface BugServiceClient {
    
    @GetMapping("/api/bugs/list")
    List<Bug> getBugs();
}

@Configuration
public class TenantFeignConfig {
    
    @Bean
    public RequestInterceptor tenantRequestInterceptor() {
        return template -> {
            String tenantId = TenantContextHolder.getTenantId();
            if (tenantId != null) {
                template.header("X-Tenant-ID", tenantId);
            }
        };
    }
}
```

## 5. 租户管理接口

在 my-product-tenant 微服务中实现租户管理接口：

```java
@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    
    @Autowired
    private TenantService tenantService;
    
    @PostMapping
    public ResponseEntity<?> createTenant(@RequestBody TenantDTO tenant) {
        return ResponseEntity.ok(tenantService.createTenant(tenant));
    }
    
    @GetMapping
    public ResponseEntity<?> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }
    
    @GetMapping("/{tenantId}")
    public ResponseEntity<?> getTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(tenantService.getTenant(tenantId));
    }
    
    @PutMapping("/{tenantId}")
    public ResponseEntity<?> updateTenant(@PathVariable String tenantId, @RequestBody TenantDTO tenant) {
        return ResponseEntity.ok(tenantService.updateTenant(tenantId, tenant));
    }
    
    @DeleteMapping("/{tenantId}")
    public ResponseEntity<?> deleteTenant(@PathVariable String tenantId) {
        tenantService.deleteTenant(tenantId);
        return ResponseEntity.ok().build();
    }
}
```

## 6. 启动顺序

1. 先启动 my-product-tenant (租户管理服务)
2. 再启动其他业务微服务
3. 通过租户管理服务创建租户后，其他服务会自动加载新的租户配置

## 7. 测试验证

### 7.1 测试脚本
```bash
# 测试bugs服务
curl -X GET "http://localhost:8081/api/bugs/list" \
  -H "X-Tenant-ID: tenant001"

# 测试test-execute服务  
curl -X GET "http://localhost:8082/api/test/execute" \
  -H "X-Tenant-ID: tenant001"

# 测试function-demand服务
curl -X GET "http://localhost:8083/api/demand/list" \
  -H "X-Tenant-ID: tenant001"
```

## 8. 监控和日志

每个微服务都会自动记录租户切换日志，可以通过日志追踪租户路由情况：

```
[Tenant:tenant001] Switching to tenant database
[Tenant:tenant001] SQL executed: SELECT * FROM bugs
```

## 9. 性能优化建议

1. **连接池配置**: 根据业务量调整连接池大小
2. **缓存策略**: 租户配置会自动缓存，避免重复查询
3. **监控指标**: 可通过JMX监控连接池状态

## 10. 安全考虑

1. **租户隔离**: 确保租户间数据完全隔离
2. **权限验证**: 建议在业务层添加租户权限验证
3. **SQL注入**: 使用PreparedStatement防止SQL注入