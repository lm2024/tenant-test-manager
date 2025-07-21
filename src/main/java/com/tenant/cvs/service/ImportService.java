package com.tenant.cvs.service;

import com.tenant.cvs.model.CvsTask;
import com.tenant.cvs.queue.CvsTaskQueue;
import com.tenant.cvs.util.ExcelImportUtil;
import com.tenant.cvs.util.CsvImportUtil;
import com.tenant.cvs.util.TxtImportUtil;
import com.tenant.entity.TestCase;
import com.tenant.repository.TestCaseRepository;
import com.tenant.config.dynamic.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单可靠的导入服务
 */
@Service
public class ImportService {
    
    @Autowired private CvsTaskQueue taskQueue;
    @Autowired private ThreadPoolTaskExecutor cvsImportExecutor;
    @Autowired private TestCaseRepository testCaseRepository;
    
    /**
     * 提交导入任务
     */
    public void submitImportTask(CvsTask task) {
        taskQueue.enqueue(task);
        cvsImportExecutor.submit(() -> processImportTask(task));
    }
    
    /**
     * 处理导入任务
     */
    @Transactional
    public void processImportTask(CvsTask task) {
        try {
            String tenantId = task.getTenantId();
            DataSourceContextHolder.set(tenantId);
            
            AtomicInteger totalCount = new AtomicInteger(0);
            List<String> filePaths = task.getFilePaths();
            
            for (String filePath : filePaths) {
                String ext = getFileExtension(filePath);
                
                if (ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")) {
                    // Excel导入
                    importExcel(filePath, task, totalCount);
                } else if (ext.equalsIgnoreCase("csv")) {
                    // CSV导入
                    importCsv(filePath, task, totalCount);
                } else if (ext.equalsIgnoreCase("txt")) {
                    // TXT导入
                    importTxt(filePath, task, totalCount);
                } else {
                    throw new RuntimeException("不支持的文件类型: " + ext);
                }
            }
            
            taskQueue.setProgress(task.getTaskId(), 100, "SUCCESS", "导入完成，共导入" + totalCount.get() + "条数据");
            DataSourceContextHolder.clear();
            
        } catch (Exception e) {
            taskQueue.setProgress(task.getTaskId(), 0, "FAILED", "导入失败: " + e.getMessage());
            DataSourceContextHolder.clear();
            e.printStackTrace();
        }
    }
    
    /**
     * 导入Excel文件
     */
    private void importExcel(String filePath, CvsTask task, AtomicInteger totalCount) {
        // 默认读取test_case sheet
        ExcelImportUtil.readExcelSheet(filePath, "test_case", batch -> {
            saveBatch(batch, totalCount, task);
        }, 1000);
    }
    
    /**
     * 导入CSV文件
     */
    private void importCsv(String filePath, CvsTask task, AtomicInteger totalCount) {
        CsvImportUtil.readCsv(filePath, batch -> {
            saveBatch(batch, totalCount, task);
        }, 1000);
    }
    
    /**
     * 导入TXT文件
     */
    private void importTxt(String filePath, CvsTask task, AtomicInteger totalCount) {
        TxtImportUtil.readTxt(filePath, batch -> {
            saveBatch(batch, totalCount, task);
        }, 1000);
    }
    
    /**
     * 批量保存数据
     */
    private void saveBatch(List<TestCase> batch, AtomicInteger totalCount, CvsTask task) {
        if (batch.isEmpty()) return;
        
        // 分离新增和更新
        List<TestCase> toInsert = new ArrayList<>();
        List<TestCase> toUpdate = new ArrayList<>();
        
        for (TestCase tc : batch) {
            if (tc.getId() != null && tc.getId() > 0) {
                toUpdate.add(tc);
            } else {
                tc.setId(null);
                toInsert.add(tc);
            }
        }
        
        // 批量新增
        if (!toInsert.isEmpty()) {
            List<TestCase> saved = testCaseRepository.saveAll(toInsert);
            System.out.println("批量新增: " + saved.size() + " 条");
        }
        
        // 批量更新
        if (!toUpdate.isEmpty()) {
            for (TestCase tc : toUpdate) {
                testCaseRepository.save(tc);
            }
            System.out.println("批量更新: " + toUpdate.size() + " 条");
        }
        
        int current = totalCount.addAndGet(batch.size());
        taskQueue.setProgress(task.getTaskId(), 0, "RUNNING", "已导入" + current + "条数据");
    }
    
    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int idx = filename.lastIndexOf('.');
        return idx > 0 ? filename.substring(idx + 1) : "";
    }
} 