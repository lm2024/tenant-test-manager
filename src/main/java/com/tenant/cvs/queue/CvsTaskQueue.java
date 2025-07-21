package com.tenant.cvs.queue;

import com.tenant.cvs.model.CvsTask;
import com.tenant.cvs.model.CvsProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CvsTaskQueue {
    private static final String TASK_QUEUE_KEY = "com.tenant.cvs:task:queue";
    private static final String TASK_PROGRESS_KEY = "com.tenant.cvs:task:progress:";
    private static final String TASK_STATUS_KEY = "com.tenant.cvs:task:status:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 入队
    public void enqueue(CvsTask task) {
        redisTemplate.opsForList().rightPush(TASK_QUEUE_KEY, task);
        setProgress(task.getTaskId(), 0, "WAITING", "任务已入队");
    }

    // 出队
    public CvsTask dequeue(long timeout, TimeUnit unit) {
        Object obj = redisTemplate.opsForList().leftPop(TASK_QUEUE_KEY, timeout, unit);
        return obj instanceof CvsTask ? (CvsTask) obj : null;
    }

    // 设置进度
    public void setProgress(String taskId, int progress, String status, String message) {
        CvsProgress p = new CvsProgress();
        p.setTaskId(taskId);
        p.setProgress(progress);
        p.setStatus(status);
        p.setMessage(message);
        redisTemplate.opsForValue().set(TASK_PROGRESS_KEY + taskId, p);
    }

    // 查询进度
    public CvsProgress getProgress(String taskId) {
        Object obj = redisTemplate.opsForValue().get(TASK_PROGRESS_KEY + taskId);
        return obj instanceof CvsProgress ? (CvsProgress) obj : null;
    }

    // 删除进度
    public void deleteProgress(String taskId) {
        redisTemplate.delete(TASK_PROGRESS_KEY + taskId);
    }

    // 设置任务状态
    public void setStatus(String taskId, String status) {
        redisTemplate.opsForValue().set(TASK_STATUS_KEY + taskId, status);
    }

    // 查询任务状态
    public String getStatus(String taskId) {
        Object obj = redisTemplate.opsForValue().get(TASK_STATUS_KEY + taskId);
        return obj != null ? obj.toString() : null;
    }

    // 删除任务状态
    public void deleteStatus(String taskId) {
        redisTemplate.delete(TASK_STATUS_KEY + taskId);
    }

    // 暂停/终止/删除任务（可扩展）
    // ...
} 