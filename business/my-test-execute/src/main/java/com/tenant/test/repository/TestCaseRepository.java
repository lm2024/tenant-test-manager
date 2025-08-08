package com.tenant.test.repository;

import com.tenant.test.entity.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试用例数据访问接口
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    
    /**
     * 根据租户ID查询测试用例
     */
    List<TestCase> findByTenantId(String tenantId);
    
    /**
     * 根据租户ID分页查询测试用例
     */
    Page<TestCase> findByTenantId(String tenantId, Pageable pageable);
    
    /**
     * 根据租户ID和状态查询测试用例
     */
    List<TestCase> findByTenantIdAndStatus(String tenantId, String status);
    
    /**
     * 根据租户ID和优先级查询测试用例
     */
    List<TestCase> findByTenantIdAndPriority(String tenantId, String priority);
    
    /**
     * 根据租户ID、状态和优先级查询测试用例
     */
    List<TestCase> findByTenantIdAndStatusAndPriority(String tenantId, String status, String priority);
    
    /**
     * 根据租户ID、状态和优先级分页查询测试用例
     */
    @Query("SELECT tc FROM TestCase tc WHERE tc.tenantId = :tenantId " +
           "AND (:status IS NULL OR tc.status = :status) " +
           "AND (:priority IS NULL OR tc.priority = :priority)")
    Page<TestCase> findByTenantIdWithFilters(@Param("tenantId") String tenantId,
                                           @Param("status") String status,
                                           @Param("priority") String priority,
                                           Pageable pageable);
    
    /**
     * 统计租户下的测试用例数量
     */
    long countByTenantId(String tenantId);
    
    /**
     * 统计租户下指定状态的测试用例数量
     */
    long countByTenantIdAndStatus(String tenantId, String status);
    
    /**
     * 删除租户下的所有测试用例
     */
    void deleteByTenantId(String tenantId);
}