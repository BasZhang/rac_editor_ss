/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.34-log : Database - rac_editor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rac_editor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `rac_editor`;

/*Table structure for table `CarStore` */

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

/*Data for the table `CarStore` */

LOCK TABLES `CarStore` WRITE;

insert  into `CarStore`(`code`,`isUpdate`,`needCoin`,`needGem`,`needStar`,`useNeedLv`) values (1,1,10000,0,0,1),(2,1,20000,0,3,1),(3,1,0,20000,6,1),(4,1,0,50000,9,1),(5,1,1600000,0,12,1),(1010000,1,10000,0,0,1),(1020000,1,10000,0,30,1),(1031000,1,0,2000,120,1),(2011000,1,0,5000,240,1),(2020000,1,160000,0,390,1);

UNLOCK TABLES;

/*Table structure for table `CarUpdate` */

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

/*Data for the table `CarUpdate` */

LOCK TABLES `CarUpdate` WRITE;

UNLOCK TABLES;

/*Table structure for table `ChannelData` */

DROP TABLE IF EXISTS `ChannelData`;

CREATE TABLE `ChannelData` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sign` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `ChannelData` */

LOCK TABLES `ChannelData` WRITE;

UNLOCK TABLES;

/*Table structure for table `ChannelPassPortData` */

DROP TABLE IF EXISTS `ChannelPassPortData`;

CREATE TABLE `ChannelPassPortData` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `passport` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `ChannelPassPortData` */

LOCK TABLES `ChannelPassPortData` WRITE;

UNLOCK TABLES;

/*Table structure for table `ConditionData` */

DROP TABLE IF EXISTS `ConditionData`;

CREATE TABLE `ConditionData` (
  `code` bigint(20) NOT NULL,
  `conditionName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `params` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `ConditionData` */

LOCK TABLES `ConditionData` WRITE;

insert  into `ConditionData`(`code`,`conditionName`,`isUpdate`,`params`) values (1,'Certificate',1,'2'),(2,'Certificate',1,'3');

UNLOCK TABLES;

/*Table structure for table `Download` */

DROP TABLE IF EXISTS `Download`;

