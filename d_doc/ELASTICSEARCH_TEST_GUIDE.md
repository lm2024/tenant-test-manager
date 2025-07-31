# Elasticsearch 测试指南

## 问题解决

您遇到的启动错误是因为JPA配置与现有的TestCaseDataProcessor冲突。为了专注测试Elasticsearch功能，我创建了一个独立的测试环境。

## 快速开始

### 1. 准备环境

确保以下服务正在运行：

```bash
# 启动Elasticsearch (Docker)
docker run -d \
  --name elasticsearch \
  -p 9200:9200 \
  -e "discovery.type=single-node" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  elasticsearch:7.10.2

# 启动Redis (Docker)
docker run -d \
  --name redis \
  -p 6379:6379 \
  redis:6.2-alpine

# 验证服务
curl http://localhost:9200  # 应该返回ES集群信息
redis-cli ping              # 应该返回PONG
```

### 2. 编译项目

```bash
# 在项目根目录
mvn clean install -DskipTests

# 或者只编译elasticsearch-crud-starter
cd elasticsearch-crud-starter
mvn clean install -DskipTests
```

### 3. 启动测试服务

```bash
# 方式1: 使用启动脚本
chmod +x start-es-test.sh
./start-es-test.sh

# 方式2: 手动启动
cd my-test-execute
mvn spring-boot:run \
  -Dspring-boot.run.main-class=org.example.ElasticsearchTestApplication \
  -Dspring-boot.run.profiles=es-test
```

### 4. 验证启动

服务启动后，访问：
- **Swagger文档**: http://localhost:8083/doc.html
- **健康检查**: http://localhost:8083/api/elasticsearch/simple/health

### 5. 运行测试

```bash
# 运行自动化测试脚本
chmod +x test-elasticsearch.sh
./test-elasticsearch.sh
```

## 测试接口说明

### 基础测试
- `GET /api/elasticsearch/simple/health` - 健康检查
- `GET /api/elasticsearch/simple/check-config` - 配置检查
- `GET /api/elasticsearch/simple/app-info` - 应用信息

### Elasticsearch功能测试
- `GET /api/elasticsearch/test/connection` - ES连接测试
- `POST /api/elasticsearch/test/document` - 保存文档
- `GET /api/elasticsearch/test/documents` - 查询文档
- `GET /api/elasticsearch/test/indices` - 索引管理

## 配置说明

### 端口变更
- 原服务端口: 8082 (有JPA依赖冲突)
- 测试服务端口: 8083 (纯Elasticsearch测试)

### 主要差异
1. **排除JPA配置**: 避免与现有TestCaseDataProcessor冲突
2. **简化依赖**: 只包含Elasticsearch相关功能
3. **独立配置**: 使用application-es-test.yml配置文件

## 故障排查

### 启动失败
1. 检查端口8083是否被占用
2. 确认Elasticsearch在9200端口运行
3. 确认Redis在6379端口运行

### 连接失败
1. 检查ES服务状态: `curl http://localhost:9200`
2. 检查防火墙设置
3. 查看应用日志中的错误信息

### Bean初始化失败
1. 检查Maven依赖是否正确安装
2. 确认Java版本为8+
3. 清理Maven缓存: `mvn clean`

## 下一步

测试通过后，可以：
1. 继续实现实际的CRUD逻辑
2. 添加复杂查询功能
3. 集成到原有的my-test-execute服务中

## 恢复原服务

如需恢复原来的服务配置：
```bash
cd my-test-execute
mvn spring-boot:run  # 使用原来的TestExecuteApplication
```

注意：原服务需要解决JPA配置问题才能正常启动。