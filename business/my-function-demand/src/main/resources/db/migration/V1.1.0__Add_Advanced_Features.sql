-- =====================================================
-- 需求管理和功能管理模块 - 高级功能升级脚本
-- 版本: V1.1.0
-- 描述: 添加高级功能，包括工作流、通知、模板等
-- 作者: System Team
-- 创建时间: 2024-01-01
-- =====================================================

-- =====================================================
-- 1. 工作流表 (workflows)
-- =====================================================
DROP TABLE IF EXISTS `workflows`;
CREATE TABLE `workflows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) NOT NULL COMMENT '工作流名称',
  `description` text COMMENT '工作流描述',
  `entity_type` varchar(50) NOT NULL COMMENT '适用实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点',
  `definition` json NOT NULL COMMENT '工作流定义，JSON格式',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否激活：0-未激活, 1-激活',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认工作流：0-否, 1-是',
  `version` varchar(20) NOT NULL DEFAULT '1.0' COMMENT '版本号',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_workflows_entity_type` (`entity_type`),
  KEY `idx_workflows_is_active` (`is_active`),
  KEY `idx_workflows_is_default` (`is_default`),
  KEY `idx_workflows_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工作流表';

-- =====================================================
-- 2. 工作流实例表 (workflow_instances)
-- =====================================================
DROP TABLE IF EXISTS `workflow_instances`;
CREATE TABLE `workflow_instances` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `workflow_id` bigint(20) NOT NULL COMMENT '工作流ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `current_step` varchar(100) NOT NULL COMMENT '当前步骤',
  `status` varchar(50) NOT NULL DEFAULT 'RUNNING' COMMENT '实例状态：RUNNING-运行中, COMPLETED-已完成, SUSPENDED-已暂停, TERMINATED-已终止',
  `variables` json DEFAULT NULL COMMENT '流程变量，JSON格式',
  `started_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `completed_at` datetime DEFAULT NULL COMMENT '完成时间',
  `started_by` varchar(100) DEFAULT NULL COMMENT '启动人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_workflow_instances_workflow_id` (`workflow_id`),
  KEY `idx_workflow_instances_entity` (`entity_type`,`entity_id`),
  KEY `idx_workflow_instances_status` (`status`),
  KEY `idx_workflow_instances_current_step` (`current_step`),
  KEY `idx_workflow_instances_started_by` (`started_by`),
  CONSTRAINT `fk_workflow_instances_workflow` FOREIGN KEY (`workflow_id`) REFERENCES `workflows` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工作流实例表';

