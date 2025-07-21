package com.tenant.cvs.util;

import java.io.*;
import java.util.function.Consumer;

public class TxtUtil {
    public static void readTxt(String filePath, Consumer<String> consumer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) consumer.accept(line);
        }
    }
    public static void writeTxt(String filePath, Iterable<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) bw.write(line + System.lineSeparator());
        }
    }
} 