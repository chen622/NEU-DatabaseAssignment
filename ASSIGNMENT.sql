CREATE DATABASE  IF NOT EXISTS `assignment` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `assignment`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: assignment
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.21-MariaDB

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
-- Table structure for table `cast`
--

DROP TABLE IF EXISTS `cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cast` (
  `prop_id` int(11) NOT NULL,
  `cast` varchar(255) NOT NULL,
  KEY `prop_id_constraint` (`prop_id`),
  CONSTRAINT `prop_id_constraint` FOREIGN KEY (`prop_id`) REFERENCES `dvd_prop` (`prop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cast`
--

LOCK TABLES `cast` WRITE;
/*!40000 ALTER TABLE `cast` DISABLE KEYS */;
INSERT INTO `cast` VALUES (1,'David'),(4,'Kim Jong-un'),(2,'People calling for \"free\"'),(3,'Hirai Ken, Yamata Shinichi'),(5,'Bored Stuff'),(6,'People from DPRK'),(7,'Andrew Ng'),(8,'Lenora Crichlow, Daniel Rigby, Hayley Atwell');
/*!40000 ALTER TABLE `cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd_entity`
--

DROP TABLE IF EXISTS `dvd_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd_entity` (
  `entity_id` int(11) NOT NULL AUTO_INCREMENT,
  `library_name` varchar(64) DEFAULT NULL,
  `prop_id` int(11) NOT NULL,
  PRIMARY KEY (`entity_id`),
  KEY `dvd_library_name_constraint` (`library_name`),
  KEY `dvd_prop_id_constraint` (`prop_id`),
  CONSTRAINT `dvd_library_name_constraint` FOREIGN KEY (`library_name`) REFERENCES `library` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `dvd_prop_id_constraint` FOREIGN KEY (`prop_id`) REFERENCES `dvd_prop` (`prop_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd_entity`
--

LOCK TABLES `dvd_entity` WRITE;
/*!40000 ALTER TABLE `dvd_entity` DISABLE KEYS */;
INSERT INTO `dvd_entity` VALUES (1,'NEUHN',1),(2,'NEUHN',1),(3,'NEUNH',1),(4,'NEUNH',1),(5,'TAIYUAN',1),(6,'NEUHN',2),(7,'NEUHN',2),(8,'NEUNH',3),(9,'NEUHN',3),(10,'TAIYUAN',3),(11,'TAIYUAN',3),(12,'TAIYUAN',3),(13,'TAIYUAN',4),(14,'NEUHN',4),(15,'NEUHN',4);
/*!40000 ALTER TABLE `dvd_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd_genre`
--

DROP TABLE IF EXISTS `dvd_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd_genre` (
  `prop_id` int(11) NOT NULL,
  `genre_type_id` int(11) NOT NULL,
  KEY `genre_dvd_prop_id_constraint` (`prop_id`),
  KEY `genre_type_id_constraint` (`genre_type_id`),
  CONSTRAINT `genre_dvd_prop_id_constraint` FOREIGN KEY (`prop_id`) REFERENCES `dvd_prop` (`prop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `genre_type_id_constraint` FOREIGN KEY (`genre_type_id`) REFERENCES `genre_type` (`genre_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd_genre`
--

LOCK TABLES `dvd_genre` WRITE;
/*!40000 ALTER TABLE `dvd_genre` DISABLE KEYS */;
INSERT INTO `dvd_genre` VALUES (1,5),(2,5),(3,4),(4,1),(8,3),(7,5),(5,1),(6,5);
/*!40000 ALTER TABLE `dvd_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd_prop`
--

DROP TABLE IF EXISTS `dvd_prop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd_prop` (
  `prop_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `release_date` date NOT NULL,
  `director` varchar(32) NOT NULL,
  PRIMARY KEY (`prop_id`),
  UNIQUE KEY `title` (`title`,`release_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd_prop`
--

LOCK TABLES `dvd_prop` WRITE;
/*!40000 ALTER TABLE `dvd_prop` DISABLE KEYS */;
INSERT INTO `dvd_prop` VALUES (1,'Introduction to Database','2018-01-01','David'),(2,'Top Secret Record','1989-06-04','Unknown'),(3,'Hirai Ken Music Video Collection','2017-01-01','Hirai Ken'),(4,'The Interview','2015-01-01','Kim Jong-un'),(5,'Sleep Helping Videos','2018-01-01','A really famous directory'),(6,'North Korea Adventure','1970-01-09','Kim Il-sung'),(7,'Machine Learning','2014-01-01','Andrew Ng'),(8,'Black Mirror','2013-01-18','Otto Bathurst, Brian Welsh');
/*!40000 ALTER TABLE `dvd_prop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre_type`
--

DROP TABLE IF EXISTS `genre_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre_type` (
  `genre_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_type_name` varchar(32) NOT NULL,
  PRIMARY KEY (`genre_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre_type`
--

LOCK TABLES `genre_type` WRITE;
/*!40000 ALTER TABLE `genre_type` DISABLE KEYS */;
INSERT INTO `genre_type` VALUES (1,'comedy'),(2,'action'),(3,'horror'),(4,'romance'),(5,'factual');
/*!40000 ALTER TABLE `genre_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library` (
  `name` varchar(64) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES ('NEUHN','Hunan District, Shenyang, Liaoning, China.'),('NEUNH','Heping District, Shenyang, Liaoning, China.'),('TAIYUAN','Taiyuan St. Shenyang, Liaoning, China.');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(32) NOT NULL,
  `member_address` varchar(255) NOT NULL,
  `category` int(11) NOT NULL,
  `balance` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`member_id`),
  KEY `category_id_constraint` (`category`),
  CONSTRAINT `category_id_constraint` FOREIGN KEY (`category`) REFERENCES `member_category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='Membership and account';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'Bruce Lee','Hunnan Campus of NEU, Shenyang, China.',2,100.00);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_category`
--

DROP TABLE IF EXISTS `member_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(32) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_category`
--

LOCK TABLES `member_category` WRITE;
/*!40000 ALTER TABLE `member_category` DISABLE KEYS */;
INSERT INTO `member_category` VALUES (1,'normal',5.00),(2,'premium',3.00);
/*!40000 ALTER TABLE `member_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `date_taken_from` date NOT NULL,
  `library_taken_from` varchar(64) NOT NULL,
  `date_return_on` date DEFAULT NULL,
  `library_return_on` varchar(64) DEFAULT NULL,
  `money` decimal(8,2) NOT NULL,
  PRIMARY KEY (`rental_id`),
  KEY `entity_id_constraint` (`entity_id`),
  KEY `library_return_on_constraint` (`library_return_on`),
  KEY `library_taken_from_constraint` (`library_taken_from`),
  KEY `member_id_constraint` (`member_id`),
  CONSTRAINT `entity_id_constraint` FOREIGN KEY (`entity_id`) REFERENCES `dvd_entity` (`entity_id`),
  CONSTRAINT `library_return_on_constraint` FOREIGN KEY (`library_return_on`) REFERENCES `library` (`name`),
  CONSTRAINT `library_taken_from_constraint` FOREIGN KEY (`library_taken_from`) REFERENCES `library` (`name`),
  CONSTRAINT `member_id_constraint` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 21:44:57