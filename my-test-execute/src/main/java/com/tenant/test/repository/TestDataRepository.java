package com.tenant.test.repository;

import com.tenant.test.entity.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试数据仓库
 */
@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long> {
    
    /**
     * 根据租户ID查询数据
     */
    List<TestData> findByTenantId(String tenantId);
}