package com.common.fileio.util;

import com.common.fileio.exception.FileOperationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CSV工具类
 * 提供CSV文件操作相关的工具方法
 */
@Slf4j
public class CsvUtils {
    
    /**
     * 写入CSV文件
     * 
     * @param filePath 文件路径
     * @param dataList 数据列表
     * @param headers 表头列表
     */
    public static void writeCsv(String filePath, List<Map<String, Object>> dataList, List<String> headers) {
        log.info("写入CSV文件: {}, 数据量: {}", filePath, dataList.size());
        
        try {
            // 创建目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 创建CSV格式
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers.toArray(new String[0]))
                    .build();
            
            // 写入CSV
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
                 CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
                
                // 写入数据
                for (Map<String, Object> data : dataList) {
                    List<Object> row = new ArrayList<>();
                    for (String header : headers) {
                        row.add(data.get(header));
                    }
                    printer.printRecord(row);
                }
                
                printer.flush();
            }
            
            log.info("CSV文件写入成功: {}", filePath);
        } catch (IOException e) {
            log.error("写入CSV文件失败: {}", e.getMessage());
            throw new FileOperationException("写入CSV文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 分批写入CSV文件
     * 
     * @param filePath 文件路径
     * @param headers 表头列表
     * @param dataProvider 数据提供者
     * @param batchSize 批次大小
     */
    public static void writeCsvInBatches(String filePath, List<String> headers, 
                                        BatchDataProvider dataProvider, int batchSize) {
        log.info("分批写入CSV文件: {}, 批次大小: {}", filePath, batchSize);
        
        try {
            // 创建目录
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 创建CSV格式
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(headers.toArray(new String[0]))
                    .build();
            
            // 写入CSV
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
                 CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
                
                // 分批获取数据
                int page = 0;
                List<Map<String, Object>> batch;
                
                while ((batch = dataProvider.getNextBatch(page, batchSize)) != null && !batch.isEmpty()) {
                    // 写入数据
                    for (Map<String, Object> data : batch) {
                        List<Object> row = new ArrayList<>();
                        for (String header : headers) {
                            row.add(data.get(header));
                        }
                        printer.printRecord(row);
                    }
                    
                    printer.flush();
                    page++;
                }
            }
            
            log.info("CSV文件写入成功: {}", filePath);
        } catch (IOException e) {
            log.error("写入CSV文件失败: {}", e.getMessage());
            throw new FileOperationException("写入CSV文件失败: " + e.getMessage(), e);
        }
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