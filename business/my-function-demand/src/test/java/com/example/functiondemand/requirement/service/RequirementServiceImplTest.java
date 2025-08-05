package com.example.functiondemand.requirement.service;

import com.example.functiondemand.TestConfig;
import com.example.functiondemand.common.enums.RequirementPriority;
import com.example.functiondemand.common.enums.RequirementStatus;
import com.example.functiondemand.common.exception.RequirementNotFoundException;
import com.example.functiondemand.common.util.BatchPerformanceOptimizer;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import com.example.functiondemand.relation.repository.RequirementFunctionRelationRepository;
import com.example.functiondemand.requirement.dto.RequirementCreateDTO;
import com.example.functiondemand.requirement.dto.RequirementDTO;
import com.example.functiondemand.requirement.dto.RequirementTreeDTO;
import com.example.functiondemand.requirement.dto.RequirementUpdateDTO;
import com.example.functiondemand.requirement.entity.Requirement;
import com.example.functiondemand.requirement.repository.RequirementRepository;
import com.example.functiondemand.requirement.service.impl.RequirementServiceImpl;
import com.tenant.routing.core.TenantContextHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
class RequirementServiceImplTest {

    @Mock
    private RequirementRepository requirementRepository;

    @Mock
    private RequirementFunctionRelationRepository relationRepository;

    @Mock
    private BatchPerformanceOptimizer batchOptimizer;

    @Mock
    private BusinessRuleChecker businessRuleChecker;

    @InjectMocks
    private RequirementServiceImpl requirementService;

    private Requirement testRequirement;
    private RequirementCreateDTO createDTO;
    private RequirementUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        testRequirement = new Requirement();
        testRequirement.setId("REQ_001");
        testRequirement.setTitle("测试需求");
        testRequirement.setDescription("测试需求描述");
        testRequirement.setPriority(RequirementPriority.HIGH);
        testRequirement.setStatus(RequirementStatus.DRAFT);
        testRequirement.setLevel(1);
        testRequirement.setPath("/REQ_001");
        testRequirement.setCreatedTime(LocalDateTime.now());
        testRequirement.setUpdatedTime(LocalDateTime.now());
        testRequirement.setCreatedBy("testUser");
        testRequirement.setUpdatedBy("testUser");

        createDTO = new RequirementCreateDTO();
        createDTO.setTitle("新需求");
        createDTO.setDescription("新需求描述");
        createDTO.setPriority(RequirementPriority.MEDIUM);
        createDTO.setStatus(RequirementStatus.DRAFT);
        createDTO.setCreatedBy("testUser");

