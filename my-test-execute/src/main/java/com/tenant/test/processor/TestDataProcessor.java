package com.tenant.test.processor;

import com.common.fileio.processor.ExcelDataProcessor;
import com.tenant.test.entity.TestData;
import com.tenant.test.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试数据处理器
 * 用于处理测试数据的导入导出
 */
@Component
public class TestDataProcessor extends ExcelDataProcessor<TestData> {
    
    @Autowired
    private TestDataRepository testDataRepository;
    
    @Override
    public void saveBatch(List<TestData> entities) {
        testDataRepository.saveAll(entities);
    }
    
    @Override
    public List<TestData> queryExportData(Map<String, Object> params, int page, int size) {
        return testDataRepository.findAll(PageRequest.of(page, size)).getContent();
    }
    
    @Override
    public long count(Map<String, Object> params) {
        return testDataRepository.count();
    }
    
    @Override
    public String getProcessorName() {
        return "testData";
    }
    
    @Override
    public Class<TestData> getEntityClass() {
        return TestData.class;
    }
    
    @Override
    public Map<String, String> getFieldMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("ID", "id");
        mapping.put("名称", "name");
        mapping.put("租户ID", "tenantId");
        mapping.put("创建时间", "createdAt");
        return mapping;
    }
    
    @Override
    protected TestData convertToEntity(Map<String, String> rowData) {
        TestData testData = new TestData();
        
        // 设置ID（如果有）
        String idStr = rowData.get("ID");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                testData.setId(Long.parseLong(idStr));
            } catch (NumberFormatException e) {
                // 忽略无效的ID
            }
        }
        
        // 设置名称
        testData.setName(rowData.get("名称"));
        
        // 设置租户ID
        testData.setTenantId(rowData.get("租户ID"));
        
        // 设置创建时间
        testData.setCreatedAt(LocalDateTime.now());
        
        return testData;
    }
    
    @Override
    public String validate(TestData entity) {
        if (entity.getName() == null || entity.getName().isEmpty()) {
            return "名称不能为空";
        }
        
        return null;
    }
}