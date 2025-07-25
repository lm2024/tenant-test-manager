package com.tenant.test.processor;

import com.common.fileio.processor.DataProcessor;
import com.tenant.test.entity.TestCase;
import com.tenant.test.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用例数据处理器
 * 用于处理测试用例的导入导出
 */
@Component
public class TestCaseDataProcessor implements DataProcessor<TestCase> {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public List<TestCase> parseFileData(String filePath, String fileType) {
        // 根据文件类型解析文件数据
        List<TestCase> testCases = new ArrayList<>();
        
        // 这里应该根据文件类型调用不同的解析方法
        // 为了简化示例，这里直接返回空列表
        return testCases;
    }

    @Override
    public void saveBatch(List<TestCase> entities) {
        // 批量保存测试用例数据
        testCaseRepository.saveAll(entities);
    }

    @Override
    public List<TestCase> queryExportData(Map<String, Object> params, int page, int size) {
        // 查询要导出的测试用例数据
        // 这里应该根据参数查询数据库
        // 为了简化示例，这里直接返回空列表
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> convertToExportFormat(List<TestCase> entities) {
        // 将测试用例实体转换为导出格式
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (TestCase testCase : entities) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", testCase.getId());
            row.put("name", testCase.getName());
            row.put("description", testCase.getDescription());
            row.put("status", testCase.getStatus());
            row.put("priority", testCase.getPriority());
            row.put("createTime", testCase.getCreateTime());
            result.add(row);
        }
        
        return result;
    }

    @Override
    public List<String> getExportHeaders() {
        // 获取导出表头
        List<String> headers = new ArrayList<>();
        headers.add("ID");
        headers.add("名称");
        headers.add("描述");
        headers.add("状态");
        headers.add("优先级");
        headers.add("创建时间");
        return headers;
    }

    @Override
    public String getProcessorName() {
        // 返回处理器名称
        return "testData";
    }

    @Override
    public Class<TestCase> getEntityClass() {
        // 返回实体类类型
        return TestCase.class;
    }
}