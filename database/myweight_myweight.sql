-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: myweight
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `myweight`
--

DROP TABLE IF EXISTS `myweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `myweight` (
  `date` varchar(13) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `height` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myweight`
--

LOCK TABLES `myweight` WRITE;
/*!40000 ALTER TABLE `myweight` DISABLE KEYS */;
INSERT INTO `myweight` VALUES ('19.06.19',85.5,176),('19.06.19',85.5,176),('TESTDAY',177,96),('2019년 6월 23일 ',80,60),('2019년 5월 23일 ',70,170),('2019년 6월 21일 ',79,177),('2019년 5월 21일 ',78,177),('2019년 5월 28일 ',80,176),('2019년 6월 14일 ',79,177),('2019년 5월 26일 ',80,177),('2019년 5월 29일 ',80,177),('2019년 5월 1일 ',90,177),('2019년 6월 24일 ',80,177),('2019년 6월 22일 ',80,177),('2019년 6월 1일 ',80,177),('2019년 5월 31일 ',79,177),('2019년 4월 30일 ',91,177),('2019년 6월 2일 ',79,177),('2019년 6월 3일 ',78,177),('2019년 6월 4일 ',80,177),('2019년 6월 5일 ',81,177),('2019년 6월 6일 ',80,177),('2019년 6월 7일 ',78,177),('2019년 6월 8일 ',79,177),('2019년 6월 9일 ',79.5,177),('2019년 6월 10일 ',78.5,177),('2019년 6월 11일 ',78.8,177),('2019년 6월 12일 ',78.9,177),('2019년 6월 13일 ',79.3,177),('2019년 6월 15일 ',78.8,177),('2019년 6월 16일 ',78.8,177),('2019년 6월 17일 ',79.5,177),('2019년 6월 18일 ',81,177),('2019년 6월 19일 ',80.5,177),('2019년 6월 20일 ',79.5,177);
/*!40000 ALTER TABLE `myweight` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-27  9:13:35
