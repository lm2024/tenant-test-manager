package com.common.fileio.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 路径工具类
 * 提供跨平台路径处理的工具方法
 */
@Slf4j
public class PathUtils {
    
    /**
     * 是否Windows系统
     */
    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");
    
    /**
     * 获取跨平台路径
     * 
     * @param path 路径
     * @return 跨平台路径
     */
    public static String getCrossPlatformPath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        
        // 替换路径分隔符
        if (IS_WINDOWS) {
            return path.replace('/', '\\');
        } else {
            return path.replace('\\', '/');
        }
    }
    
    /**
     * 获取绝对路径
     * 
     * @param path 路径
     * @return 绝对路径
     */
    public static String getAbsolutePath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        
        Path resolvedPath = Paths.get(path).toAbsolutePath().normalize();
        return resolvedPath.toString();
    }
    
    /**
     * 获取相对路径
     * 
     * @param basePath 基础路径
     * @param fullPath 完整路径
     * @return 相对路径
     */
    public static String getRelativePath(String basePath, String fullPath) {
        if (basePath == null || basePath.isEmpty() || fullPath == null || fullPath.isEmpty()) {
            return fullPath;
        }
        
        Path base = Paths.get(basePath).toAbsolutePath().normalize();
        Path full = Paths.get(fullPath).toAbsolutePath().normalize();
        
        Path relative = base.relativize(full);
        return relative.toString();
    }
    
    /**
     * 获取文件名
     * 
     * @param path 路径
     * @return 文件名
     */
    public static String getFileName(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        
        File file = new File(path);
        return file.getName();
    }
    
    /**
     * 获取不带扩展名的文件名
     * 
     * @param path 路径
     * @return 不带扩展名的文件名
     */
    public static String getFileNameWithoutExtension(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        
        String fileName = getFileName(path);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        
        return fileName;
    }
    
    /**
     * 获取文件扩展名
     * 
     * @param path 路径
     * @return 文件扩展名
     */
    public static String getFileExtension(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        
        String fileName = getFileName(path);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        
        return "";
    }
    
    /**
     * 获取父目录
     * 
     * @param path 路径
     * @return 父目录
     */
    public static String getParentDirectory(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        
        File file = new File(path);
        File parent = file.getParentFile();
        return parent != null ? parent.getPath() : "";
    }
    
    /**
     * 组合路径
     * 
     * @param basePath 基础路径
     * @param relativePath 相对路径
     * @return 组合后的路径
     */
    public static String combinePath(String basePath, String relativePath) {
        if (basePath == null || basePath.isEmpty()) {
            return relativePath;
        }
        if (relativePath == null || relativePath.isEmpty()) {
            return basePath;
        }
        
        Path base = Paths.get(basePath);
        Path combined = base.resolve(relativePath).normalize();
        return combined.toString();
    }
}