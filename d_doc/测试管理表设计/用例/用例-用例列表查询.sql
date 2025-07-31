请求网址
https://www.tapd.cn/api/testx/case/v1/namespaces/33142026/repos/17090/versions/18158/cases/search
请求方法
POST
入参：
{"FolderUid":"12571480","ShowMode":"FLAT","ExtendFields":["LABEL","AUTOMATE","CUSTOM","ISSUE","CASE_EXTENSIONS","FOLDER_EXTENSIONS","STEP"],"PageInfo":{"Limit":10,"Offset":0},"OrderBy":""}

返参：
{
    "Error": null,
    "Data": {
        "Folders": [],
        "Cases": [
            {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:28:31+08:00",
                    "UpdatedAt": "2025-07-15T14:28:31+08:00",
                    "Tenant": "46730612"
                },
                "Uid": "12571481",
                "RepoUid": "17090",
                "RepoVersionUid": "18158",
                "FolderUid": "12571480",
                "FullPath": "",
                "UUID": "4eea9118-18de-48ef-9776-a0597d756d2a",
                "Name": "登录时记住用户名",
                "Description": "",
                "Priority": "P0",
                "PreConditions": "1.进入平台首页\n2.成功注册账户，用户名testxadmin，密码admintestx",
                "Type": "",
                "StepType": "STEP",
                "Steps": [
                    {
                        "Id": "16066828",
                        "Content": "点击登录按钮",
                        "ExpectedResult": "成功进入登录页面",
                        "AutomationCases": [],
                        "NID": "cstp-J459Jtt2CV"
                    },
                    {
                        "Id": "16066829",
                        "Content": "分别在对应的输入框填写用户名testxadmin，密码admintestx，勾选‘记住用户名’，点击登录按钮",
                        "ExpectedResult": "成功登录平台",
                        "AutomationCases": [],
                        "NID": "cstp-kln0sKUMGT"
                    },
                    {
                        "Id": "16066830",
                        "Content": "退出登录，重新进入登录页面",
                        "ExpectedResult": "用户名显示testxadmin",
                        "AutomationCases": [],
                        "NID": "cstp-atJhUddsdp"
                    }
                ],
                "StepText": null,
                "Attachments": [],
                "CustomFields": [],
                "Labels": [],
                "Source": "TESTX",
                "ReviewAt": "",
                "ReviewState": "DRAFT",
                "AutomatedState": "",
                "IsManualRelation": false,
                "AutomationCases": [],
                "Issues": [],
                "RelatedSuiteUids": [],
                "RelatedSetUids": [],
                "Owners": [],
                "ManHourEstimated": "",
                "Nid": "",
                "Path": ".12571474.12571475.12571480.",
                "BugCount": "0",
                "Bugs": [],
                "IsNotification": false,
                "RunTimes": "0",
                "Executions": [],
                "Reviews": [],
                "CaseBug": null,
                "FolderExtensions": null,
                "IsFolder": false,
                "SetUids": [],
                "IsBaseline": false,
                "ExecutionPlatforms": ""
            },
            {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:28:31+08:00",
                    "UpdatedAt": "2025-07-15T14:28:31+08:00",
                    "Tenant": "46730612"
                },
                "Uid": "12571482",
                "RepoUid": "17090",
                "RepoVersionUid": "18158",
                "FolderUid": "12571480",
                "FullPath": "",
                "UUID": "1eeb9078-2f4f-42e6-920b-3666b86c2618",
                "Name": "正常登录进入平台",
                "Description": "",
                "Priority": "P0",
                "PreConditions": "1.进入平台首页\n2.成功注册账户，用户名testxadmin，密码admintestx",
                "Type": "",
                "StepType": "STEP",
                "Steps": [
                    {
                        "Id": "16066831",
                        "Content": "点击登录按钮",
                        "ExpectedResult": "成功进入登录页面",
                        "AutomationCases": [],
                        "NID": "cstp-GrN6YDA49O"
                    }
                ],
                "StepText": null,
                "Attachments": [],
                "CustomFields": [],
                "Labels": [],
                "Source": "TESTX",
                "ReviewAt": "",
                "ReviewState": "DRAFT",
                "AutomatedState": "",
                "IsManualRelation": false,
                "AutomationCases": [],
                "Issues": [],
                "RelatedSuiteUids": [],
                "RelatedSetUids": [],
                "Owners": [],
                "ManHourEstimated": "",
                "Nid": "",
                "Path": ".12571474.12571475.12571480.",
                "BugCount": "0",
                "Bugs": [],
                "IsNotification": false,
                "RunTimes": "0",
                "Executions": [],
                "Reviews": [],
                "CaseBug": null,
                "FolderExtensions": null,
                "IsFolder": false,
                "SetUids": [],
                "IsBaseline": false,
                "ExecutionPlatforms": ""
            },
            {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:28:31+08:00",
                    "UpdatedAt": "2025-07-15T14:28:31+08:00",
                    "Tenant": "46730612"
                },
                "Uid": "12571483",
                "RepoUid": "17090",
                "RepoVersionUid": "18158",
                "FolderUid": "12571480",
                "FullPath": "",
                "UUID": "f2deef67-f12f-4aad-8687-fc95107041dc",
                "Name": "未登录状态浏览平台",
                "Description": "",
                "Priority": "P0",
                "PreConditions": "1.进入平台首页\n2.成功注册账户，用户名testxadmin，密码admintestx",
                "Type": "",
                "StepType": "STEP",
                "Steps": [
                    {
                        "Id": "16066832",
                        "Content": "随意点击任意页面",
                        "ExpectedResult": "除了个人中心提示未登录，其它页面均可正常查看",
                        "AutomationCases": [],
                        "NID": "cstp-PRSo30V9nS"
                    }
                ],
                "StepText": null,
                "Attachments": [],
                "CustomFields": [],
                "Labels": [],
                "Source": "TESTX",
                "ReviewAt": "",
                "ReviewState": "DRAFT",
                "AutomatedState": "",
                "IsManualRelation": false,
                "AutomationCases": [],
                "Issues": [],
                "RelatedSuiteUids": [],
                "RelatedSetUids": [],
                "Owners": [],
                "ManHourEstimated": "",
                "Nid": "",
                "Path": ".12571474.12571475.12571480.",
                "BugCount": "0",
                "Bugs": [],
                "IsNotification": false,
                "RunTimes": "0",
                "Executions": [],
                "Reviews": [],
                "CaseBug": null,
                "FolderExtensions": null,
                "IsFolder": false,
                "SetUids": [],
                "IsBaseline": false,
                "ExecutionPlatforms": ""
            }
        ],
        "Repos": [
            {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "",
                    "CreatedAt": "2025-07-15T14:28:31+08:00",
                    "UpdatedAt": "2025-07-15T14:28:31+08:00",
                    "Tenant": "46730612"
                },
                "Namespace": "33142026",
                "Uid": "17090",
                "Name": "默认用例库",
                "Description": "",
                "CodeRepo": {
                    "ID": "0",
                    "Name": "",
                    "URL": "https://git.woa.com",
                    "Type": "CODE_REPO_TYPE_TGIT"
                },
                "RefAutomationRepos": [],
                "Versions": [
                    {
                        "Audit": {
                            "Creator": "412178979",
                            "Updater": "",
                            "CreatedAt": "2025-07-15T14:28:31+08:00",
                            "UpdatedAt": "2025-07-15T14:28:31+08:00",
                            "Tenant": "46730612"
                        },
                        "Uid": "18158",
                        "Repo": {
                            "Audit": null,
                            "Namespace": "",
                            "Uid": "17090",
                            "Name": "",
                            "Description": "",
                            "CodeRepo": null,
                            "RefAutomationRepos": [],
                            "Versions": [],
                            "AssociatedResources": [],
                            "Nid": "",
                            "Type": "DEFALT"
                        },
                        "ParentVersionUid": "0",
                        "Name": "master",
                        "Description": "稳定版本(默认)",
                        "SourceCount": 0,
                        "TargetCount": 0,
                        "TargetFolderCount": 0,
                        "TimeLeft": 0,
                        "Status": "COMPLETED",
                        "FolderOnly": false,
                        "CaseUids": [],
                        "CaseCount": 0,
                        "Nid": ""
                    }
                ],
                "AssociatedResources": [],
                "Nid": "",
                "Type": "SYSTEM"
            }
        ]
    },
    "TotalCount": 3
}



