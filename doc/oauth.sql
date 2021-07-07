

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_authority
-- ----------------------------
DROP TABLE IF EXISTS `oauth_authority`;
CREATE TABLE `oauth_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父ID',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `value` varchar(128) NOT NULL COMMENT '权限值标识符',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '权限类型：0 功能，1 菜单，2 按钮',
  `path` varchar(128) DEFAULT NULL COMMENT '路径',
  `menu_type` char(1) DEFAULT '0' COMMENT '菜单类型： 0 内置，1外链',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序，升序',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：0 否，1 是',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of oauth_authority
-- ----------------------------
INSERT INTO `oauth_authority` VALUES ('1', '0', '查询用户', 'FIND_USER', '0', null, '0', null, '0', '0', '2021-03-11 16:21:56.000', '2021-03-11 16:21:58.000');
INSERT INTO `oauth_authority` VALUES ('2', '0', '管理权限', 'DEPT:ALL', '0', null, '0', null, '0', '0', '2021-03-11 16:22:30.000', '2021-03-11 16:22:33.000');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(32) NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(1000) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(512) DEFAULT NULL COMMENT '客户端密匙',
  `scope` varchar(512) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(512) DEFAULT NULL COMMENT '客户端支持的grant_type',
  `authorities` varchar(1000) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效时间值',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '更新令牌有效时间值',
  `additional_information` varchar(512) DEFAULT NULL,
  `autoapprove` varchar(512) DEFAULT NULL COMMENT '用户是否自动Approval操作',
  `web_server_redirect_uri` varchar(512) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='client';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_role
-- ----------------------------
DROP TABLE IF EXISTS `oauth_role`;
CREATE TABLE `oauth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `value` varchar(128) NOT NULL COMMENT '角色值',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 0：未启用 1：启用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of oauth_role
-- ----------------------------
INSERT INTO `oauth_role` VALUES ('1', '管理员', 'ADMIN', '1', '0', '2021-03-11 21:40:08.000', '2021-07-01 15:17:57.467');

-- ----------------------------
-- Table structure for oauth_role_authority_mapping
-- ----------------------------
DROP TABLE IF EXISTS `oauth_role_authority_mapping`;
CREATE TABLE `oauth_role_authority_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `authority_id` int(11) NOT NULL COMMENT '权限ID',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of oauth_role_authority_mapping
-- ----------------------------
INSERT INTO `oauth_role_authority_mapping` VALUES ('1', '1', '1', '2021-03-11 21:40:45.000');
INSERT INTO `oauth_role_authority_mapping` VALUES ('2', '1', '2', '2021-03-11 21:40:55.000');


-- ----------------------------
-- Table structure for oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user`;
CREATE TABLE `oauth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 主键ID ',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '加密后的密码',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `head_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `is_unexpired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否未过期 0：过期 1：未过期',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
  `is_unlocked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否未锁定 0:  锁定 1:未锁定',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of oauth_user
-- ----------------------------
INSERT INTO `oauth_user` VALUES ('1', 'admin', '$2a$10$Wvp7hkJpVALlEePxHiTw7unNlqb3Tbph0/qVCQZ/zNerFpxTO3yAC', null, null, '1', '1', '1', '0', '2021-03-11 16:02:23.000', '2021-03-11 16:02:26.000');

-- ----------------------------
-- Table structure for oauth_user_authority_mapping
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user_authority_mapping`;
CREATE TABLE `oauth_user_authority_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `authority_id` int(11) NOT NULL COMMENT '权限ID',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户权限关系表';

-- ----------------------------
-- Records of oauth_user_authority_mapping
-- ----------------------------
INSERT INTO `oauth_user_authority_mapping` VALUES ('2', '1', '1', '2021-07-01 15:44:44.745');

-- ----------------------------
-- Table structure for oauth_user_role_mapping
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user_role_mapping`;
CREATE TABLE `oauth_user_role_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_user` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of oauth_user_role_mapping
-- ----------------------------
INSERT INTO `oauth_user_role_mapping` VALUES ('1', '1', '1', '2021-03-11 21:39:48.000');

-- ----------------------------
-- Table structure for verification_code_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `verification_code_blacklist`;
CREATE TABLE `verification_code_blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(32) DEFAULT NULL COMMENT '拉黑IP地址',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验证码黑名单';

-- ----------------------------
-- Records of verification_code_blacklist
-- ----------------------------

-- ----------------------------
-- Table structure for verification_code_info
-- ----------------------------
DROP TABLE IF EXISTS `verification_code_info`;
CREATE TABLE `verification_code_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(16) DEFAULT NULL COMMENT '验证码',
  `is_check` tinyint(1) DEFAULT '0' COMMENT '是否校验：0 否，1 是',
  `address` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 COMMENT='验证码信息表';
