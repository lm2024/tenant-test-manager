请求网址
https://www.tapd.cn/api/testx/report/v1/namespaces/33142026/reports
请求方法
POST
入参：
{"Title":"77777","Summary":"","Source":"REPORTSOURCE_PLAN","Plans":[{"Meta":{"Uid":"152227","Name":"77777"}}]}
返参：
{
    "RequestId": "",
    "Error": null,
    "Data": {
        "Audit": {
            "Creator": "412178979",
            "Updater": "412178979",
            "CreatedAt": "2025-07-15T16:55:25+08:00",
            "UpdatedAt": "2025-07-15T16:55:25+08:00",
            "Tenant": ""
        },
        "Uid": "11183",
        "Title": "77777",
        "Namespace": "33142026",
        "Summary": "",
        "Plans": [
            {
                "Meta": {
                    "Uid": "152227",
                    "Namespace": "",
                    "Audit": null,
                    "FolderUid": "",
                    "Name": "77777",
                    "Description": "",
                    "DataSource": null,
                    "State": "NONE",
                    "Testers": [],
                    "FolderPath": "",
                    "Version": "V1",
                    "CasePath": null,
                    "Nid": "",
                    "Path": ""
                },
                "Spec": null
            }
        ],
        "Source": "REPORTSOURCE_PLAN",
        "TemplateUid": "0",
        "NotificationUid": "0",
        "Tasks": [],
        "Nid": "",
        "Stat": null
    }
}


请求网址
https://www.tapd.cn/api/testx/report/v1/namespaces/33142026/reports/11183
请求方法
GET
入参：

返参：
{
    "RequestId": "",
    "Error": null,
    "Data": {
        "Audit": {
            "Creator": "412178979",
            "Updater": "412178979",
            "CreatedAt": "2025-07-15T16:55:25+08:00",
            "UpdatedAt": "2025-07-15T16:55:25+08:00",
            "Tenant": ""
        },
        "Uid": "11183",
        "Title": "77777",
        "Namespace": "33142026",
        "Summary": "",
        "Plans": [
            {
                "Meta": {
                    "Uid": "152227",
                    "Namespace": "33142026",
                    "Audit": {
                        "Creator": "412178979",
                        "CreatedAt": "2025-07-15T15:59:53+08:00",
                        "StartedAt": "",
                        "EndedAt": "",
                        "Starter": "",
                        "Terminator": "412178979",
                        "UpdatedAt": "2025-07-15T16:28:33+08:00",
                        "Updater": ""
                    },
                    "FolderUid": "152226",
                    "Name": "77777",
                    "Description": "",
                    "DataSource": {
                        "Mode": "CASE_REPO",
                        "CaseUids": [],
                        "CasesetUids": [],
                        "CaseSuiteUid": "1788265"
                    },
                    "State": "WAITING",
                    "Testers": [
                        "412178979"
                    ],
                    "FolderPath": "/6666666",
                    "Version": "V2",
                    "CasePath": {
                        "RepoUid": "",
                        "RepoVersionUid": "",
                        "FolderUid": ""
                    },
                    "Nid": "",
                    "Path": ".152226."
                },
                "Spec": {
                    "Notification": null,
                    "Field": {
                        "Developers": [],
                        "ProjectManagers": [],
                        "ProductManagers": [],
                        "Summary": "",
                        "Version": "",
                        "Type": "",
                        "ExtranetPublish": "",
                        "PublishReview": "",
                        "EstimateTestAt": "",
                        "ActualTestAt": "",
                        "EstimatePublishAt": "",
                        "ActualPublishAt": "",
                        "EstimateStartedAt": "",
                        "EstimateEndedAt": "",
                        "Env": ""
                    },
                    "CustomFields": [],
                    "Stories": [],
                    "Bugs": [],
                    "Iterations": [],
                    "AutoTasks": [],
                    "Statistic": null,
                    "Properties": [],
                    "SystemFields": [],
                    "Scope": null,
                    "Target": null,
                    "Versions": []
                }
            }
        ],
        "Source": "REPORTSOURCE_PLAN",
        "TemplateUid": "0",
        "NotificationUid": "0",
        "Tasks": [],
        "Nid": "",
        "Stat": null
    }
}


