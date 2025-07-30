package com.tenant.test.repository;

import com.tenant.test.entity.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface SequenceJpaRepository extends JpaRepository<Sequence, Long> {
    
    Optional<Sequence> findByTenantIdAndBizType(String tenantId, String bizType);
    
    @Modifying
    @Transactional
    @Query("UPDATE Sequence s SET s.currentValue = :currentValue, s.updateTime = CURRENT_TIMESTAMP WHERE s.tenantId = :tenantId AND s.bizType = :bizType")
    int updateCurrentValue(@Param("tenantId") String tenantId, @Param("bizType") String bizType, @Param("currentValue") Long currentValue);
    
    @Modifying
    @Transactional
    @Query("UPDATE Sequence s SET s.currentValue = 0, s.updateTime = CURRENT_TIMESTAMP WHERE s.tenantId = :tenantId AND s.bizType = :bizType")
    int resetSequence(@Param("tenantId") String tenantId, @Param("bizType") String bizType);
}