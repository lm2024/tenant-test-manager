需求
https://www.tapd.cn/api/aggregation/story_aggregation/get_stories_list?workspace_id=60498179&data[type]=story&location=/prong/stories/stories_list&data[query_token]=&from=stories_list
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
        "stories_list": {
            "data": {
                "page": "1",
                "perpage": "50",
                "relation_info": [],
                "display_setting": [
                    "id",
                    "name",
                    "priority",
                    "iteration_id",
                    "status",
                    "owner",
                    "begin",
                    "due"
                ],
                "export_token": "export_cache_4f7466d29cce37a29e2b102d07aaff50",
                "view_mode": "slide",
                "socket_login_data": {
                    "socket_path": "https:\/\/spider.tapd.cn\/workitems",
                    "login_data": {
                        "user_nick": "未命名小程序::328519613",
                        "workspace_id": "60498179",
                        "timespan": "1749777228.8811155",
                        "data_id": "workitems",
                        "token": "87f4bd7f602dbc8ea67e530ed24583ee7bb4fe6f"
                    },
                    "room_ids": [
                        "workitems_change_stories_list_328519613_1749777228_2276082"
                    ],
                    "socket_id": "60498179",
                    "need_disconnect": "1"
                },
                "category_counts": {
                    "-1": "10",
                    "1160498179001000018": "10",
                    "1160498179001000016": "0",
                    "1160498179001000017": "0",
                    "1160498179001000019": "0",
                    "1160498179001000020": "0",
                    "0": "20"
                },
                "enable_quick_create": "1",
                "is_admin": "1",
                "workspace_tree": [],
                "select_project_id": [
                    "60498179"
                ],
                "is_parent_project": "0",
                "project_special_fields": {
                    "60498179": {
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
                                "id": "1160498179001000023",
                                "label": "【产品】+开发环境+版本号（如：【途强在线】web v4.0.0）",
                                "parent_id": "0",
                                "sort": "100001100000",
                                "icon": "",
                                "origin_sort": "0",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldname": "iterationid",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000008",
                                "label": "【买家应用】迭代2",
                                "parent_id": "0",
                                "sort": "100000800000",
                                "icon": "",
                                "origin_sort": "1",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldname": "iterationid",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000010",
                                "label": "【卖家应用】迭代2",
                                "parent_id": "0",
                                "sort": "100001000000",
                                "icon": "",
                                "origin_sort": "2",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldname": "iterationid",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000007",
                                "label": "【买家应用】迭代1",
                                "parent_id": "0",
                                "sort": "100000700000",
                                "icon": "",
                                "origin_sort": "3",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldname": "iterationid",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000009",
                                "label": "【卖家应用】迭代1",
                                "parent_id": "0",
                                "sort": "100000900000",
                                "icon": "",
                                "origin_sort": "4",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldname": "iterationid",
                                "is_parent": "false"
                            }
                        ]
                    }
                },
                "is_load_all": "1",
                "tree_snapshoot": [],
                "enabled_measurement_map": {
                    "60498179": "true"
                },
                "view_info": {
                    "id": "1000000000000000016",
                    "title": "所有的",
                    "conf_id": "1160498179001000415"
                },
                "head_width_map": "",
                "result_workspace_ids": [
                    "60498179"
                ],
                "custom_fields_map_key": [],
                "lazy_no_permission_ids": [],
                "workspace_configs": {
                    "workitem_types": [
                        {
                            "id": "1160498179001000016",
                            "name": "需求",
                            "english_name": "story",
                            "color": "#3582fb",
                            "icon": "",
                            "icon_small": "",
                            "workspace_id": "60498179",
                            "status": "3",
                            "children_ids": "",
                            "parent_ids": "",
                            "remark": "",
                            "workflow_id": "1160498179001000013",
                            "app_id": "1",
                            "modified": "2025-05-30 11:17:12",
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
                            "id": "1160498179001000017",
                            "name": "特性",
                            "english_name": "featrue",
                            "color": "#64c195",
                            "icon": "57051699\/icon\/1157051699001000038",
                            "icon_small": "57051699\/icon\/1157051699001000039",
                            "workspace_id": "60498179",
                            "status": "3",
                            "children_ids": "1160498179001000016",
                            "parent_ids": "",
                            "remark": "",
                            "workflow_id": "1160498179001000014",
                            "app_id": "1",
                            "modified": "2025-05-30 11:17:12",
                            "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000038.png?token=799d01cd2b3b246ba0a5f825aa72e95135c1c904ea30048551919af3e9540208&version=1748575032&company_id=61298134",
                            "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000039.png?token=be5e48c24e8a1e31dd901f45531222992188c6ce0727ea4633e6d7007905a240&version=1748575032&company_id=61298134",
                            "is_new_task_workitem": "false",
                            "workflow_type": "classic",
                            "sub_workitem_types_create_permission": {
                                "setting": "custom",
                                "types": {
                                    "1160498179001000016": {
                                        "workitemtype": {
                                            "id": "1160498179001000016",
                                            "workspace_id": "60498179",
                                            "app_id": "1",
                                            "plan_app_id": "0",
                                            "entity_type": "story",
                                            "name": "需求",
                                            "english_name": "story",
                                            "status": "3",
                                            "color": "#3582fb",
                                            "icon": "",
                                            "icon_small": "",
                                            "workflow_id": "1160498179001000013",
                                            "template_ids": "1160498179001000025",
                                            "default_template_id": "0",
                                            "creator": "tapd_system",
                                            "created": "2025-05-30 11:17:12",
                                            "modified_by": "tapd_system",
                                            "modified": "2025-05-30 11:17:12",
                                            "sort": "9999",
                                            "children_ids": "",
                                            "parent_ids": "",
                                            "is_menu": "0",
                                            "remark": "",
                                            "icon_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story.png",
                                            "icon_small_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/story_small.png"
                                        }
                                    }
                                }
                            }
                        },
                        {
                            "id": "1160498179001000018",
                            "name": "史诗故事",
                            "english_name": "epic",
                            "color": "#de71f1",
                            "icon": "57051699\/icon\/1157051699001000040",
                            "icon_small": "57051699\/icon\/1157051699001000041",
                            "workspace_id": "60498179",
                            "status": "3",
                            "children_ids": "1160498179001000017",
                            "parent_ids": "",
                            "remark": "",
                            "workflow_id": "1160498179001000013",
                            "app_id": "1",
                            "modified": "2025-05-30 11:17:12",
                            "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000040.png?token=bb7f99d83cb58731fd41c46fbca7c4e21965248efedd07b06ea0aced7b3e4306&version=1748575032&company_id=61298134",
                            "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000041.png?token=a6ab4f57be75a43bf1f211fcb47d6a6e2c4bcd85ad882fee154e823878d0e9d2&version=1748575032&company_id=61298134",
                            "is_new_task_workitem": "false",
                            "workflow_type": "classic",
                            "sub_workitem_types_create_permission": {
                                "setting": "custom",
                                "types": {
                                    "1160498179001000017": {
                                        "workitemtype": {
                                            "id": "1160498179001000017",
                                            "workspace_id": "60498179",
                                            "app_id": "1",
                                            "plan_app_id": "0",
                                            "entity_type": "story",
                                            "name": "特性",
                                            "english_name": "featrue",
                                            "status": "3",
                                            "color": "#64c195",
                                            "icon": "57051699\/icon\/1157051699001000038",
                                            "icon_small": "57051699\/icon\/1157051699001000039",
                                            "workflow_id": "1160498179001000014",
                                            "template_ids": "1160498179001000026",
                                            "default_template_id": "0",
                                            "creator": "tapd_system",
                                            "created": "2025-05-30 11:17:12",
                                            "modified_by": "tapd_system",
                                            "modified": "2025-05-30 11:17:12",
                                            "sort": "9999",
                                            "children_ids": "1160498179001000016",
                                            "parent_ids": "",
                                            "is_menu": "0",
                                            "remark": "",
                                            "icon_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000038.png?token=799d01cd2b3b246ba0a5f825aa72e95135c1c904ea30048551919af3e9540208&version=1748575032&company_id=61298134",
                                            "icon_small_viper": "https:\/\/viper.tapd.cn\/icon\/files\/57051699\/icon\/1157051699001000039.png?token=be5e48c24e8a1e31dd901f45531222992188c6ce0727ea4633e6d7007905a240&version=1748575032&company_id=61298134"
                                        }
                                    }
                                }
                            }
                        },
                        {
                            "id": "1160498179001000020",
                            "name": "任务",
                            "english_name": "task",
                            "color": "#182b50",
                            "icon": "",
                            "icon_small": "",
                            "workspace_id": "60498179",
                            "status": "3",
                            "children_ids": "|",
                            "parent_ids": "",
                            "remark": "",
                            "workflow_id": "1160498179001000016",
                            "app_id": "1",
                            "modified": "2025-05-30 11:17:13",
                            "icon_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/default.png",
                            "icon_small_viper": "https:\/\/www.tapd.cn\/\/img\/workitem_type\/default_icon\/@2\/default_small.png",
                            "menu_workitem_type_id": "1160498179001000006",
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
                        "story": {
                            "first_steps": {
                                "1160498179001000016": "planning",
                                "1160498179001000018": "planning",
                                "1160498179001000017": "planning",
                                "1160498179001000020": "open"
                            },
                            "end_steps": {
                                "60498179": {
                                    "1160498179001000016": [
                                        "resolved",
                                        "rejected"
                                    ],
                                    "1160498179001000018": [
                                        "resolved",
                                        "rejected"
                                    ],
                                    "1160498179001000017": [
                                        "resolved",
                                        "rejected"
                                    ],
                                    "1160498179001000020": [
                                        "done"
                                    ]
                                }
                            },
                            "status_map": {
                                "60498179": {
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
                                "60498179": {
                                    "1160498179001000016": {
                                        "planning": "0",
                                        "developing": "0",
                                        "resolved": "0",
                                        "rejected": "0"
                                    },
                                    "1160498179001000018": {
                                        "planning": "0",
                                        "developing": "0",
                                        "resolved": "0",
                                        "rejected": "0"
                                    },
                                    "1160498179001000017": {
                                        "planning": "0",
                                        "developing": "0",
                                        "resolved": "0",
                                        "rejected": "0",
                                        "status_3": "0",
                                        "status_4": "0",
                                        "status_5": "0"
                                    },
                                    "1160498179001000020": {
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
                        "60498179": {
                            "story": {
                                "id": {
                                    "editabled_type": "text",
                                    "system_name": "id",
                                    "required": ""
                                },
                                "name": {
                                    "editabled_type": "text",
                                    "system_name": "name",
                                    "required": "1"
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
                                            "id": "1160498179001000023",
                                            "pid": "0",
                                            "name": "【产品】+开发环境+版本号（如：【途强在线】web v4.0.0）",
                                            "disabled": "",
                                            "disabledtips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "true"
                                        },
                                        {
                                            "id": "1160498179001000008",
                                            "pid": "0",
                                            "name": "【买家应用】迭代2",
                                            "disabled": "",
                                            "disabledtips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "true"
                                        },
                                        {
                                            "id": "1160498179001000010",
                                            "pid": "0",
                                            "name": "【卖家应用】迭代2",
                                            "disabled": "",
                                            "disabledtips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "true"
                                        },
                                        {
                                            "id": "1160498179001000007",
                                            "pid": "0",
                                            "name": "【买家应用】迭代1",
                                            "disabled": "",
                                            "disabledtips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "true"
                                        },
                                        {
                                            "id": "1160498179001000009",
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
                                            "id": "1160498179001000023",
                                            "label": "【产品】+开发环境+版本号（如：【途强在线】web v4.0.0）",
                                            "parent_id": "0",
                                            "sort": "100001100000",
                                            "icon": "",
                                            "origin_sort": "0",
                                            "disabled": "",
                                            "disabled_tips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "false"
                                        },
                                        {
                                            "id": "1160498179001000008",
                                            "label": "【买家应用】迭代2",
                                            "parent_id": "0",
                                            "sort": "100000800000",
                                            "icon": "",
                                            "origin_sort": "1",
                                            "disabled": "",
                                            "disabled_tips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "false"
                                        },
                                        {
                                            "id": "1160498179001000010",
                                            "label": "【卖家应用】迭代2",
                                            "parent_id": "0",
                                            "sort": "100001000000",
                                            "icon": "",
                                            "origin_sort": "2",
                                            "disabled": "",
                                            "disabled_tips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "false"
                                        },
                                        {
                                            "id": "1160498179001000007",
                                            "label": "【买家应用】迭代1",
                                            "parent_id": "0",
                                            "sort": "100000700000",
                                            "icon": "",
                                            "origin_sort": "3",
                                            "disabled": "",
                                            "disabled_tips": "",
                                            "fieldname": "iterationid",
                                            "is_parent": "false"
                                        },
                                        {
                                            "id": "1160498179001000009",
                                            "label": "【卖家应用】迭代1",
                                            "parent_id": "0",
                                            "sort": "100000900000",
                                            "icon": "",
                                            "origin_sort": "4",
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
                                            "label": "?workspace_id=60498179&r=3"
                                        },
                                        {
                                            "value": "chooser_type",
                                            "label": "3"
                                        }
                                    ]
                                },
                                "begin": {
                                    "editabled_type": "date",
                                    "system_name": "begin",
                                    "required": ""
                                },
                                "due": {
                                    "editabled_type": "date",
                                    "system_name": "due",
                                    "required": ""
                                }
                            }
                        }
                    }
                },
                "workitem_app_map": {
                    "1160498179001000006": [
                        "1160498179001000020"
                    ]
                },
                "plan_id_name_map": {
                    "1160498179001000009": "【卖家应用】迭代1",
                    "1160498179001000010": "【卖家应用】迭代2"
                },
                "file_list": [],
                "attachment_list": [],
                "is_new_task_display": "1",
                "app_id": "1",
                "is_checklist": "0",
                "workitemtype_childs_map": {
                    "1160498179001000016": [],
                    "1160498179001000017": [
                        "1160498179001000016"
                    ],
                    "1160498179001000018": [
                        "1160498179001000017"
                    ],
                    "1160498179001000020": ""
                },
                "all_story_count": "10",
                "total_count": "2",
                "stories_list": [
                    {
                        "story": {
                            "id": "1160498179001000078",
                            "name": "【示例】校园小拍卖家应用",
                            "priority": "middle",
                            "iteration_id": "0",
                            "status": "planning",
                            "owner": "",
                            "begin": "",
                            "due": "2025-07-12",
                            "children_id": "|1160498179001000081|1160498179001000080",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000018",
                            "level": "0",
                            "path": "1160498179001000078",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "0",
                            "remain": "6",
                            "effort_completed": "0",
                            "sort": "100007800000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "1",
                            "sub_task_count": "0",
                            "sub_story_count": "2",
                            "parent_name": "",
                            "relative_id": "0",
                            "match_condition": "1",
                            "short_id": "1000078",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1752249600",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "0",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000081",
                            "name": "【示例】订单管理",
                            "priority": "high",
                            "iteration_id": "0",
                            "status": "developing",
                            "owner": "",
                            "begin": "",
                            "due": "2025-07-12",
                            "children_id": "|1160498179001000089|1160498179001000088",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000017",
                            "level": "1",
                            "path": "1160498179001000078:1160498179001000081",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000078",
                            "remain": "2",
                            "effort_completed": "0",
                            "sort": "100037100000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "2",
                            "parent_name": "【示例】校园小拍卖家应用",
                            "relative_id": "1160498179001000078",
                            "match_condition": "1",
                            "short_id": "1000081",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1752249600",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000078",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000089",
                            "name": "【示例】订单处理",
                            "priority": "high",
                            "iteration_id": "1160498179001000010",
                            "status": "developing",
                            "owner": "",
                            "begin": "",
                            "due": "2025-07-12",
                            "children_id": "|",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "2",
                            "path": "1160498179001000078:1160498179001000081:1160498179001000089",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000081",
                            "remain": "2",
                            "effort_completed": "0",
                            "sort": "100037500000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": {
                                "id": "1160498179001000010",
                                "name": "【卖家应用】迭代2"
                            },
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "0",
                            "parent_name": "【示例】订单管理",
                            "relative_id": "1160498179001000081",
                            "match_condition": "1",
                            "short_id": "1000089",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1752249600",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000081",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000088",
                            "name": "【示例】订单查看",
                            "priority": "middle",
                            "iteration_id": "0",
                            "status": "developing",
                            "owner": "",
                            "begin": "",
                            "due": "2025-06-09",
                            "children_id": "||1160498179001000135",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "2",
                            "path": "1160498179001000078:1160498179001000081:1160498179001000088",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000081",
                            "remain": "0",
                            "effort_completed": "0",
                            "sort": "100037600000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "1",
                            "parent_name": "【示例】订单管理",
                            "relative_id": "1160498179001000081",
                            "match_condition": "1",
                            "short_id": "1000088",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1749398400",
                            "delay": "1",
                            "is_delay": "1",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000081",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000135",
                            "name": "【示例】订单查看",
                            "priority": "middle",
                            "iteration_id": "0",
                            "status": "planning",
                            "owner": "",
                            "begin": "",
                            "due": "2025-06-09",
                            "children_id": "||1160498179001000136",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "3",
                            "path": "1160498179001000078:1160498179001000081:1160498179001000088:1160498179001000135:",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "未命名小程序",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000088",
                            "remain": "0",
                            "effort_completed": "0",
                            "sort": "100037600000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "1",
                            "parent_name": "【示例】订单查看",
                            "relative_id": "1160498179001000088",
                            "match_condition": "1",
                            "short_id": "1000135",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1749398400",
                            "delay": "1",
                            "is_delay": "1",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000088",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000136",
                            "name": "【示例】订单查看",
                            "priority": "middle",
                            "iteration_id": "0",
                            "status": "planning",
                            "owner": "",
                            "begin": "",
                            "due": "2025-06-09",
                            "children_id": "|",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "4",
                            "path": "1160498179001000078:1160498179001000081:1160498179001000088:1160498179001000135::1160498179001000136:",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "未命名小程序",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000135",
                            "remain": "0",
                            "effort_completed": "0",
                            "sort": "100037600000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "0",
                            "parent_name": "【示例】订单查看",
                            "relative_id": "1160498179001000135",
                            "match_condition": "1",
                            "short_id": "1000136",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1749398400",
                            "delay": "1",
                            "is_delay": "1",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000135",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000080",
                            "name": "【示例】商品详情管理",
                            "priority": "middle",
                            "iteration_id": "0",
                            "status": "status_4",
                            "owner": "",
                            "begin": "",
                            "due": "2025-07-11",
                            "children_id": "|1160498179001000087|1160498179001000086",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000017",
                            "level": "1",
                            "path": "1160498179001000078:1160498179001000080",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000078",
                            "remain": "4",
                            "effort_completed": "0",
                            "sort": "100037200000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "2",
                            "parent_name": "【示例】校园小拍卖家应用",
                            "relative_id": "1160498179001000078",
                            "match_condition": "1",
                            "short_id": "1000080",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1752163200",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000078",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000086",
                            "name": "【示例】商品图片上传",
                            "priority": "low",
                            "iteration_id": "1160498179001000009",
                            "status": "planning",
                            "owner": "",
                            "begin": "",
                            "due": "2025-07-08",
                            "children_id": "|",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "2",
                            "path": "1160498179001000078:1160498179001000080:1160498179001000086",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000080",
                            "remain": "2",
                            "effort_completed": "0",
                            "sort": "100037900000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": {
                                "id": "1160498179001000009",
                                "name": "【卖家应用】迭代1"
                            },
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "0",
                            "parent_name": "【示例】商品详情管理",
                            "relative_id": "1160498179001000080",
                            "match_condition": "1",
                            "short_id": "1000086",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1751904000",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000080",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000087",
                            "name": "【示例】商品详情编辑",
                            "priority": "middle",
                            "iteration_id": "1160498179001000009",
                            "status": "developing",
                            "owner": "",
                            "begin": "",
                            "due": "2025-06-02",
                            "children_id": "|",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000016",
                            "level": "2",
                            "path": "1160498179001000078:1160498179001000080:1160498179001000087",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "tapd",
                            "ancestor_id": "1160498179001000078",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "1160498179001000080",
                            "remain": "2",
                            "effort_completed": "0",
                            "sort": "100037800000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "tapd",
                            "display": "1",
                            "iteration_info": {
                                "id": "1160498179001000009",
                                "name": "【卖家应用】迭代1"
                            },
                            "has_sub_task": "0",
                            "tree_node_count": "9",
                            "relate_story_count": "0",
                            "sub_task_count": "0",
                            "sub_story_count": "0",
                            "parent_name": "【示例】商品详情管理",
                            "relative_id": "1160498179001000080",
                            "match_condition": "1",
                            "short_id": "1000087",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "1104508800",
                            "due_ts": "1748793600",
                            "delay": "1",
                            "is_delay": "1",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "1160498179001000080",
                            "secret_workitem": "0"
                        }
                    },
                    {
                        "story": {
                            "id": "1160498179001000134",
                            "name": "12121212121",
                            "priority": "",
                            "iteration_id": "0",
                            "status": "progressing",
                            "owner": "",
                            "begin": "",
                            "due": "",
                            "children_id": "|",
                            "app_id": "1",
                            "workitem_type_id": "1160498179001000020",
                            "level": "0",
                            "path": "1160498179001000134:",
                            "workspace_id": "60498179",
                            "attachment_count": "0",
                            "step": "",
                            "creator": "未命名小程序",
                            "ancestor_id": "1160498179001000134",
                            "follower": "",
                            "secret_root_id": "0",
                            "parent_id": "0",
                            "remain": "0",
                            "effort_completed": "0",
                            "sort": "100008100000",
                            "entity_type": "story",
                            "progress": "0",
                            "completed": "",
                            "modifier": "",
                            "display": "1",
                            "iteration_info": "",
                            "has_sub_task": "0",
                            "tree_node_count": "1",
                            "relate_story_count": "1",
                            "sub_task_count": "0",
                            "sub_story_count": "0",
                            "parent_name": "",
                            "relative_id": "0",
                            "match_condition": "1",
                            "short_id": "1000134",
                            "workspace_name": "大型团队敏捷研发",
                            "begin_ts": "",
                            "due_ts": "",
                            "delay": "0",
                            "is_delay": "0",
                            "is_done_status": "0",
                            "step_delay_info": [],
                            "_parent_id": "0",
                            "menu_workitem_type_id": "1160498179001000006",
                            "secret_workitem": "0"
                        }
                    }
                ]
            },
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749777228"
        },
        "group_fields": [
            {
                "entity_type": "common",
                "display_entity_type": "公共",
                "fields": []
            },
            {
                "entity_type": "story",
                "display_entity_type": "需求",
                "fields": [
                    {
                        "value": "status",
                        "label": "状态",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "workitem_type_id",
                        "label": "需求类别",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "iteration_id",
                        "label": "迭代",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "module",
                        "label": "模块",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "priority",
                        "label": "优先级",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "owner",
                        "label": "处理人",
                        "html_type": [
                            "user_chooser"
                        ]
                    },
                    {
                        "value": "developer",
                        "label": "开发人员",
                        "html_type": [
                            "user_chooser"
                        ]
                    },
                    {
                        "value": "creator",
                        "label": "创建人",
                        "html_type": [
                            "user_chooser"
                        ]
                    },
                    {
                        "value": "category_id",
                        "label": "分类",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "version",
                        "label": "版本",
                        "html_type": [
                            "select"
                        ]
                    },
                    {
                        "value": "label",
                        "label": "标签",
                        "html_type": [
                            "multi_select"
                        ]
                    },
                    {
                        "value": "custom_field_需求来源",
                        "label": "需求来源",
                        "html_type": [
                            "select"
                        ]
                    }
                ]
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











CREATE TABLE workspaces (
    id BIGINT UNSIGNED NOT NULL COMMENT '工作区ID',
    name VARCHAR(255) NOT NULL COMMENT '工作区名称',
    config JSON COMMENT '工作区配置(JSON格式)',
    enabled_measurement BOOLEAN DEFAULT 0 COMMENT '是否启用测量',
    is_admin BOOLEAN DEFAULT 0 COMMENT '当前用户是否管理员',
    PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='工作区基本信息';

INSERT INTO workspaces (id, name) VALUES 
(60498179, '大型团队敏捷研发');





CREATE TABLE workitem_types (
    id VARCHAR(50) NOT NULL COMMENT '工作项类型ID',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '关联工作区ID',
    name VARCHAR(50) NOT NULL COMMENT '类型名称',
    english_name VARCHAR(50) COMMENT '英文名称',
    color VARCHAR(20) COMMENT '颜色代码',
    icon_url VARCHAR(255) COMMENT '图标URL',
    icon_small_url VARCHAR(255) COMMENT '小图标URL',
    status TINYINT COMMENT '状态(1启用/0禁用)',
    children_ids TEXT COMMENT '子类型ID列表(|分隔)',
    parent_ids TEXT COMMENT '父类型ID列表',
    workflow_id VARCHAR(50) COMMENT '关联工作流ID',
    entity_type VARCHAR(20) COMMENT '实体类型(story/task等)',
    PRIMARY KEY (id),
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
) ENGINE=InnoDB COMMENT='工作项类型配置';

INSERT INTO workitem_types (id, workspace_id, name, english_name, color, icon_url, status) VALUES
('1160498179001000016', 60498179, '需求', 'story', '#3582fb', 'https://www.tapd.cn//img/workitem_type/default_icon/@2/story.png', 3),
('1160498179001000017', 60498179, '特性', 'feature', '#64c195', 'https://viper.tapd.cn/icon/files/57051699/icon/1157051699001000038.png', 3),
('1160498179001000018', 60498179, '史诗故事', 'epic', '#de71f1', 'https://viper.tapd.cn/icon/files/57051699/icon/1157051699001000040.png', 3),
('1160498179001000020', 60498179, '任务', 'task', '#182b50', 'https://www.tapd.cn//img/workitem_type/default_icon/@2/default.png', 3);





CREATE TABLE iterations (
    id VARCHAR(50) NOT NULL COMMENT '迭代ID',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '关联工作区ID',
    name VARCHAR(100) NOT NULL COMMENT '迭代名称',
    parent_id VARCHAR(50) DEFAULT '0' COMMENT '父迭代ID',
    sort_order INT COMMENT '排序序号',
    is_parent BOOLEAN DEFAULT 0 COMMENT '是否为父节点',
    disabled BOOLEAN DEFAULT 0 COMMENT '是否禁用',
    PRIMARY KEY (id),
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
) ENGINE=InnoDB COMMENT='迭代信息';

INSERT INTO iterations (id, workspace_id, name, parent_id, sort_order) VALUES
('1160498179001000023', 60498179, '【产品】+开发环境+版本号（如：【途强在线】web v4.0.0）', '0', 100001100000),
('1160498179001000008', 60498179, '【买家应用】迭代2', '0', 100000800000),
('1160498179001000010', 60498179, '【卖家应用】迭代2', '0', 100001000000),
('1160498179001000009', 60498179, '【卖家应用】迭代1', '0', 100000900000);


CREATE TABLE stories (
    id VARCHAR(50) NOT NULL COMMENT '需求ID',
    workspace_id BIGINT UNSIGNED NOT NULL COMMENT '工作区ID',
    name VARCHAR(255) NOT NULL COMMENT '需求名称',
    priority ENUM('high', 'middle', 'low', 'nice to have') COMMENT '优先级',
    iteration_id VARCHAR(50) COMMENT '迭代ID',
    status VARCHAR(50) NOT NULL COMMENT '状态(planning/developing等)',
    owner VARCHAR(100) COMMENT '负责人',
    start_date DATE COMMENT '开始日期',
    due_date DATE COMMENT '截止日期',
    workitem_type_id VARCHAR(50) NOT NULL COMMENT '工作项类型ID',
    level TINYINT UNSIGNED COMMENT '层级深度(0开始)',
    path TEXT COMMENT '树形路径(:分隔)',
    parent_id VARCHAR(50) DEFAULT '0' COMMENT '父需求ID',
    ancestor_id VARCHAR(50) COMMENT '祖先需求ID',
    progress TINYINT UNSIGNED DEFAULT 0 COMMENT '进度百分比',
    short_id VARCHAR(20) COMMENT '短ID',
    is_delay BOOLEAN DEFAULT 0 COMMENT '是否延期',
    is_done BOOLEAN DEFAULT 0 COMMENT '是否完成状态',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id),
    FOREIGN KEY (iteration_id) REFERENCES iterations(id),
    FOREIGN KEY (workitem_type_id) REFERENCES workitem_types(id)
) ENGINE=InnoDB COMMENT='需求核心信息';

INSERT INTO stories (id, workspace_id, name, priority, iteration_id, status, due_date, workitem_type_id, level, path, parent_id, ancestor_id, short_id, is_delay) VALUES
('1160498179001000078', 60498179, '【示例】校园小拍卖家应用', 'middle', NULL, 'planning', '2025-07-12', '1160498179001000018', 0, '1160498179001000078', '0', '1160498179001000078', '1000078', 0),
('1160498179001000081', 60498179, '【示例】订单管理', 'high', NULL, 'developing', '2025-07-12', '1160498179001000017', 1, '1160498179001000078:1160498179001000081', '1160498179001000078', '1160498179001000078', '1000081', 0),
('1160498179001000089', 60498179, '【示例】订单处理', 'high', '1160498179001000010', 'developing', '2025-07-12', '1160498179001000016', 2, '1160498179001000078:1160498179001000081:1160498179001000089', '1160498179001000081', '1160498179001000078', '1000089', 0);



CREATE TABLE story_details (
    story_id VARCHAR(50) NOT NULL COMMENT '需求ID',
    attachment_count INT DEFAULT 0 COMMENT '附件数量',
    follower VARCHAR(255) COMMENT '关注者',
    effort_completed FLOAT DEFAULT 0 COMMENT '已完成工作量',
    effort_remaining FLOAT DEFAULT 0 COMMENT '剩余工作量',
    sub_story_count INT DEFAULT 0 COMMENT '子需求数量',
    sub_task_count INT DEFAULT 0 COMMENT '子任务数量',
    relate_story_count INT DEFAULT 0 COMMENT '关联需求数',
    tree_node_count INT DEFAULT 0 COMMENT '树节点总数',
    display BOOLEAN DEFAULT 1 COMMENT '是否显示',
    secret_workitem BOOLEAN DEFAULT 0 COMMENT '是否保密工作项',
    FOREIGN KEY (story_id) REFERENCES stories(id)
) ENGINE=InnoDB COMMENT='需求扩展信息';

INSERT INTO story_details (story_id, attachment_count, effort_completed, effort_remaining) VALUES
('1160498179001000078', 0, 0, 6),
('1160498179001000081', 0, 0, 2),
('1160498179001000089', 0, 0, 2);


CREATE TABLE workflow_statuses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    workspace_id BIGINT UNSIGNED NOT NULL,
    entity_type VARCHAR(20) NOT NULL COMMENT '实体类型(story/task)',
    status_key VARCHAR(50) NOT NULL COMMENT '状态键名',
    status_label VARCHAR(50) NOT NULL COMMENT '状态显示名',
    status_color VARCHAR(20) COMMENT '状态颜色',
    is_end_state BOOLEAN DEFAULT 0 COMMENT '是否为结束状态',
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
) ENGINE=InnoDB COMMENT='工作流状态配置';

INSERT INTO workflow_statuses (workspace_id, entity_type, status_key, status_label, status_color) VALUES
(60498179, 'story', 'planning', '规划中', NULL),
(60498179, 'story', 'developing', '实现中', NULL),
(60498179, 'story', 'resolved', '已实现', NULL),
(60498179, 'task', 'open', '未开始', NULL),
(60498179, 'task', 'progressing', '进行中', NULL);


CREATE TABLE view_configs (
    id VARCHAR(50) NOT NULL COMMENT '视图ID',
    workspace_id BIGINT UNSIGNED NOT NULL,
    title VARCHAR(100) NOT NULL COMMENT '视图名称',
    display_fields JSON NOT NULL COMMENT '显示字段列表(JSON数组)',
    perpage INT DEFAULT 50 COMMENT '每页数量',
    PRIMARY KEY (id),
    FOREIGN KEY (workspace_id) REFERENCES workspaces(id)
) ENGINE=InnoDB COMMENT='列表视图配置';

INSERT INTO view_configs (id, workspace_id, title, display_fields) VALUES 
('1000000000000000016', 60498179, '所有的', '["id", "name", "priority", "iteration_id", "status", "owner", "begin", "due"]');



























