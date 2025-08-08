package com.example.functiondemand.requirement.controller;

import com.common.redis.cache.annotation.CacheConfig;
import com.common.redis.cache.annotation.CacheEvict;
import com.common.redis.cache.annotation.ListCache;
import com.example.functiondemand.requirement.dto.*;
import com.example.functiondemand.requirement.service.RequirementService;
import com.tenant.routing.annotation.TenantSwitchHeader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/requirements")
@RequiredArgsConstructor
@Validated
@Tag(name = "需求管理", description = "需求管理相关接口")
@CacheConfig(
    cacheNames = "requirements", 
    keyPrefix = "req_service", 
    expireTime = 1800,  // 30分钟
    maxCachePages = 5,
    tenantAware = true
)
public class RequirementController {

    private final RequirementService requirementService;

    @PostMapping
    @Operation(summary = "创建需求", description = "创建新的需求")
    @CacheEvict(keyPattern = "requirements:*", timing = CacheEvict.EvictTiming.AFTER)
    public ResponseEntity<RequirementDTO> create(@Valid @RequestBody RequirementCreateDTO dto) {
        log.info("创建需求请求: {}", dto.getTitle());
        RequirementDTO result = requirementService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新需求", description = "根据ID更新需求信息")
    @CacheEvict(keyPattern = "requirements:*", condition = "#result != null")
    public ResponseEntity<RequirementDTO> update(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String id,
            @Valid @RequestBody RequirementUpdateDTO dto) {
        log.info("更新需求请求: {}", id);
        dto.setId(id);
        RequirementDTO result = requirementService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除需求", description = "根据ID删除需求")
    @CacheEvict(allEntries = true, timing = CacheEvict.EvictTiming.AFTER)
    public ResponseEntity<Void> delete(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String id) {
        log.info("删除需求请求: {}", id);
        requirementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取需求详情", description = "根据ID获取需求详细信息")
    public ResponseEntity<RequirementDTO> findById(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String id) {
        RequirementDTO result = requirementService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "分页查询需求", description = "根据条件分页查询需求列表")
    @ListCache(
        value = "requirements_list",
        expireTime = 1800,  // 30分钟
        maxCachePages = 5,
        condition = "#pageable.pageNumber < 5"
    )
    public ResponseEntity<Page<RequirementDTO>> findAll(
            RequirementQueryDTO query,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<RequirementDTO> result = requirementService.findAll(query, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建需求", description = "批量创建多个需求")
    @CacheEvict(allEntries = true, timing = CacheEvict.EvictTiming.AFTER)
    public ResponseEntity<List<RequirementDTO>> batchCreate(
            @Valid @RequestBody @NotEmpty List<RequirementCreateDTO> dtos) {
        log.info("批量创建需求请求，数量: {}", dtos.size());
        List<RequirementDTO> result = requirementService.batchCreate(dtos);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新需求", description = "批量更新多个需求")
    @CacheEvict(allEntries = true, timing = CacheEvict.EvictTiming.AFTER)
    public ResponseEntity<Void> batchUpdate(
            @Valid @RequestBody @NotEmpty List<RequirementUpdateDTO> dtos) {
        log.info("批量更新需求请求，数量: {}", dtos.size());
        requirementService.batchUpdate(dtos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除需求", description = "批量删除多个需求")
    @CacheEvict(allEntries = true, timing = CacheEvict.EvictTiming.AFTER)
    public ResponseEntity<Void> batchDelete(
            @RequestBody @NotEmpty List<String> ids) {
        log.info("批量删除需求请求，数量: {}", ids.size());
        requirementService.batchDelete(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tree")
    @Operation(summary = "获取需求树", description = "获取树形结构的需求列表")
    public ResponseEntity<List<RequirementTreeDTO>> getRequirementTree(
            @Parameter(description = "父需求ID，为空时获取根节点") @RequestParam(required = false) String parentId) {
        List<RequirementTreeDTO> result = requirementService.getRequirementTree(parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/children")
    @Operation(summary = "获取子需求", description = "获取指定需求的直接子需求")
    public ResponseEntity<List<RequirementDTO>> getChildren(
            @Parameter(description = "父需求ID") @PathVariable @NotBlank String id) {
        List<RequirementDTO> result = requirementService.getChildren(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/ancestors")
    @Operation(summary = "获取祖先需求", description = "获取指定需求的所有祖先需求")
    public ResponseEntity<List<RequirementDTO>> getAncestors(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String id) {
        List<RequirementDTO> result = requirementService.getAncestors(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{requirementId}/functions/{functionId}")
    @Operation(summary = "关联功能点", description = "将需求与功能点建立关联关系")
    public ResponseEntity<Void> associateFunction(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String requirementId,
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String functionId) {
        log.info("关联需求和功能点: {} -> {}", requirementId, functionId);
        requirementService.associateFunction(requirementId, functionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{requirementId}/functions/{functionId}")
    @Operation(summary = "取消关联功能点", description = "取消需求与功能点的关联关系")
    public ResponseEntity<Void> disassociateFunction(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String requirementId,
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String functionId) {
        log.info("取消关联需求和功能点: {} -> {}", requirementId, functionId);
        requirementService.disassociateFunction(requirementId, functionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/functions")
    @Operation(summary = "获取关联的功能点", description = "获取需求关联的所有功能点ID")
    public ResponseEntity<List<String>> getAssociatedFunctions(
            @Parameter(description = "需求ID") @PathVariable @NotBlank String id) {
        List<String> result = requirementService.getAssociatedFunctionIds(id);
        return ResponseEntity.ok(result);
    }
}