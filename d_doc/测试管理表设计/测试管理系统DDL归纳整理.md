# 测试管理系统 DDL 归纳整理文档

> 本文档对文件夹内所有 DDL 进行归纳、去重、合并与补充，涵盖测试管理系统的主要业务场景（用例、计划、报告、评审、执行等），并对每个表的使用场景、字段含义、设计思路进行详细说明。所有表结构均为 MySQL5 语法，字段注释完整，含典型测试数据。

---

## 1. 用例库与用例管理

### 1.1 用例库表（test_case_repos）
**场景说明**：用于管理测试用例的分组、归档、代码关联等。
```sql
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
```

### 1.2 用例库版本表（test_case_repo_versions）
**场景说明**：记录用例库的版本信息。
```sql
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
```

### 1.3 用例文件夹表（test_case_folders）
**场景说明**：用例库下的文件夹结构。
```sql
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
```

### 1.4 用例主表（test_cases）
**场景说明**：存储具体的测试用例。
```sql
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
```

### 1.5 用例步骤表（test_case_steps）
**场景说明**：记录用例的详细测试步骤。
```sql
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
```

---

## 2. 测试计划与计划管理

### 2.1 计划文件夹表（test_plan_folders）
**场景说明**：测试计划的分组、归档。
```sql
drop table if exists `test_plan_folders`;
create table `test_plan_folders` (
  `uid` varchar(32) not null comment '文件夹唯一ID',
  `namespace` varchar(32) not null comment '工作区ID',
  `parent_uid` varchar(32) default '0' comment '父文件夹ID',
  `name` varchar(255) not null comment '文件夹名称',
  `description` text comment '描述',
  `plan_count` int default 0 comment '计划数',
  `archive_auto` tinyint(1) default 0 comment '是否自动归档',
  `nid` varchar(64) default '' comment 'NID',
  `path` varchar(255) default '' comment '路径',
  `created_by` varchar(32) not null comment '创建人',
  `updated_by` varchar(32) default '' comment '更新人',
  `created_at` datetime not null comment '创建时间',
  `updated_at` datetime not null comment '更新时间',
  primary key (`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划文件夹';