请求网址
https://www.tapd.cn/api/testx/report/v1/namespaces/33142026/templates/1730
请求方法
GET
入参：

返参：
{
    "RequestId": "",
    "Error": null,
    "Data": {
        "Audit": null,
        "Uid": "1730",
        "Title": "系统报告模板",
        "Namespace": "33142026",
        "IsSystem": true,
        "Desc": "系统报告模板",
        "AsDefault": true,
        "Order": 0,
        "NotificationUid": "",
        "NotificationName": "",
        "Modules": [
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "268",
                "Title": "概览",
                "Name": "overview",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "可选择需要的字段计划体现测试结果数据。",
                "ModuleOffset": 5,
                "ParentUid": "0",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "TextContent",
                "Order": 1,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41248"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "293",
                "Title": "测试结论",
                "Name": "summary",
                "Namespace": "33142026",
                "Type": "summary",
                "Desc": "可填写测试结果、测试总结、发布标准。",
                "ModuleOffset": 1,
                "ParentUid": "0",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "FormContent",
                "Order": 2,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41245"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "298",
                "Title": "测试详情",
                "Name": "detail",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "通过丰富图表形式统计测试结果数据。",
                "ModuleOffset": 2,
                "ParentUid": "0",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "ChartContent",
                "Order": 3,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41241"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "270",
                "Title": "用例总数",
                "Name": "total_case_count",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "case",
                "ContentType": "DataText",
                "Order": 4,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41249"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "271",
                "Title": "测试通过率",
                "Name": "succeed_case_rate",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "case",
                "ContentType": "DataText",
                "Order": 5,
                "Help": "测试通过率 = 已测通过用例数 / 计划中总用例数",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41244"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "275",
                "Title": "用例执行进度",
                "Name": "execution_rate",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "case",
                "ContentType": "DataText",
                "Order": 6,
                "Help": "用例执行进度 = ( 计划中总用例数 - 未测用例数 ) / 计划中总用例数",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41242"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "291",
                "Title": "开始时间",
                "Name": "start_time",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "plan",
                "ContentType": "DataText",
                "Order": 7,
                "Help": "开始时间：指提交第一个用例的执行结果（包括录入结果、写注释、上传附件、关联缺陷等任一动作）的时间点",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41231"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "277",
                "Title": "缺陷总数",
                "Name": "total_bug_count",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "tapd",
                "ContentType": "DataText",
                "Order": 8,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41238"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "294",
                "Title": "测试结果",
                "Name": "test_result",
                "Namespace": "33142026",
                "Type": "Select",
                "Desc": "",
                "ModuleOffset": 1,
                "ParentUid": "293",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "SelectWidget",
                "Order": 9,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41250"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "278",
                "Title": "缺陷解决率",
                "Name": "bug_solve_rate",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "tapd",
                "ContentType": "DataText",
                "Order": 10,
                "Help": "缺陷解决率 = 计划中缺陷状态为已关闭、已解决、已验证或已拒绝的缺陷数量 / 计划中总缺陷数量",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41236"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "279",
                "Title": "严重缺陷解决率",
                "Name": "serious_bug_solve_rate",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "tapd",
                "ContentType": "DataText",
                "Order": 11,
                "Help": "严重缺陷解决率 = ( 计划关联的缺陷严重程度为严重、且状态为已关闭或已解决或已拒绝的缺陷数量 ) / 计划关联的缺陷严重程度为严重的缺陷数量",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41234"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "283",
                "Title": "执行耗时",
                "Name": "spend_time",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "plan",
                "ContentType": "DataText",
                "Order": 12,
                "Help": "执行耗时 = 结束时间 - 开始时间",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41233"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "292",
                "Title": "结束时间",
                "Name": "end_time",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "plan",
                "ContentType": "DataText",
                "Order": 13,
                "Help": "结束时间：指所有用例都有了执行结果，且最后一次提交用例执行结果（包括录入结果、写注释、上传附件、关联缺陷任一动作）的时间点",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41230"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "290",
                "Title": "测试整体进度",
                "Name": "total_test_rate",
                "Namespace": "33142026",
                "Type": "overview",
                "Desc": "",
                "ModuleOffset": 5,
                "ParentUid": "268",
                "IsSystem": true,
                "ServerType": "case",
                "ContentType": "DataText",
                "Order": 14,
                "Help": "",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41232"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "295",
                "Title": "测试总结",
                "Name": "test_result_summary",
                "Namespace": "33142026",
                "Type": "summary",
                "Desc": "",
                "ModuleOffset": 1,
                "ParentUid": "293",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "TextArea",
                "Order": 15,
                "Help": "",
                "Content": "",
                "Editable": true,
                "TemplateModuleUid": "41246"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "299",
                "Title": "用例分布-按执行结果",
                "Name": "case_result_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 16,
                "Help": "计划下全部用例不同测试结果分布占比",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41251"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "300",
                "Title": "用例分布-按执行人员",
                "Name": "case_tester_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 17,
                "Help": "计划下分配给各测试人员已执行用例数统计",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41247"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "303",
                "Title": "缺陷分布-按处理状态",
                "Name": "bug_open_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 18,
                "Help": "计划下用例关联的缺陷打开和关闭状态分布占比",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41240"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "304",
                "Title": "缺陷分布-按上报人",
                "Name": "bug_tester_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 19,
                "Help": "计划下各测试人员执行用例时上报缺陷数统计",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41239"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "305",
                "Title": "缺陷分布-按处理人",
                "Name": "bug_handler_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 20,
                "Help": "计划下各测试人员执行用例时处理缺陷数统计",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41237"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "306",
                "Title": "缺陷分布-按严重程度",
                "Name": "bug_level_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 21,
                "Help": "计划下用例关联的缺陷按照不同优先级分布占比",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41235"
            },
            {
                "Audit": {
                    "Creator": "sys",
                    "Updater": "sys",
                    "CreatedAt": "2025-07-15 16:13:48",
                    "UpdatedAt": "2025-07-15 16:13:48",
                    "Tenant": ""
                },
                "Uid": "301",
                "Title": "用例分布-按测试手段",
                "Name": "case_type_img",
                "Namespace": "33142026",
                "Type": "detail",
                "Desc": "",
                "ModuleOffset": 2,
                "ParentUid": "298",
                "IsSystem": true,
                "ServerType": "",
                "ContentType": "DataChart",
                "Order": 22,
                "Help": "计划下用例通过不同测试手段执行用例数统计",
                "Content": "",
                "Editable": false,
                "TemplateModuleUid": "41243"
            }
        ],
        "Count": 0
    }
}




