package com.example.functiondemand.function.service;

import com.example.functiondemand.TestConfig;
import com.example.functiondemand.common.enums.FunctionComplexity;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.exception.FunctionPointNotFoundException;
import com.example.functiondemand.common.util.BatchPerformanceOptimizer;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import com.example.functiondemand.function.dto.FunctionPointCreateDTO;
import com.example.functiondemand.function.dto.FunctionPointDTO;
import com.example.functiondemand.function.dto.FunctionPointTreeDTO;
import com.example.functiondemand.function.dto.FunctionPointUpdateDTO;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.function.service.impl.FunctionPointServiceImpl;
import com.example.functiondemand.relation.repository.RequirementFunctionRelationRepository;
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
class FunctionPointServiceImplTest {

    @Mock
    private FunctionPointRepository functionPointRepository;

    @Mock
    private RequirementFunctionRelationRepository relationRepository;

    @Mock
    private BatchPerformanceOptimizer batchOptimizer;

    @Mock
    private BusinessRuleChecker businessRuleChecker;

    @InjectMocks
    private FunctionPointServiceImpl functionPointService;

    private FunctionPoint testFunctionPoint;
    private FunctionPointCreateDTO createDTO;
    private FunctionPointUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        testFunctionPoint = new FunctionPoint();
        testFunctionPoint.setId("FUNC_001");
        testFunctionPoint.setName("测试功能点");
        testFunctionPoint.setDescription("测试功能点描述");
        testFunctionPoint.setModule("测试模块");
        testFunctionPoint.setComplexity(FunctionComplexity.MEDIUM);
        testFunctionPoint.setStatus(FunctionStatus.PLANNING);
        testFunctionPoint.setOwner("testUser");
        testFunctionPoint.setLevel(1);
        testFunctionPoint.setPath("/FUNC_001");
        testFunctionPoint.setCreatedTime(LocalDateTime.now());
        testFunctionPoint.setUpdatedTime(LocalDateTime.now());
        testFunctionPoint.setCreatedBy("testUser");
        testFunctionPoint.setUpdatedBy("testUser");

        createDTO = new FunctionPointCreateDTO();
        createDTO.setName("新功能点");
        createDTO.setDescription("新功能点描述");
        createDTO.setModule("新模块");
        createDTO.setComplexity(FunctionComplexity.LOW);
        createDTO.setStatus(FunctionStatus.PLANNING);
        createDTO.setOwner("testUser");
        createDTO.setCreatedBy("testUser");