-- =====================================================
-- 3. 工作流任务表 (workflow_tasks)
-- =====================================================
DROP TABLE IF EXISTS `workflow_tasks`;
CREATE TABLE `workflow_tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `instance_id` bigint(20) NOT NULL COMMENT '工作流实例ID',
  `task_key` varchar(100) NOT NULL COMMENT '任务键',
  `task_name` varchar(200) NOT NULL COMMENT '任务名称',
  `task_type` varchar(50) NOT NULL DEFAULT 'USER_TASK' COMMENT '任务类型：USER_TASK-用户任务, SERVICE_TASK-服务任务, SCRIPT_TASK-脚本任务',
  `assignee` varchar(100) DEFAULT NULL COMMENT '任务分配人',
  `candidate_users` varchar(500) DEFAULT NULL COMMENT '候选用户，逗号分隔',
  `candidate_groups` varchar(500) DEFAULT NULL COMMENT '候选组，逗号分隔',
  `status` varchar(50) NOT NULL DEFAULT 'CREATED' COMMENT '任务状态：CREATED-已创建, ASSIGNED-已分配, COMPLETED-已完成, CANCELLED-已取消',
  `priority` int(11) NOT NULL DEFAULT '50' COMMENT '任务优先级',
  `due_date` datetime DEFAULT NULL COMMENT '截止时间',
  `form_data` json DEFAULT NULL COMMENT '表单数据，JSON格式',
  `comments` text COMMENT '任务备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `assigned_at` datetime DEFAULT NULL COMMENT '分配时间',
  `completed_at` datetime DEFAULT NULL COMMENT '完成时间',
  `completed_by` varchar(100) DEFAULT NULL COMMENT '完成人',
  PRIMARY KEY (`id`),
  KEY `idx_workflow_tasks_instance_id` (`instance_id`),
  KEY `idx_workflow_tasks_task_key` (`task_key`),
  KEY `idx_workflow_tasks_assignee` (`assignee`),
  KEY `idx_workflow_tasks_status` (`status`),
  KEY `idx_workflow_tasks_priority` (`priority`),
  KEY `idx_workflow_tasks_due_date` (`due_date`),
  CONSTRAINT `fk_workflow_tasks_instance` FOREIGN KEY (`instance_id`) REFERENCES `workflow_instances` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工作流任务表';

-- =====================================================
-- 4. 通知表 (notifications)
-- =====================================================
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `recipient` varchar(100) NOT NULL COMMENT '接收人',
  `sender` varchar(100) DEFAULT NULL COMMENT '发送人',
  `title` varchar(500) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知内容',
  `type` varchar(50) NOT NULL DEFAULT 'INFO' COMMENT '通知类型：INFO-信息, WARNING-警告, ERROR-错误, SUCCESS-成功',
  `category` varchar(50) DEFAULT NULL COMMENT '通知分类：SYSTEM-系统, WORKFLOW-工作流, REMINDER-提醒',
  `entity_type` varchar(50) DEFAULT NULL COMMENT '关联实体类型',
  `entity_id` bigint(20) DEFAULT NULL COMMENT '关联实体ID',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已读：0-未读, 1-已读',
  `read_at` datetime DEFAULT NULL COMMENT '阅读时间',
  `priority` int(11) NOT NULL DEFAULT '3' COMMENT '优先级：1-最高, 2-高, 3-中, 4-低, 5-最低',
  `expires_at` datetime DEFAULT NULL COMMENT '过期时间',
  `action_url` varchar(500) DEFAULT NULL COMMENT '操作链接',
  `metadata` json DEFAULT NULL COMMENT '元数据，JSON格式',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_notifications_recipient` (`recipient`),
  KEY `idx_notifications_sender` (`sender`),
  KEY `idx_notifications_type` (`type`),
  KEY `idx_notifications_category` (`category`),
  KEY `idx_notifications_entity` (`entity_type`,`entity_id`),
  KEY `idx_notifications_is_read` (`is_read`),
  KEY `idx_notifications_priority` (`priority`),
  KEY `idx_notifications_created_at` (`created_at`),
  KEY `idx_notifications_expires_at` (`expires_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知表';

-- =====================================================
-- 5. 模板表 (templates)
-- =====================================================
DROP TABLE IF EXISTS `templates`;
CREATE TABLE `templates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) NOT NULL COMMENT '模板名称',
  `description` text COMMENT '模板描述',
  `type` varchar(50) NOT NULL COMMENT '模板类型：REQUIREMENT-需求模板, FUNCTION_POINT-功能点模板, CATEGORY-分类模板',
  `content` json NOT NULL COMMENT '模板内容，JSON格式',
  `is_public` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否公开：0-私有, 1-公开',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否系统模板：0-用户模板, 1-系统模板',
  `usage_count` int(11) NOT NULL DEFAULT '0' COMMENT '使用次数',
  `tags` varchar(500) DEFAULT NULL COMMENT '标签，逗号分隔',
  `version` varchar(20) NOT NULL DEFAULT '1.0' COMMENT '版本号',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_templates_type` (`type`),
  KEY `idx_templates_is_public` (`is_public`),
  KEY `idx_templates_is_system` (`is_system`),
  KEY `idx_templates_usage_count` (`usage_count`),
  KEY `idx_templates_created_by` (`created_by`),
  KEY `idx_templates_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='模板表';