请求网址
https://www.tapd.cn/api/testx/plan/v1/namespaces/33142026/plans/statistics
请求方法
POST
入参：
{"Uids":["152227"]}
返参：
{
    "Error": null,
    "Data": [
        {
            "Uid": "152227",
            "Statistic": {
                "SucceedCaseCount": 0,
                "FailedCaseCount": 0,
                "BlockCaseCount": 0,
                "ErrorCaseCount": 0,
                "RetryCaseCount": 0,
                "IgnoreCaseCount": 0,
                "TodoCaseCount": 0,
                "ManualSucceedRate": "",
                "TestedCaseCount": 0,
                "TotalCaseCount": 0,
                "CaseSucceedRate": "",
                "AutoTaskCount": 0,
                "AutoCaseCount": 0,
                "AutoSucceedRate": "",
                "AutoCaseCoverageRate": "",
                "StoryPassedRate": "",
                "FailedTaskCount": 0,
                "CaseCoverageRate": "",
                "StoryCount": 1
            },
            "CaseRepos": []
        }
    ],
    "TotalCount": 1
}

-- ========================
-- 测试管理系统-用例报告相关表结构（含自定义字段EAV模型）
-- ========================

-- 1. 报告主表
CREATE TABLE IF NOT EXISTS report (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    uid VARCHAR(32) NOT NULL COMMENT '报告唯一ID',
    title VARCHAR(255) NOT NULL COMMENT '报告标题',
    namespace VARCHAR(64) NOT NULL COMMENT '空间ID',
    summary TEXT COMMENT '报告摘要',
    source VARCHAR(64) COMMENT '报告来源',
    template_uid VARCHAR(32) COMMENT '模板UID',
    notification_uid VARCHAR(32) COMMENT '通知UID',
    nid VARCHAR(64) COMMENT 'NID',
    stat TEXT COMMENT '统计信息',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    creator VARCHAR(64) COMMENT '创建人',
    updater VARCHAR(64) COMMENT '更新人',
    tenant VARCHAR(64) COMMENT '租户',
    INDEX idx_uid(uid)
) COMMENT='测试报告主表';

