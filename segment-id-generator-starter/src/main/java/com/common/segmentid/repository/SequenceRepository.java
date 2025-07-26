package com.common.segmentid.repository;

import com.common.segmentid.entity.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {
    Optional<Sequence> findByTenantIdAndBizType(String tenantId, String bizType);
    List<Sequence> findByTenantId(String tenantId);
} 