-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 34.77.90.167    Database: pentru_licenta
-- ------------------------------------------------------
-- Server version	5.7.25-google-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '113a1e10-818a-11ea-b956-42010a84005e:1-1340453';

--
-- Table structure for table `abonat`
--

DROP TABLE IF EXISTS `abonat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `abonat` (
  `Client_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Nume` varchar(30) NOT NULL,
  `Prenume` varchar(30) NOT NULL,
  `CNP` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Client_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonat`
--

LOCK TABLES `abonat` WRITE;
/*!40000 ALTER TABLE `abonat` DISABLE KEYS */;
INSERT INTO `abonat` VALUES (1,'Ionescu','Ion','1910619253461'),(2,'Popescu','Andreea','2920721253645'),(3,'Popa','Diana','2810529233471'),(5,'Andreescu','Alina','2760312253345'),(6,'Branescu','Constantin','1720611245431'),(30,'Popescu','Ion','1720611235421'),(31,'Ionescu','Andrei','1720611237651'),(32,'Ionascu','Ionel','1920611237645');
/*!40000 ALTER TABLE `abonat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adresa`
--

DROP TABLE IF EXISTS `adresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `adresa` (
  `Contr_NR` int(6) NOT NULL,
  `Tara` varchar(30) NOT NULL,
  `Judet` varchar(30) NOT NULL,
  `Localitate` varchar(30) NOT NULL,
  `Strada` varchar(30) DEFAULT NULL,
  `Numar` int(5) NOT NULL,
  `Bloc` int(5) DEFAULT NULL,
  `Scara` int(5) DEFAULT NULL,
  `Apartament` int(5) DEFAULT NULL,
  `Table_Key` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Table_Key`),
  KEY `contr_nr_adr_idx` (`Contr_NR`),
  CONSTRAINT `contr_nr_adr` FOREIGN KEY (`Contr_NR`) REFERENCES `contract` (`Contr_NR`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresa`
--

LOCK TABLES `adresa` WRITE;
/*!40000 ALTER TABLE `adresa` DISABLE KEYS */;
INSERT INTO `adresa` VALUES (1,'Romania','GJ','Baia de Fier','Principala',23,0,0,0,1),(3,'Romania','TM','Timisoara','Calea Buziasului',45,2,2,34,3),(3,'Romania','VL','Rm. Valcea','Nucilor',43,3,1,28,4),(4,'Romania','DJ','Craiova','Plopilor',234,4,2,45,5),(13,'Romania','TM','Timisoara','Marius Nemtoc',345,1,3,23,6),(14,'Romania','GJ','Tg. Jiu','9 Mai',32,7,4,65,7),(21,'Ro','GJ','Tg Jiu','Nucilor',12,1,1,1,11),(22,'Ro','Gj','Tg. Jiu','Principala',2,3,2,1,12);
/*!40000 ALTER TABLE `adresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contract` (
  `Contr_NR` int(6) NOT NULL AUTO_INCREMENT,
  `Client_ID` int(6) NOT NULL,
  `Data_contract` date NOT NULL,
  `Durata` int(2) NOT NULL,
  PRIMARY KEY (`Contr_NR`),
  KEY `client_id_idx` (`Client_ID`),
  CONSTRAINT `client_id_ctr` FOREIGN KEY (`Client_ID`) REFERENCES `abonat` (`Client_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,1,'2020-04-01',2),(3,2,'2020-04-08',2),(4,5,'2020-05-08',2),(13,3,'2019-05-05',2),(14,6,'2020-04-24',2),(16,2,'2020-06-22',2),(21,30,'2020-07-03',2),(22,31,'2020-07-03',2);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deranjamente`
--

DROP TABLE IF EXISTS `deranjamente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `deranjamente` (
  `Client_ID` int(6) NOT NULL,
  `Contr_NR` int(6) NOT NULL,
  `Problema` varchar(150) DEFAULT NULL,
  `Data_deranjament` date NOT NULL,
  `Serviciu` varchar(15) NOT NULL,
  `Stare` varchar(15) NOT NULL,
  `Id_deranjament` int(6) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id_deranjament`),
  KEY `client_id_drj_idx` (`Client_ID`),
  KEY `contr_nr_drj_idx` (`Contr_NR`),
  CONSTRAINT `client_id_drj` FOREIGN KEY (`Client_ID`) REFERENCES `abonat` (`Client_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `contr_nr_drj` FOREIGN KEY (`Contr_NR`) REFERENCES `contract` (`Contr_NR`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deranjamente`
--

LOCK TABLES `deranjamente` WRITE;
/*!40000 ALTER TABLE `deranjamente` DISABLE KEYS */;
INSERT INTO `deranjamente` VALUES (3,13,'Merge slab','2020-06-21','Internet','Nerezolvat',5),(3,13,'Nu se vede bine','2020-06-21','CabluTV','Preluat',6),(6,14,'Nu se poate conecta','2020-06-20','Internet','Preluat',7),(6,14,'Nu merge bine','2020-06-16','CabluTV','Nerezolvat',10);
/*!40000 ALTER TABLE `deranjamente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicii`
--

DROP TABLE IF EXISTS `servicii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `servicii` (
  `Client_ID` int(6) NOT NULL,
  `Tip_serv` varchar(15) NOT NULL,
  `Pret` int(3) NOT NULL,
  `Id_serv` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id_serv`),
  KEY `client_id_idx` (`Client_ID`),
  CONSTRAINT `client_id_src` FOREIGN KEY (`Client_ID`) REFERENCES `abonat` (`Client_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicii`
--

LOCK TABLES `servicii` WRITE;
/*!40000 ALTER TABLE `servicii` DISABLE KEYS */;
INSERT INTO `servicii` VALUES (1,'Internet',50,19),(1,'Cablu TV',25,20),(3,'Cablu TV',25,29),(3,'Internet',50,30),(5,'Cablu TV',25,31),(6,'Internet',50,32),(2,'Cablu TV',25,33),(30,'Intenet',50,36);
/*!40000 ALTER TABLE `servicii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizator`
--

DROP TABLE IF EXISTS `utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utilizator` (
  `User_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Nume` varchar(15) NOT NULL,
  `Parola` varchar(15) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `ImageURL` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  UNIQUE KEY `User_ID_UNIQUE` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
INSERT INTO `utilizator` VALUES (1,'Ion78','123','1234','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(73,'Ana','123','ana@gmail.com','C:\\Users\\Teddy9812\\Desktop\\671fd133be858a4e89d2efda7882eeb496abb94c_full.jpg'),(74,'Ion Tiriac','123456789','ion.ion@gmail.com',NULL),(75,'Ion98','1234','12',NULL),(76,'Maria','qwert','qw',NULL),(77,'Marian','1234','1234',NULL),(78,'Ionovici','123','245','C:\\Users\\Teddy9812\\Desktop\\19105498_1356585624396207_713877810694556684_n.jpg'),(79,'Ionus','123','123','C:\\Users\\Teddy9812\\Desktop\\19105498_1356585624396207_713877810694556684_n.jpg'),(80,'Constantin','1234','feufegheighe','C:\\Users\\Teddy9812\\Desktop\\153955-spacex-wallpaper-1920x1080-for-tablet.jpg'),(81,'Avatar','123','123','C:\\Users\\Teddy9812\\Documents\\man-hairstyle-for-round-face-164223-22-haircut-name-for-men-classic-undercut-hairstyle-round-face-male-of-man-hairstyle-for-round-face.jpg'),(82,'Ionica','1234','re',NULL),(83,'Ionica123','123','123','C:\\Users\\Teddy9812\\Desktop\\stuff 5\\image_test.jpg'),(84,'Ana12345','12345','ana','C:\\Users\\Teddy9812\\Desktop\\stuff4\\wp2022265.jpg'),(85,'Andrei','123','123','D:\\Logo.jpg'),(86,'Ionica12','123','ion@f',NULL),(87,'Ana','1234','teddy@gmail.com','C:\\Users\\Teddy9812\\Desktop\\671fd133be858a4e89d2efda7882eeb496abb94c_full.jpg'),(88,'Camelia','1234','ion@gmail.com','C:\\Users\\Teddy9812\\Desktop\\STUFF 6\\94763956_5262768147385260_5714006271347130368_o.jpg'),(89,'Marcel123','123','marci@gmail.com','C:\\Users\\Teddy9812\\Desktop\\153955-spacex-wallpaper-1920x1080-for-tablet.jpg'),(90,'Ana123456','123456','ana@gmail.com',NULL),(91,'Ana123456789','123456789','ana12@gmail.com',NULL),(92,'Adrian2020','12345','adrian@gmail.com','C:\\Users\\Teddy9812\\Desktop\\671fd133be858a4e89d2efda7882eeb496abb94c_full.jpg'),(93,'Adrian2021','12345','adrian21@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(94,'Ana2015','12345','ana2015@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(95,'Teodor2021','12345','teodor98@gmail.com',NULL),(97,'Teddy98','12345','teddy@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(98,'Ionel2020','12345','ionel@gmail.com',NULL),(99,'Ionel2016','12345','ionelion@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(100,'Paul2000','12345','paul@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(101,'Andrei2000','12345','andrei@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg'),(102,'Andrei2021','12345','andreib@gmail.com',NULL),(103,'Bogdan','12345','bogdan@gmail.com','C:\\Users\\Teddy9812\\Desktop\\Teodor Branescu.jpg');
/*!40000 ALTER TABLE `utilizator` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-05 10:12:59
