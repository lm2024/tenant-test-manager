左侧菜单接口
请求网址
https://www.tapd.cn/api/testx/plan/v1/namespaces/33142026/folders/children
请求方法
POST
入参：
{"ItemType":"ALL","Uid":"0","PlanArchive":"ARCHIVEMODE_NO"}
返参：
{
    "Error": null,
    "Data": {
        "Folders": [
            {
                "Uid": "152226",
                "Namespace": "33142026",
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T15:58:00+08:00",
                    "UpdatedAt": "2025-07-15T15:58:00+08:00",
                    "Tenant": ""
                },
                "ParentUid": "0",
                "Name": "6666666",
                "Description": "",
                "PlanCount": 1,
                "ArchiveAuto": false,
                "Folders": [],
                "Plans": [],
                "Nid": "",
                "Path": "."
            }
        ],
        "Plans": []
    }
}


测试计划列表查询接口
请求网址
https://www.tapd.cn/api/testx/plan/v1/namespaces/33142026/folders/152226/plans-list
请求方法
POST
入参：
{"Archive":"ARCHIVEMODE_NO","PageInfo":{"Limit":10,"Offset":0},"Ordering":"","Testers":[],"AssociatedResources":null}
返参：
{
    "Error": null,
    "Data": [
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
                    "UpdatedAt": "2025-07-15T15:59:53+08:00",
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
                "FolderPath": "",
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
    "TotalCount": 1
}


根据上述内容设计MySQL5 的表结构（可能涉及多个表）， 需要带上完整的备注内容。
确保语法合法 。 不要漏掉吧表。 我需要完整的ddl以及一两条测试数据

-- =============================
-- 测试计划相关表结构 DDL
-- =============================

-- 测试计划文件夹表
CREATE TABLE IF NOT EXISTS `test_plan_folders` (
  `uid` VARCHAR(32) NOT NULL COMMENT '文件夹唯一ID',
  `namespace` VARCHAR(32) NOT NULL COMMENT '工作区ID',
  `parent_uid` VARCHAR(32) DEFAULT '0' COMMENT '父文件夹ID',
  `name` VARCHAR(255) NOT NULL COMMENT '文件夹名称',
  `description` TEXT COMMENT '描述',
  `plan_count` INT DEFAULT 0 COMMENT '计划数',
  `archive_auto` TINYINT(1) DEFAULT 0 COMMENT '是否自动归档',
  `nid` VARCHAR(64) DEFAULT '' COMMENT 'NID',
  `path` VARCHAR(255) DEFAULT '' COMMENT '路径',
  `created_by` VARCHAR(32) NOT NULL COMMENT '创建人',
  `updated_by` VARCHAR(32) DEFAULT '' COMMENT '更新人',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试计划文件夹';

-- 测试计划主表
CREATE TABLE IF NOT EXISTS `test_plans` (
  `uid` VARCHAR(32) NOT NULL COMMENT '测试计划唯一ID',
  `namespace` VARCHAR(32) NOT NULL COMMENT '工作区ID',
  `folder_uid` VARCHAR(32) NOT NULL COMMENT '所属文件夹ID',
  `name` VARCHAR(255) NOT NULL COMMENT '测试计划名称',
  `description` TEXT COMMENT '描述',
  `data_source_mode` VARCHAR(32) DEFAULT '' COMMENT '数据源模式',
  `data_source_case_suite_uid` VARCHAR(32) DEFAULT '' COMMENT '数据源用例集UID',
  `state` VARCHAR(32) DEFAULT '' COMMENT '计划状态',
  `version` VARCHAR(16) DEFAULT '' COMMENT '计划版本',
  `folder_path` VARCHAR(255) DEFAULT '' COMMENT '文件夹路径',
  `nid` VARCHAR(64) DEFAULT '' COMMENT 'NID',
  `path` VARCHAR(255) DEFAULT '' COMMENT '路径',
  `created_by` VARCHAR(32) NOT NULL COMMENT '创建人',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_by` VARCHAR(32) DEFAULT '' COMMENT '更新人',
  `updated_at` DATETIME NOT NULL COMMENT '更新时间',
  `starter` VARCHAR(32) DEFAULT '' COMMENT '启动人',
  `terminator` VARCHAR(32) DEFAULT '' COMMENT '终止人',
  PRIMARY KEY (`uid`),
  KEY idx_folder (`folder_uid`),
  CONSTRAINT fk_plan_folder FOREIGN KEY (`folder_uid`) REFERENCES `test_plan_folders`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试计划主表';

-- 测试计划字段表（Spec.Field）
CREATE TABLE IF NOT EXISTS `test_plan_fields` (
  `plan_uid` VARCHAR(32) NOT NULL COMMENT '测试计划ID',
  `summary` VARCHAR(255) DEFAULT '' COMMENT '概要',
  `version` VARCHAR(64) DEFAULT '' COMMENT '版本',
  `type` VARCHAR(32) DEFAULT '' COMMENT '类型',
  `extranet_publish` VARCHAR(32) DEFAULT '' COMMENT '是否外网发布',
  `publish_review` VARCHAR(32) DEFAULT '' COMMENT '发布评审',
  `estimate_test_at` VARCHAR(32) DEFAULT '' COMMENT '预计测试时间',
  `actual_test_at` VARCHAR(32) DEFAULT '' COMMENT '实际测试时间',
  `estimate_publish_at` VARCHAR(32) DEFAULT '' COMMENT '预计发布时间',
  `actual_publish_at` VARCHAR(32) DEFAULT '' COMMENT '实际发布时间',
  `estimate_started_at` VARCHAR(32) DEFAULT '' COMMENT '预计开始时间',
  `estimate_ended_at` VARCHAR(32) DEFAULT '' COMMENT '预计结束时间',
  `env` VARCHAR(64) DEFAULT '' COMMENT '环境',
  `developers` VARCHAR(255) DEFAULT '' COMMENT '开发人员(逗号分隔)',
  `project_managers` VARCHAR(255) DEFAULT '' COMMENT '项目经理(逗号分隔)',
  `product_managers` VARCHAR(255) DEFAULT '' COMMENT '产品经理(逗号分隔)',
  PRIMARY KEY (`plan_uid`),
  CONSTRAINT fk_field_plan FOREIGN KEY (`plan_uid`) REFERENCES `test_plans`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试计划字段信息';

-- 测试数据
INSERT INTO `test_plan_folders` (`uid`, `namespace`, `parent_uid`, `name`, `description`, `plan_count`, `archive_auto`, `nid`, `path`, `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES
('152226', '33142026', '0', '6666666', '', 1, 0, '', '.', '412178979', '412178979', '2025-07-15 15:58:00', '2025-07-15 15:58:00');

INSERT INTO `test_plans` (`uid`, `namespace`, `folder_uid`, `name`, `description`, `data_source_mode`, `data_source_case_suite_uid`, `state`, `version`, `folder_path`, `nid`, `path`, `created_by`, `created_at`, `updated_by`, `updated_at`, `starter`, `terminator`) VALUES
('152227', '33142026', '152226', '77777', '', 'CASE_REPO', '1788265', 'WAITING', 'V2', '', '', '.152226.', '412178979', '2025-07-15 15:59:53', '', '2025-07-15 15:59:53', '', '412178979');

INSERT INTO `test_plan_fields` (`plan_uid`, `summary`, `version`, `type`, `extranet_publish`, `publish_review`, `estimate_test_at`, `actual_test_at`, `estimate_publish_at`, `actual_publish_at`, `estimate_started_at`, `estimate_ended_at`, `env`, `developers`, `project_managers`, `product_managers`) VALUES
('152227', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');






