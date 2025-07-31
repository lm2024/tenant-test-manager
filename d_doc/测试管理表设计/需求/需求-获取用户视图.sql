获取故事字段的用户视图、角色、工作流步骤、分类及列表​

https://www.tapd.cn/api/aggregation/story_aggregation/get_story_fields_userviews_roles_workflowsteps_category_and_list?workspace_id=60498179&data[type]=story&location=/prong/stories/stories_list&data[query_token]=&from=stories_list
请求方法
POST


workspace_id=60498179&data[type]=story&location=/prong/stories/stories_list&data[query_token]=&from=stories_list


{
    "data": {
        "parse_token_to_array": {
            "data": [],
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749777228"
        },
        "get_category_tree": {
            "data": [
                {
                    "id": "0",
                    "name": "\u6240\u6709\u7684",
                    "open": "1",
                    "children": [
                        {
                            "id": "-1",
                            "name": "\u672a\u5206\u7c7b",
                            "description": "\u672a\u5206\u7c7b",
                            "parent_id": "0",
                            "path": "",
                            "sorting": "0",
                            "app_id": "1",
                            "workspace_id": "60498179",
                            "level": "1",
                            "label": "\u672a\u5206\u7c7b",
                            "children": []
                        },
                        {
                            "workspace_id": "60498179",
                            "app_id": "1",
                            "id": "1160498179001000018",
                            "name": "\u65b0\u529f\u80fd",
                            "description": "\u65b0\u529f\u80fd",
                            "path": "",
                            "parent_id": "0",
                            "modified": "2025-06-04 09:45:50",
                            "created": "2025-05-30 11:17:13",
                            "creator": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                            "modifier": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                            "sorting": "1",
                            "level": "1",
                            "label": "\u65b0\u529f\u80fd",
                            "children": []
                        },
                        {
                            "workspace_id": "60498179",
                            "app_id": "1",
                            "id": "1160498179001000017",
                            "name": "\u4ea7\u54c1\u9700\u6c42",
                            "description": "\u4ea7\u54c1\u9700\u6c42",
                            "path": "",
                            "parent_id": "0",
                            "modified": "2025-06-04 09:45:50",
                            "created": "2025-05-30 11:17:13",
                            "creator": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                            "modifier": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                            "sorting": "2",
                            "level": "1",
                            "label": "\u4ea7\u54c1\u9700\u6c42",
                            "children": [
                                {
                                    "workspace_id": "60498179",
                                    "app_id": "1",
                                    "id": "1160498179001000020",
                                    "name": "\u4f18\u5316\u9700\u6c42",
                                    "description": "\u4f18\u5316\u9700\u6c42",
                                    "path": ":1160498179001000017",
                                    "parent_id": "1160498179001000017",
                                    "modified": "2025-06-04 09:46:21",
                                    "created": "2025-05-30 11:17:13",
                                    "creator": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                                    "modifier": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                                    "sorting": "0",
                                    "level": "2",
                                    "label": "\u4f18\u5316\u9700\u6c42",
                                    "children": []
                                },
                                {
                                    "workspace_id": "60498179",
                                    "app_id": "1",
                                    "id": "1160498179001000019",
                                    "name": "\u6280\u672f\u9700\u6c42",
                                    "description": "\u6280\u672f\u9700\u6c42",
                                    "path": ":1160498179001000017",
                                    "parent_id": "1160498179001000017",
                                    "modified": "2025-06-04 09:46:21",
                                    "created": "2025-05-30 11:17:13",
                                    "creator": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                                    "modifier": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                                    "sorting": "1",
                                    "level": "2",
                                    "label": "\u6280\u672f\u9700\u6c42",
                                    "children": []
                                }
                            ]
                        }
                    ]
                }
            ],
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749777228"
        },
        "get_userviews": {
            "data": {
                "enabled_add_view": "1",
                "location_title": "story_view",
                "system_userviews": [
                    {
                        "id": "1160498179001000415",
                        "title": "\u6240\u6709\u7684",
                        "enable": "1",
                        "type": "system",
                        "default_show": "true",
                        "initial": {
                            "1": {
                                "column": "created",
                                "operator": "\u003E",
                                "value": [
                                    "1970-01-01"
                                ]
                            }
                        },
                        "relationship": "AND",
                        "view_id": "1000000000000000016",
                        "sort": "1",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "1"
                    },
                    {
                        "id": "1160498179001000416",
                        "title": "\u6211\u8d1f\u8d23\u7684",
                        "enable": "1",
                        "type": "system",
                        "default_show": "false",
                        "initial": {
                            "1": {
                                "column": "owner",
                                "operator": "LIKE",
                                "value": [
                                    "joeyue"
                                ]
                            }
                        },
                        "relationship": "AND",
                        "view_id": "1000000000000000017",
                        "sort": "2",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "1"
                    },
                    {
                        "id": "1160498179001000417",
                        "title": "\u53ef\u8bbf\u95ee\u7684\u5de5\u4f5c\u9879",
                        "enable": "1",
                        "type": "system",
                        "default_show": "false",
                        "initial": [],
                        "relationship": "AND",
                        "view_id": "1000000000000000423",
                        "sort": "3",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "0"
                    },
                    {
                        "id": "1160498179001000418",
                        "title": "\u6211\u5173\u6ce8\u7684",
                        "enable": "1",
                        "type": "system",
                        "default_show": "false",
                        "initial": [],
                        "relationship": "AND",
                        "view_id": "1000000000000000413",
                        "sort": "3",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "0"
                    },
                    {
                        "id": "1160498179001000420",
                        "title": "\u6211\u521b\u5efa\u7684",
                        "enable": "1",
                        "type": "system",
                        "default_show": "false",
                        "initial": {
                            "1": {
                                "column": "creator",
                                "operator": "LIKE",
                                "value": [
                                    "joeyue"
                                ]
                            }
                        },
                        "relationship": "AND",
                        "view_id": "1000000000000000018",
                        "sort": "4",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "1"
                    },
                    {
                        "id": "1160498179001000426",
                        "title": "\u5f85\u89c4\u5212\u5de5\u4f5c\u9879Backlog",
                        "enable": "1",
                        "type": "system",
                        "default_show": "false",
                        "initial": {
                            "1": {
                                "column": "iteration_id",
                                "operator": "=",
                                "value": [
                                    "0"
                                ]
                            }
                        },
                        "relationship": "AND",
                        "view_id": "1000000000000000100",
                        "sort": "10",
                        "lock_show_field": "",
                        "locker": "",
                        "filter_item_count": "1"
                    }
                ],
                "person_userviews": [],
                "remember_switch": "1"
            },
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749777228"
        },
        "installed_app_entrance": {
            "data": [],
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749777228"
        },
        "pin_group_fields": [
            {
                "value": "owner",
                "entity_type": "common"
            },
            {
                "value": "iteration_id",
                "entity_type": "common"
            },
            {
                "value": "creator",
                "entity_type": "common"
            }
        ]
    },
    "meta": {
        "code": 0,
        "message": "success"
    },
    "timestamp": 1749777228
}

