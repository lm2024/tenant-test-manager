/*
 Navicat Premium Data Transfer

 Source Server         : local_root_password
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : tenant_center

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 30/07/2025 14:35:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sequence1
-- ----------------------------
DROP TABLE IF EXISTS `sequence1`;
CREATE TABLE `sequence1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增长）',
  `tenant_id` varchar(64) NOT NULL COMMENT '租户ID，标识业务归属',
  `biz_type` varchar(64) NOT NULL COMMENT '业务类型标识符',
  `service_name` varchar(64) NOT NULL COMMENT '服务名称标识',
  `prefix` varchar(32) DEFAULT NULL COMMENT 'ID前缀（如订单号"ORD"）',
  `suffix` varchar(32) DEFAULT NULL COMMENT 'ID后缀（如校验码）',
  `step` int(11) NOT NULL COMMENT 'ID递增步长',
  `length` int(11) NOT NULL COMMENT 'ID总长度（含前后缀）',
  `current_value` bigint(20) NOT NULL COMMENT '当前序列值',
  `max_value` bigint(20) NOT NULL COMMENT '序列最大值（防溢出）',
  `type` int(11) NOT NULL COMMENT '序列类型（0:顺序,1:日期等）',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tenant_biz` (`tenant_id`,`biz_type`) COMMENT '租户与业务类型唯一约束'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分布式序列号生成规则表';

-- ----------------------------
-- Records of sequence1
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sequence_a
-- ----------------------------
DROP TABLE IF EXISTS `sequence_a`;
CREATE TABLE `sequence_a` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID（自增长）',
  `tenant_id` varchar(64) NOT NULL COMMENT '租户ID，标识业务归属',
  `biz_type` varchar(64) NOT NULL COMMENT '业务类型标识符',
  `service_name` varchar(64) NOT NULL COMMENT '服务名称标识',
  `prefix` varchar(32) DEFAULT NULL COMMENT 'ID前缀（如订单号"ORD"）',
  `suffix` varchar(32) DEFAULT NULL COMMENT 'ID后缀（如校验码）',
  `step` int(11) NOT NULL COMMENT 'ID递增步长',
  `length` int(11) NOT NULL COMMENT 'ID总长度（含前后缀）',
  `current_value` bigint(20) NOT NULL COMMENT '当前序列值',
  `max_value` bigint(20) NOT NULL COMMENT '序列最大值（防溢出）',
  `type` int(11) NOT NULL COMMENT '序列类型（0:顺序,1:日期等）',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tenant_biz` (`tenant_id`,`biz_type`) COMMENT '租户与业务类型唯一约束',
  UNIQUE KEY `UK1a4srhv5uy18qi0ihqtbs4twn` (`tenant_id`,`biz_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分布式序列号生成规则表';

-- ----------------------------
-- Records of sequence_a
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_db_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_db_info`;
CREATE TABLE `tenant_db_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `db_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `db_user` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenant_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_74725eycx8ff4ad8pgux0sbte` (`db_name`),
  UNIQUE KEY `UK_gtawh818d0fkj3gei6xydyvjr` (`tenant_id`),
  UNIQUE KEY `UK_sxnmw84qysu0cv4jbp09rc2pa` (`tenant_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of tenant_db_info
-- ----------------------------
BEGIN;
INSERT INTO `tenant_db_info` (`id`, `db_name`, `db_password`, `db_url`, `db_user`, `tenant_id`, `tenant_name`) VALUES (3, 'db_tenant003', 'password', 'jdbc:mysql://localhost:3306/db_tenant003', 'root', 'tenant003', '产品A3');
INSERT INTO `tenant_db_info` (`id`, `db_name`, `db_password`, `db_url`, `db_user`, `tenant_id`, `tenant_name`) VALUES (4, 'db_tenant004', 'password', 'jdbc:mysql://localhost:3306/db_tenant004', 'root', 'tenant004', '产品A4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
