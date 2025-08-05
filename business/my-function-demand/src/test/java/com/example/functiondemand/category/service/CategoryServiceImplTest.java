package com.example.functiondemand.category.service;

import com.example.functiondemand.TestConfig;
import com.example.functiondemand.category.dto.CategoryCreateDTO;
import com.example.functiondemand.category.dto.CategoryDTO;
import com.example.functiondemand.category.dto.CategoryTreeDTO;
import com.example.functiondemand.category.dto.CategoryUpdateDTO;
import com.example.functiondemand.category.entity.Category;
import com.example.functiondemand.category.repository.CategoryRepository;
import com.example.functiondemand.category.service.impl.CategoryServiceImpl;
import com.example.functiondemand.common.enums.CategoryType;
import com.example.functiondemand.common.exception.CategoryNotFoundException;
import com.example.functiondemand.common.util.IdGenerator;
import com.example.functiondemand.common.validator.BusinessRuleChecker;
import com.tenant.routing.core.TenantContextHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Import(TestConfig.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BusinessRuleChecker businessRuleChecker;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category testCategory;
    private CategoryCreateDTO createDTO;
    private CategoryUpdateDTO updateDTO;

    @BeforeEach
    void setUp() {
        testCategory = new Category();
        testCategory.setId("CAT_001");
        testCategory.setName("测试分类");
        testCategory.setType(CategoryType.REQUIREMENT);
        testCategory.setDescription("测试分类描述");
        testCategory.setLevel(1);
        testCategory.setPath("/CAT_001");
        testCategory.setSortOrder(0);
        testCategory.setCreatedTime(LocalDateTime.now());
        testCategory.setUpdatedTime(LocalDateTime.now());
        testCategory.setCreatedBy("testUser");
        testCategory.setUpdatedBy("testUser");

        createDTO = new CategoryCreateDTO();
        createDTO.setName("新分类");
        createDTO.setType(CategoryType.REQUIREMENT);
        createDTO.setDescription("新分类描述");
        createDTO.setSortOrder(1);
        createDTO.setCreatedBy("testUser");

        updateDTO = new CategoryUpdateDTO();
        updateDTO.setId("CAT_001");
        updateDTO.setName("更新的分类");
        updateDTO.setType(CategoryType.REQUIREMENT);
        updateDTO.setDescription("更新的分类描述");
        updateDTO.setSortOrder(2);
        updateDTO.setUpdatedBy("testUser");
    }

    @Test
    void testCreate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateCategoryId).thenReturn("CAT_NEW");
            
            when(categoryRepository.existsByTypeAndName(CategoryType.REQUIREMENT, "新分类")).thenReturn(false);
            when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);
            doNothing().when(businessRuleChecker).checkCategoryRules(any(Category.class));

            // When
            CategoryDTO result = categoryService.create(createDTO);

            // Then
            assertNotNull(result);
            assertEquals("CAT_001", result.getId());
            assertEquals("测试分类", result.getName());
            verify(categoryRepository).existsByTypeAndName(CategoryType.REQUIREMENT, "新分类");
            verify(categoryRepository).save(any(Category.class));
            verify(businessRuleChecker).checkCategoryRules(any(Category.class));
        }
    }

    @Test
    void testCreate_DuplicateName() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(categoryRepository.existsByTypeAndName(CategoryType.REQUIREMENT, "新分类")).thenReturn(true);

            // When & Then
            assertThrows(IllegalArgumentException.class, 
                () -> categoryService.create(createDTO));
            
            verify(categoryRepository).existsByTypeAndName(CategoryType.REQUIREMENT, "新分类");
            verify(categoryRepository, never()).save(any(Category.class));
        }
    }

    @Test
    void testCreate_WithParent_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class);
             MockedStatic<IdGenerator> mockedIdGenerator = mockStatic(IdGenerator.class)) {
            
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            mockedIdGenerator.when(IdGenerator::generateCategoryId).thenReturn("CAT_CHILD");
            
            createDTO.setParentId("CAT_PARENT");
            
            Category parentCategory = new Category();
            parentCategory.setId("CAT_PARENT");
            parentCategory.setType(CategoryType.REQUIREMENT);
            parentCategory.setLevel(1);
            parentCategory.setPath("/CAT_PARENT");
            
            when(categoryRepository.existsByTypeAndName(CategoryType.REQUIREMENT, "新分类")).thenReturn(false);
            when(categoryRepository.findById("CAT_PARENT")).thenReturn(Optional.of(parentCategory));
            when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);
            doNothing().when(businessRuleChecker).checkCategoryRules(any(Category.class));

            // When
            CategoryDTO result = categoryService.create(createDTO);

            // Then
            assertNotNull(result);
            verify(categoryRepository).findById("CAT_PARENT");
            verify(categoryRepository).save(any(Category.class));
        }
    }

    @Test
    void testUpdate_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(categoryRepository.findById("CAT_001")).thenReturn(Optional.of(testCategory));
            when(categoryRepository.findByTypeAndName(CategoryType.REQUIREMENT, "更新的分类"))
                .thenReturn(Optional.empty());
            when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);
            doNothing().when(businessRuleChecker).checkCategoryRules(any(Category.class));

            // When
            CategoryDTO result = categoryService.update("CAT_001", updateDTO);

            // Then
            assertNotNull(result);
            assertEquals("CAT_001", result.getId());
            verify(categoryRepository).findById("CAT_001");
            verify(categoryRepository).save(any(Category.class));
            verify(businessRuleChecker).checkCategoryRules(any(Category.class));
        }
    }

    @Test
    void testUpdate_NotFound() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(categoryRepository.findById("CAT_001")).thenReturn(Optional.empty());

            // When & Then
            assertThrows(CategoryNotFoundException.class, 
                () -> categoryService.update("CAT_001", updateDTO));
            
            verify(categoryRepository).findById("CAT_001");
            verify(categoryRepository, never()).save(any(Category.class));
        }
    }

    @Test
    void testDelete_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(categoryRepository.existsById("CAT_001")).thenReturn(true);
            doNothing().when(businessRuleChecker).checkDeleteRules("CAT_001", "category");
            doNothing().when(categoryRepository).deleteById("CAT_001");

            // When
            categoryService.delete("CAT_001");

            // Then
            verify(categoryRepository).existsById("CAT_001");
            verify(businessRuleChecker).checkDeleteRules("CAT_001", "category");
            verify(categoryRepository).deleteById("CAT_001");
        }
    }

    @Test
    void testFindById_Success() {
        // Given
        when(categoryRepository.findById("CAT_001")).thenReturn(Optional.of(testCategory));

        // When
        CategoryDTO result = categoryService.findById("CAT_001");

        // Then
        assertNotNull(result);
        assertEquals("CAT_001", result.getId());
        assertEquals("测试分类", result.getName());
        verify(categoryRepository).findById("CAT_001");
    }

    @Test
    void testFindByType_Success() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(categoryRepository.findByTypeOrderByLevelAndSort(CategoryType.REQUIREMENT)).thenReturn(categories);

        // When
        List<CategoryDTO> result = categoryService.findByType(CategoryType.REQUIREMENT);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CAT_001", result.get(0).getId());
        verify(categoryRepository).findByTypeOrderByLevelAndSort(CategoryType.REQUIREMENT);
    }

    @Test
    void testGetCategoryTree_Success() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(categoryRepository.findByTypeAndParentIdIsNull(CategoryType.REQUIREMENT)).thenReturn(categories);
        when(categoryRepository.findByTypeAndParentIdOrderBySortOrder(CategoryType.REQUIREMENT, "CAT_001"))
            .thenReturn(Arrays.asList());

        // When
        List<CategoryTreeDTO> result = categoryService.getCategoryTree(CategoryType.REQUIREMENT, null);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CAT_001", result.get(0).getId());
        verify(categoryRepository).findByTypeAndParentIdIsNull(CategoryType.REQUIREMENT);
    }

    @Test
    void testUpdateSortOrder_Success() {
        // Given
        try (MockedStatic<TenantContextHolder> mockedTenant = mockStatic(TenantContextHolder.class)) {
            mockedTenant.when(TenantContextHolder::getTenantId).thenReturn("tenant001");
            
            when(categoryRepository.findById("CAT_001")).thenReturn(Optional.of(testCategory));
            when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

            // When
            categoryService.updateSortOrder("CAT_001", 5);

            // Then
            verify(categoryRepository).findById("CAT_001");
            verify(categoryRepository).save(any(Category.class));
        }
    }

    @Test
    void testFindByTypeAndKeyword_Success() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(categoryRepository.findByTypeAndNameContaining(CategoryType.REQUIREMENT, "测试"))
            .thenReturn(categories);

        // When
        List<CategoryDTO> result = categoryService.findByTypeAndKeyword(CategoryType.REQUIREMENT, "测试");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CAT_001", result.get(0).getId());
        verify(categoryRepository).findByTypeAndNameContaining(CategoryType.REQUIREMENT, "测试");
    }
}