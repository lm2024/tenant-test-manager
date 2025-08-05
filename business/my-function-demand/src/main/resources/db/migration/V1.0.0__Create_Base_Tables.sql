-- =====================================================
-- 需求管理和功能管理模块 - 基础表结构创建脚本
-- 版本: V1.0.0
-- 描述: 创建需求、功能点、分类和关联关系表
-- 作者: System Team
-- 创建时间: 2024-01-01
-- =====================================================

-- 设置字符集和排序规则
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =====================================================
-- 1. 需求表 (requirements)
-- =====================================================
DROP TABLE IF EXISTS `requirements`;
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
  CONSTRAINT `fk_requirements_parent` FOREIGN KEY (`parent_id`) REFERENCES `requirements` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='需求表';

-- =====================================================
-- 2. 功能点表 (function_points)
-- =====================================================
DROP TABLE IF EXISTS `function_points`;
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
  CONSTRAINT `fk_function_points_parent` FOREIGN KEY (`parent_id`) REFERENCES `function_points` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='功能点表';

-- =====================================================
-- 3. 分类表 (categories)
-- =====================================================
DROP TABLE IF EXISTS `categories`;
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
  CONSTRAINT `fk_categories_parent` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- =====================================================
-- 4. 需求与功能点关联表 (requirement_function_relations)
-- =====================================================
DROP TABLE IF EXISTS `requirement_function_relations`;
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

-- =====================================================
-- 5. 附件表 (attachments)
-- =====================================================
DROP TABLE IF EXISTS `attachments`;
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

-- =====================================================
-- 6. 评论表 (comments)
-- =====================================================
DROP TABLE IF EXISTS `comments`;
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

-- =====================================================
-- 7. 变更历史表 (change_logs)
-- =====================================================
DROP TABLE IF EXISTS `change_logs`;
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

-- =====================================================
-- 8. 导入导出任务表 (import_export_tasks)
-- =====================================================
DROP TABLE IF EXISTS `import_export_tasks`;
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
-- 添加外键约束（分类表的外键约束）
-- =====================================================
ALTER TABLE `requirements` ADD CONSTRAINT `fk_requirements_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE `function_points` ADD CONSTRAINT `fk_function_points_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

-- =====================================================
-- 创建全文索引（用于搜索功能）
-- =====================================================
-- 需求表全文索引
ALTER TABLE `requirements` ADD FULLTEXT KEY `ft_requirements_search` (`title`,`description`);

-- 功能点表全文索引
ALTER TABLE `function_points` ADD FULLTEXT KEY `ft_function_points_search` (`name`,`description`);

-- 分类表全文索引
ALTER TABLE `categories` ADD FULLTEXT KEY `ft_categories_search` (`name`,`description`);

-- 评论表全文索引
ALTER TABLE `comments` ADD FULLTEXT KEY `ft_comments_search` (`content`);

-- =====================================================
-- 创建视图（用于常用查询）
-- =====================================================

-- 需求统计视图
CREATE VIEW `v_requirement_statistics` AS
SELECT 
    `type`,
    `status`,
    `priority`,
    COUNT(*) as `count`,
    AVG(`estimated_hours`) as `avg_estimated_hours`,
    SUM(`estimated_hours`) as `total_estimated_hours`,
    AVG(`actual_hours`) as `avg_actual_hours`,
    SUM(`actual_hours`) as `total_actual_hours`
FROM `requirements` 
WHERE `deleted` = 0 
GROUP BY `type`, `status`, `priority`;

-- 功能点统计视图
CREATE VIEW `v_function_point_statistics` AS
SELECT 
    `type`,
    `status`,
    `priority`,
    `complexity`,
    COUNT(*) as `count`,
    AVG(`estimated_points`) as `avg_estimated_points`,
    SUM(`estimated_points`) as `total_estimated_points`,
    AVG(`actual_points`) as `avg_actual_points`,
    SUM(`actual_points`) as `total_actual_points`,
    AVG(`estimated_hours`) as `avg_estimated_hours`,
    SUM(`estimated_hours`) as `total_estimated_hours`
FROM `function_points` 
WHERE `deleted` = 0 
GROUP BY `type`, `status`, `priority`, `complexity`;

-- 分类统计视图
CREATE VIEW `v_category_statistics` AS
SELECT 
    `type`,
    `level`,
    COUNT(*) as `count`,
    COUNT(CASE WHEN `is_active` = 1 THEN 1 END) as `active_count`,
    COUNT(CASE WHEN `is_active` = 0 THEN 1 END) as `inactive_count`
FROM `categories` 
WHERE `deleted` = 0 
GROUP BY `type`, `level`;

-- 关联关系统计视图
CREATE VIEW `v_relation_statistics` AS
SELECT 
    `relation_type`,
    COUNT(*) as `count`,
    COUNT(CASE WHEN `is_active` = 1 THEN 1 END) as `active_count`,
    AVG(`weight`) as `avg_weight`
FROM `requirement_function_relations` 
WHERE `deleted` = 0 
GROUP BY `relation_type`;

-- =====================================================
-- 设置表注释和字符集
-- =====================================================
SET FOREIGN_KEY_CHECKS = 1;

-- 添加表级别的注释
ALTER TABLE `requirements` COMMENT = '需求表 - 存储项目需求信息，支持树形结构和多种状态管理';
ALTER TABLE `function_points` COMMENT = '功能点表 - 存储功能点信息，支持复杂度评估和工时管理';
ALTER TABLE `categories` COMMENT = '分类表 - 存储需求和功能点的分类信息，支持多层级分类';
ALTER TABLE `requirement_function_relations` COMMENT = '需求功能点关联表 - 管理需求与功能点之间的关联关系';
ALTER TABLE `attachments` COMMENT = '附件表 - 存储各种实体的附件信息';
ALTER TABLE `comments` COMMENT = '评论表 - 存储各种实体的评论和审批信息';
ALTER TABLE `change_logs` COMMENT = '变更历史表 - 记录所有实体的变更历史';
ALTER TABLE `import_export_tasks` COMMENT = '导入导出任务表 - 管理批量导入导出任务的状态和进度';

-- =====================================================
-- 脚本执行完成
-- =====================================================