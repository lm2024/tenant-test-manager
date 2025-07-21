package com.tenant.repository;

import com.tenant.entity.TenantDbInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 租户库信息表JPA仓储接口。
 */
public interface TenantDbInfoRepository extends JpaRepository<TenantDbInfo, Long> {
    Optional<TenantDbInfo> findByTenantId(String tenantId);
    Optional<TenantDbInfo> findByTenantName(String tenantName);
} 