迭代列表查询
请求网址
https://www.tapd.cn/api/entity/iterations/iterations_workitem_list
请求方法
POST

入参：{"workspace_id":"33142026","iteration_root_id":["1133142026001000004"],"iteration_conf_id":"1133142026001000092","perpage":50,"page":1,"sort_type":"sort","order_type":"desc","list_type":"tree","active_workitem":"story,bug,task,workitem","query_token":"b323b21ec421b3df508215f7b4aa72ba","exclude_workspace_configs":[],"use_alias":1,"show_fields":["name","owner","priority","effort","iteration_id","status"],"token_workspace_id":"33142026","select_workspace":"33142026","dsc_token":"kqyBPEuY79wU7Mmv"}



返参：
{
    "data": {
        "iterations_workitem_list": [
            {
                "id": "1133142026001000026",
                "workspace_id": "33142026",
                "workitem_type_id": "1133142026001000009",
                "app_id": "1",
                "secret_root_id": "0",
                "name": "【示例】校园小拍买家应用",
                "status": "developing",
                "step": "",
                "priority": "high",
                "owner": "",
                "begin": "",
                "due": "2025-08-27",
                "entity_type": "story",
                "path": "1133142026001000026",
                "ancestor_id": "1133142026001000026",
                "parent_id": "0",
                "level": "0",
                "children_id": "|1133142026001000032|1133142026001000030|1133142026001000031|1133142026001000029",
                "sort": "100007900000",
                "progress": "0",
                "iteration_id": "0",
                "creator": "tapd",
                "completed": "",
                "effort_completed": "0",
                "effort": "7",
                "attachment_count": "0",
                "modifier": "tapd",
                "iteration_info": "",
                "has_sub_task": "0",
                "tree_node_count": "10",
                "relate_story_count": "0",
                "sub_task_count": "0",
                "sub_story_count": "4",
                "parent_name": "",
                "relative_id": "0",
                "match_condition": "0",
                "short_id": "1000026",
                "workspace_name": "大型敏捷",
                "begin_ts": "1104508800",
                "due_ts": "1756224000",
                "delay": "0",
                "is_delay": "0",
                "is_done_status": "0",
                "step_delay_info": [],
                "_parent_id": "0",
                "secret_workitem": "0"
            },
            {
                "id": "1133142026001000032",
                "workspace_id": "33142026",
                "workitem_type_id": "1133142026001000008",
                "app_id": "1",
                "secret_root_id": "0",
                "name": "【示例】登陆授权",
                "status": "planning",
                "step": "",
                "priority": "high",
                "owner": "",
                "begin": "",
                "due": "2025-07-18",
                "entity_type": "story",
                "path": "1133142026001000026:1133142026001000032",
                "ancestor_id": "1133142026001000026",
                "parent_id": "1133142026001000026",
                "level": "1",
                "children_id": "|1133142026001000039|1133142026001000040|1133142026001000041",
                "sort": "100029300000",
                "progress": "0",
                "iteration_id": "0",
                "creator": "tapd",
                "completed": "",
                "effort_completed": "0",
                "effort": "",
                "attachment_count": "0",
                "modifier": "tapd",
                "iteration_info": "",
                "has_sub_task": "0",
                "tree_node_count": "10",
                "relate_story_count": "0",
                "sub_task_count": "0",
                "sub_story_count": "3",
                "parent_name": "【示例】校园小拍买家应用",
                "relative_id": "1133142026001000026",
                "match_condition": "0",
                "short_id": "1000032",
                "workspace_name": "大型敏捷",
                "begin_ts": "1104508800",
                "due_ts": "1752768000",
                "delay": "0",
                "is_delay": "0",
                "is_done_status": "0",
                "step_delay_info": [],
                "_parent_id": "1133142026001000026",
                "secret_workitem": "0"
            },
            {
                "id": "1133142026001000041",
                "workspace_id": "33142026",
                "workitem_type_id": "1133142026001000007",
                "app_id": "1",
                "secret_root_id": "0",
                "name": "【示例】邮箱密码登陆",
                "status": "planning",
                "step": "",
                "priority": "high",
                "owner": "",
                "begin": "2025-06-05",
                "due": "2025-07-18",
                "entity_type": "story",
                "path": "1133142026001000026:1133142026001000032:1133142026001000041",
                "ancestor_id": "1133142026001000026",
                "parent_id": "1133142026001000032",
                "level": "2",
                "children_id": "|",
                "sort": "100029600000",
                "progress": "0",
                "iteration_id": "1133142026001000004",
                "creator": "tapd",
                "completed": "",
                "effort_completed": "0",
                "effort": "",
                "attachment_count": "0",
                "modifier": "tapd",
                "display": "1",
                "iteration_info": {
                    "id": "1133142026001000004",
                    "name": "【买家应用】迭代2"
                },
                "has_sub_task": "0",
                "tree_node_count": "10",
                "relate_story_count": "0",
                "sub_task_count": "0",
                "sub_story_count": "0",
                "parent_name": "【示例】登陆授权",
                "relative_id": "1133142026001000032",
                "match_condition": "1",
                "short_id": "1000041",
                "workspace_name": "大型敏捷",
                "begin_ts": "1749052800",
                "due_ts": "1752768000",
                "delay": "0",
                "is_delay": "0",
                "is_done_status": "0",
                "step_delay_info": [],
                "_parent_id": "1133142026001000032",
                "secret_workitem": "0"
            },
            {
                "id": "1133142026001000040",
                "workspace_id": "33142026",
                "workitem_type_id": "1133142026001000007",
                "app_id": "1",
                "secret_root_id": "0",
                "name": "【示例】权限管理",
                "status": "developing",
                "step": "",
                "priority": "high",
                "owner": "",
                "begin": "2025-06-05",
                "due": "2025-07-18",
                "entity_type": "story",
                "path": "1133142026001000026:1133142026001000032:1133142026001000040",
                "ancestor_id": "1133142026001000026",
                "parent_id": "1133142026001000032",
                "level": "2",
                "children_id": "|",
                "sort": "100029500000",
                "progress": "0",
                "iteration_id": "1133142026001000004",
                "creator": "tapd",
                "completed": "",
                "effort_completed": "0",
                "effort": "",
                "attachment_count": "0",
                "modifier": "tapd",
                "display": "1",
                "iteration_info": {
                    "id": "1133142026001000004",
                    "name": "【买家应用】迭代2"
                },
                "has_sub_task": "0",
                "tree_node_count": "10",
                "relate_story_count": "0",
                "sub_task_count": "0",
                "sub_story_count": "0",
                "parent_name": "【示例】登陆授权",
                "relative_id": "1133142026001000032",
                "match_condition": "1",
                "short_id": "1000040",
                "workspace_name": "大型敏捷",
                "begin_ts": "1749052800",
                "due_ts": "1752768000",
                "delay": "0",
                "is_delay": "0",
                "is_done_status": "0",
                "step_delay_info": [],
                "_parent_id": "1133142026001000032",
                "secret_workitem": "0"
            },
            {
                "id": "1133142026001000039",
                "workspace_id": "33142026",
                "workitem_type_id": "1133142026001000007",
                "app_id": "1",
                "secret_root_id": "0",
                "name": "【示例】微信授权登陆",
                "status": "planning",
                "step": "",
                "priority": "high",
                "owner": "",
                "begin": "2025-06-06",
                "due": "2025-06-06",
                "entity_type": "story",
                "path": "1133142026001000026:1133142026001000032:1133142026001000039",
                "ancestor_id": "1133142026001000026",
                "parent_id": "1133142026001000032",
                "level": "2",
                "children_id": "|",
                "sort": "100029400000",
                "progress": "0",
                "iteration_id": "1133142026001000004",
                "creator": "tapd",
                "completed": "",
                "effort_completed": "0",
                "effort": "",
                "attachment_count": "0",
                "modifier": "tapd",
                "display": "1",
                "iteration_info": {
                    "id": "1133142026001000004",
                    "name": "【买家应用】迭代2"
                },
                "has_sub_task": "0",
                "tree_node_count": "10",
                "relate_story_count": "0",
                "sub_task_count": "0",
                "sub_story_count": "0",
                "parent_name": "【示例】登陆授权",
                "relative_id": "1133142026001000032",
                "match_condition": "1",
                "short_id": "1000039",
                "workspace_name": "大型敏捷",
                "begin_ts": "1749139200",
                "due_ts": "1749139200",
                "delay": "1",
                "is_delay": "1",
                "is_done_status": "0",
                "step_delay_info": [],
                "_parent_id": "1133142026001000032",
                "secret_workitem": "0"
            }
        ],
        "status_map": {
            "story": {
                "33142026": {
                    "planning": "规划中",
                    "developing": "实现中",
                    "resolved": "已实现",
                    "rejected": "已拒绝",
                    "status_3": "评审中",
                    "status_4": "测试中",
                    "status_5": "产品体验",
                    "open": "未开始",
                    "progressing": "进行中",
                    "done": "已完成"
                }
            },
            "task": {
                "open": "未开始",
                "progressing": "进行中",
                "done": "已完成"
            },
            "bug": {
                "33142026": {
                    "new": "新",
                    "in_progress": "接受\/处理",
                    "resolved": "已解决",
                    "verified": "已验证",
                    "reopened": "重新打开",
                    "rejected": "已拒绝",
                    "closed": "已关闭"
                }
            }
        },
        "workspace_field_map": {
            "33142026": {
                "story": {
                    "name": [],
                    "owner": [],
                    "priority": [
                        {
                            "value": "high",
                            "label": "high",
                            "color": "#ff6770"
                        },
                        {
                            "value": "middle",
                            "label": "middle",
                            "color": "#28ab80"
                        },
                        {
                            "value": "low",
                            "label": "low",
                            "color": "#7c8597"
                        },
                        {
                            "value": "nice to have",
                            "label": "nice to have",
                            "color": "#7c8597"
                        }
                    ],
                    "effort": [],
                    "iteration_id": [
                        {
                            "id": "0",
                            "label": "-空-",
                            "parent_id": "0",
                            "sort": "0",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid"
                        },
                        {
                            "id": "1133142026001000004",
                            "label": "【买家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000400000",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000006",
                            "label": "【卖家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000600000",
                            "icon": "",
                            "origin_sort": "1",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000003",
                            "label": "【买家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000300000",
                            "icon": "",
                            "origin_sort": "2",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000005",
                            "label": "【卖家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000500000",
                            "icon": "",
                            "origin_sort": "3",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        }
                    ],
                    "status": [
                        {
                            "value": "planning",
                            "label": "规划中"
                        },
                        {
                            "value": "developing",
                            "label": "实现中"
                        },
                        {
                            "value": "resolved",
                            "label": "已实现"
                        },
                        {
                            "value": "rejected",
                            "label": "已拒绝"
                        },
                        {
                            "value": "status_3",
                            "label": "评审中"
                        },
                        {
                            "value": "status_4",
                            "label": "测试中"
                        },
                        {
                            "value": "status_5",
                            "label": "产品体验"
                        }
                    ]
                },
                "bug": {
                    "name": [],
                    "owner": [],
                    "priority": [
                        {
                            "value": "high",
                            "label": "high",
                            "color": "#ff6770"
                        },
                        {
                            "value": "middle",
                            "label": "middle",
                            "color": "#28ab80"
                        },
                        {
                            "value": "low",
                            "label": "low",
                            "color": "#7c8597"
                        },
                        {
                            "value": "nice to have",
                            "label": "nice to have",
                            "color": "#7c8597"
                        }
                    ],
                    "effort": [],
                    "iteration_id": [
                        {
                            "id": "0",
                            "label": "-空-",
                            "parent_id": "0",
                            "sort": "0",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid"
                        },
                        {
                            "id": "1133142026001000004",
                            "label": "【买家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000400000",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000006",
                            "label": "【卖家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000600000",
                            "icon": "",
                            "origin_sort": "1",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000003",
                            "label": "【买家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000300000",
                            "icon": "",
                            "origin_sort": "2",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000005",
                            "label": "【卖家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000500000",
                            "icon": "",
                            "origin_sort": "3",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        }
                    ],
                    "status": [
                        {
                            "value": "planning",
                            "label": "规划中"
                        },
                        {
                            "value": "developing",
                            "label": "实现中"
                        },
                        {
                            "value": "resolved",
                            "label": "已实现"
                        },
                        {
                            "value": "rejected",
                            "label": "已拒绝"
                        },
                        {
                            "value": "status_3",
                            "label": "评审中"
                        },
                        {
                            "value": "status_4",
                            "label": "测试中"
                        },
                        {
                            "value": "status_5",
                            "label": "产品体验"
                        }
                    ]
                },
                "task": {
                    "name": [],
                    "owner": [],
                    "priority": [
                        {
                            "value": "high",
                            "label": "high",
                            "color": "#ff6770"
                        },
                        {
                            "value": "middle",
                            "label": "middle",
                            "color": "#28ab80"
                        },
                        {
                            "value": "low",
                            "label": "low",
                            "color": "#7c8597"
                        },
                        {
                            "value": "nice to have",
                            "label": "nice to have",
                            "color": "#7c8597"
                        }
                    ],
                    "effort": [],
                    "iteration_id": [
                        {
                            "id": "0",
                            "label": "-空-",
                            "parent_id": "0",
                            "sort": "0",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid"
                        },
                        {
                            "id": "1133142026001000004",
                            "label": "【买家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000400000",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000006",
                            "label": "【卖家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000600000",
                            "icon": "",
                            "origin_sort": "1",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000003",
                            "label": "【买家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000300000",
                            "icon": "",
                            "origin_sort": "2",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000005",
                            "label": "【卖家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000500000",
                            "icon": "",
                            "origin_sort": "3",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        }
                    ],
                    "status": [
                        {
                            "value": "planning",
                            "label": "规划中"
                        },
                        {
                            "value": "developing",
                            "label": "实现中"
                        },
                        {
                            "value": "resolved",
                            "label": "已实现"
                        },
                        {
                            "value": "rejected",
                            "label": "已拒绝"
                        },
                        {
                            "value": "status_3",
                            "label": "评审中"
                        },
                        {
                            "value": "status_4",
                            "label": "测试中"
                        },
                        {
                            "value": "status_5",
                            "label": "产品体验"
                        }
                    ]
                },
                "workitem": {
                    "name": [],
                    "owner": [],
                    "priority": [
                        {
                            "value": "high",
                            "label": "high",
                            "color": "#ff6770"
                        },
                        {
                            "value": "middle",
                            "label": "middle",
                            "color": "#28ab80"
                        },
                        {
                            "value": "low",
                            "label": "low",
                            "color": "#7c8597"
                        },
                        {
                            "value": "nice to have",
                            "label": "nice to have",
                            "color": "#7c8597"
                        }
                    ],
                    "effort": [],
                    "iteration_id": [
                        {
                            "id": "0",
                            "label": "-空-",
                            "parent_id": "0",
                            "sort": "0",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid"
                        },
                        {
                            "id": "1133142026001000004",
                            "label": "【买家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000400000",
                            "icon": "",
                            "origin_sort": "0",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000006",
                            "label": "【卖家应用】迭代2",
                            "parent_id": "0",
                            "sort": "100000600000",
                            "icon": "",
                            "origin_sort": "1",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000003",
                            "label": "【买家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000300000",
                            "icon": "",
                            "origin_sort": "2",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        },
                        {
                            "id": "1133142026001000005",
                            "label": "【卖家应用】迭代1",
                            "parent_id": "0",
                            "sort": "100000500000",
                            "icon": "",
                            "origin_sort": "3",
                            "disabled": "",
                            "disabled_tips": "",
                            "fieldname": "iterationid",
                            "is_parent": "false"
                        }
                    ],
                    "status": [
                        {
                            "value": "planning",
                            "label": "规划中"
                        },
                        {
                            "value": "developing",
                            "label": "实现中"
                        },
                        {
                            "value": "resolved",
                            "label": "已实现"
                        },
                        {
                            "value": "rejected",
                            "label": "已拒绝"
                        },
                        {
                            "value": "status_3",
                            "label": "评审中"
                        },
                        {
                            "value": "status_4",
                            "label": "测试中"
                        },
                        {
                            "value": "status_5",
                            "label": "产品体验"
                        }
                    ]
                }
            }
        },
        "workspace_custom_field_map": {
            "33142026": []
        },
        "display_setting": [
            "name",
            "owner",
            "priority",
            "effort",
            "iteration_id",
            "status"
        ],
        "tree_root_count": "1",
        "total_count": "3",
        "socket_login_data": {
            "socket_path": "https:\/\/spider.tapd.cn\/workitems",
            "login_data": {
                "user_nick": "ta::412178979",
                "workspace_id": "33142026",
                "timespan": "1752544216.3501673",
                "data_id": "workitems",
                "token": "0a20c88cf0c53d4c2d9b4f0a193c5915ea09e5eb"
            },
            "room_ids": [
                "workitems_change_iteration_list_412178979_1752544216_1589730"
            ],
            "socket_id": "33142026",
            "need_disconnect": "1"
        },
        "tree_snapshoot": [],
        "current_page_load_all": "1",
        "lazy_no_permission_ids": [],
        "head_width_map": [],
        "plan_id_name_map": {
            "1133142026001000004": "【买家应用】迭代2"
        },
        "workspace_configs": {
            "workitem_types": [
                {
                    "id": "1133142026001000007",
                    "name": "需求",
                    "english_name": "story",
                    "color": "#3582fb",
                    "icon": "",
                    "icon_small": "",
                    "workspace_id": "33142026",
                    "status": "3",
                    "children_ids": "",
                    "parent_ids": "",
                    "remark": "",
                    "workflow_id": "1133142026001000006",
                    "app_id": "1",
                    "modified": "2025-07-15 09:47:42",
                    "icon_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story.png",
                    "icon_small_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story_small.png",
                    "is_new_task_workitem": "false",
                    "workflow_type": "classic",
                    "sub_workitem_types_create_permission": {
                        "setting": "all",
                        "types": []
                    }
                },
                {
                    "id": "1133142026001000008",
                    "name": "特性",
                    "english_name": "featrue",
                    "color": "#64c195",
                    "icon": "57051699\/icon\/1157051699001000038",
                    "icon_small": "57051699\/icon\/1157051699001000039",
                    "workspace_id": "33142026",
                    "status": "3",
                    "children_ids": "1133142026001000007",
                    "parent_ids": "",
                    "remark": "",
                    "workflow_id": "1133142026001000007",
                    "app_id": "1",
                    "modified": "2025-07-15 09:47:42",
                    "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000038.png?token=799d01cd2b3b246ba0a5f825aa72e95135c1c904ea30048551919af3e9540208&version=1752544062&company_id=61298134",
                    "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000039.png?token=be5e48c24e8a1e31dd901f45531222992188c6ce0727ea4633e6d7007905a240&version=1752544062&company_id=61298134",
                    "is_new_task_workitem": "false",
                    "workflow_type": "classic",
                    "sub_workitem_types_create_permission": {
                        "setting": "custom",
                        "types": {
                            "1133142026001000007": {
                                "workitemtype": {
                                    "id": "1133142026001000007",
                                    "workspace_id": "33142026",
                                    "app_id": "1",
                                    "plan_app_id": "0",
                                    "entity_type": "story",
                                    "name": "需求",
                                    "english_name": "story",
                                    "status": "3",
                                    "color": "#3582fb",
                                    "icon": "",
                                    "icon_small": "",
                                    "workflow_id": "1133142026001000006",
                                    "template_ids": "1133142026001000010",
                                    "default_template_id": "0",
                                    "creator": "tapd_system",
                                    "created": "2025-07-15 09:47:42",
                                    "modified_by": "tapd_system",
                                    "modified": "2025-07-15 09:47:42",
                                    "sort": "9999",
                                    "children_ids": "",
                                    "parent_ids": "",
                                    "is_menu": "0",
                                    "remark": "",
                                    "from_workspace_id": "0",
                                    "icon_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story.png",
                                    "icon_small_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story_small.png"
                                }
                            },
                            "1133142026001000008": {
                                "workitemtype": {
                                    "id": "1133142026001000008",
                                    "workspace_id": "33142026",
                                    "app_id": "1",
                                    "plan_app_id": "0",
                                    "entity_type": "story",
                                    "name": "特性",
                                    "english_name": "featrue",
                                    "status": "3",
                                    "color": "#64c195",
                                    "icon": "57051699\/icon\/1157051699001000038",
                                    "icon_small": "57051699\/icon\/1157051699001000039",
                                    "workflow_id": "1133142026001000007",
                                    "template_ids": "1133142026001000011",
                                    "default_template_id": "0",
                                    "creator": "tapd_system",
                                    "created": "2025-07-15 09:47:42",
                                    "modified_by": "tapd_system",
                                    "modified": "2025-07-15 09:47:42",
                                    "sort": "9999",
                                    "children_ids": "1133142026001000007",
                                    "parent_ids": "",
                                    "is_menu": "0",
                                    "remark": "",
                                    "from_workspace_id": "0",
                                    "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000038.png?token=799d01cd2b3b246ba0a5f825aa72e95135c1c904ea30048551919af3e9540208&version=1752544062&company_id=61298134",
                                    "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000039.png?token=be5e48c24e8a1e31dd901f45531222992188c6ce0727ea4633e6d7007905a240&version=1752544062&company_id=61298134"
                                }
                            }
                        }
                    }
                },
                {
                    "id": "1133142026001000009",
                    "name": "史诗故事",
                    "english_name": "epic",
                    "color": "#de71f1",
                    "icon": "57051699\/icon\/1157051699001000040",
                    "icon_small": "57051699\/icon\/1157051699001000041",
                    "workspace_id": "33142026",
                    "status": "3",
                    "children_ids": "1133142026001000008",
                    "parent_ids": "",
                    "remark": "",
                    "workflow_id": "1133142026001000006",
                    "app_id": "1",
                    "modified": "2025-07-15 09:47:42",
                    "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000040.png?token=bb7f99d83cb58731fd41c46fbca7c4e21965248efedd07b06ea0aced7b3e4306&version=1752544062&company_id=61298134",
                    "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000041.png?token=a6ab4f57be75a43bf1f211fcb47d6a6e2c4bcd85ad882fee154e823878d0e9d2&version=1752544062&company_id=61298134",
                    "is_new_task_workitem": "false",
                    "workflow_type": "classic",
                    "sub_workitem_types_create_permission": {
                        "setting": "custom",
                        "types": {
                            "1133142026001000008": {
                                "workitemtype": {
                                    "id": "1133142026001000008",
                                    "workspace_id": "33142026",
                                    "app_id": "1",
                                    "plan_app_id": "0",
                                    "entity_type": "story",
                                    "name": "特性",
                                    "english_name": "featrue",
                                    "status": "3",
                                    "color": "#64c195",
                                    "icon": "57051699\/icon\/1157051699001000038",
                                    "icon_small": "57051699\/icon\/1157051699001000039",
                                    "workflow_id": "1133142026001000007",
                                    "template_ids": "1133142026001000011",
                                    "default_template_id": "0",
                                    "creator": "tapd_system",
                                    "created": "2025-07-15 09:47:42",
                                    "modified_by": "tapd_system",
                                    "modified": "2025-07-15 09:47:42",
                                    "sort": "9999",
                                    "children_ids": "1133142026001000007",
                                    "parent_ids": "",
                                    "is_menu": "0",
                                    "remark": "",
                                    "from_workspace_id": "0",
                                    "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000038.png?token=799d01cd2b3b246ba0a5f825aa72e95135c1c904ea30048551919af3e9540208&version=1752544062&company_id=61298134",
                                    "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000039.png?token=be5e48c24e8a1e31dd901f45531222992188c6ce0727ea4633e6d7007905a240&version=1752544062&company_id=61298134"
                                }
                            }
                        }
                    }
                },
                {
                    "id": "1133142026001000011",
                    "name": "任务",
                    "english_name": "task",
                    "color": "#182b50",
                    "icon": "",
                    "icon_small": "",
                    "workspace_id": "33142026",
                    "status": "2",
                    "children_ids": "|",
                    "parent_ids": "",
                    "remark": "",
                    "workflow_id": "1133142026001000009",
                    "app_id": "1",
                    "modified": "2025-07-15 09:47:42",
                    "icon_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/default.png",
                    "icon_small_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/default_small.png",
                    "menu_workitem_type_id": "1133142026001000004",
                    "is_new_task_workitem": "true",
                    "workflow_type": "classic",
                    "sub_workitem_types_create_permission": {
                        "setting": "none",
                        "types": []
                    }
                }
            ],
            "step_schedule_config": [],
            "workflow_infos": {
                "bug": {
                    "first_steps": {
                        "33142026": {
                            "start": "new"
                        }
                    },
                    "end_steps": {
                        "33142026": {
                            "end": [
                                "closed"
                            ]
                        }
                    },
                    "status_map": {
                        "33142026": {
                            "new": "新",
                            "in_progress": "接受\/处理",
                            "resolved": "已解决",
                            "verified": "已验证",
                            "reopened": "重新打开",
                            "rejected": "已拒绝",
                            "closed": "已关闭"
                        }
                    },
                    "status_color": {
                        "33142026": {
                            "new": "0",
                            "in_progress": "0",
                            "resolved": "0",
                            "verified": "0",
                            "reopened": "0",
                            "rejected": "0",
                            "closed": "0"
                        }
                    },
                    "status_color_map": {
                        "1": "#0d68ff",
                        "2": "#00b3eb",
                        "3": "#28ab80",
                        "4": "#70b400",
                        "5": "#faa23b",
                        "6": "#f7c100",
                        "7": "#7e84ff",
                        "8": "#a576ee",
                        "9": "#fe66a8",
                        "10": "#ff6770",
                        "11": "#7c8597"
                    },
                    "step_map": [],
                    "step_color": [],
                    "step_color_map": {
                        "1": "#0d68ff",
                        "2": "#00b3eb",
                        "3": "#28ab80",
                        "4": "#70b400",
                        "5": "#faa23b",
                        "6": "#f7c100",
                        "7": "#7e84ff",
                        "8": "#a576ee",
                        "9": "#fe66a8",
                        "10": "#ff6770",
                        "11": "#7c8597",
                        "12": "#ffffff"
                    }
                },
                "story": {
                    "first_steps": {
                        "1133142026001000007": "planning",
                        "1133142026001000009": "planning",
                        "1133142026001000008": "planning",
                        "1133142026001000011": "open"
                    },
                    "end_steps": {
                        "33142026": {
                            "1133142026001000007": [
                                "resolved",
                                "rejected"
                            ],
                            "1133142026001000009": [
                                "resolved",
                                "rejected"
                            ],
                            "1133142026001000008": [
                                "resolved",
                                "rejected"
                            ],
                            "1133142026001000011": [
                                "done"
                            ]
                        }
                    },
                    "status_map": {
                        "33142026": {
                            "planning": "规划中",
                            "developing": "实现中",
                            "resolved": "已实现",
                            "rejected": "已拒绝",
                            "status_3": "评审中",
                            "status_4": "测试中",
                            "status_5": "产品体验",
                            "open": "未开始",
                            "progressing": "进行中",
                            "done": "已完成"
                        }
                    },
                    "status_color": {
                        "33142026": {
                            "1133142026001000007": {
                                "planning": "0",
                                "developing": "0",
                                "resolved": "0",
                                "rejected": "0"
                            },
                            "1133142026001000009": {
                                "planning": "0",
                                "developing": "0",
                                "resolved": "0",
                                "rejected": "0"
                            },
                            "1133142026001000008": {
                                "planning": "0",
                                "developing": "0",
                                "resolved": "0",
                                "rejected": "0",
                                "status_3": "0",
                                "status_4": "0",
                                "status_5": "0"
                            },
                            "1133142026001000011": {
                                "open": "0",
                                "progressing": "0",
                                "done": "0"
                            }
                        }
                    },
                    "status_color_map": {
                        "1": "#0d68ff",
                        "2": "#00b3eb",
                        "3": "#28ab80",
                        "4": "#70b400",
                        "5": "#faa23b",
                        "6": "#f7c100",
                        "7": "#7e84ff",
                        "8": "#a576ee",
                        "9": "#fe66a8",
                        "10": "#ff6770",
                        "11": "#7c8597"
                    },
                    "step_map": [],
                    "step_color": [],
                    "step_color_map": {
                        "1": "#0d68ff",
                        "2": "#00b3eb",
                        "3": "#28ab80",
                        "4": "#70b400",
                        "5": "#faa23b",
                        "6": "#f7c100",
                        "7": "#7e84ff",
                        "8": "#a576ee",
                        "9": "#fe66a8",
                        "10": "#ff6770",
                        "11": "#7c8597",
                        "12": "#ffffff"
                    }
                },
                "task": {
                    "status_map": {
                        "open": "未开始",
                        "progressing": "进行中",
                        "done": "已完成"
                    }
                }
            },
            "all_fields_map": {
                "33142026": {
                    "story": {
                        "name": {
                            "editabled_type": "text",
                            "system_name": "name",
                            "required": "1"
                        },
                        "owner": {
                            "editabled_type": "pinyinuserchooser",
                            "system_name": "owner",
                            "required": "",
                            "options": [
                                {
                                    "value": "data_source_url",
                                    "label": "https:\/\/www.tapd.cn\/"
                                },
                                {
                                    "value": "data_source_url_params",
                                    "label": "?workspace_id=33142026&r=0"
                                },
                                {
                                    "value": "chooser_type",
                                    "label": "3"
                                }
                            ]
                        },
                        "priority": {
                            "editabled_type": "tselect",
                            "system_name": "priority",
                            "required": "",
                            "options": [
                                {
                                    "value": "",
                                    "label": ""
                                },
                                {
                                    "value": "4",
                                    "label": "high"
                                },
                                {
                                    "value": "3",
                                    "label": "middle"
                                },
                                {
                                    "value": "2",
                                    "label": "low"
                                },
                                {
                                    "value": "1",
                                    "label": "nice to have"
                                }
                            ],
                            "color_options": [
                                {
                                    "value": "high",
                                    "label": "high",
                                    "color": "#ff6770"
                                },
                                {
                                    "value": "middle",
                                    "label": "middle",
                                    "color": "#28ab80"
                                },
                                {
                                    "value": "low",
                                    "label": "low",
                                    "color": "#7c8597"
                                },
                                {
                                    "value": "nice to have",
                                    "label": "nice to have",
                                    "color": "#7c8597"
                                }
                            ]
                        },
                        "effort": {
                            "editabled_type": "digits",
                            "system_name": "effort",
                            "required": ""
                        },
                        "iteration_id": {
                            "editabled_type": "treeselect",
                            "system_name": "iteration_id",
                            "required": "",
                            "options": [
                                {
                                    "id": "",
                                    "pid": "0",
                                    "name": "-空-",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000004",
                                    "pid": "0",
                                    "name": "【买家应用】迭代2",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000006",
                                    "pid": "0",
                                    "name": "【卖家应用】迭代2",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000003",
                                    "pid": "0",
                                    "name": "【买家应用】迭代1",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000005",
                                    "pid": "0",
                                    "name": "【卖家应用】迭代1",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                }
                            ],
                            "tree_options": [
                                {
                                    "id": "0",
                                    "label": "-空-",
                                    "parent_id": "0",
                                    "sort": "0",
                                    "icon": "",
                                    "origin_sort": "0",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid"
                                },
                                {
                                    "id": "1133142026001000004",
                                    "label": "【买家应用】迭代2",
                                    "parent_id": "0",
                                    "sort": "100000400000",
                                    "icon": "",
                                    "origin_sort": "0",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000006",
                                    "label": "【卖家应用】迭代2",
                                    "parent_id": "0",
                                    "sort": "100000600000",
                                    "icon": "",
                                    "origin_sort": "1",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000003",
                                    "label": "【买家应用】迭代1",
                                    "parent_id": "0",
                                    "sort": "100000300000",
                                    "icon": "",
                                    "origin_sort": "2",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000005",
                                    "label": "【卖家应用】迭代1",
                                    "parent_id": "0",
                                    "sort": "100000500000",
                                    "icon": "",
                                    "origin_sort": "3",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                }
                            ]
                        },
                        "status": {
                            "editabled_type": "text",
                            "system_name": "status",
                            "required": ""
                        }
                    },
                    "bug": {
                        "priority": {
                            "editabled_type": "tselect",
                            "system_name": "priority",
                            "required": "",
                            "options": [
                                {
                                    "value": "",
                                    "label": ""
                                },
                                {
                                    "value": "urgent",
                                    "label": "紧急"
                                },
                                {
                                    "value": "high",
                                    "label": "高"
                                },
                                {
                                    "value": "medium",
                                    "label": "中"
                                },
                                {
                                    "value": "low",
                                    "label": "低"
                                },
                                {
                                    "value": "insignificant",
                                    "label": "无关紧要"
                                }
                            ],
                            "tree_options": [],
                            "color_options": [
                                {
                                    "value": "urgent",
                                    "label": "紧急",
                                    "color": "#ff6770"
                                },
                                {
                                    "value": "high",
                                    "label": "高",
                                    "color": "#ff6770"
                                },
                                {
                                    "value": "medium",
                                    "label": "中",
                                    "color": "#28ab80"
                                },
                                {
                                    "value": "low",
                                    "label": "低",
                                    "color": "#7c8597"
                                },
                                {
                                    "value": "insignificant",
                                    "label": "无关紧要",
                                    "color": "#7c8597"
                                }
                            ]
                        },
                        "effort": {
                            "editabled_type": "digits",
                            "system_name": "effort",
                            "required": "",
                            "tree_options": []
                        },
                        "iteration_id": {
                            "editabled_type": "treeselect",
                            "system_name": "iteration_id",
                            "required": "",
                            "options": [
                                {
                                    "id": "",
                                    "pid": "0",
                                    "name": "-空-",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000004",
                                    "pid": "0",
                                    "name": "【买家应用】迭代2",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000006",
                                    "pid": "0",
                                    "name": "【卖家应用】迭代2",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000003",
                                    "pid": "0",
                                    "name": "【买家应用】迭代1",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                },
                                {
                                    "id": "1133142026001000005",
                                    "pid": "0",
                                    "name": "【卖家应用】迭代1",
                                    "disabled": "",
                                    "disabledtips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "true"
                                }
                            ],
                            "tree_options": [
                                {
                                    "id": "0",
                                    "label": "-空-",
                                    "parent_id": "0",
                                    "sort": "0",
                                    "icon": "",
                                    "origin_sort": "0",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid"
                                },
                                {
                                    "id": "1133142026001000004",
                                    "label": "【买家应用】迭代2",
                                    "parent_id": "0",
                                    "sort": "100000400000",
                                    "icon": "",
                                    "origin_sort": "0",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000006",
                                    "label": "【卖家应用】迭代2",
                                    "parent_id": "0",
                                    "sort": "100000600000",
                                    "icon": "",
                                    "origin_sort": "1",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000003",
                                    "label": "【买家应用】迭代1",
                                    "parent_id": "0",
                                    "sort": "100000300000",
                                    "icon": "",
                                    "origin_sort": "2",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                },
                                {
                                    "id": "1133142026001000005",
                                    "label": "【卖家应用】迭代1",
                                    "parent_id": "0",
                                    "sort": "100000500000",
                                    "icon": "",
                                    "origin_sort": "3",
                                    "disabled": "",
                                    "disabled_tips": "",
                                    "fieldname": "iterationid",
                                    "is_parent": "false"
                                }
                            ]
                        },
                        "status": {
                            "editabled_type": "text",
                            "system_name": "status",
                            "required": "",
                            "tree_options": []
                        },
                        "title": {
                            "editabled_type": "text",
                            "system_name": "title",
                            "required": "1",
                            "tree_options": []
                        },
                        "current_owner": {
                            "editabled_type": "pinyinuserchooser",
                            "system_name": "current_owner",
                            "required": "",
                            "options": [
                                {
                                    "value": "data_source_url",
                                    "label": "https:\/\/www.tapd.cn\/"
                                },
                                {
                                    "value": "data_source_url_params",
                                    "label": "?workspace_id=33142026&r=0"
                                }
                            ],
                            "tree_options": []
                        },
                        "name": {
                            "editabled_type": "text",
                            "system_name": "title",
                            "required": "1",
                            "tree_options": []
                        },
                        "owner": {
                            "editabled_type": "pinyinuserchooser",
                            "system_name": "current_owner",
                            "required": "",
                            "options": [
                                {
                                    "value": "data_source_url",
                                    "label": "https:\/\/www.tapd.cn\/"
                                },
                                {
                                    "value": "data_source_url_params",
                                    "label": "?workspace_id=33142026&r=0"
                                }
                            ],
                            "tree_options": []
                        }
                    }
                }
            },
            "user_permissions_map": {
                "33142026": [
                    "assistant::create",
                    "assistant::edit",
                    "assistant::save",
                    "roles::edit_feature_permission",
                    "roles::feature_index",
                    "roles::edit_feature",
                    "roles::delete_feature",
                    "botsetting::index",
                    "bugs::set_secret_bug",
                    "tasks::archive",
                    "bugs::archive",
                    "stories::archive",
                    "archive_list::index",
                    "archive_list::recover",
                    "workspaces::auto_scheduling",
                    "workspaces::delete_workspace_milestone",
                    "workspaces::export_workspace_milestones",
                    "workspaces::modify_workspace_milestone",
                    "workspaces::create_workspace_milestone",
                    "workspaces::get_workspace_milestone_list",
                    "workspaces::checklist_setting",
                    "roadmap::add_view",
                    "roadmap::edit_view",
                    "roadmap::delete_view",
                    "roadmap::add_snapshot",
                    "roadmap::edit_snapshot",
                    "roadmap::delete_snapshot",
                    "workspaces::modify_milestone_branch",
                    "workspaces::create_milestone_branch",
                    "bugworkflowsettings::getdefaultproperty",
                    "bugworkflowsettings::addworkflow",
                    "bugworkflowsettings::editworkflow",
                    "bugworkflowsettings::delete_workflow_cache",
                    "bugworkflowsettings::deleteworkflow",
                    "bugworkflowsettings::workflowlist",
                    "bugworkflowsettings::set_default_workflow",
                    "bugworkflowsettings::steplist",
                    "bugworkflowsettings::edit_status",
                    "bugworkflowsettings::transition_setting",
                    "bugworkflowsettings::transition_config",
                    "bugworkflowsettings::view_workflow_chart",
                    "workspaces::bug_display_setting",
                    "bugsettings::copy_bug_settings",
                    "bugsettings::templatelist",
                    "bugsettings::set_default_template",
                    "bugsettings::set_bug_template_sort",
                    "bugsettings::bug_delete_template",
                    "bugsettings::bug_template_add",
                    "bugsettings::bug_template_edit",
                    "bugsettings::bug_more_field",
                    "bugsettings::bu_template_edit",
                    "bugsettings::bu_template_report",
                    "bugsettings::bu_template_fields",
                    "bugsettings::bu_template_apply",
                    "bugsettings::field_setting",
                    "system_field_configs::bug",
                    "bugsettings::customfieldsedit",
                    "bugsettings::delete_customfields_for_bug",
                    "bugsettings::customfieldslist",
                    "settings::ajax_edit",
                    "prong_setting::workflowlist",
                    "workflowsetting::story_workflowlist",
                    "prong_setting::view_workflow_chart",
                    "workflow::delete_story_status",
                    "prong_setting::story_template_list",
                    "prong_setting::story_template_add",
                    "prong_setting::story_delete_template",
                    "prong_setting::story_template_copy",
                    "prong_setting::story_template_edit",
                    "prongsetting::story_template_edit",
                    "prong_setting::set_story_template_sort",
                    "prong_setting::template_edit_from_workitem_type",
                    "prongsetting::template_edit_from_workitem_type",
                    "prongsetting::story_more_field",
                    "system_field_configs::story",
                    "custom_fields::story_add",
                    "prong_setting::story_field_setting",
                    "workitem_type::view",
                    "workitemtype::view",
                    "workitem_type::edit_workitem_type",
                    "workitemtype::edit_workitem_type",
                    "workitem_type::create",
                    "workitemtype::create",
                    "iterations::launch_form",
                    "launchform::iteration_add",
                    "team::calendar_setting",
                    "insight::create_indicator",
                    "insight::edit_indicator",
                    "insight::delete_indicator",
                    "story::story_transfer_bug",
                    "workspacereports::export",
                    "iterations::lock_iteration",
                    "insight::export",
                    "p4_source::config",
                    "p4_source::config_setting_intro",
                    "p4_source::config_use_intro",
                    "insight::delete",
                    "insight::delete_folder",
                    "insight::edit",
                    "insight::edit_folder",
                    "insight::create",
                    "insight::create_folder",
                    "imports::import_test_plan",
                    "gitlab_ci::edit",
                    "gitlab_ci::view",
                    "jenkins::index",
                    "pipeline::index",
                    "pipeline::detail",
                    "pipeline::ajax_get_history_table",
                    "pipeline::ajax_pipelines",
                    "jenkins::download_artifact",
                    "jenkins::start_build",
                    "pipeline::start_build",
                    "jenkins::edit",
                    "jenkins::view",
                    "jenkins::ajax_get_jenkins_list",
                    "jenkins::detail",
                    "jenkins::history",
                    "jenkins::ajax_get_build_object_table",
                    "jenkins::ajax_get_history_table",
                    "jenkins::ajax_get_nexus_data",
                    "jenkins::ajax_get_auto_test_data",
                    "jenkins::ajax_get_auto_test_page_data",
                    "pipeline::show_build_console_log",
                    "pipeline::show_task_console_log",
                    "pipeline::show_step_console_log",
                    "gantts::switch_dependency_conflict_resolution",
                    "test_plan::export",
                    "tcase_instance::execute_result_all",
                    "tcase_instance::execute_result",
                    "test_plan::ajax_get_testplan_last_modified",
                    "test_plan::save_plan_tcase",
                    "test_plan::save_plan_story",
                    "tcase_instance::delete_tcase_tset_plan_relation",
                    "tcase::add_save_from_plan_scope",
                    "test_plan::save_and_return",
                    "test_plan::copy",
                    "test_plan::delete",
                    "gantts::add_view",
                    "gantts::edit_view",
                    "gantts::delete_view",
                    "gantts::add_snapshot",
                    "gantts::edit_snapshot",
                    "gantts::delete_snapshot",
                    "kanban::create_view",
                    "kanban::edit_view",
                    "kanban::clone_view",
                    "kanban::delete_view",
                    "iterations::changestatus",
                    "iterations::planning",
                    "iterations::planning_story",
                    "iterations::export_iteration",
                    "exports::export_with_select_fields",
                    "exports::export_table",
                    "exports::export_xls",
                    "iterations::delete",
                    "iterations::edit",
                    "iteration::cgi_update_iteration",
                    "iterations::edit_iteration",
                    "iteration::ci_job_related",
                    "iteration::set_dashboard",
                    "iteration::ajax_set_dashboard_setting",
                    "iteration::ajax_get_job_with_related_status",
                    "iteration::do_ci_job_related",
                    "iterations::add",
                    "iterations::create_iteration",
                    "iterations::update_logo",
                    "iteration::cgi_create_iteration",
                    "iterations::add_iteration",
                    "stories::set_secret_story",
                    "gantts::display_setting",
                    "gantts::add_color_rule_config",
                    "gantts::delete_color_rule_config",
                    "gantts::update_color_rule_config",
                    "gantts::resort_color_rule_config",
                    "workspaces::workspace_setting_gantt",
                    "workspaces::gantt_display_setting",
                    "workspaces::gantt_date_display_setting",
                    "workspaces::gantt_color_rule",
                    "workspaces::gantt_color_rule_dialog",
                    "labels::index",
                    "label::edit",
                    "label::delete",
                    "wikis::share",
                    "attachments::wiki_delete",
                    "attachments::wiki_download",
                    "attachments::wiki_add",
                    "tcase::import_tcase",
                    "imports::import_tcase",
                    "tcase::export_tcase",
                    "exports::export_tcase_table",
                    "exports::export_tcase_with_select_fields",
                    "exports::export_tcase_xls",
                    "svnsource::config",
                    "svnsource::config_setting_intro",
                    "svnsource::config_use_intro",
                    "settings::devops",
                    "entitysort::set_sort",
                    "stories::delete_custom_statistic",
                    "bugreports::deletestat",
                    "stories::update_custom_statistic",
                    "stories::update_time_charts_statistic",
                    "bugreports::update_custom_statistic",
                    "bugreports::save_bugreport_sort",
                    "stories::add_custom_statistic",
                    "stories::add_time_charts_statistic",
                    "bugreports::add_custom_statistic",
                    "releases::export_table",
                    "releases::change_status",
                    "releases::planning_story",
                    "releases::edit",
                    "releases::add",
                    "gantts::add_highlight",
                    "gantts::edit_highlight",
                    "gantts::export",
                    "gantts::export_picture",
                    "auto_trigger::index",
                    "autotasksetting::index",
                    "autotasksetting::create_task",
                    "autotasksetting::set_status",
                    "recyclebin::index",
                    "recyclebin::recover",
                    "recyclebin::delete",
                    "recyclebin::stories_list",
                    "recyclebin::bugs_list",
                    "recyclebin::tasks_list",
                    "recyclebin::cards_list",
                    "recyclebin::tcases_list",
                    "recyclebin::get_filter_content",
                    "workspace::dashboard_settings",
                    "workspace::save_modules_setting",
                    "workspace::modules_display_setting",
                    "workspace::save_modules_direction_setting",
                    "markdown_wikis::download",
                    "wikis::download",
                    "card::copy_panel",
                    "board::edit_labels",
                    "taskboard::cgi_copy_card",
                    "card::copy_card",
                    "board::delete_custom_template",
                    "board::save_custom_template",
                    "tgitsource::config",
                    "tgitsource::config_setting_intro",
                    "tgitsource::config_use_intro",
                    "pipeline::fill_build_params",
                    "jenkinssetting::index",
                    "jenkinssetting::relate",
                    "jenkinssetting::do_relate",
                    "jenkinssetting::ajax_get_client_info",
                    "devopssetting::index",
                    "devopssetting::ajax_get_config_page",
                    "devopssetting::devops_setting_overview",
                    "devopssetting::ajax_save_config",
                    "devopssetting::config_done",
                    "gitlab_source::ajax_get_hook_info",
                    "github_source::ajax_get_hook_info",
                    "tgit_source::ajax_get_hook_info",
                    "gitlab_source::update_hook_url",
                    "github_source::update_hook_url",
                    "tgit_source::update_hook_url",
                    "gitlab_source::switch_secret_token",
                    "github_source::switch_secret_token",
                    "tgit_source::switch_secret_token",
                    "devopssetting::open_source",
                    "clouddevopssetting::index",
                    "clouddevopssetting::ajax_get_config_page",
                    "clouddevopssetting::devops_setting_overview",
                    "clouddevopssetting::ajax_save_config",
                    "clouddevopssetting::config_done",
                    "clouddevopssetting::open_source",
                    "githubsource::config",
                    "githubsource::config_setting_intro",
                    "githubsource::config_use_intro",
                    "githubsource::update_hook_url",
                    "githubsource::update_secret_token",
                    "githubsource::switch_secret_token",
                    "gitlabsource::config",
                    "gitlabsource::config_setting_intro",
                    "gitlabsource::config_use_intro",
                    "gitlabsource::update_hook_url",
                    "gitlabsource::update_secret_token",
                    "gitlabsource::switch_secret_token",
                    "board::copy",
                    "taskboard::cgi_copy_board",
                    "documents::transfer_mindmap_to_word",
                    "documents::transfer_mindmap_to_word_dialog",
                    "documents::update_mindmap_to_word",
                    "documents::show_first_generate_wiki",
                    "documents::show_save_as_wiki",
                    "documents::delete_file",
                    "documents::delete_folder",
                    "documents::cgi_delete_file",
                    "documents::download",
                    "documents::multi_download",
                    "documents::export_mindmap",
                    "documents::cgi_download_file",
                    "documents::edit",
                    "documents::rename",
                    "documents::upload",
                    "documents::create_folder",
                    "documents::update_file",
                    "documents::add_word",
                    "documents::add_mindmap",
                    "documents::inline_update_name",
                    "documents::move_frame",
                    "documents::cgi_create_folder",
                    "documents::cgi_upload_image",
                    "documents::cgi_upload_image_h5",
                    "documents::save_mindmap_position",
                    "documents::transfer_file_to_word",
                    "documents::release_document_folders",
                    "documents::release_to_workspace",
                    "documents::copy",
                    "documents::cgi_rename_document",
                    "documents::cgi_move_document",
                    "documents::cgi_copy_document",
                    "documents::cgi_create_word",
                    "documents::cgi_create_mindmap",
                    "documents::view",
                    "documents::file_list",
                    "documents::show",
                    "documents::cgi_get_file_list",
                    "documents::get_mindmap_image",
                    "documents::cgi_get_comments",
                    "documents::cgi_add_comment",
                    "documents::cgi_get_word_content",
                    "documents::transfer_xmind_to_mindmap",
                    "documents::add_comment",
                    "mindmap::delete",
                    "mindmap::move_to_folder",
                    "mindmap::is_first_transform_wiki",
                    "mindmap::show_first_generate_wiki",
                    "mindmap::show_save_as_wiki",
                    "mindmap::export",
                    "mindmap::show_export_type",
                    "mindmap::add",
                    "mindmap::inline_edit_name",
                    "mindmap::edit",
                    "mindmap::index",
                    "mindmap::view",
                    "mindmap::show_folder_tree",
                    "card::relate_from_mindmap_dialog",
                    "qareports::qatemplatelist",
                    "qareports::qatemplateedit",
                    "qareports::qatemplateeditcontent",
                    "qareports::qatemplatedelete",
                    "qareports::qatemplatepreview",
                    "qareports::qatemplateddel",
                    "qareports::qatemplatedetaileditview",
                    "qareports::ajax_check_qatemplate_title",
                    "qareports::edit_rich_editor",
                    "qareports::edit_workitem_view",
                    "qareports::edit_workitem_stat",
                    "workspaces::report_display_setting",
                    "qareports::qatemplateadd",
                    "card::delete_attachment",
                    "taskboard::cgi_delete_attachment",
                    "taskboard::cgi_delete_relate",
                    "card::download_attachment",
                    "attachments::card_download",
                    "taskboard::cgi_get_attachment_detail",
                    "taskboard::cgi_get_attachment_content",
                    "taskboard::cgi_get_word_content_attachment",
                    "taskboard::cgi_get_text_content_attachment",
                    "taskboard::cgi_download_attachment",
                    "attachments::card_add",
                    "card::relate_document",
                    "taskboard::cgi_add_attachment",
                    "taskboard::cgi_relate_document",
                    "card::export",
                    "board::export",
                    "card::delete",
                    "taskboard::cgi_delete_card",
                    "card::edit_card",
                    "card::sort_card",
                    "taskboard::cgi_update_card_field",
                    "taskboard::cgi_add_checklist",
                    "taskboard::cgi_change_checklist_status",
                    "card::add_checklist",
                    "card::checklist_done",
                    "card::checklist_start",
                    "card::move_checklist",
                    "card::cancel_cover",
                    "card::set_cover",
                    "card::finish_card",
                    "card::re_open_card",
                    "card::add_card",
                    "taskboard::cgi_add_card",
                    "board::delete_column",
                    "taskboard::cgi_delete_column",
                    "columns::edit",
                    "board::sort_column",
                    "taskboard::cgi_update_column_name",
                    "board::save_column_name",
                    "taskboard::cgi_add_column",
                    "board::delete",
                    "board::delete_board",
                    "taskboard::cgi_delete_board",
                    "board::edit",
                    "board::update_board_name",
                    "board::change_status",
                    "taskboard::cgi_update_board_name",
                    "board::add",
                    "taskboard::cgi_add_board",
                    "board::submit_create_by_template",
                    "workspacereports::delete",
                    "workspacereports::copy",
                    "workspacereports::edit",
                    "workspacereports::add",
                    "workspacereports::create_workspace_report_test",
                    "fileapp::delete_file",
                    "fileapp::delete_folder",
                    "fileapp::multi_delete",
                    "fileapp::upload",
                    "fileapp::rename",
                    "fileapp::update_file",
                    "fileapp::create_folder",
                    "fileapp::save_desc",
                    "fileapp::move_frame",
                    "fileapp::move",
                    "fileapp::show",
                    "fileapp::file_list",
                    "fileapp::download",
                    "fileapp::multi_download",
                    "fileapp::preview",
                    "comment::quick_add_comment",
                    "comment::add_file_comment",
                    "comment::download_attachment",
                    "comment::upload_attachment",
                    "comment::load_more_comment",
                    "comment::delete_comment",
                    "fileapp::star_folder_all",
                    "fileapp::star_file",
                    "fileapp::preview_office_type",
                    "fileapp::image_preview_office_type",
                    "fileapp::preview_wps_type",
                    "fileapp::send_mail",
                    "fileapp::multi_share",
                    "fileapp::try_share",
                    "fileapp::check_name_duplicated",
                    "fileapp::card_file_association_thumb",
                    "card::relate_from_file_dialog",
                    "wikis::delete",
                    "wikis::edit",
                    "wikis::add",
                    "wikis::check_titile_exist",
                    "markdown_wikis::edit",
                    "markdown_wikis::add",
                    "markdown_wikis::check_titile_exist",
                    "markdownwikis::check_titile_exist",
                    "wikis::view",
                    "card::relate_from_wiki_dialog",
                    "wiki::cgi_get_items",
                    "wiki::cgi_get_item",
                    "wiki::cgi_get_tags",
                    "wikis::history",
                    "wikis::get_wiki",
                    "wikis::add_comment",
                    "wikis::add_like",
                    "markdownwikis::view",
                    "markdown_wikis::history",
                    "markdownwikis::get_wiki",
                    "markdown_wikis::add_comment",
                    "markdown_wikis::add_like",
                    "markdownwikis::history",
                    "markdownwikis::add_comment",
                    "markdownwikis::add_like",
                    "workspaces::workspace_setting_release",
                    "releases::progress_setting",
                    "workspaces::ce_setting",
                    "workspaces::nce_setting",
                    "workspaces::workspace_setting_testplan",
                    "testplancustomsetting",
                    "testplantemplate",
                    "testplantemplate::test_plan_more_field",
                    "testplan::test_plan_field_setting",
                    "workspaces::testplan_display_setting",
                    "test_plan_template::test_plan_template_add",
                    "system_field_configs::test_plan_type",
                    "test_plan_template::test_plan_template_list",
                    "test_plan_custom_setting::custom_field_edit",
                    "workspacereporttemplates::preview",
                    "workspacereporttemplates::index",
                    "workspacereporttemplates::add",
                    "workspacereporttemplates::edit",
                    "workspacereporttemplates::delete",
                    "bugsettings::qatemplatelist",
                    "workspaces::workspace_setting_tcase",
                    "tcasecustomsetting",
                    "tcasetemplate",
                    "tcasetemplate::tcase_more_field",
                    "tcase::tcase_field_setting",
                    "workspaces::tcase_display_setting",
                    "tcase_template::tcase_template_add",
                    "system_field_configs::tcase_type",
                    "system_field_configs::tcase_priority",
                    "tcase_template::tcase_template_list",
                    "settings::discuss_setting",
                    "workspaces::workspace_function_enable",
                    "workspaces::ajax_save_workspace_metrology",
                    "workspaces::ajax_save_optional_func_sw",
                    "workspaces::fake_workspace_edit",
                    "workspaces::workspace_inline_edit",
                    "workspaces::replace_logo_from_tmp",
                    "workspaces::upload_logo_ajax",
                    "project::cgi_set_project_name",
                    "project::cgi_set_projetc_logo",
                    "settings::security_ip",
                    "menusettings",
                    "menu_settings::config_navbar",
                    "bugs::get_workspace_tree_for_transfer_bug",
                    "bugs::show_workspace_tree_for_transfer_bug",
                    "tobjects::builder",
                    "workflow::chart_preview",
                    "iterations::stories_batch_process_new",
                    "iterations::stories_batch_process_status",
                    "stories::get_story_status_transfer_info_for_batch",
                    "attachments::task_delete",
                    "attachments::task_download",
                    "attachments::task_add",
                    "tasks::changestatus",
                    "tasks::delete",
                    "tasks::export_task",
                    "imports::import_task",
                    "imports::import_preview_task",
                    "tasks::edit",
                    "tasks::tasks_batch_edit",
                    "tracking::timeedit_task",
                    "tasks::inline_update",
                    "tasks::inline_update_intab",
                    "tasks::add",
                    "iterations::to_test",
                    "workspacereports::to_test",
                    "iterations::assignment",
                    "iterations::save_assignment_workitems_change",
                    "iterations::do_planning",
                    "iterations::inline_update",
                    "bugs::move_bug",
                    "bugs::move_bugs",
                    "bugs::replicate",
                    "bugs::copy_bug",
                    "bugs::submit_from_copy",
                    "bugs::edit",
                    "bugs::batch_edit",
                    "bugs::submit_from_edit",
                    "bugs::inline_update",
                    "bugs::inline_update_intab",
                    "bugs::add",
                    "bugs::add_from_tobject",
                    "bugs::submit_from_add",
                    "bugs::quickly_add_bug",
                    "bugs::add_from_tcase_result",
                    "bugs::addce",
                    "bugs::add_support",
                    "bugs::quickly_add_bug_from_story",
                    "bugs::add_from_jenkins_autotest",
                    "attachments::bug_delete",
                    "attachments::bug_download",
                    "attachments::bug_add",
                    "attachments::story_delete",
                    "attachments::story_download",
                    "attachments::story_add",
                    "stories::edit",
                    "stories::inline_update",
                    "stories::change_parent_story",
                    "stories::changepriority",
                    "stories::ajax_batch_edit_stories_parent",
                    "stories::update_story_category",
                    "stories::add",
                    "stories::quickly_add_story",
                    "stories::add_from_mgr",
                    "stories::addce",
                    "stories::add_support",
                    "stories::quick_add_child_story",
                    "stories::quick_add_story",
                    "stories::edit_draft",
                    "stories::remove_draft",
                    "stroies::worktable_remove_draft",
                    "stories::quickly_add_story_from_tab",
                    "stories::transfer_mindmap_to_story",
                    "storywalls::add_card",
                    "categories::index",
                    "categories::view",
                    "categories::add",
                    "categories::edit",
                    "categories::delete",
                    "stories::move_story",
                    "stories::move_story_in_view_page",
                    "stories::move_story_view_tree",
                    "stories::export_story",
                    "imports::import_story",
                    "imports::import_preview_story",
                    "stories::copy_story",
                    "stories::delete",
                    "stories::batch_delete",
                    "stories::secrecy",
                    "workspaces::workspace_setting_launch_view",
                    "prong_setting::custom_field_setting",
                    "launchsetting::index",
                    "launchsetting::templatelist",
                    "launchsetting::templatedelete",
                    "launchsetting::templateedit",
                    "launchsetting::edit_activity",
                    "launchsetting::activity_list",
                    "launchsetting::select_factors",
                    "launchsetting::applytemplate",
                    "launchsetting::list_factors",
                    "launchsetting::edit_factor",
                    "launchsetting::set_default_template",
                    "launchsetting::set_template_sort",
                    "launchsetting::load_activity_factors",
                    "prong_setting::launch_field_setting",
                    "prong_setting::launch_field_edit",
                    "launchsetting::copy_launch_template",
                    "userviews::workspace_view_index",
                    "userviews::workspace_view_list",
                    "userviews::workspace_view_edit",
                    "userviews::delete_workspace_view",
                    "userviews::update_workspace_view_settings",
                    "userviews::update_view_settings",
                    "workspaces::workspace_setting_bug",
                    "bugworkflowsettings::status_fields_setting",
                    "bugworkflowsettings::bug_workflow_select_fields",
                    "bug_workflow_settings::status_edit_fields_setting",
                    "workspaces::bug_switch_setting",
                    "bugworkflowsettings::status_edit_fields_setting",
                    "workflows::bugtrace_workflow_history",
                    "workspaces::ajax_save_bug_setting_sw",
                    "workspaces::workspace_setting_iteration",
                    "prong_setting::copy_iteration_setting",
                    "prong_setting::iteration_status_config",
                    "prong_setting::iteration_template_list",
                    "progress_setting::iteration_progress",
                    "prong_setting::set_iteration_template_sort",
                    "prong_setting::iteration_progress",
                    "prong_setting::iteration_template_edit",
                    "prongsetting::iteration_more_field",
                    "iterations::ajax_enable_extend_iteration_name",
                    "prong_setting::iteration_field_setting",
                    "workspaces::iteration_display_setting",
                    "workspaces::iteration_switch_setting",
                    "system_field_configs::iteration_status",
                    "prong_setting::iteration_template_add",
                    "prongsetting::iteration_delete_template",
                    "prongsetting::save_iteration_progress",
                    "prong_setting::iteration_workitem_type_edit",
                    "prongsetting::iteration_workitem_type_edit",
                    "workspaces::delete_iteration_workitem_type",
                    "workspaces::workspace_setting_tasks",
                    "prong_setting::custom_task_field",
                    "prong_setting::task_field_setting",
                    "workspaces::workspace_setting_story",
                    "prong_setting::story_source_config",
                    "sourcefiles::sourcefile_type_config",
                    "prong_setting::copy_story_setting",
                    "workspaces::story_display_setting",
                    "prong_setting::status_edit_fields_setting",
                    "workspaces::story_switch_setting",
                    "prongsetting::template_add_from_workitem_type",
                    "workspaces::ajax_save_story_setting_sw",
                    "workflows::config_edit_page_show_fields_submit",
                    "workflows::config_show_fields_submit",
                    "workspaces::workspace_secret_user_group_setting",
                    "workspaces::workspace_setting_project",
                    "workspaces::workspace_settings_module",
                    "modules::index",
                    "features::index",
                    "features::add",
                    "features::edit",
                    "features::delete",
                    "versions::index",
                    "versions::add",
                    "versions::edit",
                    "versions::operate",
                    "baselines::index",
                    "baselines::add",
                    "baselines::edit",
                    "baselines::operate",
                    "workspacesettings::common_params_setting",
                    "workspace_settings::common_params_setting",
                    "modules::ajax_set_sort",
                    "modules::edit",
                    "modules::do_copy_module_setting",
                    "baselines::edit_submit",
                    "workspaces::message_setting",
                    "workspaces::save_message_setting",
                    "workspaces::save_message_other_setting",
                    "workspaces::delete_message_setting",
                    "roles::edit_permission",
                    "roles::index",
                    "roles::edit",
                    "roles::delete",
                    "settings::add_member_from_permission",
                    "settings::add_member_to_permission_group",
                    "settings::team",
                    "settings::team_nav",
                    "settings::team_batch_delete",
                    "settings::team_save_changes",
                    "settings::_add_member",
                    "settings::edit_member",
                    "settings::delete_member",
                    "workspaces::copy_members",
                    "settings::inline_update",
                    "settings::cloud_edit_user_info",
                    "settings::add_company_member",
                    "settings::cloud_delete_project_member",
                    "project::cgi_add_project_user",
                    "project::cgi_remove_user_from_project",
                    "personal_settings::edit_member",
                    "personalsettings::edit_member",
                    "settings::import_members",
                    "bugreports::export_setting",
                    "buglists::export",
                    "bugreports::batchprocess",
                    "bugreports::get_bug_status_transfer_info_for_batch",
                    "bugreports::batch_change_bug_status",
                    "imports::import_bug",
                    "imports::import_preview_bug",
                    "bugs::delete",
                    "bugs::merge",
                    "bugs::bug_merge_validate",
                    "bu_admin",
                    "workspace_admin",
                    "workspace_setting",
                    "workspace_manager",
                    "mini_project_manager",
                    "mini_project_admin",
                    "mini_project_setting"
                ]
            },
            "workitem_permissions": {
                "33142026": [
                    "stories::secrecy",
                    "stories::delete",
                    "stories::batch_delete",
                    "stories::copy_story",
                    "imports::import_story",
                    "imports::import_preview_story",
                    "stories::export_story",
                    "exports::export_table",
                    "exports::export_with_select_fields",
                    "exports::export_xls",
                    "stories::move_story",
                    "stories::move_story_in_view_page",
                    "stories::move_story_view_tree",
                    "categories::index",
                    "categories::view",
                    "categories::add",
                    "categories::edit",
                    "categories::delete",
                    "stories::add",
                    "stories::quickly_add_story",
                    "stories::add_from_mgr",
                    "stories::addce",
                    "stories::add_support",
                    "stories::quick_add_child_story",
                    "stories::quick_add_story",
                    "stories::edit_draft",
                    "stories::remove_draft",
                    "stroies::worktable_remove_draft",
                    "stories::quickly_add_story_from_tab",
                    "stories::transfer_mindmap_to_story",
                    "storywalls::add_card",
                    "stories::edit",
                    "stories::inline_update",
                    "stories::change_parent_story",
                    "stories::changepriority",
                    "stories::ajax_batch_edit_stories_parent",
                    "stories::update_story_category",
                    "attachments::story_add",
                    "attachments::story_download",
                    "attachments::story_delete",
                    "iterations::stories_batch_process_new",
                    "iterations::stories_batch_process_status",
                    "stories::get_story_status_transfer_info_for_batch",
                    "entitysort::set_sort",
                    "stories::set_secret_story",
                    "story::story_transfer_bug",
                    "stories::archive"
                ],
                "1133142026001000007|1133142026001000008|1133142026001000009|1133142026001000011": [
                    "stories::secrecy",
                    "stories::delete",
                    "stories::batch_delete",
                    "stories::copy_story",
                    "imports::import_story",
                    "imports::import_preview_story",
                    "stories::export_story",
                    "exports::export_table",
                    "exports::export_with_select_fields",
                    "exports::export_xls",
                    "stories::move_story",
                    "stories::move_story_in_view_page",
                    "stories::move_story_view_tree",
                    "categories::index",
                    "categories::view",
                    "categories::add",
                    "categories::edit",
                    "categories::delete",
                    "stories::add",
                    "stories::quickly_add_story",
                    "stories::add_from_mgr",
                    "stories::addce",
                    "stories::add_support",
                    "stories::quick_add_child_story",
                    "stories::quick_add_story",
                    "stories::edit_draft",
                    "stories::remove_draft",
                    "stroies::worktable_remove_draft",
                    "stories::quickly_add_story_from_tab",
                    "stories::transfer_mindmap_to_story",
                    "storywalls::add_card",
                    "stories::edit",
                    "stories::inline_update",
                    "stories::change_parent_story",
                    "stories::changepriority",
                    "stories::ajax_batch_edit_stories_parent",
                    "stories::update_story_category",
                    "attachments::story_add",
                    "attachments::story_download",
                    "attachments::story_delete",
                    "iterations::stories_batch_process_new",
                    "iterations::stories_batch_process_status",
                    "stories::get_story_status_transfer_info_for_batch",
                    "entitysort::set_sort",
                    "stories::set_secret_story",
                    "story::story_transfer_bug",
                    "stories::archive"
                ]
            },
            "disabled_permission_map": {
                "33142026": {
                    "story": {
                        "iteration_id": "",
                        "measurement": "",
                        "release_id": "项目下未开启发布计划应用",
                        "feature": "项目下未启用特性",
                        "source": "该需求没有此字段",
                        "type": "该需求没有此字段",
                        "parent_story_edit_iteration": "父需求不能被规划到迭代中，请拆分子需求后规划",
                        "search_filter_batch_related_branch": "源码应用尚未开启",
                        "parent_id": "",
                        "enable_filter_parent_dialog": ""
                    },
                    "bug": {
                        "iteration_id": "",
                        "bug_relate_story": "",
                        "release_id": "项目下未开启发布计划应用",
                        "feature": "项目下未启用特性",
                        "measurement": "",
                        "enable_filter_parent_dialog": ""
                    },
                    "task": {
                        "iteration_id": "",
                        "measurement": "项目应用未开启",
                        "bug_relate_story": "",
                        "release_id": "项目下未开启发布计划应用",
                        "enable_filter_parent_dialog": ""
                    }
                }
            },
            "enable_map": {
                "33142026": {
                    "enabled_task": "false",
                    "enabled_board": "false",
                    "enabled_story": "true",
                    "enabled_bug": "true",
                    "enabled_measurement": "true",
                    "enabled_bug_measurement": "true",
                    "enable_to_test": "false",
                    "accessible_menu_launchforms": "true",
                    "accessible_func_to_test": "true",
                    "enable_testplan": "false",
                    "enable_launchform": "true",
                    "enabled_story_category": "true",
                    "enabled_report": "true",
                    "enabled_quick_create": "true",
                    "enable_parent_story_edit_iteration": "false",
                    "enable_timesheet": "false",
                    "sync_task_iteration_with_story": "true",
                    "enable_feature": "false",
                    "enable_secret_story": "true",
                    "enable_secret_bug": "false",
                    "enabled_tcase": "false",
                    "enabled_program": "false",
                    "enable_multi_linkage_fields": "false"
                }
            },
            "all_edit_field_rules_map": {
                "33142026": {
                    "story": [],
                    "bug": []
                }
            },
            "story_tree_depth_map": {
                "33142026": {
                    "enable_depth_limit": "false",
                    "max_depth": "0"
                }
            },
            "limited_status_create_sub_story_map": {
                "33142026": "false"
            },
            "story_tree_size_map": {
                "33142026": "200"
            },
            "max_sub_story_number_map": {
                "33142026": "200"
            },
            "custom_copy_link_configs": {
                "33142026": []
            },
            "system_copy_link_display_configs": {
                "33142026": [
                    {
                        "name": "复制标题和链接",
                        "code": "titleandlinkcopy",
                        "display": "1",
                        "copytype": "sys"
                    },
                    {
                        "name": "复制标题和内网链接",
                        "code": "converturl",
                        "display": "1",
                        "copytype": "sys"
                    },
                    {
                        "name": "复制标题和项目集链接",
                        "code": "titleandlinkcopyprogram",
                        "display": "1",
                        "copytype": "sys"
                    },
                    {
                        "name": "复制 id",
                        "code": "idcopy",
                        "display": "1",
                        "copytype": "sys"
                    },
                    {
                        "name": "复制源码关键字",
                        "code": "commitkeyword",
                        "display": "1",
                        "copytype": "sys"
                    }
                ]
            },
            "workspace_plan_lock_info": {
                "33142026": {
                    "iteration": []
                }
            },
            "workspace_plan_apps": {
                "33142026": []
            },
            "workspace_plan_permission_map": {
                "33142026": {
                    "iteration": [
                        "iterations::add",
                        "iteration::cgi_create_iteration",
                        "iterations::create_iteration",
                        "iterations::update_logo",
                        "iterations::add_iteration",
                        "iterations::edit",
                        "iteration::cgi_update_iteration",
                        "iterations::edit_iteration",
                        "iteration::ci_job_related",
                        "iteration::set_dashboard",
                        "iteration::ajax_set_dashboard_setting",
                        "iteration::ajax_get_job_with_related_status",
                        "iteration::do_ci_job_related",
                        "iterations::inline_update",
                        "iterations::delete",
                        "iterations::export_iteration",
                        "exports::export_with_select_fields",
                        "exports::export_table",
                        "exports::export_xls",
                        "iterations::planning",
                        "iterations::planning_story",
                        "iterations::do_planning",
                        "iterations::assignment",
                        "iterations::save_assignment_workitems_change",
                        "iterations::to_test",
                        "workspacereports::to_test",
                        "iterations::launch_form",
                        "launchform::iteration_add",
                        "iterations::changestatus"
                    ]
                }
            },
            "workspace_effort_unit_map": {
                "33142026": "day"
            },
            "workspace_holiday_map": [
                {
                    "2024": [
                        {
                            "9": [
                                "14",
                                "29"
                            ],
                            "10": [
                                "12"
                            ]
                        },
                        {
                            "9": [
                                "15",
                                "16",
                                "17"
                            ],
                            "10": [
                                "01",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "02"
                            ]
                        }
                    ],
                    "2025": [
                        {
                            "1": [
                                "26"
                            ],
                            "2": [
                                "08"
                            ],
                            "4": [
                                "27"
                            ],
                            "9": [
                                "28"
                            ],
                            "10": [
                                "11"
                            ]
                        },
                        {
                            "1": [
                                "01",
                                "28",
                                "29",
                                "30",
                                "31"
                            ],
                            "2": [
                                "01",
                                "02",
                                "03",
                                "04"
                            ],
                            "4": [
                                "04",
                                "05",
                                "06"
                            ],
                            "5": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "31"
                            ],
                            "6": [
                                "01",
                                "02"
                            ],
                            "10": [
                                "01",
                                "02",
                                "03",
                                "04",
                                "05",
                                "06",
                                "07",
                                "08"
                            ]
                        }
                    ]
                }
            ],
            "workspace_workitem_apps": {
                "33142026": [
                    {
                        "id": "1133142026001000002",
                        "system_name": "app_step",
                        "label_name": "节点",
                        "enabled": "1"
                    }
                ]
            },
            "quick_transfer_workflow_and_status": {
                "story": {
                    "33142026": {
                        "1133142026001000011": [
                            {
                                "origin_name": "done",
                                "cus_name": "已完成",
                                "is_begin": "0",
                                "is_end": "1",
                                "sort": "0"
                            },
                            {
                                "origin_name": "progressing",
                                "cus_name": "进行中",
                                "is_begin": "0",
                                "is_end": "0",
                                "sort": "0"
                            },
                            {
                                "origin_name": "open",
                                "cus_name": "未开始",
                                "is_begin": "1",
                                "is_end": "0",
                                "sort": "0"
                            }
                        ]
                    }
                }
            },
            "status_allow_create_sub_story_map": {
                "33142026": [
                    "planning",
                    "developing",
                    "resolved",
                    "rejected",
                    "status_3",
                    "status_4",
                    "status_5",
                    "open",
                    "progressing",
                    "done"
                ]
            },
            "sw_item_edit_parent_iteration": []
        }
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1752544216"
}




