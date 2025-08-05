-- =====================================================
-- 租户数据库创建脚本
-- 描述: 为新租户创建独立的数据库和表结构
-- 使用方法: 替换 {TENANT_ID} 为实际的租户ID
-- 作者: System Team
-- 创建时间: 2024-01-01
-- =====================================================

-- 注意：执行此脚本前，请将 {TENANT_ID} 替换为实际的租户ID
-- 例如：将 function_demand_tenant_{TENANT_ID} 替换为 function_demand_tenant_001

-- =====================================================
-- 1. 创建租户数据库
-- =====================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `function_demand_tenant_{TENANT_ID}` 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `function_demand_tenant_{TENANT_ID}`;

-- =====================================================
-- 2. 创建租户用户（可选）
-- =====================================================

-- 创建专用数据库用户（生产环境建议使用）
-- CREATE USER IF NOT EXISTS 'tenant_{TENANT_ID}'@'%' IDENTIFIED BY 'secure_password_here';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON `function_demand_tenant_{TENANT_ID}`.* TO 'tenant_{TENANT_ID}'@'%';
-- FLUSH PRIVILEGES;

-- =====================================================
-- 3. 创建表结构（与主表结构相同）
-- =====================================================

-- 设置字符集和排序规则
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 需求表
CREATE TABLE `requirements` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(500) NOT NULL COMMENT '需求标题',
  `description` text COMMENT '需求描述',
  `type` varchar(50) NOT NULL DEFAULT 'FUNCTIONAL' COMMENT '需求类型：FUNCTIONAL-功能性需求, NON_FUNCTIONAL-非功能性需求',
  `status` varchar(50) NOT NULL DEFAULT 'DRAFT' COMMENT '需求状态：DRAFT-草稿, APPROVED-已批准, REJECTED-已拒绝, IMPLEMENTED-已实现, TESTED-已测试, CLOSED-已关闭',
  `priority` int(11) NOT NULL DEFAULT '3' COMMENT '优先级：1-最高, 2-高, 3-中, 4-低, 5-最低',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父需求ID',
  `path` varchar(1000) NOT NULL DEFAULT '/' COMMENT '树形路径',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '层级深度',
  `sort_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序顺序',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `estimated_hours` decimal(10,2) DEFAULT NULL COMMENT '预估工时',
  `actual_hours` decimal(10,2) DEFAULT NULL COMMENT '实际工时',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `assignee` varchar(100) DEFAULT NULL COMMENT '负责人',
  `reporter` varchar(100) DEFAULT NULL COMMENT '报告人',
  `tags` varchar(500) DEFAULT NULL COMMENT '标签，逗号分隔',
  `custom_fields` json DEFAULT NULL COMMENT '自定义字段，JSON格式',
  `workflow_instance_id` bigint(20) DEFAULT NULL COMMENT '工作流实例ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `risk_level` varchar(50) DEFAULT 'LOW' COMMENT '风险等级：LOW-低, MEDIUM-中, HIGH-高, CRITICAL-严重',
  `business_value` int(11) DEFAULT NULL COMMENT '业务价值评分：1-10',
  `technical_complexity` int(11) DEFAULT NULL COMMENT '技术复杂度评分：1-10',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号，用于乐观锁',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_requirements_parent_id` (`parent_id`),
  KEY `idx_requirements_path` (`path`(255)),
  KEY `idx_requirements_level` (`level`),
  KEY `idx_requirements_type` (`type`),
  KEY `idx_requirements_status` (`status`),
  KEY `idx_requirements_priority` (`priority`),
  KEY `idx_requirements_category_id` (`category_id`),
  KEY `idx_requirements_assignee` (`assignee`),
  KEY `idx_requirements_created_at` (`created_at`),
  KEY `idx_requirements_updated_at` (`updated_at`),
  KEY `idx_requirements_deleted` (`deleted`),
  KEY `idx_requirements_title` (`title`(100)),
  KEY `idx_requirements_workflow_instance_id` (`workflow_instance_id`),
  KEY `idx_requirements_template_id` (`template_id`),
  KEY `idx_requirements_risk_level` (`risk_level`),
  KEY `idx_requirements_business_value` (`business_value`),
  CONSTRAINT `fk_requirements_parent` FOREIGN KEY (`parent_id`) REFERENCES `requirements` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='需求表';

