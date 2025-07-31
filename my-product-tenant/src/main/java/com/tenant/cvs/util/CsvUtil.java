package com.tenant.cvs.util;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class CsvUtil {
    // 流式读取CSV
    public static void readCsv(String filePath, Consumer<CSVRecord> consumer) throws IOException {
        try (Reader in = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().setHeader().build().parse(in);
            for (CSVRecord record : records) consumer.accept(record);
        }
    }
    // 写入CSV
    public static void writeCsv(String filePath, List<String> headers, List<List<String>> rows) throws IOException {
        try (Writer out = new FileWriter(filePath);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))) {
            for (List<String> row : rows) printer.printRecord(row);
        }
    }
} 