
https://www.tapd.cn/api/new_filter/new_filter/get_fields?needRepeatInterceptors=false
请求方法
POST

needRepeatInterceptors=false


{
    "data": {
        "fields": {
            "story": {
                "id": {
                    "name": "id",
                    "label": "ID",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "name": {
                    "name": "name",
                    "label": "\u6807\u9898",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "status": {
                    "name": "status",
                    "label": "\u72b6\u6001",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "description": {
                    "name": "description",
                    "label": "\u8be6\u7ec6\u63cf\u8ff0",
                    "html_type": "rich_edit",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "workitem_type_id": {
                    "name": "workitem_type_id",
                    "label": "\u9700\u6c42\u7c7b\u522b",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "children_id": {
                    "name": "children_id",
                    "label": "\u5b50\u9700\u6c42",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": "",
                    "field_sub_config": "story"
                },
                "parent_id": {
                    "name": "parent_id",
                    "label": "\u7236\u9700\u6c42",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": "",
                    "field_sub_config": "story"
                },
                "iteration_id": {
                    "name": "iteration_id",
                    "label": "\u8fed\u4ee3",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": "",
                    "lazyModel": "iterationOptions",
                    "switchShowLabel": "\u67e5\u770b\u5df2\u5173\u95ed\u7684\u8fed\u4ee3",
                    "switchHideLabel": "\u9690\u85cf\u5df2\u5173\u95ed\u7684\u8fed\u4ee3"
                },
                "module": {
                    "name": "module",
                    "label": "\u6a21\u5757",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "priority": {
                    "name": "priority",
                    "label": "\u4f18\u5148\u7ea7",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "owner": {
                    "name": "owner",
                    "label": "\u5904\u7406\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "developer": {
                    "name": "developer",
                    "label": "\u5f00\u53d1\u4eba\u5458",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "begin": {
                    "name": "begin",
                    "label": "\u9884\u8ba1\u5f00\u59cb",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "due": {
                    "name": "due",
                    "label": "\u9884\u8ba1\u7ed3\u675f",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "business_value": {
                    "name": "business_value",
                    "label": "\u4e1a\u52a1\u4ef7\u503c",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "size": {
                    "name": "size",
                    "label": "\u89c4\u6a21",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "effort": {
                    "name": "effort",
                    "label": "\u9884\u4f30\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "cc": {
                    "name": "cc",
                    "label": "\u6284\u9001\u4eba",
                    "html_type": "mix_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "creator": {
                    "name": "creator",
                    "label": "\u521b\u5efa\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "created": {
                    "name": "created",
                    "label": "\u521b\u5efa\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "modified": {
                    "name": "modified",
                    "label": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "participator": {
                    "name": "participator",
                    "label": "\u53c2\u4e0e\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "effort_completed": {
                    "name": "effort_completed",
                    "label": "\u5b8c\u6210\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "exceed": {
                    "name": "exceed",
                    "label": "\u8d85\u51fa\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "remain": {
                    "name": "remain",
                    "label": "\u5269\u4f59\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "progress": {
                    "name": "progress",
                    "label": "\u8fdb\u5ea6",
                    "html_type": "percent",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [],
                    "hidden": ""
                },
                "completed": {
                    "name": "completed",
                    "label": "\u5b8c\u6210\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "test_focus": {
                    "name": "test_focus",
                    "label": "\u6d4b\u8bd5\u91cd\u70b9",
                    "html_type": "textarea",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f(\u2260)",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "category_id": {
                    "name": "category_id",
                    "label": "\u5206\u7c7b",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "version": {
                    "name": "version",
                    "label": "\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "label": {
                    "name": "label",
                    "label": "\u6807\u7b7e",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "has_attachment": {
                    "name": "has_attachment",
                    "label": "\u9644\u4ef6",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "level": {
                    "name": "level",
                    "label": "\u5c42\u7ea7",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42",
                            "value": "leaf",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u72ec\u7acb\u9700\u6c42",
                            "value": "single",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u72ec\u7acb\u6216\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42",
                            "value": "single_leaf",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b2c",
                            "label2": "\u5c42"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "single",
                            "label": "\u72ec\u7acb\u9700\u6c42"
                        },
                        {
                            "value": "leaf",
                            "label": "\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42"
                        },
                        {
                            "value": "single_leaf",
                            "label": "\u72ec\u7acb\u6216\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42"
                        }
                    ],
                    "hidden": ""
                },
                "comment": {
                    "name": "comment",
                    "label": "\u8bc4\u8bba",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "commentator": {
                    "name": "commentator",
                    "label": "\u8bc4\u8bba\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "attachment_uploader": {
                    "name": "attachment_uploader",
                    "label": "\u9644\u4ef6\u4e0a\u4f20\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "EntityBugRelation_relative_id": {
                    "name": "EntityBugRelation_relative_id",
                    "label": "\u5173\u8054\u7f3a\u9677",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": "",
                    "field_sub_config": "bug"
                },
                "status_remain": {
                    "name": "status_remain",
                    "label": "\u5f53\u524d\u72b6\u6001\u505c\u7559\u65f6\u957f",
                    "html_type": "number-time",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number-time",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number-time",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number-time",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "",
                    "is_system": "1",
                    "category": "foundation",
                    "options": []
                },
                "\u9700\u6c42\u6765\u6e90": {
                    "html_type": "select",
                    "name": "\u9700\u6c42\u6765\u6e90",
                    "custom_field": "custom_field_one",
                    "label": "\u9700\u6c42\u6765\u6e90",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "0",
                    "category": "custom_field",
                    "options": []
                },
                "relationship:iteration_id": {
                    "name": "relationship:iteration_id",
                    "label": "\u8fed\u4ee3\u5b57\u6bb5",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "field_sub_config": "iteration"
                },
                "predecessor_count": {
                    "category": "relationships",
                    "field_sub_config": "story",
                    "name": "predecessor_count",
                    "label": "\u524d\u7f6e\u5bf9\u8c61",
                    "default_operator": "like",
                    "is_system": "1",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ]
                },
                "successor_count": {
                    "category": "relationships",
                    "field_sub_config": "story",
                    "name": "successor_count",
                    "label": "\u540e\u7f6e\u5bf9\u8c61",
                    "default_operator": "like",
                    "is_system": "1",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ]
                },
                "ancestor_id": {
                    "name": "ancestor_id",
                    "label": "\u9700\u6c42\u6811",
                    "category": "foundation",
                    "default_operator": "equal",
                    "is_system": "1",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u8bf7\u8f93\u5165\u9700\u6c42ID"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u8bf7\u8f93\u5165\u9700\u6c42ID"
                            ],
                            "default_value": []
                        }
                    ],
                    "options": []
                },
                "secret_root_id": {
                    "name": "secret_root_id",
                    "label": "\u4fdd\u5bc6\u8303\u56f4",
                    "category": "foundation",
                    "default_operator": "in",
                    "is_system": "1",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "options": [
                        {
                            "value": "= 0",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "\u003E 0",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ]
                }
            }
        },
        "fields_category": {
            "story": {
                "foundation": {
                    "name": "foundation",
                    "options": {
                        "id": "ID",
                        "name": "\u6807\u9898",
                        "status": "\u72b6\u6001",
                        "description": "\u8be6\u7ec6\u63cf\u8ff0",
                        "workitem_type_id": "\u9700\u6c42\u7c7b\u522b",
                        "iteration_id": "\u8fed\u4ee3",
                        "module": "\u6a21\u5757",
                        "priority": "\u4f18\u5148\u7ea7",
                        "business_value": "\u4e1a\u52a1\u4ef7\u503c",
                        "size": "\u89c4\u6a21",
                        "test_focus": "\u6d4b\u8bd5\u91cd\u70b9",
                        "category_id": "\u5206\u7c7b",
                        "version": "\u7248\u672c",
                        "label": "\u6807\u7b7e",
                        "has_attachment": "\u9644\u4ef6",
                        "level": "\u5c42\u7ea7",
                        "comment": "\u8bc4\u8bba",
                        "ancestor_id": "\u9700\u6c42\u6811",
                        "secret_root_id": "\u4fdd\u5bc6\u8303\u56f4"
                    },
                    "label": "\u57fa\u7840\u5b57\u6bb5",
                    "advanced_fields": {
                        "status_remain": "\u5f53\u524d\u72b6\u6001\u505c\u7559\u65f6\u957f",
                        "workspace_id": "\u6240\u5c5e\u9879\u76ee"
                    }
                },
                "relationships": {
                    "name": "relationships",
                    "options": {
                        "children_id": "\u5b50\u9700\u6c42",
                        "parent_id": "\u7236\u9700\u6c42",
                        "EntityBugRelation_relative_id": "\u5173\u8054\u7f3a\u9677",
                        "relationship:iteration_id": "\u8fed\u4ee3\u5b57\u6bb5",
                        "predecessor_count": "\u524d\u7f6e\u5bf9\u8c61",
                        "successor_count": "\u540e\u7f6e\u5bf9\u8c61"
                    },
                    "label": "\u5173\u8054\u5173\u7cfb"
                },
                "person\u0026time": {
                    "name": "person\u0026time",
                    "options": {
                        "owner": "\u5904\u7406\u4eba",
                        "developer": "\u5f00\u53d1\u4eba\u5458",
                        "begin": "\u9884\u8ba1\u5f00\u59cb",
                        "due": "\u9884\u8ba1\u7ed3\u675f",
                        "cc": "\u6284\u9001\u4eba",
                        "creator": "\u521b\u5efa\u4eba",
                        "created": "\u521b\u5efa\u65f6\u95f4",
                        "modified": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                        "participator": "\u53c2\u4e0e\u4eba",
                        "completed": "\u5b8c\u6210\u65f6\u95f4",
                        "commentator": "\u8bc4\u8bba\u4eba",
                        "attachment_uploader": "\u9644\u4ef6\u4e0a\u4f20\u4eba"
                    },
                    "label": "\u4eba\u5458\u548c\u65f6\u95f4"
                },
                "measure": {
                    "name": "measure",
                    "options": {
                        "effort": "\u9884\u4f30\u5de5\u65f6",
                        "effort_completed": "\u5b8c\u6210\u5de5\u65f6",
                        "exceed": "\u8d85\u51fa\u5de5\u65f6",
                        "remain": "\u5269\u4f59\u5de5\u65f6",
                        "progress": "\u8fdb\u5ea6"
                    },
                    "label": "\u5de5\u65f6\u5b57\u6bb5"
                },
                "custom_field": {
                    "name": "custom_field",
                    "options": {
                        "\u9700\u6c42\u6765\u6e90": "\u9700\u6c42\u6765\u6e90"
                    },
                    "label": "\u81ea\u5b9a\u4e49\u5b57\u6bb5"
                }
            }
        },
        "meta": {
            "init_filter_fields": {
                "story": [
                    {
                        "fieldDisplayName": "\u6807\u9898",
                        "fieldIsSystem": "1",
                        "fieldOption": "like",
                        "fieldSystemName": "name",
                        "fieldType": "text",
                        "selectOption": [],
                        "value": ""
                    },
                    {
                        "id": "1",
                        "fieldDisplayName": "\u72b6\u6001",
                        "fieldIsSystem": "1",
                        "fieldOption": "in",
                        "fieldSystemName": "status",
                        "fieldType": "multi_select",
                        "selectOption": [],
                        "value": ""
                    },
                    {
                        "id": "2",
                        "fieldDisplayName": "\u5904\u7406\u4eba",
                        "fieldIsSystem": "1",
                        "fieldOption": "in",
                        "fieldSystemName": "owner",
                        "fieldType": "user_chooser",
                        "selectOption": [],
                        "value": ""
                    },
                    {
                        "id": "3",
                        "fieldDisplayName": "\u8fed\u4ee3",
                        "fieldIsSystem": "1",
                        "fieldOption": "in",
                        "fieldSystemName": "iteration_id",
                        "fieldType": "treeselect",
                        "selectOption": [],
                        "value": ""
                    }
                ]
            },
            "workitem_type_status_map": {
                "1160498179001000016": [
                    {
                        "status": "planning",
                        "status_alias": "\u89c4\u5212\u4e2d"
                    },
                    {
                        "status": "developing",
                        "status_alias": "\u5b9e\u73b0\u4e2d"
                    },
                    {
                        "status": "resolved",
                        "status_alias": "\u5df2\u5b9e\u73b0"
                    },
                    {
                        "status": "rejected",
                        "status_alias": "\u5df2\u62d2\u7edd"
                    }
                ],
                "1160498179001000017": [
                    {
                        "status": "planning",
                        "status_alias": "\u89c4\u5212\u4e2d"
                    },
                    {
                        "status": "developing",
                        "status_alias": "\u5b9e\u73b0\u4e2d"
                    },
                    {
                        "status": "resolved",
                        "status_alias": "\u5df2\u5b9e\u73b0"
                    },
                    {
                        "status": "rejected",
                        "status_alias": "\u5df2\u62d2\u7edd"
                    },
                    {
                        "status": "status_3",
                        "status_alias": "\u8bc4\u5ba1\u4e2d"
                    },
                    {
                        "status": "status_4",
                        "status_alias": "\u6d4b\u8bd5\u4e2d"
                    },
                    {
                        "status": "status_5",
                        "status_alias": "\u4ea7\u54c1\u4f53\u9a8c"
                    }
                ],
                "1160498179001000018": [
                    {
                        "status": "planning",
                        "status_alias": "\u89c4\u5212\u4e2d"
                    },
                    {
                        "status": "developing",
                        "status_alias": "\u5b9e\u73b0\u4e2d"
                    },
                    {
                        "status": "resolved",
                        "status_alias": "\u5df2\u5b9e\u73b0"
                    },
                    {
                        "status": "rejected",
                        "status_alias": "\u5df2\u62d2\u7edd"
                    }
                ],
                "1160498179001000020": [
                    {
                        "status": "open",
                        "status_alias": "\u672a\u5f00\u59cb"
                    },
                    {
                        "status": "progressing",
                        "status_alias": "\u8fdb\u884c\u4e2d"
                    },
                    {
                        "status": "done",
                        "status_alias": "\u5df2\u5b8c\u6210"
                    }
                ]
            },
            "workitem_type_map": {
                "1160498179001000016": {
                    "id": "1160498179001000016",
                    "name": "\u9700\u6c42"
                },
                "1160498179001000017": {
                    "id": "1160498179001000017",
                    "name": "\u7279\u6027"
                },
                "1160498179001000018": {
                    "id": "1160498179001000018",
                    "name": "\u53f2\u8bd7\u6545\u4e8b"
                },
                "1160498179001000020": {
                    "id": "1160498179001000020",
                    "name": "\u4efb\u52a1"
                }
            }
        },
        "field_sub_config_map": {
            "iteration": {
                "id": {
                    "name": "id",
                    "label": "ID",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "name": {
                    "name": "name",
                    "label": "\u6807\u9898",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "startdate": {
                    "name": "startdate",
                    "label": "\u5f00\u59cb\u65f6\u95f4",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "enddate": {
                    "name": "enddate",
                    "label": "\u7ed3\u675f\u65f6\u95f4",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "status": {
                    "name": "status",
                    "label": "\u72b6\u6001",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "label": {
                    "name": "label",
                    "label": "\u6807\u7b7e",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "creator": {
                    "name": "creator",
                    "label": "\u521b\u5efa\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "created": {
                    "name": "created",
                    "label": "\u521b\u5efa\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "modified": {
                    "name": "modified",
                    "label": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "completed": {
                    "name": "completed",
                    "label": "\u5b8c\u6210\u65f6\u95f4",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "description": {
                    "name": "description",
                    "label": "\u76ee\u6807",
                    "html_type": "rich_edit",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                }
            },
            "story": {
                "id": {
                    "name": "id",
                    "label": "ID",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "name": {
                    "name": "name",
                    "label": "\u6807\u9898",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "status": {
                    "name": "status",
                    "label": "\u72b6\u6001",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "description": {
                    "name": "description",
                    "label": "\u8be6\u7ec6\u63cf\u8ff0",
                    "html_type": "rich_edit",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "workitem_type_id": {
                    "name": "workitem_type_id",
                    "label": "\u9700\u6c42\u7c7b\u522b",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "children_id": {
                    "name": "children_id",
                    "label": "\u5b50\u9700\u6c42",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "parent_id": {
                    "name": "parent_id",
                    "label": "\u7236\u9700\u6c42",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "iteration_id": {
                    "name": "iteration_id",
                    "label": "\u8fed\u4ee3",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": "",
                    "lazyModel": "iterationOptions",
                    "switchShowLabel": "\u67e5\u770b\u5df2\u5173\u95ed\u7684\u8fed\u4ee3",
                    "switchHideLabel": "\u9690\u85cf\u5df2\u5173\u95ed\u7684\u8fed\u4ee3"
                },
                "module": {
                    "name": "module",
                    "label": "\u6a21\u5757",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "priority": {
                    "name": "priority",
                    "label": "\u4f18\u5148\u7ea7",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "owner": {
                    "name": "owner",
                    "label": "\u5904\u7406\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "developer": {
                    "name": "developer",
                    "label": "\u5f00\u53d1\u4eba\u5458",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "begin": {
                    "name": "begin",
                    "label": "\u9884\u8ba1\u5f00\u59cb",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "due": {
                    "name": "due",
                    "label": "\u9884\u8ba1\u7ed3\u675f",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "business_value": {
                    "name": "business_value",
                    "label": "\u4e1a\u52a1\u4ef7\u503c",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "size": {
                    "name": "size",
                    "label": "\u89c4\u6a21",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "effort": {
                    "name": "effort",
                    "label": "\u9884\u4f30\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "cc": {
                    "name": "cc",
                    "label": "\u6284\u9001\u4eba",
                    "html_type": "mix_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "creator": {
                    "name": "creator",
                    "label": "\u521b\u5efa\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "created": {
                    "name": "created",
                    "label": "\u521b\u5efa\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "modified": {
                    "name": "modified",
                    "label": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "participator": {
                    "name": "participator",
                    "label": "\u53c2\u4e0e\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "effort_completed": {
                    "name": "effort_completed",
                    "label": "\u5b8c\u6210\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "exceed": {
                    "name": "exceed",
                    "label": "\u8d85\u51fa\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "remain": {
                    "name": "remain",
                    "label": "\u5269\u4f59\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "progress": {
                    "name": "progress",
                    "label": "\u8fdb\u5ea6",
                    "html_type": "percent",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [],
                    "hidden": ""
                },
                "completed": {
                    "name": "completed",
                    "label": "\u5b8c\u6210\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "test_focus": {
                    "name": "test_focus",
                    "label": "\u6d4b\u8bd5\u91cd\u70b9",
                    "html_type": "textarea",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f(\u2260)",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "category_id": {
                    "name": "category_id",
                    "label": "\u5206\u7c7b",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "version": {
                    "name": "version",
                    "label": "\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "label": {
                    "name": "label",
                    "label": "\u6807\u7b7e",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "has_attachment": {
                    "name": "has_attachment",
                    "label": "\u9644\u4ef6",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "level": {
                    "name": "level",
                    "label": "\u5c42\u7ea7",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42",
                            "value": "leaf",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u72ec\u7acb\u9700\u6c42",
                            "value": "single",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u72ec\u7acb\u6216\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42",
                            "value": "single_leaf",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b2c",
                            "label2": "\u5c42"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "single",
                            "label": "\u72ec\u7acb\u9700\u6c42"
                        },
                        {
                            "value": "leaf",
                            "label": "\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42"
                        },
                        {
                            "value": "single_leaf",
                            "label": "\u72ec\u7acb\u6216\u5c42\u7ea7\u6700\u4f4e\u9700\u6c42"
                        }
                    ],
                    "hidden": ""
                },
                "comment": {
                    "name": "comment",
                    "label": "\u8bc4\u8bba",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "commentator": {
                    "name": "commentator",
                    "label": "\u8bc4\u8bba\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "attachment_uploader": {
                    "name": "attachment_uploader",
                    "label": "\u9644\u4ef6\u4e0a\u4f20\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "EntityBugRelation_relative_id": {
                    "name": "EntityBugRelation_relative_id",
                    "label": "\u5173\u8054\u7f3a\u9677",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "\u9700\u6c42\u6765\u6e90": {
                    "html_type": "select",
                    "name": "\u9700\u6c42\u6765\u6e90",
                    "custom_field": "custom_field_one",
                    "label": "\u9700\u6c42\u6765\u6e90",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "0",
                    "category": "custom_field",
                    "options": []
                }
            },
            "bug": {
                "id": {
                    "name": "id",
                    "label": "ID",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "name": {
                    "name": "name",
                    "label": "\u6807\u9898",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "description": {
                    "name": "description",
                    "label": "\u8be6\u7ec6\u63cf\u8ff0",
                    "html_type": "rich_edit",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "module": {
                    "name": "module",
                    "label": "\u6a21\u5757",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "deadline": {
                    "name": "deadline",
                    "label": "\u89e3\u51b3\u671f\u9650",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "creator": {
                    "name": "creator",
                    "label": "\u521b\u5efa\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "created": {
                    "name": "created",
                    "label": "\u521b\u5efa\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "modified": {
                    "name": "modified",
                    "label": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "reopen_time": {
                    "name": "reopen_time",
                    "label": "\u91cd\u65b0\u6253\u5f00\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "closed": {
                    "name": "closed",
                    "label": "\u5173\u95ed\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "lastmodify": {
                    "name": "lastmodify",
                    "label": "\u6700\u540e\u4fee\u6539\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "de": {
                    "name": "de",
                    "label": "\u5f00\u53d1\u4eba\u5458",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "te": {
                    "name": "te",
                    "label": "\u6d4b\u8bd5\u4eba\u5458",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "auditer": {
                    "name": "auditer",
                    "label": "\u5ba1\u6838\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "confirmer": {
                    "name": "confirmer",
                    "label": "\u9a8c\u8bc1\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "owner": {
                    "name": "owner",
                    "label": "\u5904\u7406\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "status": {
                    "name": "status",
                    "label": "\u72b6\u6001",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "resolution": {
                    "name": "resolution",
                    "label": "\u89e3\u51b3\u65b9\u6cd5",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "priority": {
                    "name": "priority",
                    "label": "\u4f18\u5148\u7ea7",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "severity": {
                    "name": "severity",
                    "label": "\u4e25\u91cd\u7a0b\u5ea6",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "platform": {
                    "name": "platform",
                    "label": "\u8f6f\u4ef6\u5e73\u53f0",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "os": {
                    "name": "os",
                    "label": "\u64cd\u4f5c\u7cfb\u7edf",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "testmode": {
                    "name": "testmode",
                    "label": "\u6d4b\u8bd5\u65b9\u5f0f",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "testtype": {
                    "name": "testtype",
                    "label": "\u6d4b\u8bd5\u7c7b\u578b",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "testphase": {
                    "name": "testphase",
                    "label": "\u6d4b\u8bd5\u9636\u6bb5",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "source": {
                    "name": "source",
                    "label": "\u7f3a\u9677\u6839\u6e90",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "frequency": {
                    "name": "frequency",
                    "label": "\u91cd\u73b0\u89c4\u5f8b",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "cc": {
                    "name": "cc",
                    "label": "\u6284\u9001\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "fixer": {
                    "name": "fixer",
                    "label": "\u4fee\u590d\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "closer": {
                    "name": "closer",
                    "label": "\u5173\u95ed\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "participator": {
                    "name": "participator",
                    "label": "\u53c2\u4e0e\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "version_report": {
                    "name": "version_report",
                    "label": "\u53d1\u73b0\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "version_test": {
                    "name": "version_test",
                    "label": "\u9a8c\u8bc1\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "version_fix": {
                    "name": "version_fix",
                    "label": "\u5408\u5165\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "version_close": {
                    "name": "version_close",
                    "label": "\u5173\u95ed\u7248\u672c",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "baseline_find": {
                    "name": "baseline_find",
                    "label": "\u53d1\u73b0\u57fa\u7ebf",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "baseline_join": {
                    "name": "baseline_join",
                    "label": "\u5408\u5165\u57fa\u7ebf",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "baseline_close": {
                    "name": "baseline_close",
                    "label": "\u5173\u95ed\u57fa\u7ebf",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "baseline_test": {
                    "name": "baseline_test",
                    "label": "\u9a8c\u8bc1\u57fa\u7ebf",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "originphase": {
                    "name": "originphase",
                    "label": "\u53d1\u73b0\u9636\u6bb5",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "sourcephase": {
                    "name": "sourcephase",
                    "label": "\u5f15\u5165\u9636\u6bb5",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "bugtype": {
                    "name": "bugtype",
                    "label": "\u7f3a\u9677\u7c7b\u578b",
                    "html_type": "select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "reject_time": {
                    "name": "reject_time",
                    "label": "\u62d2\u7edd\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "in_progress_time": {
                    "name": "in_progress_time",
                    "label": "\u63a5\u53d7\u5904\u7406\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "resolved": {
                    "name": "resolved",
                    "label": "\u89e3\u51b3\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "verify_time": {
                    "name": "verify_time",
                    "label": "\u9a8c\u8bc1\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "assigned_time": {
                    "name": "assigned_time",
                    "label": "\u5206\u914d\u65f6\u95f4",
                    "html_type": "datetime",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "datetime",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "datetime-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "begin": {
                    "name": "begin",
                    "label": "\u9884\u8ba1\u5f00\u59cb",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "due": {
                    "name": "due",
                    "label": "\u9884\u8ba1\u7ed3\u675f",
                    "html_type": "dateinput",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "date",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "date-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u52a8\u6001\u65f6\u95f4",
                            "value": "dynamic_time",
                            "type": "dynamic_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1",
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "1",
                                "month_before",
                                "0",
                                "today"
                            ]
                        },
                        {
                            "label": "\u65e5\u5386\u65f6\u95f4",
                            "value": "calendar_time",
                            "type": "calendar_time",
                            "need_options": "",
                            "placeholder": [
                                "\u5982\uff1a1"
                            ],
                            "default_value": [
                                "before",
                                "1",
                                "week"
                            ]
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "between",
                    "is_system": "1",
                    "category": "person\u0026time",
                    "options": [],
                    "hidden": ""
                },
                "iteration_id": {
                    "name": "iteration_id",
                    "label": "\u8fed\u4ee3",
                    "html_type": "treeselect",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "treeselect",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": "",
                    "lazyModel": "iterationOptions",
                    "switchShowLabel": "\u67e5\u770b\u5df2\u5173\u95ed\u7684\u8fed\u4ee3",
                    "switchHideLabel": "\u9690\u85cf\u5df2\u5173\u95ed\u7684\u8fed\u4ee3"
                },
                "label": {
                    "name": "label",
                    "label": "\u6807\u7b7e",
                    "html_type": "multi_select",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "tselect-multiple",
                            "need_options": "1",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "size": {
                    "name": "size",
                    "label": "\u89c4\u6a21",
                    "html_type": "integer",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "number",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "integer-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "effort": {
                    "name": "effort",
                    "label": "\u9884\u4f30\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "effort_completed": {
                    "name": "effort_completed",
                    "label": "\u5b8c\u6210\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "exceed": {
                    "name": "exceed",
                    "label": "\u8d85\u51fa\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "remain": {
                    "name": "remain",
                    "label": "\u5269\u4f59\u5de5\u65f6",
                    "html_type": "float",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "float",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u533a\u95f4",
                            "value": "between",
                            "type": "float-between",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [
                        {
                            "value": "9999991",
                            "label": "\u7b49\u4e8e"
                        },
                        {
                            "value": "9999992",
                            "label": "\u4e0d\u7b49\u4e8e"
                        },
                        {
                            "value": "9999990",
                            "label": "\u533a\u95f4"
                        },
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "progress": {
                    "name": "progress",
                    "label": "\u8fdb\u5ea6",
                    "html_type": "percent",
                    "operators": [
                        {
                            "label": "\u7b49\u4e8e\uff08=\uff09",
                            "value": "equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u7b49\u4e8e\uff08\u2260\uff09",
                            "value": "not_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\uff08\u003E\uff09",
                            "value": "more",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\uff08\u003C\uff09",
                            "value": "less",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5927\u4e8e\u7b49\u4e8e\uff08\u2265\uff09",
                            "value": "more_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5c0f\u4e8e\u7b49\u4e8e\uff08\u2264\uff09",
                            "value": "less_equal",
                            "type": "percent",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "equal",
                    "is_system": "1",
                    "category": "measure",
                    "options": [],
                    "hidden": ""
                },
                "BugStoryRelation_relative_id": {
                    "name": "BugStoryRelation_relative_id",
                    "label": "\u5173\u8054\u9700\u6c42",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "comment": {
                    "name": "comment",
                    "label": "\u8bc4\u8bba",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "commentator": {
                    "name": "commentator",
                    "label": "\u8bc4\u8bba\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "has_attachment": {
                    "name": "has_attachment",
                    "label": "\u9644\u4ef6",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "attachment_uploader": {
                    "name": "attachment_uploader",
                    "label": "\u9644\u4ef6\u4e0a\u4f20\u4eba",
                    "html_type": "user_chooser",
                    "operators": [
                        {
                            "label": "\u5c5e\u4e8e",
                            "value": "in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5c5e\u4e8e",
                            "value": "not_in",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "user_chooser_with_group",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "in",
                    "is_system": "1",
                    "category": "foundation",
                    "options": [],
                    "hidden": ""
                },
                "EntityBugRelation_relative_id": {
                    "name": "EntityBugRelation_relative_id",
                    "label": "\u5173\u8054\u7f3a\u9677",
                    "html_type": "input",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "1",
                    "category": "relationships",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ],
                    "hidden": ""
                },
                "abc": {
                    "html_type": "text",
                    "name": "abc",
                    "custom_field": "custom_field_one",
                    "label": "abc",
                    "operators": [
                        {
                            "label": "\u5305\u542b",
                            "value": "like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u5305\u542b",
                            "value": "not_like",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [
                                "\u5173\u952e\u5b57\u4e4b\u95f4\u4ee5\u7a7a\u683c\u5206\u9694"
                            ],
                            "default_value": []
                        },
                        {
                            "label": "\u662f",
                            "value": "equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u662f",
                            "value": "not_equal",
                            "type": "text",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e3a\u7a7a",
                            "value": "empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        },
                        {
                            "label": "\u4e0d\u4e3a\u7a7a",
                            "value": "not_empty",
                            "type": "",
                            "need_options": "",
                            "placeholder": [],
                            "default_value": []
                        }
                    ],
                    "default_operator": "like",
                    "is_system": "0",
                    "category": "custom_field",
                    "options": [
                        {
                            "value": "9999999",
                            "label": "\u4e3a\u7a7a"
                        },
                        {
                            "value": "9999995",
                            "label": "\u4e0d\u4e3a\u7a7a"
                        }
                    ]
                }
            }
        },
        "field_sub_category": {
            "iteration": {
                "foundation": {
                    "name": "foundation",
                    "options": {
                        "id": "ID",
                        "name": "\u6807\u9898",
                        "status": "\u72b6\u6001",
                        "label": "\u6807\u7b7e",
                        "completed": "\u5b8c\u6210\u65f6\u95f4",
                        "description": "\u76ee\u6807"
                    },
                    "label": "\u57fa\u7840\u5b57\u6bb5"
                },
                "person\u0026time": {
                    "name": "person\u0026time",
                    "options": {
                        "startdate": "\u5f00\u59cb\u65f6\u95f4",
                        "enddate": "\u7ed3\u675f\u65f6\u95f4",
                        "creator": "\u521b\u5efa\u4eba",
                        "created": "\u521b\u5efa\u65f6\u95f4",
                        "modified": "\u6700\u540e\u4fee\u6539\u65f6\u95f4"
                    },
                    "label": "\u4eba\u5458\u548c\u65f6\u95f4"
                }
            },
            "story": {
                "foundation": {
                    "name": "foundation",
                    "options": {
                        "id": "ID",
                        "name": "\u6807\u9898",
                        "status": "\u72b6\u6001",
                        "description": "\u8be6\u7ec6\u63cf\u8ff0",
                        "workitem_type_id": "\u9700\u6c42\u7c7b\u522b",
                        "iteration_id": "\u8fed\u4ee3",
                        "module": "\u6a21\u5757",
                        "priority": "\u4f18\u5148\u7ea7",
                        "business_value": "\u4e1a\u52a1\u4ef7\u503c",
                        "size": "\u89c4\u6a21",
                        "test_focus": "\u6d4b\u8bd5\u91cd\u70b9",
                        "category_id": "\u5206\u7c7b",
                        "version": "\u7248\u672c",
                        "label": "\u6807\u7b7e",
                        "has_attachment": "\u9644\u4ef6",
                        "level": "\u5c42\u7ea7",
                        "comment": "\u8bc4\u8bba"
                    },
                    "label": "\u57fa\u7840\u5b57\u6bb5",
                    "advanced_fields": {
                        "status_remain": "\u5f53\u524d\u72b6\u6001\u505c\u7559\u65f6\u957f",
                        "workspace_id": "\u6240\u5c5e\u9879\u76ee"
                    }
                },
                "relationships": {
                    "name": "relationships",
                    "options": {
                        "children_id": "\u5b50\u9700\u6c42",
                        "parent_id": "\u7236\u9700\u6c42",
                        "EntityBugRelation_relative_id": "\u5173\u8054\u7f3a\u9677"
                    },
                    "label": "\u5173\u8054\u5173\u7cfb"
                },
                "person\u0026time": {
                    "name": "person\u0026time",
                    "options": {
                        "owner": "\u5904\u7406\u4eba",
                        "developer": "\u5f00\u53d1\u4eba\u5458",
                        "begin": "\u9884\u8ba1\u5f00\u59cb",
                        "due": "\u9884\u8ba1\u7ed3\u675f",
                        "cc": "\u6284\u9001\u4eba",
                        "creator": "\u521b\u5efa\u4eba",
                        "created": "\u521b\u5efa\u65f6\u95f4",
                        "modified": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                        "participator": "\u53c2\u4e0e\u4eba",
                        "completed": "\u5b8c\u6210\u65f6\u95f4",
                        "commentator": "\u8bc4\u8bba\u4eba",
                        "attachment_uploader": "\u9644\u4ef6\u4e0a\u4f20\u4eba"
                    },
                    "label": "\u4eba\u5458\u548c\u65f6\u95f4"
                },
                "measure": {
                    "name": "measure",
                    "options": {
                        "effort": "\u9884\u4f30\u5de5\u65f6",
                        "effort_completed": "\u5b8c\u6210\u5de5\u65f6",
                        "exceed": "\u8d85\u51fa\u5de5\u65f6",
                        "remain": "\u5269\u4f59\u5de5\u65f6",
                        "progress": "\u8fdb\u5ea6"
                    },
                    "label": "\u5de5\u65f6\u5b57\u6bb5"
                },
                "custom_field": {
                    "name": "custom_field",
                    "options": {
                        "\u9700\u6c42\u6765\u6e90": "\u9700\u6c42\u6765\u6e90"
                    },
                    "label": "\u81ea\u5b9a\u4e49\u5b57\u6bb5"
                }
            },
            "bug": {
                "foundation": {
                    "name": "foundation",
                    "options": {
                        "id": "ID",
                        "name": "\u6807\u9898",
                        "description": "\u8be6\u7ec6\u63cf\u8ff0",
                        "module": "\u6a21\u5757",
                        "status": "\u72b6\u6001",
                        "resolution": "\u89e3\u51b3\u65b9\u6cd5",
                        "priority": "\u4f18\u5148\u7ea7",
                        "severity": "\u4e25\u91cd\u7a0b\u5ea6",
                        "platform": "\u8f6f\u4ef6\u5e73\u53f0",
                        "os": "\u64cd\u4f5c\u7cfb\u7edf",
                        "testmode": "\u6d4b\u8bd5\u65b9\u5f0f",
                        "testtype": "\u6d4b\u8bd5\u7c7b\u578b",
                        "testphase": "\u6d4b\u8bd5\u9636\u6bb5",
                        "source": "\u7f3a\u9677\u6839\u6e90",
                        "frequency": "\u91cd\u73b0\u89c4\u5f8b",
                        "version_report": "\u53d1\u73b0\u7248\u672c",
                        "version_test": "\u9a8c\u8bc1\u7248\u672c",
                        "version_fix": "\u5408\u5165\u7248\u672c",
                        "version_close": "\u5173\u95ed\u7248\u672c",
                        "baseline_find": "\u53d1\u73b0\u57fa\u7ebf",
                        "baseline_join": "\u5408\u5165\u57fa\u7ebf",
                        "baseline_close": "\u5173\u95ed\u57fa\u7ebf",
                        "baseline_test": "\u9a8c\u8bc1\u57fa\u7ebf",
                        "originphase": "\u53d1\u73b0\u9636\u6bb5",
                        "sourcephase": "\u5f15\u5165\u9636\u6bb5",
                        "bugtype": "\u7f3a\u9677\u7c7b\u578b",
                        "iteration_id": "\u8fed\u4ee3",
                        "label": "\u6807\u7b7e",
                        "size": "\u89c4\u6a21",
                        "comment": "\u8bc4\u8bba",
                        "commentator": "\u8bc4\u8bba\u4eba",
                        "has_attachment": "\u9644\u4ef6",
                        "attachment_uploader": "\u9644\u4ef6\u4e0a\u4f20\u4eba"
                    },
                    "label": "\u57fa\u7840\u5b57\u6bb5",
                    "advanced_fields": {
                        "status_remain": "\u5f53\u524d\u72b6\u6001\u505c\u7559\u65f6\u957f",
                        "project_id": "\u6240\u5c5e\u9879\u76ee"
                    }
                },
                "person\u0026time": {
                    "name": "person\u0026time",
                    "options": {
                        "deadline": "\u89e3\u51b3\u671f\u9650",
                        "creator": "\u521b\u5efa\u4eba",
                        "created": "\u521b\u5efa\u65f6\u95f4",
                        "modified": "\u6700\u540e\u4fee\u6539\u65f6\u95f4",
                        "reopen_time": "\u91cd\u65b0\u6253\u5f00\u65f6\u95f4",
                        "closed": "\u5173\u95ed\u65f6\u95f4",
                        "lastmodify": "\u6700\u540e\u4fee\u6539\u4eba",
                        "de": "\u5f00\u53d1\u4eba\u5458",
                        "te": "\u6d4b\u8bd5\u4eba\u5458",
                        "auditer": "\u5ba1\u6838\u4eba",
                        "confirmer": "\u9a8c\u8bc1\u4eba",
                        "owner": "\u5904\u7406\u4eba",
                        "cc": "\u6284\u9001\u4eba",
                        "fixer": "\u4fee\u590d\u4eba",
                        "closer": "\u5173\u95ed\u4eba",
                        "participator": "\u53c2\u4e0e\u4eba",
                        "reject_time": "\u62d2\u7edd\u65f6\u95f4",
                        "in_progress_time": "\u63a5\u53d7\u5904\u7406\u65f6\u95f4",
                        "resolved": "\u89e3\u51b3\u65f6\u95f4",
                        "verify_time": "\u9a8c\u8bc1\u65f6\u95f4",
                        "assigned_time": "\u5206\u914d\u65f6\u95f4",
                        "begin": "\u9884\u8ba1\u5f00\u59cb",
                        "due": "\u9884\u8ba1\u7ed3\u675f"
                    },
                    "label": "\u4eba\u5458\u548c\u65f6\u95f4"
                },
                "measure": {
                    "name": "measure",
                    "options": {
                        "effort": "\u9884\u4f30\u5de5\u65f6",
                        "effort_completed": "\u5b8c\u6210\u5de5\u65f6",
                        "exceed": "\u8d85\u51fa\u5de5\u65f6",
                        "remain": "\u5269\u4f59\u5de5\u65f6",
                        "progress": "\u8fdb\u5ea6"
                    },
                    "label": "\u5de5\u65f6\u5b57\u6bb5"
                },
                "relationships": {
                    "name": "relationships",
                    "options": {
                        "BugStoryRelation_relative_id": "\u5173\u8054\u9700\u6c42",
                        "EntityBugRelation_relative_id": "\u5173\u8054\u7f3a\u9677"
                    },
                    "label": "\u5173\u8054\u5173\u7cfb"
                },
                "custom_field": {
                    "name": "custom_field",
                    "options": {
                        "abc": "abc"
                    },
                    "label": "\u81ea\u5b9a\u4e49\u5b57\u6bb5"
                }
            }
        },
        "total": "20"
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749777228"
}
我需要mysql表设计。 ddl   dml  




CREATE DATABASE IF NOT EXISTS tapd_config;
USE tapd_config;

-- 实体类型表 (story, iteration, bug等)
CREATE TABLE entity_type (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE COMMENT '实体类型名称',
  label VARCHAR(50) COMMENT '显示名称'
) COMMENT '实体类型表';

-- 字段定义表 (核心表)
CREATE TABLE field_definition (
  id INT AUTO_INCREMENT PRIMARY KEY,
  entity_type_id INT NOT NULL COMMENT '关联实体类型',
  name VARCHAR(100) NOT NULL COMMENT '字段名',
  label VARCHAR(100) COMMENT '显示标签',
  html_type VARCHAR(50) NOT NULL COMMENT 'HTML控件类型',
  default_operator VARCHAR(50) COMMENT '默认操作符',
  is_system TINYINT(1) DEFAULT 0 COMMENT '是否系统字段',
  category VARCHAR(50) COMMENT '字段分类',
  hidden TINYINT(1) DEFAULT 0 COMMENT '是否隐藏',
  field_sub_config VARCHAR(50) COMMENT '子配置类型',
  lazy_model VARCHAR(50) COMMENT '懒加载模型',
  switch_show_label VARCHAR(100) COMMENT '开关显示标签',
  switch_hide_label VARCHAR(100) COMMENT '开关隐藏标签',
  FOREIGN KEY (entity_type_id) REFERENCES entity_type(id)
) COMMENT '字段定义表';

-- 字段操作符表
CREATE TABLE field_operator (
  id INT AUTO_INCREMENT PRIMARY KEY,
  field_id INT NOT NULL COMMENT '关联字段',
  label VARCHAR(50) NOT NULL COMMENT '操作符标签',
  value VARCHAR(50) NOT NULL COMMENT '操作符值',
  type VARCHAR(50) COMMENT '输入类型',
  need_options TINYINT(1) DEFAULT 0 COMMENT '是否需要选项',
  placeholder JSON COMMENT '占位符文本',
  default_value JSON COMMENT '默认值',
  FOREIGN KEY (field_id) REFERENCES field_definition(id)
) COMMENT '字段操作符表';

-- 字段选项表
CREATE TABLE field_option (
  id INT AUTO_INCREMENT PRIMARY KEY,
  field_id INT NOT NULL COMMENT '关联字段',
  value VARCHAR(100) NOT NULL COMMENT '选项值',
  label VARCHAR(100) NOT NULL COMMENT '选项标签',
  label2 VARCHAR(100) COMMENT '第二标签',
  FOREIGN KEY (field_id) REFERENCES field_definition(id)
) COMMENT '字段选项表';

-- 字段分类表
CREATE TABLE field_category (
  id INT AUTO_INCREMENT PRIMARY KEY,
  entity_type_id INT NOT NULL COMMENT '关联实体类型',
  name VARCHAR(50) NOT NULL COMMENT '分类名称',
  label VARCHAR(100) COMMENT '分类标签',
  FOREIGN KEY (entity_type_id) REFERENCES entity_type(id)
) COMMENT '字段分类表';

-- 分类字段映射表
CREATE TABLE category_field_mapping (
  category_id INT NOT NULL COMMENT '关联分类',
  field_name VARCHAR(100) NOT NULL COMMENT '字段名',
  field_label VARCHAR(100) COMMENT '字段标签',
  FOREIGN KEY (category_id) REFERENCES field_category(id),
  PRIMARY KEY (category_id, field_name)
) COMMENT '分类字段映射表';

-- 工作项类型映射表
CREATE TABLE workitem_type_map (
  id VARCHAR(50) PRIMARY KEY COMMENT '工作项类型ID',
  name VARCHAR(100) NOT NULL COMMENT '工作项类型名称'
) COMMENT '工作项类型映射表';

-- 工作项状态映射表
CREATE TABLE workitem_status_map (
  workitem_type_id VARCHAR(50) NOT NULL COMMENT '关联工作项类型',
  status VARCHAR(50) NOT NULL COMMENT '状态值',
  status_alias VARCHAR(100) NOT NULL COMMENT '状态别名',
  FOREIGN KEY (workitem_type_id) REFERENCES workitem_type_map(id),
  PRIMARY KEY (workitem_type_id, status)
) COMMENT '工作项状态映射表';

-- 初始化过滤字段表
CREATE TABLE init_filter_field (
  id INT AUTO_INCREMENT PRIMARY KEY,
  entity_type_id INT NOT NULL COMMENT '关联实体类型',
  field_display_name VARCHAR(100) COMMENT '字段显示名',
  field_is_system TINYINT(1) DEFAULT 0 COMMENT '是否系统字段',
  field_option VARCHAR(50) COMMENT '字段操作符',
  field_system_name VARCHAR(100) COMMENT '字段系统名称',
  field_type VARCHAR(50) COMMENT '字段类型',
  select_option JSON COMMENT '选择选项',
  value VARCHAR(255) COMMENT '值',
  FOREIGN KEY (entity_type_id) REFERENCES entity_type(id)
) COMMENT '初始化过滤字段配置';





-- 插入实体类型
INSERT INTO entity_type (name, label) VALUES 
('story', '需求'),
('iteration', '迭代'),
('bug', '缺陷');

-- 插入字段定义 (示例)
INSERT INTO field_definition (entity_type_id, name, label, html_type, default_operator, is_system, category, field_sub_config)
SELECT id, 'id', 'ID', 'input', 'like', 1, 'foundation', NULL
FROM entity_type WHERE name = 'story';

-- 插入操作符 (示例)
INSERT INTO field_operator (field_id, label, value, type, need_options)
SELECT fd.id, '包含', 'like', 'text', 0
FROM field_definition fd
JOIN entity_type et ON fd.entity_type_id = et.id
WHERE et.name = 'story' AND fd.name = 'name';

-- 插入选项 (示例)
INSERT INTO field_option (field_id, value, label)
SELECT fd.id, '9999999', '为空'
FROM field_definition fd
JOIN entity_type et ON fd.entity_type_id = et.id
WHERE et.name = 'story' AND fd.name = 'name';

-- 插入分类 (示例)
INSERT INTO field_category (entity_type_id, name, label)
SELECT id, 'foundation', '基础字段'
FROM entity_type WHERE name = 'story';

-- 插入分类字段映射 (示例)
INSERT INTO category_field_mapping (category_id, field_name, field_label)
SELECT fc.id, 'id', 'ID'
FROM field_category fc
JOIN entity_type et ON fc.entity_type_id = et.id
WHERE et.name = 'story' AND fc.name = 'foundation';

-- 工作项类型映射
INSERT INTO workitem_type_map (id, name) VALUES
('1160498179001000016', '需求'),
('1160498179001000017', '特性'),
('1160498179001000018', '史诗故事'),
('1160498179001000020', '任务');

-- 工作项状态映射
INSERT INTO workitem_status_map (workitem_type_id, status, status_alias) VALUES
('1160498179001000016', 'planning', '规划中'),
('1160498179001000016', 'developing', '实现中');

-- 初始化过滤字段
INSERT INTO init_filter_field (entity_type_id, field_display_name, field_is_system, field_option, field_system_name, field_type)
SELECT id, '标题', 1, 'like', 'name', 'text'
FROM entity_type WHERE name = 'story';




































