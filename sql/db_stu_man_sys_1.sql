/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : db_stu_man_sys

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-06-01 20:11:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('8');

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `type` varchar(5) DEFAULT '' COMMENT '0 缴费  1扣费',
  `total_amount` double DEFAULT NULL,
  `used_amount` double DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('9', '5', '0', '3915', '2000', null, '2020-06-01 10:56:48', '王小匏', null);
INSERT INTO `tb_account` VALUES ('10', '4', '0', '200', '200', null, '2020-06-01 11:08:38', '张小美', null);
INSERT INTO `tb_account` VALUES ('12', '4', '1', '110', '90', '9', '2020-06-01 11:14:22', '张小美', '2020-06-01 19:14:10.000000');
INSERT INTO `tb_account` VALUES ('13', '4', '0', '310', '200', null, '2020-06-01 11:14:58', '张小美', null);
INSERT INTO `tb_account` VALUES ('14', '4', '1', '220', '90', '10', '2020-06-01 11:25:19', '张小美', '2020-06-01 19:25:09.000000');

-- ----------------------------
-- Table structure for tb_class_price
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_price`;
CREATE TABLE `tb_class_price` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_id` bigint DEFAULT NULL,
  `price_id` bigint DEFAULT NULL COMMENT '0 缴费  1扣费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_class_price
-- ----------------------------
INSERT INTO `tb_class_price` VALUES ('2', '4', '3');
INSERT INTO `tb_class_price` VALUES ('3', '5', '7');
INSERT INTO `tb_class_price` VALUES ('4', '6', '6');
INSERT INTO `tb_class_price` VALUES ('5', '7', '4');

-- ----------------------------
-- Table structure for tb_clazz
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `clazz` varchar(255) DEFAULT NULL,
  `current_total_student` int DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `head_teacher` varchar(255) DEFAULT NULL,
  `total_student` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES ('4', '1对1-85折', null, '8年级', '王老师', '100');
INSERT INTO `tb_clazz` VALUES ('5', '1对1-原价', null, '8年级', '王老师', '100');
INSERT INTO `tb_clazz` VALUES ('6', '1对1-原价', null, '9年级', '王老师', '100');
INSERT INTO `tb_clazz` VALUES ('7', '1对1-半价', null, '8年级', '张老师', '100');

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `course_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `class_id` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `time_size` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES ('9', '4', '薛MEM', 'aaaa', '2020-06-01 19:14:10', '2020-06-01 21:14:12', '1对1-半价', '张小美', '2');
INSERT INTO `tb_course` VALUES ('10', '4', '薛MEM', '111', '2020-06-01 19:25:09', '2020-06-01 21:25:11', '1对1-半价', '张小美', '2');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK699y5dgfrr061sel92x7hkiu9` (`role_id`),
  CONSTRAINT `FK699y5dgfrr061sel92x7hkiu9` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------

-- ----------------------------
-- Table structure for tb_price
-- ----------------------------
DROP TABLE IF EXISTS `tb_price`;
CREATE TABLE `tb_price` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price_amount` double DEFAULT NULL,
  `discount` double(5,0) DEFAULT NULL COMMENT '0 缴费  1扣费',
  `display` varchar(255) DEFAULT NULL,
  `delete_status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_price
-- ----------------------------
INSERT INTO `tb_price` VALUES ('3', '100', '85', '100 * 0.85', '\0');
INSERT INTO `tb_price` VALUES ('4', '90', '50', '90.0 * 0.50', '\0');
INSERT INTO `tb_price` VALUES ('5', '100', '50', '100.0 * 0.50', '\0');
INSERT INTO `tb_price` VALUES ('6', '100', '100', '100.0 * 1.00', '\0');
INSERT INTO `tb_price` VALUES ('7', '90', '100', '90.0 * 1.00', '\0');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqp78yv76u44ewp8nqf970ewcr` (`user_id`),
  CONSTRAINT `FKqp78yv76u44ewp8nqf970ewcr` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `clazz` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('4', '未央区佳苑小区', '15', '1对1-原价', '8年级', '张小美', '1', '0', '1111111111', '826347391@qq.com');
INSERT INTO `tb_student` VALUES ('5', '高新区丈八北路', '14', '1对1-85折', '8年级', '王小匏', '3', '1', '15688881112', '1238273273@qq.com');
INSERT INTO `tb_student` VALUES ('6', '大理寺道家村', '13', '1对1-原价', '8年级', '厘志新', '5', '1', '18899991111', '237dfha12323@163.com');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('2', '26', '物理', '薛MEM', '1', null, null);
INSERT INTO `tb_teacher` VALUES ('3', '26', '物理', '王泽坤', '2', null, null);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `introduction` longtext,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', null, null, '$2a$10$jgkkOG.13nSiYAq0f71UtuVtMOiW90Wt4YWSPghEXyUERBOi/tHzG', null, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0b2tlbiIsInVzZXJOYW1lIjoiYWRtaW4iLCJleHAiOjE1OTEwMjg2NjgsInVzZXJJZCI6IjEiLCJpYXQiOjE1OTEwMTA2Njh9.dRapJg0hRrbcy1JP8kZQrUXJ3j2dI5goAMESJWeOvBI', 'admin');
