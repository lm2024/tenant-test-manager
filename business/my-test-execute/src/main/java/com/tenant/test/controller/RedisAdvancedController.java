package com.tenant.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Redis高级功能控制器
 * 提供Redis的增删改查、模糊查询、目录列表等高级功能
 * 
 * @author Kiro
 */
@Slf4j
@RestController
@RequestMapping("/api/redis")
@Tag(name = "Redis高级功能", description = "Redis增删改查、模糊查询、目录列表等高级功能接口")
public class RedisAdvancedController {

    @Autowired
    private RedissonClient redissonClient;

    // ================================
    // 基础CRUD操作
    // ================================

    @Operation(summary = "设置键值对", description = "设置Redis键值对，支持过期时间")
    @PostMapping("/set")
    public ResponseEntity<Map<String, Object>> setKey(
            @Parameter(description = "键名") @RequestParam String key,
            @Parameter(description = "值") @RequestParam String value,
            @Parameter(description = "过期时间(秒)，-1表示永不过期") @RequestParam(defaultValue = "-1") long expireSeconds) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBucket<String> bucket = redissonClient.getBucket(key);
            
            if (expireSeconds > 0) {
                bucket.set(value, expireSeconds, TimeUnit.SECONDS);
            } else {
                bucket.set(value);
            }
            
            result.put("status", "success");
            result.put("message", "键值对设置成功");
            result.put("key", key);
            result.put("value", value);
            result.put("expireSeconds", expireSeconds);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis设置键值对成功，key: {}, value: {}, expire: {}秒", key, value, expireSeconds);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "设置键值对失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis设置键值对失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取键值", description = "根据键名获取值")
    @GetMapping("/get/{key}")
    public ResponseEntity<Map<String, Object>> getKey(
            @Parameter(description = "键名") @PathVariable String key) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBucket<String> bucket = redissonClient.getBucket(key);
            String value = bucket.get();
            
            if (value != null) {
                result.put("status", "success");
                result.put("message", "获取值成功");
                result.put("key", key);
                result.put("value", value);
                result.put("exists", true);
                
                // 获取TTL
                long ttl = bucket.remainTimeToLive();
                result.put("ttl", ttl);
                result.put("ttlFormatted", ttl > 0 ? formatTtl(ttl) : "永不过期");
                
            } else {
                result.put("status", "not_found");
                result.put("message", "键不存在");
                result.put("key", key);
                result.put("exists", false);
            }
            
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取键值，key: {}, exists: {}", key, value != null);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取值失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取键值失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "删除键", description = "删除指定的键")
    @DeleteMapping("/delete/{key}")
    public ResponseEntity<Map<String, Object>> deleteKey(
            @Parameter(description = "键名") @PathVariable String key) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean deleted = bucket.delete();
            
