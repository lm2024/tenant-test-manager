# segment-id-generator-starter

高性能号段ID生成starter，支持多租户、步进缓存、Redis高并发、MySQL持久化，适用于分布式唯一ID生成场景。

## 主要特性
- 多租户隔离
- 步进号段分配，支持高并发
- Redis缓存+MySQL持久化
- 支持多种ID生成规则（纯自增、前缀、后缀、自定义长度）
- 提供Spring Boot自动装配
- 提供管理与测试接口

## 依赖
- spring-boot-starter-data-jpa
- redisson
- mysql-connector-java
- lombok

## 快速集成
1. 引入依赖：
```xml
<dependency>
    <groupId>com.common</groupId>
    <artifactId>segment-id-generator-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
2. 配置数据源与Redis
3. 使用SegmentIdService生成ID

## 更多
详见源码与接口文档。 