-- 2. 报告与计划关联表
CREATE TABLE IF NOT EXISTS report_plan (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    report_uid VARCHAR(32) NOT NULL COMMENT '报告UID',
    plan_uid VARCHAR(32) NOT NULL COMMENT '计划UID',
    plan_name VARCHAR(255) COMMENT '计划名称',
    plan_version VARCHAR(32) COMMENT '计划版本',
    plan_folder_uid VARCHAR(32) COMMENT '计划文件夹UID',
    plan_folder_path VARCHAR(255) COMMENT '计划文件夹路径',
    plan_state VARCHAR(32) COMMENT '计划状态',
    plan_description TEXT COMMENT '计划描述',
    plan_nid VARCHAR(64) COMMENT '计划NID',
    plan_path VARCHAR(255) COMMENT '计划路径',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_report_uid(report_uid),
    INDEX idx_plan_uid(plan_uid)
) COMMENT='报告-计划关联表';

-- 3. 报告模块表（如概览、结论、详情等）
CREATE TABLE IF NOT EXISTS report_module (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    report_uid VARCHAR(32) NOT NULL COMMENT '报告UID',
    module_uid VARCHAR(32) NOT NULL COMMENT '模块UID',
    title VARCHAR(255) NOT NULL COMMENT '模块标题',
    name VARCHAR(64) NOT NULL COMMENT '模块名称',
    type VARCHAR(32) NOT NULL COMMENT '模块类型',
    content_type VARCHAR(32) COMMENT '内容类型',
    parent_uid VARCHAR(32) COMMENT '父模块UID',
    order_num INT COMMENT '排序',
    desc_text TEXT COMMENT '模块描述',
    help_text TEXT COMMENT '帮助说明',
    content TEXT COMMENT '模块内容',
    editable TINYINT(1) DEFAULT 0 COMMENT '是否可编辑',
    is_system TINYINT(1) DEFAULT 0 COMMENT '是否系统模块',
    module_offset INT COMMENT '模块偏移',
    server_type VARCHAR(32) COMMENT '服务类型',
    template_module_uid VARCHAR(32) COMMENT '模板模块UID',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    creator VARCHAR(64) COMMENT '创建人',
    updater VARCHAR(64) COMMENT '更新人',
    tenant VARCHAR(64) COMMENT '租户',
    INDEX idx_report_uid(report_uid)
) COMMENT='报告模块表';

-- 4. 报告计划统计表
CREATE TABLE IF NOT EXISTS report_plan_statistic (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    plan_uid VARCHAR(32) NOT NULL COMMENT '计划UID',
    succeed_case_count INT DEFAULT 0 COMMENT '通过用例数',
    failed_case_count INT DEFAULT 0 COMMENT '失败用例数',
    block_case_count INT DEFAULT 0 COMMENT '阻塞用例数',
    error_case_count INT DEFAULT 0 COMMENT '错误用例数',
    retry_case_count INT DEFAULT 0 COMMENT '重试用例数',
    ignore_case_count INT DEFAULT 0 COMMENT '忽略用例数',
    todo_case_count INT DEFAULT 0 COMMENT '未测用例数',
    manual_succeed_rate VARCHAR(16) COMMENT '手动通过率',
    tested_case_count INT DEFAULT 0 COMMENT '已测用例数',
    total_case_count INT DEFAULT 0 COMMENT '总用例数',
    case_succeed_rate VARCHAR(16) COMMENT '用例通过率',
    auto_task_count INT DEFAULT 0 COMMENT '自动化任务数',
    auto_case_count INT DEFAULT 0 COMMENT '自动化用例数',
    auto_succeed_rate VARCHAR(16) COMMENT '自动化通过率',
    auto_case_coverage_rate VARCHAR(16) COMMENT '自动化用例覆盖率',
    story_passed_rate VARCHAR(16) COMMENT '需求通过率',
    failed_task_count INT DEFAULT 0 COMMENT '失败任务数',
    case_coverage_rate VARCHAR(16) COMMENT '用例覆盖率',
    story_count INT DEFAULT 0 COMMENT '需求数',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_plan_uid(plan_uid)
) COMMENT='报告计划统计表';

