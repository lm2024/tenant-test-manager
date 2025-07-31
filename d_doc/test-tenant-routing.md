# 多微服务租户路由测试指南

## 1. 环境准备

### 1.1 安装租户路由组件
```bash
cd tenant-routing-starter
mvn clean install
```

### 1.2 准备租户中心数据库
```sql
-- 创建租户中心数据库
CREATE DATABASE IF NOT EXISTS tenant_center;
USE tenant_center;

-- 创建租户信息表
CREATE TABLE IF NOT EXISTS `tenant_db_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(255) NOT NULL,
  `db_password` varchar(255) NOT NULL,
  `db_url` varchar(255) NOT NULL,
  `db_user` varchar(255) NOT NULL,
  `tenant_id` varchar(255) NOT NULL,
  `tenant_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tenant_id` (`tenant_id`)
);

-- 插入测试租户数据
INSERT INTO `tenant_db_info` VALUES 
(1, 'db_tenant001', 'password', 'jdbc:mysql://localhost:3306/db_tenant001', 'root', 'tenant001', '测试租户1'),
(2, 'db_tenant002', 'password', 'jdbc:mysql://localhost:3306/db_tenant002', 'root', 'tenant002', '测试租户2');

-- 创建测试租户数据库
CREATE DATABASE IF NOT EXISTS db_tenant001;
CREATE DATABASE IF NOT EXISTS db_tenant002;
```

### 1.3 创建测试表
```sql
-- 在每个租户数据库中创建测试表
USE db_tenant001;
CREATE TABLE IF NOT EXISTS `test_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tenant_mark` varchar(255) DEFAULT 'tenant001',
  PRIMARY KEY (`id`)
);
INSERT INTO `test_data` VALUES (1, '租户1的数据', 'tenant001');

USE db_tenant002;
CREATE TABLE IF NOT EXISTS `test_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tenant_mark` varchar(255) DEFAULT 'tenant002',
  PRIMARY KEY (`id`)
);
INSERT INTO `test_data` VALUES (1, '租户2的数据', 'tenant002');
```

## 2. 微服务配置

### 2.1 修改各微服务的pom.xml
在每个微服务的pom.xml中添加依赖：
```xml
<dependency>
    <groupId>com.tenant</groupId>
    <artifactId>tenant-routing-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2.2 配置文件
在每个微服务的application.yml中添加配置：
```yaml
tenant:
  routing:
    enabled: true
    header-name: X-Tenant-ID
    required: true  # 根据需要设置是否必需
    exclude-paths: 
      - /swagger-ui.html
      - /doc.html
      - /actuator/**
    tenant-center:
      url: jdbc:mysql://localhost:3306/tenant_center
      username: root
      password: root
```

## 3. 测试验证

### 3.1 测试my-bugs服务
```bash
# 测试租户1
curl -X GET "http://localhost:8081/api/bugs/list" \
  -H "X-Tenant-ID: tenant001"

# 预期结果: 返回租户1的bug列表

# 测试租户2
curl -X GET "http://localhost:8081/api/bugs/list" \
  -H "X-Tenant-ID: tenant002"

# 预期结果: 返回租户2的bug列表
```

### 3.2 测试my-test-execute服务
```bash
# 测试租户1
curl -X GET "http://localhost:8082/api/test/execute" \
  -H "X-Tenant-ID: tenant001"

# 预期结果: 返回租户1的测试执行结果

# 测试租户2
curl -X GET "http://localhost:8082/api/test/execute" \
  -H "X-Tenant-ID: tenant002"

# 预期结果: 返回租户2的测试执行结果
```

### 3.3 测试my-function-demand服务
```bash
# 测试租户1
curl -X GET "http://localhost:8083/api/demand/list" \
  -H "X-Tenant-ID: tenant001"

# 预期结果: 返回租户1的需求列表

# 测试租户2
curl -X GET "http://localhost:8083/api/demand/list" \
  -H "X-Tenant-ID: tenant002"

# 预期结果: 返回租户2的需求列表
```

### 3.4 测试跨服务调用
```bash
# 测试跨服务调用
curl -X GET "http://localhost:8084/api/product/bugs" \
  -H "X-Tenant-ID: tenant001"

# 预期结果: 通过my-product-tenant服务调用my-bugs服务，返回租户1的bug列表
```

## 4. 验证租户隔离

### 4.1 数据库查询验证
```sql
-- 查询租户1数据库
USE db_tenant001;
SELECT * FROM test_data;

-- 查询租户2数据库
USE db_tenant002;
SELECT * FROM test_data;
```

### 4.2 日志验证
检查应用日志中是否包含租户信息：
```
[Tenant:tenant001] Executing SQL: SELECT * FROM bugs
[Tenant:tenant002] Executing SQL: SELECT * FROM bugs
```

## 5. 故障排查

### 5.1 常见问题
1. **租户ID未传递**: 检查请求头是否包含租户ID
2. **数据源创建失败**: 检查数据库连接配置
3. **租户配置未加载**: 检查tenant_center数据库连接

### 5.2 查看连接池状态
```bash
# 使用JConsole连接应用，查看HikariCP连接池状态
jconsole
```

## 6. 性能测试

### 6.1 并发测试
```bash
# 使用Apache Bench进行并发测试
ab -n 1000 -c 100 -H "X-Tenant-ID: tenant001" http://localhost:8081/api/bugs/list
```

### 6.2 切换租户测试
```bash
# 交替发送不同租户的请求
for i in {1..100}; do
  curl -X GET "http://localhost:8081/api/bugs/list" -H "X-Tenant-ID: tenant001"
  curl -X GET "http://localhost:8081/api/bugs/list" -H "X-Tenant-ID: tenant002"
done
```