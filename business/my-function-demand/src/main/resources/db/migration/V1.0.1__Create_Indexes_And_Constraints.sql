-- =====================================================
-- 需求管理和功能管理模块 - 索引和约束优化脚本
-- 版本: V1.0.1
-- 描述: 创建性能优化索引和业务约束
-- 作者: System Team
-- 创建时间: 2024-01-01
-- =====================================================

-- =====================================================
-- 1. 复合索引优化（用于常用查询组合）
-- =====================================================

-- 需求表复合索引
CREATE INDEX `idx_requirements_status_priority` ON `requirements` (`status`, `priority`, `deleted`);
CREATE INDEX `idx_requirements_type_status` ON `requirements` (`type`, `status`, `deleted`);
CREATE INDEX `idx_requirements_assignee_status` ON `requirements` (`assignee`, `status`, `deleted`);
CREATE INDEX `idx_requirements_category_status` ON `requirements` (`category_id`, `status`, `deleted`);
CREATE INDEX `idx_requirements_parent_level` ON `requirements` (`parent_id`, `level`, `sort_order`);
CREATE INDEX `idx_requirements_path_level` ON `requirements` (`path`(100), `level`);
CREATE INDEX `idx_requirements_created_date` ON `requirements` (`created_at`, `deleted`);
CREATE INDEX `idx_requirements_updated_date` ON `requirements` (`updated_at`, `deleted`);

-- 功能点表复合索引
CREATE INDEX `idx_function_points_status_priority` ON `function_points` (`status`, `priority`, `deleted`);
CREATE INDEX `idx_function_points_type_status` ON `function_points` (`type`, `status`, `deleted`);
CREATE INDEX `idx_function_points_complexity_status` ON `function_points` (`complexity`, `status`, `deleted`);
CREATE INDEX `idx_function_points_assignee_status` ON `function_points` (`assignee`, `status`, `deleted`);
CREATE INDEX `idx_function_points_category_status` ON `function_points` (`category_id`, `status`, `deleted`);
CREATE INDEX `idx_function_points_parent_level` ON `function_points` (`parent_id`, `level`, `sort_order`);
CREATE INDEX `idx_function_points_path_level` ON `function_points` (`path`(100), `level`);
CREATE INDEX `idx_function_points_created_date` ON `function_points` (`created_at`, `deleted`);

-- 分类表复合索引
CREATE INDEX `idx_categories_type_active` ON `categories` (`type`, `is_active`, `deleted`);
CREATE INDEX `idx_categories_parent_level` ON `categories` (`parent_id`, `level`, `sort_order`);
CREATE INDEX `idx_categories_path_level` ON `categories` (`path`(100), `level`);

-- 关联表复合索引
CREATE INDEX `idx_requirement_function_relations_req_type` ON `requirement_function_relations` (`requirement_id`, `relation_type`, `is_active`, `deleted`);
CREATE INDEX `idx_requirement_function_relations_func_type` ON `requirement_function_relations` (`function_point_id`, `relation_type`, `is_active`, `deleted`);

-- 附件表复合索引
CREATE INDEX `idx_attachments_entity_public` ON `attachments` (`entity_type`, `entity_id`, `is_public`, `deleted`);
CREATE INDEX `idx_attachments_type_size` ON `attachments` (`file_type`, `file_size`, `deleted`);

-- 评论表复合索引
CREATE INDEX `idx_comments_entity_type` ON `comments` (`entity_type`, `entity_id`, `comment_type`, `deleted`);
CREATE INDEX `idx_comments_parent_created` ON `comments` (`parent_id`, `created_at`, `deleted`);

-- 变更历史表复合索引
CREATE INDEX `idx_change_logs_entity_operation` ON `change_logs` (`entity_type`, `entity_id`, `operation_type`, `created_at`);
CREATE INDEX `idx_change_logs_user_date` ON `change_logs` (`created_by`, `created_at`);

-- 导入导出任务表复合索引
CREATE INDEX `idx_import_export_tasks_type_status` ON `import_export_tasks` (`task_type`, `entity_type`, `status`);
CREATE INDEX `idx_import_export_tasks_user_date` ON `import_export_tasks` (`created_by`, `created_at`);

-- =====================================================
-- 2. 分区索引（用于大数据量优化）
-- =====================================================