-- 5. 报告计划用例库表（可扩展）
CREATE TABLE IF NOT EXISTS report_plan_case_repo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    plan_uid VARCHAR(32) NOT NULL COMMENT '计划UID',
    repo_uid VARCHAR(32) COMMENT '用例库UID',
    repo_version_uid VARCHAR(32) COMMENT '用例库版本UID',
    folder_uid VARCHAR(32) COMMENT '用例库文件夹UID',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_plan_uid(plan_uid)
) COMMENT='报告计划用例库表';

-- 6. 报告自定义字段（EAV模型，支持动态扩展）
CREATE TABLE IF NOT EXISTS report_custom_field (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    report_uid VARCHAR(32) NOT NULL COMMENT '报告UID',
    field_key VARCHAR(64) NOT NULL COMMENT '自定义字段key',
    field_value TEXT COMMENT '自定义字段值',
    field_type VARCHAR(32) COMMENT '字段类型',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_report_uid(report_uid),
    INDEX idx_field_key(field_key)
) COMMENT='报告自定义字段EAV表';

-- 7. 报告计划自定义字段（EAV模型，支持动态扩展）
CREATE TABLE IF NOT EXISTS report_plan_custom_field (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    plan_uid VARCHAR(32) NOT NULL COMMENT '计划UID',
    field_key VARCHAR(64) NOT NULL COMMENT '自定义字段key',
    field_value TEXT COMMENT '自定义字段值',
    field_type VARCHAR(32) COMMENT '字段类型',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_plan_uid(plan_uid),
    INDEX idx_field_key(field_key)
) COMMENT='报告计划自定义字段EAV表';

-- 8. 报告模块自定义字段（EAV模型，支持动态扩展）
CREATE TABLE IF NOT EXISTS report_module_custom_field (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    module_uid VARCHAR(32) NOT NULL COMMENT '模块UID',
    field_key VARCHAR(64) NOT NULL COMMENT '自定义字段key',
    field_value TEXT COMMENT '自定义字段值',
    field_type VARCHAR(32) COMMENT '字段类型',
    created_at DATETIME NOT NULL COMMENT '创建时间',
    updated_at DATETIME NOT NULL COMMENT '更新时间',
    INDEX idx_module_uid(module_uid),
    INDEX idx_field_key(field_key)
) COMMENT='报告模块自定义字段EAV表';

-- 9. 测试数据示例
INSERT INTO report (uid, title, namespace, summary, source, template_uid, notification_uid, nid, stat, created_at, updated_at, creator, updater, tenant)
VALUES ('11183', '77777', '33142026', '', 'REPORTSOURCE_PLAN', '0', '0', '', NULL, '2025-07-15 16:55:25', '2025-07-15 16:55:25', '412178979', '412178979', '');

INSERT INTO report_plan (report_uid, plan_uid, plan_name, plan_version, plan_folder_uid, plan_folder_path, plan_state, plan_description, plan_nid, plan_path, created_at, updated_at)
VALUES ('11183', '152227', '77777', 'V2', '152226', '/6666666', 'WAITING', '', '', '.152226.', '2025-07-15 16:55:25', '2025-07-15 16:55:25');

INSERT INTO report_plan_statistic (plan_uid, succeed_case_count, failed_case_count, block_case_count, error_case_count, retry_case_count, ignore_case_count, todo_case_count, manual_succeed_rate, tested_case_count, total_case_count, case_succeed_rate, auto_task_count, auto_case_count, auto_succeed_rate, auto_case_coverage_rate, story_passed_rate, failed_task_count, case_coverage_rate, story_count, created_at, updated_at)
VALUES ('152227', 0, 0, 0, 0, 0, 0, 0, '', 0, 0, '', 0, 0, '', '', '', 0, '', 1, '2025-07-15 16:55:25', '2025-07-15 16:55:25');

