-- =====================================================
-- 需求管理和功能管理模块 - 测试数据初始化脚本
-- 版本: V1.0.2
-- 描述: 插入测试数据，用于开发和测试环境
-- 作者: System Team
-- 创建时间: 2024-01-01
-- =====================================================

-- 注意：此脚本仅在开发和测试环境执行
-- 生产环境请勿执行此脚本

-- =====================================================
-- 1. 插入分类测试数据
-- =====================================================

-- 需求分类
INSERT INTO `categories` (`id`, `name`, `description`, `type`, `parent_id`, `path`, `level`, `sort_order`, `icon`, `color`, `is_active`, `created_by`, `created_at`, `updated_at`) VALUES
(1, '功能需求', '系统功能相关需求', 'REQUIREMENT', NULL, '/', 1, 1, 'function', '#1890ff', 1, 'system', NOW(), NOW()),
(2, '性能需求', '系统性能相关需求', 'REQUIREMENT', NULL, '/', 1, 2, 'performance', '#52c41a', 1, 'system', NOW(), NOW()),
(3, '安全需求', '系统安全相关需求', 'REQUIREMENT', NULL, '/', 1, 3, 'security', '#fa541c', 1, 'system', NOW(), NOW()),
(4, '用户管理', '用户相关功能需求', 'REQUIREMENT', 1, '/1/', 2, 1, 'user', '#1890ff', 1, 'system', NOW(), NOW()),
(5, '权限管理', '权限相关功能需求', 'REQUIREMENT', 1, '/1/', 2, 2, 'permission', '#1890ff', 1, 'system', NOW(), NOW()),
(6, '数据管理', '数据相关功能需求', 'REQUIREMENT', 1, '/1/', 2, 3, 'database', '#1890ff', 1, 'system', NOW(), NOW()),
(7, '响应时间', '系统响应时间需求', 'REQUIREMENT', 2, '/2/', 2, 1, 'time', '#52c41a', 1, 'system', NOW(), NOW()),
(8, '并发处理', '系统并发处理需求', 'REQUIREMENT', 2, '/2/', 2, 2, 'concurrent', '#52c41a', 1, 'system', NOW(), NOW());

