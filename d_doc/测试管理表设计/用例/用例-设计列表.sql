请求网址
https://www.tapd.cn/api/testx/design/v2/namespaces/33142026/designs/search
请求方法
POST

入参：
{"Filter":{"Search":""},"PageInfo":{"Limit":10,"Offset":0}}

返参：
{
    "Error": null,
    "Data": [
        {
            "Meta": {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:29:14+08:00",
                    "UpdatedAt": "2025-07-15T14:40:47+08:00",
                    "Tenant": ""
                },
                "Namespace": "33142026",
                "Uid": "design-F6zhMhgk8T",
                "ParentUid": "",
                "Name": "啊啊啊",
                "Description": "",
                "Thumbnail": "",
                "Source": "MANUALLY",
                "UseAI": false,
                "TemplateUid": ""
            },
            "Spec": {
                "Theme": "",
                "Labels": [],
                "Stories": [
                    {
                        "IssueUid": "1133142026001000041",
                        "Namespace": "33142026",
                        "WorkspaceUid": "33142026",
                        "IssueUrl": "",
                        "Type": "STORY",
                        "Source": "TAPD",
                        "Detail": null,
                        "IssueName": "【示例】邮箱密码登陆",
                        "IsDeleted": false,
                        "Uid": "story-xjnEttA8ee"
                    },
                    {
                        "IssueUid": "1133142026001000040",
                        "Namespace": "33142026",
                        "WorkspaceUid": "33142026",
                        "IssueUrl": "",
                        "Type": "STORY",
                        "Source": "TAPD",
                        "Detail": null,
                        "IssueName": "【示例】权限管理",
                        "IsDeleted": false,
                        "Uid": "story-KwHtdZZUzI"
                    }
                ],
                "Bugs": [],
                "CustomFields": {}
            },
            "Review": {
                "Audit": {
                    "Creator": "412178979",
                    "Updater": "412178979",
                    "CreatedAt": "2025-07-15T14:29:15+08:00",
                    "UpdatedAt": "2025-07-15T14:29:15+08:00",
                    "Tenant": ""
                },
                "Namespace": "33142026",
                "DesignUid": "",
                "Uid": "design_review-oWawDgS3da",
                "State": "UNREVIEW",
                "Comments": [],
                "Reviewers": [],
                "Stat": null
            },
            "Nodes": [],
            "Stat": {
                "CustomCount": 0,
                "StoryCount": 2,
                "SceneCount": 0,
                "TestPointCount": 1,
                "CaseCount": 3,
                "FeatureCount": 1,
                "BugCount": 0,
                "TotalCount": 7,
                "NodeUid": "",
                "ExecCount": 0,
                "ExecPassCount": 0,
                "AiCaseCount": 0
            }
        }
    ],
    "TotalCount": 1
}



根据上述内容设计MySQL5 的表结构（可能涉及多个表）， 需要带上完整的备注内容。
确保语法合法 。 不要漏掉吧表。 我需要完整的ddl以及一两条测试数据

