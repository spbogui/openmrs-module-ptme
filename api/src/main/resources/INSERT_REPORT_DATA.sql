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


--
-- Create schema openmrs
--

USE openmrs;

--
-- Dumping data for table `ptme_reporting_dataset`
--

/*!40000 ALTER TABLE `ptme_reporting_dataset` DISABLE KEYS */;
REPLACE INTO `ptme_reporting_dataset` (`dataset_id`,`name`,`code`,`uuid`,`creator`,`date_created`,`changed_by`,`date_changed`,`voided`,`voided_by`,`date_voided`,`void_reason`) VALUES
 (1,'Conseil et d&eacute;pistage en CPN et en Maternit&eacute;','DS1','25357924-ab0b-4b5b-ba9d-1582e17831d7',1,'2018-11-05 19:29:59',1,'2019-01-15 09:18:50',0,NULL,NULL,NULL),
 (2,'Suivi des m&egrave;res et des enfants n&eacute;s de m&egrave;res s&eacute;ropositives au VIH','DS2','27a53cfa-9c10-4d22-9c9e-d7138449dccc',1,'2018-11-09 19:53:48',1,'2019-01-15 09:19:36',0,NULL,NULL,NULL),
 (3,'D&eacute;pistage du VIH chez les enfants n&eacute;s de m&egrave;re s&eacute;ropositive au VIH','DS3','da403c6b-57b9-49c2-84dd-2dc60dc67d4a',1,'2018-11-11 22:38:22',1,'2019-01-15 09:19:13',0,NULL,NULL,NULL),
 (4,'D&eacute;pistage des conjoints des femmes enceintes','DS4','d483ae85-d669-41d5-aa2b-5b9aee1a4841',1,'2018-11-12 08:53:21',1,'2019-01-15 09:19:06',0,NULL,NULL,NULL),
 (5,'Femmes enceintes ou allaitantes depist&eacute;es positives nouvellement mises sous traitement ARV','DS5','5a35e688-ab91-454f-8f2a-2462af0e3336',1,'2018-12-09 12:35:50',1,'2019-01-15 09:19:30',0,NULL,NULL,NULL),
 (6,'Femmes d&eacute;j&agrave; sous ARV contractant une nouvelle grossesse','DS6','8dbe3ab5-8925-4991-b4b2-e08bc8ebade3',1,'2018-12-09 12:37:10',1,'2019-01-15 09:19:20',0,NULL,NULL,NULL),
 (7,'Suivi des rendez-vous des enfants expos&eacute;s avant 2 mois','DS7','ee7867de-e5df-4dfe-a43f-911f340d730a',1,'2018-12-09 12:38:08',1,'2019-01-15 09:20:32',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ptme_reporting_dataset` ENABLE KEYS */;


--
-- Dumping data for table `ptme_reporting_dataset_indicator`
--

/*!40000 ALTER TABLE `ptme_reporting_dataset_indicator` DISABLE KEYS */;
REPLACE INTO `ptme_reporting_dataset_indicator` (`indicator_id`,`dataset_id`) VALUES
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
 (1,'Rapport Mensuel de la Pr&eacute;vention de la Transmission M&egrave;re - Enfant (PTME)',NULL,1,'145c0d54-44fc-44a6-9fbd-bdb4cd897b82',1,'2018-11-05 19:37:31',1,'2019-01-15 09:04:08',0,NULL,NULL,NULL),
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
