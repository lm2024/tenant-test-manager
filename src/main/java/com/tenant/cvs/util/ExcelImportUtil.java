package com.tenant.cvs.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tenant.entity.TestCase;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.io.File;
import java.util.*;
import java.util.function.Consumer;

/**
 * 支持sheet别名映射的Excel导入工具
 */
public class ExcelImportUtil {
    // sheet名 -> (别名 -> 实体字段名)
    private static final Map<String, Map<String, String>> SHEET_FIELD_MAPPING = new HashMap<>();
    static {
        Map<String, String> testCaseMap = new HashMap<>();
        testCaseMap.put("ID", "id");
        testCaseMap.put("标题", "title");
        testCaseMap.put("描述", "description");
        SHEET_FIELD_MAPPING.put("test_case", testCaseMap);
        // 可扩展更多sheet
    }

    /**
     * 只读取指定sheet
     */
    public static void readExcelSheet(String filePath, String sheetName, Consumer<List<TestCase>> consumer, int batchSize) {
        Map<String, String> aliasToField = SHEET_FIELD_MAPPING.get(sheetName);
        if (aliasToField == null) throw new RuntimeException("未配置sheet映射: " + sheetName);
        EasyExcel.read(new File(filePath))
            .sheet(sheetName)
            .headRowNumber(0)
            .registerReadListener(new DynamicHeaderListener<>(aliasToField, TestCase.class, batchSize, consumer))
            .doRead();
    }

    /**
     * 动态表头监听器，支持别名-字段映射+反射赋值
     */
    public static class DynamicHeaderListener<T> extends AnalysisEventListener<Map<Integer, String>> {
        private final Map<String, String> aliasToField;
        private final Class<T> clazz;
        private final int batchSize;
        private final Consumer<List<T>> consumer;
        private final List<T> batch = new ArrayList<>();
        private Map<Integer, String> colIndexToField = new HashMap<>();
        private boolean headerParsed = false;

        public DynamicHeaderListener(Map<String, String> aliasToField, Class<T> clazz, int batchSize, Consumer<List<T>> consumer) {
            this.aliasToField = aliasToField;
            this.clazz = clazz;
            this.batchSize = batchSize;
            this.consumer = consumer;
        }

        @Override
        public void invoke(Map<Integer, String> rowData, AnalysisContext context) {
            if (!headerParsed) {
                // 解析表头，建立列索引-实体字段名映射
                for (Map.Entry<Integer, String> entry : rowData.entrySet()) {
                    String alias = entry.getValue();
                    String field = aliasToField.get(alias);
                    if (field != null) {
                        colIndexToField.put(entry.getKey(), field);
                    }
                }
                headerParsed = true;
                return;
            }
            try {
                T entity = clazz.getDeclaredConstructor().newInstance();
                BeanWrapper wrapper = new BeanWrapperImpl(entity);
                for (Map.Entry<Integer, String> entry : colIndexToField.entrySet()) {
                    String value = rowData.get(entry.getKey());
                    if (value != null) {
                        wrapper.setPropertyValue(entry.getValue(), value);
                    }
                }
                batch.add(entity);
                if (batch.size() >= batchSize) {
                    consumer.accept(new ArrayList<>(batch));
                    batch.clear();
                }
            } catch (Exception e) {
                // 可记录日志
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            if (!batch.isEmpty()) {
                consumer.accept(new ArrayList<>(batch));
            }
        }
    }
} 