package com.example.functiondemand.requirement.service;

import com.example.functiondemand.requirement.service.impl.RequirementServiceImpl;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.requirement.dto.RequirementCreateDTO;
import com.example.functiondemand.requirement.dto.RequirementDTO;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import com.example.functiondemand.common.util.BatchPerformanceOptimizer;
import com.example.functiondemand.relation.repository.RequirementFunctionRelationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequirementServiceImplTest {

    @Mock
    private RequirementRepository requirementRepository;

    @Mock
    private RequirementFunctionRelationRepository relationRepository;

    @Mock
    private BatchPerformanceOptimizer batchOptimizer;

    @Mock
    private BusinessRuleChecker businessRuleChecker;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @InjectMocks
    private RequirementServiceImpl requirementService;

    private Requirement testRequirement;
    private RequirementCreateDTO testCreateDTO;

    @BeforeEach
    public void setUp() {
        testRequirement = new Requirement();
        testRequirement.setId("req1");
        testRequirement.setTitle("测试需求");
        testRequirement.setDescription("测试需求描述");
        testRequirement.setPriority(RequirementPriority.HIGH);
        testRequirement.setStatus(RequirementStatus.DRAFT);
        testRequirement.setSource("测试");
        testRequirement.setLevel(1);
        testRequirement.setCreatedTime(LocalDateTime.now());
        testRequirement.setUpdatedTime(LocalDateTime.now());

        testCreateDTO = new RequirementCreateDTO();
        testCreateDTO.setTitle("测试需求");
        testCreateDTO.setDescription("测试需求描述");
        testCreateDTO.setPriority(RequirementPriority.HIGH);
        testCreateDTO.setStatus(RequirementStatus.DRAFT);
        testCreateDTO.setSource("测试");
    }

    @Test
    public void testFindById() {
        // 准备测试数据
        when(requirementRepository.findById("req1")).thenReturn(Optional.of(testRequirement));

        // 执行测试
        RequirementDTO result = requirementService.findById("req1");

        // 验证结果
        assertNotNull(result);
        assertEquals("req1", result.getId());
        assertEquals("测试需求", result.getTitle());
        verify(requirementRepository).findById("req1");
    }

    @Test
    public void testCreate() {
        // 准备测试数据
        when(requirementRepository.save(any(Requirement.class))).thenReturn(testRequirement);

        // 执行测试
        RequirementDTO result = requirementService.create(testCreateDTO);

        // 验证结果
        assertNotNull(result);
        assertEquals("测试需求", result.getTitle());
        verify(requirementRepository).save(any(Requirement.class));
    }
}