-- 功能点分类
INSERT INTO `categories` (`id`, `name`, `description`, `type`, `parent_id`, `path`, `level`, `sort_order`, `icon`, `color`, `is_active`, `created_by`, `created_at`, `updated_at`) VALUES
(11, '前端模块', '前端相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 1, 'frontend', '#722ed1', 1, 'system', NOW(), NOW()),
(12, '后端模块', '后端相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 2, 'backend', '#13c2c2', 1, 'system', NOW(), NOW()),
(13, '数据库模块', '数据库相关功能点', 'FUNCTION_POINT', NULL, '/', 1, 3, 'database', '#eb2f96', 1, 'system', NOW(), NOW()),
(14, '用户界面', '用户界面相关功能点', 'FUNCTION_POINT', 11, '/11/', 2, 1, 'ui', '#722ed1', 1, 'system', NOW(), NOW()),
(15, '数据展示', '数据展示相关功能点', 'FUNCTION_POINT', 11, '/11/', 2, 2, 'display', '#722ed1', 1, 'system', NOW(), NOW()),
(16, 'API接口', 'API接口相关功能点', 'FUNCTION_POINT', 12, '/12/', 2, 1, 'api', '#13c2c2', 1, 'system', NOW(), NOW()),
(17, '业务逻辑', '业务逻辑相关功能点', 'FUNCTION_POINT', 12, '/12/', 2, 2, 'business', '#13c2c2', 1, 'system', NOW(), NOW()),
(18, '数据存储', '数据存储相关功能点', 'FUNCTION_POINT', 13, '/13/', 2, 1, 'storage', '#eb2f96', 1, 'system', NOW(), NOW());

-- =====================================================
-- 2. 插入需求测试数据
-- =====================================================

-- 一级需求
INSERT INTO `requirements` (`id`, `title`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `estimated_hours`, `assignee`, `reporter`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(1, '用户登录功能', '实现用户登录功能，包括用户名密码登录、记住密码、忘记密码等功能', 'FUNCTIONAL', 'APPROVED', 1, NULL, '/', 1, 1, 4, 40.00, 'developer1', 'pm1', '登录,认证,安全', 'pm1', NOW(), NOW()),
(2, '用户注册功能', '实现用户注册功能，包括邮箱注册、手机号注册、邮箱验证等功能', 'FUNCTIONAL', 'APPROVED', 1, NULL, '/', 1, 2, 4, 32.00, 'developer1', 'pm1', '注册,验证', 'pm1', NOW(), NOW()),
(3, '权限控制系统', '实现基于角色的权限控制系统，支持多级权限管理', 'FUNCTIONAL', 'DRAFT', 2, NULL, '/', 1, 3, 5, 80.00, 'developer2', 'pm1', '权限,角色,RBAC', 'pm1', NOW(), NOW()),
(4, '数据导入导出', '实现Excel格式的数据批量导入导出功能', 'FUNCTIONAL', 'APPROVED', 2, NULL, '/', 1, 4, 6, 60.00, 'developer3', 'pm1', '导入,导出,Excel', 'pm1', NOW(), NOW()),
(5, '系统性能优化', '优化系统响应时间，提升用户体验', 'NON_FUNCTIONAL', 'DRAFT', 3, NULL, '/', 1, 5, 7, 120.00, 'developer2', 'pm1', '性能,优化', 'pm1', NOW(), NOW());

-- 二级需求（用户登录功能的子需求）
INSERT INTO `requirements` (`id`, `title`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `estimated_hours`, `assignee`, `reporter`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(6, '用户名密码登录', '实现基本的用户名密码登录功能', 'FUNCTIONAL', 'IMPLEMENTED', 1, 1, '/1/', 2, 1, 4, 16.00, 'developer1', 'pm1', '登录,基础功能', 'pm1', NOW(), NOW()),
(7, '记住密码功能', '实现记住密码功能，用户可选择下次自动登录', 'FUNCTIONAL', 'IMPLEMENTED', 2, 1, '/1/', 2, 2, 4, 8.00, 'developer1', 'pm1', '登录,便利性', 'pm1', NOW(), NOW()),
(8, '忘记密码功能', '实现忘记密码功能，支持邮箱找回密码', 'FUNCTIONAL', 'APPROVED', 2, 1, '/1/', 2, 3, 4, 16.00, 'developer1', 'pm1', '登录,密码找回', 'pm1', NOW(), NOW());

-- 二级需求（用户注册功能的子需求）
INSERT INTO `requirements` (`id`, `title`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `estimated_hours`, `assignee`, `reporter`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(9, '邮箱注册功能', '实现邮箱注册功能，包括邮箱格式验证', 'FUNCTIONAL', 'APPROVED', 1, 2, '/2/', 2, 1, 4, 16.00, 'developer1', 'pm1', '注册,邮箱', 'pm1', NOW(), NOW()),
(10, '手机号注册功能', '实现手机号注册功能，包括手机号格式验证', 'FUNCTIONAL', 'DRAFT', 2, 2, '/2/', 2, 2, 4, 16.00, 'developer1', 'pm1', '注册,手机号', 'pm1', NOW(), NOW());

-- 三级需求（忘记密码功能的子需求）
INSERT INTO `requirements` (`id`, `title`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `estimated_hours`, `assignee`, `reporter`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(11, '发送重置邮件', '向用户邮箱发送密码重置邮件', 'FUNCTIONAL', 'APPROVED', 1, 8, '/1/8/', 3, 1, 4, 8.00, 'developer1', 'pm1', '邮件,重置', 'pm1', NOW(), NOW()),
(12, '密码重置页面', '提供密码重置页面，用户可设置新密码', 'FUNCTIONAL', 'DRAFT', 1, 8, '/1/8/', 3, 2, 4, 8.00, 'developer1', 'pm1', '页面,重置', 'pm1', NOW(), NOW());

-- =====================================================
-- 3. 插入功能点测试数据
-- =====================================================

-- 一级功能点
INSERT INTO `function_points` (`id`, `name`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `complexity`, `estimated_points`, `estimated_hours`, `assignee`, `reviewer`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(1, '用户管理模块', '负责用户相关的所有功能，包括注册、登录、个人信息管理等', 'MODULE', 'DEVELOPMENT', 1, NULL, '/', 1, 1, 12, 'HIGH', 50.00, 200.00, 'developer1', 'architect1', '用户,管理', 'architect1', NOW(), NOW()),
(2, '权限管理模块', '负责系统权限控制，包括角色管理、权限分配等', 'MODULE', 'PLANNING', 1, NULL, '/', 1, 2, 12, 'VERY_HIGH', 80.00, 320.00, 'developer2', 'architect1', '权限,角色', 'architect1', NOW(), NOW()),
(3, '数据管理模块', '负责数据的导入导出、备份恢复等功能', 'MODULE', 'DEVELOPMENT', 2, NULL, '/', 1, 3, 12, 'HIGH', 40.00, 160.00, 'developer3', 'architect1', '数据,管理', 'architect1', NOW(), NOW()),
(4, '前端界面组件', '前端通用界面组件库', 'COMPONENT', 'COMPLETED', 3, NULL, '/', 1, 4, 11, 'MEDIUM', 30.00, 120.00, 'frontend1', 'architect1', '前端,组件', 'architect1', NOW(), NOW()),
(5, '数据库设计', '系统数据库表结构设计和优化', 'SERVICE', 'COMPLETED', 1, NULL, '/', 1, 5, 13, 'HIGH', 25.00, 100.00, 'dba1', 'architect1', '数据库,设计', 'architect1', NOW(), NOW());

-- 二级功能点（用户管理模块的子功能点）
INSERT INTO `function_points` (`id`, `name`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `complexity`, `estimated_points`, `estimated_hours`, `assignee`, `reviewer`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(6, '用户登录服务', '提供用户登录相关的后端服务', 'SERVICE', 'COMPLETED', 1, 1, '/1/', 2, 1, 16, 'MEDIUM', 15.00, 60.00, 'developer1', 'architect1', '登录,服务', 'architect1', NOW(), NOW()),
(7, '用户注册服务', '提供用户注册相关的后端服务', 'SERVICE', 'DEVELOPMENT', 1, 1, '/1/', 2, 2, 16, 'MEDIUM', 12.00, 48.00, 'developer1', 'architect1', '注册,服务', 'architect1', NOW(), NOW()),
(8, '用户信息管理', '用户个人信息的增删改查功能', 'FEATURE', 'PLANNING', 2, 1, '/1/', 2, 3, 17, 'LOW', 8.00, 32.00, 'developer1', 'architect1', '用户信息,管理', 'architect1', NOW(), NOW()),
(9, '登录界面', '用户登录页面的前端实现', 'FEATURE', 'COMPLETED', 1, 1, '/1/', 2, 4, 14, 'LOW', 5.00, 20.00, 'frontend1', 'architect1', '登录,界面', 'architect1', NOW(), NOW()),
(10, '注册界面', '用户注册页面的前端实现', 'FEATURE', 'DEVELOPMENT', 1, 1, '/1/', 2, 5, 14, 'LOW', 6.00, 24.00, 'frontend1', 'architect1', '注册,界面', 'architect1', NOW(), NOW());

-- 三级功能点（用户登录服务的子功能点）
INSERT INTO `function_points` (`id`, `name`, `description`, `type`, `status`, `priority`, `parent_id`, `path`, `level`, `sort_order`, `category_id`, `complexity`, `estimated_points`, `estimated_hours`, `assignee`, `reviewer`, `tags`, `created_by`, `created_at`, `updated_at`) VALUES
(11, '登录验证API', '验证用户登录信息的API接口', 'FEATURE', 'COMPLETED', 1, 6, '/1/6/', 3, 1, 16, 'LOW', 5.00, 20.00, 'developer1', 'architect1', 'API,验证', 'architect1', NOW(), NOW()),
(12, '登录状态管理', '管理用户登录状态和会话', 'FEATURE', 'COMPLETED', 1, 6, '/1/6/', 3, 2, 16, 'MEDIUM', 8.00, 32.00, 'developer1', 'architect1', '状态,会话', 'architect1', NOW(), NOW()),
(13, '密码加密服务', '用户密码的加密和验证服务', 'FEATURE', 'COMPLETED', 1, 6, '/1/6/', 3, 3, 16, 'LOW', 2.00, 8.00, 'developer1', 'architect1', '密码,加密', 'architect1', NOW(), NOW());

-- =====================================================
-- 4. 插入需求与功能点关联关系测试数据
-- =====================================================

INSERT INTO `requirement_function_relations` (`requirement_id`, `function_point_id`, `relation_type`, `description`, `weight`, `is_active`, `created_by`, `created_at`, `updated_at`) VALUES
-- 用户登录功能相关关联
(1, 1, 'IMPLEMENTS', '用户管理模块实现用户登录功能', 1.00, 1, 'pm1', NOW(), NOW()),
(6, 6, 'IMPLEMENTS', '用户登录服务实现用户名密码登录', 1.00, 1, 'pm1', NOW(), NOW()),
(6, 9, 'IMPLEMENTS', '登录界面实现用户名密码登录', 1.00, 1, 'pm1', NOW(), NOW()),
(7, 12, 'IMPLEMENTS', '登录状态管理实现记住密码功能', 0.80, 1, 'pm1', NOW(), NOW()),
(8, 6, 'RELATES', '忘记密码功能与用户登录服务相关', 0.60, 1, 'pm1', NOW(), NOW()),

-- 用户注册功能相关关联
(2, 1, 'IMPLEMENTS', '用户管理模块实现用户注册功能', 1.00, 1, 'pm1', NOW(), NOW()),
(9, 7, 'IMPLEMENTS', '用户注册服务实现邮箱注册功能', 1.00, 1, 'pm1', NOW(), NOW()),
(9, 10, 'IMPLEMENTS', '注册界面实现邮箱注册功能', 1.00, 1, 'pm1', NOW(), NOW()),
(10, 7, 'IMPLEMENTS', '用户注册服务实现手机号注册功能', 1.00, 1, 'pm1', NOW(), NOW()),

-- 权限控制系统相关关联
(3, 2, 'IMPLEMENTS', '权限管理模块实现权限控制系统', 1.00, 1, 'pm1', NOW(), NOW()),

-- 数据导入导出相关关联
(4, 3, 'IMPLEMENTS', '数据管理模块实现数据导入导出功能', 1.00, 1, 'pm1', NOW(), NOW()),

-- 依赖关系
(1, 5, 'DEPENDS', '用户登录功能依赖数据库设计', 0.50, 1, 'pm1', NOW(), NOW()),
(2, 5, 'DEPENDS', '用户注册功能依赖数据库设计', 0.50, 1, 'pm1', NOW(), NOW()),
(3, 5, 'DEPENDS', '权限控制系统依赖数据库设计', 0.80, 1, 'pm1', NOW(), NOW());

-- =====================================================
-- 5. 插入附件测试数据
-- =====================================================

INSERT INTO `attachments` (`entity_type`, `entity_id`, `file_name`, `original_name`, `file_path`, `file_size`, `file_type`, `mime_type`, `description`, `is_public`, `created_by`, `created_at`, `updated_at`) VALUES
('REQUIREMENT', 1, 'login_requirement_20240101_001.pdf', '用户登录需求文档.pdf', '/uploads/requirements/login_requirement_20240101_001.pdf', 1024000, 'pdf', 'application/pdf', '用户登录功能详细需求文档', 1, 'pm1', NOW(), NOW()),
('REQUIREMENT', 3, 'permission_design_20240101_001.docx', '权限系统设计文档.docx', '/uploads/requirements/permission_design_20240101_001.docx', 2048000, 'docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', '权限控制系统设计文档', 0, 'pm1', NOW(), NOW()),
('FUNCTION_POINT', 1, 'user_module_api_20240101_001.json', '用户模块API文档.json', '/uploads/function_points/user_module_api_20240101_001.json', 512000, 'json', 'application/json', '用户管理模块API接口文档', 1, 'architect1', NOW(), NOW()),
('FUNCTION_POINT', 9, 'login_ui_mockup_20240101_001.png', '登录界面原型图.png', '/uploads/function_points/login_ui_mockup_20240101_001.png', 3072000, 'png', 'image/png', '登录界面设计原型图', 1, 'designer1', NOW(), NOW());

-- =====================================================
-- 6. 插入评论测试数据
-- =====================================================

INSERT INTO `comments` (`entity_type`, `entity_id`, `parent_id`, `content`, `comment_type`, `is_private`, `created_by`, `created_at`, `updated_at`) VALUES
('REQUIREMENT', 1, NULL, '这个需求很重要，建议优先实现。需要考虑安全性问题。', 'REVIEW', 0, 'reviewer1', NOW(), NOW()),
('REQUIREMENT', 1, 1, '同意，安全性确实是重点。建议增加验证码功能。', 'COMMENT', 0, 'pm1', NOW(), NOW()),
('REQUIREMENT', 3, NULL, '权限系统比较复杂，建议分阶段实现。', 'REVIEW', 0, 'architect1', NOW(), NOW()),
('FUNCTION_POINT', 1, NULL, '用户管理模块的设计需要进一步细化。', 'REVIEW', 0, 'reviewer1', NOW(), NOW()),
('FUNCTION_POINT', 6, NULL, '登录服务的性能需要优化，建议使用缓存。', 'COMMENT', 0, 'developer2', NOW(), NOW()),
('CATEGORY', 1, NULL, '功能需求分类可以进一步细分。', 'COMMENT', 0, 'pm1', NOW(), NOW());

-- =====================================================
-- 7. 插入变更历史测试数据
-- =====================================================

INSERT INTO `change_logs` (`entity_type`, `entity_id`, `operation_type`, `field_name`, `old_value`, `new_value`, `change_reason`, `ip_address`, `user_agent`, `created_by`, `created_at`) VALUES
('REQUIREMENT', 1, 'CREATE', NULL, NULL, NULL, '创建用户登录功能需求', '192.168.1.100', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'pm1', DATE_SUB(NOW(), INTERVAL 7 DAY)),
('REQUIREMENT', 1, 'UPDATE', 'status', 'DRAFT', 'APPROVED', '需求评审通过', '192.168.1.100', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'pm1', DATE_SUB(NOW(), INTERVAL 5 DAY)),
('REQUIREMENT', 6, 'UPDATE', 'status', 'APPROVED', 'IMPLEMENTED', '开发完成', '192.168.1.101', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'developer1', DATE_SUB(NOW(), INTERVAL 2 DAY)),
('FUNCTION_POINT', 1, 'CREATE', NULL, NULL, NULL, '创建用户管理模块', '192.168.1.102', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'architect1', DATE_SUB(NOW(), INTERVAL 10 DAY)),
('FUNCTION_POINT', 6, 'UPDATE', 'status', 'DEVELOPMENT', 'COMPLETED', '开发完成', '192.168.1.101', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'developer1', DATE_SUB(NOW(), INTERVAL 1 DAY));

-- =====================================================
-- 8. 插入导入导出任务测试数据
-- =====================================================

INSERT INTO `import_export_tasks` (`task_id`, `task_type`, `entity_type`, `file_name`, `file_path`, `file_size`, `status`, `progress`, `total_records`, `processed_records`, `success_records`, `failed_records`, `result_file_path`, `started_at`, `completed_at`, `created_by`, `created_at`, `updated_at`) VALUES
('IMPORT_REQ_20240101_001', 'IMPORT', 'REQUIREMENT', 'requirements_import.xlsx', '/uploads/import/requirements_import.xlsx', 1024000, 'COMPLETED', 100, 50, 50, 48, 2, '/exports/import_result_20240101_001.xlsx', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR), 'pm1', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR)),
('EXPORT_FP_20240101_001', 'EXPORT', 'FUNCTION_POINT', NULL, NULL, NULL, 'COMPLETED', 100, 13, 13, 13, 0, '/exports/function_points_export_20240101_001.xlsx', DATE_SUB(NOW(), INTERVAL 30 MINUTE), DATE_SUB(NOW(), INTERVAL 15 MINUTE), 'architect1', DATE_SUB(NOW(), INTERVAL 30 MINUTE), DATE_SUB(NOW(), INTERVAL 15 MINUTE)),
('IMPORT_CAT_20240101_001', 'IMPORT', 'CATEGORY', 'categories_import.xlsx', '/uploads/import/categories_import.xlsx', 512000, 'PROCESSING', 60, 20, 12, 12, 0, NULL, DATE_SUB(NOW(), INTERVAL 10 MINUTE), NULL, 'pm1', DATE_SUB(NOW(), INTERVAL 10 MINUTE), NOW());

-- =====================================================
-- 更新自增ID起始值
-- =====================================================

-- 设置各表的自增ID起始值，避免与测试数据冲突
ALTER TABLE `categories` AUTO_INCREMENT = 100;
ALTER TABLE `requirements` AUTO_INCREMENT = 100;
ALTER TABLE `function_points` AUTO_INCREMENT = 100;
ALTER TABLE `requirement_function_relations` AUTO_INCREMENT = 100;
ALTER TABLE `attachments` AUTO_INCREMENT = 100;
ALTER TABLE `comments` AUTO_INCREMENT = 100;
ALTER TABLE `change_logs` AUTO_INCREMENT = 1000;
ALTER TABLE `import_export_tasks` AUTO_INCREMENT = 100;

-- =====================================================
-- 脚本执行完成
-- =====================================================

-- 输出统计信息
SELECT '测试数据插入完成' as message;
SELECT 'categories' as table_name, COUNT(*) as record_count FROM categories WHERE deleted = 0
UNION ALL
SELECT 'requirements' as table_name, COUNT(*) as record_count FROM requirements WHERE deleted = 0
UNION ALL
SELECT 'function_points' as table_name, COUNT(*) as record_count FROM function_points WHERE deleted = 0
UNION ALL
SELECT 'requirement_function_relations' as table_name, COUNT(*) as record_count FROM requirement_function_relations WHERE deleted = 0
UNION ALL
SELECT 'attachments' as table_name, COUNT(*) as record_count FROM attachments WHERE deleted = 0
UNION ALL
SELECT 'comments' as table_name, COUNT(*) as record_count FROM comments WHERE deleted = 0
UNION ALL
SELECT 'change_logs' as table_name, COUNT(*) as record_count FROM change_logs
UNION ALL
SELECT 'import_export_tasks' as table_name, COUNT(*) as record_count FROM import_export_tasks;