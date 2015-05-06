/*
 Author: Brandy Lee Camacho

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Host           : localhost
 Source Database       : test
 File Encoding         : utf-8

*/
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE test;
USE test1;
SET NAMES utf8;
-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `time_stamp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;