-- 测试用例设计主表
drop table if exists `test_designs`;
create table `test_designs` (
  `uid` varchar(32) not null comment '设计唯一ID',
  `namespace` varchar(32) not null comment '工作区ID',
  `parent_uid` varchar(32) default '' comment '父设计ID',
  `name` varchar(255) not null comment '设计名称',
  `description` text comment '描述',
  `thumbnail` varchar(255) default '' comment '缩略图',
  `source` varchar(32) default '' comment '来源',
  `use_ai` tinyint(1) default 0 comment '是否使用AI',
  `template_uid` varchar(32) default '' comment '模板UID',
  `created_by` varchar(32) not null comment '创建人',
  `updated_by` varchar(32) not null comment '更新人',
  `created_at` datetime not null comment '创建时间',
  `updated_at` datetime not null comment '更新时间',
  primary key (`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例设计主表';

-- 设计统计信息表
drop table if exists `test_design_stats`;
create table `test_design_stats` (
  `design_uid` varchar(32) not null comment '设计唯一ID',
  `custom_count` int default 0 comment '自定义数',
  `story_count` int default 0 comment '需求数',
  `scene_count` int default 0 comment '场景数',
  `test_point_count` int default 0 comment '测试点数',
  `case_count` int default 0 comment '用例数',
  `feature_count` int default 0 comment '特性数',
  `bug_count` int default 0 comment '缺陷数',
  `total_count` int default 0 comment '总数',
  `node_uid` varchar(32) default '' comment '节点UID',
  `exec_count` int default 0 comment '执行次数',
  `exec_pass_count` int default 0 comment '通过次数',
  `ai_case_count` int default 0 comment 'AI用例数',
  primary key (`design_uid`),
  constraint fk_stat_design foreign key (`design_uid`) references `test_designs`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例设计统计信息';

-- 设计需求（Story）表
drop table if exists `test_design_stories`;
create table `test_design_stories` (
  `uid` varchar(32) not null comment 'Story唯一ID',
  `design_uid` varchar(32) not null comment '所属设计ID',
  `issue_uid` varchar(32) not null comment '需求工作项ID',
  `workspace_uid` varchar(32) not null comment '工作区ID',
  `issue_url` varchar(255) default '' comment '需求链接',
  `type` varchar(32) not null comment '类型',
  `source` varchar(32) default '' comment '来源',
  `issue_name` varchar(255) not null comment '需求名称',
  `is_deleted` tinyint(1) default 0 comment '是否已删除',
  primary key (`uid`),
  key idx_design (`design_uid`),
  constraint fk_story_design foreign key (`design_uid`) references `test_designs`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例设计关联需求';

-- 设计审核表
drop table if exists `test_design_reviews`;
create table `test_design_reviews` (
  `uid` varchar(32) not null comment '审核唯一ID',
  `design_uid` varchar(32) not null comment '关联设计ID',
  `state` varchar(32) not null comment '审核状态',
  `created_by` varchar(32) not null comment '创建人',
  `updated_by` varchar(32) not null comment '更新人',
  `created_at` datetime not null comment '创建时间',
  `updated_at` datetime not null comment '更新时间',
  primary key (`uid`),
  key idx_design (`design_uid`),
  constraint fk_review_design foreign key (`design_uid`) references `test_designs`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试用例设计审核信息';

-- 测试数据
insert into `test_designs` (`uid`, `namespace`, `parent_uid`, `name`, `description`, `thumbnail`, `source`, `use_ai`, `template_uid`, `created_by`, `updated_by`, `created_at`, `updated_at`) values
('design-F6zhMhgk8T', '33142026', '', '啊啊啊', '', '', 'MANUALLY', 0, '', '412178979', '412178979', '2025-07-15 14:29:14', '2025-07-15 14:40:47');

insert into `test_design_stats` (`design_uid`, `custom_count`, `story_count`, `scene_count`, `test_point_count`, `case_count`, `feature_count`, `bug_count`, `total_count`, `node_uid`, `exec_count`, `exec_pass_count`, `ai_case_count`) values
('design-F6zhMhgk8T', 0, 2, 0, 1, 3, 1, 0, 7, '', 0, 0, 0);

insert into `test_design_stories` (`uid`, `design_uid`, `issue_uid`, `workspace_uid`, `issue_url`, `type`, `source`, `issue_name`, `is_deleted`) values
('story-xjnEttA8ee', 'design-F6zhMhgk8T', '1133142026001000041', '33142026', '', 'STORY', 'TAPD', '【示例】邮箱密码登陆', 0),
('story-KwHtdZZUzI', 'design-F6zhMhgk8T', '1133142026001000040', '33142026', '', 'STORY', 'TAPD', '【示例】权限管理', 0);

insert into `test_design_reviews` (`uid`, `design_uid`, `state`, `created_by`, `updated_by`, `created_at`, `updated_at`) values
('design_review-oWawDgS3da', 'design-F6zhMhgk8T', 'UNREVIEW', '412178979', '412178979', '2025-07-15 14:29:15', '2025-07-15 14:29:15');