-- =====================================================
-- 6. 标签表 (tags)
-- =====================================================
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `description` varchar(500) DEFAULT NULL COMMENT '标签描述',
  `color` varchar(20) DEFAULT '#1890ff' COMMENT '标签颜色',
  `category` varchar(50) DEFAULT NULL COMMENT '标签分类',
  `usage_count` int(11) NOT NULL DEFAULT '0' COMMENT '使用次数',
  `is_system` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否系统标签：0-用户标签, 1-系统标签',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tags_name` (`name`,`deleted`),
  KEY `idx_tags_category` (`category`),
  KEY `idx_tags_usage_count` (`usage_count`),
  KEY `idx_tags_is_system` (`is_system`),
  KEY `idx_tags_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- =====================================================
-- 7. 实体标签关联表 (entity_tags)
-- =====================================================
DROP TABLE IF EXISTS `entity_tags`;
CREATE TABLE `entity_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_entity_tags` (`entity_type`,`entity_id`,`tag_id`),
  KEY `idx_entity_tags_entity` (`entity_type`,`entity_id`),
  KEY `idx_entity_tags_tag_id` (`tag_id`),
  CONSTRAINT `fk_entity_tags_tag` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='实体标签关联表';

-- =====================================================
-- 8. 收藏表 (favorites)
-- =====================================================
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(100) NOT NULL COMMENT '用户ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `notes` varchar(500) DEFAULT NULL COMMENT '收藏备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_favorites` (`user_id`,`entity_type`,`entity_id`),
  KEY `idx_favorites_user_id` (`user_id`),
  KEY `idx_favorites_entity` (`entity_type`,`entity_id`),
  KEY `idx_favorites_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';

-- =====================================================
-- 9. 为现有表添加新字段
-- =====================================================

-- 为需求表添加新字段
ALTER TABLE `requirements` 
ADD COLUMN `workflow_instance_id` bigint(20) DEFAULT NULL COMMENT '工作流实例ID' AFTER `custom_fields`,
ADD COLUMN `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID' AFTER `workflow_instance_id`,
ADD COLUMN `risk_level` varchar(50) DEFAULT 'LOW' COMMENT '风险等级：LOW-低, MEDIUM-中, HIGH-高, CRITICAL-严重' AFTER `template_id`,
ADD COLUMN `business_value` int(11) DEFAULT NULL COMMENT '业务价值评分：1-10' AFTER `risk_level`,
ADD COLUMN `technical_complexity` int(11) DEFAULT NULL COMMENT '技术复杂度评分：1-10' AFTER `business_value`;

-- 为功能点表添加新字段
ALTER TABLE `function_points` 
ADD COLUMN `workflow_instance_id` bigint(20) DEFAULT NULL COMMENT '工作流实例ID' AFTER `custom_fields`,
ADD COLUMN `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID' AFTER `workflow_instance_id`,
ADD COLUMN `risk_level` varchar(50) DEFAULT 'LOW' COMMENT '风险等级：LOW-低, MEDIUM-中, HIGH-高, CRITICAL-严重' AFTER `template_id`,
ADD COLUMN `business_value` int(11) DEFAULT NULL COMMENT '业务价值评分：1-10' AFTER `risk_level`,
ADD COLUMN `technical_debt` decimal(10,2) DEFAULT NULL COMMENT '技术债务评分' AFTER `business_value`;

-- 为分类表添加新字段
ALTER TABLE `categories` 
ADD COLUMN `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID' AFTER `custom_fields`,
ADD COLUMN `metadata` json DEFAULT NULL COMMENT '元数据，JSON格式' AFTER `template_id`;

-- =====================================================
-- 10. 添加新的索引
-- =====================================================

-- 为新字段添加索引
ALTER TABLE `requirements` 
ADD KEY `idx_requirements_workflow_instance_id` (`workflow_instance_id`),
ADD KEY `idx_requirements_template_id` (`template_id`),
ADD KEY `idx_requirements_risk_level` (`risk_level`),
ADD KEY `idx_requirements_business_value` (`business_value`);

