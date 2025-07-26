package com.common.segmentid.service;

import com.common.segmentid.entity.Sequence;
import com.common.segmentid.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SequenceManager {
    @Autowired
    private SequenceRepository sequenceRepository;

    public Optional<Sequence> getSequence(String tenantId, String bizType) {
        return sequenceRepository.findByTenantIdAndBizType(tenantId, bizType);
    }

    public List<Sequence> getSequencesByTenant(String tenantId) {
        return sequenceRepository.findByTenantId(tenantId);
    }

    @Transactional
    public Sequence save(Sequence sequence) {
        return sequenceRepository.save(sequence);
    }

    @Transactional
    public void delete(Long id) {
        sequenceRepository.deleteById(id);
    }

    public List<Sequence> findAll() {
        return sequenceRepository.findAll();
    }
} 