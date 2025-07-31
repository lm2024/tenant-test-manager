package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 任务进度模型
 * 用于在Redis中存储和查询任务进度信息
 */
@Data
public class TaskProgress implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 任务ID
     */
    private String taskId;
    
    /**
     * 进度百分比（0-100）
     */
    private int progress;
    
    /**
     * 任务状态
     */
    private String status;
    
    /**
     * 进度消息
     */
    private String message;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 结果文件路径（导出任务）
     */
    private String resultFilePath;
    
    /**
     * 创建一个新的任务进度对象
     * 
     * @param taskId 任务ID
     * @param progress 进度百分比
     * @param status 任务状态
     * @param message 进度消息
     * @return 任务进度对象
     */
    public static TaskProgress of(String taskId, int progress, String status, String message) {
        TaskProgress taskProgress = new TaskProgress();
        taskProgress.setTaskId(taskId);
        taskProgress.setProgress(progress);
        taskProgress.setStatus(status);
        taskProgress.setMessage(message);
        taskProgress.setUpdateTime(new Date());
        return taskProgress;
    }
}