请求网址
https://www.tapd.cn/api/entity/workflow/change_story_status
请求方法
POST


{"workspace_id":"60498179","data":{"type":"storieslist","new_status":"developing","change_type":"","Story":{"current_status":"developing","story_id":"1160498179001000088","close_task":false,"complete_effort":false},"branch":{},"Comment":{"description":"<p>123</p>","markdown_description":"","description_type":1},"is_editor_or_markdown":1,"STATUS_developing-developing":{"remarks":"<p>123</p>","owner":"未命名小程序;"}},"dsc_token":"B14PcWsEU6IsFDYQ"}



{
    "data": {
        "remarks": "\u003Cp\u003E123\u003C\/p\u003E",
        "owner": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f;",
        "step": "",
        "status_name": "\u5b9e\u73b0\u4e2d",
        "status": "developing",
        "status_alias": "\u5b9e\u73b0\u4e2d",
        "is_done_status": "",
        "is_first_status": "",
        "old_is_first_status": "",
        "old_is_done_status": "",
        "message": "\u66f4\u65b0\u9700\u6c42\u64cd\u4f5c\u6210\u529f",
        "is_ban_create_child": "0",
        "comment": {
            "id": "1160498179001000007",
            "title": "\u5728\u72b6\u6001 [\u5b9e\u73b0\u4e2d] \u6dfb\u52a0",
            "parent_id": "",
            "reply_id": "0",
            "root_id": "0",
            "description": "\u003Cp\u003E123\u003C\/p\u003E",
            "markdown_description": "",
            "plain_text": "123",
            "description_type": "1",
            "author": "\u672a\u547d\u540d\u5c0f\u7a0b\u5e8f",
            "entry_type": "stories",
            "entry_id": "1160498179001000088",
            "workitem_type_id": "0",
            "created": "2025-06-13 10:27:23",
            "readcount": "0",
            "replycount": "0",
            "top": "0",
            "essential": "0",
            "modified": "2025-06-13 10:27:23",
            "agree_count": "0",
            "appose_count": "0",
            "entry_id_bak": "",
            "changes": "",
            "project_id": "60498179",
            "created_from": "",
            "is_delete": "0",
            "type": "text",
            "workflow_step": "",
            "source_id": "0"
        },
        "is_partial_flush": "1",
        "fields_have_changed": "1"
    },
    "meta": {
        "code": "0",
        "message": "success"
    },
    "timestamp": "1749781643"
}

我需要mysql表设计。 ddl   dml





CREATE TABLE story_status_change (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    workspace_id VARCHAR(20) NOT NULL COMMENT '工作空间ID',
    story_id VARCHAR(30) NOT NULL COMMENT '需求ID',
    new_status VARCHAR(50) NOT NULL COMMENT '新状态值',
    current_status VARCHAR(50) NOT NULL COMMENT '当前状态值',
    old_status VARCHAR(50) COMMENT '原始状态值',
    status_name VARCHAR(50) NOT NULL COMMENT '状态名称',
    status_alias VARCHAR(50) NOT NULL COMMENT '状态别名',
    change_type VARCHAR(50) COMMENT '变更类型',
    owner VARCHAR(100) NOT NULL COMMENT '负责人',
    remarks TEXT COMMENT '备注(HTML格式)',
    data_message VARCHAR(255) COMMENT '操作消息',
    meta_code CHAR(2) NOT NULL COMMENT '状态码',
    meta_message VARCHAR(255) NOT NULL COMMENT '状态信息',
    timestamp VARCHAR(15) NOT NULL COMMENT '时间戳',
    dsc_token VARCHAR(50) NOT NULL COMMENT '认证令牌',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_story (story_id),
    INDEX idx_workspace (workspace_id),
    INDEX idx_status (new_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='需求状态变更记录';




CREATE TABLE story_comment (
    id VARCHAR(30) PRIMARY KEY COMMENT '评论ID',
    story_change_id BIGINT UNSIGNED NOT NULL COMMENT '关联的状态变更ID',
    story_id VARCHAR(30) NOT NULL COMMENT '需求ID',
    title VARCHAR(255) NOT NULL COMMENT '评论标题',
    description TEXT COMMENT '描述(HTML格式)',
    markdown_description TEXT COMMENT 'Markdown描述',
    plain_text TEXT COMMENT '纯文本内容',
    author VARCHAR(100) NOT NULL COMMENT '评论人',
    description_type TINYINT NOT NULL COMMENT '描述类型(1=富文本)',
    created DATETIME NOT NULL COMMENT '评论创建时间',
    modified DATETIME NOT NULL COMMENT '最后修改时间',
    FOREIGN KEY (story_change_id) REFERENCES story_status_change(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='需求变更评论';




INSERT INTO story_status_change (
    workspace_id,
    story_id,
    new_status,
    current_status,
    status_name,
    status_alias,
    owner,
    remarks,
    data_message,
    meta_code,
    meta_message,
    timestamp,
    dsc_token
) VALUES (
    '60498179',
    '1160498179001000088',
    'developing',
    'developing',
    '实现中',
    '实现中',
    '未命名小程序;',
    '<p>123</p>',
    '更新需求操作成功',
    '0',
    'success',
    '1749781643',
    'B14PcWsEU6IsFDYQ'
);


















