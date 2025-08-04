package com.example.elasticsearch.service;

import java.util.List;

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
    T findById(String id);

    /**
     * 根据ID删除实体
     * 
     * @param id 实体ID
     * @return 是否删除成功
     */
    boolean deleteById(String id);

    /**
     * 批量删除实体
     * 
     * @param ids ID列表
     * @return 是否删除成功
     */
    boolean deleteByIds(List<String> ids);

    /**
     * 更新实体
     * 
     * @param entity 更新的实体数据
     * @return 更新后的实体
     */
    T update(T entity);

    /**
     * 查询所有实体
     * 
     * @return 实体列表
     */
    List<T> findAll();

    /**
     * 根据字段搜索实体
     * 
     * @param field 搜索字段
     * @param value 搜索值
     * @return 搜索结果
     */
    List<T> search(String field, String value);

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