-- 变更历史表按月分区（如果数据量大）
-- ALTER TABLE `change_logs` PARTITION BY RANGE (YEAR(created_at) * 100 + MONTH(created_at)) (
--     PARTITION p202401 VALUES LESS THAN (202402),
--     PARTITION p202402 VALUES LESS THAN (202403),
--     PARTITION p202403 VALUES LESS THAN (202404),
--     PARTITION p202404 VALUES LESS THAN (202405),
--     PARTITION p202405 VALUES LESS THAN (202406),
--     PARTITION p202406 VALUES LESS THAN (202407),
--     PARTITION p202407 VALUES LESS THAN (202408),
--     PARTITION p202408 VALUES LESS THAN (202409),
--     PARTITION p202409 VALUES LESS THAN (202410),
--     PARTITION p202410 VALUES LESS THAN (202411),
--     PARTITION p202411 VALUES LESS THAN (202412),
--     PARTITION p202412 VALUES LESS THAN (202501),
--     PARTITION p_future VALUES LESS THAN MAXVALUE
-- );

-- =====================================================
-- 3. 业务约束检查
-- =====================================================

-- 需求表约束
ALTER TABLE `requirements` ADD CONSTRAINT `chk_requirements_priority` CHECK (`priority` BETWEEN 1 AND 5);
ALTER TABLE `requirements` ADD CONSTRAINT `chk_requirements_level` CHECK (`level` BETWEEN 1 AND 5);
ALTER TABLE `requirements` ADD CONSTRAINT `chk_requirements_estimated_hours` CHECK (`estimated_hours` IS NULL OR `estimated_hours` >= 0);
ALTER TABLE `requirements` ADD CONSTRAINT `chk_requirements_actual_hours` CHECK (`actual_hours` IS NULL OR `actual_hours` >= 0);
ALTER TABLE `requirements` ADD CONSTRAINT `chk_requirements_date_range` CHECK (`start_date` IS NULL OR `end_date` IS NULL OR `start_date` <= `end_date`);

-- 功能点表约束
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_priority` CHECK (`priority` BETWEEN 1 AND 5);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_level` CHECK (`level` BETWEEN 1 AND 5);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_estimated_points` CHECK (`estimated_points` IS NULL OR `estimated_points` >= 0);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_actual_points` CHECK (`actual_points` IS NULL OR `actual_points` >= 0);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_estimated_hours` CHECK (`estimated_hours` IS NULL OR `estimated_hours` >= 0);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_actual_hours` CHECK (`actual_hours` IS NULL OR `actual_hours` >= 0);
ALTER TABLE `function_points` ADD CONSTRAINT `chk_function_points_date_range` CHECK (`start_date` IS NULL OR `end_date` IS NULL OR `start_date` <= `end_date`);

-- 分类表约束
ALTER TABLE `categories` ADD CONSTRAINT `chk_categories_level` CHECK (`level` BETWEEN 1 AND 5);

-- 关联表约束
ALTER TABLE `requirement_function_relations` ADD CONSTRAINT `chk_requirement_function_relations_weight` CHECK (`weight` > 0 AND `weight` <= 10);

-- 附件表约束
ALTER TABLE `attachments` ADD CONSTRAINT `chk_attachments_file_size` CHECK (`file_size` > 0);
ALTER TABLE `attachments` ADD CONSTRAINT `chk_attachments_download_count` CHECK (`download_count` >= 0);

-- 导入导出任务表约束
ALTER TABLE `import_export_tasks` ADD CONSTRAINT `chk_import_export_tasks_progress` CHECK (`progress` BETWEEN 0 AND 100);
ALTER TABLE `import_export_tasks` ADD CONSTRAINT `chk_import_export_tasks_records` CHECK (
    `total_records` IS NULL OR 
    (`total_records` >= 0 AND 
     `processed_records` >= 0 AND 
     `success_records` >= 0 AND 
     `failed_records` >= 0 AND
     `processed_records` <= `total_records` AND
     `success_records` + `failed_records` <= `processed_records`)
);

-- =====================================================
-- 4. 触发器（用于自动维护数据一致性）
-- =====================================================

-- 需求表路径自动维护触发器
DELIMITER $$
CREATE TRIGGER `tr_requirements_path_insert` 
BEFORE INSERT ON `requirements`
FOR EACH ROW
BEGIN
    IF NEW.parent_id IS NOT NULL THEN
        SELECT CONCAT(path, id, '/'), level + 1 
        INTO NEW.path, NEW.level
        FROM requirements 
        WHERE id = NEW.parent_id AND deleted = 0;
    ELSE
        SET NEW.path = '/';
        SET NEW.level = 1;
    END IF;
END$$

CREATE TRIGGER `tr_requirements_path_update` 
BEFORE UPDATE ON `requirements`
FOR EACH ROW
BEGIN
    IF NEW.parent_id != OLD.parent_id OR (NEW.parent_id IS NOT NULL AND OLD.parent_id IS NULL) OR (NEW.parent_id IS NULL AND OLD.parent_id IS NOT NULL) THEN
        IF NEW.parent_id IS NOT NULL THEN
            SELECT CONCAT(path, id, '/'), level + 1 
            INTO NEW.path, NEW.level
            FROM requirements 
            WHERE id = NEW.parent_id AND deleted = 0;
        ELSE
            SET NEW.path = '/';
            SET NEW.level = 1;
        END IF;
    END IF;
