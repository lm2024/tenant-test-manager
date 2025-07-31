package com.common.fileio.exception;

/**
 * 任务未找到异常
 * 当查询的任务不存在时抛出
 */
public class TaskNotFoundException extends FileProcessException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建一个任务未找到异常
     * 
     * @param taskId 任务ID
     */
    public TaskNotFoundException(String taskId) {
        super("任务未找到: " + taskId, "TASK_NOT_FOUND");
    }
}