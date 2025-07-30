/*
 Navicat Premium Data Transfer

 Source Server         : local_root_password
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : db_tenant003

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 30/07/2025 14:35:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bug
-- ----------------------------
DROP TABLE IF EXISTS `bug`;
CREATE TABLE `bug` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '缺陷标题',
  `description` text NOT NULL COMMENT '缺陷描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='缺陷表';

-- ----------------------------
-- Records of bug
-- ----------------------------
BEGIN;
INSERT INTO `bug` (`id`, `title`, `description`) VALUES (1, '登录页面崩溃', '在IE11下打开登录页面，页面空白');
INSERT INTO `bug` (`id`, `title`, `description`) VALUES (2, '注册验证码不发送', '点击获取验证码无响应');
INSERT INTO `bug` (`id`, `title`, `description`) VALUES (3, '密码重置邮件丢失', '部分用户未收到重置密码邮件');
COMMIT;

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '用例标题',
  `description` text NOT NULL COMMENT '用例描述',
  `titlecn` varchar(255) DEFAULT NULL COMMENT 'a a a a a ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='测试用例表';

-- ----------------------------
-- Records of test_case
-- ----------------------------
BEGIN;
INSERT INTO `test_case` (`id`, `title`, `description`, `titlecn`) VALUES (1, '登录功33333333333正常登录', '输入正确用户名和密码，点击登录，期望进入系统首页', NULL);
INSERT INTO `test_case` (`id`, `title`, `description`, `titlecn`) VALUES (2, '登录功能-密码错误', '输入正确用户名和错误密码，点击登录，期望提示密码错误', NULL);
INSERT INTO `test_case` (`id`, `title`, `description`, `titlecn`) VALUES (3, '注册功能-邮箱已注册', '输入已注册邮箱，期望提示邮箱已存在', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