INSERT INTO report_module (report_uid, module_uid, title, name, type, content_type, parent_uid, order_num, desc_text, help_text, content, editable, is_system, module_offset, server_type, template_module_uid, created_at, updated_at, creator, updater, tenant)
VALUES ('11183', '268', '概览', 'overview', 'overview', 'TextContent', '0', 1, '可选择需要的字段计划体现测试结果数据。', '', '', 0, 1, 5, '', '41248', '2025-07-15 16:13:48', '2025-07-15 16:13:48', 'sys', 'sys', ''),
       ('11183', '293', '测试结论', 'summary', 'summary', 'FormContent', '0', 2, '可填写测试结果、测试总结、发布标准。', '', '', 0, 1, 1, '', '41245', '2025-07-15 16:13:48', '2025-07-15 16:13:48', 'sys', 'sys', '');

INSERT INTO report_custom_field (report_uid, field_key, field_value, field_type, created_at, updated_at)
VALUES ('11183', 'custom_field_1', '自定义值1', 'string', '2025-07-15 16:55:25', '2025-07-15 16:55:25');

INSERT INTO report_plan_custom_field (plan_uid, field_key, field_value, field_type, created_at, updated_at)
VALUES ('152227', 'custom_field_2', '自定义值2', 'string', '2025-07-15 16:55:25', '2025-07-15 16:55:25');

INSERT INTO report_module_custom_field (module_uid, field_key, field_value, field_type, created_at, updated_at)
VALUES ('268', 'custom_field_3', '自定义值3', 'string', '2025-07-15 16:13:48', '2025-07-15 16:13:48');

-- 10. 动态自定义字段作为查询条件的SQL示例：
-- 查询报告中自定义字段 custom_field_1=某值 的所有报告
-- SELECT r.* FROM report r
-- JOIN report_custom_field cf ON r.uid = cf.report_uid AND cf.field_key = 'custom_field_1' AND cf.field_value = '某值';
--
-- 查询报告中同时满足 custom_field_1=值1 且 custom_field_2=值2 的所有报告
-- SELECT r.* FROM report r
-- JOIN report_custom_field cf1 ON r.uid = cf1.report_uid AND cf1.field_key = 'custom_field_1' AND cf1.field_value = '值1'
-- JOIN report_custom_field cf2 ON r.uid = cf2.report_uid AND cf2.field_key = 'custom_field_2' AND cf2.field_value = '值2';

-- ========================
-- 以上为用例报告相关完整DDL及测试数据，支持灵活自定义字段存储与查询
-- ========================

-- =============================
-- 测试报告相关表结构 DDL（含EAV自定义字段，完整注释）
-- =============================

-- 1. 测试报告主表
drop table if exists `test_report`;
create table `test_report` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `uid` varchar(32) not null comment '报告唯一标识',
  `namespace` varchar(64) not null comment '空间/项目ID',
  `title` varchar(255) not null comment '报告标题',
  `summary` text comment '报告摘要',
  `source` varchar(64) not null comment '报告来源（如计划、迭代等）',
  `template_uid` varchar(32) default null comment '报告模板UID',
  `notification_uid` varchar(32) default null comment '通知模板UID',
  `nid` varchar(64) default null comment '报告NID',
  `stat` json default null comment '统计信息（冗余）',
  `creator` varchar(32) not null comment '创建人',
  `updater` varchar(32) not null comment '更新人',
  `created_at` datetime not null comment '创建时间',
  `updated_at` datetime not null comment '更新时间',
  `tenant` varchar(64) default null comment '租户',
  primary key (`id`),
  unique key `uniq_uid` (`uid`),
  key `idx_namespace` (`namespace`)
) engine=innodb default charset=utf8mb4 comment='测试报告主表';

-- 2. 报告与计划关联表
drop table if exists `test_report_plan`;
create table `test_report_plan` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `report_uid` varchar(32) not null comment '报告UID',
  `plan_uid` varchar(32) not null comment '计划UID',
  `plan_meta` json default null comment '计划元信息（如名称、描述等）',
  `plan_spec` json default null comment '计划Spec（如通知、字段等）',
  primary key (`id`),
  key `idx_report_uid` (`report_uid`),
  key `idx_plan_uid` (`plan_uid`)
) engine=innodb default charset=utf8mb4 comment='报告与计划关联表';