根据上述内容设计MySQL5 的表结构（可能涉及多个表）， 需要带上完整的备注内容。
确保语法合法 。 不要漏掉吧表。 我需要完整的ddl以及一两条测试数据


腾讯生成的
-- ----------------------------
-- Table structure for work_item_types
-- ----------------------------
DROP TABLE IF EXISTS `work_item_types`;
CREATE TABLE `work_item_types` (
  `id` bigint(20) NOT NULL COMMENT '工作项类型唯一标识',
  `name` varchar(64) NOT NULL COMMENT '类型名称（如story/bug/task）',
  `color` varchar(7) DEFAULT NULL COMMENT '类型颜色代码（如#3582fb）',
  `description` text COMMENT '类型描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作项类型表';

-- ----------------------------
-- Table structure for work_items
-- ----------------------------
DROP TABLE IF EXISTS `work_items`;
CREATE TABLE `work_items` (
  `id` bigint(20) NOT NULL COMMENT '工作项唯一标识',
  `workspace_id` bigint(20) NOT NULL COMMENT '所属工作区ID',
  `type_id` bigint(20) NOT NULL COMMENT '关联类型ID',
  `name` varchar(255) NOT NULL COMMENT '工作项名称',
  `status` varchar(32) NOT NULL COMMENT '当前状态（如planning/developing）',
  `priority` varchar(32) NOT NULL COMMENT '优先级（如high/middle/low）',
  `owner` varchar(64) DEFAULT NULL COMMENT '负责人',
  `begin_date` date DEFAULT NULL COMMENT '开始日期',
  `due_date` date DEFAULT NULL COMMENT '截止日期',
  `effort` decimal(10,2) DEFAULT NULL COMMENT '预估工时',
  `iteration_id` bigint(20) DEFAULT NULL COMMENT '关联迭代ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级工作项ID',
  `level` int(11) DEFAULT NULL COMMENT '层级深度',
  `children_count` int(11) DEFAULT '0' COMMENT '子项数量',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_workspace_type` (`workspace_id`,`type_id`),
  KEY `fk_parent_id` (`parent_id`),
  CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `work_items` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `work_item_types` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作项主表';

-- ----------------------------
-- Table structure for work_item_statuses
-- ----------------------------
DROP TABLE IF EXISTS `work_item_statuses`;
CREATE TABLE `work_item_statuses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_item_type_id` bigint(20) NOT NULL COMMENT '所属工作项类型ID',
  `status_code` varchar(32) NOT NULL COMMENT '状态代码',
  `status_label` varchar(64) NOT NULL COMMENT '状态显示标签',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_type_status` (`work_item_type_id`,`status_code`),
  CONSTRAINT `fk_status_type` FOREIGN KEY (`work_item_type_id`) REFERENCES `work_item_types` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作项状态映射表';

-- ----------------------------
-- Table structure for priorities
-- ----------------------------
DROP TABLE IF EXISTS `priorities`;
CREATE TABLE `priorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `work_item_type_id` bigint(20) NOT NULL COMMENT '所属工作项类型ID',
  `priority_code` varchar(32) NOT NULL COMMENT '优先级代码',
  `label` varchar(32) NOT NULL COMMENT '优先级标签',
  `color` varchar(7) DEFAULT NULL COMMENT '颜色代码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_type_priority` (`work_item_type_id`,`priority_code`),
  CONSTRAINT `fk_priority_type` FOREIGN KEY (`work_item_type_id`) REFERENCES `work_item_types` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优先级配置表';

-- ----------------------------
-- Table structure for iterations
-- ----------------------------
DROP TABLE IF EXISTS `iterations`;
CREATE TABLE `iterations` (
  `id` bigint(20) NOT NULL COMMENT '迭代唯一标识',
  `name` varchar(255) NOT NULL COMMENT '迭代名称',
  `start_date` date NOT NULL COMMENT '开始日期',
  `end_date` date NOT NULL COMMENT '结束日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='迭代信息表';

-- ----------------------------
-- Test data
-- ----------------------------
INSERT INTO `work_item_types` (`id`, `name`, `color`, `description`)
VALUES
(1133142026001000007, 'story', '#3582fb', '用户需求'),
(1133142026001000008, 'featrue', '#64c195', '功能模块'),
(1133142026001000009, 'epic', '#de71f1', '史诗故事');

INSERT INTO `work_items` (`id`, `workspace_id`, `type_id`, `name`, `status`, `priority`, `owner`, `begin_date`, `due_date`, `effort`, `iteration_id`, `parent_id`, `level`)
VALUES
(1133142026001000026, 33142026, 1133142026001000007, '【示例】校园小拍买家应用', 'developing', 'high', '', '2025-07-18', NULL, 7.00, NULL, 0, 0),
(1133142026001000032, 33142026, 1133142026001000007, '【示例】登陆授权', 'planning', 'high', '', '2025-07-18', NULL, NULL, NULL, 1133142026001000026, 1);

INSERT INTO `work_item_statuses` (`work_item_type_id`, `status_code`, `status_label`)
VALUES
(1133142026001000007, 'planning', '规划中'),
(1133142026001000007, 'developing', '实现中'),
(1133142026001000007, 'resolved', '已实现'),
(1133142026001000008, 'new', '新'),
(1133142026001000008, 'in_progress', '处理中');

INSERT INTO `priorities` (`work_item_type_id`, `priority_code`, `label`, `color`)
VALUES
(1133142026001000007, 'high', '高', '#ff6770'),
(1133142026001000007, 'middle', '中', '#28ab80'),
(1133142026001000007, 'low', '低', '#7c8597');

INSERT INTO `iterations` (`id`, `name`, `start_date`, `end_date`)
VALUES
(1133142026001000004, '【买家应用】迭代2', '2025-06-05', '2025-07-18');








deepseek生成的

-- 工作区表
CREATE TABLE workspaces (
    workspace_id VARCHAR(20) PRIMARY KEY COMMENT '工作区ID',
    workspace_name VARCHAR(100) COMMENT '工作区名称',
    effort_unit VARCHAR(10) DEFAULT 'day' COMMENT '工作量单位'
) COMMENT='工作区基本信息';

-- 迭代表
CREATE TABLE iterations (
    iteration_id VARCHAR(30) PRIMARY KEY COMMENT '迭代ID',
    workspace_id VARCHAR(20) COMMENT '所属工作区ID',
    name VARCHAR(100) COMMENT '迭代名称',
    parent_id VARCHAR(30) DEFAULT '0' COMMENT '父迭代ID',
    sort_order INT COMMENT '排序序号',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id)
) COMMENT='迭代信息';

-- 工作项类型表
CREATE TABLE workitem_types (
    type_id VARCHAR(30) PRIMARY KEY COMMENT '类型ID',
    workspace_id VARCHAR(20) COMMENT '所属工作区ID',
    name VARCHAR(50) COMMENT '类型名称(如:需求/任务)',
    english_name VARCHAR(50) COMMENT '英文名称',
    color VARCHAR(20) COMMENT '代表颜色',
    entity_type ENUM('story','bug','task','workitem') COMMENT '实体类型',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id)
) COMMENT='工作项类型配置';

-- 工作项表
CREATE TABLE workitems (
    id VARCHAR(30) PRIMARY KEY COMMENT '工作项ID',
    workspace_id VARCHAR(20) COMMENT '所属工作区ID',
    type_id VARCHAR(30) COMMENT '工作项类型ID',
    name VARCHAR(255) COMMENT '工作项名称',
    status VARCHAR(50) COMMENT '当前状态',
    priority ENUM('high','middle','low','nice to have') COMMENT '优先级',
    owner VARCHAR(100) COMMENT '负责人',
    effort DECIMAL(10,2) COMMENT '预估工作量',
    iteration_id VARCHAR(30) COMMENT '所属迭代ID',
    parent_id VARCHAR(30) DEFAULT '0' COMMENT '父工作项ID',
    level TINYINT COMMENT '层级(0=根节点)',
    due_date DATE COMMENT '截止日期',
    is_delay TINYINT(1) DEFAULT 0 COMMENT '是否延期(1=是)',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id),
    FOREIGN KEY (type_id) REFERENCES workitem_types(type_id),
    FOREIGN KEY (iteration_id) REFERENCES iterations(iteration_id)
) COMMENT='工作项详细信息';

