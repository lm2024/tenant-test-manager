# 租户切换功能使用说明

## 概述

本项目提供了两种租户切换方式：
1. **@TenantSwitchHeader注解** - 针对特定方法的租户切换
2. **全局租户拦截器** - 自动为所有请求处理租户切换

## 1. @TenantSwitchHeader注解

### 基本用法

```java
@GetMapping("/list")
@TenantSwitchHeader(headerName = "X-Tenant-ID")
public List<TestCase> list() {
    return repository.findAll();
}
```

### 参数说明

- `headerName`: 请求头名称，默认为 "X-Tenant-ID"
- `required`: 是否必需租户ID，默认为true

### 使用示例

```java
// 基本用法
@TenantSwitchHeader(headerName = "X-Tenant-ID")
public void method1() {}

// 自定义请求头名称
@TenantSwitchHeader(headerName = "Custom-Tenant-ID")
public void method2() {}

// 非必需租户ID
@TenantSwitchHeader(headerName = "X-Tenant-ID", required = false)
public void method3() {}
```

## 2. 全局租户拦截器

### 配置启用

在 `application.yml` 中配置：

```yaml
tenant:
  global-interceptor:
    enabled: true  # 启用全局租户拦截器
    header-name: X-Tenant-ID  # 租户ID请求头名称
    required: true  # 是否必需租户ID
    exclude-paths: /api/tenant/**,/swagger-ui.html,/doc.html,/druid/**  # 排除的路径
```

### 功能特性

- ✅ 自动从请求头获取租户ID
- ✅ 支持多种常见请求头名称
- ✅ 可配置排除路径
- ✅ 可配置是否必需租户ID
- ✅ 自动清理租户上下文

## 3. 支持的请求头名称

系统会自动尝试以下请求头名称：

1. `X-Tenant-ID` (默认)
2. `X-TenantId`
3. `X-Tenant`
4. `tenant-id`
5. `tenantId`
6. `tenant`
7. `X-User-Tenant`
8. `User-Tenant`

## 4. 使用示例

### 4.1 使用注解方式

```bash
# 请求示例
curl -X GET "http://localhost:8080/api/example/with-annotation" \
  -H "X-Tenant-ID: tenant001"
```

### 4.2 使用全局拦截器

```bash
# 请求示例
curl -X GET "http://localhost:8080/api/example/global-interceptor" \
  -H "X-Tenant-ID: tenant001"
```

### 4.3 自定义请求头

```bash
# 请求示例
curl -X GET "http://localhost:8080/api/example/custom-header" \
  -H "Custom-Tenant-ID: tenant001"
```

## 5. 错误处理

### 5.1 缺少租户ID

```json
{
  "error": "请求头缺少租户ID: X-Tenant-ID"
}
```

### 5.2 租户不存在

```json
{
  "error": "租户不存在: tenant001"
}
```

## 6. 配置说明

### 6.1 注解配置

```java
// 必需租户ID（默认）
@TenantSwitchHeader(headerName = "X-Tenant-ID", required = true)

// 可选租户ID
@TenantSwitchHeader(headerName = "X-Tenant-ID", required = false)
```

### 6.2 全局拦截器配置

```yaml
tenant:
  global-interceptor:
    enabled: true                    # 是否启用
    header-name: X-Tenant-ID        # 请求头名称
    required: true                   # 是否必需
    exclude-paths: /api/tenant/**   # 排除路径
```

## 7. 最佳实践

### 7.1 选择使用方式

- **注解方式**: 适用于需要精确控制的特定接口
- **全局拦截器**: 适用于大部分接口都需要租户切换的场景

### 7.2 排除路径配置

建议排除以下路径：
- `/api/tenant/**` - 租户管理接口
- `/swagger-ui.html` - Swagger文档
- `/doc.html` - Knife4j文档
- `/druid/**` - Druid监控

### 7.3 请求头命名规范

推荐使用 `X-Tenant-ID` 作为标准请求头名称，便于统一管理。

## 8. 测试验证

### 8.1 启动应用

```bash
mvn spring-boot:run
```

### 8.2 访问Swagger文档

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Knife4j文档: `http://localhost:8080/doc.html`

### 8.3 测试接口

```bash
# 测试注解方式
curl -X GET "http://localhost:8080/api/example/with-annotation" \
  -H "X-Tenant-ID: tenant001"

# 测试全局拦截器（需要先启用）
curl -X GET "http://localhost:8080/api/example/global-interceptor" \
  -H "X-Tenant-ID: tenant001"
```

## 9. 注意事项

1. **数据源管理**: 系统会自动创建和管理租户数据源
2. **上下文清理**: 请求结束后会自动清理租户上下文
3. **异常处理**: 租户不存在或数据源创建失败会返回错误信息
4. **性能考虑**: 全局拦截器会增加一定的请求处理时间
5. **安全考虑**: 建议在生产环境中启用租户ID验证

## 10. 扩展功能

### 10.1 自定义租户验证逻辑

可以在 `TenantSwitchHeaderAspect` 中添加自定义的租户验证逻辑。

### 10.2 租户信息缓存

可以在 `DynamicDataSourceManager` 中添加租户信息缓存机制。

### 10.3 多租户数据源池

可以实现租户数据源的连接池管理，提高性能。 