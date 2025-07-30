# Elasticsearch 功能使用说明

## 概述

本项目已成功集成了 `elasticsearch-crud-starter` 组件，提供了完整的 Elasticsearch 功能支持。

## 功能特性

### 1. 基础测试接口
- **简单测试**: `/api/elasticsearch/simple/*`
  - 配置检查: `GET /api/elasticsearch/simple/check-config`
  - 应用信息: `GET /api/elasticsearch/simple/app-info`
  - 健康检查: `GET /api/elasticsearch/simple/health`

### 2. 功能测试接口
- **连接测试**: `GET /api/elasticsearch/test/connection`
- **文档操作**: 
  - 保存文档: `POST /api/elasticsearch/test/document`
  - 批量保存: `POST /api/elasticsearch/test/documents/batch`
  - 查询文档: `GET /api/elasticsearch/test/document/{id}`
  - 分页查询: `GET /api/elasticsearch/test/documents`
  - 搜索文档: `GET /api/elasticsearch/test/documents/search`
  - 删除文档: `DELETE /api/elasticsearch/test/document/{id}`
- **索引管理**:
  - 获取索引列表: `GET /api/elasticsearch/test/indices`
  - 创建索引: `POST /api/elasticsearch/test/index/{indexName}`

### 3. 业务接口
- **文档管理**:
  - 创建文档: `POST /api/elasticsearch/business/document/create`
  - 批量创建: `POST /api/elasticsearch/business/documents/batch-create`
  - 更新文档: `PUT /api/elasticsearch/business/document/{id}`
  - 高级搜索: `GET /api/elasticsearch/business/documents/search/advanced`
  - 统计数量: `GET /api/elasticsearch/business/documents/count`
  - 批量删除: `DELETE /api/elasticsearch/business/documents/batch-delete`
- **数据同步**:
  - 同步状态: `GET /api/elasticsearch/business/sync/status`
  - 触发同步: `POST /api/elasticsearch/business/sync/trigger`

## 配置说明

### 1. 主配置文件 (application.yml)
`my-test-execute` 工程的主配置文件只包含基础配置，ES 相关配置由 `elasticsearch-crud-starter` 组件提供。

### 2. Elasticsearch 配置 (elasticsearch-crud-starter)
所有 Elasticsearch 相关配置都在 `elasticsearch-crud-starter/src/main/resources/application-elasticsearch.yml` 中：

```yaml
elasticsearch:
  crud:
    enabled: true
    hosts:
      - localhost:9200
      - localhost:9201
      - localhost:9202
    username: elastic
    password: password
    connection-timeout: 5000
    socket-timeout: 60000
    request-timeout: 60000
    max-connections: 100
    max-connections-per-route: 100
    
    # 同步配置
    sync:
      batch-size: 1000
      thread-pool-size: 10
      retry-times: 3
      retry-interval: 1000
      enable-checkpoint: true
      checkpoint-interval: 1000
    
    # 租户配置
    tenant:
      enabled: true
      index-prefix: "tenant_"
      auto-create-index: true
      default-shards: 3
      default-replicas: 1
```

## 使用示例

### 1. 创建测试文档
```bash
curl -X POST http://localhost:8082/api/elasticsearch/business/document/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "测试文档",
    "content": "这是一个测试文档",
    "category": "测试分类",
    "status": "active",
    "tenantId": "tenant001",
    "priority": 1,
    "active": true
  }'
```

### 2. 搜索文档
```bash
curl -X GET "http://localhost:8082/api/elasticsearch/business/documents/search/advanced?title=测试&category=测试分类&page=0&size=10"
```

### 3. 检查配置
```bash
curl -X GET http://localhost:8082/api/elasticsearch/simple/check-config
```

## 测试

### 运行集成测试
```bash
mvn test -Dtest=ElasticsearchIntegrationTest
```

### 启动应用
```bash
mvn spring-boot:run -Dspring.profiles.active=elasticsearch
```

## 注意事项

1. **依赖要求**: 确保 Elasticsearch 服务已启动并运行在 `localhost:9200`
2. **配置文件**: 使用 `elasticsearch` profile 启动应用以获得最佳配置
3. **日志级别**: 已配置详细的 Elasticsearch 相关日志输出
4. **错误处理**: 所有接口都包含完善的错误处理和日志记录

## 目录结构

```
my-test-execute/
├── src/main/java/com/tenant/test/
│   ├── controller/
│   │   ├── ElasticsearchSimpleTestController.java    # 简单测试接口
│   │   ├── ElasticsearchTestController.java          # 功能测试接口
│   │   └── ElasticsearchBusinessController.java      # 业务接口
│   ├── entity/
│   │   └── TestDocument.java                         # 测试文档实体
│   └── config/
│       └── JpaConfig.java                            # JPA配置
├── src/test/java/com/tenant/test/
│   └── ElasticsearchIntegrationTest.java             # 集成测试
└── src/main/resources/
    ├── application.yml                                # 主配置
    └── application-elasticsearch.yml                  # ES专用配置
```

## 技术栈

- **Spring Boot 2.7.0**
- **Elasticsearch 7.10.x**
- **elasticsearch-crud-starter 1.0.0**
- **Knife4j 4.3.0** (API文档)
- **Lombok** (代码简化)

## 故障排除

1. **服务未初始化**: 检查 Elasticsearch 连接配置
2. **索引不存在**: 使用索引管理接口创建索引
3. **查询失败**: 检查查询条件和索引映射
4. **同步失败**: 检查数据源配置和网络连接 