-- 3. 报告模板表
drop table if exists `test_report_template`;
create table `test_report_template` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `uid` varchar(32) not null comment '模板UID',
  `namespace` varchar(64) not null comment '空间/项目ID',
  `title` varchar(255) not null comment '模板标题',
  `desc` text comment '模板描述',
  `is_system` tinyint(1) not null default 0 comment '是否系统模板',
  `as_default` tinyint(1) not null default 0 comment '是否默认模板',
  `order_num` int not null default 0 comment '排序',
  `notification_uid` varchar(32) default null comment '通知模板UID',
  `notification_name` varchar(255) default null comment '通知模板名称',
  `count` int not null default 0 comment '引用次数',
  primary key (`id`),
  unique key `uniq_uid` (`uid`),
  key `idx_namespace` (`namespace`)
) engine=innodb default charset=utf8mb4 comment='测试报告模板表';

-- 4. 报告模板模块表
drop table if exists `test_report_template_module`;
create table `test_report_template_module` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `uid` varchar(32) not null comment '模块UID',
  `template_uid` varchar(32) not null comment '所属模板UID',
  `namespace` varchar(64) not null comment '空间/项目ID',
  `title` varchar(255) not null comment '模块标题',
  `name` varchar(64) not null comment '模块英文名',
  `type` varchar(32) not null comment '模块类型',
  `desc` text comment '模块描述',
  `module_offset` int not null default 0 comment '模块偏移',
  `parent_uid` varchar(32) not null default '0' comment '父模块UID',
  `is_system` tinyint(1) not null default 0 comment '是否系统模块',
  `server_type` varchar(32) default null comment '服务类型',
  `content_type` varchar(32) default null comment '内容类型',
  `order_num` int not null default 0 comment '排序',
  `help` text comment '帮助说明',
  `content` text comment '内容',
  `editable` tinyint(1) not null default 0 comment '是否可编辑',
  `template_module_uid` varchar(32) default null comment '模板模块UID',
  `creator` varchar(32) default null comment '创建人',
  `updater` varchar(32) default null comment '更新人',
  `created_at` datetime default null comment '创建时间',
  `updated_at` datetime default null comment '更新时间',
  `tenant` varchar(64) default null comment '租户',
  primary key (`id`),
  unique key `uniq_uid` (`uid`),
  key `idx_template_uid` (`template_uid`)
) engine=innodb default charset=utf8mb4 comment='测试报告模板模块表';

-- 5. 报告统计表
drop table if exists `test_report_statistic`;
create table `test_report_statistic` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `report_uid` varchar(32) not null comment '报告UID',
  `plan_uid` varchar(32) not null comment '计划UID',
  `succeed_case_count` int not null default 0 comment '通过用例数',
  `failed_case_count` int not null default 0 comment '失败用例数',
  `block_case_count` int not null default 0 comment '阻塞用例数',
  `error_case_count` int not null default 0 comment '异常用例数',
  `retry_case_count` int not null default 0 comment '重试用例数',
  `ignore_case_count` int not null default 0 comment '忽略用例数',
  `todo_case_count` int not null default 0 comment '未测用例数',
  `manual_succeed_rate` varchar(16) default '' comment '手工通过率',
  `tested_case_count` int not null default 0 comment '已测用例数',
  `total_case_count` int not null default 0 comment '总用例数',
  `case_succeed_rate` varchar(16) default '' comment '用例通过率',
  `auto_task_count` int not null default 0 comment '自动化任务数',
  `auto_case_count` int not null default 0 comment '自动化用例数',
  `auto_succeed_rate` varchar(16) default '' comment '自动化通过率',
  `auto_case_coverage_rate` varchar(16) default '' comment '自动化覆盖率',
  `story_passed_rate` varchar(16) default '' comment '需求通过率',
  `failed_task_count` int not null default 0 comment '失败任务数',
  `case_coverage_rate` varchar(16) default '' comment '用例覆盖率',
  `story_count` int not null default 0 comment '需求数',
  primary key (`id`),
  key `idx_report_uid` (`report_uid`),
  key `idx_plan_uid` (`plan_uid`)
) engine=innodb default charset=utf8mb4 comment='测试报告统计表';