-- 功能点表
CREATE TABLE `function_points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(500) NOT NULL COMMENT '功能点名称',
  `description` text COMMENT '功能点描述',
  `type` varchar(50) NOT NULL DEFAULT 'FEATURE' COMMENT '功能点类型：FEATURE-功能特性, MODULE-功能模块, COMPONENT-组件, SERVICE-服务',
  `status` varchar(50) NOT NULL DEFAULT 'PLANNING' COMMENT '功能点状态：PLANNING-规划中, DEVELOPMENT-开发中, TESTING-测试中, COMPLETED-已完成, CANCELLED-已取消',
  `priority` int(11) NOT NULL DEFAULT '3' COMMENT '优先级：1-最高, 2-高, 3-中, 4-低, 5-最低',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父功能点ID',
  `path` varchar(1000) NOT NULL DEFAULT '/' COMMENT '树形路径',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '层级深度',
  `sort_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序顺序',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `complexity` varchar(50) DEFAULT 'MEDIUM' COMMENT '复杂度：LOW-低, MEDIUM-中, HIGH-高, VERY_HIGH-很高',
  `estimated_points` decimal(10,2) DEFAULT NULL COMMENT '预估功能点数',
  `actual_points` decimal(10,2) DEFAULT NULL COMMENT '实际功能点数',
  `estimated_hours` decimal(10,2) DEFAULT NULL COMMENT '预估工时',
  `actual_hours` decimal(10,2) DEFAULT NULL COMMENT '实际工时',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `assignee` varchar(100) DEFAULT NULL COMMENT '负责人',
  `reviewer` varchar(100) DEFAULT NULL COMMENT '评审人',
  `tags` varchar(500) DEFAULT NULL COMMENT '标签，逗号分隔',
  `custom_fields` json DEFAULT NULL COMMENT '自定义字段，JSON格式',
  `workflow_instance_id` bigint(20) DEFAULT NULL COMMENT '工作流实例ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `risk_level` varchar(50) DEFAULT 'LOW' COMMENT '风险等级：LOW-低, MEDIUM-中, HIGH-高, CRITICAL-严重',
  `business_value` int(11) DEFAULT NULL COMMENT '业务价值评分：1-10',
  `technical_debt` decimal(10,2) DEFAULT NULL COMMENT '技术债务评分',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号，用于乐观锁',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_function_points_parent_id` (`parent_id`),
  KEY `idx_function_points_path` (`path`(255)),
  KEY `idx_function_points_level` (`level`),
  KEY `idx_function_points_type` (`type`),
  KEY `idx_function_points_status` (`status`),
  KEY `idx_function_points_priority` (`priority`),
  KEY `idx_function_points_category_id` (`category_id`),
  KEY `idx_function_points_complexity` (`complexity`),
  KEY `idx_function_points_assignee` (`assignee`),
  KEY `idx_function_points_created_at` (`created_at`),
  KEY `idx_function_points_updated_at` (`updated_at`),
  KEY `idx_function_points_deleted` (`deleted`),
  KEY `idx_function_points_name` (`name`(100)),
  KEY `idx_function_points_workflow_instance_id` (`workflow_instance_id`),
  KEY `idx_function_points_template_id` (`template_id`),
  KEY `idx_function_points_risk_level` (`risk_level`),
  KEY `idx_function_points_business_value` (`business_value`),
  CONSTRAINT `fk_function_points_parent` FOREIGN KEY (`parent_id`) REFERENCES `function_points` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='功能点表';