        updateDTO = new RequirementUpdateDTO();
        updateDTO.setId("REQ_001");
        updateDTO.setTitle("更新的需求");
        updateDTO.setDescription("更新的需求描述");
        updateDTO.setPriority(RequirementPriority.HIGH);
        updateDTO.setStatus(RequirementStatus.APPROVED);
        updateDTO.setUpdatedBy("testUser");
    }

    @Test
    void testCreate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateRequirementId).thenReturn("REQ_NEW");
            
            when(requirementRepository.save(any(Requirement.class))).thenReturn(testRequirement);
            doNothing().when(businessRuleChecker).checkRequirementRules(any(Requirement.class));

            // When
            RequirementDTO result = requirementService.create(createDTO);

            // Then
            assertNotNull(result);
            assertEquals("REQ_001", result.getId());
            assertEquals("测试需求", result.getTitle());
            verify(requirementRepository).save(any(Requirement.class));
            verify(businessRuleChecker).checkRequirementRules(any(Requirement.class));
        }
    }

    @Test
    void testCreate_WithParent_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateRequirementId).thenReturn("REQ_CHILD");
            
            createDTO.setParentId("REQ_PARENT");
            
            Requirement parentRequirement = new Requirement();
            parentRequirement.setId("REQ_PARENT");
            parentRequirement.setLevel(1);
            parentRequirement.setPath("/REQ_PARENT");
            
            when(requirementRepository.findById("REQ_PARENT")).thenReturn(Optional.of(parentRequirement));
            when(requirementRepository.save(any(Requirement.class))).thenReturn(testRequirement);
            doNothing().when(businessRuleChecker).checkRequirementRules(any(Requirement.class));

            // When
            RequirementDTO result = requirementService.create(createDTO);

            // Then
            assertNotNull(result);
            verify(requirementRepository).findById("REQ_PARENT");
            verify(requirementRepository).save(any(Requirement.class));
        }
    }

    @Test
    void testUpdate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.findById("REQ_001")).thenReturn(Optional.of(testRequirement));
            when(requirementRepository.save(any(Requirement.class))).thenReturn(testRequirement);
            doNothing().when(businessRuleChecker).checkRequirementRules(any(Requirement.class));

            // When
            RequirementDTO result = requirementService.update("REQ_001", updateDTO);

            // Then
            assertNotNull(result);
            assertEquals("REQ_001", result.getId());
            verify(requirementRepository).findById("REQ_001");
            verify(requirementRepository).save(any(Requirement.class));
            verify(businessRuleChecker).checkRequirementRules(any(Requirement.class));
        }
    }

    @Test
    void testUpdate_NotFound() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.findById("REQ_001")).thenReturn(Optional.empty());

            // When & Then
            assertThrows(RequirementNotFoundException.class, 
                () -> requirementService.update("REQ_001", updateDTO));
            
            verify(requirementRepository).findById("REQ_001");
            verify(requirementRepository, never()).save(any(Requirement.class));
        }
    }

    @Test
    void testDelete_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.existsById("REQ_001")).thenReturn(true);
            doNothing().when(businessRuleChecker).checkDeleteRules("REQ_001", "requirement");
            doNothing().when(relationRepository).deleteByRequirementId("REQ_001");
            doNothing().when(requirementRepository).deleteById("REQ_001");

            // When
            requirementService.delete("REQ_001");

            // Then
            verify(requirementRepository).existsById("REQ_001");
            verify(businessRuleChecker).checkDeleteRules("REQ_001", "requirement");
            verify(relationRepository).deleteByRequirementId("REQ_001");
            verify(requirementRepository).deleteById("REQ_001");
        }
    }

    @Test
    void testDelete_NotFound() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.existsById("REQ_001")).thenReturn(false);

            // When & Then
            assertThrows(RequirementNotFoundException.class, 
                () -> requirementService.delete("REQ_001"));
            
            verify(requirementRepository).existsById("REQ_001");
            verify(requirementRepository, never()).deleteById(any());
        }
    }

    @Test
    void testFindById_Success() {
        // Given
        when(requirementRepository.findById("REQ_001")).thenReturn(Optional.of(testRequirement));

        // When
        RequirementDTO result = requirementService.findById("REQ_001");

        // Then
        assertNotNull(result);
        assertEquals("REQ_001", result.getId());
        assertEquals("测试需求", result.getTitle());
        verify(requirementRepository).findById("REQ_001");
    }

    @Test
    void testFindById_NotFound() {
        // Given
        when(requirementRepository.findById("REQ_001")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RequirementNotFoundException.class, 
            () -> requirementService.findById("REQ_001"));
        
        verify(requirementRepository).findById("REQ_001");
    }

    @Test
    void testFindAll_Success() {
        // Given
        List<Requirement> requirements = Arrays.asList(testRequirement);
        Page<Requirement> page = new PageImpl<>(requirements);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(requirementRepository.findAll(pageable)).thenReturn(page);

        // When
        Page<RequirementDTO> result = requirementService.findAll(null, pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("REQ_001", result.getContent().get(0).getId());
        verify(requirementRepository).findAll(pageable);
    }

    @Test
    void testBatchCreate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedTenant.when(TenantContextHolder::hasTenant).thenReturn(true);
            
            List<RequirementCreateDTO> dtos = Arrays.asList(createDTO);
            doNothing().when(businessRuleChecker).checkBatchOperationRules(1, "create");

            // Mock the create method to return a DTO
            RequirementServiceImpl spyService = spy(requirementService);
            RequirementDTO mockResult = new RequirementDTO();
            mockResult.setId("REQ_NEW");
            mockResult.setTitle("新需求");
            doReturn(mockResult).when(spyService).create(any(RequirementCreateDTO.class));

            // When
            List<RequirementDTO> result = spyService.batchCreate(dtos);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("REQ_NEW", result.get(0).getId());
            verify(businessRuleChecker).checkBatchOperationRules(1, "create");
        }
    }

    @Test
    void testBatchCreate_NoTenantContext() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn(null);
            
            List<RequirementCreateDTO> dtos = Arrays.asList(createDTO);

            // When & Then
            assertThrows(IllegalStateException.class, 
                () -> requirementService.batchCreate(dtos));
        }
    }

    @Test
    void testGetRequirementTree_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            List<Requirement> requirements = Arrays.asList(testRequirement);
            when(requirementRepository.findByParentIdIsNull()).thenReturn(requirements);
            when(requirementRepository.findByParentId("REQ_001")).thenReturn(Arrays.asList());

            // When
            List<RequirementTreeDTO> result = requirementService.getRequirementTree(null);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("REQ_001", result.get(0).getId());
            verify(requirementRepository).findByParentIdIsNull();
        }
    }

    @Test
    void testGetChildren_Success() {
        // Given
        List<Requirement> children = Arrays.asList(testRequirement);
        when(requirementRepository.findChildrenByParentId("REQ_PARENT")).thenReturn(children);

        // When
        List<RequirementDTO> result = requirementService.getChildren("REQ_PARENT");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("REQ_001", result.get(0).getId());
        verify(requirementRepository).findChildrenByParentId("REQ_PARENT");
    }

    @Test
    void testAssociateFunction_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateRelationId).thenReturn("REL_001");
            
            when(requirementRepository.existsById("REQ_001")).thenReturn(true);
            when(relationRepository.existsByRequirementIdAndFunctionId("REQ_001", "FUNC_001")).thenReturn(false);

            // When
            requirementService.associateFunction("REQ_001", "FUNC_001");

            // Then
            verify(requirementRepository).existsById("REQ_001");
            verify(relationRepository).existsByRequirementIdAndFunctionId("REQ_001", "FUNC_001");
            verify(relationRepository).save(any());
        }
    }

    @Test
    void testAssociateFunction_RequirementNotFound() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.existsById("REQ_001")).thenReturn(false);

            // When & Then
            assertThrows(RequirementNotFoundException.class, 
                () -> requirementService.associateFunction("REQ_001", "FUNC_001"));
            
            verify(requirementRepository).existsById("REQ_001");
            verify(relationRepository, never()).save(any());
        }
    }

    @Test
    void testAssociateFunction_RelationExists() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(requirementRepository.existsById("REQ_001")).thenReturn(true);
            when(relationRepository.existsByRequirementIdAndFunctionId("REQ_001", "FUNC_001")).thenReturn(true);

            // When & Then
            assertThrows(IllegalArgumentException.class, 
                () -> requirementService.associateFunction("REQ_001", "FUNC_001"));
            
            verify(requirementRepository).existsById("REQ_001");
            verify(relationRepository).existsByRequirementIdAndFunctionId("REQ_001", "FUNC_001");
            verify(relationRepository, never()).save(any());
        }
    }
}