# 租户路由组件使用指南

## 1. 快速开始

### 1.1 安装组件
```bash
cd tenant-routing-starter
mvn clean install
```

### 1.2 在微服务中添加依赖
```xml
<dependency>
    <groupId>com.tenant</groupId>
    <artifactId>tenant-routing-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 1.3 配置文件
```yaml
# application.yml
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

## 2. 使用方式

### 2.1 HTTP请求
```bash
curl -X GET "http://localhost:8080/api/data" \
  -H "X-Tenant-ID: tenant001"
```

### 2.2 注解方式
```java
@RestController
public class DataController {
    
    // 自动从请求头获取租户ID
    @GetMapping("/api/data")
    public List<Data> getData() {
        return dataService.findAll();
    }
    
    // 指定特定租户
    @TenantSwitch("tenant001")
    @GetMapping("/api/admin/data")
    public List<Data> getAdminData() {
        return dataService.findAll();
    }
}
```

### 2.3 Feign调用配置
```java
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

## 3. 配置说明

| 配置项 | 默认值 | 说明 |
|--------|--------|------|
| tenant.routing.enabled | true | 是否启用租户路由 |
| tenant.routing.header-name | X-Tenant-ID | 租户ID请求头名称 |
| tenant.routing.required | false | 是否必需租户ID |
| tenant.routing.exclude-paths | 见配置 | 排除的路径 |
| tenant.routing.max-pool-size | 10 | 连接池最大连接数 |
| tenant.routing.min-idle | 2 | 连接池最小空闲连接数 |

## 4. 故障排查

### 4.1 常见问题
1. **租户ID未传递**: 检查请求头是否包含租户ID
2. **数据源创建失败**: 检查数据库连接配置
3. **租户配置未加载**: 检查tenant_center数据库连接

### 4.2 日志查看
```
# 启动日志
Tenant configurations loaded successfully

# 运行时日志
[Tenant:tenant001] Switching to tenant database
```

## 5. 性能优化

1. **连接池配置**: 根据业务量调整连接池大小
2. **缓存策略**: 租户配置会自动缓存，避免重复查询
3. **监控指标**: 可通过JMX监控连接池状态

## 6. 安全考虑

1. **租户隔离**: 确保租户间数据完全隔离
2. **权限验证**: 建议在业务层添加租户权限验证
3. **SQL注入**: 使用PreparedStatement防止SQL注入