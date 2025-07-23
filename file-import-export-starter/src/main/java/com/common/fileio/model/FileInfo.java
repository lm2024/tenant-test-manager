package com.common.fileio.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息模型
 * 用于存储上传文件的元数据信息
 */
@Data
public class FileInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 原始文件名
     */
    private String originalName;
    
    /**
     * 保存路径
     */
    private String savedPath;
    
    /**
     * 文件类型
     */
    private String fileType;
    
    /**
     * 文件大小（字节）
     */
    private long fileSize;
    
    /**
     * 上传时间
     */
    private Date uploadTime;
    
    /**
     * 创建一个新的文件信息对象
     * 
     * @param originalName 原始文件名
     * @param savedPath 保存路径
     * @param fileType 文件类型
     * @param fileSize 文件大小
     * @return 文件信息对象
     */
    public static FileInfo of(String originalName, String savedPath, String fileType, long fileSize) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalName(originalName);
        fileInfo.setSavedPath(savedPath);
        fileInfo.setFileType(fileType);
        fileInfo.setFileSize(fileSize);
        fileInfo.setUploadTime(new Date());
        return fileInfo;
    }
}