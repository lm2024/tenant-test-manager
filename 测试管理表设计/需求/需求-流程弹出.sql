请求网址
https://www.tapd.cn/api/entity/workflow/get_workflow_condition_map?needRepeatInterceptors=false&workspace_id=60498179&entity_id=1160498179001000088&entity_type=Story
请求方法
GET


needRepeatInterceptors=false&workspace_id=60498179&entity_id=1160498179001000088&entity_type=Story



{"data":[],"meta":{"code":"0","message":"success"},"timestamp":"1749780827"}





请求网址
https://www.tapd.cn/api/aggregation/story_aggregation/get_story_transition_info?workspace_id=60498179&story_id=1160498179001000088&field_blocker=story_fields_rich
请求方法
GET

workspace_id=60498179&story_id=1160498179001000088&field_blocker=story_fields_rich


{
    "data": {
        "get_workflow_by_story": {
            "data": {
                "current_story": {
                    "Story": {
                        "id": "1160498179001000088",
                        "workitem_type_id": "1160498179001000016",
                        "name": "\u3010\u793a\u4f8b\u3011\u8ba2\u5355\u67e5\u770b",
                        "creator": "TAPD",
                        "children_id": "||1160498179001000135",
                        "path": "1160498179001000078:1160498179001000081:1160498179001000088",
                        "workspace_id": "60498179",
                        "status": "developing",
                        "priority": "Middle",
                        "owner": "",
                        "participator": "",
                        "cc": "",
                        "source": "",
                        "module": "",
                        "type": "",
                        "status_append": "",
                        "remain": "0",
                        "developer": "",
                        "test_focus": "",
                        "version": "",
                        "created_from": "",
                        "modifier": "TAPD",
                        "step": ""
                    }
                },
                "current_status": "developing",
                "complete_effort_display": "none",
                "tasks": [],
                "story_not_relation_task_and_remain_is_not_zero": "",
                "my_all_transitions": [
                    {
                        "Name": "developing-developing",
                        "StepPrevious": "developing",
                        "StepNext": "developing",
                        "Appendfield": [
                            {
                                "DBModel": "Story",
                                "FieldName": "remarks",
                                "Notnull": "no",
                                "Sort": "0",
                                "DefaultValue": [
                                    {
                                        "Type": "default_value",
                                        "Value": ""
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "remarks",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Story",
                                "FieldName": "owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "yes",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Story",
                                        "Field": [
                                            "owner",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "owner",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u5b9e\u73b0\u4e2d",
                        "from": [
                            "developing",
                            "\u5b9e\u73b0\u4e2d"
                        ],
                        "to": [
                            "developing",
                            "\u5b9e\u73b0\u4e2d"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0",
                        "actions_config": []
                    },
                    {
                        "Name": "developing-resolved",
                        "StepPrevious": "developing",
                        "StepNext": "resolved",
                        "Appendfield": [
                            {
                                "DBModel": "Story",
                                "FieldName": "remarks",
                                "Notnull": "no",
                                "Sort": "0",
                                "DefaultValue": [
                                    {
                                        "Type": "default_value",
                                        "Value": ""
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "remarks",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Story",
                                "FieldName": "owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "yes",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Story",
                                        "Field": [
                                            "owner",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "owner",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u5df2\u5b9e\u73b0",
                        "from": [
                            "developing",
                            "\u5b9e\u73b0\u4e2d"
                        ],
                        "to": [
                            "resolved",
                            "\u5df2\u5b9e\u73b0"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0",
                        "actions_config": []
                    },
                    {
                        "Name": "developing-rejected",
                        "StepPrevious": "developing",
                        "StepNext": "rejected",
                        "Appendfield": [
                            {
                                "DBModel": "Story",
                                "FieldName": "remarks",
                                "Notnull": "no",
                                "Sort": "0",
                                "DefaultValue": [
                                    {
                                        "Type": "default_value",
                                        "Value": ""
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "remarks",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Story",
                                "FieldName": "owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "yes",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Story",
                                        "Field": [
                                            "owner",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "owner",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u5df2\u62d2\u7edd",
                        "from": [
                            "developing",
                            "\u5b9e\u73b0\u4e2d"
                        ],
                        "to": [
                            "rejected",
                            "\u5df2\u62d2\u7edd"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0",
                        "actions_config": []
                    },
                    {
                        "Name": "developing-planning",
                        "StepPrevious": "developing",
                        "StepNext": "planning",
                        "Appendfield": [
                            {
                                "DBModel": "Story",
                                "FieldName": "remarks",
                                "FieldLabel": "\u8bc4\u8bba",
                                "Notnull": "no",
                                "Sort": "0",
                                "LinkageRules": [],
                                "DefaultValue": [
                                    {
                                        "Type": "default_value",
                                        "Value": ""
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "remarks",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Story",
                                "FieldName": "owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "no",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Story",
                                        "Field": "owner"
                                    }
                                ],
                                "default_value": "",
                                "origin_data_value": "",
                                "field_name": "owner",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u89c4\u5212\u4e2d",
                        "from": [
                            "developing",
                            "\u5b9e\u73b0\u4e2d"
                        ],
                        "to": [
                            "planning",
                            "\u89c4\u5212\u4e2d"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0",
                        "actions_config": []
                    }
                ],
                "is_parent_story": "1",
                "remark_info": {
                    "planning": "",
                    "developing": "",
                    "resolved": "",
                    "rejected": ""
                },
                "status_alias": {
                    "planning": "\u89c4\u5212\u4e2d",
                    "developing": "\u5b9e\u73b0\u4e2d",
                    "resolved": "\u5df2\u5b9e\u73b0",
                    "rejected": "\u5df2\u62d2\u7edd"
                },
                "end_status": {
                    "resolved": "resolved",
                    "rejected": "rejected"
                },
                "story_fields_rich": "",
                "fields_disabled_to_edit": [
                    "effort"
                ],
                "effort_disabled_reason_type": "2",
                "fields_name_map": {
                    "id": "ID",
                    "name": "\u6807\u9898",
                    "status": "\u72b6\u6001",
                    "description": "\u8be6\u7ec6\u63cf\u8ff0",
                    "owner": "\u5904\u7406\u4eba",
                    "creator": "\u521b\u5efa\u4eba",
                    "created": "\u521b\u5efa\u65f6\u95f4",
                    "iteration_id": "\u8fed\u4ee3",
                    "effort": "\u9884\u4f30\u5de5\u65f6",
                    "effort_completed": "\u5b8c\u6210\u5de5\u65f6",
                    "remain": "\u5269\u4f59\u5de5\u65f6",
                    "exceed": "\u8d85\u51fa\u5de5\u65f6",
                    "progress": "\u8fdb\u5ea6",
                    "modified": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                    "priority": "\u4f18\u5148\u7ea7",
                    "cc": "\u6284\u9001\u4eba",
                    "begin": "\u9884\u8ba1\u5f00\u59cb",
                    "due": "\u9884\u8ba1\u7ed3\u675f",
                    "workitem_type_id": "\u9700\u6c42\u7c7b\u522b",
                    "children_id": "\u5b50\u9700\u6c42",
                    "completed": "\u5b8c\u6210\u65f6\u95f4",
                    "parent_id": "\u7236\u9700\u6c42",
                    "business_value": "\u4e1a\u52a1\u4ef7\u503c",
                    "has_attachment": "\u9644\u4ef6",
                    "size": "\u89c4\u6a21",
                    "feature": "\u7279\u6027",
                    "test_focus": "\u6d4b\u8bd5\u91cd\u70b9",
                    "developer": "\u5f00\u53d1\u4eba\u5458",
                    "category_id": "\u5206\u7c7b",
                    "module": "\u6a21\u5757",
                    "version": "\u7248\u672c",
                    "label": "\u6807\u7b7e",
                    "custom_field_one": "\u9700\u6c42\u6765\u6e90"
                },
                "enabled_measurement": "1",
                "need_close_task": "",
                "workflow_type": "classic",
                "fields_disabled_tips_map": {
                    "iteration_id": "\u4e0d\u652f\u6301\u5c06\u7236\u9700\u6c42\u89c4\u5212\u5165\u8fed\u4ee3"
                },
                "is_isolated_status": "",
                "plan_id_name_map": [],
                "branch": [],
                "metrology": "day",
                "end_steps": {
                    "60498179": {
                        "1160498179001000016": [
                            "resolved",
                            "rejected"
                        ],
                        "1160498179001000017": [
                            "resolved",
                            "rejected"
                        ],
                        "1160498179001000018": [
                            "resolved",
                            "rejected"
                        ],
                        "1160498179001000020": [
                            "done"
                        ]
                    }
                },
                "first_steps": {
                    "1160498179001000016": "planning",
                    "1160498179001000018": "planning",
                    "1160498179001000017": "planning",
                    "1160498179001000020": "open"
                },
                "is_need_fill_schedule_field": "0",
                "feature_option_map": [],
                "workflow_id": "1160498179001000013",
                "workitem_type_id": "1160498179001000016",
                "enable_editing_iteration_in_parent_story": "0",
                "enable_multi_linkage_fields": "0",
                "date_fields": [],
                "sw_item_edit_parent_iteration": [],
                "plan_apps": []
            },
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749780827"
        },
        "get_related_branch": {
            "data": [],
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749780827"
        }
    },
    "meta": {
        "code": 0,
        "message": "success"
    },
    "timestamp": 1749780827
}


请求网址
https://www.tapd.cn/api/open/open_setting/get_workflow_entrances?workflow_id=1160498179001000013&workspace_id=60498179&pre_status=developing&tag=story&noEmitErrorMessage=true
请求方法
GET
workflow_id=1160498179001000013&workspace_id=60498179&pre_status=developing&tag=story&noEmitErrorMessage=true


{"data":[],"meta":{"code":"0","message":"success"},"timestamp":"1749780827"}

我需要mysql表设计。 ddl   dml


CREATE DATABASE IF NOT EXISTS tapd_workflow;
USE tapd_workflow;

-- 工作流表
CREATE TABLE workflows (
    id BIGINT UNSIGNED PRIMARY KEY COMMENT '工作流ID',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '工作空间ID',
    workflow_type VARCHAR(50) NOT NULL DEFAULT 'classic' COMMENT '工作流类型',
    metrology VARCHAR(20) NOT NULL DEFAULT 'day' COMMENT '计量单位',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 需求(Story)表
CREATE TABLE stories (
    id BIGINT UNSIGNED PRIMARY KEY COMMENT '需求ID',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '工作空间ID',
    workitem_type_id BIGINT UNSIGNED NOT NULL COMMENT '工作项类型ID',
    name VARCHAR(255) NOT NULL COMMENT '标题',
    status VARCHAR(50) NOT NULL COMMENT '状态码',
    creator VARCHAR(100) NOT NULL DEFAULT 'TAPD' COMMENT '创建人',
    owner VARCHAR(100) COMMENT '处理人',
    path VARCHAR(255) NOT NULL COMMENT '层级路径',
    children_id VARCHAR(255) COMMENT '子需求ID',
    priority VARCHAR(50) NOT NULL DEFAULT 'Middle' COMMENT '优先级',
    effort FLOAT DEFAULT 0 COMMENT '预估工时',
    remain FLOAT DEFAULT 0 COMMENT '剩余工时',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    KEY idx_workspace (workspace_id),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 工作流状态转换表
CREATE TABLE workflow_transitions (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    workflow_id BIGINT UNSIGNED NOT NULL COMMENT '工作流ID',
    transition_name VARCHAR(100) NOT NULL COMMENT '转换名称',
    from_status VARCHAR(50) NOT NULL COMMENT '起始状态',
    to_status VARCHAR(50) NOT NULL COMMENT '目标状态',
    has_permission TINYINT(1) DEFAULT 1 COMMENT '是否有权限',
    condition_hidden TINYINT(1) DEFAULT 0 COMMENT '是否隐藏条件',
    FOREIGN KEY (workflow_id) REFERENCES workflows(id),
    UNIQUE KEY uniq_transition (workflow_id, from_status, to_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 状态别名表
CREATE TABLE status_aliases (
    status_code VARCHAR(50) PRIMARY KEY COMMENT '状态码',
    alias VARCHAR(100) NOT NULL COMMENT '别名',
    workflow_id BIGINT UNSIGNED NOT NULL COMMENT '工作流ID',
    FOREIGN KEY (workflow_id) REFERENCES workflows(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 字段映射表
CREATE TABLE field_mappings (
    field_name VARCHAR(100) PRIMARY KEY COMMENT '字段名',
    field_label VARCHAR(100) NOT NULL COMMENT '字段标签',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '工作空间ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 工作流端点状态表
CREATE TABLE workflow_end_status (
    workflow_id BIGINT UNSIGNED NOT NULL COMMENT '工作流ID',
    status_code VARCHAR(50) NOT NULL COMMENT '状态码',
    PRIMARY KEY (workflow_id, status_code),
    FOREIGN KEY (workflow_id) REFERENCES workflows(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;










-- 插入工作流
INSERT INTO workflows (id, workspace_id, workflow_type, metrology) 
VALUES (1160498179001000013, 60498179, 'classic', 'day');

-- 插入需求
INSERT INTO stories (id, workspace_id, workitem_type_id, name, status, path, children_id)
VALUES (1160498179001000088, 60498179, 1160498179001000016, '【示例】订单查看', 'developing', 
        '1160498179001000078:1160498179001000081:1160498179001000088', '||1160498179001000135');

-- 插入状态转换
INSERT INTO workflow_transitions (workflow_id, transition_name, from_status, to_status)
VALUES
(1160498179001000013, 'developing-developing', 'developing', 'developing'),
(1160498179001000013, 'developing-resolved', 'developing', 'resolved'),
(1160498179001000013, 'developing-rejected', 'developing', 'rejected'),
(1160498179001000013, 'developing-planning', 'developing', 'planning');

-- 插入状态别名
INSERT INTO status_aliases (status_code, alias, workflow_id)
VALUES 
('planning', '规划中', 1160498179001000013),
('developing', '实现中', 1160498179001000013),
('resolved', '已实现', 1160498179001000013),
('rejected', '已拒绝', 1160498179001000013);

-- 插入字段映射
INSERT INTO field_mappings (field_name, field_label, workspace_id)
VALUES
('id', 'ID', 60498179),
('name', '标题', 60498179),
('status', '状态', 60498179),
('description', '详细描述', 60498179),
('owner', '处理人', 60498179);

-- 插入端点状态
INSERT INTO workflow_end_status (workflow_id, status_code)
VALUES
(1160498179001000013, 'resolved'),
(1160498179001000013, 'rejected');


