-- 分类表
CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) NOT NULL COMMENT '分类名称',
  `description` text COMMENT '分类描述',
  `type` varchar(50) NOT NULL COMMENT '分类类型：REQUIREMENT-需求分类, FUNCTION_POINT-功能点分类',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID',
  `path` varchar(1000) NOT NULL DEFAULT '/' COMMENT '树形路径',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '层级深度',
  `sort_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序顺序',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `color` varchar(20) DEFAULT NULL COMMENT '颜色',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否激活：0-未激活, 1-激活',
  `custom_fields` json DEFAULT NULL COMMENT '自定义字段，JSON格式',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `metadata` json DEFAULT NULL COMMENT '元数据，JSON格式',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号，用于乐观锁',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_categories_parent_id` (`parent_id`),
  KEY `idx_categories_path` (`path`(255)),
  KEY `idx_categories_level` (`level`),
  KEY `idx_categories_type` (`type`),
  KEY `idx_categories_is_active` (`is_active`),
  KEY `idx_categories_created_at` (`created_at`),
  KEY `idx_categories_updated_at` (`updated_at`),
  KEY `idx_categories_deleted` (`deleted`),
  KEY `idx_categories_name` (`name`),
  KEY `idx_categories_template_id` (`template_id`),
  CONSTRAINT `fk_categories_parent` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- 需求与功能点关联表
CREATE TABLE `requirement_function_relations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `requirement_id` bigint(20) NOT NULL COMMENT '需求ID',
  `function_point_id` bigint(20) NOT NULL COMMENT '功能点ID',
  `relation_type` varchar(50) NOT NULL DEFAULT 'IMPLEMENTS' COMMENT '关联类型：IMPLEMENTS-实现, DEPENDS-依赖, RELATES-相关',
  `description` varchar(500) DEFAULT NULL COMMENT '关联描述',
  `weight` decimal(5,2) DEFAULT '1.00' COMMENT '关联权重',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否激活：0-未激活, 1-激活',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_requirement_function_relation` (`requirement_id`,`function_point_id`,`relation_type`,`deleted`),
  KEY `idx_requirement_function_relations_requirement_id` (`requirement_id`),
  KEY `idx_requirement_function_relations_function_point_id` (`function_point_id`),
  KEY `idx_requirement_function_relations_relation_type` (`relation_type`),
  KEY `idx_requirement_function_relations_is_active` (`is_active`),
  KEY `idx_requirement_function_relations_created_at` (`created_at`),
  KEY `idx_requirement_function_relations_deleted` (`deleted`),
  CONSTRAINT `fk_requirement_function_relations_requirement` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_requirement_function_relations_function_point` FOREIGN KEY (`function_point_id`) REFERENCES `function_points` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='需求与功能点关联表';

-- 附件表
CREATE TABLE `attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `file_name` varchar(500) NOT NULL COMMENT '文件名',
  `original_name` varchar(500) NOT NULL COMMENT '原始文件名',
  `file_path` varchar(1000) NOT NULL COMMENT '文件路径',
  `file_size` bigint(20) NOT NULL COMMENT '文件大小（字节）',
  `file_type` varchar(100) NOT NULL COMMENT '文件类型',
  `mime_type` varchar(200) DEFAULT NULL COMMENT 'MIME类型',
  `description` varchar(500) DEFAULT NULL COMMENT '文件描述',
  `is_public` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否公开：0-私有, 1-公开',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_attachments_entity` (`entity_type`,`entity_id`),
  KEY `idx_attachments_file_name` (`file_name`(100)),
  KEY `idx_attachments_file_type` (`file_type`),
  KEY `idx_attachments_is_public` (`is_public`),
  KEY `idx_attachments_created_at` (`created_at`),
  KEY `idx_attachments_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='附件表';

-- 评论表
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论ID',
  `content` text NOT NULL COMMENT '评论内容',
  `comment_type` varchar(50) NOT NULL DEFAULT 'COMMENT' COMMENT '评论类型：COMMENT-评论, REVIEW-评审, APPROVAL-审批',
  `is_private` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否私有：0-公开, 1-私有',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志：0-未删除, 1-已删除',
  PRIMARY KEY (`id`),
  KEY `idx_comments_entity` (`entity_type`,`entity_id`),
  KEY `idx_comments_parent_id` (`parent_id`),
  KEY `idx_comments_comment_type` (`comment_type`),
  KEY `idx_comments_is_private` (`is_private`),
  KEY `idx_comments_created_by` (`created_by`),
  KEY `idx_comments_created_at` (`created_at`),
  KEY `idx_comments_deleted` (`deleted`),
  CONSTRAINT `fk_comments_parent` FOREIGN KEY (`parent_id`) REFERENCES `comments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- 变更历史表
