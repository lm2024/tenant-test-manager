

-- 请求网址
-- https://www.tapd.cn/api/entity/tracking/get_timesheet?needRepeatInterceptors=false&workspace_id=60498179&program_id=&entity_id=1160498179001000009&entity_type=bug
-- 请求方法
-- GET

-- needRepeatInterceptors=false&workspace_id=60498179&program_id=&entity_id=1160498179001000009&entity_type=bug



{
    "data": {
        "time_list": [],
        "metrology": "day",
        "entity_effort_info": {
            "id": "1160498179001000009",
            "effort": "",
            "effort_completed": "0",
            "exceed": "0",
            "progress": "0",
            "remain": "0"
        }
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749774307"
}
我需要mysql表设计。 ddl   dml  





-- 实体工作量主表
CREATE TABLE entity_effort (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    entity_id VARCHAR(32) NOT NULL COMMENT '关联实体ID',
    entity_type ENUM('bug', 'task', 'story') NOT NULL DEFAULT 'bug' COMMENT '实体类型',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '工作空间ID',
    effort VARCHAR(255) DEFAULT '' COMMENT '总工作量描述',
    effort_completed DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '已完成工作量',
    exceed DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '超额工作量',
    progress TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '进度百分比(0-100)',
    remain DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '剩余工作量',
    metrology ENUM('day', 'hour', 'minute') NOT NULL DEFAULT 'day' COMMENT '计量单位',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    
    INDEX idx_entity (entity_id, workspace_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='实体工作量主表';


-- 工时明细表（预留）
CREATE TABLE time_tracking (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    effort_id BIGINT UNSIGNED NOT NULL COMMENT '关联工作量ID',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    duration DECIMAL(5,2) NOT NULL COMMENT '工时数',
    user_id VARCHAR(32) NOT NULL COMMENT '操作人ID',
    
    FOREIGN KEY (effort_id) REFERENCES entity_effort(id) ON DELETE CASCADE,
    INDEX idx_time (start_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工时明细表';




INSERT INTO entity_effort (
    entity_id, entity_type, workspace_id, 
    effort, effort_completed, exceed, progress, remain, metrology
) VALUES (
    '1160498179001000009',
    'bug',
    60498179,
    '',
    0.00,
    0.00,
    0,
    0.00,
    'day'
);

SET @effort_id = LAST_INSERT_ID();



















