-- 状态映射表
CREATE TABLE status_mappings (
    mapping_id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) COMMENT '工作区ID',
    entity_type ENUM('story','bug','task') COMMENT '实体类型',
    status_key VARCHAR(50) COMMENT '状态键名',
    status_label VARCHAR(50) COMMENT '状态显示名称',
    UNIQUE KEY (workspace_id, entity_type, status_key),
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id)
) COMMENT='状态值映射配置';

-- 迭代字段选项表
CREATE TABLE iteration_field_options (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) COMMENT '工作区ID',
    entity_type ENUM('story','bug','task','workitem') COMMENT '实体类型',
    iteration_id VARCHAR(30) COMMENT '迭代ID',
    label VARCHAR(100) COMMENT '显示名称',
    sort_order INT COMMENT '排序序号',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id),
    FOREIGN KEY (iteration_id) REFERENCES iterations(iteration_id)
) COMMENT='迭代字段下拉选项';

-- 权限表
CREATE TABLE permissions (
    permission_id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id VARCHAR(20) COMMENT '工作区ID',
    permission_code VARCHAR(100) COMMENT '权限代码',
    permission_name VARCHAR(200) COMMENT '权限名称',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(workspace_id)
) COMMENT='工作区权限配置';

-- 插入测试数据
INSERT INTO workspaces (workspace_id, workspace_name) 
VALUES ('33142026', '大型敏捷');