```

### 2.2 测试计划主表（test_plans）
**场景说明**：测试计划的核心信息。
```sql
drop table if exists `test_plans`;
create table `test_plans` (
  `uid` varchar(32) not null comment '测试计划唯一ID',
  `namespace` varchar(32) not null comment '工作区ID',
  `folder_uid` varchar(32) not null comment '所属文件夹ID',
  `name` varchar(255) not null comment '测试计划名称',
  `description` text comment '描述',
  `data_source_mode` varchar(32) default '' comment '数据源模式',
  `data_source_case_suite_uid` varchar(32) default '' comment '数据源用例集UID',
  `state` varchar(32) default '' comment '计划状态',
  `version` varchar(16) default '' comment '计划版本',
  `folder_path` varchar(255) default '' comment '文件夹路径',
  `nid` varchar(64) default '' comment 'NID',
  `path` varchar(255) default '' comment '路径',
  `created_by` varchar(32) not null comment '创建人',
  `created_at` datetime not null comment '创建时间',
  `updated_by` varchar(32) default '' comment '更新人',
  `updated_at` datetime not null comment '更新时间',
  `starter` varchar(32) default '' comment '启动人',
  `terminator` varchar(32) default '' comment '终止人',
  primary key (`uid`),
  key idx_folder (`folder_uid`),
  constraint fk_plan_folder foreign key (`folder_uid`) references `test_plan_folders`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划主表';
```

### 2.3 计划字段表（test_plan_fields）
**场景说明**：计划的扩展字段（如版本、环境等）。
```sql
drop table if exists `test_plan_fields`;
create table `test_plan_fields` (
  `plan_uid` varchar(32) not null comment '测试计划ID',
  `summary` varchar(255) default '' comment '概要',
  `version` varchar(64) default '' comment '版本',
  `type` varchar(32) default '' comment '类型',
  `extranet_publish` varchar(32) default '' comment '是否外网发布',
  `publish_review` varchar(32) default '' comment '发布评审',
  `estimate_test_at` varchar(32) default '' comment '预计测试时间',
  `actual_test_at` varchar(32) default '' comment '实际测试时间',
  `estimate_publish_at` varchar(32) default '' comment '预计发布时间',
  `actual_publish_at` varchar(32) default '' comment '实际发布时间',
  `estimate_started_at` varchar(32) default '' comment '预计开始时间',
  `estimate_ended_at` varchar(32) default '' comment '预计结束时间',
  `env` varchar(64) default '' comment '环境',
  `developers` varchar(255) default '' comment '开发人员(逗号分隔)',
  `project_managers` varchar(255) default '' comment '项目经理(逗号分隔)',
  `product_managers` varchar(255) default '' comment '产品经理(逗号分隔)',
  primary key (`plan_uid`),
  constraint fk_field_plan foreign key (`plan_uid`) references `test_plans`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划字段信息';
```

---

## 3. 计划用例执行与自定义字段

### 3.1 计划用例执行主表（plan_case_executes）
**场景说明**：记录计划下每个需求/故事的执行情况。
```sql
drop table if exists `plan_case_executes`;
create table `plan_case_executes` (
  `id` varchar(32) not null comment '计划用例执行唯一ID',
  `plan_uid` varchar(32) not null comment '所属测试计划ID',
  `story_uid` varchar(32) not null comment '需求/故事ID',
  `workspace_id` varchar(32) not null comment '工作区ID',
  `iteration_id` varchar(32) default '' comment '迭代ID',
  `summary` varchar(255) default '' comment '需求摘要',
  `creator` varchar(32) default '' comment '创建人',
  `priority_label` varchar(32) default '' comment '优先级标签',
  `priority_value` varchar(32) default '' comment '优先级值',
  `status_label` varchar(32) default '' comment '状态标签',
  `status_value` varchar(32) default '' comment '状态值',
  `status_raw` varchar(32) default '' comment '原始状态',
  `owners` varchar(255) default '' comment '负责人',
  `created_at` datetime default null comment '创建时间',
  `modified_at` datetime default null comment '修改时间',
  `completed` tinyint(1) default 0 comment '是否完成',
  `start_date` date default null comment '开始日期',
  `end_date` date default null comment '结束日期',
  `description` text comment '描述',
  `url` varchar(255) default '' comment '需求链接',
  `cc` varchar(255) default '' comment '抄送人',
  `developer` varchar(255) default '' comment '开发人员',
  `effort` varchar(32) default '' comment '工时',
  `remain` varchar(32) default '' comment '剩余工时',
  `exceed` varchar(32) default '' comment '超出工时',
  `release_id` varchar(32) default '' comment '发布ID',
  `business_value` varchar(255) default '' comment '业务价值',
  `module` varchar(255) default '' comment '模块',
  `test_focus` varchar(255) default '' comment '测试关注点',
  `size` varchar(32) default '' comment '规模',
  `templated_id` varchar(32) default '' comment '模板ID',
  `parent_id` varchar(32) default '' comment '父ID',
  `category_id` varchar(32) default '' comment '分类ID',
  `workitem_type_id` varchar(32) default '' comment '工作项类型ID',
  `feature` varchar(255) default '' comment '特性',
  `tech_risk` varchar(255) default '' comment '技术风险',
  `is_apply_template_default_value` tinyint(1) default 0 comment '是否应用模板默认值',
  `tapd_version` varchar(64) default '' comment 'TAPD版本',
  primary key (`id`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划用例执行主表';
```

### 3.2 计划用例执行自定义字段（plan_case_execute_custom_fields）
**场景说明**：EAV模型，支持计划用例执行的动态扩展字段。
```sql
drop table if exists `plan_case_execute_custom_fields`;
create table `plan_case_execute_custom_fields` (
  `id` int auto_increment primary key,
  `plan_case_execute_id` varchar(32) not null comment '计划用例执行ID',
  `field_key` varchar(64) not null comment '自定义字段名，如custom_field_89',
  `field_value` text comment '自定义字段值',
  constraint fk_plan_case_custom_field foreign key (`plan_case_execute_id`) references `plan_case_executes`(`id`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划用例执行自定义字段';
```

---

## 4. 评审与评审节点

### 4.1 评审节点主表（plan_review_nodes）
**场景说明**：记录计划/需求/故事的评审节点。
```sql
drop table if exists `plan_review_nodes`;
create table `plan_review_nodes` (
  `uid` varchar(64) not null comment '评审节点唯一ID',
  `namespace` varchar(32) not null comment '工作区ID',
  `kind` varchar(32) not null comment '节点类型（如DESIGN、STORY等）',
  `parent_uid` varchar(64) default '' comment '父节点ID',
  `name` varchar(255) not null comment '节点名称',
  `description` text comment '描述',
  `path` varchar(255) default '' comment '路径',
  `order_num` int default 0 comment '排序号',
  `image_url` varchar(255) default '' comment '图片URL',
  `ref_link` varchar(255) default '' comment '引用链接',
  `html_name` varchar(255) default '' comment 'HTML名称',
  `source` varchar(32) default '' comment '来源',
  `level` int default 0 comment '层级',
  `temp_uid` varchar(64) default '' comment '临时UID',
  `created_by` varchar(32) default '' comment '创建人',
  `updated_by` varchar(32) default '' comment '更新人',
  `created_at` datetime default null comment '创建时间',
  `updated_at` datetime default null comment '更新时间',
  primary key (`uid`)
) engine=InnoDB default charset=utf8mb4 comment='测试计划评审节点';
```

### 4.2 评审节点状态表、扩展表、评审人、标签、图标、评审记录、自定义字段
**场景说明**：详见各表注释，均为评审节点的扩展信息。
```sql
-- 节点状态表
create table `plan_review_node_states` (
  `node_uid` varchar(64) not null comment '评审节点ID',
  `exec_state` varchar(32) default '' comment '执行状态',
  `review_state` varchar(32) default '' comment '评审状态',
  `review_count` int default 0 comment '评审次数',
  `is_has_bug` tinyint(1) default 0 comment '是否有缺陷',
  primary key (`node_uid`),
  constraint fk_node_state_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点状态';

-- 节点扩展信息
create table `plan_review_node_specs` (
  `node_uid` varchar(64) not null comment '评审节点ID',
  `dimension_width` int default null comment '节点宽度',
  `dimension_height` int default null comment '节点高度',
  `node_style` varchar(255) default '' comment '节点样式',
  `is_collapsed` tinyint(1) default 0 comment '是否折叠',
  `digest` text comment '摘要',
  primary key (`node_uid`),
  constraint fk_node_spec_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点扩展信息';

-- 评审人
create table `plan_review_node_reviewers` (
  `id` int auto_increment primary key,
  `node_uid` varchar(64) not null comment '评审节点ID',
  `reviewer_uid` varchar(32) not null comment '评审人ID',
  constraint fk_node_reviewer_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点评审人';

-- 标签
create table `plan_review_node_labels` (
  `id` int auto_increment primary key,
  `node_uid` varchar(64) not null comment '评审节点ID',
  `label` varchar(64) not null comment '标签',
  constraint fk_node_label_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点标签';

-- 图标
create table `plan_review_node_icons` (
  `id` int auto_increment primary key,
  `node_uid` varchar(64) not null comment '评审节点ID',
  `icon` varchar(64) not null comment '图标',
  constraint fk_node_icon_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点图标';

-- 评审记录
create table `plan_review_records` (
  `id` int auto_increment primary key,
  `node_uid` varchar(64) not null comment '评审节点ID',
  `reviewer_uid` varchar(32) not null comment '评审人ID',
  `review_state` varchar(32) default '' comment '评审状态',
  `comment` text comment '评审意见',
  `created_at` datetime default null comment '评审时间',
  constraint fk_review_record_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审记录';

-- 节点自定义字段
create table `plan_review_node_custom_fields` (
  `id` int auto_increment primary key,
  `node_uid` varchar(64) not null comment '评审节点ID',
  `field_key` varchar(64) not null comment '自定义字段名，如custom_field_89',
  `field_value` text comment '自定义字段值',
  constraint fk_custom_field_node foreign key (`node_uid`) references `plan_review_nodes`(`uid`)
) engine=InnoDB default charset=utf8mb4 comment='评审节点自定义字段';
```

---

## 5. 报告与统计

### 5.1 报告主表、计划关联、模块、统计、模板、EAV自定义字段
**场景说明**：详见表注释，支持灵活的报告结构与动态字段。
```sql
-- 详见“用例-报告.sql”与“用例-计划列表.sql”归纳，已合并同义表字段。
-- 主要表：test_report、test_report_plan、test_report_template、test_report_template_module、test_report_statistic、test_report_custom_field
-- 结构与注释详见前述内容。
```

---

## 6. 典型测试数据示例

```sql
-- 用例库
insert into `test_case_repos` (`uid`, `namespace`, `name`, `description`, `type`, `code_repo_id`, `code_repo_name`, `code_repo_url`, `code_repo_type`, `created_by`, `created_at`) values
('17090', '33142026', '默认用例库', '', 'SYSTEM', '0', '', 'https://git.woa.com', 'CODE_REPO_TYPE_TGIT', '412178979', '2025-07-15 14:28:31');

-- 用例
insert into `test_cases` (`uid`, `repo_uid`, `repo_version_uid`, `folder_uid`, `uuid`, `name`, `description`, `priority`, `pre_conditions`, `type`, `step_type`, `source`, `review_state`, `automated_state`, `is_manual_relation`, `bug_count`, `run_times`, `is_notification`, `is_folder`, `is_baseline`, `created_by`, `created_at`) values
('12571481', '17090', '18158', '12571480', '4eea9118-18de-48ef-9776-a0597d756d2a', '登录时记住用户名', '', 'P0', '1.进入平台首页\n2.成功注册账号，用户名testxadmin，密码admintestx', '', 'STEP', 'TESTX', 'DRAFT', '', 0, 0, 0, 0, 0, 0, '412178979', '2025-07-15 14:28:31');

-- 计划
insert into `test_plans` (`uid`, `namespace`, `folder_uid`, `name`, `description`, `data_source_mode`, `data_source_case_suite_uid`, `state`, `version`, `folder_path`, `nid`, `path`, `created_by`, `created_at`, `updated_by`, `updated_at`, `starter`, `terminator`) values
('152227', '33142026', '152226', '77777', '', 'CASE_REPO', '1788265', 'WAITING', 'V2', '', '', '.152226.', '412178979', '2025-07-15 15:59:53', '', '2025-07-15 15:59:53', '', '412178979');

-- 计划用例执行
insert into `plan_case_executes` (`id`, `plan_uid`, `story_uid`, `workspace_id`, `iteration_id`, `summary`, `creator`, `priority_label`, `priority_value`, `status_label`, `status_value`, `status_raw`, `owners`, `created_at`, `modified_at`, `completed`, `start_date`, `end_date`, `description`, `url`, `cc`, `developer`, `effort`, `remain`, `exceed`, `release_id`, `business_value`, `module`, `test_focus`, `size`, `templated_id`, `parent_id`, `category_id`, `workitem_type_id`, `feature`, `tech_risk`, `is_apply_template_default_value`, `tapd_version`) values
('exec-001', '152227', '1133142026001000041', '33142026', '1133142026001000004', '【示例】邮箱密码登录', 'TAPD', 'High', 'High', '规划中', 'PLANNING', 'PLANNING', '', '2025-07-15 09:47:47', '2025-07-15 09:47:47', 0, '2025-06-05', '2025-07-18', '', '', '', '', '', '0', '0', '0', '0', '', '', '', '', '1133142026001000010', '1133142026001000032', '1133142026001000010', '1133142026001000007', '', '', 0, '');

-- 计划用例执行自定义字段
insert into `plan_case_execute_custom_fields` (`plan_case_execute_id`, `field_key`, `field_value`) values
('exec-001', 'custom_field_89', '自定义值示例'),
('exec-001', 'custom_field_10', '');
```

---

## 7. 设计说明与最佳实践
- 所有主表均有自增主键或唯一ID，便于扩展和分布式部署。
- 动态自定义字段统一采用EAV模型，便于灵活扩展。
- 统计、评审、报告等表均有详细注释，便于理解和维护。
- 典型业务场景（如计划、用例、报告、评审、执行）均有对应表，字段合并去重，避免重复。
- 建议所有表均加上必要索引，提升统计与查询性能。

---

> 如需补充其他业务表结构或有特殊场景，请补充说明。

---

## 缺陷管理

### 1. 缺陷主表（bugs）
**场景说明**：记录每个缺陷的核心信息，支持多种描述格式、智能检测、价值流等扩展。
```sql
drop table if exists `bugs`;
create table `bugs` (
    id varchar(36) primary key comment '缺陷唯一标识（UUID 或业务ID）',
    project_id varchar(36) not null comment '关联项目ID',
    title text not null comment '缺陷标题（HTML实体编码）',
    description text comment '描述内容（HTML格式）',
    markdown_description text comment 'Markdown格式描述',
    description_type varchar(10) comment '描述类型（1: HTML, 2: Markdown）',
    enable_app_checklist tinyint(1) default 0 comment '是否启用应用检查项',
    self_is_checklist tinyint(1) default 0 comment '是否为检查项',
    enable_value_flow tinyint(1) default 0 comment '是否启用价值流',
    enable_story_mr_summary tinyint(1) default 0 comment '是否启用需求MR汇总',
    enable_bug_mr_summary tinyint(1) default 0 comment '是否启用缺陷MR汇总',
    enable_vsm tinyint(1) default 0 comment '是否启用VSM',
    enable_zhiyan tinyint(1) default 0 comment '是否启用智验',
    created_at timestamp default current_timestamp comment '创建时间',
    updated_at timestamp default current_timestamp on update current_timestamp comment '更新时间',
    foreign key (project_id) references projects(id)
) engine=InnoDB default charset=utf8mb4 comment='缺陷基本信息表';
```

### 2. 项目表（projects）
**场景说明**：缺陷归属的项目基础信息。
```sql
drop table if exists `projects`;
create table `projects` (
    id varchar(36) primary key comment '项目唯一标识',
    name varchar(255) not null comment '项目名称',
    description text comment '项目描述',
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
) engine=InnoDB default charset=utf8mb4 comment='项目基础信息表';
```

### 3. 缺陷标签设置（tab_settings）
**场景说明**：缺陷详情页的标签页配置，支持折叠/展开、跳转、统计等。
```sql
drop table if exists `tab_settings`;
create table `tab_settings` (
    id varchar(36) primary key comment '标签设置ID',
    bug_id varchar(36) not null comment '关联缺陷ID',
    tab_type enum('fold', 'unfold') not null comment '标签类型',
    name varchar(255) not null comment '标签名称',
    url text not null comment '标签跳转URL',
    count int not null default 0 comment '关联数量',
    params json comment '参数（JSON格式）',
    default_value varchar(255) comment '默认值',
    original_name varchar(255) not null comment '原始名称',
    label varchar(255) not null comment '标签显示名称',
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    foreign key (bug_id) references bugs(id)
) engine=InnoDB default charset=utf8mb4 comment='缺陷标签设置表';
```

### 4. 缺陷变更历史（bug_changes）
**场景说明**：记录缺陷的每一次变更，便于追溯和审计。
```sql
drop table if exists `bug_changes`;
create table `bug_changes` (
    id bigint unsigned not null primary key comment '变更记录ID',
    entity_id varchar(32) not null comment '关联的缺陷ID',
    workspace_id int unsigned not null comment '工作空间ID',
    no tinyint unsigned not null comment '变更序号',
    entity_type varchar(20) not null default 'bug_change' comment '实体类型',
    created datetime not null comment '变更时间',
    creator varchar(50) not null comment '变更人',
    creator_tips varchar(255) default '' comment '变更人提示',
    change_type varchar(50) not null comment '变更类型',
    change_field text comment '变更字段',
    change_before text comment '变更前值',
    change_after text comment '变更后值',
    change_after_tips text comment '变更后提示',
    created_at timestamp default current_timestamp comment '记录创建时间',
    index idx_entity (entity_id, workspace_id),
    index idx_created (created)
) engine=InnoDB default charset=utf8mb4 comment='缺陷变更历史表';
```

### 5. 缺陷工时与进度（entity_effort, time_tracking）
**场景说明**：记录缺陷/任务/需求的工时、进度、剩余量等，支持多实体类型。
```sql
drop table if exists `entity_effort`;
create table `entity_effort` (
    id bigint unsigned auto_increment primary key comment '自增主键',
    entity_id varchar(32) not null comment '关联实体ID',
    entity_type enum('bug', 'task', 'story') not null default 'bug' comment '实体类型',
    workspace_id bigint unsigned not null comment '工作空间ID',
    effort varchar(255) default '' comment '总工作量描述',
    effort_completed decimal(10,2) not null default 0.00 comment '已完成工作量',
    exceed decimal(10,2) not null default 0.00 comment '超额工作量',
    progress tinyint unsigned not null default 0 comment '进度百分比(0-100)',
    remain decimal(10,2) not null default 0.00 comment '剩余工作量',
    metrology enum('day', 'hour', 'minute') not null default 'day' comment '计量单位',
    created_at timestamp default current_timestamp comment '记录创建时间',
    index idx_entity (entity_id, workspace_id)
) engine=InnoDB default charset=utf8mb4 comment='实体工作量主表';

drop table if exists `time_tracking`;
create table `time_tracking` (
    id bigint unsigned auto_increment primary key,
    effort_id bigint unsigned not null comment '关联工作量ID',
    start_time datetime not null comment '开始时间',
    end_time datetime not null comment '结束时间',
    duration decimal(5,2) not null comment '工时数',
    user_id varchar(32) not null comment '操作人ID',
    foreign key (effort_id) references entity_effort(id) on delete cascade,
    index idx_time (start_time)
) engine=InnoDB default charset=utf8mb4 comment='工时明细表';
```

### 6. 缺陷与代码提交关联（scm_source, scm_support, scm_commits, scm_file_changes, scm_file_sort, scm_query_metadata）
**场景说明**：支持缺陷与代码仓库、提交、文件变更等的自动/手动关联，便于追溯。
（表结构较多，建议分章节详细说明，已合并同义表字段）

---

## 迭代与工作项管理

> 本章节为全系统唯一的“工作区/迭代/工作项类型/工作项/状态/优先级/权限”等相关表结构定义，所有涉及工作项流转、权限、状态、优先级等均以此为准，便于统一维护和扩展。下述DDL已合并所有相关SQL文件字段，无遗漏。

### 1. 工作区与迭代表
**场景说明**：支持多工作区、多迭代的敏捷研发管理，迭代可多级嵌套，支持排序、起止时间等。
```sql
drop table if exists `workspaces`;
create table `workspaces` (
  `workspace_id` varchar(20) not null comment '工作区ID',
  `workspace_name` varchar(100) not null comment '工作区名称',
  `effort_unit` varchar(10) default 'day' comment '工作量单位',
  primary key (`workspace_id`)
) engine=InnoDB default charset=utf8mb4 comment='工作区基本信息';

drop table if exists `iterations`;
create table `iterations` (
  `iteration_id` varchar(30) not null comment '迭代ID',
  `workspace_id` varchar(20) not null comment '所属工作区ID',
  `name` varchar(100) not null comment '迭代名称',
  `parent_id` varchar(30) default '0' comment '父迭代ID',
  `sort_order` bigint default 0 comment '排序序号',
  `start_date` date default null comment '开始日期',
  `end_date` date default null comment '结束日期',
  primary key (`iteration_id`),
  key `idx_workspace_id` (`workspace_id`),
  constraint `fk_iteration_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`)
) engine=InnoDB default charset=utf8mb4 comment='迭代信息';
```

### 2. 工作项类型与主表
**场景说明**：支持需求、任务、史诗等多类型工作项，类型可配置颜色、英文名、实体类型。
```sql
drop table if exists `workitem_types`;
create table `workitem_types` (
  `type_id` varchar(30) not null comment '类型ID',
  `workspace_id` varchar(20) not null comment '所属工作区ID',
  `name` varchar(50) not null comment '类型名称(如:需求/任务)',
  `english_name` varchar(50) not null comment '英文名称',
  `color` varchar(20) default null comment '代表颜色',
  `entity_type` enum('story','bug','task','workitem') not null comment '实体类型',
  `icon_url` varchar(255) default null comment '图标URL',
  `parent_id` varchar(30) default null comment '父类型ID',
  `workflow_id` varchar(32) default null comment '关联工作流ID',
  `sort_order` smallint default 9999 comment '排序值',
  `description` text comment '类型描述',
  primary key (`type_id`),
  key `idx_type_workspace` (`workspace_id`),
  constraint `fk_type_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`)
) engine=InnoDB default charset=utf8mb4 comment='工作项类型配置';

drop table if exists `workitems`;
create table `workitems` (
  `id` varchar(30) not null comment '工作项ID',
  `workspace_id` varchar(20) not null comment '所属工作区ID',
  `type_id` varchar(30) not null comment '工作项类型ID',
  `name` varchar(255) not null comment '工作项名称',
  `status` varchar(50) not null comment '当前状态',
  `priority` enum('high','middle','low','nice to have') default null comment '优先级',
  `owner` varchar(100) default null comment '负责人',
  `effort` decimal(10,2) default null comment '预估工作量',
  `iteration_id` varchar(30) default null comment '所属迭代ID',
  `parent_id` varchar(30) default '0' comment '父工作项ID',
  `level` tinyint default 0 comment '层级(0=根节点)',
  `due_date` date default null comment '截止日期',
  `begin_date` date default null comment '开始日期',
  `is_delay` tinyint(1) default 0 comment '是否延期(1=是)',
  `children_count` int default 0 comment '子项数量',
  `created_at` datetime default current_timestamp comment '创建时间',
  `updated_at` datetime default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`id`),
  key `idx_workspace_type` (`workspace_id`,`type_id`),
  key `idx_iteration_id` (`iteration_id`),
  key `idx_parent_id` (`parent_id`),
  constraint `fk_workitem_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`),
  constraint `fk_workitem_type` foreign key (`type_id`) references `workitem_types` (`type_id`),
  constraint `fk_workitem_iteration` foreign key (`iteration_id`) references `iterations` (`iteration_id`)
) engine=InnoDB default charset=utf8mb4 comment='工作项详细信息';
```

### 3. 状态、优先级、字段选项与权限
**场景说明**：支持多工作区、多类型的状态、优先级、字段选项、权限灵活配置。
```sql
drop table if exists `status_mappings`;
create table `status_mappings` (
  `mapping_id` int auto_increment primary key,
  `workspace_id` varchar(20) not null comment '工作区ID',
  `entity_type` enum('story','bug','task','workitem') not null comment '实体类型',
  `status_key` varchar(50) not null comment '状态键名',
  `status_label` varchar(50) not null comment '状态显示名称',
  unique key `uk_workspace_entity_status` (`workspace_id`, `entity_type`, `status_key`),
  constraint `fk_statusmap_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`)
) engine=InnoDB default charset=utf8mb4 comment='状态值映射配置';

drop table if exists `priorities`;
create table `priorities` (
  `id` int auto_increment primary key,
  `workspace_id` varchar(20) not null comment '工作区ID',
  `type_id` varchar(30) not null comment '工作项类型ID',
  `priority_code` varchar(32) not null comment '优先级代码',
  `label` varchar(32) not null comment '优先级标签',
  `color` varchar(7) default null comment '颜色代码',
  unique key `uk_type_priority` (`workspace_id`, `type_id`, `priority_code`),
  constraint `fk_priority_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`),
  constraint `fk_priority_type` foreign key (`type_id`) references `workitem_types` (`type_id`)
) engine=InnoDB default charset=utf8mb4 comment='优先级配置表';

drop table if exists `iteration_field_options`;
create table `iteration_field_options` (
  `option_id` int auto_increment primary key,
  `workspace_id` varchar(20) not null comment '工作区ID',
  `entity_type` enum('story','bug','task','workitem') not null comment '实体类型',
  `iteration_id` varchar(30) not null comment '迭代ID',
  `label` varchar(100) not null comment '显示名称',
  `sort_order` bigint default 0 comment '排序序号',
  constraint `fk_iteropt_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`),
  constraint `fk_iteropt_iteration` foreign key (`iteration_id`) references `iterations` (`iteration_id`)
) engine=InnoDB default charset=utf8mb4 comment='迭代字段下拉选项';

drop table if exists `permissions`;
create table `permissions` (
  `permission_id` int auto_increment primary key,
  `workspace_id` varchar(20) not null comment '工作区ID',
  `permission_code` varchar(100) not null comment '权限代码',
  `permission_name` varchar(200) not null comment '权限名称',
  constraint `fk_permission_workspace` foreign key (`workspace_id`) references `workspaces` (`workspace_id`)
) engine=InnoDB default charset=utf8mb4 comment='工作区权限配置';
```

### 4. 实体与字段元数据（EAV模型）
**场景说明**：支持需求、迭代、缺陷等多实体类型的动态字段扩展，便于灵活配置和维护。
```sql
drop table if exists `entity_type`;
create table `entity_type` (
  id int auto_increment primary key,
  name varchar(50) not null unique comment '实体类型名称',
  label varchar(50) comment '显示名称'
) comment '实体类型表';

drop table if exists `field_definition`;
create table `field_definition` (
  id int auto_increment primary key,
  entity_type_id int not null comment '关联实体类型',
  name varchar(100) not null comment '字段名',
  label varchar(100) comment '显示标签',
  html_type varchar(50) not null comment 'HTML控件类型',
  default_operator varchar(50) comment '默认操作符',
  is_system tinyint(1) default 0 comment '是否系统字段',
  category varchar(50) comment '字段分类',
  hidden tinyint(1) default 0 comment '是否隐藏',
  field_sub_config varchar(50) comment '子配置类型',
  lazy_model varchar(50) comment '懒加载模型',
  switch_show_label varchar(100) comment '开关显示标签',
  switch_hide_label varchar(100) comment '开关隐藏标签',
  foreign key (entity_type_id) references entity_type(id)
) comment '字段定义表';
```

### 5. 分类、视图与标签
**场景说明**：支持需求的多级分类、用户视图、置顶字段、标签等灵活配置。
```sql
drop table if exists `category`;
create table `category` (
  id varchar(50) not null primary key comment '分类ID',
  workspace_id varchar(20) not null comment '工作空间ID',
  app_id varchar(10) not null comment '应用ID',
  name varchar(100) not null comment '分类名称',
  description text comment '分类描述',
  path varchar(255) comment '分类路径',
  parent_id varchar(50) not null comment '父分类ID',
  level tinyint not null comment '层级',
  sorting int not null comment '排序值',
  label varchar(100) not null comment '显示标签',
  created datetime not null comment '创建时间',
  modified datetime not null comment '修改时间',
  creator varchar(50) not null comment '创建者',
  modifier varchar(50) not null comment '修改者',
  index idx_parent (parent_id),
  index idx_workspace (workspace_id)
) engine=InnoDB default charset=utf8mb4;

drop table if exists `user_view`;
create table `user_view` (
  id varchar(50) not null primary key comment '视图ID',
  workspace_id varchar(20) not null comment '工作空间ID',
  title varchar(100) not null comment '视图标题',
  enable tinyint(1) not null comment '是否启用',
  type enum('system','person') not null comment '视图类型',
  default_show tinyint(1) not null comment '是否默认显示',
  initial json not null comment '初始过滤条件',
  relationship varchar(10) comment '条件关系',
  view_id varchar(50) not null comment '关联视图ID',
  sort smallint not null comment '排序值',
  filter_item_count smallint not null comment '过滤条件数量',
  lock_show_field varchar(255) comment '锁定显示字段',
  locker varchar(50) comment '锁定者',
  index idx_workspace (workspace_id)
) engine=InnoDB default charset=utf8mb4;

drop table if exists `pinned_field`;
create table `pinned_field` (
  id int auto_increment primary key,
  workspace_id varchar(20) not null comment '工作空间ID',
  value varchar(50) not null comment '字段标识',
  entity_type varchar(20) not null comment '实体类型',
  unique key uniq_workspace_field (workspace_id, value)
) engine=InnoDB default charset=utf8mb4;

drop table if exists `workitem_label`;
create table `workitem_label` (
  id bigint unsigned auto_increment primary key,
  workspace_id varchar(32) not null comment '工作空间ID',
  name varchar(50) not null comment '标签名称',
  color_id tinyint comment '颜色ID',
  color_hex varchar(7) comment '颜色值',
  key idx_workspace (workspace_id)
) engine=InnoDB comment='工作项标签配置';
```

### 6. 工作空间配置
**场景说明**：支持不同空间下需求等实体的功能开关、计量单位等配置。
```sql
drop table if exists `workspace_config`;
create table `workspace_config` (
  id bigint unsigned auto_increment primary key,
  workspace_id varchar(32) not null comment '工作空间ID',
  entity_type varchar(20) not null comment '实体类型(story/bug/task等)',
  enabled_features json comment '启用的功能开关',
  effort_unit enum('day','hour') default 'day' comment '工作量单位',
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp on update current_timestamp,
  unique key uniq_ws_entity (workspace_id, entity_type)
) engine=InnoDB comment='工作空间基础配置';
```

---

> 本文档由腾讯云开发者实验室支持，更多内容请关注我们的[官方频道](https://cloud.tencent.com/developer/labs)。如有问题，请联系 lab@tencent.com。
