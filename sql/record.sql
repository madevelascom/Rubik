-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.10-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.3.0.5036
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla rubik.record
CREATE TABLE IF NOT EXISTS `record` (
  `entry` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `name` tinytext NOT NULL,
  `moves` smallint(6) NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`entry`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla rubik.record: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` (`entry`, `date`, `name`, `moves`, `duration`) VALUES
	(1, '2016-01-22 10:15:32', 'made', 35, 130),
	(2, '2016-01-23 11:15:32', 'edison', 35, 130),
	(4, '2016-01-21 15:24:05', 'gabriel', 35, 130),
	(5, '2016-01-20 15:24:05', 'carlos', 35, 130),
	(6, '2016-01-19 15:24:05', 'gordo', 35, 130),
	(7, '2015-01-23 15:24:05', 'adre', 35, 130);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
