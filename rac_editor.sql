/*
Navicat MySQL Data Transfer

Source Server         : CentOS_VM
Source Server Version : 50505
Source Host           : 192.168.1.122:3306
Source Database       : rac_editor

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-07-11 00:10:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for _tt_alltypes
-- ----------------------------
DROP TABLE IF EXISTS `_tt_alltypes`;
CREATE TABLE `_tt_alltypes` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `jbyte` tinyint(4) DEFAULT NULL,
  `jdouble` double DEFAULT NULL,
  `jfloat` float DEFAULT NULL,
  `jint` int(11) DEFAULT NULL,
  `jlong` bigint(20) DEFAULT NULL,
  `jshort` smallint(6) DEFAULT NULL,
  `jstring` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `jstringarray` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_alltypes
-- ----------------------------

-- ----------------------------
-- Table structure for _tt_arrayrule
-- ----------------------------
DROP TABLE IF EXISTS `_tt_arrayrule`;
CREATE TABLE `_tt_arrayrule` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `constraint_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idx` int(11) NOT NULL,
  `keyField` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keyValue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tableName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `targetField` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_arrayrule
-- ----------------------------
INSERT INTO `_tt_arrayrule` VALUES ('1', '1', '比赛序列', '1', 'type', '1', 'TournamentData', 'matchIds');
INSERT INTO `_tt_arrayrule` VALUES ('2', '-1', '1星目标', '1', 'isUpdate', '1', 'MatchData', 'goal');
INSERT INTO `_tt_arrayrule` VALUES ('3', '-1', '2星目标', '2', 'isUpdate', '1', 'MatchData', 'goal');
INSERT INTO `_tt_arrayrule` VALUES ('4', '-1', '3星目标', '3', 'isUpdate', '1', 'MatchData', 'goal');
INSERT INTO `_tt_arrayrule` VALUES ('5', '3', '0星奖励', '1', 'isUpdate', '1', 'MatchData', 'rewardsId');
INSERT INTO `_tt_arrayrule` VALUES ('6', '3', '1星奖励', '2', 'isUpdate', '1', 'MatchData', 'rewardsId');
INSERT INTO `_tt_arrayrule` VALUES ('7', '3', '2星奖励', '3', 'isUpdate', '1', 'MatchData', 'rewardsId');
INSERT INTO `_tt_arrayrule` VALUES ('8', '3', '3星奖励', '4', 'isUpdate', '1', 'MatchData', 'rewardsId');
INSERT INTO `_tt_arrayrule` VALUES ('9', '5', '参数条件', '1', 'isUpdate', '1', 'TournamentData', 'unlockConditions');
INSERT INTO `_tt_arrayrule` VALUES ('10', '6', '可用的车ID', '1', 'isUpdate', '1', 'MatchData', 'carIds');

-- ----------------------------
-- Table structure for _tt_ChannelFileData
-- ----------------------------
DROP TABLE IF EXISTS `_tt_ChannelFileData`;
CREATE TABLE `_tt_ChannelFileData` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `filePaths` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `folder` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remote` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resVersion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_ChannelFileData
-- ----------------------------
INSERT INTO `_tt_ChannelFileData` VALUES ('1', '(COMMON_RES)', '', '/home/z/Downloads/', '', '');

-- ----------------------------
-- Table structure for _tt_constraint
-- ----------------------------
DROP TABLE IF EXISTS `_tt_constraint`;
CREATE TABLE `_tt_constraint` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apos` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bpos` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_constraint
-- ----------------------------
INSERT INTO `_tt_constraint` VALUES ('1', 'TournamentData', 'matchIds', 'MatchData', 'code');
INSERT INTO `_tt_constraint` VALUES ('2', 'MatchData', 'type', 'MatchTypeData', 'code');
INSERT INTO `_tt_constraint` VALUES ('3', 'MatchData', 'rewardsId', 'RewardData', 'code');
INSERT INTO `_tt_constraint` VALUES ('4', 'MatchData', 'tourId', 'TournamentData', 'code');
INSERT INTO `_tt_constraint` VALUES ('5', 'TournamentData', 'unlockConditions', 'ConditionData', 'code');
INSERT INTO `_tt_constraint` VALUES ('6', 'MatchData', 'carIds', 'CarStore', 'code');

-- ----------------------------
-- Table structure for _tt_etrole
-- ----------------------------
DROP TABLE IF EXISTS `_tt_etrole`;
CREATE TABLE `_tt_etrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `despripe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `roleCode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_etrole
-- ----------------------------
INSERT INTO `_tt_etrole` VALUES ('1', '管理员', 'ADMIN');
INSERT INTO `_tt_etrole` VALUES ('2', '游戏开发者', 'DEV');
INSERT INTO `_tt_etrole` VALUES ('3', '普通GM', 'COMM');
INSERT INTO `_tt_etrole` VALUES ('4', '超级用户', 'SUPER');

-- ----------------------------
-- Table structure for _tt_etuser
-- ----------------------------
DROP TABLE IF EXISTS `_tt_etuser`;
CREATE TABLE `_tt_etuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_etuser
-- ----------------------------
INSERT INTO `_tt_etuser` VALUES ('1', 'admin', '2014-03-31', 'admin');
INSERT INTO `_tt_etuser` VALUES ('2', 'developer', '2014-03-31', 'developer');
INSERT INTO `_tt_etuser` VALUES ('3', 'gm', '2014-03-31', 'gm');

-- ----------------------------
-- Table structure for _tt_filetransfer
-- ----------------------------
DROP TABLE IF EXISTS `_tt_filetransfer`;
CREATE TABLE `_tt_filetransfer` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `toPath` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_filetransfer
-- ----------------------------
INSERT INTO `_tt_filetransfer` VALUES ('1', '_tt_toPath_root', '/home/z/Downloads/');

-- ----------------------------
-- Table structure for _tt_sys
-- ----------------------------
DROP TABLE IF EXISTS `_tt_sys`;
CREATE TABLE `_tt_sys` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `propKey` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `propValue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_sys
-- ----------------------------
INSERT INTO `_tt_sys` VALUES ('1', 'LOG_SERVER_KEY_PATH', '/opt/ourpalm/apps/tomcat_8079/work/secc');
INSERT INTO `_tt_sys` VALUES ('2', 'LOG_SERVER_ADDR', '113.31.128.124');
INSERT INTO `_tt_sys` VALUES ('3', 'LOG_SERVER_USERNAME', 'service');
INSERT INTO `_tt_sys` VALUES ('4', 'HTTP_REQUEST_PREFIX', '113.31.128.120');

-- ----------------------------
-- Table structure for _tt_user_role
-- ----------------------------
DROP TABLE IF EXISTS `_tt_user_role`;
CREATE TABLE `_tt_user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  KEY `FK323C474A79E2073D` (`roleid`),
  KEY `FK323C474A7F375CA7` (`userid`),
  CONSTRAINT `FK18qp04y9bekfg6ouexx11h0tw` FOREIGN KEY (`roleid`) REFERENCES `_tt_etrole` (`id`),
  CONSTRAINT `FK323C474A79E2073D` FOREIGN KEY (`roleid`) REFERENCES `_tt_etrole` (`id`),
  CONSTRAINT `FK323C474A7F375CA7` FOREIGN KEY (`userid`) REFERENCES `_tt_etuser` (`id`),
  CONSTRAINT `FKj3uy8wigjbrtasbdwmx2e969j` FOREIGN KEY (`userid`) REFERENCES `_tt_etuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of _tt_user_role
-- ----------------------------
INSERT INTO `_tt_user_role` VALUES ('1', '1');
INSERT INTO `_tt_user_role` VALUES ('1', '2');
INSERT INTO `_tt_user_role` VALUES ('1', '3');
INSERT INTO `_tt_user_role` VALUES ('2', '2');
INSERT INTO `_tt_user_role` VALUES ('2', '3');
INSERT INTO `_tt_user_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `authority` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `FK_authorities_users` (`username`),
  CONSTRAINT `FK_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('admin', 'ADMIN');
INSERT INTO `authorities` VALUES ('admin', 'DEV');
INSERT INTO `authorities` VALUES ('admin', 'COMM');
INSERT INTO `authorities` VALUES ('deve', 'DEV');
INSERT INTO `authorities` VALUES ('deve', 'COMM');
INSERT INTO `authorities` VALUES ('gm', 'COMM');
INSERT INTO `authorities` VALUES ('zhangbo', 'ADMIN');
INSERT INTO `authorities` VALUES ('zhangbo', 'COMM');
INSERT INTO `authorities` VALUES ('zhangbo', 'DEV');
INSERT INTO `authorities` VALUES ('liuyuan', 'ADMIN');
INSERT INTO `authorities` VALUES ('liuyuan', 'COMM');
INSERT INTO `authorities` VALUES ('liuyuan', 'DEV');
INSERT INTO `authorities` VALUES ('admin', 'SUPER');
INSERT INTO `authorities` VALUES ('shenyou', 'COMM');
INSERT INTO `authorities` VALUES ('shenyou', 'DEV');
INSERT INTO `authorities` VALUES ('wenweiping', 'COMM');
INSERT INTO `authorities` VALUES ('wenweiping', 'DEV');

-- ----------------------------
-- Table structure for CarStore
-- ----------------------------
DROP TABLE IF EXISTS `CarStore`;
CREATE TABLE `CarStore` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `needCoin` int(11) NOT NULL,
  `needGem` int(11) NOT NULL,
  `needStar` int(11) NOT NULL,
  `useNeedLv` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of CarStore
-- ----------------------------
INSERT INTO `CarStore` VALUES ('1', '1', '10000', '0', '0', '1');
INSERT INTO `CarStore` VALUES ('2', '1', '20000', '0', '3', '1');
INSERT INTO `CarStore` VALUES ('3', '1', '0', '20000', '6', '1');
INSERT INTO `CarStore` VALUES ('4', '1', '0', '50000', '9', '1');
INSERT INTO `CarStore` VALUES ('5', '1', '1600000', '0', '12', '1');
INSERT INTO `CarStore` VALUES ('1010000', '1', '10000', '0', '0', '1');
INSERT INTO `CarStore` VALUES ('1020000', '1', '10000', '0', '30', '1');
INSERT INTO `CarStore` VALUES ('1031000', '1', '0', '2000', '120', '1');
INSERT INTO `CarStore` VALUES ('2011000', '1', '0', '5000', '240', '1');
INSERT INTO `CarStore` VALUES ('2020000', '1', '160000', '0', '390', '1');

-- ----------------------------
-- Table structure for CarUpdate
-- ----------------------------
DROP TABLE IF EXISTS `CarUpdate`;
CREATE TABLE `CarUpdate` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `needCoin` int(11) NOT NULL,
  `needTime` bigint(20) NOT NULL,
  `propId` int(11) NOT NULL,
  `quickNeedGem` int(11) NOT NULL,
  `typeLv` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of CarUpdate
-- ----------------------------

-- ----------------------------
-- Table structure for ChannelData
-- ----------------------------
DROP TABLE IF EXISTS `ChannelData`;
CREATE TABLE `ChannelData` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sign` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of ChannelData
-- ----------------------------

-- ----------------------------
-- Table structure for ChannelPassPortData
-- ----------------------------
DROP TABLE IF EXISTS `ChannelPassPortData`;
CREATE TABLE `ChannelPassPortData` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `passport` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of ChannelPassPortData
-- ----------------------------

-- ----------------------------
-- Table structure for ConditionData
-- ----------------------------
DROP TABLE IF EXISTS `ConditionData`;
CREATE TABLE `ConditionData` (
  `code` bigint(20) NOT NULL,
  `conditionName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `params` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of ConditionData
-- ----------------------------
INSERT INTO `ConditionData` VALUES ('1', 'Certificate', '1', '2');
INSERT INTO `ConditionData` VALUES ('2', 'Certificate', '1', '3');

-- ----------------------------
-- Table structure for Download
-- ----------------------------
DROP TABLE IF EXISTS `Download`;
CREATE TABLE `Download` (
  `code` bigint(20) NOT NULL,
  `android` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `channel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ios` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Download
-- ----------------------------
INSERT INTO `Download` VALUES ('1', 'www.baidu.com', 'ourpalm', 'www.baidu.com', '1');
INSERT INTO `Download` VALUES ('2', 'www.google.com', 'UC', 'www.google.com', '1');

-- ----------------------------
-- Table structure for ErrorMsg
-- ----------------------------
DROP TABLE IF EXISTS `ErrorMsg`;
CREATE TABLE `ErrorMsg` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of ErrorMsg
-- ----------------------------
INSERT INTO `ErrorMsg` VALUES ('1', '1', '协议校验失败!');
INSERT INTO `ErrorMsg` VALUES ('2', '1', '你需要下载新的客户端!');
INSERT INTO `ErrorMsg` VALUES ('3', '1', '您已在其他客户端登录了!');
INSERT INTO `ErrorMsg` VALUES ('1000', '1', '用户不存在!');
INSERT INTO `ErrorMsg` VALUES ('1001', '1', '昵称不合法!');
INSERT INTO `ErrorMsg` VALUES ('2000', '1', '该车不存在!');
INSERT INTO `ErrorMsg` VALUES ('2001', '1', '该车已经存在!');
INSERT INTO `ErrorMsg` VALUES ('2002', '1', '钻石不足!');
INSERT INTO `ErrorMsg` VALUES ('2003', '1', '该车正在升级中!');
INSERT INTO `ErrorMsg` VALUES ('2004', '1', '星星不足!');
INSERT INTO `ErrorMsg` VALUES ('2005', '1', '该车尚未开始升级!');
INSERT INTO `ErrorMsg` VALUES ('2006', '1', '金币不足!');
INSERT INTO `ErrorMsg` VALUES ('2007', '1', '协议不存在!');
INSERT INTO `ErrorMsg` VALUES ('2008', '1', '你不拥有该道具!');
INSERT INTO `ErrorMsg` VALUES ('2009', '1', '你已经拥有该道具!');
INSERT INTO `ErrorMsg` VALUES ('2010', '1', '无此升级配置!');
INSERT INTO `ErrorMsg` VALUES ('2011', '1', '无此装饰配置!');
INSERT INTO `ErrorMsg` VALUES ('2012', '1', '无此临时道具配置!');
INSERT INTO `ErrorMsg` VALUES ('3001', '1', '比赛未解锁!');
INSERT INTO `ErrorMsg` VALUES ('3002', '1', '比赛未开始!');
INSERT INTO `ErrorMsg` VALUES ('3003', '1', '分数不合理!');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for Level
-- ----------------------------
DROP TABLE IF EXISTS `Level`;
CREATE TABLE `Level` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `prestige` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Level
-- ----------------------------
INSERT INTO `Level` VALUES ('1', '1', '60');
INSERT INTO `Level` VALUES ('2', '1', '90');
INSERT INTO `Level` VALUES ('3', '1', '180');
INSERT INTO `Level` VALUES ('4', '1', '330');
INSERT INTO `Level` VALUES ('5', '1', '540');
INSERT INTO `Level` VALUES ('6', '1', '810');
INSERT INTO `Level` VALUES ('7', '1', '1140');
INSERT INTO `Level` VALUES ('8', '1', '1530');
INSERT INTO `Level` VALUES ('9', '1', '1980');
INSERT INTO `Level` VALUES ('10', '1', '2490');
INSERT INTO `Level` VALUES ('11', '1', '3060');
INSERT INTO `Level` VALUES ('12', '1', '3690');
INSERT INTO `Level` VALUES ('13', '1', '4380');
INSERT INTO `Level` VALUES ('14', '1', '5130');
INSERT INTO `Level` VALUES ('15', '1', '5490');
INSERT INTO `Level` VALUES ('16', '1', '6810');
INSERT INTO `Level` VALUES ('17', '1', '7740');
INSERT INTO `Level` VALUES ('18', '1', '8730');
INSERT INTO `Level` VALUES ('19', '1', '9780');
INSERT INTO `Level` VALUES ('20', '1', '10890');
INSERT INTO `Level` VALUES ('21', '1', '12060');
INSERT INTO `Level` VALUES ('22', '1', '13290');
INSERT INTO `Level` VALUES ('23', '1', '14580');
INSERT INTO `Level` VALUES ('24', '1', '15930');
INSERT INTO `Level` VALUES ('25', '1', '17340');
INSERT INTO `Level` VALUES ('26', '1', '18810');
INSERT INTO `Level` VALUES ('27', '1', '20340');
INSERT INTO `Level` VALUES ('28', '1', '21930');
INSERT INTO `Level` VALUES ('29', '1', '23580');
INSERT INTO `Level` VALUES ('30', '1', '25290');

-- ----------------------------
-- Table structure for MatchData
-- ----------------------------
DROP TABLE IF EXISTS `MatchData`;
CREATE TABLE `MatchData` (
  `code` bigint(20) NOT NULL,
  `carIds` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `conditionIds` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `goal` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isUpdate` int(11) NOT NULL,
  `matchGroup` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `passScore` int(11) NOT NULL,
  `rewardsId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tourId` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `unreachableScore` int(11) NOT NULL,
  `needRank` int(11) NOT NULL,
  `preMatch` int(11) NOT NULL,
  `isCertificate` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of MatchData
-- ----------------------------
INSERT INTO `MatchData` VALUES ('1', '', '-1', '1,2,3', '1', '0', '普q1', '12', '1,4,3,2', '1', '1', '-1', '0', '-1', '0');
INSERT INTO `MatchData` VALUES ('2', '', '-1', '100,140,180', '1', '0', '普2', '0', '1,4,3,2', '1', '2', '500', '0', '1', '0');
INSERT INTO `MatchData` VALUES ('3', '', '-1', '1,5,10', '1', '0', '普3', '1', '1,4,3,2', '1', '3', '30', '0', '2', '0');
INSERT INTO `MatchData` VALUES ('4', '', '-1', '100,200,300', '1', '1', '普4', '100', '1,4,3,2', '1', '4', '5000', '0', '3', '0');
INSERT INTO `MatchData` VALUES ('5', '', '-1', '1,1,1', '1', '1', '普5', '1', '1,4,3,2', '1', '5', '10000', '0', '4', '0');
INSERT INTO `MatchData` VALUES ('6', '', '-1', '90,99,180', '1', '1', '普6', '20000', '1,4,3,2', '1', '6', '0', '0', '5', '0');
INSERT INTO `MatchData` VALUES ('7', '', '-1', '1,2,3', '1', '0', '活动1', '12', '1,4,3,2', '10000', '1', '-1', '1', '-1', '0');
INSERT INTO `MatchData` VALUES ('8', '', '-1', '1,2,3', '1', '0', '活动2', '12', '1,4,3,2', '10000', '1', '-1', '1', '7', '0');
INSERT INTO `MatchData` VALUES ('9', '', '-1', '1,2,3', '1', '0', '活动3', '12', '1,4,2,3', '10000', '1', '-1', '1', '8', '0');
INSERT INTO `MatchData` VALUES ('10', '', '-1', '1,2,3', '1', '0', '资格证1', '12', '1,1,1,6', '20000', '1', '-1', '0', '-1', '1');
INSERT INTO `MatchData` VALUES ('11', '', '-1', '100,140,180', '1', '0', '资格证2', '0', '1,4,3,2', '20000', '2', '500', '0', '10', '1');
INSERT INTO `MatchData` VALUES ('12', '', '-1', '1,5,10', '1', '0', '资格证3', '1', '1,4,3,2', '20000', '3', '30', '0', '11', '1');

-- ----------------------------
-- Table structure for MatchTypeData
-- ----------------------------
DROP TABLE IF EXISTS `MatchTypeData`;
CREATE TABLE `MatchTypeData` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `strategy` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of MatchTypeData
-- ----------------------------
INSERT INTO `MatchTypeData` VALUES ('1', '0', '排名', 'Place');
INSERT INTO `MatchTypeData` VALUES ('2', '0', '平均时速', 'AvgSpeed');
INSERT INTO `MatchTypeData` VALUES ('3', '0', '完美过弯', 'PerfectTurn');
INSERT INTO `MatchTypeData` VALUES ('4', '0', '漂移时间', 'DriftingTime');
INSERT INTO `MatchTypeData` VALUES ('5', '0', '追逐', 'Pursuit');
INSERT INTO `MatchTypeData` VALUES ('6', '0', '时间', 'TimeAttack');

-- ----------------------------
-- Table structure for NoticeData
-- ----------------------------
DROP TABLE IF EXISTS `NoticeData`;
CREATE TABLE `NoticeData` (
  `code` bigint(20) NOT NULL,
  `platform` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isUpdate` int(11) NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of NoticeData
-- ----------------------------
INSERT INTO `NoticeData` VALUES ('1', '91', '1', 'http://cdn.0210001700.gamebean.net/z/notice/notice.jpg', 'url');
INSERT INTO `NoticeData` VALUES ('2', 'Appstore', '1', '你爱我', 'text');

-- ----------------------------
-- Table structure for NotifyMsg
-- ----------------------------
DROP TABLE IF EXISTS `NotifyMsg`;
CREATE TABLE `NotifyMsg` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of NotifyMsg
-- ----------------------------

-- ----------------------------
-- Table structure for Prop
-- ----------------------------
DROP TABLE IF EXISTS `Prop`;
CREATE TABLE `Prop` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `needGem` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Prop
-- ----------------------------
INSERT INTO `Prop` VALUES ('10', '1', '0', '0');
INSERT INTO `Prop` VALUES ('11', '1', '0', '0');
INSERT INTO `Prop` VALUES ('12', '1', '0', '0');
INSERT INTO `Prop` VALUES ('13', '1', '0', '0');
INSERT INTO `Prop` VALUES ('20', '1', '0', '0');
INSERT INTO `Prop` VALUES ('21', '1', '0', '0');
INSERT INTO `Prop` VALUES ('22', '1', '0', '0');
INSERT INTO `Prop` VALUES ('23', '1', '0', '0');
INSERT INTO `Prop` VALUES ('30', '1', '0', '0');
INSERT INTO `Prop` VALUES ('31', '1', '0', '0');
INSERT INTO `Prop` VALUES ('32', '1', '0', '0');
INSERT INTO `Prop` VALUES ('33', '1', '0', '0');
INSERT INTO `Prop` VALUES ('40', '1', '0', '0');
INSERT INTO `Prop` VALUES ('41', '1', '0', '0');
INSERT INTO `Prop` VALUES ('42', '1', '0', '0');
INSERT INTO `Prop` VALUES ('43', '1', '0', '0');
INSERT INTO `Prop` VALUES ('100', '1', '20', '1');
INSERT INTO `Prop` VALUES ('101', '1', '21', '2');
INSERT INTO `Prop` VALUES ('102', '1', '22', '3');
INSERT INTO `Prop` VALUES ('103', '1', '23', '1');
INSERT INTO `Prop` VALUES ('104', '1', '24', '2');
INSERT INTO `Prop` VALUES ('105', '1', '25', '3');
INSERT INTO `Prop` VALUES ('200', '1', '10', '4');
INSERT INTO `Prop` VALUES ('201', '1', '10', '4');

-- ----------------------------
-- Table structure for Region
-- ----------------------------
DROP TABLE IF EXISTS `Region`;
CREATE TABLE `Region` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `regionName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Region
-- ----------------------------
INSERT INTO `Region` VALUES ('100000', '1', '华北');
INSERT INTO `Region` VALUES ('200000', '1', '东北');
INSERT INTO `Region` VALUES ('300000', '1', '华东');
INSERT INTO `Region` VALUES ('400000', '1', '华中');
INSERT INTO `Region` VALUES ('500000', '1', '西南');
INSERT INTO `Region` VALUES ('600000', '1', '西北');
INSERT INTO `Region` VALUES ('700000', '1', '??');
INSERT INTO `Region` VALUES ('800000', '1', '华南');

-- ----------------------------
-- Table structure for Resource
-- ----------------------------
DROP TABLE IF EXISTS `Resource`;
CREATE TABLE `Resource` (
  `code` bigint(20) NOT NULL,
  `file` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `must` int(11) NOT NULL,
  `version` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Resource
-- ----------------------------
INSERT INTO `Resource` VALUES ('1', 'a.zip', '1', '1', '1');

-- ----------------------------
-- Table structure for RewardData
-- ----------------------------
DROP TABLE IF EXISTS `RewardData`;
CREATE TABLE `RewardData` (
  `code` bigint(20) NOT NULL,
  `certificate` int(11) NOT NULL,
  `coin` int(11) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `prestige` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of RewardData
-- ----------------------------
INSERT INTO `RewardData` VALUES ('1', '0', '0', '1', '0', '0');
INSERT INTO `RewardData` VALUES ('2', '1', '100', '1', '0', '10');
INSERT INTO `RewardData` VALUES ('3', '1', '150', '1', '0', '20');
INSERT INTO `RewardData` VALUES ('4', '1', '200', '1', '0', '30');
INSERT INTO `RewardData` VALUES ('5', '1', '200', '1', '0', '40');
INSERT INTO `RewardData` VALUES ('6', '2', '0', '1', '0', '50');

-- ----------------------------
-- Table structure for TournamentData
-- ----------------------------
DROP TABLE IF EXISTS `TournamentData`;
CREATE TABLE `TournamentData` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `matchIds` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) NOT NULL,
  `unlockConditions` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `groupCount` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of TournamentData
-- ----------------------------
INSERT INTO `TournamentData` VALUES ('1', '1', '1,2,3,4,5,6', '生涯1', '1', '-1', '2');
INSERT INTO `TournamentData` VALUES ('2', '1', '1,2,3,4,5,6', '生涯2', '1', '1', '2');
INSERT INTO `TournamentData` VALUES ('3', '1', '1,2,3,4,5,6', '生涯3', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('4', '1', '1,2,3,4,5,6', '生涯4', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('5', '1', '1,2,3,4,5,6', '生涯5', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('6', '1', '1,2,3,4,5,6', '生涯6', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('7', '1', '1,2,3,4,5,6', '生涯7', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('8', '1', '1,2,3,4,5,6', '生涯8', '1', '2', '2');
INSERT INTO `TournamentData` VALUES ('10000', '1', '7,8,9', '活动', '2', '-1', '1');
INSERT INTO `TournamentData` VALUES ('20000', '1', '10,11,12', '资格证', '3', '-1', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` int(1) NOT NULL,
  `usernamecn` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', 'bd6920aa9c2bdacd2ed757eaf49aeec2', '1', '系统管理员');
INSERT INTO `users` VALUES ('deve', 'e5cdbf271acb0ff097fa468c7b23cdb6', '1', '项目开发者');
INSERT INTO `users` VALUES ('gm', 'bf6e521247b3dde4590f3f3e56695b33', '1', '一般用户');
INSERT INTO `users` VALUES ('liuyuan', '64d08336044af782dde07ce1d53385f3', '1', '系统管理员');
INSERT INTO `users` VALUES ('shenyou', '6ffe4748451a437c8fd5e7fe3d1f8e9d', '1', null);
INSERT INTO `users` VALUES ('wenweiping', '279fb1be5685e4f39e1b018a917cd9ab', '1', null);
INSERT INTO `users` VALUES ('zhangbo', '6109c303d1a1df87f7bfbc9f562670da', '1', '系统管理员');

-- ----------------------------
-- Table structure for Version
-- ----------------------------
DROP TABLE IF EXISTS `Version`;
CREATE TABLE `Version` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `must` int(11) NOT NULL,
  `version` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of Version
-- ----------------------------
INSERT INTO `Version` VALUES ('1', '1', '0', '1.0.0');
