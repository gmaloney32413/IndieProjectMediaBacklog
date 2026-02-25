-- MySQL dump 10.13  Distrib 8.4.8, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mediabacklog
-- ------------------------------------------------------
-- Server version	8.4.8

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `backlog_entries`
--

DROP TABLE IF EXISTS `backlog_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `backlog_entries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `media_id` bigint NOT NULL,
  `status` varchar(50) NOT NULL,
  `notes` text,
  `user_rating` int DEFAULT NULL,
  `date_added` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_backlog_user` (`user_id`),
  KEY `fk_backlog_media` (`media_id`),
  CONSTRAINT `fk_backlog_media` FOREIGN KEY (`media_id`) REFERENCES `media_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_backlog_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backlog_entries`
--

LOCK TABLES `backlog_entries` WRITE;
/*!40000 ALTER TABLE `backlog_entries` DISABLE KEYS */;
INSERT INTO `backlog_entries` VALUES (1,1,1,'Completed','Mind-bending movie!',10,'2026-02-23 23:29:17'),(2,1,4,'In Progress','Weekly watch with friends.',NULL,'2026-02-23 23:29:17'),(3,1,6,'Planned','Want to play this over summer.',NULL,'2026-02-23 23:29:17'),(4,2,2,'Completed','Loved the Matrix trilogy!',9,'2026-02-23 23:29:17'),(5,2,5,'Completed','Breaking Bad is a masterpiece.',10,'2026-02-23 23:29:17'),(6,2,7,'Planned','Excited to try God of War.',NULL,'2026-02-23 23:29:17'),(7,3,3,'In Progress','Interstellar is confusing but amazing.',8,'2026-02-23 23:29:17'),(8,3,8,'In Progress','Playing Minecraft with friends.',NULL,'2026-02-23 23:29:17');
/*!40000 ALTER TABLE `backlog_entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_items`
--

DROP TABLE IF EXISTS `media_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tmdb_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `overview` text,
  `media_type` varchar(50) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `poster_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_items`
--

LOCK TABLES `media_items` WRITE;
/*!40000 ALTER TABLE `media_items` DISABLE KEYS */;
INSERT INTO `media_items` VALUES (1,101,'Inception','A thief steals corporate secrets through dream-sharing technology.','movie','2010-07-16','https://image.tmdb.org/t/p/original/inception.jpg'),(2,102,'The Matrix','A computer hacker learns about the true nature of reality.','movie','1999-03-31','https://image.tmdb.org/t/p/original/matrix.jpg'),(3,103,'Interstellar','A team of explorers travel through a wormhole in space.','movie','2014-11-07','https://image.tmdb.org/t/p/original/interstellar.jpg'),(4,201,'Stranger Things','Kids uncover supernatural mysteries in their small town.','tv_show','2016-07-15','https://image.tmdb.org/t/p/original/strangerthings.jpg'),(5,202,'Breaking Bad','A chemistry teacher turns to making meth.','tv_show','2008-01-20','https://image.tmdb.org/t/p/original/breakingbad.jpg'),(6,301,'The Legend of Zelda: Breath of the Wild','Open-world action-adventure game.','videogame','2017-03-03','https://image.tmdb.org/t/p/original/zelda.jpg'),(7,302,'God of War','Kratos and his son embark on a journey through Norse mythology.','videogame','2018-04-20','https://image.tmdb.org/t/p/original/godofwar.jpg'),(8,303,'Minecraft','Sandbox building and survival game.','videogame','2011-11-18','https://image.tmdb.org/t/p/original/minecraft.jpg');
/*!40000 ALTER TABLE `media_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` bigint NOT NULL,
  `runtime` int DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `rating` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_movies_media` FOREIGN KEY (`id`) REFERENCES `media_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,148,'Christopher Nolan','PG-13'),(2,136,'The Wachowskis','R'),(3,169,'Christopher Nolan','PG-13');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tv_shows`
--

DROP TABLE IF EXISTS `tv_shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tv_shows` (
  `id` bigint NOT NULL,
  `number_of_seasons` int DEFAULT NULL,
  `total_episodes` int DEFAULT NULL,
  `ongoing` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tvshows_media` FOREIGN KEY (`id`) REFERENCES `media_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tv_shows`
--

LOCK TABLES `tv_shows` WRITE;
/*!40000 ALTER TABLE `tv_shows` DISABLE KEYS */;
INSERT INTO `tv_shows` VALUES (4,4,34,1),(5,5,62,0);
/*!40000 ALTER TABLE `tv_shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice@example.com','password123'),(2,'bob@example.com','securepass'),(3,'charlie@example.com','charlie2026');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videogames`
--

DROP TABLE IF EXISTS `videogames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videogames` (
  `id` bigint NOT NULL,
  `platform` varchar(100) DEFAULT NULL,
  `developer` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `esrb_rating` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_videogames_media` FOREIGN KEY (`id`) REFERENCES `media_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videogames`
--

LOCK TABLES `videogames` WRITE;
/*!40000 ALTER TABLE `videogames` DISABLE KEYS */;
INSERT INTO `videogames` VALUES (6,'Nintendo Switch','Nintendo EPD','Nintendo','E10+'),(7,'PlayStation 4','SIE Santa Monica Studio','Sony','M'),(8,'Multiple Platforms','Mojang Studios','Mojang/Microsoft','E10+');
/*!40000 ALTER TABLE `videogames` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-25 14:53:33
