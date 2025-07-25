package com.tenant.test.repository;

import com.tenant.test.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 测试用例数据访问接口
 */
@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    // 可以添加自定义查询方法
}