END$$

-- 功能点表路径自动维护触发器
CREATE TRIGGER `tr_function_points_path_insert` 
BEFORE INSERT ON `function_points`
FOR EACH ROW
BEGIN
    IF NEW.parent_id IS NOT NULL THEN
        SELECT CONCAT(path, id, '/'), level + 1 
        INTO NEW.path, NEW.level
        FROM function_points 
        WHERE id = NEW.parent_id AND deleted = 0;
    ELSE
        SET NEW.path = '/';
        SET NEW.level = 1;
    END IF;
END$$

CREATE TRIGGER `tr_function_points_path_update` 
BEFORE UPDATE ON `function_points`
FOR EACH ROW
BEGIN
    IF NEW.parent_id != OLD.parent_id OR (NEW.parent_id IS NOT NULL AND OLD.parent_id IS NULL) OR (NEW.parent_id IS NULL AND OLD.parent_id IS NOT NULL) THEN
        IF NEW.parent_id IS NOT NULL THEN
            SELECT CONCAT(path, id, '/'), level + 1 
            INTO NEW.path, NEW.level
            FROM function_points 
            WHERE id = NEW.parent_id AND deleted = 0;
        ELSE
            SET NEW.path = '/';
            SET NEW.level = 1;
        END IF;
    END IF;
END$$

-- 分类表路径自动维护触发器
CREATE TRIGGER `tr_categories_path_insert` 
BEFORE INSERT ON `categories`
FOR EACH ROW
BEGIN
    IF NEW.parent_id IS NOT NULL THEN
        SELECT CONCAT(path, id, '/'), level + 1 
        INTO NEW.path, NEW.level
        FROM categories 
        WHERE id = NEW.parent_id AND deleted = 0;
    ELSE
        SET NEW.path = '/';
        SET NEW.level = 1;
    END IF;
END$$

CREATE TRIGGER `tr_categories_path_update` 
BEFORE UPDATE ON `categories`
FOR EACH ROW
BEGIN
    IF NEW.parent_id != OLD.parent_id OR (NEW.parent_id IS NOT NULL AND OLD.parent_id IS NULL) OR (NEW.parent_id IS NULL AND OLD.parent_id IS NOT NULL) THEN
        IF NEW.parent_id IS NOT NULL THEN
            SELECT CONCAT(path, id, '/'), level + 1 
            INTO NEW.path, NEW.level
            FROM categories 
            WHERE id = NEW.parent_id AND deleted = 0;
        ELSE
            SET NEW.path = '/';
            SET NEW.level = 1;
        END IF;
    END IF;
END$$

-- 变更历史记录触发器
CREATE TRIGGER `tr_requirements_change_log` 
AFTER UPDATE ON `requirements`
FOR EACH ROW
BEGIN
    INSERT INTO change_logs (entity_type, entity_id, operation_type, field_name, old_value, new_value, created_by, created_at)
    VALUES ('REQUIREMENT', NEW.id, 'UPDATE', 'status', OLD.status, NEW.status, NEW.updated_by, NOW());
END$$

CREATE TRIGGER `tr_function_points_change_log` 
AFTER UPDATE ON `function_points`
FOR EACH ROW
BEGIN
    INSERT INTO change_logs (entity_type, entity_id, operation_type, field_name, old_value, new_value, created_by, created_at)
    VALUES ('FUNCTION_POINT', NEW.id, 'UPDATE', 'status', OLD.status, NEW.status, NEW.updated_by, NOW());
END$$

DELIMITER ;

-- =====================================================
-- 5. 存储过程（用于复杂业务逻辑）
-- =====================================================

-- 获取需求树形结构的存储过程
DELIMITER $$
CREATE PROCEDURE `sp_get_requirement_tree`(
    IN p_parent_id BIGINT,
    IN p_max_level INT
)
BEGIN
    WITH RECURSIVE requirement_tree AS (
        -- 根节点
        SELECT id, title, description, type, status, priority, parent_id, path, level, sort_order,
               category_id, estimated_hours, actual_hours, assignee, created_at, updated_at
        FROM requirements 
        WHERE (p_parent_id IS NULL AND parent_id IS NULL) 
           OR (p_parent_id IS NOT NULL AND parent_id = p_parent_id)
           AND deleted = 0
        
        UNION ALL
        
        -- 子节点
        SELECT r.id, r.title, r.description, r.type, r.status, r.priority, r.parent_id, r.path, r.level, r.sort_order,
               r.category_id, r.estimated_hours, r.actual_hours, r.assignee, r.created_at, r.updated_at
        FROM requirements r
        INNER JOIN requirement_tree rt ON r.parent_id = rt.id
        WHERE r.deleted = 0 AND r.level <= p_max_level
    )
    SELECT * FROM requirement_tree ORDER BY level, sort_order, id;
