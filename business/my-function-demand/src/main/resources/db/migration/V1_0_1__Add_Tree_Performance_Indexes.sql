-- 需求表性能优化索引
CREATE INDEX idx_requirement_parent_level ON requirement(parent_id, level);
CREATE INDEX idx_requirement_path_prefix ON requirement(path(100));
CREATE INDEX idx_requirement_category_level ON requirement(category_id, level);
CREATE INDEX idx_requirement_status_level ON requirement(status, level);

-- 功能点表性能优化索引
CREATE INDEX idx_function_parent_level ON function_point(parent_id, level);
CREATE INDEX idx_function_path_prefix ON function_point(path(100));
CREATE INDEX idx_function_category_level ON function_point(category_id, level);
CREATE INDEX idx_function_module_level ON function_point(module, level);

-- 分类目录表性能优化索引
CREATE INDEX idx_category_type_parent_sort ON category(type, parent_id, sort_order);
CREATE INDEX idx_category_path_prefix ON category(path(100));
CREATE INDEX idx_category_type_level ON category(type, level);

-- 关联关系表性能优化索引
CREATE INDEX idx_relation_req_type ON requirement_function_relation(requirement_id, relation_type);
CREATE INDEX idx_relation_func_type ON requirement_function_relation(function_id, relation_type);

-- 复合索引用于常见查询模式
CREATE INDEX idx_requirement_composite ON requirement(parent_id, status, level, created_time);
CREATE INDEX idx_function_composite ON function_point(parent_id, status, module, level, created_time);
CREATE INDEX idx_category_composite ON category(type, parent_id, level, sort_order, created_time);