#!/bin/bash

echo "=== 测试Elasticsearch连接 ==="
echo ""

# 测试无认证连接
echo "1. 测试无认证连接..."
curl -s http://localhost:9200

echo ""
echo ""

# 测试有认证连接
echo "2. 测试有认证连接..."
curl -s -u elastic:changeme http://localhost:9200

echo ""
echo ""

# 测试其他常见密码
echo "3. 测试其他常见密码..."
curl -s -u elastic:password http://localhost:9200

echo ""
echo ""

# 检查ES状态
echo "4. 检查ES进程..."
ps aux | grep elasticsearch

echo ""
echo "=== 测试完成 ===" 