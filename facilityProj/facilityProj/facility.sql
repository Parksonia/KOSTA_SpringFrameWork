-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.11.1-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 testdb2.employee 구조 내보내기
CREATE TABLE IF NOT EXISTS  employee  (
   emp_no  varchar(30) NOT NULL,
   pw  varchar(30) DEFAULT NULL,
   emp_nm  varchar(50) DEFAULT NULL,
  PRIMARY KEY ( emp_no )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 testdb2.employee:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE  employee  DISABLE KEYS */;
INSERT INTO  employee  ( emp_no ,  pw ,  emp_nm ) VALUES
	('1001', '1234', '홍길동');
/*!40000 ALTER TABLE  employee  ENABLE KEYS */;

-- 테이블 testdb2.facility 구조 내보내기
CREATE TABLE IF NOT EXISTS  facility  (
   fac_id  varchar(50) NOT NULL,
   fac_type  varchar(100) DEFAULT NULL,
   fac_type_name  varchar(100) DEFAULT NULL,
   fac_nm  varchar(100) DEFAULT NULL,
   price  int(11) DEFAULT NULL,
   available_time  varchar(10) DEFAULT NULL,
   etc  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( fac_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 testdb2.facility:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE  facility  DISABLE KEYS */;
INSERT INTO  facility  ( fac_id ,  fac_type ,  fac_type_name ,  fac_nm ,  price ,  available_time ,  etc ) VALUES
	('CR_001', 'CR', '회의실', 'S회의실', 110000, '09-18', '10명 이하 회의에 적합합니다.'),
	('CR_002', 'CR', '회의실', '회의실', 120000, '09-18', NULL),
	('HL_001', 'HL', '강당', '대강당', 200000, '09-17', NULL),
	('PR_001', 'PR', '프로젝트룸', '프로젝트룸 D6-1', 90000, '00-24', NULL);
/*!40000 ALTER TABLE  facility  ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
