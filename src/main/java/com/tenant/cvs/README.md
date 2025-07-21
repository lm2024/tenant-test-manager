# com.tenant.cvs 高性能导入导出模块

本模块所有代码均在 `com.tenant.cvs` 包下，完全不影响现有业务。

## 目录结构

```
com.tenant.cvs
├── controller      # 导入导出API接口
├── service         # 业务处理、调度、并发控制
├── queue           # 任务队列、并发管理
├── util            # 工具类（文件、Excel、CSV、TXT、字段映射等）
├── model           # 任务、进度、文件、导入导出参数等模型
├── config          # 配置类（线程池、队列、参数等）
```

## 功能总览

- 支持Excel、CSV、TXT高性能导入导出
- Excel单批2万，CSV/TXT单批5万（可自定义）
- 支持多文件上传（最多20个，可自定义）
- 文件先上传到服务器指定目录，后续异步处理
- 导入并发1，导出并发10（可自定义）
- 支持进度查询、任务暂停、终止、删除
- 导入支持根据ID批量更新
- 字段中文名自动映射实体字段
- 导出支持字段转义、枚举转义
- 导入导出完成后自动删除临时文件
- 推荐使用Redis做任务队列和进度存储

## 典型流程

1. 上传文件（支持多文件）
2. 文件入队，异步导入/导出
3. 查询进度、下载结果、暂停/终止/删除任务

## 依赖
- hutool（文件、Excel工具）
- easyExcel（高性能Excel）
- commons-csv（高性能CSV）
- spring-data-redis（任务队列/进度）
- lombok

## 入口API
- `/api/com.tenant.cvs/import`  导入接口
- `/api/com.tenant.cvs/export`  导出接口
- `/api/com.tenant.cvs/progress/{taskId}`  进度查询
- `/api/com.tenant.cvs/stop/{taskId}`  暂停/终止任务
- `/api/com.tenant.cvs/delete/{taskId}`  删除任务
- `/api/com.tenant.cvs/download/{taskId}`  下载导出结果

## 说明
- 仅com.cvs包下代码与导入导出相关，绝不影响其他业务
- 详细用法见各controller和README 