用例库
请求网址
https://www.tapd.cn/api/testx/case/v1/namespaces/33142026/repos?PageInfo.Offset=0&PageInfo.Limit=10000
请求方法
GET
入参：
PageInfo.Offset=0&PageInfo.Limit=10000
返参：
{
    "Error": null,
    "Data": [
        {
            "Audit": {
                "Creator": "412178979",
                "Updater": "",
                "CreatedAt": "2025-07-15T14:28:31+08:00",
                "UpdatedAt": "2025-07-15T14:28:31+08:00",
                "Tenant": "46730612"
            },
            "Namespace": "33142026",
            "Uid": "17090",
            "Name": "默认用例库",
            "Description": "",
            "CodeRepo": {
                "ID": "0",
                "Name": "",
                "URL": "https://git.woa.com",
                "Type": "CODE_REPO_TYPE_TGIT"
            },
            "RefAutomationRepos": [],
            "Versions": [
                {
                    "Audit": {
                        "Creator": "412178979",
                        "Updater": "",
                        "CreatedAt": "2025-07-15T14:28:31+08:00",
                        "UpdatedAt": "2025-07-15T14:28:31+08:00",
                        "Tenant": "46730612"
                    },
                    "Uid": "18158",
                    "Repo": {
                        "Audit": null,
                        "Namespace": "",
                        "Uid": "17090",
                        "Name": "",
                        "Description": "",
                        "CodeRepo": null,
                        "RefAutomationRepos": [],
                        "Versions": [],
                        "AssociatedResources": [],
                        "Nid": "",
                        "Type": "DEFALT"
                    },
                    "ParentVersionUid": "0",
                    "Name": "master",
                    "Description": "稳定版本(默认)",
                    "SourceCount": 0,
                    "TargetCount": 0,
                    "TargetFolderCount": 0,
                    "TimeLeft": 0,
                    "Status": "COMPLETED",
                    "FolderOnly": false,
                    "CaseUids": [],
                    "CaseCount": 0,
                    "Nid": ""
                }
            ],
            "AssociatedResources": [],
            "Nid": "",
            "Type": "SYSTEM"
        }
    ],
    "TotalCount": 1
}