我需要mysql表设计。 ddl   dml  



-- 分类表（存储分类树结构）
CREATE TABLE `category` (
  `id` VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '分类ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作空间ID',
  `app_id` VARCHAR(10) NOT NULL COMMENT '应用ID',
  `name` VARCHAR(100) NOT NULL COMMENT '分类名称',
  `description` TEXT COMMENT '分类描述',
  `path` VARCHAR(255) COMMENT '分类路径',
  `parent_id` VARCHAR(50) NOT NULL COMMENT '父分类ID',
  `level` TINYINT NOT NULL COMMENT '层级',
  `sorting` INT NOT NULL COMMENT '排序值',
  `label` VARCHAR(100) NOT NULL COMMENT '显示标签',
  `created` DATETIME NOT NULL COMMENT '创建时间',
  `modified` DATETIME NOT NULL COMMENT '修改时间',
  `creator` VARCHAR(50) NOT NULL COMMENT '创建者',
  `modifier` VARCHAR(50) NOT NULL COMMENT '修改者',
  INDEX `idx_parent` (`parent_id`),
  INDEX `idx_workspace` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户视图表（存储系统/个人视图配置）
CREATE TABLE `user_view` (
  `id` VARCHAR(50) NOT NULL PRIMARY KEY COMMENT '视图ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作空间ID',
  `title` VARCHAR(100) NOT NULL COMMENT '视图标题',
  `enable` TINYINT(1) NOT NULL COMMENT '是否启用',
  `type` ENUM('system','person') NOT NULL COMMENT '视图类型',
  `default_show` TINYINT(1) NOT NULL COMMENT '是否默认显示',
  `initial` JSON NOT NULL COMMENT '初始过滤条件',
  `relationship` VARCHAR(10) COMMENT '条件关系',
  `view_id` VARCHAR(50) NOT NULL COMMENT '关联视图ID',
  `sort` SMALLINT NOT NULL COMMENT '排序值',
  `filter_item_count` SMALLINT NOT NULL COMMENT '过滤条件数量',
  `lock_show_field` VARCHAR(255) COMMENT '锁定显示字段',
  `locker` VARCHAR(50) COMMENT '锁定者',
  INDEX `idx_workspace` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 置顶字段表（存储固定显示的字段）
CREATE TABLE `pinned_field` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作空间ID',
  `value` VARCHAR(50) NOT NULL COMMENT '字段标识',
  `entity_type` VARCHAR(20) NOT NULL COMMENT '实体类型',
  UNIQUE KEY `uniq_workspace_field` (`workspace_id`, `value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 插入分类数据
INSERT INTO `category` (`id`, `workspace_id`, `app_id`, `name`, `description`, `path`, `parent_id`, `level`, `sorting`, `label`, `created`, `modified`, `creator`, `modifier`) VALUES
('0', '60498179', '1', '所有的', NULL, '', '0', 1, 0, '所有的', NOW(), NOW(), 'system', 'system'),
('-1', '60498179', '1', '未分类', '未分类', '', '0', 1, 0, '未分类', '2025-05-30 11:17:13', '2025-06-04 09:45:50', '未命名小程序', '未命名小程序'),
('1160498179001000018', '60498179', '1', '新功能', '新功能', '', '0', 1, 1, '新功能', '2025-05-30 11:17:13', '2025-06-04 09:45:50', '未命名小程序', '未命名小程序'),
('1160498179001000017', '60498179', '1', '产品需求', '产品需求', '', '0', 1, 2, '产品需求', '2025-05-30 11:17:13', '2025-06-04 09:45:50', '未命名小程序', '未命名小程序'),
('1160498179001000020', '60498179', '1', '优化需求', '优化需求', ':1160498179001000017', '1160498179001000017', 2, 0, '优化需求', '2025-05-30 11:17:13', '2025-06-04 09:46:21', '未命名小程序', '未命名小程序'),
('1160498179001000019', '60498179', '1', '技术需求', '技术需求', ':1160498179001000017', '1160498179001000017', 2, 1, '技术需求', '2025-05-30 11:17:13', '2025-06-04 09:46:21', '未命名小程序', '未命名小程序');

-- 插入用户视图数据
INSERT INTO `user_view` (`id`, `workspace_id`, `title`, `enable`, `type`, `default_show`, `initial`, `relationship`, `view_id`, `sort`, `filter_item_count`, `lock_show_field`, `locker`) VALUES
('1160498179001000415', '60498179', '所有的', 1, 'system', 1, '{"1": {"column": "created", "operator": ">", "value": ["1970-01-01"]}}', 'AND', '1000000000000000016', 1, 1, '', ''),
('1160498179001000416', '60498179', '我负责的', 1, 'system', 0, '{"1": {"column": "owner", "operator": "LIKE", "value": ["joeyue"]}}', 'AND', '1000000000000000017', 2, 1, '', ''),
('1160498179001000417', '60498179', '可访问的工作项', 1, 'system', 0, '[]', 'AND', '1000000000000000423', 3, 0, '', ''),
('1160498179001000420', '60498179', '我创建的', 1, 'system', 0, '{"1": {"column": "creator", "operator": "LIKE", "value": ["joeyue"]}}', 'AND', '1000000000000000018', 4, 1, '', '');

-- 插入置顶字段数据
INSERT INTO `pinned_field` (`workspace_id`, `value`, `entity_type`) VALUES
('60498179', 'owner', 'common'),
('60498179', 'iteration_id', 'common'),
('60498179', 'creator', 'common');
































