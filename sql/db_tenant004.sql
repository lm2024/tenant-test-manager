/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql5ry
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : db_tenant004

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 21/07/2025 16:06:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bug
-- ----------------------------
DROP TABLE IF EXISTS `bug`;
CREATE TABLE `bug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缺陷标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '缺陷描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '缺陷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bug
-- ----------------------------
INSERT INTO `bug` VALUES (1, '登录页面崩溃', '在IE11下打开登录页面，页面空白');
INSERT INTO `bug` VALUES (2, '注册验证码不发送', '点击获取验证码无响应');
INSERT INTO `bug` VALUES (3, '密码重置邮件丢失', '部分用户未收到重置密码邮件');

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用例标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用例描述',
  `titlecn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'a a a a a ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试用例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, '登录常登录111', '输入正确用户名和密码，点击登录，期望进入系统首页', NULL);
INSERT INTO `test_case` VALUES (2, '登录功能-密码错误', '输入正确用户名和错误密码，点击登录，期望提示密码错误', NULL);
INSERT INTO `test_case` VALUES (3, '注册功能-邮箱已注册', '输入已注册邮箱，期望提示邮箱已存在', NULL);
INSERT INTO `test_case` VALUES (29, '登录常登录1113', '输入正确用户名和密码，点击登录，期望进入系统首页', NULL);
INSERT INTO `test_case` VALUES (30, '登录功能-密码错误3', '输入正确用户名和错误密码，点击登录，期望提示密码错误', NULL);
INSERT INTO `test_case` VALUES (31, '注册功能-邮箱已注册3', '输入已注册邮箱，期望提示邮箱已存在', NULL);

SET FOREIGN_KEY_CHECKS = 1;
