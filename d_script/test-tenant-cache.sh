#!/bin/bash

# 租户缓存功能测试脚本
# 使用方法: ./test-tenant-cache.sh [base_url]

BASE_URL=${1:-"http://localhost:8080"}
TENANT_ID="test-tenant-$(date +%s)"

echo "=== 租户缓存功能测试 ==="
echo "Base URL: $BASE_URL"
echo "Tenant ID: $TENANT_ID"
echo ""

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 测试函数
test_api() {
    local method=$1
    local url=$2
    local data=$3
    local description=$4
    
    echo -e "${YELLOW}测试: $description${NC}"
    echo "请求: $method $url"
    
    if [ -n "$data" ]; then
        echo "数据: $data"
        response=$(curl -s -X $method "$BASE_URL$url" \
            -H "Content-Type: application/json" \
            -d "$data")
    else
        response=$(curl -s -X $method "$BASE_URL$url")
    fi
    
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ 请求成功${NC}"
        echo "响应: $(echo $response | jq . 2>/dev/null || echo $response)"
    else
        echo -e "${RED}✗ 请求失败${NC}"
    fi
    echo ""
}

# 1. 设置租户上下文
test_api "POST" "/api/cache-test/tenant/context?tenantId=$TENANT_ID" "" "设置租户上下文"

# 2. 获取租户上下文
test_api "GET" "/api/cache-test/tenant/context" "" "获取租户上下文"

# 3. 创建测试数据
test_data='[
    {
        "name": "测试用例1",
        "description": "这是第一个测试用例",
        "status": "active",
        "priority": "high"
    },
    {
        "name": "测试用例2", 
        "description": "这是第二个测试用例",
        "status": "inactive",
        "priority": "medium"
    },
    {
        "name": "测试用例3",
        "description": "这是第三个测试用例", 
        "status": "pending",
        "priority": "low"
    }
]'

test_api "POST" "/api/cache-test/test-cases/batch" "$test_data" "批量创建测试用例"

# 4. 第一次查询（会缓存）
echo -e "${YELLOW}=== 缓存性能测试 ===${NC}"
echo "第一次查询（会缓存）..."
start_time=$(date +%s%3N)
test_api "GET" "/api/cache-test/test-cases?page=0&size=10" "" "第一次查询测试用例"
end_time=$(date +%s%3N)
first_query_time=$((end_time - start_time))
echo "第一次查询耗时: ${first_query_time}ms"
echo ""

# 5. 第二次查询（从缓存读取）
echo "第二次查询（从缓存读取）..."
start_time=$(date +%s%3N)
test_api "GET" "/api/cache-test/test-cases?page=0&size=10" "" "第二次查询测试用例"
end_time=$(date +%s%3N)
second_query_time=$((end_time - start_time))
echo "第二次查询耗时: ${second_query_time}ms"
echo ""

# 6. 获取缓存统计
test_api "GET" "/api/cache-test/cache/stats" "" "获取缓存统计信息"

# 7. 获取租户统计
test_api "GET" "/api/cache-test/tenant/stats" "" "获取租户统计信息"

# 8. 测试过滤查询
test_api "GET" "/api/cache-test/test-cases?status=active&priority=high" "" "过滤查询测试"

# 9. 清除租户缓存
test_api "DELETE" "/api/cache-test/cache/tenant/$TENANT_ID" "" "清除租户缓存"

# 10. 第三次查询（缓存已清除）
echo "第三次查询（缓存已清除）..."
start_time=$(date +%s%3N)
test_api "GET" "/api/cache-test/test-cases?page=0&size=10" "" "第三次查询测试用例"
end_time=$(date +%s%3N)
third_query_time=$((end_time - start_time))
echo "第三次查询耗时: ${third_query_time}ms"
echo ""

# 11. 运行完整演示
test_api "POST" "/api/tenant-cache-demo/demo?tenantId=$TENANT_ID" "" "运行完整演示"

# 12. 性能测试
test_api "POST" "/api/tenant-cache-demo/performance-test?tenantId=$TENANT_ID&iterations=5" "" "缓存性能测试"

# 13. 多租户测试
TENANT_LIST="$TENANT_ID,${TENANT_ID}-2,${TENANT_ID}-3"
test_api "POST" "/api/tenant-cache-demo/multi-tenant-test?tenantIds=$TENANT_LIST" "" "多租户并发测试"

# 14. 清除租户上下文
test_api "DELETE" "/api/cache-test/tenant/context" "" "清除租户上下文"

# 总结
echo -e "${GREEN}=== 测试总结 ===${NC}"
echo "租户ID: $TENANT_ID"
echo "查询性能对比:"
echo "  第一次查询: ${first_query_time}ms"
echo "  第二次查询: ${second_query_time}ms (缓存)"
echo "  第三次查询: ${third_query_time}ms (缓存清除后)"

if [ $second_query_time -lt $first_query_time ]; then
    improvement=$(( (first_query_time - second_query_time) * 100 / first_query_time ))
    echo -e "  ${GREEN}缓存性能提升: ${improvement}%${NC}"
else
    echo -e "  ${YELLOW}缓存性能提升不明显${NC}"
fi

echo ""
echo -e "${GREEN}测试完成！${NC}"
echo "请查看上述输出结果，确认各项功能是否正常工作。"