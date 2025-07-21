package com.tenant.cvs.service;

import com.tenant.cvs.model.CvsTask;
import com.tenant.cvs.queue.CvsTaskQueue;
import com.tenant.cvs.util.ExcelUtil;
import com.tenant.entity.TestCase;
import com.tenant.repository.TestCaseRepository;
import com.tenant.config.dynamic.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CvsExportService {
    @Autowired
    private CvsTaskQueue taskQueue;
    @Autowired
    private ThreadPoolTaskExecutor cvsExportExecutor;
    @Autowired
    private TestCaseRepository testCaseRepository;

    // 导出任务入口
    public void submitExportTask(CvsTask task) {
        taskQueue.enqueue(task);
        cvsExportExecutor.submit(() -> processExportTask(task));
    }

    // 高性能多租户分批导出实现（以TestCase为例）
    public void processExportTask(CvsTask task) {
        try {
            String tenantId = task.getTenantId();
            DataSourceContextHolder.set(tenantId); // 多租户路由
            int batchSize = 20000; // 可配置
            long total = testCaseRepository.count();
            int batchCount = (int) ((total + batchSize - 1) / batchSize);
            String exportFile = "C:\\Users\\lm\\Downloads\\" + task.getTaskId() + ".xlsx";
            List<String> headers = Arrays.asList("ID", "标题", "描述");
            List<TestCaseExportVO> allRows = new ArrayList<>();
            for (int i = 0; i < batchCount; i++) {
                List<TestCase> batch = testCaseRepository.findAll(org.springframework.data.domain.PageRequest.of(i, batchSize)).getContent();
                List<TestCaseExportVO> exportRows = batch.stream().map(tc -> {
                    TestCaseExportVO vo = new TestCaseExportVO();
                    vo.setId(tc.getId());
                    vo.setTitle(tc.getTitle());
                    vo.setDescription(tc.getDescription());
                    // 可扩展：字段转义、枚举转义
                    return vo;
                }).collect(Collectors.toList());
                allRows.addAll(exportRows);
                taskQueue.setProgress(task.getTaskId(), (i+1)*100/batchCount, "RUNNING", "已导出" + ((i+1)*batchSize) + "条");
            }
            ExcelUtil.writeExcel(exportFile, allRows, TestCaseExportVO.class);
            taskQueue.setProgress(task.getTaskId(), 100, "SUCCESS", "导出完成");
            DataSourceContextHolder.clear();
        } catch (Exception e) {
            taskQueue.setProgress(task.getTaskId(), 0, "FAILED", e.getMessage());
            DataSourceContextHolder.clear();
        }
    }

    // 导出VO
    public static class TestCaseExportVO {
        private Long id;
        private String title;
        private String description;
        // getter/setter
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
} 