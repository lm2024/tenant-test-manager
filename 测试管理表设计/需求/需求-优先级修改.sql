请求网址
https://www.tapd.cn/api/entity/inline_edit/story_update
请求方法
POST

{"workspace_id":"60498179","id":"1160498179001000078","value":"Low","field":"priority","originField":"priority","oldValue":"Middle","field_name_is_alias":0,"dsc_token":"B14PcWsEU6IsFDYQ"}

{
    "data": {
        "id": "1160498179001000078",
        "secret_root_id": "0",
        "sort": "100007800000",
        "workitem_type_id": "1160498179001000018",
        "name": "\u3010\u793a\u4f8b\u3011\u6821\u56ed\u5c0f\u62cd\u5356\u5bb6\u5e94\u7528",
        "description_type": "1",
        "creator": "TAPD",
        "created": "2025-05-30 11:17:16",
        "modified": "2025-06-13 09:49:13",
        "parent_id": "0",
        "children_id": "|1160498179001000081|1160498179001000080|1160498179001000147",
        "ancestor_id": "1160498179001000078",
        "path": "1160498179001000078",
        "level": "0",
        "workspace_id": "60498179",
        "app_id": "1",
        "status": "\u89c4\u5212\u4e2d",
        "step": "",
        "flows": "",
        "priority": "Low",
        "owner": "",
        "participator": "",
        "cc": "",
        "begin": "",
        "due": "2025-07-12",
        "source": "",
        "workitem_id": "",
        "iteration_id": "0",
        "issue_id": "",
        "support_id": "",
        "support_forum_id": "",
        "module": "",
        "completed": "",
        "templated_id": "1160498179001000024",
        "delay_count": "",
        "type": "",
        "status_append": "",
        "business_value": "",
        "tech_risk": "",
        "size": "",
        "import_flag": "0",
        "effort": "6",
        "effort_completed": "0",
        "exceed": "0",
        "remain": "6",
        "progress": "0",
        "release_id": "0",
        "feature": "",
        "entity_type": "Story",
        "custom_field_one": "",
        "custom_field_two": "",
        "custom_field_three": "",
        "custom_field_four": "",
        "custom_field_five": "",
        "custom_field_six": "",
        "custom_field_seven": "",
        "custom_field_eight": "",
        "custom_plan_field_1": "0",
        "custom_plan_field_2": "0",
        "custom_plan_field_3": "0",
        "custom_plan_field_4": "0",
        "custom_plan_field_5": "0",
        "custom_plan_field_6": "0",
        "custom_plan_field_7": "0",
        "custom_plan_field_8": "0",
        "custom_plan_field_9": "0",
        "custom_plan_field_10": "0",
        "attachment_count": "0",
        "has_attachment": "0",
        "developer": "",
        "bug_id": "",
        "test_focus": "",
        "category_id": "-1",
        "version": "",
        "confidential": "N",
        "created_from": "",
        "follower": "",
        "sync_type": "",
        "predecessor_count": "0",
        "successor_count": "0",
        "label": "",
        "is_archived": "0",
        "modifier": "TAPD",
        "progress_manual": "0",
        "delay": "0",
        "is_done_status": "0",
        "status_alias": "planning"
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749780175"
}
我需要mysql表设计。 ddl   dml



-- 主表：存储故事核心信息
CREATE TABLE `stories` (
  `id` VARCHAR(20) PRIMARY KEY COMMENT '故事ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作空间ID',
  `name` VARCHAR(255) NOT NULL COMMENT '故事名称',
  `status` VARCHAR(50) NOT NULL COMMENT '状态',
  `priority` VARCHAR(20) NOT NULL COMMENT '优先级',
  `owner` VARCHAR(100) DEFAULT NULL COMMENT '负责人',
  `due_date` DATE DEFAULT NULL COMMENT '截止日期',
  `effort` INT DEFAULT 0 COMMENT '预估工作量',
  `progress` INT DEFAULT 0 COMMENT '进度百分比',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `modified_at` DATETIME NOT NULL COMMENT '修改时间',
  `entity_type` VARCHAR(20) NOT NULL DEFAULT 'Story' COMMENT '实体类型',
  `attachment_count` INT DEFAULT 0 COMMENT '附件数量',
  KEY `idx_workspace` (`workspace_id`),
  KEY `idx_due_date` (`due_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故事主表';

-- 扩展表：存储动态自定义字段
CREATE TABLE `story_custom_fields` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `story_id` VARCHAR(20) NOT NULL COMMENT '故事ID',
  `field_name` VARCHAR(50) NOT NULL COMMENT '字段名',
  `field_value` TEXT COMMENT '字段值',
  KEY `fk_story_custom` (`story_id`),
  CONSTRAINT `fk_story_custom` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故事自定义字段表';

-- 操作日志表：记录字段变更历史
CREATE TABLE `story_updates` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `story_id` VARCHAR(20) NOT NULL COMMENT '故事ID',
  `changed_field` VARCHAR(50) NOT NULL COMMENT '变更字段',
  `old_value` TEXT COMMENT '旧值',
  `new_value` TEXT NOT NULL COMMENT '新值',
  `changed_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '变更时间',
  KEY `fk_story_update` (`story_id`),
  CONSTRAINT `fk_story_update` FOREIGN KEY (`story_id`) REFERENCES `stories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故事变更日志表';




INSERT INTO `stories` (
  `id`, `workspace_id`, `name`, `status`, `priority`, 
  `due_date`, `effort`, `created_at`, `modified_at`
) VALUES (
  '1160498179001000078', 
  '60498179', 
  '【示例】校园小拍卖家应用', 
  '规划中', 
  'Low', 
  '2025-07-12', 
  6, 
  '2025-05-30 11:17:16', 
  '2025-06-13 09:49:13'
);


























