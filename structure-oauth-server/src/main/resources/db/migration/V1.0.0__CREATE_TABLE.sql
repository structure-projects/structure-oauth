-- 版本v1.0.0 初始化 SQL

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oauth`
--

-- --------------------------------------------------------

--
-- 表的结构 `oauth_authority`
--
CREATE TABLE IF NOT EXISTS `oauth_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `client_id` varchar(32) NOT NULL DEFAULT '0' COMMENT 'client_id',
  `name` varchar(64) NOT NULL COMMENT '权限名',
  `value` varchar(128) NOT NULL COMMENT '权限值标识符',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除：0 否，1 是',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- --------------------------------------------------------

--
-- 表的结构 `oauth_client_details`
--

CREATE TABLE IF NOT EXISTS `oauth_client_details` (
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
  `web_server_redirect_uri` varchar(512) DEFAULT NULL COMMENT '重定向URI',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='client';

