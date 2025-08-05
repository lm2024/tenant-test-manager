# ES 7.10.2 三节点集群

## 配置说明

- **版本**: Elasticsearch 7.10.2 (使用本地镜像)
- **节点数**: 3个节点 (es01, es02, es03)
- **内存配置**: 每个节点最小内存512MB
- **密码**: 123456
- **集群名称**: es-docker-cluster

## 端口映射

- ES01: 9200 (HTTP), 9300 (Transport)
- ES02: 9201 (HTTP), 9301 (Transport)  
- ES03: 9202 (HTTP), 9302 (Transport)

## 使用方法

### 启动集群
```bash
cd docker/escluster
./start.sh
```

### 停止集群
```bash
cd docker/escluster
./stop.sh
```

### 手动启动
```bash
cd docker/escluster
docker-compose up -d
```

### 查看状态
```bash
docker-compose ps
```

### 查看日志
```bash
docker-compose logs -f es01
docker-compose logs -f es02
docker-compose logs -f es03
```

## 访问地址

- **ES01**: http://localhost:9200
- **ES02**: http://localhost:9201
- **ES03**: http://localhost:9202

**用户名**: elastic  
**密码**: 123456

## 数据持久化

数据保存在 `../data/escluster_data/` 目录下：
- es01数据: `../data/escluster_data/es01/`
- es02数据: `../data/escluster_data/es02/`
- es03数据: `../data/escluster_data/es03/`

## 集群健康检查

```bash
curl -u elastic:123456 http://localhost:9200/_cluster/health?pretty
``` 