package com.common.fileio.util;

import com.common.fileio.config.FileImportExportProperties;
import com.common.fileio.exception.FileOperationException;
import com.common.fileio.exception.UnsupportedFileTypeException;
import com.common.fileio.model.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件工具类
 * 提供文件操作相关的工具方法
 */
@Slf4j
public class FileUtils {
    
    private final FileImportExportProperties properties;
    
    /**
     * 支持的文件类型
     */
    private static final List<String> SUPPORTED_TYPES = Arrays.asList("xls", "xlsx", "csv", "txt");
    
    /**
     * 构造函数
     * 
     * @param properties 配置属性
     */
    public FileUtils(FileImportExportProperties properties) {
        this.properties = properties;
        
        // 确保目录存在
        ensureDirectoryExists(properties.getPath().getUploadDir());
        ensureDirectoryExists(properties.getPath().getExportDir());
    }
    
    /**
     * 确保目录存在
     * 
     * @param directory 目录路径
     */
    public void ensureDirectoryExists(String directory) {
        File dir = new File(directory);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                log.info("创建目录: {}", directory);
            } else {
                log.warn("无法创建目录: {}", directory);
            }
        }
    }
    
    /**
     * 保存上传文件
     * 
     * @param file 上传文件
     * @return 文件信息
     */
    public FileInfo saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileOperationException("文件为空");
        }
        
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "unknown";
        }
        
        // 检查文件类型
        String fileType = getFileExtension(originalFilename);
        if (!isSupportedType(fileType)) {
            throw new UnsupportedFileTypeException(fileType);
        }
        
        try {
            // 生成保存路径
            String savedPath = generateSavedPath(originalFilename);
            
            // 保存文件
            File destFile = new File(savedPath);
            file.transferTo(destFile);
            
            log.info("文件保存成功: {}", savedPath);
            
            // 创建文件信息
            return FileInfo.of(originalFilename, savedPath, fileType, file.getSize());
        } catch (IOException e) {
            log.error("保存文件失败: {}", e.getMessage());
            throw new FileOperationException("保存文件失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 批量保存上传文件
     * 
     * @param files 上传文件列表
     * @return 文件信息列表
     */
    public List<FileInfo> saveFiles(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new FileOperationException("文件列表为空");
        }
        
        // 检查文件数量
        int maxFiles = properties.getImportConfig().getMaxFiles();
        if (files.size() > maxFiles) {
            throw new FileOperationException("文件数量超过限制: " + maxFiles);
        }
        
        List<FileInfo> result = new ArrayList<>();
        for (MultipartFile file : files) {
            result.add(saveFile(file));
        }
        
        return result;
    }
    
    /**
     * 删除文件
     * 
     * @param filePath 文件路径
     * @return 是否删除成功
     */
    public boolean deleteFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
            log.info("文件删除成功: {}", filePath);
            return true;
        } catch (IOException e) {
            log.error("删除文件失败: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 批量删除文件
     * 
     * @param filePaths 文件路径列表
     * @return 删除成功的文件数量
     */
    public int deleteFiles(List<String> filePaths) {
        if (filePaths == null || filePaths.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (String filePath : filePaths) {
            if (deleteFile(filePath)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * 生成保存路径
     * 
     * @param originalFilename 原始文件名
     * @return 保存路径
     */
    public String generateSavedPath(String originalFilename) {
        // 生成日期目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateDir = sdf.format(new Date());
        
        // 生成唯一文件名
        String uniqueFileName = UUID.randomUUID().toString() + "." + getFileExtension(originalFilename);
        
        // 组合路径
        String uploadDir = properties.getPath().getUploadDir();
        String dirPath = uploadDir + File.separator + dateDir;
        
        // 确保目录存在
        ensureDirectoryExists(dirPath);
        
        return dirPath + File.separator + uniqueFileName;
    }
    
    /**
     * 生成导出文件路径
     * 
     * @param fileName 文件名（不含扩展名）
     * @param fileType 文件类型
     * @return 导出文件路径
     */
    public String generateExportPath(String fileName, String fileType) {
        // 生成日期目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateDir = sdf.format(new Date());
        
        // 处理文件名
        if (fileName == null || fileName.isEmpty()) {
            fileName = "export_" + System.currentTimeMillis();
        }
        
        // 处理文件类型
        if (fileType == null || fileType.isEmpty()) {
            fileType = "xlsx";
        }
        
        // 组合路径
        String exportDir = properties.getPath().getExportDir();
        String dirPath = exportDir + File.separator + dateDir;
        
        // 确保目录存在
        ensureDirectoryExists(dirPath);
        
        return dirPath + File.separator + fileName + "." + fileType;
    }
    
    /**
     * 获取文件扩展名
     * 
     * @param filename 文件名
     * @return 扩展名
     */
    public String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        
        return "";
    }
    
    /**
     * 检查是否支持的文件类型
     * 
     * @param fileType 文件类型
     * @return 是否支持
     */
    public boolean isSupportedType(String fileType) {
        return SUPPORTED_TYPES.contains(fileType.toLowerCase());
    }
}