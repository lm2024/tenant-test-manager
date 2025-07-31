# Elasticsearch CRUD Starter

基于Spring Data Elasticsearch的高性能增删改查组件，支持ES 7.10版本和Spring Boot 2.7.x版本。

## 功能特性

- ✅ **基础CRUD操作** - 提供完整的增删改查功能
- ✅ **复杂查询支持** - 支持嵌套查询、父子查询、聚合查询等
- ✅ **索引管理** - 索引创建、删除、备份、恢复等管理功能
- ✅ **数据同步** - MySQL与ES之间的双向数据同步
- ✅ **队列支持** - 基于Redis的高性能异步队列处理
- ✅ **租户隔离** - 完整的多租户数据隔离支持
- ✅ **性能优化** - Keyword类型优化，提高查询性能
- ✅ **监控告警** - 完整的监控指标和健康检查
- ✅ **重试机制** - 可靠的错误处理和重试策略

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>elasticsearch-crud-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 2. 配置文件

```yaml
elasticsearch:
  crud:
    enabled: true
    hosts:
      - localhost:9200
    username: elastic
    password: password
    
    # 租户配置
    tenant:
      enabled: true
      index-prefix: "tenant_"
    
    # 同步配置
    sync:
      batch-size: 1000
      thread-pool-size: 10
    
    # 队列配置
    queue:
      type: redis
      redis:
        key-prefix: "es:sync:"
```

### 3. 使用示例

```java
@Autowired
private ElasticsearchCrudService<MyEntity> crudService;

@Autowired
private ElasticsearchComplexQueryService queryService;

@Autowired
private DataSyncService syncService;

// 基础CRUD操作
MyEntity entity = new MyEntity();
entity.setName("测试数据");
crudService.save(entity);

// 复杂查询
NestedQueryRequest request = new NestedQueryRequest();
List<MyEntity> results = queryService.nestedQuery(request, MyEntity.class);

// 数据同步
SyncRequest syncRequest = new SyncRequest();
syncRequest.setSourceTable("my_table");
syncRequest.setTargetIndex("my_index");
SyncResult result = syncService.syncFromMysqlToEs(syncRequest);
```

## 版本兼容性

| 组件 | 版本 |
|------|------|
| Spring Boot | 2.7.x |
| Elasticsearch | 7.10.x |
| Spring Data Elasticsearch | 4.4.x |
| Java | 8+ |

## 配置说明

详细的配置说明请参考 [配置文档](docs/configuration.md)

## API文档

完整的API文档请参考 [API文档](docs/api.md)

## 运维指南

ES集群运维指南请参考 [运维文档](docs/operations.md)

## 许可证

本项目采用 MIT 许可证。