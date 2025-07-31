package com.tenant.test.service;

import com.tenant.routing.annotation.TenantSwitch;
import com.tenant.routing.core.TenantContextHolder;
import com.tenant.test.entity.TestData;
import com.tenant.test.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试数据服务
 */
@Service
public class TestDataService {
    
    @Autowired
    private TestDataRepository testDataRepository;
    
    /**
     * 获取当前租户的所有数据
     */
    public List<TestData> getAllData() {
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            tenantId = "default";
        }
        return testDataRepository.findByTenantId(tenantId);
    }
    
    /**
     * 创建测试数据
     */
    public TestData createData(String name) {
        String tenantId = TenantContextHolder.getTenantId();
        System.out.println("Service - createData: " + tenantId);
        if (tenantId == null) {
            tenantId = "default";
        }
        
        TestData testData = new TestData();
        testData.setName(name);
        testData.setTenantId(tenantId);
        testData.setCreatedAt(LocalDateTime.now());
        
        return testDataRepository.save(testData);
    }
    
    /**
     * 使用指定租户创建数据
     */
    @TenantSwitch("tenant001")
    public TestData createDataWithFixedTenant(String name) {
        TestData testData = new TestData();
        testData.setName(name);
        testData.setTenantId("tenant001");
        testData.setCreatedAt(LocalDateTime.now());
        
        return testDataRepository.save(testData);
    }
}