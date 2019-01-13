-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.33-0ubuntu0.14.04.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


USE openmrs;

TRUNCATE ptme_reporting_dataset;
TRUNCATE ptme_reporting_dataset_indicator;
TRUNCATE ptme_reporting_report;
TRUNCATE ptme_reporting_report_dataset;

--
-- Dumping data for table `ptme_reporting_dataset`
--

/*!40000 ALTER TABLE `ptme_reporting_dataset` DISABLE KEYS */;
INSERT INTO `ptme_reporting_dataset` (`dataset_id`,`name`,`code`,`uuid`,`creator`,`date_created`,`changed_by`,`date_changed`,`voided`,`voided_by`,`date_voided`,`void_reason`) VALUES 
 (1,'Conseil et dépistage en CPN et en Maternité','DS1','25357924-ab0b-4b5b-ba9d-1582e17831d7',1,'2018-11-05 19:29:59',1,'2018-11-07 17:00:40',0,NULL,NULL,NULL),
 (2,'Suivi des mères et des enfants nés de mères séropositives au VIH','DS2','27a53cfa-9c10-4d22-9c9e-d7138449dccc',1,'2018-11-09 19:53:48',NULL,NULL,0,NULL,NULL,NULL),
 (3,'Dépistage du VIH chez les enfants nés de mère séropositive au VIH','DS3','da403c6b-57b9-49c2-84dd-2dc60dc67d4a',1,'2018-11-11 22:38:22',NULL,NULL,0,NULL,NULL,NULL),
 (4,'Dépistage des conjoints des femmes enceintes','DS4','d483ae85-d669-41d5-aa2b-5b9aee1a4841',1,'2018-11-12 08:53:21',1,'2018-12-19 10:34:02',0,NULL,NULL,NULL),
 (5,'Femmes enceintes ou allaitantes depistées positives nouvellement mises sous traitement ARV','DS5','5a35e688-ab91-454f-8f2a-2462af0e3336',1,'2018-12-09 12:35:50',NULL,NULL,0,NULL,NULL,NULL),
 (6,'Femmes déjà sous ARV contractant une nouvelle grossesse','DS6','8dbe3ab5-8925-4991-b4b2-e08bc8ebade3',1,'2018-12-09 12:37:10',1,'2018-12-09 12:55:19',0,NULL,NULL,NULL),
 (7,'Suivi des rendez-vous des enfants avant 2 mois','DS7','ee7867de-e5df-4dfe-a43f-911f340d730a',1,'2018-12-09 12:38:08',NULL,NULL,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ptme_reporting_dataset` ENABLE KEYS */;


--
-- Dumping data for table `ptme_reporting_dataset_indicator`
--

/*!40000 ALTER TABLE `ptme_reporting_dataset_indicator` DISABLE KEYS */;
INSERT INTO `ptme_reporting_dataset_indicator` (`indicator_id`,`dataset_id`) VALUES 
 (1,1),
 (2,1),
 (3,1),
 (4,1),
 (5,1),
 (6,1),
 (7,1),
 (8,2),
 (9,2),
 (10,2),
 (11,2),
 (12,3),
 (13,3),
 (14,3),
 (15,3),
 (16,3),
 (17,4),
 (18,4),
 (43,4),
 (44,4),
 (19,5),
 (20,5),
 (21,5),
 (22,5),
 (23,5),
 (24,5),
 (25,5),
 (26,5),
 (27,5),
 (28,5),
 (29,6),
 (30,6),
 (31,6),
 (32,6),
 (33,6),
 (34,6),
 (35,6),
 (36,6),
 (37,6),
 (38,6),
 (39,7),
 (40,7),
 (41,7),
 (42,7);
/*!40000 ALTER TABLE `ptme_reporting_dataset_indicator` ENABLE KEYS */;

--
-- Dumping data for table `ptme_reporting_report`
--

/*!40000 ALTER TABLE `ptme_reporting_report` DISABLE KEYS */;
REPLACE INTO `ptme_reporting_report` (`report_id`,`report_label`,`description`,`reporting_template_id`,`uuid`,`creator`,`date_created`,`changed_by`,`date_changed`,`voided`,`voided_by`,`date_voided`,`void_reason`) VALUES 
 (1,'Rapport Mensuel de la Prévention de la Transmission Mère - Enfant (PTME)',NULL,1,'145c0d54-44fc-44a6-9fbd-bdb4cd897b82',1,'2018-11-05 19:37:31',1,'2018-11-12 08:53:54',0,NULL,NULL,NULL),
 (2,'Rapport Mensuel de Suivi de Cohorte en PTME',NULL,2,'81d44e0e-380b-4b7a-a64d-6ba924cf2437',1,'2018-12-09 12:49:33',1,'2018-12-09 12:49:54',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ptme_reporting_report` ENABLE KEYS */;


--
-- Dumping data for table `ptme_reporting_report_dataset`
--

/*!40000 ALTER TABLE `ptme_reporting_report_dataset` DISABLE KEYS */;
REPLACE INTO `ptme_reporting_report_dataset` (`report_id`,`dataset_id`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (2,5),
 (2,6),
 (2,7);
/*!40000 ALTER TABLE `ptme_reporting_report_dataset` ENABLE KEYS */;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
