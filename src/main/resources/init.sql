/*
 MySQL Data Transfer
*/

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `roles` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `insert_time` date NOT NULL,
  `update_time` date NOT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '96E79218965EB72C92A549DD5A330112', 'admin', '1', '管理员', '20', now(), now());
INSERT INTO `t_user` VALUES ('2', 'normal', '96E79218965EB72C92A549DD5A330112', 'normal', '1', '普通用户', '21', now(), now());