CREATE TABLE `Download` (
  `code` bigint(20) NOT NULL,
  `android` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `channel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ios` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Download` */

LOCK TABLES `Download` WRITE;

insert  into `Download`(`code`,`android`,`channel`,`ios`,`isUpdate`) values (1,'www.baidu.com','ourpalm','www.baidu.com',1),(2,'www.google.com','UC','www.google.com',1);

UNLOCK TABLES;

/*Table structure for table `ErrorMsg` */

DROP TABLE IF EXISTS `ErrorMsg`;

CREATE TABLE `ErrorMsg` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `ErrorMsg` */

LOCK TABLES `ErrorMsg` WRITE;

insert  into `ErrorMsg`(`code`,`isUpdate`,`msg`) values (1,1,'协议校验失败!'),(2,1,'你需要下载新的客户端!'),(3,1,'您已在其他客户端登录了!'),(1000,1,'用户不存在!'),(1001,1,'昵称不合法!'),(2000,1,'该车不存在!'),(2001,1,'该车已经存在!'),(2002,1,'钻石不足!'),(2003,1,'该车正在升级中!'),(2004,1,'星星不足!'),(2005,1,'该车尚未开始升级!'),(2006,1,'金币不足!'),(2007,1,'协议不存在!'),(2008,1,'你不拥有该道具!'),(2009,1,'你已经拥有该道具!'),(2010,1,'无此升级配置!'),(2011,1,'无此装饰配置!'),(2012,1,'无此临时道具配置!'),(3001,1,'比赛未解锁!'),(3002,1,'比赛未开始!'),(3003,1,'分数不合理!');

UNLOCK TABLES;

/*Table structure for table `Level` */

DROP TABLE IF EXISTS `Level`;

CREATE TABLE `Level` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `prestige` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Level` */

LOCK TABLES `Level` WRITE;

insert  into `Level`(`code`,`isUpdate`,`prestige`) values (1,1,60),(2,1,90),(3,1,180),(4,1,330),(5,1,540),(6,1,810),(7,1,1140),(8,1,1530),(9,1,1980),(10,1,2490),(11,1,3060),(12,1,3690),(13,1,4380),(14,1,5130),(15,1,5490),(16,1,6810),(17,1,7740),(18,1,8730),(19,1,9780),(20,1,10890),(21,1,12060),(22,1,13290),(23,1,14580),(24,1,15930),(25,1,17340),(26,1,18810),(27,1,20340),(28,1,21930),(29,1,23580),(30,1,25290);

UNLOCK TABLES;

/*Table structure for table `MatchData` */

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

/*Data for the table `MatchData` */

LOCK TABLES `MatchData` WRITE;

insert  into `MatchData`(`code`,`carIds`,`conditionIds`,`goal`,`isUpdate`,`matchGroup`,`name`,`passScore`,`rewardsId`,`tourId`,`type`,`unreachableScore`,`needRank`,`preMatch`,`isCertificate`) values (1,'','-1','1,2,3',1,0,'普q1',12,'1,4,3,2',1,1,-1,0,-1,0),(2,'','-1','100,140,180',1,0,'普2',0,'1,4,3,2',1,2,500,0,1,0),(3,'','-1','1,5,10',1,0,'普3',1,'1,4,3,2',1,3,30,0,2,0),(4,'','-1','100,200,300',1,1,'普4',100,'1,4,3,2',1,4,5000,0,3,0),(5,'','-1','1,1,1',1,1,'普5',1,'1,4,3,2',1,5,10000,0,4,0),(6,'','-1','90,99,180',1,1,'普6',20000,'1,4,3,2',1,6,0,0,5,0),(7,'','-1','1,2,3',1,0,'活动1',12,'1,4,3,2',10000,1,-1,1,-1,0),(8,'','-1','1,2,3',1,0,'活动2',12,'1,4,3,2',10000,1,-1,1,7,0),(9,'','-1','1,2,3',1,0,'活动3',12,'1,4,2,3',10000,1,-1,1,8,0),(10,'','-1','1,2,3',1,0,'资格证1',12,'1,1,1,6',20000,1,-1,0,-1,1),(11,'','-1','100,140,180',1,0,'资格证2',0,'1,4,3,2',20000,2,500,0,10,1),(12,'','-1','1,5,10',1,0,'资格证3',1,'1,4,3,2',20000,3,30,0,11,1);

UNLOCK TABLES;

/*Table structure for table `MatchTypeData` */

DROP TABLE IF EXISTS `MatchTypeData`;

CREATE TABLE `MatchTypeData` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `strategy` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `MatchTypeData` */

LOCK TABLES `MatchTypeData` WRITE;

insert  into `MatchTypeData`(`code`,`isUpdate`,`name`,`strategy`) values (1,0,'排名','Place'),(2,0,'平均时速','AvgSpeed'),(3,0,'完美过弯','PerfectTurn'),(4,0,'漂移时间','DriftingTime'),(5,0,'追逐','Pursuit'),(6,0,'时间','TimeAttack');

UNLOCK TABLES;

/*Table structure for table `NoticeData` */

DROP TABLE IF EXISTS `NoticeData`;

CREATE TABLE `NoticeData` (
  `code` bigint(20) NOT NULL,
  `platform` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isUpdate` int(11) NOT NULL,
  `content` longtext COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `NoticeData` */

LOCK TABLES `NoticeData` WRITE;

insert  into `NoticeData`(`code`,`platform`,`isUpdate`,`content`,`type`) values (1,'91',1,'http://cdn.0210001700.gamebean.net/ourpalm/notice/notice.jpg','url'),(2,'Appstore',1,'你爱我','text');

UNLOCK TABLES;

/*Table structure for table `NotifyMsg` */

DROP TABLE IF EXISTS `NotifyMsg`;

CREATE TABLE `NotifyMsg` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `msg` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `NotifyMsg` */

LOCK TABLES `NotifyMsg` WRITE;

UNLOCK TABLES;

/*Table structure for table `Prop` */

DROP TABLE IF EXISTS `Prop`;

CREATE TABLE `Prop` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `needGem` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Prop` */

LOCK TABLES `Prop` WRITE;

insert  into `Prop`(`code`,`isUpdate`,`needGem`,`type`) values (10,1,0,0),(11,1,0,0),(12,1,0,0),(13,1,0,0),(20,1,0,0),(21,1,0,0),(22,1,0,0),(23,1,0,0),(30,1,0,0),(31,1,0,0),(32,1,0,0),(33,1,0,0),(40,1,0,0),(41,1,0,0),(42,1,0,0),(43,1,0,0),(100,1,20,1),(101,1,21,2),(102,1,22,3),(103,1,23,1),(104,1,24,2),(105,1,25,3),(200,1,10,4),(201,1,10,4);

UNLOCK TABLES;

/*Table structure for table `Region` */

DROP TABLE IF EXISTS `Region`;

CREATE TABLE `Region` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `regionName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Region` */

LOCK TABLES `Region` WRITE;

insert  into `Region`(`code`,`isUpdate`,`regionName`) values (100000,1,'华北'),(200000,1,'东北'),(300000,1,'华东'),(400000,1,'华中'),(500000,1,'西南'),(600000,1,'西北'),(700000,1,'??'),(800000,1,'华南');

UNLOCK TABLES;

/*Table structure for table `Resource` */

DROP TABLE IF EXISTS `Resource`;

CREATE TABLE `Resource` (
  `code` bigint(20) NOT NULL,
  `file` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `must` int(11) NOT NULL,
  `version` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Resource` */

LOCK TABLES `Resource` WRITE;

insert  into `Resource`(`code`,`file`,`isUpdate`,`must`,`version`) values (1,'a.zip',1,1,'1');

UNLOCK TABLES;

/*Table structure for table `RewardData` */

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

/*Data for the table `RewardData` */

LOCK TABLES `RewardData` WRITE;

insert  into `RewardData`(`code`,`certificate`,`coin`,`isUpdate`,`money`,`prestige`) values (1,0,0,1,0,0),(2,1,100,1,0,10),(3,1,150,1,0,20),(4,1,200,1,0,30),(5,1,200,1,0,40),(6,2,0,1,0,50);

UNLOCK TABLES;

/*Table structure for table `TournamentData` */

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

/*Data for the table `TournamentData` */

LOCK TABLES `TournamentData` WRITE;

insert  into `TournamentData`(`code`,`isUpdate`,`matchIds`,`name`,`type`,`unlockConditions`,`groupCount`) values (1,1,'1,2,3,4,5,6','生涯1',1,'-1',2),(2,1,'1,2,3,4,5,6','生涯2',1,'1',2),(3,1,'1,2,3,4,5,6','生涯3',1,'2',2),(4,1,'1,2,3,4,5,6','生涯4',1,'2',2),(5,1,'1,2,3,4,5,6','生涯5',1,'2',2),(6,1,'1,2,3,4,5,6','生涯6',1,'2',2),(7,1,'1,2,3,4,5,6','生涯7',1,'2',2),(8,1,'1,2,3,4,5,6','生涯8',1,'2',2),(10000,1,'7,8,9','活动',2,'-1',1),(20000,1,'10,11,12','资格证',3,'-1',1);

UNLOCK TABLES;

/*Table structure for table `Version` */

DROP TABLE IF EXISTS `Version`;

CREATE TABLE `Version` (
  `code` bigint(20) NOT NULL,
  `isUpdate` int(11) NOT NULL,
  `must` int(11) NOT NULL,
  `version` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `Version` */

LOCK TABLES `Version` WRITE;

insert  into `Version`(`code`,`isUpdate`,`must`,`version`) values (1,1,0,'1.0.0');

UNLOCK TABLES;

/*Table structure for table `_tt_ChannelFileData` */

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

/*Data for the table `_tt_ChannelFileData` */

LOCK TABLES `_tt_ChannelFileData` WRITE;

insert  into `_tt_ChannelFileData`(`code`,`channel`,`filePaths`,`folder`,`remote`,`resVersion`) values (1,'(COMMON_RES)','','/opt/ourpalm/app/tomcat_8080/webapps/ROOT/WEB-INF/res/data','','');

UNLOCK TABLES;

/*Table structure for table `_tt_alltypes` */

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

/*Data for the table `_tt_alltypes` */

LOCK TABLES `_tt_alltypes` WRITE;

UNLOCK TABLES;

/*Table structure for table `_tt_arrayrule` */

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

/*Data for the table `_tt_arrayrule` */

LOCK TABLES `_tt_arrayrule` WRITE;

insert  into `_tt_arrayrule`(`code`,`constraint_id`,`description`,`idx`,`keyField`,`keyValue`,`tableName`,`targetField`) values (1,1,'比赛序列',1,'type','1','TournamentData','matchIds'),(2,-1,'1星目标',1,'isUpdate','1','MatchData','goal'),(3,-1,'2星目标',2,'isUpdate','1','MatchData','goal'),(4,-1,'3星目标',3,'isUpdate','1','MatchData','goal'),(5,3,'0星奖励',1,'isUpdate','1','MatchData','rewardsId'),(6,3,'1星奖励',2,'isUpdate','1','MatchData','rewardsId'),(7,3,'2星奖励',3,'isUpdate','1','MatchData','rewardsId'),(8,3,'3星奖励',4,'isUpdate','1','MatchData','rewardsId'),(9,5,'参数条件',1,'isUpdate','1','TournamentData','unlockConditions'),(10,6,'可用的车ID',1,'isUpdate','1','MatchData','carIds');

UNLOCK TABLES;

/*Table structure for table `_tt_constraint` */

DROP TABLE IF EXISTS `_tt_constraint`;

CREATE TABLE `_tt_constraint` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `aname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apos` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bpos` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_constraint` */

LOCK TABLES `_tt_constraint` WRITE;

insert  into `_tt_constraint`(`code`,`aname`,`apos`,`bname`,`bpos`) values (1,'TournamentData','matchIds','MatchData','code'),(2,'MatchData','type','MatchTypeData','code'),(3,'MatchData','rewardsId','RewardData','code'),(4,'MatchData','tourId','TournamentData','code'),(5,'TournamentData','unlockConditions','ConditionData','code'),(6,'MatchData','carIds','CarStore','code');

UNLOCK TABLES;

/*Table structure for table `_tt_etrole` */

DROP TABLE IF EXISTS `_tt_etrole`;

CREATE TABLE `_tt_etrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `despripe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `roleCode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_etrole` */

LOCK TABLES `_tt_etrole` WRITE;

insert  into `_tt_etrole`(`id`,`despripe`,`roleCode`) values (1,'管理员','ADMIN'),(2,'游戏开发者','DEV'),(3,'普通GM','COMM'),(4,'超级用户','SUPER');

UNLOCK TABLES;

/*Table structure for table `_tt_etuser` */

DROP TABLE IF EXISTS `_tt_etuser`;

CREATE TABLE `_tt_etuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `regDate` date DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_etuser` */

LOCK TABLES `_tt_etuser` WRITE;

insert  into `_tt_etuser`(`id`,`password`,`regDate`,`username`) values (1,'admin','2014-03-31','admin'),(2,'developer','2014-03-31','developer'),(3,'gm','2014-03-31','gm');

UNLOCK TABLES;

/*Table structure for table `_tt_filetransfer` */

DROP TABLE IF EXISTS `_tt_filetransfer`;

CREATE TABLE `_tt_filetransfer` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `toPath` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_filetransfer` */

LOCK TABLES `_tt_filetransfer` WRITE;

insert  into `_tt_filetransfer`(`code`,`tableName`,`toPath`) values (1,'_tt_toPath_root','/opt/ourpalm/apps/tomcat_8080/webapps/ROOT/WEB-INF/res/data/');

UNLOCK TABLES;

/*Table structure for table `_tt_sys` */

DROP TABLE IF EXISTS `_tt_sys`;

CREATE TABLE `_tt_sys` (
  `code` bigint(20) NOT NULL AUTO_INCREMENT,
  `propKey` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `propValue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_sys` */

LOCK TABLES `_tt_sys` WRITE;

insert  into `_tt_sys`(`code`,`propKey`,`propValue`) values (1,'LOG_SERVER_KEY_PATH','/opt/ourpalm/apps/tomcat_8079/work/secc'),(2,'LOG_SERVER_ADDR','113.31.128.124'),(3,'LOG_SERVER_USERNAME','service'),(4,'HTTP_REQUEST_PREFIX','113.31.128.120');

UNLOCK TABLES;

/*Table structure for table `_tt_user_role` */

DROP TABLE IF EXISTS `_tt_user_role`;

CREATE TABLE `_tt_user_role` (
  `userid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  KEY `FK323C474A79E2073D` (`roleid`),
  KEY `FK323C474A7F375CA7` (`userid`),
  CONSTRAINT `FK323C474A7F375CA7` FOREIGN KEY (`userid`) REFERENCES `_tt_etuser` (`id`),
  CONSTRAINT `FK323C474A79E2073D` FOREIGN KEY (`roleid`) REFERENCES `_tt_etrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `_tt_user_role` */

LOCK TABLES `_tt_user_role` WRITE;

insert  into `_tt_user_role`(`userid`,`roleid`) values (1,1),(1,2),(1,3),(2,2),(2,3),(3,3);

UNLOCK TABLES;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `authority` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  KEY `FK_authorities_users` (`username`),
  CONSTRAINT `FK_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` int(1) NOT NULL,
  `usernamecn` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `users` */

LOCK TABLES `users` WRITE;

insert  into `users`(`username`,`password`,`enabled`,`usernamecn`) values ('admin','bd6920aa9c2bdacd2ed757eaf49aeec2',1,'系统管理员'),('deve','e5cdbf271acb0ff097fa468c7b23cdb6',1,'项目开发者'),('gm','bf6e521247b3dde4590f3f3e56695b33',1,'一般用户'),('liuyuan','64d08336044af782dde07ce1d53385f3',1,'系统管理员'),('shenyou','6ffe4748451a437c8fd5e7fe3d1f8e9d',1,NULL),('wenweiping','279fb1be5685e4f39e1b018a917cd9ab',1,NULL),('zhangbo','6109c303d1a1df87f7bfbc9f562670da',1,'系统管理员');

UNLOCK TABLES;

/*Data for the table `authorities` */

LOCK TABLES `authorities` WRITE;

insert  into `authorities`(`username`,`authority`) values ('admin','ADMIN'),('admin','DEV'),('admin','COMM'),('deve','DEV'),('deve','COMM'),('gm','COMM'),('zhangbo','ADMIN'),('zhangbo','COMM'),('zhangbo','DEV'),('liuyuan','ADMIN'),('liuyuan','COMM'),('liuyuan','DEV'),('admin','SUPER'),('shenyou','COMM'),('shenyou','DEV'),('wenweiping','COMM'),('wenweiping','DEV');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
