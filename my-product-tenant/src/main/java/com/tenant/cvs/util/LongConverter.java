package com.tenant.cvs.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.math.BigDecimal;

/**
 * Long类型转换器，处理空值和格式转换
 */
public class LongConverter implements Converter<Long> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Long convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (cellData.getType() == CellDataTypeEnum.EMPTY) {
            return null;
        }
        
        String stringValue = cellData.getStringValue();
        if (stringValue == null || stringValue.trim().isEmpty()) {
            return null;
        }
        
        try {
            // 尝试直接转换为Long
            return Long.valueOf(stringValue.trim());
        } catch (NumberFormatException e) {
            try {
                // 尝试通过BigDecimal转换
                BigDecimal bigDecimal = new BigDecimal(stringValue.trim());
                return bigDecimal.longValue();
            } catch (Exception ex) {
                // 如果都失败，返回null
                System.out.println("无法转换单元格值为Long: " + stringValue);
                return null;
            }
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(Long value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (value == null) {
            return new WriteCellData<>("");
        }
        return new WriteCellData<>(value.toString());
    }
} 