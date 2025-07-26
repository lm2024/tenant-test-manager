package com.tenant.test.controller;

import com.common.segmentid.service.SegmentIdService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/segmentid")
public class SegmentIdTestController {
    @Autowired
    private SegmentIdService segmentIdService;
    
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/generate/{tenantId}/{bizType}")
    public String generateId(@PathVariable String tenantId, @PathVariable String bizType) {
        return segmentIdService.generateId(tenantId, bizType);
    }

    @GetMapping("/generateBatch/{tenantId}/{bizType}/{count}")
    public List<String> generateBatch(@PathVariable String tenantId, @PathVariable String bizType, @PathVariable int count) {
        return segmentIdService.generateBatchIds(tenantId, bizType, count);
    }

    @GetMapping("/info/{tenantId}/{bizType}")
    public Object getSequenceInfo(@PathVariable String tenantId, @PathVariable String bizType) {
        return segmentIdService.getSequenceInfo(tenantId, bizType);
    }

    @DeleteMapping("/cache/{tenantId}/{bizType}")
    public String clearCache(@PathVariable String tenantId, @PathVariable String bizType) {
        String redisKey = String.format("segmentid:%s:%s", tenantId, bizType);
        redissonClient.getBucket(redisKey).delete();
        return "缓存已清理: " + redisKey;
    }
} 