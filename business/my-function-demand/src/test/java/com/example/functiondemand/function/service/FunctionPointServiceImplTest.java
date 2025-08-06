package com.example.functiondemand.function.service;

import com.example.functiondemand.function.service.impl.FunctionPointServiceImpl;
import com.example.functiondemand.function.repository.FunctionPointRepository;
import com.example.functiondemand.function.entity.FunctionPoint;
import com.example.functiondemand.function.dto.FunctionPointCreateDTO;
import com.example.functiondemand.function.dto.FunctionPointDTO;
import com.example.functiondemand.common.enums.FunctionStatus;
import com.example.functiondemand.common.enums.FunctionComplexity;
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
public class FunctionPointServiceImplTest {

    @Mock
    private FunctionPointRepository functionPointRepository;

    @Mock
    private RequirementFunctionRelationRepository relationRepository;

    @Mock
    private BatchPerformanceOptimizer batchOptimizer;

    @Mock
    private BusinessRuleChecker businessRuleChecker;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @InjectMocks
    private FunctionPointServiceImpl functionPointService;

    private FunctionPoint testFunctionPoint;
    private FunctionPointCreateDTO testCreateDTO;

    @BeforeEach
    public void setUp() {
        testFunctionPoint = new FunctionPoint();
        testFunctionPoint.setId("fp1");
        testFunctionPoint.setName("测试功能点");
        testFunctionPoint.setDescription("测试功能点描述");
        testFunctionPoint.setModule("测试模块");
        testFunctionPoint.setComplexity(FunctionComplexity.MEDIUM);
        testFunctionPoint.setStatus(FunctionStatus.PLANNING);
        testFunctionPoint.setLevel(1);
        testFunctionPoint.setCreatedTime(LocalDateTime.now());
        testFunctionPoint.setUpdatedTime(LocalDateTime.now());

        testCreateDTO = new FunctionPointCreateDTO();
        testCreateDTO.setName("测试功能点");
        testCreateDTO.setDescription("测试功能点描述");
        testCreateDTO.setModule("测试模块");
        testCreateDTO.setComplexity(FunctionComplexity.MEDIUM);
        testCreateDTO.setStatus(FunctionStatus.PLANNING);
    }

    @Test
    public void testFindById() {
        // 准备测试数据
        when(functionPointRepository.findById("fp1")).thenReturn(Optional.of(testFunctionPoint));

        // 执行测试
        FunctionPointDTO result = functionPointService.findById("fp1");

        // 验证结果
        assertNotNull(result);
        assertEquals("fp1", result.getId());
        assertEquals("测试功能点", result.getName());
        verify(functionPointRepository).findById("fp1");
    }

    @Test
    public void testCreate() {
        // 准备测试数据
        when(functionPointRepository.save(any(FunctionPoint.class))).thenReturn(testFunctionPoint);

        // 执行测试
        FunctionPointDTO result = functionPointService.create(testCreateDTO);

        // 验证结果
        assertNotNull(result);
        assertEquals("测试功能点", result.getName());
        verify(functionPointRepository).save(any(FunctionPoint.class));
    }
}