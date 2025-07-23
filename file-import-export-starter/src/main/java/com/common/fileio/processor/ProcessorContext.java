package com.common.fileio.processor;

import com.common.fileio.exception.ProcessorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理器上下文
 * 管理所有数据处理器
 */
@Slf4j
public class ProcessorContext {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    /**
     * 处理器映射（处理器名称 -> 处理器实例）
     */
    private final Map<String, DataProcessor<?>> processorMap = new HashMap<>();
    
    /**
     * 初始化处理器上下文
     * 自动扫描并注册所有DataProcessor实现
     */
    @PostConstruct
    public void init() {
        log.info("初始化数据处理器上下文");
        
        // 获取所有DataProcessor实现
        Map<String, DataProcessor> processors = applicationContext.getBeansOfType(DataProcessor.class);
        
        // 注册所有处理器
        for (DataProcessor<?> processor : processors.values()) {
            String name = processor.getProcessorName();
            log.info("注册数据处理器: {}, 实体类型: {}", name, processor.getEntityClass().getSimpleName());
            processorMap.put(name, processor);
        }
        
        log.info("数据处理器注册完成，共 {} 个处理器", processorMap.size());
    }
    
    /**
     * 获取处理器
     * 
     * @param processorName 处理器名称
     * @return 数据处理器
     * @throws ProcessorNotFoundException 如果处理器不存在
     */
    public DataProcessor<?> getProcessor(String processorName) {
        DataProcessor<?> processor = processorMap.get(processorName);
        if (processor == null) {
            throw new ProcessorNotFoundException(processorName);
        }
        return processor;
    }
    
    /**
     * 获取处理器（带类型转换）
     * 
     * @param processorName 处理器名称
     * @param entityClass 实体类型
     * @param <T> 实体类型
     * @return 数据处理器
     * @throws ProcessorNotFoundException 如果处理器不存在
     * @throws ClassCastException 如果类型不匹配
     */
    @SuppressWarnings("unchecked")
    public <T> DataProcessor<T> getProcessor(String processorName, Class<T> entityClass) {
        DataProcessor<?> processor = getProcessor(processorName);
        
        // 验证实体类型
        if (!processor.getEntityClass().equals(entityClass)) {
            throw new ClassCastException("处理器 " + processorName + " 的实体类型 " + 
                    processor.getEntityClass().getName() + " 与请求的类型 " + 
                    entityClass.getName() + " 不匹配");
        }
        
        return (DataProcessor<T>) processor;
    }
    
    /**
     * 获取所有处理器
     * 
     * @return 处理器映射
     */
    public Map<String, DataProcessor<?>> getAllProcessors() {
        return new HashMap<>(processorMap);
    }
    
    /**
     * 注册处理器
     * 
     * @param processor 数据处理器
     */
    public void registerProcessor(DataProcessor<?> processor) {
        String name = processor.getProcessorName();
        log.info("手动注册数据处理器: {}, 实体类型: {}", name, processor.getEntityClass().getSimpleName());
        processorMap.put(name, processor);
    }
}