INSERT INTO iterations (iteration_id, workspace_id, name, sort_order) 
VALUES ('1133142026001000004', '33142026', '【买家应用】迭代2', 100000400000);

INSERT INTO workitem_types (type_id, workspace_id, name, english_name, entity_type) 
VALUES ('1133142026001000007', '33142026', '需求', 'story', 'story');

INSERT INTO workitems (id, workspace_id, type_id, name, status, priority, iteration_id, parent_id, level, due_date, is_delay) 
VALUES ('1133142026001000039', '33142026', '1133142026001000007', '【示例】微信授权登陆', 
        'planning', 'high', '1133142026001000004', '1133142026001000032', 2, '2025-06-06', 1);

INSERT INTO status_mappings (workspace_id, entity_type, status_key, status_label) 
VALUES ('33142026', 'story', 'planning', '规划中');

INSERT INTO iteration_field_options (workspace_id, entity_type, iteration_id, label, sort_order) 
VALUES ('33142026', 'story', '1133142026001000004', '【买家应用】迭代2', 100000400000);

INSERT INTO permissions (workspace_id, permission_code, permission_name) 
VALUES ('33142026', 'iterations::add', '添加迭代权限');














-- copilot生成的   结合上面两个生成的近乎完美版本
-- 工作区表
DROP TABLE IF EXISTS `workspaces`;
CREATE TABLE `workspaces` (
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作区ID',
  `workspace_name` VARCHAR(100) NOT NULL COMMENT '工作区名称',
  `effort_unit` VARCHAR(10) DEFAULT 'day' COMMENT '工作量单位',
  PRIMARY KEY (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作区基本信息';

-- 迭代表
DROP TABLE IF EXISTS `iterations`;
CREATE TABLE `iterations` (
  `iteration_id` VARCHAR(30) NOT NULL COMMENT '迭代ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '所属工作区ID',
  `name` VARCHAR(100) NOT NULL COMMENT '迭代名称',
  `parent_id` VARCHAR(30) DEFAULT '0' COMMENT '父迭代ID',
  `sort_order` BIGINT DEFAULT 0 COMMENT '排序序号',
  `start_date` DATE DEFAULT NULL COMMENT '开始日期',
  `end_date` DATE DEFAULT NULL COMMENT '结束日期',
  PRIMARY KEY (`iteration_id`),
  KEY `idx_workspace_id` (`workspace_id`),
  CONSTRAINT `fk_iteration_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='迭代信息';

-- 工作项类型表
DROP TABLE IF EXISTS `workitem_types`;
CREATE TABLE `workitem_types` (
  `type_id` VARCHAR(30) NOT NULL COMMENT '类型ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '所属工作区ID',
  `name` VARCHAR(50) NOT NULL COMMENT '类型名称(如:需求/任务)',
  `english_name` VARCHAR(50) NOT NULL COMMENT '英文名称',
  `color` VARCHAR(20) DEFAULT NULL COMMENT '代表颜色',
  `entity_type` ENUM('story','bug','task','workitem') NOT NULL COMMENT '实体类型',
  `description` TEXT COMMENT '类型描述',
  PRIMARY KEY (`type_id`),
  KEY `idx_type_workspace` (`workspace_id`),
  CONSTRAINT `fk_type_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作项类型配置';

-- 工作项主表
DROP TABLE IF EXISTS `workitems`;
CREATE TABLE `workitems` (
  `id` VARCHAR(30) NOT NULL COMMENT '工作项ID',
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '所属工作区ID',
  `type_id` VARCHAR(30) NOT NULL COMMENT '工作项类型ID',
  `name` VARCHAR(255) NOT NULL COMMENT '工作项名称',
  `status` VARCHAR(50) NOT NULL COMMENT '当前状态',
  `priority` ENUM('high','middle','low','nice to have') DEFAULT NULL COMMENT '优先级',
  `owner` VARCHAR(100) DEFAULT NULL COMMENT '负责人',
  `effort` DECIMAL(10,2) DEFAULT NULL COMMENT '预估工作量',
  `iteration_id` VARCHAR(30) DEFAULT NULL COMMENT '所属迭代ID',
  `parent_id` VARCHAR(30) DEFAULT '0' COMMENT '父工作项ID',
  `level` TINYINT DEFAULT 0 COMMENT '层级(0=根节点)',
  `due_date` DATE DEFAULT NULL COMMENT '截止日期',
  `begin_date` DATE DEFAULT NULL COMMENT '开始日期',
  `is_delay` TINYINT(1) DEFAULT 0 COMMENT '是否延期(1=是)',
  `children_count` INT DEFAULT 0 COMMENT '子项数量',
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_workspace_type` (`workspace_id`,`type_id`),
  KEY `idx_iteration_id` (`iteration_id`),
  KEY `idx_parent_id` (`parent_id`),
  CONSTRAINT `fk_workitem_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`),
  CONSTRAINT `fk_workitem_type` FOREIGN KEY (`type_id`) REFERENCES `workitem_types` (`type_id`),
  CONSTRAINT `fk_workitem_iteration` FOREIGN KEY (`iteration_id`) REFERENCES `iterations` (`iteration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作项详细信息';

-- 状态映射表
DROP TABLE IF EXISTS `status_mappings`;
CREATE TABLE `status_mappings` (
  `mapping_id` INT AUTO_INCREMENT PRIMARY KEY,
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作区ID',
  `entity_type` ENUM('story','bug','task','workitem') NOT NULL COMMENT '实体类型',
  `status_key` VARCHAR(50) NOT NULL COMMENT '状态键名',
  `status_label` VARCHAR(50) NOT NULL COMMENT '状态显示名称',
  UNIQUE KEY `uk_workspace_entity_status` (`workspace_id`, `entity_type`, `status_key`),
  CONSTRAINT `fk_statusmap_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='状态值映射配置';

-- 优先级配置表
DROP TABLE IF EXISTS `priorities`;
CREATE TABLE `priorities` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作区ID',
  `type_id` VARCHAR(30) NOT NULL COMMENT '工作项类型ID',
  `priority_code` VARCHAR(32) NOT NULL COMMENT '优先级代码',
  `label` VARCHAR(32) NOT NULL COMMENT '优先级标签',
  `color` VARCHAR(7) DEFAULT NULL COMMENT '颜色代码',
  UNIQUE KEY `uk_type_priority` (`workspace_id`, `type_id`, `priority_code`),
  CONSTRAINT `fk_priority_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`),
  CONSTRAINT `fk_priority_type` FOREIGN KEY (`type_id`) REFERENCES `workitem_types` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优先级配置表';

-- 迭代字段选项表
DROP TABLE IF EXISTS `iteration_field_options`;
CREATE TABLE `iteration_field_options` (
  `option_id` INT AUTO_INCREMENT PRIMARY KEY,
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作区ID',
  `entity_type` ENUM('story','bug','task','workitem') NOT NULL COMMENT '实体类型',
  `iteration_id` VARCHAR(30) NOT NULL COMMENT '迭代ID',
  `label` VARCHAR(100) NOT NULL COMMENT '显示名称',
  `sort_order` BIGINT DEFAULT 0 COMMENT '排序序号',
  CONSTRAINT `fk_iteropt_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`),
  CONSTRAINT `fk_iteropt_iteration` FOREIGN KEY (`iteration_id`) REFERENCES `iterations` (`iteration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='迭代字段下拉选项';

-- 权限表
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `permission_id` INT AUTO_INCREMENT PRIMARY KEY,
  `workspace_id` VARCHAR(20) NOT NULL COMMENT '工作区ID',
  `permission_code` VARCHAR(100) NOT NULL COMMENT '权限代码',
  `permission_name` VARCHAR(200) NOT NULL COMMENT '权限名称',
  CONSTRAINT `fk_permission_workspace` FOREIGN KEY (`workspace_id`) REFERENCES `workspaces` (`workspace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作区权限配置';

-- ----------------------------
-- 测试数据
-- ----------------------------
INSERT INTO `workspaces` (`workspace_id`, `workspace_name`) VALUES ('33142026', '大型敏捷');

INSERT INTO `iterations` (`iteration_id`, `workspace_id`, `name`, `sort_order`, `start_date`, `end_date`)
VALUES ('1133142026001000004', '33142026', '【买家应用】迭代2', 100000400000, '2025-06-05', '2025-07-18');

INSERT INTO `workitem_types` (`type_id`, `workspace_id`, `name`, `english_name`, `color`, `entity_type`, `description`)
VALUES ('1133142026001000007', '33142026', '需求', 'story', '#3582fb', 'story', '用户需求');

INSERT INTO `workitems` (`id`, `workspace_id`, `type_id`, `name`, `status`, `priority`, `owner`, `effort`, `iteration_id`, `parent_id`, `level`, `due_date`, `begin_date`, `is_delay`)
VALUES ('1133142026001000039', '33142026', '1133142026001000007', '【示例】微信授权登陆', 'planning', 'high', '', 7.00, '1133142026001000004', '0', 2, '2025-06-06', '2025-06-05', 1);

INSERT INTO `status_mappings` (`workspace_id`, `entity_type`, `status_key`, `status_label`)
VALUES ('33142026', 'story', 'planning', '规划中');

INSERT INTO `priorities` (`workspace_id`, `type_id`, `priority_code`, `label`, `color`)
VALUES ('33142026', '1133142026001000007', 'high', '高', '#ff6770');

INSERT INTO `iteration_field_options` (`workspace_id`, `entity_type`, `iteration_id`, `label`, `sort_order`)
VALUES ('33142026', 'story', '1133142026001000004', '【买家应用】迭代2', 100000400000);

INSERT INTO `permissions` (`workspace_id`, `permission_code`, `permission_name`)
VALUES ('33142026', 'iterations::add', '添加迭代权限');