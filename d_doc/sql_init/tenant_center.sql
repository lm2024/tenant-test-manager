/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql5ry
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : tenant_center

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 21/07/2025 16:06:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tenant_db_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_db_info`;
CREATE TABLE `tenant_db_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_74725eycx8ff4ad8pgux0sbte`(`db_name`) USING BTREE,
  UNIQUE INDEX `UK_gtawh818d0fkj3gei6xydyvjr`(`tenant_id`) USING BTREE,
  UNIQUE INDEX `UK_sxnmw84qysu0cv4jbp09rc2pa`(`tenant_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_db_info
-- ----------------------------
INSERT INTO `tenant_db_info` VALUES (3, 'db_tenant003', 'password', 'jdbc:mysql://localhost:3306/db_tenant003', 'root', 'tenant003', '产品A3');
INSERT INTO `tenant_db_info` VALUES (4, 'db_tenant004', 'password', 'jdbc:mysql://localhost:3306/db_tenant004', 'root', 'tenant004', '产品A4');

SET FOREIGN_KEY_CHECKS = 1;
