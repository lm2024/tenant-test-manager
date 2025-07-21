请求网址
https://www.tapd.cn/api/new_filter/new_filter/get_options
请求方法
POST

{"entity_type":"story","workspace_ids":["60498179"],"field":"iteration_id","is_system":1,"use_scene":"story_list","Filter":{"iteration_id":{"use_page":1,"Filter":{"OR":{"created":">= 2022-06-14","AND":{"startdate":"<= 2025-06-14","enddate":">= 2025-06-14"}}},"page":1,"perpage":800}},"app_id":"1","dsc_token":"B14PcWsEU6IsFDYQ"}


{
    "data": {
        "iteration_id": [
            {
                "label": "\u5168\u9009",
                "value": "9999998"
            },
            {
                "label": "-\u7a7a-",
                "value": "9999999"
            },
            {
                "label": "\u5f53\u524d\u8fed\u4ee3",
                "value": "9999996"
            },
            {
                "parent_id": "0",
                "workspace_id": "60498179",
                "sort": "100001100000",
                "display": "1",
                "value": "1160498179001000023",
                "label": "\u3010\u4ea7\u54c1\u3011+\u5f00\u53d1\u73af\u5883+\u7248\u672c\u53f7\uff08\u5982\uff1a\u3010\u9014\u5f3a\u5728\u7ebf\u3011Web V4.0.0\uff09",
                "panel": "0"
            },
            {
                "parent_id": "0",
                "workspace_id": "60498179",
                "sort": "100000800000",
                "display": "1",
                "value": "1160498179001000008",
                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                "panel": "0"
            },
            {
                "parent_id": "0",
                "workspace_id": "60498179",
                "sort": "100001000000",
                "display": "1",
                "value": "1160498179001000010",
                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                "panel": "0"
            },
            {
                "parent_id": "0",
                "workspace_id": "60498179",
                "sort": "100000700000",
                "display": "1",
                "value": "1160498179001000007",
                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                "panel": "0"
            },
            {
                "parent_id": "0",
                "workspace_id": "60498179",
                "sort": "100000900000",
                "display": "1",
                "value": "1160498179001000009",
                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                "panel": "0"
            }
        ]
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749777229"
}
我需要mysql表设计。 ddl   dml  


CREATE TABLE iteration_options (
    id VARCHAR(50) PRIMARY KEY COMMENT '迭代ID (从value字段映射)',
    label VARCHAR(255) NOT NULL COMMENT '显示名称',
    parent_id VARCHAR(50) DEFAULT NULL COMMENT '父迭代ID',
    workspace_id VARCHAR(20) DEFAULT NULL COMMENT '工作空间ID',
    sort VARCHAR(20) DEFAULT NULL COMMENT '排序值',
    is_display TINYINT(1) DEFAULT 1 COMMENT '是否显示 (0/1)',
    panel VARCHAR(10) DEFAULT '0' COMMENT '面板标识',
    category ENUM('special', 'normal') NOT NULL DEFAULT 'normal' COMMENT '类型：special-特殊选项, normal-普通迭代',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='迭代选项数据表';

CREATE INDEX idx_parent ON iteration_options(parent_id);
CREATE INDEX idx_workspace ON iteration_options(workspace_id);

-- 特殊选项
INSERT INTO iteration_options (id, label, category) VALUES
('9999998', '全选', 'special'),
('9999999', '-空-', 'special'),
('9999996', '当前迭代', 'special');

-- 普通迭代选项
INSERT INTO iteration_options (
    id, label, parent_id, workspace_id, sort, is_display, panel
) VALUES
('1160498179001000023', '【产品】+开发环境+版本号（如：【途强在线】Web V4.0.0', '0', '60498179', '100001100000', 1, '0'),
('1160498179001000008', '【买家应用】迭代2', '0', '60498179', '100000800000', 1, '0'),
('1160498179001000010', '【卖家应用】迭代2', '0', '60498179', '100001000000', 1, '0'),
('1160498179001000007', '【买家应用】迭代1', '0', '60498179', '100000700000', 1, '0'),
('1160498179001000009', '【卖家应用】迭代1', '0', '60498179', '100000900000', 1, '0');