END$$

-- 获取功能点树形结构的存储过程
CREATE PROCEDURE `sp_get_function_point_tree`(
    IN p_parent_id BIGINT,
    IN p_max_level INT
)
BEGIN
    WITH RECURSIVE function_point_tree AS (
        -- 根节点
        SELECT id, name, description, type, status, priority, parent_id, path, level, sort_order,
               category_id, complexity, estimated_points, actual_points, estimated_hours, actual_hours, 
               assignee, created_at, updated_at
        FROM function_points 
        WHERE (p_parent_id IS NULL AND parent_id IS NULL) 
           OR (p_parent_id IS NOT NULL AND parent_id = p_parent_id)
           AND deleted = 0
        
        UNION ALL
        
        -- 子节点
        SELECT fp.id, fp.name, fp.description, fp.type, fp.status, fp.priority, fp.parent_id, fp.path, fp.level, fp.sort_order,
               fp.category_id, fp.complexity, fp.estimated_points, fp.actual_points, fp.estimated_hours, fp.actual_hours,
               fp.assignee, fp.created_at, fp.updated_at
        FROM function_points fp
        INNER JOIN function_point_tree fpt ON fp.parent_id = fpt.id
        WHERE fp.deleted = 0 AND fp.level <= p_max_level
    )
    SELECT * FROM function_point_tree ORDER BY level, sort_order, id;
END$$

-- 批量更新排序顺序的存储过程
CREATE PROCEDURE `sp_update_sort_order`(
    IN p_table_name VARCHAR(50),
    IN p_parent_id BIGINT,
    IN p_id_list TEXT
)
BEGIN
    DECLARE v_sql TEXT;
    DECLARE v_pos INT DEFAULT 1;
    DECLARE v_id BIGINT;
    DECLARE v_start INT DEFAULT 1;
    DECLARE v_end INT;
    
    WHILE v_start <= CHAR_LENGTH(p_id_list) DO
        SET v_end = LOCATE(',', p_id_list, v_start);
        IF v_end = 0 THEN
            SET v_end = CHAR_LENGTH(p_id_list) + 1;
        END IF;
        
        SET v_id = CAST(SUBSTRING(p_id_list, v_start, v_end - v_start) AS UNSIGNED);
        
        SET v_sql = CONCAT('UPDATE ', p_table_name, ' SET sort_order = ', v_pos, ' WHERE id = ', v_id);
        SET @sql = v_sql;
        PREPARE stmt FROM @sql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;
        
        SET v_pos = v_pos + 1;
        SET v_start = v_end + 1;
    END WHILE;
END$$

DELIMITER ;

-- =====================================================
-- 6. 函数（用于计算和转换）
-- =====================================================

-- 计算需求完成度的函数
DELIMITER $$
CREATE FUNCTION `fn_calculate_requirement_completion`(p_requirement_id BIGINT)
RETURNS DECIMAL(5,2)
READS SQL DATA
DETERMINISTIC
BEGIN
    DECLARE v_total_count INT DEFAULT 0;
    DECLARE v_completed_count INT DEFAULT 0;
    DECLARE v_completion_rate DECIMAL(5,2) DEFAULT 0.00;
    
    -- 获取子需求总数
    SELECT COUNT(*) INTO v_total_count
    FROM requirements 
    WHERE path LIKE CONCAT((SELECT path FROM requirements WHERE id = p_requirement_id), p_requirement_id, '/%')
      AND deleted = 0;
    
    -- 获取已完成的子需求数
    SELECT COUNT(*) INTO v_completed_count
    FROM requirements 
    WHERE path LIKE CONCAT((SELECT path FROM requirements WHERE id = p_requirement_id), p_requirement_id, '/%')
      AND status IN ('IMPLEMENTED', 'TESTED', 'CLOSED')
      AND deleted = 0;
    
    -- 计算完成度
    IF v_total_count > 0 THEN
        SET v_completion_rate = (v_completed_count / v_total_count) * 100;
    END IF;
    
    RETURN v_completion_rate;
END$$

-- 计算功能点复杂度得分的函数
CREATE FUNCTION `fn_calculate_complexity_score`(p_complexity VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
    CASE p_complexity
        WHEN 'LOW' THEN RETURN 1;
        WHEN 'MEDIUM' THEN RETURN 3;
        WHEN 'HIGH' THEN RETURN 5;
        WHEN 'VERY_HIGH' THEN RETURN 8;
        ELSE RETURN 3;
    END CASE;
END$$

DELIMITER ;

-- =====================================================
-- 脚本执行完成
-- =====================================================