-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.4.0-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- tek 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `tek` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `tek`;

-- 테이블 tek.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(50) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  `date` timestamp NOT NULL,
  `hit` int(10) DEFAULT NULL,
  `recommend` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='자유게시판';

-- 테이블 데이터 tek.board:~3 rows (대략적) 내보내기
INSERT INTO `board` (`id`, `user_id`, `title`, `content`, `date`, `hit`, `recommend`) VALUES
	(2, 9, 'zd', 'zcx', '2024-02-29 01:12:53', 3, 0),
	(3, 9, 'xzcsdfvwarvqwrefrfaaw', 'sfdasf', '2024-02-29 00:50:37', 0, 0),
	(4, 9, 'sadvfvafewvrfadgsaghfgagsfdg ', 'sfdavaf', '2024-02-29 00:41:31', 0, 0),
	(5, 9, 'sadf', 'fdasfad', '2024-02-29 02:24:54', 0, 0),
	(6, 9, 'fqw', 'fasda', '2024-02-29 02:24:57', 0, 0),
	(7, 9, 'fsdaasd', 'fasd', '2024-02-29 02:25:00', 1, 0),
	(8, 9, 'asfd', 'afdsadfs', '2024-02-29 02:25:03', 0, 0),
	(9, 9, 'fds', 'asf', '2024-02-29 07:04:46', 0, 0),
	(10, 9, 'asdf', 'asfd', '2024-02-29 07:04:49', 0, 0),
	(11, 9, 'asfd', 'asfd', '2024-02-29 07:04:57', 0, 0),
	(12, 9, 'safd', 'asfd', '2024-02-29 07:05:00', 0, 0),
	(13, 9, 'qwe', 'qweqwe', '2024-02-29 07:05:03', 19, 0);

-- 테이블 tek.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='역할\r\n';

-- 테이블 데이터 tek.role:~1 rows (대략적) 내보내기
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'admin');

-- 테이블 tek.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자';

-- 테이블 데이터 tek.user:~1 rows (대략적) 내보내기
INSERT INTO `user` (`id`, `username`, `password`, `enabled`) VALUES
	(9, 'aaa', '$2a$10$jeXDoa5xpFbbSBlGTsxX3OC/qmPFW07yPxqmgWK7jIEjfxGKkWLym', b'1');

-- 테이블 tek.user_role 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `FK_user_role_role` (`role_id`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 역할';

-- 테이블 데이터 tek.user_role:~1 rows (대략적) 내보내기
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(9, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
