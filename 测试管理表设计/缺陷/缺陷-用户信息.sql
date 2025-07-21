用户信息

Request URL
https://www.tapd.cn/api/basic/roles/get_role_group_batch
Request Method
POST



{"workspace_ids":["60498179"],"need_user_field":true}





-- 工作空间表
CREATE TABLE workspace (
    id VARCHAR(20) PRIMARY KEY COMMENT '工作空间ID',
    name VARCHAR(100) NOT NULL COMMENT '工作空间名称',
    logo_src VARCHAR(255) DEFAULT '' COMMENT 'LOGO路径',
    logo_color VARCHAR(20) DEFAULT '#FFFFFF' COMMENT 'LOGO颜色',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作空间基本信息表';

-- 角色定义表
CREATE TABLE role (
    id VARCHAR(30) PRIMARY KEY COMMENT '角色ID',
    name VARCHAR(50) NOT NULL COMMENT '角色名称',
    description VARCHAR(100) NOT NULL COMMENT '角色描述',
    workspace_id VARCHAR(20) NOT NULL COMMENT '关联工作空间ID',
    role_type VARCHAR(10) NOT NULL COMMENT '角色类型',
    sort INT DEFAULT 9999 COMMENT '排序值',
    leader VARCHAR(50) DEFAULT '' COMMENT '负责人',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    FOREIGN KEY (workspace_id) REFERENCES workspace(id) ON DELETE CASCADE,
    INDEX idx_workspace (workspace_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色定义表';

-- 角色成员表
CREATE TABLE role_member (
    role_id VARCHAR(30) NOT NULL COMMENT '角色ID',
    member_name VARCHAR(100) NOT NULL COMMENT '成员名称',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
    PRIMARY KEY (role_id, member_name, workspace_id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY (workspace_id) REFERENCES workspace(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色成员关系表';

-- 用户字段映射表
CREATE TABLE user_field_mapping (
    workspace_id VARCHAR(20) PRIMARY KEY COMMENT '工作空间ID',
    creator_field VARCHAR(50) NOT NULL COMMENT '创建人字段标签',
    owner_field VARCHAR(50) NOT NULL COMMENT '处理人字段标签',
    developer_field VARCHAR(50) NOT NULL COMMENT '开发人员字段标签',
    cc_field VARCHAR(50) NOT NULL COMMENT '抄送人字段标签',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (workspace_id) REFERENCES workspace(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户字段映射配置表';



-- 插入工作空间数据
INSERT INTO workspace (id, name, logo_src, logo_color) 
VALUES ('60498179', '大型团队敏捷研发', '', '#FE66A8');

-- 插入角色数据
INSERT INTO role (id, name, description, workspace_id, role_type, sort, leader)
VALUES 
('1000000000000000002', '管理员', 'Workspace Admin', '60498179', '1', 9999, ''),
('1000000000000000089', '普通成员', 'Normal User', '60498179', '1', 9999, '');

-- 插入角色成员数据
INSERT INTO role_member (role_id, member_name, workspace_id)
VALUES 
('1000000000000000002', '未命名小程序', '60498179'),
('1000000000000000089', '未命名小程序', '60498179');

-- 插入用户字段映射配置
INSERT INTO user_field_mapping (workspace_id, creator_field, owner_field, developer_field, cc_field)
VALUES ('60498179', '创建人', '处理人', '开发人员', '抄送人');





缺陷 修改状态。推进到下一个状态

Request URL
https://www.tapd.cn/api/entity/workflow/change_bug_status
Request Method
POST




{"workspace_id":"60498179","data":{"Bug":{"current_status":"in_progress","id":"1160498179001000009","complete_effort":false},"new_status":"resolved","Comment":{"description":"<p>1212</p>","markdown_description":"","description_type":1},"is_editor_or_markdown":1,"branch":{},"STATUS_in_progress-resolved":{"remarks":"<p>1212</p>","current_owner":"未命名小程序;","te":"未命名小程序;"}},"dsc_token":"B14PcWsEU6IsFDYQ"}




{
    "data": {
        "id": "1160498179001000009",
        "is_done_status": "",
        "is_first_status": "",
        "old_is_first_status": "",
        "old_is_done_status": "",
        "message": "Bug\u5355\u66f4\u65b0\u6210\u529f\u3002",
        "new_status": "resolved",
        "new_status_name": "resolved",
        "new_status_name_cn": "\u5df2\u89e3\u51b3",
        "owner": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
        "workspace_id": "60498179",
        "is_partial_flush": "1",
        "fields_have_changed": "1",
        "comment": {
            "project_id": "60498179",
            "entry_type": "bug_remark",
            "entry_id": "1160498179001000009",
            "description": "\u003Cp\u003E1212\u003C\/p\u003E",
            "markdown_description": "",
            "description_type": "1",
            "author": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
            "title": " \u5728\u6d41\u8f6c [\u63a5\u53d7\/\u5904\u7406-\u5df2\u89e3\u51b3] \u6dfb\u52a0",
            "id": "1160498179001000006",
            "change_log_version": "1749730388"
        }
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749730388"
}




-- 缺陷状态变更请求表
CREATE TABLE bug_status_change_request (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '请求记录ID',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    bug_id VARCHAR(30) NOT NULL COMMENT '缺陷ID',
    from_status VARCHAR(50) NOT NULL COMMENT '原始状态',
    to_status VARCHAR(50) NOT NULL COMMENT '目标状态',
    request_json JSON NOT NULL COMMENT '完整请求JSON',
    dsc_token VARCHAR(100) COMMENT '安全令牌',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
    INDEX idx_workspace_bug (workspace_id, bug_id),
    INDEX idx_status_change (from_status, to_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷状态变更请求记录表';

-- 缺陷状态变更响应表
CREATE TABLE bug_status_change_response (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '响应记录ID',
    request_id INT NOT NULL COMMENT '关联的请求ID',
    bug_id VARCHAR(30) NOT NULL COMMENT '缺陷ID',
    new_status VARCHAR(50) NOT NULL COMMENT '新状态',
    new_status_name VARCHAR(50) COMMENT '新状态名称',
    new_status_name_cn VARCHAR(50) COMMENT '新状态中文名称',
    owner VARCHAR(100) COMMENT '处理人',
    message VARCHAR(255) COMMENT '操作结果消息',
    is_partial_flush TINYINT(1) DEFAULT 0 COMMENT '是否部分刷新',
    fields_changed TINYINT(1) DEFAULT 0 COMMENT '字段是否变更',
    response_json JSON NOT NULL COMMENT '完整响应JSON',
    is_success TINYINT(1) DEFAULT 0 COMMENT '是否成功',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '响应时间',
    FOREIGN KEY (request_id) REFERENCES bug_status_change_request(id) ON DELETE CASCADE,
    INDEX idx_bug (bug_id),
    INDEX idx_new_status (new_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷状态变更响应记录表';

-- 状态变更评论表
CREATE TABLE bug_status_comment (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '评论ID',
    response_id INT NOT NULL COMMENT '关联的响应ID',
    project_id VARCHAR(20) NOT NULL COMMENT '项目ID',
    entry_type VARCHAR(20) NOT NULL COMMENT '条目类型',
    entry_id VARCHAR(30) NOT NULL COMMENT '条目ID',
    description TEXT COMMENT '评论内容(HTML格式)',
    markdown_description TEXT COMMENT 'Markdown评论内容',
    description_type TINYINT COMMENT '描述类型(1-HTML)',
    author VARCHAR(100) COMMENT '评论人',
    title VARCHAR(255) COMMENT '评论标题',
    comment_id VARCHAR(30) COMMENT '评论在系统中的ID',
    change_log_version VARCHAR(50) COMMENT '变更日志版本',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    FOREIGN KEY (response_id) REFERENCES bug_status_change_response(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷状态变更的评论记录表';


插入请求数据
INSERT INTO bug_status_change_request (
    workspace_id, 
    bug_id, 
    from_status, 
    to_status, 
    request_json, 
    dsc_token
) VALUES (
    '60498179',
    '1160498179001000009',
    'in_progress',
    'resolved',
    '{
        "workspace_id":"60498179",
        "data":{
            "Bug":{"current_status":"in_progress","id":"1160498179001000009","complete_effort":false},
            "new_status":"resolved",
            "Comment":{"description":"<p>1212</p>","markdown_description":"","description_type":1},
            "is_editor_or_markdown":1,
            "branch":{},
            "STATUS_in_progress-resolved":{"remarks":"<p>1212</p>","current_owner":"未命名小程序;","te":"未命名小程序;"}
        },
        "dsc_token":"B14PcWsEU6IsFDYQ"
    }',
    'B14PcWsEU6IsFDYQ'
);



假设上一条请求的ID为1，插入响应数据
INSERT INTO bug_status_change_response (
    request_id, 
    bug_id, 
    new_status, 
    new_status_name, 
    new_status_name_cn, 
    owner, 
    message, 
    is_partial_flush, 
    fields_changed, 
    response_json, 
    is_success
) VALUES (
    1,
    '1160498179001000009',
    'resolved',
    'resolved',
    '已解决',
    '未命名小程序;',
    'Bug单更新成功。',
    1,
    1,
    '{
        "data": {
            "id": "1160498179001000009",
            "is_done_status": "",
            "is_first_status": "",
            "old_is_first_status": "",
            "old_is_done_status": "",
            "message": "Bug单更新成功。",
            "new_status": "resolved",
            "new_status_name": "resolved",
            "new_status_name_cn": "已解决",
            "owner": "未命名小程序;",
            "workspace_id": "60498179",
            "is_partial_flush": "1",
            "fields_have_changed": "1",
            "comment": {
                "project_id": "60498179",
                "entry_type": "bug_remark",
                "entry_id": "1160498179001000009",
                "description": "<p>1212</p>",
                "markdown_description": "",
                "description_type": "1",
                "author": "未命名小程序",
                "title": " 在流转 [接受/处理-已解决] 添加",
                "id": "1160498179001000006",
                "change_log_version": "1749730388"
            }
        },
        "meta": {
            "code": "0",
            "message": "success"
        },
        "timestamp": "1749730388"
    }',
    1
);

插入评论数据（假设上一条响应的ID为1）

INSERT INTO bug_status_comment (
    response_id, 
    project_id, 
    entry_type, 
    entry_id, 
    description, 
    markdown_description, 
    description_type, 
    author, 
    title, 
    comment_id, 
    change_log_version
) VALUES (
    1,
    '60498179',
    'bug_remark',
    '1160498179001000009',
    '<p>1212</p>',
    '',
    1,
    '未命名小程序',
    ' 在流转 [接受/处理-已解决] 添加',
    '1160498179001000006',
    '1749730388'
);


表结构说明

erDiagram
    bug_status_change_request ||--o{ bug_status_change_response : "1:N"
    bug_status_change_response ||--o{ bug_status_comment : "1:1"
    
    bug_status_change_request {
        INT id PK
        VARCHAR(20) workspace_id
        VARCHAR(30) bug_id
        VARCHAR(50) from_status
        VARCHAR(50) to_status
        JSON request_json
        VARCHAR(100) dsc_token
        TIMESTAMP created_at
    }
    
    bug_status_change_response {
        INT id PK
        INT request_id FK
        VARCHAR(30) bug_id
        VARCHAR(50) new_status
        VARCHAR(50) new_status_name
        VARCHAR(50) new_status_name_cn
        VARCHAR(100) owner
        VARCHAR(255) message
        TINYINT is_partial_flush
        TINYINT fields_changed
        JSON response_json
        TINYINT is_success
        TIMESTAMP created_at
    }
    
    bug_status_comment {
        INT id PK
        INT response_id FK
        VARCHAR(20) project_id
        VARCHAR(20) entry_type
        VARCHAR(30) entry_id
        TEXT description
        TEXT markdown_description
        TINYINT description_type
        VARCHAR(100) author
        VARCHAR(255) title
        VARCHAR(30) comment_id
        VARCHAR(50) change_log_version
        TIMESTAMP created_at
    }



