            result.put("status", "success");
            result.put("message", deleted ? "键删除成功" : "键不存在");
            result.put("key", key);
            result.put("deleted", deleted);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis删除键，key: {}, deleted: {}", key, deleted);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "删除键失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis删除键失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "检查键是否存在", description = "检查指定的键是否存在")
    @GetMapping("/exists/{key}")
    public ResponseEntity<Map<String, Object>> existsKey(
            @Parameter(description = "键名") @PathVariable String key) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBucket<String> bucket = redissonClient.getBucket(key);
            boolean exists = bucket.isExists();
            
            result.put("status", "success");
            result.put("message", "检查键存在性成功");
            result.put("key", key);
            result.put("exists", exists);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis检查键存在性，key: {}, exists: {}", key, exists);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "检查键存在性失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis检查键存在性失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 批量操作
    // ================================

    @Operation(summary = "批量设置键值对", description = "批量设置多个键值对")
    @PostMapping("/batch-set")
    public ResponseEntity<Map<String, Object>> batchSetKeys(@RequestBody Map<String, String> keyValueMap) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                batch.getBucket(entry.getKey()).setAsync(entry.getValue());
            }
            
            BatchResult<?> batchResult = batch.execute();
            
            result.put("status", "success");
            result.put("message", "批量设置键值对成功");
            result.put("count", keyValueMap.size());
            result.put("keys", new ArrayList<>(keyValueMap.keySet()));
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis批量设置键值对成功，数量: {}", keyValueMap.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "批量设置键值对失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis批量设置键值对失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "批量获取键值", description = "批量获取多个键的值")
    @PostMapping("/batch-get")
    public ResponseEntity<Map<String, Object>> batchGetKeys(@RequestBody List<String> keys) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (String key : keys) {
                batch.getBucket(key).getAsync();
            }
            
            BatchResult<?> batchResult = batch.execute();
            List<?> values = batchResult.getResponses();
            
            Map<String, Object> keyValueMap = new HashMap<>();
            for (int i = 0; i < keys.size(); i++) {
                keyValueMap.put(keys.get(i), values.get(i));
            }
            
            result.put("status", "success");
            result.put("message", "批量获取键值成功");
            result.put("count", keys.size());
            result.put("data", keyValueMap);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis批量获取键值成功，数量: {}", keys.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "批量获取键值失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis批量获取键值失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "批量删除键", description = "批量删除多个键")
    @DeleteMapping("/batch-delete")
    public ResponseEntity<Map<String, Object>> batchDeleteKeys(@RequestBody List<String> keys) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            RBatch batch = redissonClient.createBatch();
            
            for (String key : keys) {
                batch.getBucket(key).deleteAsync();
            }
            
            BatchResult<?> batchResult = batch.execute();
            List<?> responses = batchResult.getResponses();
            
            long deletedCount = responses.stream()
                    .mapToLong(response -> (Boolean) response ? 1 : 0)
                    .sum();
            
            result.put("status", "success");
            result.put("message", "批量删除键成功");
            result.put("totalKeys", keys.size());
            result.put("deletedCount", deletedCount);
            result.put("keys", keys);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis批量删除键成功，总数: {}, 删除数: {}", keys.size(), deletedCount);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "批量删除键失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis批量删除键失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 模糊查询和目录列表
    // ================================

    @Operation(summary = "模糊查询键", description = "根据模式模糊查询键")
    @GetMapping("/keys/search")
    public ResponseEntity<Map<String, Object>> searchKeys(
            @Parameter(description = "查询模式，支持通配符 * ?") @RequestParam String pattern,
            @Parameter(description = "最大返回数量") @RequestParam(defaultValue = "100") int limit) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            Iterable<String> keys = redissonClient.getKeys().getKeysByPattern(pattern);
            List<String> keyList = new ArrayList<>();
            
            int count = 0;
            for (String key : keys) {
                if (count >= limit) break;
                keyList.add(key);
                count++;
            }
            
            result.put("status", "success");
            result.put("message", "模糊查询键成功");
            result.put("pattern", pattern);
            result.put("limit", limit);
            result.put("count", keyList.size());
            result.put("keys", keyList);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis模糊查询键成功，pattern: {}, 结果数: {}", pattern, keyList.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "模糊查询键失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis模糊查询键失败，pattern: {}", pattern, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "模糊查询键(JSON树)", description = "根据模式模糊查询键并以JSON树结构展示")
    @GetMapping("/keys/search-tree")
    public ResponseEntity<Map<String, Object>> searchKeysAsTree(
            @Parameter(description = "查询模式，支持通配符 * ?") @RequestParam String pattern,
            @Parameter(description = "分隔符，支持 : 或 ::") @RequestParam(defaultValue = ":") String separator,
            @Parameter(description = "最大返回数量") @RequestParam(defaultValue = "100") int limit,
            @Parameter(description = "最大树深度") @RequestParam(defaultValue = "5") int maxDepth) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            Iterable<String> keys = redissonClient.getKeys().getKeysByPattern(pattern);
            List<String> keyList = new ArrayList<>();
            
            int count = 0;
            for (String key : keys) {
                if (count >= limit) break;
                keyList.add(key);
                count++;
            }
            
            // 构建JSON树结构
            Map<String, Object> treeStructure = buildJsonTree(keyList, separator, maxDepth);
            
            result.put("status", "success");
            result.put("message", "模糊查询键(JSON树)成功");
            result.put("pattern", pattern);
            result.put("separator", separator);
            result.put("limit", limit);
            result.put("maxDepth", maxDepth);
            result.put("count", keyList.size());
            result.put("keys", keyList);
            result.put("tree", treeStructure);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis模糊查询键(JSON树)成功，pattern: {}, 结果数: {}", pattern, keyList.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "模糊查询键(JSON树)失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis模糊查询键(JSON树)失败，pattern: {}", pattern, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "高级模糊查询", description = "支持多种分隔符的高级模糊查询")
    @GetMapping("/keys/search-advanced")
    public ResponseEntity<Map<String, Object>> searchKeysAdvanced(
            @Parameter(description = "查询模式，支持通配符 * ?") @RequestParam String pattern,
            @Parameter(description = "分隔符类型：single(单个:), double(双冒号::), custom(自定义)") @RequestParam(defaultValue = "single") String separatorType,
            @Parameter(description = "自定义分隔符，当separatorType=custom时使用") @RequestParam(required = false) String customSeparator,
            @Parameter(description = "最大返回数量") @RequestParam(defaultValue = "100") int limit,
            @Parameter(description = "是否返回树结构") @RequestParam(defaultValue = "true") boolean returnTree,
            @Parameter(description = "最大树深度") @RequestParam(defaultValue = "5") int maxDepth) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 确定分隔符
            String separator = determineSeparator(separatorType, customSeparator);
            
            Iterable<String> keys = redissonClient.getKeys().getKeysByPattern(pattern);
            List<String> keyList = new ArrayList<>();
            
            int count = 0;
            for (String key : keys) {
                if (count >= limit) break;
                keyList.add(key);
                count++;
            }
            
            result.put("status", "success");
            result.put("message", "高级模糊查询成功");
            result.put("pattern", pattern);
            result.put("separatorType", separatorType);
            result.put("separator", separator);
            result.put("limit", limit);
            result.put("count", keyList.size());
            result.put("keys", keyList);
            
            if (returnTree) {
                Map<String, Object> treeStructure = buildJsonTree(keyList, separator, maxDepth);
                result.put("tree", treeStructure);
                result.put("maxDepth", maxDepth);
            }
            
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis高级模糊查询成功，pattern: {}, separator: {}, 结果数: {}", pattern, separator, keyList.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "高级模糊查询失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis高级模糊查询失败，pattern: {}", pattern, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取键统计信息", description = "获取Redis键的统计信息")
    @GetMapping("/keys/stats")
    public ResponseEntity<Map<String, Object>> getKeysStats() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            RKeys keys = redissonClient.getKeys();
            long totalKeys = keys.count();
            
            // 按类型统计
            Map<String, Long> typeStats = new HashMap<>();
            long stringCount = 0;
            for (String key : keys.getKeysByPattern("*")) {
                if (redissonClient.getBucket(key).isExists()) {
                    stringCount++;
                }
            }
            typeStats.put("string", stringCount);
            
            // 按前缀统计
            Map<String, Long> prefixStats = new HashMap<>();
            String[] commonPrefixes = {"user:", "session:", "cache:", "lock:", "queue:"};
            for (String prefix : commonPrefixes) {
                long count = keys.getKeysByPattern(prefix + "*").spliterator().getExactSizeIfKnown();
                if (count > 0) {
                    prefixStats.put(prefix, count);
                }
            }
            
            result.put("status", "success");
            result.put("message", "获取键统计信息成功");
            result.put("totalKeys", totalKeys);
            result.put("typeStats", typeStats);
            result.put("prefixStats", prefixStats);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取键统计信息成功，总键数: {}", totalKeys);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取键统计信息失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取键统计信息失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取目录结构", description = "获取Redis键的目录结构")
    @GetMapping("/keys/directory")
    public ResponseEntity<Map<String, Object>> getKeysDirectory(
            @Parameter(description = "目录分隔符") @RequestParam(defaultValue = ":") String separator,
            @Parameter(description = "最大深度") @RequestParam(defaultValue = "3") int maxDepth) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RKeys keys = redissonClient.getKeys();
            Iterable<String> allKeys = keys.getKeys();
            
            Map<String, Object> directory = new HashMap<>();
            Set<String> directories = new HashSet<>();
            
            for (String key : allKeys) {
                String[] parts = key.split(separator);
                StringBuilder path = new StringBuilder();
                
                for (int i = 0; i < Math.min(parts.length, maxDepth); i++) {
                    if (i > 0) path.append(separator);
                    path.append(parts[i]);
                    
                    String currentPath = path.toString();
                    directories.add(currentPath);
                    
                    if (i == maxDepth - 1 || i == parts.length - 1) {
                        // 这是叶子节点
                        if (!directory.containsKey(currentPath)) {
                            directory.put(currentPath, new HashMap<>());
                        }
                    }
                }
            }
            
            // 构建目录树
            Map<String, Object> directoryTree = buildDirectoryTree(directories, separator);
            
            result.put("status", "success");
            result.put("message", "获取目录结构成功");
            result.put("separator", separator);
            result.put("maxDepth", maxDepth);
            result.put("totalDirectories", directories.size());
            result.put("directoryTree", directoryTree);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取目录结构成功，目录数: {}", directories.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取目录结构失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取目录结构失败", e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 高级数据类型操作
    // ================================

    @Operation(summary = "设置Hash字段", description = "设置Hash类型的字段值")
    @PostMapping("/hash/set")
    public ResponseEntity<Map<String, Object>> setHashField(
            @Parameter(description = "Hash键名") @RequestParam String key,
            @Parameter(description = "字段名") @RequestParam String field,
            @Parameter(description = "字段值") @RequestParam String value) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RMap<String, String> map = redissonClient.getMap(key);
            String oldValue = map.put(field, value);
            
            result.put("status", "success");
            result.put("message", "Hash字段设置成功");
            result.put("key", key);
            result.put("field", field);
            result.put("value", value);
            result.put("oldValue", oldValue);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis设置Hash字段成功，key: {}, field: {}, value: {}", key, field, value);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "设置Hash字段失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis设置Hash字段失败，key: {}, field: {}", key, field, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取Hash字段", description = "获取Hash类型的字段值")
    @GetMapping("/hash/get/{key}/{field}")
    public ResponseEntity<Map<String, Object>> getHashField(
            @Parameter(description = "Hash键名") @PathVariable String key,
            @Parameter(description = "字段名") @PathVariable String field) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RMap<String, String> map = redissonClient.getMap(key);
            String value = map.get(field);
            
            if (value != null) {
                result.put("status", "success");
                result.put("message", "获取Hash字段成功");
                result.put("key", key);
                result.put("field", field);
                result.put("value", value);
                result.put("exists", true);
            } else {
                result.put("status", "not_found");
                result.put("message", "Hash字段不存在");
                result.put("key", key);
                result.put("field", field);
                result.put("exists", false);
            }
            
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取Hash字段，key: {}, field: {}, exists: {}", key, field, value != null);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取Hash字段失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取Hash字段失败，key: {}, field: {}", key, field, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取Hash所有字段", description = "获取Hash类型的所有字段")
    @GetMapping("/hash/get-all/{key}")
    public ResponseEntity<Map<String, Object>> getAllHashFields(
            @Parameter(description = "Hash键名") @PathVariable String key) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RMap<String, String> map = redissonClient.getMap(key);
            Map<String, String> allFields = map.readAllMap();
            
            result.put("status", "success");
            result.put("message", "获取Hash所有字段成功");
            result.put("key", key);
            result.put("count", allFields.size());
            result.put("data", allFields);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取Hash所有字段成功，key: {}, 字段数: {}", key, allFields.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取Hash所有字段失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取Hash所有字段失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "设置List元素", description = "向List类型添加元素")
    @PostMapping("/list/add")
    public ResponseEntity<Map<String, Object>> addToList(
            @Parameter(description = "List键名") @RequestParam String key,
            @Parameter(description = "元素值") @RequestParam String value,
            @Parameter(description = "是否添加到头部") @RequestParam(defaultValue = "false") boolean addToHead) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RList<String> list = redissonClient.getList(key);
            int index;
            
            if (addToHead) {
                list.add(0, value);
                index = 0;
            } else {
                index = list.size();
                list.add(value);
            }
            
            result.put("status", "success");
            result.put("message", "List元素添加成功");
            result.put("key", key);
            result.put("value", value);
            result.put("index", index);
            result.put("addToHead", addToHead);
            result.put("listSize", list.size());
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis添加List元素成功，key: {}, value: {}, index: {}", key, value, index);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "添加List元素失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis添加List元素失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取List元素", description = "获取List类型的元素")
    @GetMapping("/list/get/{key}")
    public ResponseEntity<Map<String, Object>> getListElements(
            @Parameter(description = "List键名") @PathVariable String key,
            @Parameter(description = "起始索引") @RequestParam(defaultValue = "0") int start,
            @Parameter(description = "结束索引，-1表示到末尾") @RequestParam(defaultValue = "-1") int end) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RList<String> list = redissonClient.getList(key);
            int size = list.size();
            
            List<String> elements;
            if (end == -1) {
                elements = list.range(start, size - 1);
            } else {
                elements = list.range(start, end);
            }
            
            result.put("status", "success");
            result.put("message", "获取List元素成功");
            result.put("key", key);
            result.put("start", start);
            result.put("end", end);
            result.put("size", size);
            result.put("count", elements.size());
            result.put("data", elements);
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取List元素成功，key: {}, 范围: [{}, {}], 结果数: {}", key, start, end, elements.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取List元素失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取List元素失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "设置Set元素", description = "向Set类型添加元素")
    @PostMapping("/set/add")
    public ResponseEntity<Map<String, Object>> addToSet(
            @Parameter(description = "Set键名") @RequestParam String key,
            @Parameter(description = "元素值") @RequestParam String value) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RSet<String> set = redissonClient.getSet(key);
            boolean added = set.add(value);
            
            result.put("status", "success");
            result.put("message", "Set元素添加成功");
            result.put("key", key);
            result.put("value", value);
            result.put("added", added);
            result.put("setSize", set.size());
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis添加Set元素成功，key: {}, value: {}, added: {}", key, value, added);
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "添加Set元素失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis添加Set元素失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "获取Set元素", description = "获取Set类型的所有元素")
    @GetMapping("/set/get/{key}")
    public ResponseEntity<Map<String, Object>> getSetElements(
            @Parameter(description = "Set键名") @PathVariable String key) {
        
        Map<String, Object> result = new HashMap<>();
        
        try {
            RSet<String> set = redissonClient.getSet(key);
            Set<String> elements = set.readAll();
            
            result.put("status", "success");
            result.put("message", "获取Set元素成功");
            result.put("key", key);
            result.put("size", elements.size());
            result.put("data", new ArrayList<>(elements));
            result.put("timestamp", LocalDateTime.now());
            
            log.info("Redis获取Set元素成功，key: {}, 元素数: {}", key, elements.size());
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "获取Set元素失败: " + e.getMessage());
            result.put("timestamp", LocalDateTime.now());
            
            log.error("Redis获取Set元素失败，key: {}", key, e);
            return ResponseEntity.status(500).body(result);
        }
    }

    // ================================
    // 工具方法
    // ================================

    /**
     * 格式化TTL时间
     */
    private String formatTtl(long ttl) {
        if (ttl <= 0) return "永不过期";
        
        long days = ttl / (24 * 60 * 60 * 1000);
        long hours = (ttl % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutes = (ttl % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (ttl % (60 * 1000)) / 1000;
        
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("天");
        if (hours > 0) sb.append(hours).append("小时");
        if (minutes > 0) sb.append(minutes).append("分钟");
        if (seconds > 0) sb.append(seconds).append("秒");
        
        return sb.toString();
    }

    /**
     * 构建目录树
     */
    private Map<String, Object> buildDirectoryTree(Set<String> directories, String separator) {
        Map<String, Object> tree = new HashMap<>();
        
        for (String dir : directories) {
            String[] parts = dir.split(separator);
            Map<String, Object> current = tree;
            
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                if (i > 0) path.append(separator);
                path.append(parts[i]);
                
                String currentPath = path.toString();
                if (!current.containsKey(parts[i])) {
                    current.put(parts[i], new HashMap<>());
                }
                current = (Map<String, Object>) current.get(parts[i]);
            }
        }
        
        return tree;
    }

    /**
     * 构建JSON树结构
     */
    private Map<String, Object> buildJsonTree(List<String> keys, String separator, int maxDepth) {
        Map<String, Object> tree = new HashMap<>();
        
        for (String key : keys) {
            String[] parts = key.split(separator);
            Map<String, Object> current = tree;
            
            for (int i = 0; i < Math.min(parts.length, maxDepth); i++) {
                String part = parts[i];
                if (!current.containsKey(part)) {
                    Map<String, Object> node = new HashMap<>();
                    node.put("_type", "node");
                    node.put("_path", String.join(separator, Arrays.copyOfRange(parts, 0, i + 1)));
                    node.put("_children", new HashMap<>());
                    current.put(part, node);
                }
                
                Map<String, Object> node = (Map<String, Object>) current.get(part);
                Map<String, Object> children = (Map<String, Object>) node.get("_children");
                
                if (i == Math.min(parts.length, maxDepth) - 1) {
                    // 叶子节点，添加键信息
                    if (!children.containsKey("_keys")) {
                        children.put("_keys", new ArrayList<>());
                    }
                    List<String> keyList = (List<String>) children.get("_keys");
                    keyList.add(key);
                }
                
                current = children;
            }
        }
        
        return tree;
    }

    /**
     * 确定分隔符
     */
    private String determineSeparator(String separatorType, String customSeparator) {
        switch (separatorType.toLowerCase()) {
            case "single":
                return ":";
            case "double":
                return "::";
            case "custom":
                if (customSeparator != null && !customSeparator.isEmpty()) {
                    return customSeparator;
                } else {
                    throw new IllegalArgumentException("自定义分隔符不能为空");
                }
            default:
                return ":";
        }
    }
} 