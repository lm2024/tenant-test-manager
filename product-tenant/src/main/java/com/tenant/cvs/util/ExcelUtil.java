package com.tenant.cvs.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ExcelUtil {
    // 流式读取Excel，每批batchSize条回调一次
    public static <T> void readExcel(String filePath, Class<T> clazz, int batchSize, Consumer<List<T>> consumer) {
        EasyExcel.read(new File(filePath), clazz, new PageReadListener<T>(consumer, batchSize)).sheet().doRead();
    }
    // 流式写入Excel
    public static <T> void writeExcel(String filePath, List<T> data, Class<T> clazz) {
        EasyExcel.write(filePath, clazz).sheet("Sheet1").doWrite(data);
    }
} 