#!/bin/bash

echo "停止ES 7.10.2三节点集群..."

# 停止并删除容器
docker-compose down

echo "ES集群已停止！" 