package com.example.functiondemand.category.controller;

import com.example.functiondemand.category.dto.*;
import com.example.functiondemand.category.service.CategoryService;
import com.example.functiondemand.common.enums.CategoryType;
import com.tenant.routing.annotation.TenantSwitchHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
@Tag(name = "分类目录管理", description = "分类目录管理相关接口")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "创建分类目录", description = "创建新的分类目录")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryCreateDTO dto) {
        log.info("创建分类目录请求: {} - {}", dto.getType(), dto.getName());
        CategoryDTO result = categoryService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分类目录", description = "根据ID更新分类目录信息")
    public ResponseEntity<CategoryDTO> update(
            @Parameter(description = "分类目录ID") @PathVariable @NotBlank String id,
            @Valid @RequestBody CategoryUpdateDTO dto) {
        log.info("更新分类目录请求: {}", id);
        dto.setId(id);
        CategoryDTO result = categoryService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类目录", description = "根据ID删除分类目录")
    public ResponseEntity<Void> delete(
            @Parameter(description = "分类目录ID") @PathVariable @NotBlank String id) {
        log.info("删除分类目录请求: {}", id);
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取分类目录详情", description = "根据ID获取分类目录详细信息")
    public ResponseEntity<CategoryDTO> findById(
            @Parameter(description = "分类目录ID") @PathVariable @NotBlank String id) {
        CategoryDTO result = categoryService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "根据类型查询分类目录", description = "根据分类类型查询分类目录列表")
    public ResponseEntity<List<CategoryDTO>> findByType(
            @Parameter(description = "分类类型") @RequestParam @NotNull CategoryType type) {
        List<CategoryDTO> result = categoryService.findByType(type);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建分类目录", description = "批量创建多个分类目录")
    public ResponseEntity<List<CategoryDTO>> batchCreate(
            @Valid @RequestBody @NotEmpty List<CategoryCreateDTO> dtos) {
        log.info("批量创建分类目录请求，数量: {}", dtos.size());
        List<CategoryDTO> result = categoryService.batchCreate(dtos);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新分类目录", description = "批量更新多个分类目录")
    public ResponseEntity<Void> batchUpdate(
            @Valid @RequestBody @NotEmpty List<CategoryUpdateDTO> dtos) {
        log.info("批量更新分类目录请求，数量: {}", dtos.size());
        categoryService.batchUpdate(dtos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除分类目录", description = "批量删除多个分类目录")
    public ResponseEntity<Void> batchDelete(
            @RequestBody @NotEmpty List<String> ids) {
        log.info("批量删除分类目录请求，数量: {}", ids.size());
        categoryService.batchDelete(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tree")
    @Operation(summary = "获取分类目录树", description = "获取树形结构的分类目录列表")
    public ResponseEntity<List<CategoryTreeDTO>> getCategoryTree(
            @Parameter(description = "分类类型") @RequestParam @NotNull CategoryType type,
            @Parameter(description = "父分类ID，为空时获取根节点") @RequestParam(required = false) String parentId) {
        List<CategoryTreeDTO> result = categoryService.getCategoryTree(type, parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/children")
    @Operation(summary = "获取子分类目录", description = "获取指定分类目录的直接子分类")
    public ResponseEntity<List<CategoryDTO>> getChildren(
            @Parameter(description = "父分类ID") @PathVariable @NotBlank String id) {
        List<CategoryDTO> result = categoryService.getChildren(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/ancestors")
    @Operation(summary = "获取祖先分类目录", description = "获取指定分类目录的所有祖先分类")
    public ResponseEntity<List<CategoryDTO>> getAncestors(
            @Parameter(description = "分类目录ID") @PathVariable @NotBlank String id) {
        List<CategoryDTO> result = categoryService.getAncestors(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{parentId}/path")
    @Operation(summary = "生成路径", description = "根据父分类ID生成路径")
    public ResponseEntity<String> generatePath(
            @Parameter(description = "父分类ID") @PathVariable String parentId) {
        String result = categoryService.generatePath(parentId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{parentId}/validate-level")
    @Operation(summary = "验证层级", description = "验证父分类的层级是否超出限制")
    public ResponseEntity<Void> validateLevel(
            @Parameter(description = "父分类ID") @PathVariable String parentId) {
        categoryService.validateLevel(parentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/sort-order")
    @Operation(summary = "更新排序", description = "更新分类目录的排序顺序")
    public ResponseEntity<Void> updateSortOrder(
            @Parameter(description = "分类目录ID") @PathVariable @NotBlank String id,
            @Parameter(description = "排序值") @RequestParam @NotNull Integer sortOrder) {
        log.info("更新分类排序: {} -> {}", id, sortOrder);
        categoryService.updateSortOrder(id, sortOrder);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "搜索分类目录", description = "根据类型和关键字搜索分类目录")
    public ResponseEntity<List<CategoryDTO>> search(
            @Parameter(description = "分类类型") @RequestParam @NotNull CategoryType type,
            @Parameter(description = "搜索关键字") @RequestParam @NotBlank String keyword) {
        List<CategoryDTO> result = categoryService.findByTypeAndKeyword(type, keyword);
        return ResponseEntity.ok(result);
    }
}