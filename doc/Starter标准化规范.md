# Starter模块标准化规范

## 1. 统一的Starter结构

### 1.1 标准目录结构
```
{starter-name}/
├── src/main/java/com/tenant/{module}/
│   ├── config/           # 自动配置类
│   ├── properties/       # 配置属性类
│   ├── service/          # 核心服务类
│   ├── annotation/       # 注解定义
│   ├── exception/        # 异常定义
│   └── util/            # 工具类
├── src/main/resources/
│   ├── META-INF/
│   │   └── spring.factories  # 自动配置声明
│   └── application.yml   # 默认配置
└── pom.xml
```

### 1.2 统一的包命名规范
- **基础包**：`com.tenant.{module}`
- **配置包**：`com.tenant.{module}.config`
- **属性包**：`com.tenant.{module}.properties`
- **服务包**：`com.tenant.{module}.service`

### 1.3 统一的GroupId
所有starter使用统一的groupId：`com.tenant`

## 2. 配置类标准化

### 2.1 自动配置类模板
```java
@Configuration
@EnableConfigurationProperties({ModuleProperties.class})
@ConditionalOnProperty(prefix = "tenant.{module}", name = "enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
public class {Module}AutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public {Module}Service {module}Service({Module}Properties properties) {
        return new {Module}ServiceImpl(properties);
    }
}
```

### 2.2 配置属性类模板
```java
@ConfigurationProperties(prefix = "tenant.{module}")
@Data
public class {Module}Properties {
    
    /**
     * 是否启用模块
     */
    private boolean enabled = true;
    
    /**
     * 其他配置项...
     */
}
```

## 3. 异常处理标准化

### 3.1 统一异常基类
```java
public class TenantException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    
    public TenantException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
```

### 3.2 模块特定异常
```java
public class {Module}Exception extends TenantException {
    public {Module}Exception(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
```

## 4. 日志标准化

### 4.1 统一日志格式
```java
@Slf4j
public class {Module}ServiceImpl {
    
    public void someMethod() {
        log.info("[{}] 开始执行操作: {}", getClass().getSimpleName(), "操作描述");
        try {
            // 业务逻辑
            log.info("[{}] 操作执行成功", getClass().getSimpleName());
        } catch (Exception e) {
            log.error("[{}] 操作执行失败: {}", getClass().getSimpleName(), e.getMessage(), e);
            throw e;
        }
    }
}
```

## 5. 测试标准化

### 5.1 单元测试模板
```java
@SpringBootTest
@TestPropertySource(properties = {
    "tenant.{module}.enabled=true"
})
class {Module}ServiceTest {
    
    @Autowired
    private {Module}Service {module}Service;
    
    @Test
    void test{Method}() {
        // 测试逻辑
    }
}
```

## 6. 文档标准化

### 6.1 README.md模板
每个starter都应包含：
- 功能描述
- 快速开始
- 配置说明
- 使用示例
- 常见问题

### 6.2 API文档
使用统一的注解标准：
```java
@Api(tags = "{模块名称}管理")
@RestController
@RequestMapping("/api/{module}")
public class {Module}Controller {
    
    @ApiOperation("获取{模块}信息")
    @GetMapping("/{id}")
    public Result<{Module}VO> get(@PathVariable Long id) {
        // 实现
    }
}
```