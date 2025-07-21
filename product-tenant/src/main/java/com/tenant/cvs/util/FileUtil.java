package com.tenant.cvs.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    // 保存多文件，返回保存路径
    public static List<String> saveFiles(List<MultipartFile> files, String uploadDir) throws IOException {
        List<String> paths = new ArrayList<>();
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
        for (MultipartFile file : files) {
            String ext = getFileExtension(file.getOriginalFilename());
            String name = UUID.randomUUID().toString() + (ext.isEmpty() ? "" : "." + ext);
            String path = uploadDir + File.separator + name;
            file.transferTo(new File(path));
            paths.add(path);
        }
        return paths;
    }
    // 获取文件扩展名
    public static String getFileExtension(String filename) {
        if (filename == null) return "";
        int idx = filename.lastIndexOf('.');
        return idx > 0 ? filename.substring(idx + 1) : "";
    }
    // 删除文件
    public static void deleteFile(String path) {
        if (path != null) {
            try { Files.deleteIfExists(Paths.get(path)); } catch (Exception ignored) {}
        }
    }
    // 批量删除
    public static void deleteFiles(List<String> paths) {
        if (paths != null) paths.forEach(FileUtil::deleteFile);
    }
    // 校验文件类型
    public static boolean isSupportedType(String filename, List<String> types) {
        String ext = getFileExtension(filename).toLowerCase();
        return types.contains(ext);
    }
} 