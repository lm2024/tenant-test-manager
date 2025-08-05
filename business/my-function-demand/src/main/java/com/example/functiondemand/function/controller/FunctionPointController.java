package com.example.functiondemand.function.controller;

import com.example.functiondemand.function.dto.*;
import com.example.functiondemand.function.service.FunctionPointService;
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
@RequestMapping("/api/function-points")
@RequiredArgsConstructor
@Validated
@TenantSwitchHeader
@Tag(name = "功能点管理", description = "功能点管理相关接口")
public class FunctionPointController {

    private final FunctionPointService functionPointService;

    @PostMapping
    @Operation(summary = "创建功能点", description = "创建新的功能点")
    public ResponseEntity<FunctionPointDTO> create(@Valid @RequestBody FunctionPointCreateDTO dto) {
        log.info("创建功能点请求: {}", dto.getName());
        FunctionPointDTO result = functionPointService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新功能点", description = "根据ID更新功能点信息")
    public ResponseEntity<FunctionPointDTO> update(
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String id,
            @Valid @RequestBody FunctionPointUpdateDTO dto) {
        log.info("更新功能点请求: {}", id);
        dto.setId(id);
        FunctionPointDTO result = functionPointService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除功能点", description = "根据ID删除功能点")
    public ResponseEntity<Void> delete(
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String id) {
        log.info("删除功能点请求: {}", id);
        functionPointService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取功能点详情", description = "根据ID获取功能点详细信息")
    public ResponseEntity<FunctionPointDTO> findById(
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String id) {
        FunctionPointDTO result = functionPointService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Operation(summary = "分页查询功能点", description = "根据条件分页查询功能点列表")
    public ResponseEntity<Page<FunctionPointDTO>> findAll(
            FunctionPointQueryDTO query,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<FunctionPointDTO> result = functionPointService.findAll(query, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建功能点", description = "批量创建多个功能点")
    public ResponseEntity<List<FunctionPointDTO>> batchCreate(
            @Valid @RequestBody @NotEmpty List<FunctionPointCreateDTO> dtos) {
        log.info("批量创建功能点请求，数量: {}", dtos.size());
        List<FunctionPointDTO> result = functionPointService.batchCreate(dtos);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/batch")
    @Operation(summary = "批量更新功能点", description = "批量更新多个功能点")
    public ResponseEntity<Void> batchUpdate(
            @Valid @RequestBody @NotEmpty List<FunctionPointUpdateDTO> dtos) {
        log.info("批量更新功能点请求，数量: {}", dtos.size());
        functionPointService.batchUpdate(dtos);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除功能点", description = "批量删除多个功能点")
    public ResponseEntity<Void> batchDelete(
            @RequestBody @NotEmpty List<String> ids) {
        log.info("批量删除功能点请求，数量: {}", ids.size());
        functionPointService.batchDelete(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tree")
    @Operation(summary = "获取功能点树", description = "获取树形结构的功能点列表")
    public ResponseEntity<List<FunctionPointTreeDTO>> getFunctionTree(
            @Parameter(description = "父功能点ID，为空时获取根节点") @RequestParam(required = false) String parentId) {
        List<FunctionPointTreeDTO> result = functionPointService.getFunctionTree(parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/children")
    @Operation(summary = "获取子功能点", description = "获取指定功能点的直接子功能点")
    public ResponseEntity<List<FunctionPointDTO>> getChildren(
            @Parameter(description = "父功能点ID") @PathVariable @NotBlank String id) {
        List<FunctionPointDTO> result = functionPointService.getChildren(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/ancestors")
    @Operation(summary = "获取祖先功能点", description = "获取指定功能点的所有祖先功能点")
    public ResponseEntity<List<FunctionPointDTO>> getAncestors(
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String id) {
        List<FunctionPointDTO> result = functionPointService.getAncestors(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/requirements")
    @Operation(summary = "获取关联的需求", description = "获取功能点关联的所有需求ID")
    public ResponseEntity<List<String>> getAssociatedRequirements(
            @Parameter(description = "功能点ID") @PathVariable @NotBlank String id) {
        List<String> result = functionPointService.getAssociatedRequirementIds(id);
        return ResponseEntity.ok(result);
    }
}