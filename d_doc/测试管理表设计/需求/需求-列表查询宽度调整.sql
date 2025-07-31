请求网址
https://www.tapd.cn/api/basic/drag_config/update_config
请求方法
POST

{"config":{"name":400,"owner":150,"iteration_id":194,"id":120,"priority":120,"status":120,"begin":120,"due":120},"target":"story/normal","workspace_id":"60498179","dsc_token":"B14PcWsEU6IsFDYQ"}


{"data":"","meta":{"code":"0","message":"success"},"timestamp":"1749779196"}
我需要mysql表设计。 ddl   dml  


CREATE TABLE drag_config_updates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    config_name INT NOT NULL COMMENT '配置名称ID',
    config_owner INT NOT NULL COMMENT '负责人ID',
    iteration_id INT NOT NULL COMMENT '迭代ID',
    config_id INT NOT NULL COMMENT '配置项ID',
    priority_val INT NOT NULL COMMENT '优先级值',
    status_val INT NOT NULL COMMENT '状态值',
    begin_val INT NOT NULL COMMENT '开始时间值',
    due_val INT NOT NULL COMMENT '截止时间值',
    target VARCHAR(50) NOT NULL DEFAULT 'story/normal' COMMENT '目标类型',
    workspace_id CHAR(8) NOT NULL COMMENT '工作空间ID',
    dsc_token CHAR(15) NOT NULL COMMENT '安全令牌',
    response_data TEXT COMMENT '响应数据',
    meta_code CHAR(1) NOT NULL DEFAULT '0' COMMENT '响应状态码',
    meta_message VARCHAR(50) NOT NULL DEFAULT 'success' COMMENT '响应消息',
    response_timestamp BIGINT UNSIGNED NOT NULL COMMENT '响应时间戳',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='拖拽配置更新记录表';

-- 添加索引优化查询
CREATE INDEX idx_workspace ON drag_config_updates(workspace_id);
CREATE INDEX idx_iteration ON drag_config_updates(iteration_id);
CREATE INDEX idx_timestamp ON drag_config_updates(response_timestamp);




INSERT INTO drag_config_updates (
    config_name,
    config_owner,
    iteration_id,
    config_id,
    priority_val,
    status_val,
    begin_val,
    due_val,
    target,
    workspace_id,
    dsc_token,
    meta_code,
    meta_message,
    response_timestamp
) VALUES (
    400,        -- 配置名称ID
    150,        -- 负责人ID
    194,        -- 迭代ID
    120,        -- 配置项ID
    120,        -- 优先级值
    120,        -- 状态值
    120,        -- 开始时间值
    120,        -- 截止时间值
    'story/normal',
    '60498179',
    'B14PcWsEU6IsFDYQ',
    '0',
    'success',
    1749779196  -- 响应时间戳
);















