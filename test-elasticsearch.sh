#!/bin/bash

# Elasticsearch CRUD Starter 快速测试脚本

echo "=== Elasticsearch CRUD Starter 测试脚本 ==="
echo ""

BASE_URL="http://localhost:8083"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 测试函数
test_api() {
    local url=$1
    local method=${2:-GET}
    local data=$3
    local description=$4
    
    echo -e "${YELLOW}测试: $description${NC}"
    echo "URL: $method $url"
    
    if [ "$method" = "POST" ] && [ -n "$data" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST \
            -H "Content-Type: application/json" \
            -d "$data" \
            "$url")
    else
        response=$(curl -s -w "\n%{http_code}" "$url")
    fi
    
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | head -n -1)
    
    if [ "$http_code" = "200" ]; then
        echo -e "${GREEN}✓ 成功 (HTTP $http_code)${NC}"
        echo "响应: $(echo "$body" | jq -r '.message // .status // "OK"' 2>/dev/null || echo "OK")"
    else
        echo -e "${RED}✗ 失败 (HTTP $http_code)${NC}"
        echo "响应: $body"
    fi
    echo ""
}

# 检查服务是否启动
echo "检查服务状态..."
if ! curl -s "$BASE_URL/api/elasticsearch/simple/health" > /dev/null; then
    echo -e "${RED}错误: 服务未启动或无法访问 $BASE_URL${NC}"
    echo "请确保 Elasticsearch测试服务正在运行在端口 8083"
    exit 1
fi

echo -e "${GREEN}服务正在运行${NC}"
echo ""

# 1. 基础配置测试
echo "=== 1. 基础配置测试 ==="
test_api "$BASE_URL/api/elasticsearch/simple/health" "GET" "" "健康检查"
test_api "$BASE_URL/api/elasticsearch/simple/check-config" "GET" "" "配置检查"
test_api "$BASE_URL/api/elasticsearch/simple/app-info" "GET" "" "应用信息"

# 2. Elasticsearch连接测试
echo "=== 2. Elasticsearch连接测试 ==="
test_api "$BASE_URL/api/elasticsearch/test/connection" "GET" "" "ES连接测试"

# 3. 索引管理测试
echo "=== 3. 索引管理测试 ==="
test_api "$BASE_URL/api/elasticsearch/test/indices" "GET" "" "获取索引列表"
test_api "$BASE_URL/api/elasticsearch/test/index/test-index" "POST" "" "创建测试索引"

# 4. 文档操作测试
echo "=== 4. 文档操作测试 ==="

# 保存单个文档
test_data='{
  "title": "测试文档",
  "content": "这是一个测试文档的内容",
  "category": "测试分类",
  "status": "active",
  "priority": 1
}'
test_api "$BASE_URL/api/elasticsearch/test/document" "POST" "$test_data" "保存单个文档"

# 批量保存文档
batch_data='[
  {
    "title": "批量测试文档1",
    "content": "第一个批量测试文档",
    "category": "批量分类",
    "status": "active",
    "priority": 1
  },
  {
    "title": "批量测试文档2",
    "content": "第二个批量测试文档",
    "category": "批量分类",
    "status": "active",
    "priority": 2
  }
]'
test_api "$BASE_URL/api/elasticsearch/test/documents/batch" "POST" "$batch_data" "批量保存文档"

# 查询文档
test_api "$BASE_URL/api/elasticsearch/test/document/test-id" "GET" "" "根据ID查询文档"
test_api "$BASE_URL/api/elasticsearch/test/documents?page=0&size=10" "GET" "" "分页查询文档"
test_api "$BASE_URL/api/elasticsearch/test/documents/search?keyword=测试&page=0&size=10" "GET" "" "搜索文档"

echo "=== 测试完成 ==="
echo ""
echo "如果看到很多绿色的 ✓，说明基础配置正常"
echo "如果看到红色的 ✗，请检查："
echo "1. Elasticsearch 是否在 localhost:9200 运行"
echo "2. Redis 是否在 localhost:6379 运行"
echo "3. 服务启动日志是否有错误"
echo ""
echo "访问 Swagger 文档: $BASE_URL/doc.html"
echo ""
echo "启动命令: ./start-es-test.sh"