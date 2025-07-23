package com.common.fileio.queue;

import com.common.fileio.model.ImportExportTask;
import com.common.fileio.service.ExportService;
import com.common.fileio.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 任务队列监听器
 * 自动监听和处理队列中的任务
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "file.import.export", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TaskQueueListener implements SmartLifecycle {
    
    @Autowired
    private TaskQueueManager taskQueueManager;
    
    @Autowired
    private ImportService importService;
    
    @Autowired
    private ExportService exportService;
    
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread listenerThread;
    
    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            log.info("启动任务队列监听器");
            listenerThread = new Thread(this::processTaskQueue, "task-queue-listener");
            listenerThread.setDaemon(true);
            listenerThread.start();
        }
    }
    
    @Override
    public void stop() {
        if (running.compareAndSet(true, false)) {
            log.info("停止任务队列监听器");
            if (listenerThread != null) {
                listenerThread.interrupt();
            }
        }
    }
    
    @Override
    public boolean isRunning() {
        return running.get();
    }
    
    /**
     * 处理任务队列
     */
    private void processTaskQueue() {
        log.info("任务队列处理线程启动");
        
        while (running.get()) {
            try {
                // 从队列中取出任务，最多等待1秒
                ImportExportTask task = taskQueueManager.dequeueTask(1, TimeUnit.SECONDS);
                
                if (task != null) {
                    log.info("处理任务: {}, 类型: {}", task.getTaskId(), task.getType());
                    
                    try {
                        // 根据任务类型分发到不同的服务处理
                        if (ImportExportTask.TaskType.IMPORT.equals(task.getType())) {
                            importService.processImportTask(task);
                        } else if (ImportExportTask.TaskType.EXPORT.equals(task.getType())) {
                            exportService.processExportTask(task);
                        } else {
                            log.warn("未知任务类型: {}", task.getType());
                        }
                    } catch (Exception e) {
                        log.error("处理任务异常: {}", task.getTaskId(), e);
                        taskQueueManager.updateProgress(task.getTaskId(), 0, "FAILED", "处理失败: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                log.error("任务队列处理异常", e);
                
                // 避免CPU占用过高
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        log.info("任务队列处理线程停止");
    }
    
    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }
}