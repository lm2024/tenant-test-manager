-- =============================
-- 表结构：测试用例表 test_case
-- =============================
CREATE TABLE IF NOT EXISTS `test_case` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` VARCHAR(255) NOT NULL COMMENT '用例标题',
  `description` TEXT NOT NULL COMMENT '用例描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试用例表';

-- =============================
-- 表结构：缺陷表 bug
-- =============================
CREATE TABLE IF NOT EXISTS `bug` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` VARCHAR(255) NOT NULL COMMENT '缺陷标题',
  `description` TEXT NOT NULL COMMENT '缺陷描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='缺陷表';

-- =============================
-- 测试数据：test_case
-- =============================
INSERT INTO `test_case` (`title`, `description`) VALUES
('登录功能-正常登录', '输入正确用户名和密码，点击登录，期望进入系统首页'),
('登录功能-密码错误', '输入正确用户名和错误密码，点击登录，期望提示密码错误'),
('注册功能-邮箱已注册', '输入已注册邮箱，期望提示邮箱已存在');

-- =============================
-- 测试数据：bug
-- =============================
INSERT INTO `bug` (`title`, `description`) VALUES
('登录页面崩溃', '在IE11下打开登录页面，页面空白'),
('注册验证码不发送', '点击获取验证码无响应'),
('密码重置邮件丢失', '部分用户未收到重置密码邮件'); 