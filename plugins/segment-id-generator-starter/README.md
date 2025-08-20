# Segment ID Generator Starter

高性能号段ID生成器，支持多租户、多业务类型的ID生成。

## 功能特性

- 支持多租户ID生成
- 支持多种业务类型
- 高性能号段缓存机制（Redis List）
- 支持批量ID生成
- 自动号段预加载
- 分布式锁保护
- 队列式ID消费

## 核心原理

### Redis List缓存机制

新的号段ID生成方法采用Redis List作为缓存：

1. **号段预分配**: 每次从数据库获取一个号段范围（如1000个ID）
2. **List缓存**: 将号段ID按顺序添加到Redis List中
3. **队列消费**: 从List头部获取ID，用完自动删除
4. **自动补充**: 当List为空时，自动从数据库获取新号段
5. **扩展功能保持**: 完全保持原有的前缀、后缀、自定义长度、serviceName等扩展功能

### 优势

- **高性能**: 减少数据库访问，从List头部获取ID
- **内存友好**: 使用Redis List，支持大量ID缓存
- **自动管理**: 号段用完自动补充，无需手动干预
- **并发安全**: 分布式锁保护，支持高并发场景
- **功能完整**: 保持所有原有扩展功能，完全向后兼容

## 使用方法

### 1. 基础ID生成

```java
@Autowired
private SegmentIdService segmentIdService;

// 生成单个ID
String id = segmentIdService.generateId("tenant001", "order");

// 生成批量ID
List<String> ids = segmentIdService.generateBatchIds("tenant001", "order", 10);
```

### 2. 高性能号段ID生成（推荐）

```java
@Autowired
private SegmentIdService segmentIdService;

// 生成单个号段ID
String id = segmentIdService.generateSegmentId("tenant001", "order");

// 生成批量号段ID
List<String> ids = segmentIdService.generateSegmentBatchIds("tenant001", "order", 100);

// 预加载号段缓存
segmentIdService.preloadSegmentCache("tenant001", "order");

// 查看剩余ID数量
int remainingCount = segmentIdService.getRemainingIdCount("tenant001", "order");

// 清空缓存（谨慎使用）
segmentIdService.clearSegmentCache("tenant001", "order");
```

### 缓存管理

```java
// 清空缓存（谨慎使用）
segmentIdService.clearSegmentCache("tenant001", "order");

// 预加载缓存
segmentIdService.preloadSegmentCache("tenant001", "order");
```

## 扩展功能保持

新的Redis List实现完全保持了原有的所有扩展功能：

### 1. ID类型支持
- **纯自增**: 生成纯数字ID
- **前缀+自增**: 如 `ORDER00000001`
- **自增+后缀**: 如 `00000001_END`
- **自定义长度**: 支持自定义ID长度

### 2. 配置参数
- **prefix**: 前缀字符串
- **suffix**: 后缀字符串
- **length**: ID长度
- **serviceName**: 服务名称
- **step**: 号段大小
- **maxValue**: 最大值限制

### 3. 功能一致性
- 新旧方法生成的ID格式完全一致
- 所有扩展功能在新方法中正常工作
- 完全向后兼容，无需修改现有配置

## 注意事项

1. 确保Redis集群高可用
2. 合理设置号段大小，避免频繁数据库访问
3. 监控号段消耗速度，及时调整配置
4. 处理分布式环境下的并发问题
5. Redis List缓存过期时间设置为30天，避免数据丢失
6. 新的Redis List实现完全保持原有扩展功能，可以放心使用

## 性能对比

| 方法 | 数据库访问频率 | Redis缓存策略 | 适用场景 |
|------|----------------|---------------|----------|
| `generateId` | 每次调用 | 单值缓存 | 低频调用 |
| `generateSegmentId` | 号段用完时 | Redis List缓存 | 高频调用（推荐） |

## Redis List缓存机制

新的号段ID生成方法采用以下策略：

1. **号段预分配**: 每次从数据库获取一个号段范围（如1000个ID）
2. **List缓存**: 在Redis List中按顺序存储号段ID
3. **队列消费**: 从List头部获取ID，用完自动删除
4. **自动补充**: 当List为空时，自动从数据库获取新号段

## 配置说明

### 数据库表结构

```sql
CREATE TABLE sequence (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tenant_id VARCHAR(64) NOT NULL,
    biz_type VARCHAR(64) NOT NULL,
    service_name VARCHAR(64) NOT NULL,
    prefix VARCHAR(32),
    suffix VARCHAR(32),
    step INT NOT NULL,
    length INT NOT NULL,
    current_value BIGINT NOT NULL,
    max_value BIGINT NOT NULL,
    type INT NOT NULL,
    update_time TIMESTAMP,
    create_time TIMESTAMP,
    UNIQUE KEY uk_tenant_biz (tenant_id, biz_type)
);
```

### 关键参数

- `step`: 号段大小，建议设置为1000-10000
- `length`: ID长度，用于格式化
- `max_value`: 最大值限制
- `type`: ID类型（1:纯数字, 2:前缀+数字, 3:数字+后缀, 4:自定义）

## 最佳实践

1. **号段大小设置**: 根据业务QPS设置合适的step值
2. **预加载策略**: 在服务启动时预加载常用业务类型的号段
3. **监控告警**: 监控号段消耗速度，及时调整配置
4. **缓存管理**: 定期检查剩余ID数量，避免号段耗尽

## 示例配置

```yaml
# application.yml
segment:
  id:
    default-step: 1000          # 号段大小
    cache-expire-hours: 24*30   # 缓存过期时间（30天）
    preload-enabled: true       # 启用预加载
    preload-business-types:
      - order
      - user
      - product
```

## 监控和管理

### 查看缓存状态

```java
// 查看剩余ID数量
int remainingCount = segmentIdService.getRemainingIdCount("tenant001", "order");

// 如果数量不足，预加载
if (remainingCount < 100) {
    segmentIdService.preloadSegmentCache("tenant001", "order");
}
```