package com.example.elasticsearch.service.impl;

import com.example.elasticsearch.config.ElasticsearchCrudProperties;
import com.example.elasticsearch.service.ElasticsearchIndexService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticsearchIndexServiceImpl implements ElasticsearchIndexService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElasticsearchIndexServiceImpl.class);

    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final ElasticsearchCrudProperties properties;

    public ElasticsearchIndexServiceImpl(RestHighLevelClient restHighLevelClient,
                                        ElasticsearchRestTemplate elasticsearchRestTemplate,
                                        ElasticsearchCrudProperties properties) {
        this.restHighLevelClient = restHighLevelClient;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.properties = properties;
    }

    @Override
    public List<Map<String, Object>> listIndices() {
        log.info("获取所有索引信息");
        List<Map<String, Object>> indices = new ArrayList<>();
        try {
            GetIndexRequest request = new GetIndexRequest("*");
            GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
            String[] indexNames = response.getIndices();
            for (String indexName : indexNames) {
                Map<String, Object> indexInfo = new HashMap<>();
                indexInfo.put("name", indexName);
                // 别名
                if (response.getAliases().containsKey(indexName)) {
                    List<String> aliases = new ArrayList<>();
                    response.getAliases().get(indexName).forEach(alias -> aliases.add(alias.alias()));
                    indexInfo.put("aliases", aliases);
                }
                // 映射
                if (response.getMappings().containsKey(indexName)) {
                    Map<String, Object> mappings = response.getMappings().get(indexName).sourceAsMap();
                    indexInfo.put("mappings", mappings);
                }
                // 设置
                if (response.getSettings().containsKey(indexName)) {
                    Map<String, Object> settings = new HashMap<>();
                    Settings s = response.getSettings().get(indexName);
                    for (String key : s.keySet()) {
                        settings.put(key, s.get(key));
                    }
                    indexInfo.put("settings", settings);
                }
                indices.add(indexInfo);
            }
            log.info("成功获取 {} 个索引", indices.size());
        } catch (IOException e) {
            log.error("获取索引列表失败", e);
        }
        return indices;
    }

    @Override
    public boolean createIndex(String indexName, Map<String, Object> mapping) {
        log.info("创建索引: {}", indexName);
        try {
            if (indexExists(indexName)) {
                log.warn("索引 {} 已存在", indexName);
                return true;
            }
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            Settings.Builder settingsBuilder = Settings.builder();
            settingsBuilder.put("number_of_shards", properties.getIndex().getDefaultShards());
            settingsBuilder.put("number_of_replicas", properties.getIndex().getDefaultReplicas());
            settingsBuilder.put("refresh_interval", properties.getIndex().getRefreshInterval());
            request.settings(settingsBuilder);
            if (mapping != null && !mapping.isEmpty()) {
                XContentBuilder builder = XContentFactory.jsonBuilder();
                builder.map(mapping);
                request.mapping(builder);
            }
            CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            boolean acknowledged = response.isAcknowledged();
            if (acknowledged) {
                log.info("索引 {} 创建成功", indexName);
            } else {
                log.warn("索引 {} 创建失败", indexName);
            }
            return acknowledged;
        } catch (IOException e) {
            log.error("创建索引 {} 失败", indexName, e);
            return false;
        }
    }

    @Override
    public boolean deleteIndex(String indexName) {
        log.info("删除索引: {}", indexName);
        try {
            if (!indexExists(indexName)) {
                log.warn("索引 {} 不存在", indexName);
                return true;
            }
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            boolean acknowledged = response.isAcknowledged();
            if (acknowledged) {
                log.info("索引 {} 删除成功", indexName);
            } else {
                log.warn("索引 {} 删除失败", indexName);
            }
            return acknowledged;
        } catch (IOException e) {
            log.error("删除索引 {} 失败", indexName, e);
            return false;
        }
    }

    @Override
    public boolean indexExists(String indexName) {
        log.info("检查索引是否存在: {}", indexName);
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);
            boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            log.info("索引 {} 存在: {}", indexName, exists);
            return exists;
        } catch (IOException e) {
            log.error("检查索引 {} 是否存在失败", indexName, e);
            return false;
        }
    }

    @Override
    public Map<String, Object> getIndexMapping(String indexName) {
        log.info("获取索引映射信息: {}", indexName);
        Map<String, Object> mapping = new HashMap<>();
        try {
            if (!indexExists(indexName)) {
                log.warn("索引 {} 不存在", indexName);
                return mapping;
            }
            GetIndexRequest request = new GetIndexRequest(indexName);
            GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
            if (response.getMappings().containsKey(indexName)) {
                mapping = response.getMappings().get(indexName).sourceAsMap();
            }
            log.info("成功获取索引 {} 的映射信息", indexName);
        } catch (IOException e) {
            log.error("获取索引 {} 映射信息失败", indexName, e);
        }
        return mapping;
    }

    @Override
    public Map<String, Object> getIndexStats(String indexName) {
        log.info("获取索引统计信息: {}", indexName);
        Map<String, Object> stats = new HashMap<>();
        try {
            if (!indexExists(indexName)) {
                log.warn("索引 {} 不存在", indexName);
                return stats;
            }
            IndicesStatsRequest request = new IndicesStatsRequest();
            request.indices(indexName);
            IndicesStatsResponse response = restHighLevelClient.indices().stats(request, RequestOptions.DEFAULT);
            if (response.getIndices().containsKey(indexName)) {
                Map<String, Object> indexStats = new HashMap<>();
                indexStats.put("docs", response.getIndices().get(indexName).getTotal().getDocs().getCount());
                indexStats.put("size", response.getIndices().get(indexName).getTotal().getStore().getSizeInBytes());
                indexStats.put("segments", response.getIndices().get(indexName).getTotal().getSegments().getCount());
                indexStats.put("indexing", response.getIndices().get(indexName).getTotal().getIndexing().getTotal());
                indexStats.put("search", response.getIndices().get(indexName).getTotal().getSearch().getTotal());
                stats.put(indexName, indexStats);
            }
            log.info("成功获取索引 {} 的统计信息", indexName);
        } catch (IOException e) {
            log.error("获取索引 {} 统计信息失败", indexName, e);
        }
        return stats;
    }

    @Override
    public Map<String, Object> getClusterHealth() {
        log.info("获取集群健康状态");
        Map<String, Object> health = new HashMap<>();
        try {
            ClusterHealthRequest request = new ClusterHealthRequest();
            ClusterHealthResponse response = restHighLevelClient.cluster().health(request, RequestOptions.DEFAULT);
            health.put("status", response.getStatus().name().toLowerCase());
            health.put("number_of_nodes", response.getNumberOfNodes());
            health.put("number_of_data_nodes", response.getNumberOfDataNodes());
            health.put("active_primary_shards", response.getActivePrimaryShards());
            health.put("active_shards", response.getActiveShards());
            health.put("relocating_shards", response.getRelocatingShards());
            health.put("initializing_shards", response.getInitializingShards());
            health.put("unassigned_shards", response.getUnassignedShards());
            health.put("delayed_unassigned_shards", response.getDelayedUnassignedShards());
            health.put("number_of_pending_tasks", response.getNumberOfPendingTasks());
            health.put("number_of_in_flight_fetch", response.getNumberOfInFlightFetch());
            log.info("成功获取集群健康状态: {}", health.get("status"));
        } catch (IOException e) {
            log.error("获取集群健康状态失败", e);
            health.put("status", "unknown");
            health.put("error", e.getMessage());
        }
        return health;
    }

    @Override
    public boolean refreshIndex(String indexName) {
        log.info("刷新索引: {}", indexName);
        try {
            if (!indexExists(indexName)) {
                log.warn("索引 {} 不存在", indexName);
                return false;
            }
            RefreshRequest request = new RefreshRequest(indexName);
            RefreshResponse response = restHighLevelClient.indices().refresh(request, RequestOptions.DEFAULT);
            boolean success = response.getStatus() == RestStatus.OK;
            if (success) {
                log.info("索引 {} 刷新成功", indexName);
            } else {
                log.warn("索引 {} 刷新失败", indexName);
            }
            return success;
        } catch (IOException e) {
            log.error("刷新索引 {} 失败", indexName, e);
            return false;
        }
    }
}