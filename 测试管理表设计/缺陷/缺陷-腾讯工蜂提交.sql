请求网址
https://www.tapd.cn/api/devops/source_code/get_scm_detail?workspace_id=60498179&scm_type=tgit
请求方法
GET

workspace_id=60498179&scm_type=tgit




{
    "data": {
        "code": "tgit",
        "name": "\u817e\u8baf\u5de5\u8702",
        "desc": "desc",
        "sw_name": "sw_tgit_source",
        "enable": "1",
        "support_config_system": "1",
        "support_branch": "",
        "support": {
            "commit": "1",
            "api": "",
            "vsm": "",
            "branch": "",
            "mr": "",
            "cr": "",
            "create_cr_from_commit": "",
            "repo": "",
            "sub_story_commit": ""
        },
        "instances": []
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749731569"
}

-- 主表：存储核心数据
CREATE TABLE scm_source (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    code VARCHAR(50) NOT NULL COMMENT '代码类型',
    name VARCHAR(100) NOT NULL COMMENT '名称',
    desc TEXT COMMENT '描述',
    sw_name VARCHAR(100) NOT NULL COMMENT '软件名称',
    enable TINYINT(1) DEFAULT 0 COMMENT '是否启用',
    support_config_system TINYINT(1) DEFAULT 0 COMMENT '支持配置系统',
    support_branch VARCHAR(255) COMMENT '支持的分支',
    meta_code VARCHAR(10) COMMENT '元状态码',
    meta_message VARCHAR(255) COMMENT '元信息消息',
    timestamp BIGINT NOT NULL COMMENT '时间戳',
    
    UNIQUE KEY idx_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SCM源数据主表';

-- 关系表：存储support键值对
CREATE TABLE scm_support (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_id INT NOT NULL COMMENT '关联主表ID',
    support_key VARCHAR(50) NOT NULL COMMENT '支持项键名',
    support_value VARCHAR(255) NOT NULL COMMENT '支持项值',
    
    FOREIGN KEY (source_id) REFERENCES scm_source(id) ON DELETE CASCADE,
    INDEX idx_key (support_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SCM支持项关系表';



INSERT INTO scm_source (
    code, name, desc, sw_name, enable, 
    support_config_system, support_branch,
    meta_code, meta_message, timestamp
) VALUES (
    'tgit',
    '腾讯工蜂',
    'desc',
    'sw_tgit_source',
    1,
    1,
    '',
    '0',
    'success',
    1749731569
);

SET @source_id = LAST_INSERT_ID();


INSERT INTO scm_support (source_id, support_key, support_value)
VALUES 
    (@source_id, 'commit', '1'),
    (@source_id, 'api', ''),
    (@source_id, 'vsm', ''),
    (@source_id, 'branch', ''),
    (@source_id, 'mr', ''),
    (@source_id, 'cr', ''),
    (@source_id, 'create_cr_from_commit', ''),
    (@source_id, 'repo', ''),
    (@source_id, 'sub_story_commit', '');
















请求网址
https://www.tapd.cn/api/devops/source_code/get_related_commits?workspace_id=60498179&entity_id=1160498179001000009&entity_type=bug&related_id=-1&page=1&per_page=10&scm_type=tgit
GET


workspace_id=60498179&entity_id=1160498179001000009&entity_type=bug&related_id=-1&page=1&per_page=10&scm_type=tgit


{
    "data": {
        "commits": [
            {
                "id": "1160498179001000001",
                "user_name": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                "user_id": "328519613",
                "hook_user_name": "lm2025",
                "commit_id": "a56b21564c6cbe257e824a2b702d70d14e5d87b6",
                "workspace_id": "60498179",
                "git_app_id": "0",
                "message": "--story=1000134 --user=\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f 12121212121 https:\/\/www.tapd.cn\/60498179\/s\/1000078",
                "path": "https:\/\/git.code.tencent.com\/lm2025\/test\/commit\/a56b21564c6cbe257e824a2b702d70d14e5d87b6",
                "web_url": "http:\/\/git.code.tencent.com\/lm2025\/test",
                "hook_project_name": "test",
                "commit_time": "2025-06-04 16:36:19",
                "created": "2025-06-04 16:36:20",
                "ref": "refs\/heads\/master",
                "ref_status": "0",
                "git_env": "tgit",
                "file_commit": {
                    "A": [],
                    "M": [
                        "README.md"
                    ],
                    "R": []
                },
                "repo_id": "330421",
                "branch_id": "330421\/refs\/heads\/master",
                "hook_project_path": "test",
                "file_sort": {
                    "README.md": "0"
                },
                "commit_id_text": "a56b21564c6cbe257e824a2b702d70d14e5d87b6",
                "scm_instance_name": ""
            }
        ],
        "total_count": "1"
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749731570"
}




-- 提交主表
CREATE TABLE scm_commits (
    id VARCHAR(32) PRIMARY KEY COMMENT '提交记录ID',
    user_name VARCHAR(100) NOT NULL COMMENT '用户名',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    hook_user_name VARCHAR(100) NOT NULL COMMENT '钩子用户名',
    commit_id VARCHAR(40) NOT NULL COMMENT '提交哈希值',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    git_app_id VARCHAR(20) DEFAULT '0' COMMENT 'Git应用ID',
    message TEXT NOT NULL COMMENT '提交消息',
    path VARCHAR(500) NOT NULL COMMENT '提交路径',
    web_url VARCHAR(500) NOT NULL COMMENT '仓库URL',
    hook_project_name VARCHAR(100) NOT NULL COMMENT '项目名称',
    commit_time DATETIME NOT NULL COMMENT '提交时间',
    created DATETIME NOT NULL COMMENT '创建时间',
    ref VARCHAR(100) NOT NULL COMMENT '分支引用',
    ref_status CHAR(1) DEFAULT '0' COMMENT '引用状态',
    git_env VARCHAR(20) NOT NULL COMMENT 'Git环境',
    repo_id VARCHAR(20) NOT NULL COMMENT '仓库ID',
    branch_id VARCHAR(100) NOT NULL COMMENT '分支ID',
    hook_project_path VARCHAR(100) NOT NULL COMMENT '项目路径',
    scm_instance_name VARCHAR(100) DEFAULT '' COMMENT 'SCM实例名',
    INDEX idx_commit_id (commit_id),
    INDEX idx_user (user_id),
    INDEX idx_workspace (workspace_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SCM提交主表';

-- 文件变更表
CREATE TABLE scm_file_changes (
    commit_id VARCHAR(32) NOT NULL COMMENT '提交记录ID',
    change_type ENUM('A', 'M', 'R') NOT NULL COMMENT '变更类型(A=新增/M=修改/R=删除)',
    file_path VARCHAR(255) NOT NULL COMMENT '文件路径',
    FOREIGN KEY (commit_id) REFERENCES scm_commits(id) ON DELETE CASCADE,
    INDEX idx_change_type (change_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件变更详情表';

-- 文件排序表
CREATE TABLE scm_file_sort (
    commit_id VARCHAR(32) NOT NULL COMMENT '提交记录ID',
    file_path VARCHAR(255) NOT NULL COMMENT '文件路径',
    sort_order SMALLINT NOT NULL COMMENT '排序值',
    FOREIGN KEY (commit_id) REFERENCES scm_commits(id) ON DELETE CASCADE,
    INDEX idx_sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件排序表';


INSERT INTO scm_commits (
    id, user_name, user_id, hook_user_name, commit_id, workspace_id,
    message, path, web_url, hook_project_name, commit_time, created,
    ref, git_env, repo_id, branch_id, hook_project_path
) VALUES (
    '1160498179001000001',
    '未命名小程序',
    '328519613',
    'lm2025',
    'a56b21564c6cbe257e824a2b702d70d14e5d87b6',
    '60498179',
    '--story=1000134 --user=未命名小程序 12121212121 https://www.tapd.cn/60498179/s/1000078',
    'https://git.code.tencent.com/lm2025/test/commit/a56b21564c6cbe257e824a2b702d70d14e5d87b6',
    'http://git.code.tencent.com/lm2025/test',
    'test',
    '2025-06-04 16:36:19',
    '2025-06-04 16:36:20',
    'refs/heads/master',
    'tgit',
    '330421',
    '330421/refs/heads/master',
    'test'
);




关联提交 列表查询

请求网址
https://www.tapd.cn/api/devops/source_code/query_commits
请求方法
POST


{"scm_type":"tgit","workspace_id":"60498179","scm_instance_id":0,"entity_type":"bug","entity_id":"1160498179001000009","page":1,"per_page":10,"conditions":{"repo_id":"","branch":"","hook_user_name":"未命名小程序","message":"","date":"","begin_date":"","end_date":"","commit_id":""},"program_id":"","dsc_token":"B14PcWsEU6IsFDYQ"}


{
    "data": {
        "commits": [
            {
                "id": "1160498179001000003",
                "user_name": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                "commit_id": "ab3b553c5d63a0bf6814d0f1de87c0e6d9d25c04",
                "hook_project_name": "test",
                "hook_user_name": "lm2025",
                "commit_time": "2025-06-04 16:43:49",
                "message": "\u7b54\u590d\u963f\u8428\u5fb7\u53d1\u7684\u53d1",
                "workspace_id": "60498179",
                "can_relate": "1",
                "error_tips": ""
            },
            {
                "id": "1160498179001000002",
                "user_name": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                "commit_id": "ab5e37a030cbc3a16f3335af021f50e640d7192c",
                "hook_project_name": "test",
                "hook_user_name": "lm2025",
                "commit_time": "2025-06-04 16:42:36",
                "message": "--story=1000134 --user=\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f 12121212121 https:\/\/www.tapd.cn\/60498179\/s\/1000080",
                "workspace_id": "60498179",
                "can_relate": "1",
                "error_tips": ""
            },
            {
                "id": "1160498179001000001",
                "user_name": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
                "commit_id": "a56b21564c6cbe257e824a2b702d70d14e5d87b6",
                "hook_project_name": "test",
                "hook_user_name": "lm2025",
                "commit_time": "2025-06-04 16:36:19",
                "message": "--story=1000134 --user=\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f 12121212121 https:\/\/www.tapd.cn\/60498179\/s\/1000078",
                "workspace_id": "60498179",
                "can_relate": "",
                "error_tips": "\u4f60\u5df2\u5173\u8054\u5230\u5f53\u524d \u7f3a\u9677"
            }
        ],
        "commit_count": "3",
        "msg": ""
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749732126"
}






根据您的需求，我设计了以下MySQL表结构，包含DDL和DML语句，用于存储提交关联查询数据：

DDL（表结构设计）

-- 提交主表
CREATE TABLE scm_commits (
    id VARCHAR(32) PRIMARY KEY COMMENT '提交记录ID（全局唯一）',
    commit_id VARCHAR(40) NOT NULL COMMENT '提交哈希值',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    hook_project_name VARCHAR(100) NOT NULL COMMENT '项目名称',
    hook_user_name VARCHAR(100) NOT NULL COMMENT '钩子用户名',
    user_name VARCHAR(100) NOT NULL COMMENT '用户名',
    commit_time DATETIME NOT NULL COMMENT '提交时间',
    message TEXT COMMENT '提交消息',
    can_relate TINYINT(1) DEFAULT 0 COMMENT '是否可关联（0否，1是）',
    error_tips VARCHAR(255) COMMENT '错误提示',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    
    INDEX idx_commit_id (commit_id),
    INDEX idx_workspace (workspace_id),
    INDEX idx_commit_time (commit_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='SCM提交主表';

-- 查询元数据表
CREATE TABLE scm_query_metadata (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '查询记录ID',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    entity_type VARCHAR(50) NOT NULL COMMENT '实体类型（如bug）',
    entity_id VARCHAR(32) NOT NULL COMMENT '实体ID',
    commit_count INT NOT NULL COMMENT '提交总数',
    meta_code CHAR(1) NOT NULL COMMENT '元状态码（0成功）',
    meta_message VARCHAR(50) NOT NULL COMMENT '元信息消息',
    query_timestamp BIGINT NOT NULL COMMENT '查询时间戳',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    
    INDEX idx_entity (entity_type, entity_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='查询元数据表';

-- 提交-实体关联表
CREATE TABLE scm_commit_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    commit_id VARCHAR(32) NOT NULL COMMENT '提交ID',
    entity_type VARCHAR(50) NOT NULL COMMENT '实体类型',
    entity_id VARCHAR(32) NOT NULL COMMENT '实体ID',
    
    FOREIGN KEY (commit_id) REFERENCES scm_commits(id) ON DELETE CASCADE,
    INDEX idx_entity_commit (entity_type, entity_id, commit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提交与实体关联表';

设计说明
三表结构：

scm_commits：存储提交核心信息（提交ID、用户、项目等）

scm_query_metadata：存储查询元数据（实体关联、总数、状态码）

scm_commit_entity：维护提交与实体的多对多关系
关键优化：

原子字段：所有字段符合第一范式（1NF），如拆分commit_time为独立DATETIME字段

索引策略：对高频查询字段（commit_id、entity_id、commit_time）建立索引

外键约束：确保提交与实体的关联完整性（ON DELETE CASCADE）

空间优化：使用TINYINT(1)存储布尔值（can_relate）
字段映射：

      erDiagram
       scm_commits ||--o{ scm_commit_entity : "1:N"
       scm_query_metadata }o--|| scm_commit_entity : "关联"
       
       scm_commits {
           VARCHAR(32) id PK
           VARCHAR(40) commit_id
           VARCHAR(20) workspace_id
           VARCHAR(100) hook_project_name
           VARCHAR(100) hook_user_name
           DATETIME commit_time
           TEXT message
           TINYINT can_relate
           VARCHAR(255) error_tips
           TIMESTAMP created_at
scm_query_metadata {

           BIGINT id PK
           VARCHAR(20) workspace_id
           VARCHAR(50) entity_type
           VARCHAR(32) entity_id
           INT commit_count
           CHAR(1) meta_code
           VARCHAR(50) meta_message
           BIGINT query_timestamp
           TIMESTAMP created_at
scm_commit_entity {

           BIGINT id PK
           VARCHAR(32) commit_id FK
           VARCHAR(50) entity_type
           VARCHAR(32) entity_id

DML（示例数据插入）

-- 插入提交记录
INSERT INTO scm_commits (
    id, commit_id, workspace_id, hook_project_name, 
    hook_user_name, user_name, commit_time, message, can_relate, error_tips
) VALUES 
    ('1160498179001000003', 'ab3b553c5d63a0bf6814d0f1de87c0e6d9d25c04', '60498179', 'test', 
     'lm2025', '未命名小程序', '2025-06-04 16:43:49', '回复阿萨德发的发', 1, ''),
    ('1160498179001000002', 'ab5e37a030cbc3a16f3335af021f50e640d7192c', '60498179', 'test',
     'lm2025', '未命名小程序', '2025-06-04 16:42:36', '--story=1000134 --user=未命名小程序 12121212121 https://www.tapd.cn/60498179/s/1000080', 1, ''),
    ('1160498179001000001', 'a56b21564c6cbe257e824a2b702d70d14e5d87b6', '60498179', 'test',
     'lm2025', '未命名小程序', '2025-06-04 16:36:19', '--story=1000134 --user=未命名小程序 12121212121 https://www.tapd.cn/60498179/s/1000078', 0, '你已关联到当前 缺陷');

-- 插入关联关系
INSERT INTO scm_commit_entity (commit_id, entity_type, entity_id) VALUES
    ('1160498179001000003', 'bug', '1160498179001000009'),
    ('1160498179001000002', 'bug', '1160498179001000009'),
    ('1160498179001000001', 'bug', '1160498179001000009');

-- 插入查询元数据
INSERT INTO scm_query_metadata (
    workspace_id, entity_type, entity_id, commit_count, 
    meta_code, meta_message, query_timestamp
) VALUES (
    '60498179', 'bug', '1160498179001000009', 3,
    '0', 'success', 1749732126
);

查询示例
查询某个实体的所有提交：

SELECT c.* 
FROM scm_commits c
JOIN scm_commit_entity e ON c.id = e.commit_id
WHERE e.entity_type = 'bug'
  AND e.entity_id = '1160498179001000009'
ORDER BY c.commit_time DESC;

统计各实体的提交数量：

SELECT 
    e.entity_type,
    e.entity_id,
    COUNT(*) AS commit_count
FROM scm_commit_entity e
GROUP BY e.entity_type, e.entity_id;

查询失败的提交记录：

SELECT * 
FROM scm_commits
WHERE can_relate = 0
  AND error_tips != '';

设计优势
范式兼容：

符合第三范式（3NF），消除传递依赖（如提交信息与实体信息分离）

允许反范式设计：can_relate和error_tips冗余存储提高查询效率
性能优化：

时间类型：commit_time使用DATETIME精确到秒级（比TIMESTAMP范围更大）

索引优化：联合索引加速实体关联查询

大字段分离：message使用TEXT类型单独存储
扩展能力：

支持多实体类型（bug/story/task等）

元数据表记录查询历史，便于审计

时间戳字段支持增量数据同步

此设计已优化存储效率（如使用VARCHAR变长字段）和查询性能（针对性索引），同时通过外键约束保障数据完整性。





