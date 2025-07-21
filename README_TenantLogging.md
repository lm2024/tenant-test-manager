# 租户日志功能使用说明

## 概述

本项目实现了在JPA SQL日志中自动拼接租户信息的功能，方便在多租户环境下进行SQL调试和问题排查。

## 功能特性

- ✅ 自动在SQL日志中显示租户ID
- ✅ 支持自定义日志模式
- ✅ 只对SQL相关日志进行处理
- ✅ 线程安全的租户信息获取
- ✅ 优雅的异常处理

## 实现原理

### 1. 自定义Logback Converter

创建了 `TenantLogConverter` 类，用于在日志模式中显示租户信息：

```java
public class TenantLogConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        try {
            String tenantId = DataSourceContextHolder.get();
            if (tenantId != null && !tenantId.isEmpty()) {
                return "[Tenant:" + tenantId + "]";
            }
        } catch (Exception e) {
            // 忽略异常，返回空字符串
        }
        return "";
    }
}
```

### 2. Logback配置

在 `logback-spring.xml` 中注册自定义转换器：

```xml
<!-- 注册自定义转换器 -->
<conversionRule conversionWord="tenant"
        converterClass="com.tenant.config.log.TenantLogConverter"/>

<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
<encoder>
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %tenant %logger{36} - %msg%n</pattern>
</encoder>
</appender>
```

## 日志格式说明

### 标准格式
```
2024-01-01 12:00:00.123 [http-nio-8080-exec-1] DEBUG [Tenant:tenant001] org.hibernate.SQL - select testcase0_.id as id1_0_, testcase0_.description as descript2_0_, testcase0_.title as title3_0_ from test_case testcase0_
```

### 格式解析
- `2024-01-01 12:00:00.123` - 时间戳
- `[http-nio-8080-exec-1]` - 线程名
- `DEBUG` - 日志级别
- `[Tenant:tenant001]` - 租户信息（自动添加）
- `org.hibernate.SQL` - 日志器名称
- `select ...` - SQL语句

## 配置选项

### 1. 启用SQL日志

在 `application.yml` 中配置：

```yaml
logging:
  level:
    org.hibernate.SQL: debug
    org.hibernate.type.descriptor.sql.BasicBinder: trace
```

### 2. 自定义日志模式

可以修改 `logback-spring.xml` 中的模式：

```xml
<!-- 标准模式 -->
<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %tenant %logger{36} - %msg%n</pattern>

<!-- 简化模式 -->
<pattern>%d{HH:mm:ss.SSS} %tenant %logger{36} - %msg%n</pattern>

<!-- 详细模式 -->
<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %tenant [%X{traceId}] %logger{36} - %msg%n</pattern>
```

## 使用示例

### 1. 启动应用

```bash
mvn spring-boot:run
```

### 2. 测试租户日志

```bash
# 使用注解方式
curl -X GET "http://localhost:8080/api/log-test/test-annotation" \
  -H "X-Tenant-ID: tenant001"

# 使用全局拦截器（需要启用）
curl -X GET "http://localhost:8080/api/log-test/test-global" \
  -H "X-Tenant-ID: tenant001"
```

### 3. 查看日志输出

控制台会显示类似以下的日志：

```
2024-01-01 12:00:00.123 [http-nio-8080-exec-1] INFO  [Tenant:tenant001] c.e.t.c.LogTestController - 开始查询测试用例列表
2024-01-01 12:00:00.124 [http-nio-8080-exec-1] DEBUG [Tenant:tenant001] org.hibernate.SQL - select testcase0_.id as id1_0_, testcase0_.description as descript2_0_, testcase0_.title as title3_0_ from test_case testcase0_
2024-01-01 12:00:00.125 [http-nio-8080-exec-1] TRACE [Tenant:tenant001] o.h.type.descriptor.sql.BasicBinder - binding parameter [1] as [VARCHAR] - [tenant001]
2024-01-01 12:00:00.126 [http-nio-8080-exec-1] INFO  [Tenant:tenant001] c.e.t.c.LogTestController - 查询完成，共找到 5 条记录
```