ALTER TABLE `function_points` 
ADD KEY `idx_function_points_workflow_instance_id` (`workflow_instance_id`),
ADD KEY `idx_function_points_template_id` (`template_id`),
ADD KEY `idx_function_points_risk_level` (`risk_level`),
ADD KEY `idx_function_points_business_value` (`business_value`);

ALTER TABLE `categories` 
ADD KEY `idx_categories_template_id` (`template_id`);

-- =====================================================
-- 11. 添加外键约束
-- =====================================================

ALTER TABLE `requirements` 
ADD CONSTRAINT `fk_requirements_workflow_instance` FOREIGN KEY (`workflow_instance_id`) REFERENCES `workflow_instances` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `fk_requirements_template` FOREIGN KEY (`template_id`) REFERENCES `templates` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `function_points` 
ADD CONSTRAINT `fk_function_points_workflow_instance` FOREIGN KEY (`workflow_instance_id`) REFERENCES `workflow_instances` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `fk_function_points_template` FOREIGN KEY (`template_id`) REFERENCES `templates` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `categories` 
ADD CONSTRAINT `fk_categories_template` FOREIGN KEY (`template_id`) REFERENCES `templates` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- =====================================================
-- 12. 创建新的视图
-- =====================================================

-- 工作流统计视图
CREATE VIEW `v_workflow_statistics` AS
SELECT 
    w.name as workflow_name,
    w.entity_type,
    COUNT(wi.id) as total_instances,
    COUNT(CASE WHEN wi.status = 'RUNNING' THEN 1 END) as running_instances,
    COUNT(CASE WHEN wi.status = 'COMPLETED' THEN 1 END) as completed_instances,
    COUNT(CASE WHEN wi.status = 'SUSPENDED' THEN 1 END) as suspended_instances,
    COUNT(CASE WHEN wi.status = 'TERMINATED' THEN 1 END) as terminated_instances,
    AVG(TIMESTAMPDIFF(HOUR, wi.started_at, wi.completed_at)) as avg_completion_hours
FROM workflows w
LEFT JOIN workflow_instances wi ON w.id = wi.workflow_id
WHERE w.deleted = 0
GROUP BY w.id, w.name, w.entity_type;

-- 通知统计视图
CREATE VIEW `v_notification_statistics` AS
SELECT 
    recipient,
    type,
    category,
    COUNT(*) as total_count,
    COUNT(CASE WHEN is_read = 0 THEN 1 END) as unread_count,
    COUNT(CASE WHEN is_read = 1 THEN 1 END) as read_count,
    MAX(created_at) as latest_notification
FROM notifications
GROUP BY recipient, type, category;

-- 标签使用统计视图
CREATE VIEW `v_tag_usage_statistics` AS
SELECT 
    t.name as tag_name,
    t.category as tag_category,
    t.color as tag_color,
    COUNT(et.id) as usage_count,
    COUNT(CASE WHEN et.entity_type = 'REQUIREMENT' THEN 1 END) as requirement_count,
    COUNT(CASE WHEN et.entity_type = 'FUNCTION_POINT' THEN 1 END) as function_point_count,
    COUNT(CASE WHEN et.entity_type = 'CATEGORY' THEN 1 END) as category_count
FROM tags t
LEFT JOIN entity_tags et ON t.id = et.tag_id
WHERE t.deleted = 0
GROUP BY t.id, t.name, t.category, t.color;

-- =====================================================
-- 13. 插入初始数据
-- =====================================================

