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

 Date: 31/07/2025 21:12:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增长）',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户ID，标识业务归属',
  `biz_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务类型标识符',
  `service_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名称标识',
  `prefix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ID前缀（如订单号\"ORD\"）',
  `suffix` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ID后缀（如校验码）',
  `step` int(11) NOT NULL COMMENT 'ID递增步长',
  `length` int(11) NOT NULL COMMENT 'ID总长度（含前后缀）',
  `current_value` bigint(20) NOT NULL COMMENT '当前序列值',
  `max_value` bigint(20) NOT NULL COMMENT '序列最大值（防溢出）',
  `type` int(11) NOT NULL COMMENT '序列类型（0:顺序,1:日期等）',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_tenant_biz`(`tenant_id`, `biz_type`) USING BTREE COMMENT '租户与业务类型唯一约束',
  UNIQUE INDEX `UKnw0ty6xxhyo1r98n4d9d1gpd`(`tenant_id`, `biz_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分布式序列号生成规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES (1, 'tenant004', '1', '1', NULL, NULL, 1000, 8, 1001, 99999999, 1, '2025-07-30 19:58:36', '2025-07-30 19:58:38');

SET FOREIGN_KEY_CHECKS = 1;
