#!/bin/bash

echo "=== 启动Elasticsearch测试服务 ==="
echo ""

# 检查Java版本
echo "检查Java版本..."
java -version

echo ""
echo "编译项目..."
cd my-test-execute
mvn clean compile -DskipTests

echo ""
echo "启动Elasticsearch测试服务..."
echo "服务将在端口8083启动"
echo "Swagger文档地址: http://localhost:8083/doc.html"
echo ""

# 启动服务
mvn spring-boot:run \
  -Dspring-boot.run.main-class=com.tenant.test.TestExecuteApp