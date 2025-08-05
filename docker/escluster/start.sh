#!/bin/bash

echo "启动ES 7.10.2三节点集群..."

# 创建数据目录
mkdir -p ../data/escluster_data/es01
mkdir -p ../data/escluster_data/es02
mkdir -p ../data/escluster_data/es03

# 设置目录权限
chmod 777 ../data/escluster_data/es01
chmod 777 ../data/escluster_data/es02
chmod 777 ../data/escluster_data/es03

# 启动集群
docker-compose up -d

echo "ES集群启动完成！"
echo "访问地址："
echo "  - ES01: http://localhost:9200"
echo "  - ES02: http://localhost:9201"
echo "  - ES03: http://localhost:9202"
echo "用户名: elastic"
echo "密码: 123456" 