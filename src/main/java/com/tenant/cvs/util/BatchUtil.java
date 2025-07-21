package com.tenant.cvs.util;

import java.util.ArrayList;
import java.util.List;

public class BatchUtil {
    // 按批次分割List
    public static <T> List<List<T>> split(List<T> list, int batchSize) {
        List<List<T>> result = new ArrayList<>();
        int total = list.size();
        for (int i = 0; i < total; i += batchSize) {
            result.add(list.subList(i, Math.min(total, i + batchSize)));
        }
        return result;
    }
} 