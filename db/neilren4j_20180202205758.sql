/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : neilren4j

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 02/02/2018 20:58:07 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_album`
-- ----------------------------
DROP TABLE IF EXISTS `t_album`;
CREATE TABLE `t_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creatdate` datetime NOT NULL,
  `title` text NOT NULL,
  `cover` text,
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1003270 DEFAULT CHARSET=utf8 COMMENT='文章表';

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
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8 COMMENT='文章分类关系表';

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='文章评级表';

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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='文章标签关系表';

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
  `author_name` text COLLATE utf8_unicode_ci NOT NULL COMMENT '留言者的名字',
  `author_email` text COLLATE utf8_unicode_ci NOT NULL COMMENT '留言者的Email',
  `author_url` text COLLATE utf8_unicode_ci COMMENT '留言者的链接',
  `author_IP` text COLLATE utf8_unicode_ci COMMENT '留言者的IP',
  `comment_date` datetime NOT NULL COMMENT '评论时间',
  `comment_content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '评论内容',
  `approved` int(11) NOT NULL DEFAULT '0' COMMENT '是否审核通过',
  `agent` text COLLATE utf8_unicode_ci COMMENT '留言者的浏览器信息',
  PRIMARY KEY (`id`),
  KEY `t_comments_t_article_id_fk` (`article_id`),
  KEY `t_comments_t_comments_id_fk` (`parent_id`),
  CONSTRAINT `t_comments_t_article_id_fk` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`),
  CONSTRAINT `t_comments_t_comments_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `t_comments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章评论表';

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='友情链接';

-- ----------------------------
--  Table structure for `t_image`
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` text NOT NULL,
  `albumid` bigint(20) NOT NULL,
  `meteid` bigint(20) NOT NULL,
  `title` text,
  PRIMARY KEY (`id`),
  KEY `meteid` (`meteid`),
  KEY `albumid` (`albumid`),
  CONSTRAINT `fk_image_album` FOREIGN KEY (`albumid`) REFERENCES `t_album` (`id`),
  CONSTRAINT `fk_image_metadata` FOREIGN KEY (`meteid`) REFERENCES `t_image_meta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_image_meta`
-- ----------------------------
DROP TABLE IF EXISTS `t_image_meta`;
CREATE TABLE `t_image_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ImageHeight` text COLLATE utf8_unicode_ci,
  `ImageWidth` text COLLATE utf8_unicode_ci,
  `Make` text COLLATE utf8_unicode_ci,
  `Model` text COLLATE utf8_unicode_ci,
  `Orientation` text COLLATE utf8_unicode_ci,
  `DateTime` text COLLATE utf8_unicode_ci,
  `ExposureTime` text COLLATE utf8_unicode_ci,
  `FNumber` text COLLATE utf8_unicode_ci,
  `ExposureProgram` text COLLATE utf8_unicode_ci,
  `ISOSpeedRatings` text COLLATE utf8_unicode_ci,
  `SensitivityType` text COLLATE utf8_unicode_ci,
  `RecommendedExposureIndex` text COLLATE utf8_unicode_ci,
  `DateOriginal` text COLLATE utf8_unicode_ci,
  `DateDigitized` text COLLATE utf8_unicode_ci,
  `ShutterSpeedValue` text COLLATE utf8_unicode_ci,
  `ApertureValue` text COLLATE utf8_unicode_ci,
  `ExposureBiasValue` text COLLATE utf8_unicode_ci,
  `MeteringMode` text COLLATE utf8_unicode_ci,
  `Flash` text COLLATE utf8_unicode_ci,
  `FocalLength` text COLLATE utf8_unicode_ci,
  `CustomRendered` text COLLATE utf8_unicode_ci,
  `ExposureMode` text COLLATE utf8_unicode_ci,
  `WhiteBalanceMode` text COLLATE utf8_unicode_ci,
  `SceneCaptureType` text COLLATE utf8_unicode_ci,
  `LensSpecification` text COLLATE utf8_unicode_ci,
  `LensModel` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `t_ip_table`
-- ----------------------------
DROP TABLE IF EXISTS `t_ip_table`;
CREATE TABLE `t_ip_table` (
  `startip` bigint(20) NOT NULL,
  `endip` bigint(20) NOT NULL,
  `country` text COLLATE utf8_unicode_ci NOT NULL,
  `local` text COLLATE utf8_unicode_ci NOT NULL,
  `version` varchar(8) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `logdate` datetime DEFAULT NULL,
  `logger` text COLLATE utf8_unicode_ci,
  `priority` text COLLATE utf8_unicode_ci,
  `message` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6638 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
--  Table structure for `t_log_send_email`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_send_email`;
CREATE TABLE `t_log_send_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senddate` datetime NOT NULL,
  `toemail` text COLLATE utf8_unicode_ci NOT NULL,
  `subject` text COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
--  Table structure for `t_wechat_msg`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_msg`;
CREATE TABLE `t_wechat_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `recording_time` datetime NOT NULL COMMENT '记录时间',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型：0接收，1发送',
  `from_user_name` text COLLATE utf8_unicode_ci NOT NULL COMMENT '发送方帐号（一个OpenID）',
  `create_time` bigint(20) NOT NULL COMMENT '消息创建时间 （整型）',
  `msg_type` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '消息类型',
  `content` text COLLATE utf8_unicode_ci NOT NULL COMMENT '消息内容',
  `msg_id` bigint(20) NOT NULL COMMENT '消息id，64位整型',
  `original` text COLLATE utf8_unicode_ci NOT NULL COMMENT '原始消息（XML）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='微信公众号消息表';

-- ----------------------------
--  Table structure for `t_wechat_philisense_kaoqin`
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_philisense_kaoqin`;
CREATE TABLE `t_wechat_philisense_kaoqin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wechat_name` text COLLATE utf8_unicode_ci NOT NULL COMMENT '微信用户名',
  `kaoqin_username` text COLLATE utf8_unicode_ci NOT NULL COMMENT '内控用户名',
  `kaoqin_pwd` int(11) NOT NULL COMMENT '内控密码',
  `add_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='微信飞利信考勤';

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