-- 插入系统标签
INSERT INTO `tags` (`name`, `description`, `color`, `category`, `is_system`, `created_by`, `created_at`, `updated_at`) VALUES
('高优先级', '高优先级项目', '#f5222d', 'priority', 1, 'system', NOW(), NOW()),
('中优先级', '中优先级项目', '#fa8c16', 'priority', 1, 'system', NOW(), NOW()),
('低优先级', '低优先级项目', '#52c41a', 'priority', 1, 'system', NOW(), NOW()),
('紧急', '紧急处理项目', '#f5222d', 'urgency', 1, 'system', NOW(), NOW()),
('重要', '重要项目', '#fa541c', 'importance', 1, 'system', NOW(), NOW()),
('核心功能', '系统核心功能', '#1890ff', 'feature', 1, 'system', NOW(), NOW()),
('辅助功能', '系统辅助功能', '#722ed1', 'feature', 1, 'system', NOW(), NOW()),
('性能相关', '性能优化相关', '#13c2c2', 'performance', 1, 'system', NOW(), NOW()),
('安全相关', '安全功能相关', '#eb2f96', 'security', 1, 'system', NOW(), NOW()),
('用户体验', '用户体验相关', '#52c41a', 'ux', 1, 'system', NOW(), NOW());

-- 插入默认工作流
INSERT INTO `workflows` (`name`, `description`, `entity_type`, `definition`, `is_active`, `is_default`, `version`, `created_by`, `created_at`, `updated_at`) VALUES
('需求标准流程', '需求从创建到完成的标准工作流程', 'REQUIREMENT', '{"steps": [{"key": "draft", "name": "草稿", "type": "start"}, {"key": "review", "name": "评审", "type": "user_task", "assignee": "reviewer"}, {"key": "approved", "name": "已批准", "type": "user_task"}, {"key": "implementation", "name": "实现", "type": "user_task", "assignee": "developer"}, {"key": "testing", "name": "测试", "type": "user_task", "assignee": "tester"}, {"key": "completed", "name": "完成", "type": "end"}]}', 1, 1, '1.0', 'system', NOW(), NOW()),
('功能点标准流程', '功能点从规划到完成的标准工作流程', 'FUNCTION_POINT', '{"steps": [{"key": "planning", "name": "规划中", "type": "start"}, {"key": "design", "name": "设计", "type": "user_task", "assignee": "architect"}, {"key": "development", "name": "开发中", "type": "user_task", "assignee": "developer"}, {"key": "testing", "name": "测试中", "type": "user_task", "assignee": "tester"}, {"key": "completed", "name": "已完成", "type": "end"}]}', 1, 1, '1.0', 'system', NOW(), NOW());

-- 插入系统模板
INSERT INTO `templates` (`name`, `description`, `type`, `content`, `is_public`, `is_system`, `version`, `created_by`, `created_at`, `updated_at`) VALUES
('标准需求模板', '标准的需求文档模板', 'REQUIREMENT', '{"title": "", "description": "", "type": "FUNCTIONAL", "status": "DRAFT", "priority": 3, "fields": [{"name": "背景", "type": "text", "required": true}, {"name": "目标", "type": "text", "required": true}, {"name": "范围", "type": "text", "required": false}, {"name": "约束", "type": "text", "required": false}, {"name": "验收标准", "type": "text", "required": true}]}', 1, 1, '1.0', 'system', NOW(), NOW()),
('功能点模板', '标准的功能点文档模板', 'FUNCTION_POINT', '{"name": "", "description": "", "type": "FEATURE", "status": "PLANNING", "priority": 3, "complexity": "MEDIUM", "fields": [{"name": "功能描述", "type": "text", "required": true}, {"name": "输入输出", "type": "text", "required": true}, {"name": "业务规则", "type": "text", "required": false}, {"name": "异常处理", "type": "text", "required": false}, {"name": "性能要求", "type": "text", "required": false}]}', 1, 1, '1.0', 'system', NOW(), NOW()),
('分类模板', '标准的分类模板', 'CATEGORY', '{"name": "", "description": "", "type": "REQUIREMENT", "fields": [{"name": "分类说明", "type": "text", "required": true}, {"name": "适用范围", "type": "text", "required": false}, {"name": "管理规则", "type": "text", "required": false}]}', 1, 1, '1.0', 'system', NOW(), NOW());

-- =====================================================
-- 脚本执行完成
-- =====================================================