## 测试接口

### 1. 日志测试控制器

项目提供了 `LogTestController` 用于测试租户日志功能：

- `GET /api/log-test/test-annotation` - 测试注解方式
- `GET /api/log-test/test-global` - 测试全局拦截器
- `POST /api/log-test/create` - 测试创建数据
- `PUT /api/log-test/update` - 测试更新数据
- `DELETE /api/log-test/{id}` - 测试删除数据
- `GET /api/log-test/complex-query` - 测试复杂查询

### 2. 访问Swagger文档

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Knife4j文档: `http://localhost:8080/doc.html`

## 高级配置

### 1. 自定义租户信息格式

可以修改 `TenantLogConverter` 中的格式：

```java
// 简单格式
return "[Tenant:" + tenantId + "]";

// 详细格式
return "[Tenant:" + tenantId + ",DB:" + getCurrentDatabase() + "]";

// 带时间戳格式
return "[Tenant:" + tenantId + ",Time:" + System.currentTimeMillis() + "]";
```

### 2. 条件显示租户信息

可以添加条件判断，只在特定情况下显示租户信息：

```java
@Override
public String convert(ILoggingEvent event) {
    try {
        String tenantId = DataSourceContextHolder.get();
        if (tenantId != null && !tenantId.isEmpty()) {
            // 只对SQL日志显示租户信息
            if (event.getLoggerName().contains("org.hibernate.SQL")) {
                return "[Tenant:" + tenantId + "]";
            }
        }
    } catch (Exception e) {
        // 忽略异常
    }
    return "";
}
```

### 3. 多租户环境下的日志分离

可以为不同租户配置不同的日志文件：

```xml
<appender name="TENANT_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/tenant-${tenantId}.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>logs/tenant-${tenantId}.%d{yyyy-MM-dd}.log</fileNamePattern>
        <maxHistory>30</maxHistory>
    </rollingPolicy>
    <encoder>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %tenant %logger{36} - %msg%n</pattern>
    </encoder>
</appender>
```

## 注意事项

1. **性能考虑**: 租户信息获取会增加少量性能开销
2. **线程安全**: 使用ThreadLocal确保线程安全
3. **异常处理**: 租户信息获取失败不会影响正常日志输出
4. **日志级别**: 建议在生产环境中适当调整SQL日志级别
5. **存储考虑**: 大量SQL日志可能占用较多存储空间

## 故障排查

### 1. 租户信息不显示

- 检查 `DataSourceContextHolder` 是否正确设置
- 确认请求头中包含租户ID
- 验证租户切换注解是否生效

### 2. 日志格式异常

- 检查 `logback-spring.xml` 配置是否正确
- 确认 `TenantLogConverter` 类路径正确
- 验证日志模式语法

### 3. 性能问题

- 考虑减少SQL日志级别
- 使用日志文件轮转
- 配置日志压缩

## 扩展功能

### 1. 添加更多租户信息

可以在 `TenantLogConverter` 中添加更多信息：

```java
// 添加数据库信息
String dbInfo = getCurrentDatabase();
return "[Tenant:" + tenantId + ",DB:" + dbInfo + "]";

// 添加用户信息
String userInfo = getCurrentUser();
return "[Tenant:" + tenantId + ",User:" + userInfo + "]";
```

### 2. 支持ELK格式

可以配置日志输出为JSON格式，便于ELK分析：

```xml
<encoder class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
    <providers>
        <timestamp/>
        <logLevel/>
        <loggerName/>
        <message/>
        <mdc/>
        <pattern>
            <pattern>
                {
                  "tenant": "%tenant",
                  "thread": "%thread",
                  "logger": "%logger"
                }
            </pattern>
        </pattern>
    </providers>
</encoder>
```

### 3. 租户日志监控

可以实现租户日志的监控和告警功能，及时发现异常。 