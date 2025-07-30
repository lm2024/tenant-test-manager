# Elasticsearch CRUD Starter 测试指南

## 测试环境准备

### 1. 启动Elasticsearch

确保Elasticsearch 7.10.x版本正在运行：

```bash
# 使用Docker启动Elasticsearch（推荐）
docker run -d \
  --name elasticsearch \
  -p 9200:9200 \
  -p 9300:9300 \
  -e "discovery.type=single-node" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  elasticsearch:7.10.2

# 验证Elasticsearch是否启动成功
curl http://localhost:9200
```

### 2. 启动Redis

确保Redis正在运行（用于队列功能）：

```bash
# 使用Docker启动Redis
docker run -d \
  --name redis \
  -p 6379:6379 \
  redis:6.2-alpine

# 验证Redis是否启动成功
redis-cli ping
```

### 3. 启动MySQL

确保MySQL正在运行（用于数据同步功能）：

```bash
# 使用Docker启动MySQL
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=password \
  mysql:5.7

# 创建测试数据库
mysql -h localhost -u root -ppassword -e "CREATE DATABASE IF NOT EXISTS tenant_center;"
```

## 编译和启动

### 1. 编译项目

```bash
# 在项目根目录执行
mvn clean install

# 或者只编译elasticsearch-crud-starter
cd elasticsearch-crud-starter
mvn clean install
```

### 2. 启动测试服务

```bash
# 启动my-test-execute服务
cd my-test-execute
mvn spring-boot:run
```

## 测试接口

服务启动后，访问以下地址进行测试：

### Swagger文档
- http://localhost:8082/doc.html

### 基础测试接口

1. **配置检查**
   ```
   GET http://localhost:8082/api/elasticsearch/simple/check-config
   ```

2. **健康检查**
   ```
   GET http://localhost:8082/api/elasticsearch/simple/health
   ```

3. **应用信息**
   ```
   GET http://localhost:8082/api/elasticsearch/simple/app-info
   ```

### Elasticsearch功能测试接口

1. **连接测试**
   ```
   GET http://localhost:8082/api/elasticsearch/test/connection
   ```

2. **保存测试文档**
   ```
   POST http://localhost:8082/api/elasticsearch/test/document
   Content-Type: application/json
   
   {
     "title": "测试文档",
     "content": "这是一个测试文档的内容",
     "category": "测试分类",
     "status": "active",
     "priority": 1
   }
   ```

3. **批量保存文档**
   ```
   POST http://localhost:8082/api/elasticsearch/test/documents/batch
   Content-Type: application/json
   
   [
     {
       "title": "测试文档1",
       "content": "第一个测试文档",
       "category": "分类1",
       "status": "active",
       "priority": 1
     },
     {
       "title": "测试文档2", 
       "content": "第二个测试文档",
       "category": "分类2",
       "status": "active",
       "priority": 2
     }
   ]
   ```

4. **查询文档**
   ```
   GET http://localhost:8082/api/elasticsearch/test/document/{id}
   ```

5. **分页查询**
   ```
   GET http://localhost:8082/api/elasticsearch/test/documents?page=0&size=10
   ```

6. **搜索文档**
   ```
   GET http://localhost:8082/api/elasticsearch/test/documents/search?keyword=测试&page=0&size=10
   ```

7. **获取索引列表**
   ```
   GET http://localhost:8082/api/elasticsearch/test/indices
   ```

8. **创建索引**
   ```
   POST http://localhost:8082/api/elasticsearch/test/index/test-index
   ```

## 预期结果

### 成功场景
- 所有接口返回状态码200
- 配置检查接口显示所有Bean都已正确初始化
- 连接测试接口显示Elasticsearch连接正常
- 文档操作接口正常响应（目前返回模拟数据）

### 失败场景处理
- 如果Elasticsearch未启动，连接测试会失败
- 如果配置错误，Bean初始化会失败
- 所有错误都会有详细的错误信息返回

## 注意事项

1. **当前状态**：这是基础框架测试，实际的ES操作逻辑将在后续任务中实现
2. **租户支持**：可以通过请求头 `X-Tenant-ID` 测试租户功能
3. **日志查看**：启动时注意查看控制台日志，确认组件正确初始化
4. **端口冲突**：确保8082端口未被占用

## 故障排查

### 常见问题

1. **Bean初始化失败**
   - 检查Elasticsearch是否启动
   - 检查配置文件中的连接信息
   - 查看启动日志中的错误信息

2. **连接超时**
   - 检查Elasticsearch服务状态
   - 检查网络连接
   - 调整超时时间配置

3. **依赖冲突**
   - 清理Maven缓存：`mvn clean`
   - 重新编译：`mvn install`
   - 检查依赖版本兼容性

## 下一步

完成基础测试后，可以继续实现：
1. 实际的CRUD操作逻辑
2. 复杂查询功能
3. 数据同步功能
4. 队列处理功能