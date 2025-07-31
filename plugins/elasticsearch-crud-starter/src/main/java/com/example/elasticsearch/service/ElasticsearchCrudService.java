package com.example.elasticsearch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Elasticsearch CRUD 服务接口
 * 
 * @author Kiro
 */
public interface ElasticsearchCrudService<T> {

    /**
     * 保存单个实体
     * 
     * @param entity 实体对象
     * @return 保存后的实体
     */
    T save(T entity);

    /**
     * 批量保存实体
     * 
     * @param entities 实体列表
     * @return 保存后的实体列表
     */
    List<T> saveAll(List<T> entities);

    /**
     * 根据ID查询实体
     * 
     * @param id 实体ID
     * @return 实体对象（可能为空）
     */
    Optional<T> findById(String id);

    /**
     * 根据ID删除实体
     * 
     * @param id 实体ID
     */
    void deleteById(String id);

    /**
     * 批量删除实体
     * 
     * @param ids ID列表
     */
    void deleteByIds(List<String> ids);

    /**
     * 更新实体
     * 
     * @param id 实体ID
     * @param entity 更新的实体数据
     * @return 更新后的实体
     */
    T update(String id, T entity);

    /**
     * 分页查询所有实体
     * 
     * @param pageable 分页参数
     * @return 分页结果
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 根据条件搜索实体
     * 
     * @param keyword 搜索关键词
     * @param pageable 分页参数
     * @return 搜索结果
     */
    Page<T> search(String keyword, Pageable pageable);

    /**
     * 统计实体总数
     * 
     * @return 实体总数
     */
    long count();

    /**
     * 检查实体是否存在
     * 
     * @param id 实体ID
     * @return 是否存在
     */
    boolean existsById(String id);
}