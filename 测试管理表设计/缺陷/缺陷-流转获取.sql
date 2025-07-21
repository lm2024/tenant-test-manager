// Request URL
// https://www.tapd.cn/api/aggregation/bug_aggregation/get_bug_transition_info?workspace_id=60498179&bug_id=1160498179001000009&program_id=&has_rule_fields=undefined&check_rule_fields=undefined
// Request Method
// GET

// workspace_id=60498179&bug_id=1160498179001000009&program_id=&has_rule_fields=undefined&check_rule_fields=undefined




{
    "data": {
        "get_workflow_by_bug": {
            "data": {
                "current_bug": {
                    "Bug": {
                        "id": "1160498179001000009",
                        "project_id": "60498179",
                        "title": "\u3010\u793a\u4f8b\u3011\u7f3a\u96772",
                        "module": "",
                        "reporter": "TAPD",
                        "created": "2025-05-30 11:17:18",
                        "current_owner": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                        "priority": "medium",
                        "severity": "normal",
                        "version_report": "\u7248\u672c1",
                        "custom_field_one": "",
                        "effort": "",
                        "effort_completed": "0",
                        "exceed": "0",
                        "remain": "0"
                    },
                    "BugStoryRelation": []
                },
                "current_status": "in_progress",
                "my_all_transitions": [
                    {
                        "Name": "in_progress-in_progress",
                        "StepPrevious": "in_progress",
                        "StepNext": "in_progress",
                        "Appendfield": [
                            {
                                "DBModel": "Bugtrace",
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
                                "default_value_split": [],
                                "origin_data_value": "",
                                "field_name": "\u8bc4\u8bba",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "current_owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "no",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Bug",
                                        "Field": [
                                            "current_owner",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "default_value_split": [
                                    {
                                        "field": "current_owner",
                                        "value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;"
                                    }
                                ],
                                "origin_data_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "field_name": "\u5904\u7406\u4eba",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "de",
                                "FieldLabel": "\u5f00\u53d1\u4eba\u5458",
                                "Notnull": "no",
                                "Sort": "2",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Bug",
                                        "Field": [
                                            "de",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "default_value_split": [
                                    {
                                        "field": "de",
                                        "value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;"
                                    }
                                ],
                                "origin_data_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "field_name": "\u5f00\u53d1\u4eba\u5458",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u63a5\u53d7\/\u5904\u7406",
                        "from": [
                            "in_progress",
                            "\u5904\u7406\u4e2d",
                            "bugtrace"
                        ],
                        "to": [
                            "in_progress",
                            "\u63a5\u53d7\/\u5904\u7406"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0"
                    },
                    {
                        "Name": "in_progress-resolved",
                        "StepPrevious": "in_progress",
                        "StepNext": "resolved",
                        "Appendfield": [
                            {
                                "DBModel": "Bugtrace",
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
                                "default_value_split": [],
                                "origin_data_value": "",
                                "field_name": "\u8bc4\u8bba",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "current_owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "no",
                                "Sort": "1",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Bug",
                                        "Field": [
                                            "reporter",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "default_value_split": [
                                    {
                                        "field": "reporter",
                                        "value": "TAPD"
                                    }
                                ],
                                "origin_data_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "field_name": "\u5904\u7406\u4eba",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "te",
                                "FieldLabel": "\u6d4b\u8bd5\u4eba\u5458",
                                "Notnull": "no",
                                "Sort": "2",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Bug",
                                        "Field": [
                                            "reporter",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "default_value_split": [
                                    {
                                        "field": "reporter",
                                        "value": "TAPD"
                                    }
                                ],
                                "origin_data_value": "",
                                "field_name": "\u6d4b\u8bd5\u4eba\u5458",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u5df2\u89e3\u51b3",
                        "from": [
                            "in_progress",
                            "\u5904\u7406\u4e2d",
                            "bugtrace"
                        ],
                        "to": [
                            "resolved",
                            "\u5df2\u89e3\u51b3"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0"
                    },
                    {
                        "Name": "in_progress-rejected",
                        "StepPrevious": "in_progress",
                        "StepNext": "rejected",
                        "Appendfield": [
                            {
                                "DBModel": "Bugtrace",
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
                                "default_value_split": [],
                                "origin_data_value": "",
                                "field_name": "\u8bc4\u8bba",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "resolution",
                                "FieldLabel": "\u89e3\u51b3\u65b9\u6cd5",
                                "Notnull": "no",
                                "Sort": "1",
                                "LinkageRules": [],
                                "DefaultValue": [
                                    {
                                        "Type": "default_value",
                                        "Value": ""
                                    }
                                ],
                                "default_value": "",
                                "default_value_split": [],
                                "origin_data_value": "",
                                "field_name": "\u89e3\u51b3\u65b9\u6cd5",
                                "linkage_rules": []
                            },
                            {
                                "DBModel": "Bug",
                                "FieldName": "current_owner",
                                "FieldLabel": "\u5904\u7406\u4eba",
                                "Notnull": "no",
                                "Sort": "2",
                                "LinkageRules": [],
                                "Type": "record_value",
                                "DefaultValue": [
                                    {
                                        "Type": "record_value",
                                        "DBModel": "Bug",
                                        "Field": [
                                            "reporter",
                                            ""
                                        ]
                                    }
                                ],
                                "default_value": "",
                                "default_value_split": [
                                    {
                                        "field": "reporter",
                                        "value": "TAPD"
                                    }
                                ],
                                "origin_data_value": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
                                "field_name": "\u5904\u7406\u4eba",
                                "linkage_rules": []
                            }
                        ],
                        "to_name": "\u5df2\u62d2\u7edd",
                        "from": [
                            "in_progress",
                            "\u5904\u7406\u4e2d",
                            "bugtrace"
                        ],
                        "to": [
                            "rejected",
                            "\u5df2\u62d2\u7edd"
                        ],
                        "has_permission": "1",
                        "permission_info": [],
                        "condition_hidden": "0"
                    }
                ],
                "remark_info": {
                    "new": "",
                    "in_progress": "",
                    "resolved": "",
                    "verified": "",
                    "reopened": "",
                    "rejected": "",
                    "closed": ""
                },
                "status_alias": {
                    "new": "\u65b0",
                    "in_progress": "\u63a5\u53d7\/\u5904\u7406",
                    "resolved": "\u5df2\u89e3\u51b3",
                    "verified": "\u5df2\u9a8c\u8bc1",
                    "reopened": "\u91cd\u65b0\u6253\u5f00",
                    "rejected": "\u5df2\u62d2\u7edd",
                    "closed": "\u5df2\u5173\u95ed"
                },
                "end_status": {
                    "closed": "closed"
                },
                "show_fields": {
                    "version_report": "\u53d1\u73b0\u7248\u672c",
                    "module": "\u6a21\u5757",
                    "priority": "\u4f18\u5148\u7ea7",
                    "severity": "\u4e25\u91cd\u7a0b\u5ea6",
                    "current_owner": "\u5904\u7406\u4eba",
                    "reporter": "\u521b\u5efa\u4eba",
                    "created": "\u521b\u5efa\u65f6\u95f4",
                    "id": "ID",
                    "custom_field_one": "custom_field_one"
                },
                "bug_fields_rich": {
                    "id": {
                        "name": "id",
                        "label": "ID",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "secret_root_id": {
                        "name": "secret_root_id",
                        "label": "Secret Root Id",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "sid": {
                        "name": "sid",
                        "label": "Sid",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "project_id": {
                        "name": "project_id",
                        "label": "\u6240\u5c5e\u9879\u76ee",
                        "options": {
                            "60498179": "\u5927\u578b\u56e2\u961f\u654f\u6377\u7814\u53d1"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "parent_id": {
                        "name": "parent_id",
                        "label": "\u7236\u9700\u6c42",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "title": {
                        "name": "title",
                        "label": "\u6807\u9898",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "description": {
                        "name": "description",
                        "label": "\u8be6\u7ec6\u63cf\u8ff0",
                        "options": [],
                        "pure_options": [],
                        "html_type": "rich_edit",
                        "setting_url": "",
                        "memo": ""
                    },
                    "markdown_description": {
                        "name": "markdown_description",
                        "label": "Markdown Description",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "description_type": {
                        "name": "description_type",
                        "label": "Description Type",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "module": {
                        "name": "module",
                        "label": "\u6a21\u5757",
                        "options": {
                            "\u6a21\u57571": "\u6a21\u57571"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/modules\/index",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "milestone": {
                        "name": "milestone",
                        "label": "Milestone",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "reporter": {
                        "name": "reporter",
                        "label": "\u521b\u5efa\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "deadline": {
                        "name": "deadline",
                        "label": "\u89e3\u51b3\u671f\u9650",
                        "options": [],
                        "pure_options": [],
                        "html_type": "dateinput",
                        "setting_url": "",
                        "memo": ""
                    },
                    "created": {
                        "name": "created",
                        "label": "\u521b\u5efa\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "resolved": {
                        "name": "resolved",
                        "label": "\u89e3\u51b3\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "closed": {
                        "name": "closed",
                        "label": "\u5173\u95ed\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "modified": {
                        "name": "modified",
                        "label": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "lastmodify": {
                        "name": "lastmodify",
                        "label": "\u6700\u540e\u4fee\u6539\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "auditer": {
                        "name": "auditer",
                        "label": "\u5ba1\u6838\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "de": {
                        "name": "de",
                        "label": "\u5f00\u53d1\u4eba\u5458",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "te": {
                        "name": "te",
                        "label": "\u6d4b\u8bd5\u4eba\u5458",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "confirmer": {
                        "name": "confirmer",
                        "label": "\u9a8c\u8bc1\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "current_owner": {
                        "name": "current_owner",
                        "label": "\u5904\u7406\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "participator": {
                        "name": "participator",
                        "label": "\u53c2\u4e0e\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "closer": {
                        "name": "closer",
                        "label": "\u5173\u95ed\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "user_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "status": {
                        "name": "status",
                        "label": "\u72b6\u6001",
                        "options": {
                            "new": "\u65b0",
                            "in_progress": "\u63a5\u53d7\/\u5904\u7406",
                            "resolved": "\u5df2\u89e3\u51b3",
                            "verified": "\u5df2\u9a8c\u8bc1",
                            "reopened": "\u91cd\u65b0\u6253\u5f00",
                            "rejected": "\u5df2\u62d2\u7edd",
                            "closed": "\u5df2\u5173\u95ed"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "resolution": {
                        "name": "resolution",
                        "label": "\u89e3\u51b3\u65b9\u6cd5",
                        "options": {
                            "ignore": "\u65e0\u9700\u89e3\u51b3",
                            "fixed": "\u5df2\u4fee\u6539",
                            "fix later": "\u5ef6\u671f\u89e3\u51b3",
                            "failed to recur": "\u65e0\u6cd5\u91cd\u73b0",
                            "external reason": "\u5916\u90e8\u539f\u56e0",
                            "duplicated": "\u91cd\u590d",
                            "intentional design": "\u8bbe\u8ba1\u5982\u6b64",
                            "unclear description ": "\u95ee\u9898\u63cf\u8ff0\u4e0d\u51c6\u786e",
                            "feature change": "\u9700\u6c42\u53d8\u66f4",
                            "transferred to story": "\u5df2\u8f6c\u9700\u6c42",
                            "hold": "\u6302\u8d77"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "priority": {
                        "name": "priority",
                        "label": "\u4f18\u5148\u7ea7",
                        "color_options": [
                            {
                                "value": "urgent",
                                "label": "\u7d27\u6025",
                                "color": "#FF6770"
                            },
                            {
                                "value": "high",
                                "label": "\u9ad8",
                                "color": "#FF6770"
                            },
                            {
                                "value": "medium",
                                "label": "\u4e2d",
                                "color": "#28AB80"
                            },
                            {
                                "value": "low",
                                "label": "\u4f4e",
                                "color": "#7C8597"
                            },
                            {
                                "value": "insignificant",
                                "label": "\u65e0\u5173\u7d27\u8981",
                                "color": "#7C8597"
                            }
                        ],
                        "options": {
                            "urgent": "\u7d27\u6025",
                            "high": "\u9ad8",
                            "medium": "\u4e2d",
                            "low": "\u4f4e",
                            "insignificant": "\u65e0\u5173\u7d27\u8981"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "enable_color": "0",
                        "memo": ""
                    },
                    "severity": {
                        "name": "severity",
                        "label": "\u4e25\u91cd\u7a0b\u5ea6",
                        "options": {
                            "fatal": "\u81f4\u547d",
                            "serious": "\u4e25\u91cd",
                            "normal": "\u4e00\u822c",
                            "prompt": "\u63d0\u793a",
                            "advice": "\u5efa\u8bae"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "platform": {
                        "name": "platform",
                        "label": "\u8f6f\u4ef6\u5e73\u53f0",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "os": {
                        "name": "os",
                        "label": "\u64cd\u4f5c\u7cfb\u7edf",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "testmode": {
                        "name": "testmode",
                        "label": "\u6d4b\u8bd5\u65b9\u5f0f",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "testtype": {
                        "name": "testtype",
                        "label": "\u6d4b\u8bd5\u7c7b\u578b",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "testphase": {
                        "name": "testphase",
                        "label": "\u6d4b\u8bd5\u9636\u6bb5",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "source": {
                        "name": "source",
                        "label": "\u7f3a\u9677\u6839\u6e90",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "frequency": {
                        "name": "frequency",
                        "label": "\u91cd\u73b0\u89c4\u5f8b",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "cc": {
                        "name": "cc",
                        "label": "\u6284\u9001\u4eba",
                        "options": [],
                        "pure_options": [],
                        "html_type": "mix_chooser",
                        "setting_url": "",
                        "memo": ""
                    },
                    "estimate": {
                        "name": "estimate",
                        "label": "\u9884\u8ba1\u89e3\u51b3\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "int",
                        "setting_url": "",
                        "memo": "",
                        "editabled_type": "smallint"
                    },
                    "flows": {
                        "name": "flows",
                        "label": "Flows",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "version_report": {
                        "name": "version_report",
                        "label": "\u53d1\u73b0\u7248\u672c",
                        "options": {
                            "\u7248\u672c1": "\u7248\u672c1"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/versions",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "version_test": {
                        "name": "version_test",
                        "label": "\u9a8c\u8bc1\u7248\u672c",
                        "options": {
                            "\u7248\u672c1": "\u7248\u672c1"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/versions",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "version_fix": {
                        "name": "version_fix",
                        "label": "\u5408\u5165\u7248\u672c",
                        "options": {
                            "\u7248\u672c1": "\u7248\u672c1"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/versions",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "version_close": {
                        "name": "version_close",
                        "label": "\u5173\u95ed\u7248\u672c",
                        "options": {
                            "\u7248\u672c1": "\u7248\u672c1"
                        },
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/versions",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "delayed": {
                        "name": "delayed",
                        "label": "Delayed",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "custom_field_one": {
                        "name": "custom_field_one",
                        "label": "abc",
                        "options": [],
                        "pure_options": [],
                        "html_type": "text",
                        "setting_url": "",
                        "memo": "abc"
                    },
                    "custom_field_two": {
                        "name": "custom_field_two",
                        "label": "custom_field_two",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "custom_field_three": {
                        "name": "custom_field_three",
                        "label": "custom_field_three",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "custom_field_four": {
                        "name": "custom_field_four",
                        "label": "custom_field_four",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "custom_field_five": {
                        "name": "custom_field_five",
                        "label": "custom_field_five",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "regression_number": {
                        "name": "regression_number",
                        "label": "\u7f3a\u9677\u56de\u5f52\u6b21\u6570",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "issue_id": {
                        "name": "issue_id",
                        "label": "Issue Id",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "support_id": {
                        "name": "support_id",
                        "label": "Support Id",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "support_forum_id": {
                        "name": "support_forum_id",
                        "label": "Support Forum Id",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "created_from": {
                        "name": "created_from",
                        "label": "Created From",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "baseline_find": {
                        "name": "baseline_find",
                        "label": "\u53d1\u73b0\u57fa\u7ebf",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/baselines",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "baseline_join": {
                        "name": "baseline_join",
                        "label": "\u5408\u5165\u57fa\u7ebf",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/baselines",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "baseline_close": {
                        "name": "baseline_close",
                        "label": "\u5173\u95ed\u57fa\u7ebf",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/baselines",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "ticket_id": {
                        "name": "ticket_id",
                        "label": "Ticket Id",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "story_id": {
                        "name": "story_id",
                        "label": "\u9700\u6c42",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "baseline_test": {
                        "name": "baseline_test",
                        "label": "\u9a8c\u8bc1\u57fa\u7ebf",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/baselines",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "originphase": {
                        "name": "originphase",
                        "label": "\u53d1\u73b0\u9636\u6bb5",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "sourcephase": {
                        "name": "sourcephase",
                        "label": "\u5f15\u5165\u9636\u6bb5",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "bugtype": {
                        "name": "bugtype",
                        "label": "\u7f3a\u9677\u7c7b\u578b",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "feature": {
                        "name": "feature",
                        "label": "\u7279\u6027",
                        "options": [],
                        "pure_options": [],
                        "html_type": "select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/features\/index\/-1",
                        "color_options": [],
                        "enable_color": "0",
                        "memo": ""
                    },
                    "in_progress_time": {
                        "name": "in_progress_time",
                        "label": "\u63a5\u53d7\u5904\u7406\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "verify_time": {
                        "name": "verify_time",
                        "label": "\u9a8c\u8bc1\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "reject_time": {
                        "name": "reject_time",
                        "label": "\u62d2\u7edd\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "reopen_time": {
                        "name": "reopen_time",
                        "label": "\u91cd\u65b0\u6253\u5f00\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "audit_time": {
                        "name": "audit_time",
                        "label": "Audit Time",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "suspend_time": {
                        "name": "suspend_time",
                        "label": "Suspend Time",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "assigned_time": {
                        "name": "assigned_time",
                        "label": "\u5206\u914d\u65f6\u95f4",
                        "options": [],
                        "pure_options": [],
                        "html_type": "datetime",
                        "setting_url": "",
                        "memo": ""
                    },
                    "iteration_id": {
                        "name": "iteration_id",
                        "label": "\u8fed\u4ee3",
                        "options": [
                            {
                                "id": "0",
                                "label": "-\u7a7a-",
                                "parent_id": "0",
                                "sort": "0",
                                "icon": "",
                                "origin_sort": "0",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId"
                            },
                            {
                                "id": "1160498179001000023",
                                "label": "\u3010\u4ea7\u54c1\u3011+\u5f00\u53d1\u73af\u5883+\u7248\u672c\u53f7\uff08\u5982\uff1a\u3010\u9014\u5f3a\u5728\u7ebf\u3011Web V4.0.0\uff09",
                                "parent_id": "0",
                                "sort": "100001100000",
                                "icon": "",
                                "origin_sort": "0",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000008",
                                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                                "parent_id": "0",
                                "sort": "100000800000",
                                "icon": "",
                                "origin_sort": "1",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000010",
                                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                                "parent_id": "0",
                                "sort": "100001000000",
                                "icon": "",
                                "origin_sort": "2",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000007",
                                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                                "parent_id": "0",
                                "sort": "100000700000",
                                "icon": "",
                                "origin_sort": "3",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId",
                                "is_parent": "false"
                            },
                            {
                                "id": "1160498179001000009",
                                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                                "parent_id": "0",
                                "sort": "100000900000",
                                "icon": "",
                                "origin_sort": "4",
                                "disabled": "",
                                "disabled_tips": "",
                                "fieldName": "iterationId",
                                "is_parent": "false"
                            }
                        ],
                        "pure_options": [
                            {
                                "parent_id": "0",
                                "workspace_id": "60498179",
                                "sort": "100001100000",
                                "workitem_type_id": "1160498179001000021",
                                "plan_app_id": "0",
                                "value": "1160498179001000023",
                                "label": "\u3010\u4ea7\u54c1\u3011+\u5f00\u53d1\u73af\u5883+\u7248\u672c\u53f7\uff08\u5982\uff1a\u3010\u9014\u5f3a\u5728\u7ebf\u3011Web V4.0.0\uff09",
                                "panel": "0"
                            },
                            {
                                "parent_id": "0",
                                "workspace_id": "60498179",
                                "sort": "100000800000",
                                "workitem_type_id": "1160498179001000021",
                                "plan_app_id": "0",
                                "value": "1160498179001000008",
                                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                                "panel": "0"
                            },
                            {
                                "parent_id": "0",
                                "workspace_id": "60498179",
                                "sort": "100001000000",
                                "workitem_type_id": "1160498179001000021",
                                "plan_app_id": "0",
                                "value": "1160498179001000010",
                                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee32",
                                "panel": "0"
                            },
                            {
                                "parent_id": "0",
                                "workspace_id": "60498179",
                                "sort": "100000700000",
                                "workitem_type_id": "1160498179001000021",
                                "plan_app_id": "0",
                                "value": "1160498179001000007",
                                "label": "\u3010\u4e70\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                                "panel": "0"
                            },
                            {
                                "parent_id": "0",
                                "workspace_id": "60498179",
                                "sort": "100000900000",
                                "workitem_type_id": "1160498179001000021",
                                "plan_app_id": "0",
                                "value": "1160498179001000009",
                                "label": "\u3010\u5356\u5bb6\u5e94\u7528\u3011\u8fed\u4ee31",
                                "panel": "0"
                            }
                        ],
                        "html_type": "tree_select",
                        "setting_url": "https:\/\/www.tapd.cn\/60498179\/prong\/iterations\/edit",
                        "color_options": [],
                        "enable_color": "0",
                        "lock_info": [],
                        "memo": "",
                        "locked_iteration": []
                    },
                    "custom_field_6": {
                        "name": "custom_field_6",
                        "label": "custom_field_6",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    },
                    "custom_field_7": {
                        "name": "custom_field_7",
                        "label": "custom_field_7",
                        "options": [],
                        "pure_options": [],
                        "html_type": "input",
                        "setting_url": "",
                        "memo": ""
                    }
                },
                "branch": [],
                "bug_end_steps": {
                    "60498179": [
                        "closed"
                    ]
                },
                "bug_first_steps": {
                    "60498179": "new"
                },
                "workflow_id": "1160498179001000017",
                "story_not_relation_task_and_remain_is_not_zero": "",
                "enabled_measurement": "1",
                "enable_multi_linkage_fields": "0",
                "metrology": "day",
                "baseline_option_map": [],
                "feature_option_map": [],
                "effort_disabled_reason_type": "",
                "fields_disabled_to_edit": [],
                "fields_disabled_tips_map": [],
                "sw_item_edit_parent_iteration": [],
                "plan_apps": []
            },
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749718575"
        },
        "get_related_branch": {
            "data": [],
            "meta": {
                "code": "0",
                "message": "success"
            },
            "timestamp": "1749718575"
        }
    },
    "meta": {
        "code": 0,
        "message": "success"
    },
    "timestamp": 1749718575
}








-- 创建缺陷主表
CREATE TABLE bug_main (
    id VARCHAR(30) PRIMARY KEY COMMENT '缺陷ID',
    project_id VARCHAR(20) NOT NULL COMMENT '项目ID',
    title VARCHAR(255) NOT NULL COMMENT '标题',
    module VARCHAR(100) DEFAULT NULL COMMENT '模块',
    reporter VARCHAR(50) NOT NULL COMMENT '创建人',
    created DATETIME NOT NULL COMMENT '创建时间',
    current_owner VARCHAR(100) DEFAULT NULL COMMENT '处理人',
    priority ENUM('urgent','high','medium','low','insignificant') DEFAULT NULL COMMENT '优先级(紧急/高/中/低/无关紧要)',
    severity ENUM('fatal','serious','normal','prompt','advice') DEFAULT NULL COMMENT '严重程度(致命/严重/一般/提示/建议)',
    version_report VARCHAR(50) DEFAULT NULL COMMENT '发现版本',
    custom_field_one VARCHAR(50) DEFAULT NULL COMMENT '自定义字段1',
    status ENUM('new','in_progress','resolved','verified','reopened','rejected','closed') DEFAULT 'new' COMMENT '当前状态',
    current_status VARCHAR(20) DEFAULT NULL COMMENT '当前状态别名',
    iteration_id VARCHAR(30) DEFAULT NULL COMMENT '迭代ID',
    INDEX idx_project (project_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷主信息表';

-- 创建缺陷状态转换表
CREATE TABLE bug_transition (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bug_id VARCHAR(30) NOT NULL COMMENT '关联缺陷ID',
    name VARCHAR(50) NOT NULL COMMENT '转换名称',
    step_previous VARCHAR(20) NOT NULL COMMENT '起始状态',
    step_next VARCHAR(20) NOT NULL COMMENT '目标状态',
    to_name VARCHAR(50) NOT NULL COMMENT '目标状态名称',
    has_permission TINYINT(1) DEFAULT 1 COMMENT '是否有权限(1-是,0-否)',
    condition_hidden TINYINT(1) DEFAULT 0 COMMENT '条件是否隐藏(1-是,0-否)',
    FOREIGN KEY (bug_id) REFERENCES bug_main(id) ON DELETE CASCADE,
    INDEX idx_transition (step_previous, step_next)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷状态转换表';

-- 创建转换附加字段表
CREATE TABLE transition_field (
    id INT AUTO_INCREMENT PRIMARY KEY,
    transition_id INT NOT NULL COMMENT '关联转换ID',
    db_model VARCHAR(20) NOT NULL COMMENT '所属模型(Bug/Bugtrace)',
    field_name VARCHAR(50) NOT NULL COMMENT '字段名称',
    field_label VARCHAR(50) DEFAULT NULL COMMENT '字段标签',
    notnull ENUM('yes','no') DEFAULT 'no' COMMENT '是否必填',
    sort TINYINT DEFAULT 0 COMMENT '排序',
    origin_data_value TEXT COMMENT '原始数据值',
    FOREIGN KEY (transition_id) REFERENCES bug_transition(id) ON DELETE CASCADE,
    INDEX idx_field (field_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='状态转换附加字段表';

-- 创建字段默认值表
CREATE TABLE field_default_value (
    id INT AUTO_INCREMENT PRIMARY KEY,
    field_id INT NOT NULL COMMENT '关联字段ID',
    type ENUM('default_value','record_value') NOT NULL COMMENT '值类型',
    value VARCHAR(255) DEFAULT NULL COMMENT '默认值',
    db_model VARCHAR(20) DEFAULT NULL COMMENT '关联模型(当type=record_value时)',
    field VARCHAR(50) DEFAULT NULL COMMENT '关联字段(当type=record_value时)',
    FOREIGN KEY (field_id) REFERENCES transition_field(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段默认值配置表';

-- 创建字段元数据表
CREATE TABLE bug_field_rich (
    name VARCHAR(50) PRIMARY KEY COMMENT '字段名称',
    label VARCHAR(50) NOT NULL COMMENT '字段标签',
    html_type VARCHAR(20) NOT NULL COMMENT 'HTML控件类型(input/select/dateinput等)',
    setting_url VARCHAR(255) DEFAULT NULL COMMENT '配置页面URL',
    memo TEXT COMMENT '字段说明',
    enable_color TINYINT(1) DEFAULT 0 COMMENT '是否启用颜色(1-是,0-否)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段元数据信息表';

-- 创建字段选项表
CREATE TABLE field_options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    field_name VARCHAR(50) NOT NULL COMMENT '关联字段名',
    option_value VARCHAR(50) NOT NULL COMMENT '选项值',
    option_label VARCHAR(100) NOT NULL COMMENT '选项标签',
    FOREIGN KEY (field_name) REFERENCES bug_field_rich(name) ON DELETE CASCADE,
    UNIQUE KEY uniq_field_option (field_name, option_value)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段选项值表';

-- 创建颜色选项表
CREATE TABLE field_color_options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    field_name VARCHAR(50) NOT NULL COMMENT '关联字段名',
    value VARCHAR(50) NOT NULL COMMENT '选项值',
    label VARCHAR(50) NOT NULL COMMENT '选项标签',
    color VARCHAR(20) NOT NULL COMMENT '颜色代码',
    FOREIGN KEY (field_name) REFERENCES bug_field_rich(name) ON DELETE CASCADE,
    UNIQUE KEY uniq_field_color (field_name, value)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='带颜色的字段选项表';











-- 插入主缺陷数据
INSERT INTO bug_main (
    id, project_id, title, module, reporter, created, current_owner, 
    priority, severity, version_report, status, current_status, iteration_id
) VALUES (
    '1160498179001000009', 
    '60498179', 
    '【示例】缺陷2', 
    '', 
    'TAPD', 
    '2025-05-30 11:17:18', 
    '未命名小程序;', 
    'medium', 
    'normal', 
    '版本1', 
    'in_progress', 
    '处理中',
    NULL
);

-- 插入状态转换数据
INSERT INTO bug_transition (
    bug_id, name, step_previous, step_next, to_name, has_permission, condition_hidden
) VALUES 
('1160498179001000009', 'in_progress-in_progress', 'in_progress', 'in_progress', '接受/处理', 1, 0),
('1160498179001000009', 'in_progress-resolved', 'in_progress', 'resolved', '已解决', 1, 0),
('1160498179001000009', 'in_progress-rejected', 'in_progress', 'rejected', '已拒绝', 1, 0);

-- 插入转换附加字段数据
INSERT INTO transition_field (
    transition_id, db_model, field_name, field_label, notnull, sort, origin_data_value
) VALUES 
(1, 'Bugtrace', 'remarks', '评论', 'no', 0, ''),
(1, 'Bug', 'current_owner', '处理人', 'no', 1, '未命名小程序;'),
(1, 'Bug', 'de', '开发人员', 'no', 2, '未命名小程序;'),
(2, 'Bugtrace', 'remarks', '评论', 'no', 0, ''),
(2, 'Bug', 'current_owner', '处理人', 'no', 1, '未命名小程序;'),
(2, 'Bug', 'te', '测试人员', 'no', 2, '');

-- 插入字段默认值
INSERT INTO field_default_value (
    field_id, type, value, db_model, field
) VALUES 
(2, 'record_value', NULL, 'Bug', 'current_owner'),
(3, 'record_value', NULL, 'Bug', 'de'),
(5, 'record_value', NULL, 'Bug', 'reporter'),
(6, 'record_value', NULL, 'Bug', 'reporter');

-- 插入字段元数据
INSERT INTO bug_field_rich (name, label, html_type, setting_url, memo, enable_color) VALUES
('priority', '优先级', 'select', '', '优先级字段', 1),
('severity', '严重程度', 'select', '', '严重程度字段', 0),
('version_report', '发现版本', 'select', 'https://www.tapd.cn/60498179/versions', '发现版本字段', 0),
('module', '模块', 'select', 'https://www.tapd.cn/60498179/modules/index', '模块字段', 0),
('current_owner', '处理人', 'user_chooser', '', '当前处理人字段', 0);

-- 插入字段选项
INSERT INTO field_options (field_name, option_value, option_label) VALUES
('priority', 'urgent', '紧急'),
('priority', 'high', '高'),
('priority', 'medium', '中'),
('priority', 'low', '低'),
('priority', 'insignificant', '无关紧要'),
('severity', 'fatal', '致命'),
('severity', 'serious', '严重'),
('severity', 'normal', '一般'),
('severity', 'prompt', '提示'),
('severity', 'advice', '建议'),
('version_report', '版本1', '版本1');

-- 插入颜色选项
INSERT INTO field_color_options (field_name, value, label, color) VALUES
('priority', 'urgent', '紧急', '#FF6770'),
('priority', 'high', '高', '#FF6770'),
('priority', 'medium', '中', '#28AB80'),
('priority', 'low', '低', '#7C8597'),
('priority', 'insignificant', '无关紧要', '#7C8597');










