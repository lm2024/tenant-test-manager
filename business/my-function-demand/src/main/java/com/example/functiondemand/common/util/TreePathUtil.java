package com.example.functiondemand.common.util;

import cn.hutool.core.util.StrUtil;

public class TreePathUtil {
    
    private static final String PATH_SEPARATOR = "/";
    private static final int MAX_LEVEL = 5;
    
    public static String generatePath(String parentPath, String currentId) {
        if (StrUtil.isBlank(parentPath)) {
            return PATH_SEPARATOR + currentId;
        }
        return parentPath + PATH_SEPARATOR + currentId;
    }
    
    public static int getLevel(String path) {
        if (StrUtil.isBlank(path)) {
            return 0;
        }
        return path.split(PATH_SEPARATOR).length - 1;
    }
    
    public static void validateLevel(String parentPath) {
        int level = getLevel(parentPath) + 1;
        if (level > MAX_LEVEL) {
            throw new IllegalArgumentException("超过最大层级限制：" + MAX_LEVEL);
        }
    }
    
    public static String getParentPath(String path) {
        if (StrUtil.isBlank(path) || !path.contains(PATH_SEPARATOR)) {
            return null;
        }
        int lastIndex = path.lastIndexOf(PATH_SEPARATOR);
        if (lastIndex == 0) {
            return null;
        }
        return path.substring(0, lastIndex);
    }
    
    public static boolean isAncestor(String ancestorPath, String descendantPath) {
        if (StrUtil.isBlank(ancestorPath) || StrUtil.isBlank(descendantPath)) {
            return false;
        }
        return descendantPath.startsWith(ancestorPath + PATH_SEPARATOR);
    }
}