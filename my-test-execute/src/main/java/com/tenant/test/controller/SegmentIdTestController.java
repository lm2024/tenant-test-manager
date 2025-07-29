package com.tenant.test.controller;

// import com.common.segmentid.service.SegmentIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test/segmentid")
@Tag(name = "号段ID生成", description = "号段ID生成相关接口")
public class SegmentIdTestController {
    // @Autowired
    // private SegmentIdService segmentIdService;
    
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/generate/{tenantId}/{bizType}")
    @Operation(summary = "生成单个ID", description = "根据租户ID和业务类型生成单个号段ID")
    public String generateId(
            @Parameter(description = "租户ID") @PathVariable String tenantId, 
            @Parameter(description = "业务类型") @PathVariable String bizType) {
        // return segmentIdService.generateId(tenantId, bizType);
        return "ID生成功能暂未实现";
    }

    @GetMapping("/generateBatch/{tenantId}/{bizType}/{count}")
    @Operation(summary = "批量生成ID", description = "根据租户ID和业务类型批量生成号段ID")
    public List<String> generateBatch(
            @Parameter(description = "租户ID") @PathVariable String tenantId, 
            @Parameter(description = "业务类型") @PathVariable String bizType, 
            @Parameter(description = "生成数量") @PathVariable int count) {
        // return segmentIdService.generateBatchIds(tenantId, bizType, count);
        return Arrays.asList("批量ID生成功能暂未实现");
    }

    @GetMapping("/info/{tenantId}/{bizType}")
    @Operation(summary = "获取序列信息", description = "获取指定租户和业务类型的序列信息")
    public Object getSequenceInfo(
            @Parameter(description = "租户ID") @PathVariable String tenantId, 
            @Parameter(description = "业务类型") @PathVariable String bizType) {
        // return segmentIdService.getSequenceInfo(tenantId, bizType);
        return "序列信息功能暂未实现";
    }

    @DeleteMapping("/cache/{tenantId}/{bizType}")
    @Operation(summary = "清理缓存", description = "清理指定租户和业务类型的Redis缓存")
    public String clearCache(
            @Parameter(description = "租户ID") @PathVariable String tenantId, 
            @Parameter(description = "业务类型") @PathVariable String bizType) {
        String redisKey = String.format("segmentid:%s:%s", tenantId, bizType);
        redissonClient.getBucket(redisKey).delete();
        return "缓存已清理: " + redisKey;
    }
} 