        updateDTO = new FunctionPointUpdateDTO();
        updateDTO.setId("FUNC_001");
        updateDTO.setName("更新的功能点");
        updateDTO.setDescription("更新的功能点描述");
        updateDTO.setModule("更新的模块");
        updateDTO.setComplexity(FunctionComplexity.HIGH);
        updateDTO.setStatus(FunctionStatus.DEVELOPING);
        updateDTO.setOwner("testUser");
        updateDTO.setUpdatedBy("testUser");
    }

    @Test
    void testCreate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateFunctionId).thenReturn("FUNC_NEW");
            
            when(functionPointRepository.save(any(FunctionPoint.class))).thenReturn(testFunctionPoint);
            doNothing().when(businessRuleChecker).checkFunctionPointRules(any(FunctionPoint.class));

            // When
            FunctionPointDTO result = functionPointService.create(createDTO);

            // Then
            assertNotNull(result);
            assertEquals("FUNC_001", result.getId());
            assertEquals("测试功能点", result.getName());
            verify(functionPointRepository).save(any(FunctionPoint.class));
            verify(businessRuleChecker).checkFunctionPointRules(any(FunctionPoint.class));
        }
    }

    @Test
    void testCreate_WithParent_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateFunctionId).thenReturn("FUNC_CHILD");
            
            createDTO.setParentId("FUNC_PARENT");
            
            FunctionPoint parentFunctionPoint = new FunctionPoint();
            parentFunctionPoint.setId("FUNC_PARENT");
            parentFunctionPoint.setLevel(1);
            parentFunctionPoint.setPath("/FUNC_PARENT");
            
            when(functionPointRepository.findById("FUNC_PARENT")).thenReturn(Optional.of(parentFunctionPoint));
            when(functionPointRepository.save(any(FunctionPoint.class))).thenReturn(testFunctionPoint);
            doNothing().when(businessRuleChecker).checkFunctionPointRules(any(FunctionPoint.class));

            // When
            FunctionPointDTO result = functionPointService.create(createDTO);

            // Then
            assertNotNull(result);
            verify(functionPointRepository).findById("FUNC_PARENT");
            verify(functionPointRepository).save(any(FunctionPoint.class));
        }
    }

    @Test
    void testUpdate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(functionPointRepository.findById("FUNC_001")).thenReturn(Optional.of(testFunctionPoint));
            when(functionPointRepository.save(any(FunctionPoint.class))).thenReturn(testFunctionPoint);
            doNothing().when(businessRuleChecker).checkFunctionPointRules(any(FunctionPoint.class));

            // When
            FunctionPointDTO result = functionPointService.update("FUNC_001", updateDTO);

            // Then
            assertNotNull(result);
            assertEquals("FUNC_001", result.getId());
            verify(functionPointRepository).findById("FUNC_001");
            verify(functionPointRepository).save(any(FunctionPoint.class));
            verify(businessRuleChecker).checkFunctionPointRules(any(FunctionPoint.class));
        }
    }

    @Test
    void testUpdate_NotFound() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(functionPointRepository.findById("FUNC_001")).thenReturn(Optional.empty());

            // When & Then
            assertThrows(FunctionPointNotFoundException.class, 
                () -> functionPointService.update("FUNC_001", updateDTO));
            
            verify(functionPointRepository).findById("FUNC_001");
            verify(functionPointRepository, never()).save(any(FunctionPoint.class));
        }
    }

    @Test
    void testDelete_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(functionPointRepository.existsById("FUNC_001")).thenReturn(true);
            doNothing().when(businessRuleChecker).checkDeleteRules("FUNC_001", "functionpoint");
            doNothing().when(relationRepository).deleteByFunctionId("FUNC_001");
            doNothing().when(functionPointRepository).deleteById("FUNC_001");

            // When
            functionPointService.delete("FUNC_001");

            // Then
            verify(functionPointRepository).existsById("FUNC_001");
            verify(businessRuleChecker).checkDeleteRules("FUNC_001", "functionpoint");
            verify(relationRepository).deleteByFunctionId("FUNC_001");
            verify(functionPointRepository).deleteById("FUNC_001");
        }
    }

    @Test
    void testFindById_Success() {
        // Given
        when(functionPointRepository.findById("FUNC_001")).thenReturn(Optional.of(testFunctionPoint));

        // When
        FunctionPointDTO result = functionPointService.findById("FUNC_001");

        // Then
        assertNotNull(result);
        assertEquals("FUNC_001", result.getId());
        assertEquals("测试功能点", result.getName());
        verify(functionPointRepository).findById("FUNC_001");
    }

    @Test
    void testFindById_NotFound() {
        // Given
        when(functionPointRepository.findById("FUNC_001")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(FunctionPointNotFoundException.class, 
            () -> functionPointService.findById("FUNC_001"));
        
        verify(functionPointRepository).findById("FUNC_001");
    }

    @Test
    void testFindAll_Success() {
        // Given
        List<FunctionPoint> functionPoints = Arrays.asList(testFunctionPoint);
        Page<FunctionPoint> page = new PageImpl<>(functionPoints);
        Pageable pageable = PageRequest.of(0, 10);
        
        when(functionPointRepository.findAll(pageable)).thenReturn(page);

        // When
        Page<FunctionPointDTO> result = functionPointService.findAll(null, pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("FUNC_001", result.getContent().get(0).getId());
        verify(functionPointRepository).findAll(pageable);
    }

    @Test
    void testGetFunctionTree_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            List<FunctionPoint> functionPoints = Arrays.asList(testFunctionPoint);
            when(functionPointRepository.findByParentIdIsNull()).thenReturn(functionPoints);
            when(functionPointRepository.findByParentId("FUNC_001")).thenReturn(Arrays.asList());

            // When
            List<FunctionPointTreeDTO> result = functionPointService.getFunctionTree(null);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("FUNC_001", result.get(0).getId());
            verify(functionPointRepository).findByParentIdIsNull();
        }
    }

    @Test
    void testGetChildren_Success() {
        // Given
        List<FunctionPoint> children = Arrays.asList(testFunctionPoint);
        when(functionPointRepository.findChildrenByParentId("FUNC_PARENT")).thenReturn(children);

        // When
        List<FunctionPointDTO> result = functionPointService.getChildren("FUNC_PARENT");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("FUNC_001", result.get(0).getId());
        verify(functionPointRepository).findChildrenByParentId("FUNC_PARENT");
    }

    @Test
    void testBatchCreate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedTenant.when(TenantContextHolder::hasTenant).thenReturn(true);
            
            List<FunctionPointCreateDTO> dtos = Arrays.asList(createDTO);
            doNothing().when(businessRuleChecker).checkBatchOperationRules(1, "create");

            // Mock the create method to return a DTO
            FunctionPointServiceImpl spyService = spy(functionPointService);
            FunctionPointDTO mockResult = new FunctionPointDTO();
            mockResult.setId("FUNC_NEW");
            mockResult.setName("新功能点");
            doReturn(mockResult).when(spyService).create(any(FunctionPointCreateDTO.class));

            // When
            List<FunctionPointDTO> result = spyService.batchCreate(dtos);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("FUNC_NEW", result.get(0).getId());
            verify(businessRuleChecker).checkBatchOperationRules(1, "create");
        }
    }

    @Test
    void testBatchCreate_NoTenantContext() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn(null);
            
            List<FunctionPointCreateDTO> dtos = Arrays.asList(createDTO);

            // When & Then
            assertThrows(IllegalStateException.class, 
                () -> functionPointService.batchCreate(dtos));
        }
    }
}