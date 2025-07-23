package com.common.fileio.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.common.fileio.exception.FileOperationException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * 提供Excel文件操作相关的工具方法
 */
@Slf4j
public class ExcelUtils {
    
    /**
     * 写入Excel文件（Map数据）
     * 
     * @param filePath 文件路径
     * @param dataList 数据列表
     * @param headers 表头列表
     */
    public static void writeExcel(String filePath, List<Map<String, Object>> dataList, List<String> headers) {
        log.info("写入Excel文件: {}, 数据量: {}", filePath, dataList.size());
        
        try {
            // 创建目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 转换数据为行列表
            List<List<Object>> rows = new ArrayList<>();
            for (Map<String, Object> data : dataList) {
                List<Object> row = new ArrayList<>();
                for (String header : headers) {
                    row.add(data.get(header));
                }
                rows.add(row);
            }
            
            // 写入Excel
            try (OutputStream outputStream = new FileOutputStream(file)) {
                ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream)
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
                
                // 写入表头和数据
                writerBuilder.head(createHead(headers))
                        .sheet("Sheet1")
                        .doWrite(rows);
            }
            
            log.info("Excel文件写入成功: {}", filePath);
        } catch (IOException e) {
            log.error("写入Excel文件失败: {}", e.getMessage());
            throw new FileOperationException("写入Excel文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 写入Excel文件（对象数据）
     * 
     * @param filePath 文件路径
     * @param dataList 数据列表
     * @param clazz 数据类型
     * @param <T> 数据类型
     */
    public static <T> void writeExcel(String filePath, List<T> dataList, Class<T> clazz) {
        log.info("写入Excel文件: {}, 数据量: {}", filePath, dataList.size());
        
        try {
            // 创建目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 写入Excel
            EasyExcel.write(filePath, clazz)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet("Sheet1")
                    .doWrite(dataList);
            
            log.info("Excel文件写入成功: {}", filePath);
        } catch (Exception e) {
            log.error("写入Excel文件失败: {}", e.getMessage());
            throw new FileOperationException("写入Excel文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 分批写入Excel文件
     * 
     * @param filePath 文件路径
     * @param headers 表头列表
     * @param dataProvider 数据提供者
     * @param batchSize 批次大小
     */
    public static void writeExcelInBatches(String filePath, List<String> headers, 
                                          BatchDataProvider dataProvider, int batchSize) {
        log.info("分批写入Excel文件: {}, 批次大小: {}", filePath, batchSize);
        
        try {
            // 创建目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 写入Excel
            try (OutputStream outputStream = new FileOutputStream(file)) {
                ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream)
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
                
                // 创建写入器
                writerBuilder.head(createHead(headers))
                        .sheet("Sheet1")
                        .doWrite(() -> {
                            // 分批获取数据
                            int page = 0;
                            List<Map<String, Object>> batch;
                            List<List<Object>> rows = new ArrayList<>();
                            
                            while ((batch = dataProvider.getNextBatch(page, batchSize)) != null && !batch.isEmpty()) {
                                // 转换数据为行列表
                                for (Map<String, Object> data : batch) {
                                    List<Object> row = new ArrayList<>();
                                    for (String header : headers) {
                                        row.add(data.get(header));
                                    }
                                    rows.add(row);
                                }
                                
                                page++;
                            }
                            
                            return rows;
                        });
            }
            
            log.info("Excel文件写入成功: {}", filePath);
        } catch (IOException e) {
            log.error("写入Excel文件失败: {}", e.getMessage());
            throw new FileOperationException("写入Excel文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 创建表头
     * 
     * @param headers 表头列表
     * @return 表头行列表
     */
    private static List<List<String>> createHead(List<String> headers) {
        List<List<String>> head = new ArrayList<>();
        for (String header : headers) {
            List<String> column = new ArrayList<>();
            column.add(header);
            head.add(column);
        }
        return head;
    }
    
    /**
     * 批次数据提供者接口
     */
    public interface BatchDataProvider {
        /**
         * 获取下一批数据
         * 
         * @param page 页码（从0开始）
         * @param size 每页大小
         * @return 数据列表，如果没有更多数据则返回空列表或null
         */
        List<Map<String, Object>> getNextBatch(int page, int size);
    }
}