左侧目录树
请求网址
https://www.tapd.cn/api/testx/case/v1/namespaces/33142026/repos/17090/versions/18158/cases/search
请求方法
POST
入参：
{"FolderUid":"0","ShowMode":"FLAT","PageInfo":{"Limit":1,"Offset":0}}

返参：
{
    "Error": null,
    "Data": {
        "Folders": [
            {
                "Uid": "12571474",
                "RepoUid": "17090",
                "RepoVersionUid": "18158",
                "FolderUid": "",
                "FullPath": "",
                "Name": "示例测试用例",
                "Owners": [],
                "Description": "",
                "CaseCount": 6,
                "Folders": [],
                "Cases": [],
                "UUID": "e139a7e4-18b1-47bf-bc5a-59bc2937da8e",
                "Nid": "",
                "Path": ".",
                "ArchiveUid": "",
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:28:31+08:00",
                    "UpdatedAt": "2025-07-15T14:28:31+08:00",
                    "Tenant": "46730612"
                },
                "Issues": [],
                "CopyCaseUids": [],
                "IncludeAncestors": false,
                "FolderExtensions": {
                    "FolderType": "GENERAL",
                    "AllowActions": [],
                    "CanImport": false,
                    "DisplayMonitor": false,
                    "MergingCount": 0
                },
                "Reviews": [],
                "IsBaseline": false
            }
        ],
        "Cases": [],
        "Repos": []
    },
    "TotalCount": 1
}


