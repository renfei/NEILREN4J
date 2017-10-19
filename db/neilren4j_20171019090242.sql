/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : localhost
 Source Database       : neilren4j

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : utf-8

 Date: 10/19/2017 09:02:42 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_type` int(11) NOT NULL DEFAULT '0' COMMENT '文章类型：0原创；1转载；2译文',
  `source_url` text COMMENT '原文地址',
  `views` bigint(20) NOT NULL COMMENT '浏览量',
  `article_dat` datetime NOT NULL COMMENT '发布时间',
  `author` text NOT NULL COMMENT '作者称呼',
  `author_url` text COMMENT '作者连接',
  `title` text NOT NULL COMMENT '文章标题',
  `keyword` text COMMENT '关键字',
  `describes` text COMMENT '文章简介',
  `content` text NOT NULL COMMENT '文章内容',
  `article_status` int(11) NOT NULL DEFAULT '0' COMMENT '文章状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003275 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
--  Table structure for `t_article_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_article_category`;
CREATE TABLE `t_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`),
  KEY `t_article_category_t_article_id_fk` (`article_id`),
  KEY `t_article_category_t_category_id_fk` (`category_id`),
  CONSTRAINT `t_article_category_t_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`),
  CONSTRAINT `t_article_category_t_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COMMENT='文章分类关系表';

-- ----------------------------
--  Table structure for `t_article_grade`
-- ----------------------------
DROP TABLE IF EXISTS `t_article_grade`;
CREATE TABLE `t_article_grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `grade` int(11) NOT NULL DEFAULT '0' COMMENT '评级',
  `add_date` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `t_article_grade_t_article_id_fk` (`article_id`),
  CONSTRAINT `t_article_grade_t_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='文章评级表';

-- ----------------------------
--  Table structure for `t_article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag`;
CREATE TABLE `t_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`),
  KEY `t_article_tag_t_article_id_fk` (`article_id`),
  KEY `t_article_tag_t_tag_id_fk` (`tag_id`),
  CONSTRAINT `t_article_tag_t_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`),
  CONSTRAINT `t_article_tag_t_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 COMMENT='文章标签关系表';

-- ----------------------------
--  Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `en_name` text NOT NULL COMMENT '英文名称',
  `zh_name` text NOT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
--  Table structure for `t_comments`
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `parent_id` bigint(20) DEFAULT '1' COMMENT '父级评论ID',
  `author_name` text NOT NULL COMMENT '留言者的名字',
  `author_email` text NOT NULL COMMENT '留言者的Email',
  `author_url` text COMMENT '留言者的链接',
  `author_IP` text COMMENT '留言者的IP',
  `comment_date` datetime NOT NULL COMMENT '评论时间',
  `comment_content` text NOT NULL COMMENT '评论内容',
  `approved` int(11) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  `agent` text COMMENT '留言者的浏览器信息',
  PRIMARY KEY (`id`),
  KEY `t_comments_t_article_id_fk` (`article_id`),
  KEY `t_comments_t_comments_id_fk` (`parent_id`),
  CONSTRAINT `t_comments_t_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`),
  CONSTRAINT `t_comments_t_comments_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `t_comments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='文章评论表';

-- ----------------------------
--  Table structure for `t_frielink`
-- ----------------------------
DROP TABLE IF EXISTS `t_frielink`;
CREATE TABLE `t_frielink` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `sitename` text NOT NULL COMMENT '显示名称',
  `link` text NOT NULL COMMENT '链接',
  `domain` text COMMENT '域名',
  `add_date` datetime NOT NULL COMMENT '添加时间',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '是否启用',
  `email` text COMMENT '联系方式',
  `qq` text,
  `black_white` int(11) DEFAULT '0',
  `remark` text,
  `last_check_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='友情链接';

-- ----------------------------
--  Table structure for `t_ip`
-- ----------------------------
DROP TABLE IF EXISTS `t_ip`;
CREATE TABLE `t_ip` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `StartIP` varchar(20) NOT NULL,
  `EndIP` varchar(20) NOT NULL,
  `Country` varchar(30) DEFAULT NULL,
  `Local` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IP` (`StartIP`,`EndIP`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=629200 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_ip_table`
-- ----------------------------
DROP TABLE IF EXISTS `t_ip_table`;
CREATE TABLE `t_ip_table` (
  `startip` int(11) NOT NULL,
  `endip` int(11) NOT NULL,
  `country` text NOT NULL,
  `local` text NOT NULL,
  `version` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_log_send_email`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_send_email`;
CREATE TABLE `t_log_send_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senddate` datetime NOT NULL,
  `toemail` text NOT NULL,
  `subject` text NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_sys_setting`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_setting`;
CREATE TABLE `t_sys_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `key` varchar(512) NOT NULL COMMENT 'Key键',
  `value` text COMMENT '存储值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增编号',
  `en_name` text NOT NULL COMMENT '英文名称',
  `zh_name` text NOT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
--  Table structure for `t_wechat_msg`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_msg`;
CREATE TABLE `t_wechat_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `recording_time` datetime NOT NULL COMMENT '记录时间',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型：0接收，1发送',
  `from_user_name` text NOT NULL COMMENT '发送方帐号（一个OpenID）',
  `create_time` bigint(20) NOT NULL COMMENT '消息创建时间 （整型）',
  `msg_type` varchar(30) NOT NULL COMMENT '消息类型',
  `content` text NOT NULL COMMENT '消息内容',
  `msg_id` bigint(20) NOT NULL COMMENT '消息id，64位整型',
  `original` text NOT NULL COMMENT '原始消息（XML）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 COMMENT='微信公众号消息表';

-- ----------------------------
--  Table structure for `t_wechat_philisense_kaoqin`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_philisense_kaoqin`;
CREATE TABLE `t_wechat_philisense_kaoqin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wechat_name` text NOT NULL COMMENT '微信用户名',
  `kaoqin_username` text NOT NULL COMMENT '内控用户名',
  `kaoqin_pwd` int(11) NOT NULL COMMENT '内控密码',
  `add_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='微信飞利信考勤';

-- ----------------------------
--  Procedure structure for `p_article_views_add`
-- ----------------------------
DROP PROCEDURE IF EXISTS `p_article_views_add`;
delimiter ;;
CREATE DEFINER=`neil`@`%` PROCEDURE `p_article_views_add`(IN article_id bigint)
BEGIN
  #文章浏览量自增
  #DECLARE article_id BIGINT;
  UPDATE t_article SET views = views + 1 WHERE t_article.id = article_id;

END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