-- 6. 报告动态自定义字段EAV表
drop table if exists `test_report_custom_field`;
create table `test_report_custom_field` (
  `id` bigint(20) unsigned not null auto_increment comment '主键',
  `report_uid` varchar(32) not null comment '报告UID',
  `field_key` varchar(64) not null comment '自定义字段key',
  `field_value` text comment '自定义字段值',
  `field_type` varchar(32) default null comment '字段类型',
  `field_label` varchar(128) default null comment '字段显示名',
  `plan_uid` varchar(32) default null comment '计划UID（如字段归属计划）',
  primary key (`id`),
  key `idx_report_uid` (`report_uid`),
  key `idx_field_key` (`field_key`)
) engine=innodb default charset=utf8mb4 comment='测试报告自定义字段EAV表';

-- 7. 测试数据示例
insert into `test_report` (`uid`, `namespace`, `title`, `summary`, `source`, `template_uid`, `notification_uid`, `nid`, `stat`, `creator`, `updater`, `created_at`, `updated_at`, `tenant`) values
('11183', '33142026', '77777', '', 'REPORTSOURCE_PLAN', '0', '0', '', null, '412178979', '412178979', '2025-07-15 16:55:25', '2025-07-15 16:55:25', '');

insert into `test_report_plan` (`report_uid`, `plan_uid`, `plan_meta`, `plan_spec`) values
('11183', '152227', '{"Name":"77777"}', null);

insert into `test_report_template` (`uid`, `namespace`, `title`, `desc`, `is_system`, `as_default`, `order_num`, `notification_uid`, `notification_name`, `count`) values
('1730', '33142026', '系统报告模板', '系统报告模板', 1, 1, 0, '', '', 0);

insert into `test_report_template_module` (`uid`, `template_uid`, `namespace`, `title`, `name`, `type`, `desc`, `module_offset`, `parent_uid`, `is_system`, `server_type`, `content_type`, `order_num`, `help`, `content`, `editable`, `template_module_uid`, `creator`, `updater`, `created_at`, `updated_at`, `tenant`) values
('268', '1730', '33142026', '概览', 'overview', 'overview', '可选择需要的字段计划体现测试结果数据。', 5, '0', 1, '', 'TextContent', 1, '', '', 0, '41248', 'sys', 'sys', '2025-07-15 16:13:48', '2025-07-15 16:13:48', ''),
       ('293', '1730', '33142026', '测试结论', 'summary', 'summary', '可填写测试结果、测试总结、发布标准。', 1, '0', 1, '', 'FormContent', 2, '', '', 0, '41245', 'sys', 'sys', '2025-07-15 16:13:48', '2025-07-15 16:13:48', '');

insert into `test_report_statistic` (`report_uid`, `plan_uid`, `succeed_case_count`, `failed_case_count`, `block_case_count`, `error_case_count`, `retry_case_count`, `ignore_case_count`, `todo_case_count`, `manual_succeed_rate`, `tested_case_count`, `total_case_count`, `case_succeed_rate`, `auto_task_count`, `auto_case_count`, `auto_succeed_rate`, `auto_case_coverage_rate`, `story_passed_rate`, `failed_task_count`, `case_coverage_rate`, `story_count`) values
('11183', '152227', 0, 0, 0, 0, 0, 0, 0, '', 0, 0, '', 0, 0, '', '', '', 0, '', 1);

insert into `test_report_custom_field` (`report_uid`, `field_key`, `field_value`, `field_type`, `field_label`, `plan_uid`) values
('11183', 'custom_field_89', '自定义值', 'string', '自定义字段示例', '152227');

-- 8. 动态自定义字段作为查询条件的SQL示例
-- 查询报告中自定义字段custom_field_89=某值的所有报告
select r.*
from test_report r
join test_report_custom_field cf on r.uid = cf.report_uid
where cf.field_key = 'custom_field_89' and cf.field_value = '自定义值';

-- 查询某计划下，且自定义字段custom_field_89=某值的报告
select r.*
from test_report r
join test_report_plan rp on r.uid = rp.report_uid
join test_report_custom_field cf on r.uid = cf.report_uid
where rp.plan_uid = '152227' and cf.field_key = 'custom_field_89' and cf.field_value = '自定义值';

-- =============================
-- 以上为测试报告相关完整DDL及测试数据，支持动态自定义字段EAV模型
-- =============================