根据上述内容设计MySQL5 的表结构（可能涉及多个表）， 需要带上完整的备注内容。
确保语法合法 。 不要漏掉吧表。 我需要完整的ddl以及一两条测试数据




-- 用例库表
drop table if exists `test_case_repos`;
create table `test_case_repos` (
  `uid` varchar(32) not null comment '用例库唯一ID',
  `namespace` varchar(32) not null comment '工作区ID',
  `name` varchar(255) not null comment '用例库名称',
  `description` text comment '描述',
  `type` varchar(32) default '' comment '库类型',
  `code_repo_id` varchar(32) default '' comment '代码库ID',
  `code_repo_name` varchar(255) default '' comment '代码库名称',
  `code_repo_url` varchar(255) default '' comment '代码库URL',
  `code_repo_type` varchar(32) default '' comment '代码库类型',
  `created_by` varchar(32) not null comment '创建人',
  `created_at` datetime not null comment '创建时间',
  primary key (`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例库';

-- 用例库版本表
drop table if exists `test_case_repo_versions`;
create table `test_case_repo_versions` (
  `uid` varchar(32) not null comment '版本唯一ID',
  `repo_uid` varchar(32) not null comment '所属用例库ID',
  `name` varchar(100) not null comment '版本名称',
  `description` text comment '描述',
  `status` varchar(32) default '' comment '版本状态',
  `created_by` varchar(32) not null comment '创建人',
  `created_at` datetime not null comment '创建时间',
  primary key (`uid`),
  key idx_repo (`repo_uid`),
  constraint fk_version_repo foreign key (`repo_uid`) references `test_case_repos`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='用例库版本';

-- 用例文件夹表
drop table if exists `test_case_folders`;
create table `test_case_folders` (
  `uid` varchar(32) not null comment '文件夹唯一ID',
  `repo_uid` varchar(32) not null comment '所属用例库ID',
  `repo_version_uid` varchar(32) not null comment '所属版本ID',
  `parent_uid` varchar(32) default '' comment '父文件夹ID',
  `name` varchar(255) not null comment '文件夹名称',
  `description` text comment '描述',
  `case_count` int default 0 comment '用例数',
  `uuid` varchar(64) default '' comment '唯一标识',
  `path` varchar(255) default '' comment '路径',
  `created_by` varchar(32) not null comment '创建人',
  `created_at` datetime not null comment '创建时间',
  primary key (`uid`),
  key idx_repo (`repo_uid`),
  key idx_version (`repo_version_uid`),
  constraint fk_folder_repo foreign key (`repo_uid`) references `test_case_repos`(`uid`),
  constraint fk_folder_version foreign key (`repo_version_uid`) references `test_case_repo_versions`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='用例文件夹';

-- 用例主表
drop table if exists `test_cases`;
create table `test_cases` (
  `uid` varchar(32) not null comment '用例唯一ID',
  `repo_uid` varchar(32) not null comment '所属用例库ID',
  `repo_version_uid` varchar(32) not null comment '所属版本ID',
  `folder_uid` varchar(32) not null comment '所属文件夹ID',
  `uuid` varchar(64) default '' comment '唯一标识',
  `name` varchar(255) not null comment '用例名称',
  `description` text comment '描述',
  `priority` varchar(16) default '' comment '优先级',
  `pre_conditions` text comment '前置条件',
  `type` varchar(32) default '' comment '用例类型',
  `step_type` varchar(16) default '' comment '步骤类型',
  `source` varchar(32) default '' comment '来源',
  `review_state` varchar(32) default '' comment '评审状态',
  `automated_state` varchar(32) default '' comment '自动化状态',
  `is_manual_relation` tinyint(1) default 0 comment '是否手动关联',
  `bug_count` int default 0 comment '缺陷数',
  `run_times` int default 0 comment '执行次数',
  `is_notification` tinyint(1) default 0 comment '是否通知',
  `is_folder` tinyint(1) default 0 comment '是否文件夹',
  `is_baseline` tinyint(1) default 0 comment '是否基线',
  `created_by` varchar(32) not null comment '创建人',
  `created_at` datetime not null comment '创建时间',
  primary key (`uid`),
  key idx_folder (`folder_uid`),
  key idx_repo (`repo_uid`),
  key idx_version (`repo_version_uid`),
  constraint fk_case_folder foreign key (`folder_uid`) references `test_case_folders`(`uid`),
  constraint fk_case_repo foreign key (`repo_uid`) references `test_case_repos`(`uid`),
  constraint fk_case_version foreign key (`repo_version_uid`) references `test_case_repo_versions`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例主表';

-- 用例步骤表
drop table if exists `test_case_steps`;
create table `test_case_steps` (
  `id` varchar(32) not null comment '步骤唯一ID',
  `case_uid` varchar(32) not null comment '所属用例ID',
  `content` text not null comment '步骤内容',
  `expected_result` text comment '预期结果',
  `nid` varchar(32) default '' comment '步骤NID',
  primary key (`id`),
  key idx_case (`case_uid`),
  constraint fk_step_case foreign key (`case_uid`) references `test_cases`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例步骤';

-- 测试数据
insert into `test_case_repos` (`uid`, `namespace`, `name`, `description`, `type`, `code_repo_id`, `code_repo_name`, `code_repo_url`, `code_repo_type`, `created_by`, `created_at`) values
('17090', '33142026', '默认用例库', '', 'SYSTEM', '0', '', 'https://git.woa.com', 'CODE_REPO_TYPE_TGIT', '412178979', '2025-07-15 14:28:31');

insert into `test_case_repo_versions` (`uid`, `repo_uid`, `name`, `description`, `status`, `created_by`, `created_at`) values
('18158', '17090', 'master', '稳定版本(默认)', 'COMPLETED', '412178979', '2025-07-15 14:28:31');

insert into `test_case_folders` (`uid`, `repo_uid`, `repo_version_uid`, `parent_uid`, `name`, `description`, `case_count`, `uuid`, `path`, `created_by`, `created_at`) values
('12571480', '17090', '18158', '', '示例测试用例', '', 6, 'e139a7e4-18b1-47bf-bc5a-59bc2937da8e', '.', '412178979', '2025-07-15 14:28:31');

insert into `test_cases` (`uid`, `repo_uid`, `repo_version_uid`, `folder_uid`, `uuid`, `name`, `description`, `priority`, `pre_conditions`, `type`, `step_type`, `source`, `review_state`, `automated_state`, `is_manual_relation`, `bug_count`, `run_times`, `is_notification`, `is_folder`, `is_baseline`, `created_by`, `created_at`) values
('12571481', '17090', '18158', '12571480', '4eea9118-18de-48ef-9776-a0597d756d2a', '登录时记住用户名', '', 'P0', '1.进入平台首页\n2.成功注册账户，用户名testxadmin，密码admintestx', '', 'STEP', 'TESTX', 'DRAFT', '', 0, 0, 0, 0, 0, 0, '412178979', '2025-07-15 14:28:31');

insert into `test_case_steps` (`id`, `case_uid`, `content`, `expected_result`, `nid`) values
('16066828', '12571481', '点击登录按钮', '成功进入登录页面', 'cstp-J459Jtt2CV'),
('16066829', '12571481', '分别在对应的输入框填写用户名testxadmin，密码admintestx，勾选‘记住用户名’，点击登录按钮', '成功登录平台', 'cstp-kln0sKUMGT'),
('16066830', '12571481', '退出登录，重新进入登录页面', '用户名显示testxadmin', 'cstp-atJhUddsdp');