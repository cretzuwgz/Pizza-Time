-- --------------------------------------------------------
-- Server:                       127.0.0.1
-- Versiune server:              5.6.16-log - MySQL Community Server (GPL)
-- SO server:                    Win64
-- HeidiSQL Versiune:            8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table pizza_time.client
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(50) NOT NULL,
  `nr_tel` varchar(10) NOT NULL,
  `adresa` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `nume` (`nume`,`nr_tel`,`adresa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Clientii inregistrati in baza de date.';

-- Dumping structure for table pizza_time.comanda
CREATE TABLE IF NOT EXISTS `comanda` (
  `id_comanda` int(11) NOT NULL AUTO_INCREMENT,
  `pret` int(11) NOT NULL,
  `observatie` longtext,
  `id_client` int(11) NOT NULL,
  PRIMARY KEY (`id_comanda`),
  KEY `FK_comanda_client` (`id_client`),
  CONSTRAINT `FK_comanda_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping structure for table pizza_time.oferta
CREATE TABLE IF NOT EXISTS `oferta` (
  `id_meniu` int(11) NOT NULL,
  `nume_meniu` varchar(50) NOT NULL,
  `pret` int(11) NOT NULL,
  `descriere` longtext NOT NULL,
  `link_poza` varchar(200) NOT NULL,
  PRIMARY KEY (`id_meniu`),
  UNIQUE KEY `nume_meniu` (`nume_meniu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Meniurile la oferta.';

-- Dumping structure for table pizza_time.oferta_com
CREATE TABLE IF NOT EXISTS `oferta_com` (
  `id_oferta` int(11) NOT NULL,
  `id_comanda` int(11) NOT NULL,
  `cantitate` int(11) NOT NULL,
  PRIMARY KEY (`id_oferta`,`id_comanda`),
  KEY `FK_oferta_com_oferta` (`id_oferta`),
  KEY `FK_oferta_com_comanda` (`id_comanda`),
  CONSTRAINT `FK_oferta_com_comanda` FOREIGN KEY (`id_comanda`) REFERENCES `comanda` (`id_comanda`),
  CONSTRAINT `FK_oferta_com_oferta` FOREIGN KEY (`id_oferta`) REFERENCES `oferta` (`id_meniu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping structure for table pizza_time.produs
CREATE TABLE IF NOT EXISTS `produs` (
  `id_prod` int(11) NOT NULL,
  `nume_prod` varchar(50) NOT NULL,
  `pret_prod` int(11) NOT NULL,
  `link_poza` varchar(200) NOT NULL,
  `id_tip` int(11) NOT NULL,
  `id_meniu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_prod`),
  UNIQUE KEY `unique_nume` (`nume_prod`),
  KEY `fk_meniu` (`id_meniu`),
  KEY `fk_tip` (`id_tip`),
  CONSTRAINT `fk_meniu` FOREIGN KEY (`id_meniu`) REFERENCES `oferta` (`id_meniu`),
  CONSTRAINT `fk_tip` FOREIGN KEY (`id_tip`) REFERENCES `tip_produs` (`id_tip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Fiecare produs existent in meniu.';

-- Dumping structure for table pizza_time.produs_com
CREATE TABLE IF NOT EXISTS `produs_com` (
  `id_prod` int(11) NOT NULL,
  `id_comanda` int(11) NOT NULL,
  `cantitate` int(11) NOT NULL,
  PRIMARY KEY (`id_prod`,`id_comanda`),
  KEY `FK_produs_comandat_produs` (`id_prod`),
  KEY `FK_produs_com_comanda` (`id_comanda`),
  CONSTRAINT `FK_produs_comandat_produs` FOREIGN KEY (`id_prod`) REFERENCES `produs` (`id_prod`),
  CONSTRAINT `FK_produs_com_comanda` FOREIGN KEY (`id_comanda`) REFERENCES `comanda` (`id_comanda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping structure for table pizza_time.produs_din_oferta
CREATE TABLE IF NOT EXISTS `produs_din_oferta` (
  `id_prod` int(11) NOT NULL,
  `id_oferta` int(11) NOT NULL,
  `cantitate` int(11) NOT NULL,
  PRIMARY KEY (`id_prod`,`id_oferta`),
  KEY `FK__oferta` (`id_oferta`),
  KEY `FK_produs` (`id_prod`),
  CONSTRAINT `FK__oferta` FOREIGN KEY (`id_oferta`) REFERENCES `oferta` (`id_meniu`),
  CONSTRAINT `FK__produs` FOREIGN KEY (`id_prod`) REFERENCES `produs` (`id_prod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping structure for table pizza_time.tip_produs
CREATE TABLE IF NOT EXISTS `tip_produs` (
  `id_tip` int(11) NOT NULL,
  `nume_tip` varchar(20) NOT NULL,
  `link_poza` varchar(200) NOT NULL,
  PRIMARY KEY (`id_tip`),
  UNIQUE KEY `nume_tip` (`nume_tip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Toate tipurile de produse (ex: bauturi, pizza etc.)';

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