CREATE TABLE `change_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `entity_id` bigint(20) NOT NULL COMMENT '实体ID',
  `operation_type` varchar(50) NOT NULL COMMENT '操作类型：CREATE-创建, UPDATE-更新, DELETE-删除',
  `field_name` varchar(100) DEFAULT NULL COMMENT '字段名称',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `change_reason` varchar(500) DEFAULT NULL COMMENT '变更原因',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `created_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_change_logs_entity` (`entity_type`,`entity_id`),
  KEY `idx_change_logs_operation_type` (`operation_type`),
  KEY `idx_change_logs_field_name` (`field_name`),
  KEY `idx_change_logs_created_by` (`created_by`),
  KEY `idx_change_logs_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='变更历史表';

-- 导入导出任务表
CREATE TABLE `import_export_tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` varchar(100) NOT NULL COMMENT '任务ID',
  `task_type` varchar(50) NOT NULL COMMENT '任务类型：IMPORT-导入, EXPORT-导出',
  `entity_type` varchar(50) NOT NULL COMMENT '实体类型：REQUIREMENT-需求, FUNCTION_POINT-功能点, CATEGORY-分类',
  `file_name` varchar(500) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '文件路径',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小（字节）',
  `status` varchar(50) NOT NULL DEFAULT 'PENDING' COMMENT '任务状态：PENDING-等待中, PROCESSING-处理中, COMPLETED-已完成, FAILED-失败, CANCELLED-已取消',
  `progress` int(11) NOT NULL DEFAULT '0' COMMENT '进度百分比',
  `total_records` int(11) DEFAULT NULL COMMENT '总记录数',
  `processed_records` int(11) DEFAULT '0' COMMENT '已处理记录数',
  `success_records` int(11) DEFAULT '0' COMMENT '成功记录数',
  `failed_records` int(11) DEFAULT '0' COMMENT '失败记录数',
  `error_message` text COMMENT '错误信息',
  `error_details` json DEFAULT NULL COMMENT '错误详情，JSON格式',
  `result_file_path` varchar(1000) DEFAULT NULL COMMENT '结果文件路径',
  `started_at` datetime DEFAULT NULL COMMENT '开始时间',
  `completed_at` datetime DEFAULT NULL COMMENT '完成时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_import_export_tasks_task_id` (`task_id`),
  KEY `idx_import_export_tasks_task_type` (`task_type`),
  KEY `idx_import_export_tasks_entity_type` (`entity_type`),
  KEY `idx_import_export_tasks_status` (`status`),
  KEY `idx_import_export_tasks_created_by` (`created_by`),
  KEY `idx_import_export_tasks_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='导入导出任务表';

-- =====================================================
-- 4. 添加外键约束
-- =====================================================
ALTER TABLE `requirements` ADD CONSTRAINT `fk_requirements_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `function_points` ADD CONSTRAINT `fk_function_points_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- =====================================================
-- 5. 创建全文索引
-- =====================================================
ALTER TABLE `requirements` ADD FULLTEXT KEY `ft_requirements_search` (`title`,`description`);
ALTER TABLE `function_points` ADD FULLTEXT KEY `ft_function_points_search` (`name`,`description`);
ALTER TABLE `categories` ADD FULLTEXT KEY `ft_categories_search` (`name`,`description`);
ALTER TABLE `comments` ADD FULLTEXT KEY `ft_comments_search` (`content`);

-- =====================================================
-- 6. 创建复合索引
-- =====================================================
CREATE INDEX `idx_requirements_status_priority` ON `requirements` (`status`, `priority`, `deleted`);
CREATE INDEX `idx_requirements_type_status` ON `requirements` (`type`, `status`, `deleted`);
CREATE INDEX `idx_requirements_assignee_status` ON `requirements` (`assignee`, `status`, `deleted`);
CREATE INDEX `idx_requirements_category_status` ON `requirements` (`category_id`, `status`, `deleted`);
CREATE INDEX `idx_requirements_parent_level` ON `requirements` (`parent_id`, `level`, `sort_order`);

CREATE INDEX `idx_function_points_status_priority` ON `function_points` (`status`, `priority`, `deleted`);
CREATE INDEX `idx_function_points_type_status` ON `function_points` (`type`, `status`, `deleted`);
CREATE INDEX `idx_function_points_complexity_status` ON `function_points` (`complexity`, `status`, `deleted`);
CREATE INDEX `idx_function_points_assignee_status` ON `function_points` (`assignee`, `status`, `deleted`);
CREATE INDEX `idx_function_points_category_status` ON `function_points` (`category_id`, `status`, `deleted`);

CREATE INDEX `idx_categories_type_active` ON `categories` (`type`, `is_active`, `deleted`);
CREATE INDEX `idx_categories_parent_level` ON `categories` (`parent_id`, `level`, `sort_order`);

-- =====================================================
-- 7. 插入初始分类数据
-- =====================================================

-- 需求分类
INSERT INTO `categories` (`id`, `name`, `description`, `type`, `parent_id`, `path`, `level`, `sort_order`, `icon`, `color`, `is_active`, `created_by`, `created_at`, `updated_at`) VALUES
(1, '功能需求', '系统功能相关需求', 'REQUIREMENT', NULL, '/', 1, 1, 'function', '#1890ff', 1, 'system', NOW(), NOW()),
(2, '性能需求', '系统性能相关需求', 'REQUIREMENT', NULL, '/', 1, 2, 'performance', '#52c41a', 1, 'system', NOW(), NOW()),
(3, '安全需求', '系统安全相关需求', 'REQUIREMENT', NULL, '/', 1, 3, 'security', '#fa541c', 1, 'system', NOW(), NOW());

-- 功能点分类
INSERT INTO `categories` (`id`, `name`, `description`, `type`, `parent_id`, `path`, `level`, `sort_order`, `icon`, `color`, `is_active`, `created_by`, `created_at`, `updated_at`) VALUES
(11, '前端模块', '前端相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 1, 'frontend', '#722ed1', 1, 'system', NOW(), NOW()),
(12, '后端模块', '后端相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 2, 'backend', '#13c2c2', 1, 'system', NOW(), NOW()),
(13, '数据库模块', '数据库相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 3, 'database', '#eb2f96', 1, 'system', NOW(), NOW());

-- =====================================================
-- 8. 设置自增ID起始值
-- =====================================================
ALTER TABLE `categories` AUTO_INCREMENT = 100;
ALTER TABLE `requirements` AUTO_INCREMENT = 1000;
ALTER TABLE `function_points` AUTO_INCREMENT = 1000;
ALTER TABLE `requirement_function_relations` AUTO_INCREMENT = 1000;
ALTER TABLE `attachments` AUTO_INCREMENT = 1000;
ALTER TABLE `comments` AUTO_INCREMENT = 1000;
ALTER TABLE `change_logs` AUTO_INCREMENT = 10000;
ALTER TABLE `import_export_tasks` AUTO_INCREMENT = 1000;

-- =====================================================
-- 9. 恢复外键检查
-- =====================================================
SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- 10. 输出创建结果
-- =====================================================
SELECT CONCAT('租户数据库 function_demand_tenant_{TENANT_ID} 创建完成') as message;
SELECT 
    TABLE_NAME as table_name,
    TABLE_COMMENT as table_comment,
    TABLE_ROWS as estimated_rows
FROM INFORMATION_SCHEMA.TABLES 
WHERE TABLE_SCHEMA = 'function_demand_tenant_{TENANT_ID}' 
  AND TABLE_TYPE = 'BASE TABLE'
ORDER BY TABLE_NAME;

-- =====================================================
-- 脚本执行完成
-- =====================================================