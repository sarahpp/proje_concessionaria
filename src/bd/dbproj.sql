-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: dbproj
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.25-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbclientes`
--

DROP TABLE IF EXISTS `tbclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbclientes` (
  `idcliente` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `rg` varchar(13) NOT NULL,
  `cidade` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `cnh` varchar(11) NOT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbclientes`
--

LOCK TABLES `tbclientes` WRITE;
/*!40000 ALTER TABLE `tbclientes` DISABLE KEYS */;
INSERT INTO `tbclientes` VALUES (2,'marcos antonio','sarah','2222','ce','8948','213','12212','9282948'),(3,'ducilene','ducilenedu@hotmail.com','57555131453','28728','pb','ce','92389','928974'),(4,'aurilene','auri.ps@bol','94268732','923742','212121','pedra branca','ceara','29870'),(6,'macaco','sarahij','0000001','ppl','ce','8948','213','9282948'),(7,'kdjk','kjkd','985348','298749','5555','kjskd','ce','948758'),(9,'gabriela','sarahij','0287282','ppl','ce','8948','213','19282948'),(13,'adriele','adriele.lima@hotmail.com','222222222','85487678','123444','pedra branca','ceara','2342345');
/*!40000 ALTER TABLE `tbclientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbusuarios`
--

DROP TABLE IF EXISTS `tbusuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbusuarios` (
  `login` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  `nomeusu` varchar(100) NOT NULL,
  `emailusu` varchar(100) NOT NULL,
  `telefoneusu` varchar(20) NOT NULL,
  `enderecousu` varchar(200) NOT NULL,
  `cpfusu` varchar(11) NOT NULL,
  `numepis` int(20) NOT NULL,
  `rgusu` varchar(20) NOT NULL,
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbusuarios`
--

LOCK TABLES `tbusuarios` WRITE;
/*!40000 ALTER TABLE `tbusuarios` DISABLE KEYS */;
INSERT INTO `tbusuarios` VALUES ('funcio','123','user','sarah','sarah.p.pinheiro@hotmail.com','2342','23422423','4342423',123,'123'),('funcionario','123','user','','','','','',0,''),('gerente','admin','admin','','','','','',0,'');
/*!40000 ALTER TABLE `tbusuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbveiculos`
--

DROP TABLE IF EXISTS `tbveiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbveiculos` (
  `modelo` varchar(50) NOT NULL,
  `fabricante` varchar(50) DEFAULT NULL,
  `cor` varchar(50) DEFAULT NULL,
  `ano` varchar(10) DEFAULT NULL,
  `preco` varchar(10) DEFAULT NULL,
  `chassi` varchar(20) NOT NULL,
  PRIMARY KEY (`chassi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbveiculos`
--

LOCK TABLES `tbveiculos` WRITE;
/*!40000 ALTER TABLE `tbveiculos` DISABLE KEYS */;
INSERT INTO `tbveiculos` VALUES ('prisma','ford','preto','2019','3928','048992'),('prisma','ford','cinza','2015','30000','274793'),('sarah','ford','branco','8327','92884','9384'),('uno','fiat','preto','2010','83700','971442'),('sarah','ford','branco','2018','29000','982641930');
/*!40000 ALTER TABLE `tbveiculos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-02 10:46:07
