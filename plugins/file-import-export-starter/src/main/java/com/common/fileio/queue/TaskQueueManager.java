package com.common.fileio.queue;

import com.common.fileio.config.FileImportExportProperties;
import com.common.fileio.exception.TaskNotFoundException;
import com.common.fileio.model.ImportExportTask;
import com.common.fileio.model.TaskProgress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 任务队列管理器
 * 使用Redis管理导入导出任务队列和进度
 */
@Slf4j
public class TaskQueueManager {
    
    private final RedisTemplate<String, Object> redisTemplate;
    private final FileImportExportProperties properties;
    
    /**
     * 任务队列键
     */
    private String taskQueueKey;
    
    /**
     * 任务进度键前缀
     */
    private String taskProgressKeyPrefix;
    
    /**
     * 任务状态键前缀
     */
    private String taskStatusKeyPrefix;
    
    /**
     * 构造函数
     * 
     * @param redisTemplate Redis模板
     * @param properties 配置属性
     */
    public TaskQueueManager(RedisTemplate<String, Object> redisTemplate, FileImportExportProperties properties) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
        
        // 初始化Redis键
        String keyPrefix = properties.getRedis().getKeyPrefix();
        this.taskQueueKey = keyPrefix + ":task:queue";
        this.taskProgressKeyPrefix = keyPrefix + ":task:progress:";
        this.taskStatusKeyPrefix = keyPrefix + ":task:status:";
    }
    
    /**
     * 将任务入队
     * 
     * @param task 导入导出任务
     */
    public void enqueueTask(ImportExportTask task) {
        log.info("任务入队: {}", task.getTaskId());
        task.setCreateTime(new Date());
        task.setStatus(ImportExportTask.TaskStatus.WAITING);
        
        // 将任务放入队列
        redisTemplate.opsForList().rightPush(taskQueueKey, task);
        
        // 设置初始进度
        updateProgress(task.getTaskId(), 0, "WAITING", "任务已入队");
        
        // 设置任务过期时间
        redisTemplate.expire(taskProgressKeyPrefix + task.getTaskId(), 
                properties.getRedis().getTaskTtl(), TimeUnit.SECONDS);
    }
    
    /**
     * 从队列中取出任务
     * 
     * @param timeout 超时时间
     * @param unit 时间单位
     * @return 导入导出任务，如果队列为空则返回null
     */
    public ImportExportTask dequeueTask(long timeout, TimeUnit unit) {
        Object obj = redisTemplate.opsForList().leftPop(taskQueueKey, timeout, unit);
        if (obj instanceof ImportExportTask) {
            ImportExportTask task = (ImportExportTask) obj;
            log.info("任务出队: {}", task.getTaskId());
            return task;
        }
        return null;
    }
    
    /**
     * 更新任务进度
     * 
     * @param taskId 任务ID
     * @param progress 进度百分比（0-100）
     * @param status 任务状态
     * @param message 进度消息
     */
    public void updateProgress(String taskId, int progress, String status, String message) {
        log.debug("更新任务进度: {}, 进度: {}%, 状态: {}, 消息: {}", taskId, progress, status, message);
        
        TaskProgress taskProgress = TaskProgress.of(taskId, progress, status, message);
        redisTemplate.opsForValue().set(taskProgressKeyPrefix + taskId, taskProgress);
        
        // 同时更新任务状态
        redisTemplate.opsForValue().set(taskStatusKeyPrefix + taskId, status);
    }
    
    /**
     * 更新任务进度并设置结果文件路径
     * 
     * @param taskId 任务ID
     * @param progress 进度百分比（0-100）
     * @param status 任务状态
     * @param message 进度消息
     * @param resultFilePath 结果文件路径
     */
    public void updateProgress(String taskId, int progress, String status, String message, String resultFilePath) {
        log.debug("更新任务进度: {}, 进度: {}%, 状态: {}, 消息: {}, 结果文件: {}", 
                taskId, progress, status, message, resultFilePath);
        
        TaskProgress taskProgress = TaskProgress.of(taskId, progress, status, message);
        taskProgress.setResultFilePath(resultFilePath);
        redisTemplate.opsForValue().set(taskProgressKeyPrefix + taskId, taskProgress);
        
        // 同时更新任务状态
        redisTemplate.opsForValue().set(taskStatusKeyPrefix + taskId, status);
    }
    
    /**
     * 获取任务进度
     * 
     * @param taskId 任务ID
     * @return 任务进度
     * @throws TaskNotFoundException 如果任务不存在
     */
    public TaskProgress getProgress(String taskId) {
        Object obj = redisTemplate.opsForValue().get(taskProgressKeyPrefix + taskId);
        if (obj instanceof TaskProgress) {
            return (TaskProgress) obj;
        }
        throw new TaskNotFoundException(taskId);
    }
    
    /**
     * 获取任务状态
     * 
     * @param taskId 任务ID
     * @return 任务状态
     */
    public String getStatus(String taskId) {
        Object obj = redisTemplate.opsForValue().get(taskStatusKeyPrefix + taskId);
        return obj != null ? obj.toString() : null;
    }
    
    /**
     * 停止任务
     * 
     * @param taskId 任务ID
     */
    public void stopTask(String taskId) {
        log.info("停止任务: {}", taskId);
        redisTemplate.opsForValue().set(taskStatusKeyPrefix + taskId, "STOPPED");
        updateProgress(taskId, 0, "STOPPED", "任务已停止");
    }
    
    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     */
    public void deleteTask(String taskId) {
        log.info("删除任务: {}", taskId);
        redisTemplate.delete(taskProgressKeyPrefix + taskId);
        redisTemplate.delete(taskStatusKeyPrefix + taskId);
    }
    
    /**
     * 检查任务是否存在
     * 
     * @param taskId 任务ID
     * @return 是否存在
     */
    public boolean taskExists(String taskId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(taskProgressKeyPrefix + taskId));
    }
    
    /**
     * 检查任务是否应该停止
     * 
     * @param taskId 任务ID
     * @return 是否应该停止
     */
    public boolean shouldStop(String taskId) {
        String status = getStatus(taskId);
        return "STOPPED".equals(status) || "DELETED".equals(status);
    }
}