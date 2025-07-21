package com.tenant.cvs.model;

import java.util.Date;
import lombok.Data;
import java.util.List;

@Data
public class CvsTask {
    private String taskId;
    private TaskType type; // IMPORT/EXPORT
    private TaskStatus status;
    private int progress; // 0-100
    private String message; // 进度描述
    private List<String> filePaths; // 源文件/导出文件路径
    private Date createTime;
    private Date updateTime;
    private Object params; // 导入导出参数
    private String tenantId; // 多租户支持

    public enum TaskType { IMPORT, EXPORT }
    public enum TaskStatus { WAITING, RUNNING, PAUSED, SUCCESS, FAILED, STOPPED, DELETED }
} 