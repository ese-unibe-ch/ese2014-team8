-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2014 at 02:29 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ese`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipCode` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `city`, `number`, `street`, `zipCode`) VALUES
(1, 'Bern', 127, 'Freiburgstrasse', 3000),
(3, 'Bern', 127, 'Freiburgstrasse', 3000),
(4, 'Bern', 127, 'Freiburgstrasse', 3000),
(5, 'Bern', 127, 'Freiburgstrasse', 3000),
(6, 'Bern', 127, 'Freiburgstrasse', 3000),
(7, 'Bern', 127, 'Freiburgstrasse', 3000),
(8, 'Bern', 76, 'Laupenstrasse', 3000),
(9, 'Bern', 76, 'Laupenstrasse', 3000),
(10, 'ZÃ¼rich', 99, 'Witikonerstrasse', 8000),
(11, 'Zurich', 99, 'Witikonerstrasse', 8000),
(12, 'Bern', 76, 'Lorrainestrasse', 3000),
(13, 'Bern', 76, 'Lorrainestrasse', 3000),
(14, 'Bern', 76, 'Lorrainestrasse', 3000),
(15, 'Bern', 76, 'Lorrainestrasse', 3000),
(16, 'Bern', 76, 'Lorrainestrasse', 3000),
(17, 'Bern', 76, 'Lorrainestrasse', 3000),
(18, 'Burgdorf', 33, 'Bernstrasse', 3400),
(19, 'Bern', 10, 'Ankerstrasse', 3000),
(21, 'Zurich', 17, 'Eichhalde', 8000),
(22, 'Bern', 89, 'Gesellschaftsstrasse', 3000),
(23, 'Bern', 89, 'Gesellschaftsstrasse', 3000),
(24, 'Bern', 89, 'Gesellschaftsstrasse', 3000);

-- --------------------------------------------------------

--
-- Table structure for table `apartment`
--

CREATE TABLE IF NOT EXISTS `apartment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `distanceToPark` int(11) NOT NULL,
  `distanceToPubTr` int(11) NOT NULL,
  `distanceToSchool` int(11) NOT NULL,
  `distanceToShop` int(11) NOT NULL,
  `fixedMoveIn` bit(1) NOT NULL,
  `fixedMoveOut` bit(1) NOT NULL,
  `moveIn` date DEFAULT NULL,
  `moveOut` date DEFAULT NULL,
  `numberOfImages` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `numberOfRooms` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `tags_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ewnf2duijglxn8qn3h3tpp4it` (`address_id`),
  KEY `FK_jl21a7rwsy78kay0ssrl7rfhg` (`owner_id`),
  KEY `FK_8nf93ngilh83gs874x4fnm4m7` (`tags_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `apartment`
--

INSERT INTO `apartment` (`id`, `description`, `distanceToPark`, `distanceToPubTr`, `distanceToSchool`, `distanceToShop`, `fixedMoveIn`, `fixedMoveOut`, `moveIn`, `moveOut`, `numberOfImages`, `price`, `title`, `numberOfRooms`, `size`, `address_id`, `owner_id`, `tags_id`) VALUES
(1, 'With very nice neighbors! ', 0, 400, 300, 200, b'1', b'0', '2014-12-30', NULL, 10, 5000, 'Villa out in the green', 7, 200, 7, 1, 7),
(2, 'There live three families together. All families have very lively children.', 0, 0, 0, 0, b'0', b'0', NULL, NULL, 10, 1950, 'Mehrfamilien-Haus', 3, 120, 9, 1, 9),
(3, 'Rather a small flat.', 455, 0, 234, 33, b'1', b'0', '2014-12-25', NULL, 10, 1250, 'Block', 2, 70, 11, 1, 11),
(4, 'Ein Wohnblock mit vielen Kindern und Familien.', 0, 0, 0, 0, b'1', b'0', '2015-02-01', NULL, 10, 890, 'Flat', 4, 70, 18, 2, 12),
(5, 'Near to a lake', 234, 30, 233, 700, b'0', b'0', NULL, NULL, 10, 2990, 'Shelter at the Egel-Lake', 6, 250, 19, 2, 13),
(6, 'It''s always cool even in the Summer.', 0, 0, 0, 0, b'1', b'0', '2015-01-04', NULL, 10, 4000, 'Cave', 4, 500, 21, 2, 15);

-- --------------------------------------------------------

--
-- Table structure for table `apartmenttags`
--

CREATE TABLE IF NOT EXISTS `apartmenttags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balcony` bit(1) NOT NULL,
  `bikeParking` bit(1) NOT NULL,
  `carParking` bit(1) NOT NULL,
  `elevator` bit(1) NOT NULL,
  `lowEnergyBuilding` bit(1) NOT NULL,
  `musicInstrumentsAllowed` bit(1) NOT NULL,
  `petsAllowed` bit(1) NOT NULL,
  `quietNeighbourhood` bit(1) NOT NULL,
  `sharedGarden` bit(1) NOT NULL,
  `smokingAllowed` bit(1) NOT NULL,
  `wheelchairAccessible` bit(1) NOT NULL,
  `kidFriendly` bit(1) NOT NULL,
  `onBusyRoad` bit(1) NOT NULL,
  `playgroundNearby` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `apartmenttags`
--

INSERT INTO `apartmenttags` (`id`, `balcony`, `bikeParking`, `carParking`, `elevator`, `lowEnergyBuilding`, `musicInstrumentsAllowed`, `petsAllowed`, `quietNeighbourhood`, `sharedGarden`, `smokingAllowed`, `wheelchairAccessible`, `kidFriendly`, `onBusyRoad`, `playgroundNearby`) VALUES
(1, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(3, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(4, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(5, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(6, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(7, b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'0', b'0', b'1'),
(8, b'1', b'1', b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0'),
(9, b'1', b'1', b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0'),
(10, b'0', b'0', b'1', b'0', b'0', b'0', b'1', b'0', b'0', b'1', b'0', b'0', b'1', b'0'),
(11, b'0', b'0', b'1', b'0', b'1', b'0', b'1', b'0', b'0', b'1', b'0', b'0', b'1', b'0'),
(12, b'1', b'0', b'0', b'1', b'0', b'1', b'1', b'0', b'0', b'1', b'0', b'0', b'1', b'0'),
(13, b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'0', b'1'),
(15, b'1', b'0', b'0', b'0', b'0', b'1', b'0', b'1', b'1', b'1', b'0', b'0', b'0', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `bookmark`
--

CREATE TABLE IF NOT EXISTS `bookmark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ap_id` bigint(20) DEFAULT NULL,
  `bookMarker_id` bigint(20) DEFAULT NULL,
  `shAp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r57suaxbkxfsdqjbytwjq11r6` (`ap_id`),
  KEY `FK_g1cm54tw64enk4a8tykfqgl6q` (`bookMarker_id`),
  KEY `FK_g84176hf1wlco7xu80lux0ox2` (`shAp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `dateTime` datetime DEFAULT NULL,
  `messageRead` bit(1) NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `ap_id` bigint(20) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `shAp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rku810o614p561fx8plnhymj1` (`ap_id`),
  KEY `FK_g8bx8mm7c3r5wdu9bdjq21bdr` (`receiver_id`),
  KEY `FK_tbto6hemu447oixxk730el2vx` (`sender_id`),
  KEY `FK_5tmqapy93pqsx79pvd6hde0b2` (`shAp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `imageSaved` bit(1) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `sex` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `age`, `description`, `firstName`, `imageSaved`, `lastName`, `sex`) VALUES
(1, 38, 'Oldest programmer student at the university of Berne ;)', 'Michael', b'1', 'Grünig', 'm'),
(2, 67, 'The cook!', 'Ursula', b'0', 'Mueller', 'f'),
(3, 80, 'I feel still so young!', 'Peter', b'0', 'Meier', 'm'),
(4, 29, 'Please help me out of here!', 'Maria', b'0', 'Magdalena', 'f'),
(5, 27, 'Could program day and night.', 'Sara', b'1', 'Peeters', 'f'),
(6, 25, 'Verkäuferinnen Lehere.', 'Irene', b'0', 'Sommer', 'f'),
(7, 22, 'Party, Party .. every day.', 'Hans', b'0', 'Brunner', 'm'),
(8, 19, 'Don''t know how to cook.', 'Simon', b'0', 'Frutiger', 'o');

-- --------------------------------------------------------

--
-- Table structure for table `roommate`
--

CREATE TABLE IF NOT EXISTS `roommate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `shApartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bi2kx23bkshu368clqi9uhmwe` (`address_id`),
  KEY `FK_rokbo9cfm7bag0krs096l4ej2` (`person_id`),
  KEY `FK_ikfei1qiw7qegt0hi0oyri86n` (`shApartment_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `roommate`
--

INSERT INTO `roommate` (`id`, `address_id`, `person_id`, `shApartment_id`) VALUES
(1, NULL, 2, 1),
(2, NULL, 3, 1),
(3, NULL, 4, 1),
(4, NULL, 6, 2),
(5, NULL, 7, 2),
(6, NULL, 8, 2);

-- --------------------------------------------------------

--
-- Table structure for table `shapartment`
--

CREATE TABLE IF NOT EXISTS `shapartment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `distanceToPark` int(11) NOT NULL,
  `distanceToPubTr` int(11) NOT NULL,
  `distanceToSchool` int(11) NOT NULL,
  `distanceToShop` int(11) NOT NULL,
  `fixedMoveIn` bit(1) NOT NULL,
  `fixedMoveOut` bit(1) NOT NULL,
  `moveIn` date DEFAULT NULL,
  `moveOut` date DEFAULT NULL,
  `numberOfImages` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `roomSize` int(11) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `tags_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jfjp5uxlc6dmyeepeb5wqigfu` (`address_id`),
  KEY `FK_hv8uhkdsbrdbfnh8w777e4aep` (`owner_id`),
  KEY `FK_pd390j3o6mei7of1i28a9few` (`tags_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `shapartment`
--

INSERT INTO `shapartment` (`id`, `description`, `distanceToPark`, `distanceToPubTr`, `distanceToSchool`, `distanceToShop`, `fixedMoveIn`, `fixedMoveOut`, `moveIn`, `moveOut`, `numberOfImages`, `price`, `title`, `roomSize`, `address_id`, `owner_id`, `tags_id`) VALUES
(1, 'We sing every Wednesday!', 50, 200, 100, 300, b'0', b'0', NULL, NULL, 10, 560, 'Shared Apartment for old people', 30, 17, 1, 6),
(2, 'Rather for young people.', 0, 0, 0, 0, b'0', b'0', NULL, NULL, 10, 550, 'Normal Shared Apartment', 25, 24, 2, 9);

-- --------------------------------------------------------

--
-- Table structure for table `shapartmenttags`
--

CREATE TABLE IF NOT EXISTS `shapartmenttags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balcony` bit(1) NOT NULL,
  `bikeParking` bit(1) NOT NULL,
  `carParking` bit(1) NOT NULL,
  `elevator` bit(1) NOT NULL,
  `lowEnergyBuilding` bit(1) NOT NULL,
  `musicInstrumentsAllowed` bit(1) NOT NULL,
  `petsAllowed` bit(1) NOT NULL,
  `quietNeighbourhood` bit(1) NOT NULL,
  `sharedGarden` bit(1) NOT NULL,
  `smokingAllowed` bit(1) NOT NULL,
  `wheelchairAccessible` bit(1) NOT NULL,
  `eatingCookingTogether` bit(1) NOT NULL,
  `nonVegetarian` bit(1) NOT NULL,
  `stayingWeekends` bit(1) NOT NULL,
  `vegetarianVegan` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `shapartmenttags`
--

INSERT INTO `shapartmenttags` (`id`, `balcony`, `bikeParking`, `carParking`, `elevator`, `lowEnergyBuilding`, `musicInstrumentsAllowed`, `petsAllowed`, `quietNeighbourhood`, `sharedGarden`, `smokingAllowed`, `wheelchairAccessible`, `eatingCookingTogether`, `nonVegetarian`, `stayingWeekends`, `vegetarianVegan`) VALUES
(1, b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(2, b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(3, b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(4, b'0', b'0', b'1', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(5, b'0', b'0', b'1', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(6, b'0', b'0', b'1', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0', b'0'),
(7, b'1', b'0', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'1', b'0'),
(8, b'1', b'0', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'1', b'0'),
(9, b'1', b'0', b'0', b'0', b'1', b'0', b'0', b'0', b'0', b'0', b'1', b'1', b'0', b'1', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `timeslot`
--

CREATE TABLE IF NOT EXISTS `timeslot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateTime` datetime DEFAULT NULL,
  `maxNumVisitors` int(11) NOT NULL,
  `placesLeft` int(11) NOT NULL,
  `ap_id` bigint(20) DEFAULT NULL,
  `shAp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rnp686ppgscw263i0brxji4mq` (`ap_id`),
  KEY `FK_pbs7rxdyyb8c3uvyfw9nmlxya` (`shAp_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `timeslot`
--

INSERT INTO `timeslot` (`id`, `dateTime`, `maxNumVisitors`, `placesLeft`, `ap_id`, `shAp_id`) VALUES
(1, '2015-01-10 10:00:00', 5, 5, 2, NULL),
(2, '2015-01-11 09:45:00', 10, 10, 2, NULL),
(3, '2015-01-20 16:00:00', 20, 20, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `timeslot_user`
--

CREATE TABLE IF NOT EXISTS `timeslot_user` (
  `registeredTimeSlots_id` bigint(20) NOT NULL,
  `visitors_id` bigint(20) NOT NULL,
  KEY `FK_jq9hj39tkj97rvrtpp3vsydlj` (`visitors_id`),
  KEY `FK_1enygaetr9ay5qkpyefdpeju1` (`registeredTimeSlots_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `isAdmin` bit(1) DEFAULT NULL,
  `isNew` bit(1) DEFAULT NULL,
  `lastMainHit` datetime DEFAULT NULL,
  `picture` longblob,
  `address_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_25yqck53dyy0k1q261ncjxmw3` (`address_id`),
  KEY `FK_2mt2vvhqq9j7gcyotuy1j29oi` (`person_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `isAdmin`, `isNew`, `lastMainHit`, `picture`, `address_id`, `person_id`) VALUES
(1, 'michael.gruenig@students.unibe.ch', b'1', b'0', NULL, NULL, NULL, 1),
(2, 'sara.peeters@gmail.com', b'1', b'0', NULL, NULL, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `user_authorities`
--

CREATE TABLE IF NOT EXISTS `user_authorities` (
  `User_id` bigint(20) NOT NULL,
  `authorities` tinyblob,
  KEY `FK_6yei1bmvdwkqgfn4hw53bvqus` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_authorities`
--

INSERT INTO `user_authorities` (`User_id`, `authorities`) VALUES
(1, 0xaced00057372001f6f72672e73616d706c652e6d6f64656c2e5465616d38417574686f72697479b69951e08fda0bf40200014c0009617574686f726974797400124c6a6176612f6c616e672f537472696e673b7870740011524f4c455f504552534f4e415f55534552),
(2, 0xaced00057372001f6f72672e73616d706c652e6d6f64656c2e5465616d38417574686f72697479b69951e08fda0bf40200014c0009617574686f726974797400124c6a6176612f6c616e672f537472696e673b7870740011524f4c455f504552534f4e415f55534552);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartment`
--
ALTER TABLE `apartment`
  ADD CONSTRAINT `FK_8nf93ngilh83gs874x4fnm4m7` FOREIGN KEY (`tags_id`) REFERENCES `apartmenttags` (`id`),
  ADD CONSTRAINT `FK_ewnf2duijglxn8qn3h3tpp4it` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK_jl21a7rwsy78kay0ssrl7rfhg` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `bookmark`
--
ALTER TABLE `bookmark`
  ADD CONSTRAINT `FK_g84176hf1wlco7xu80lux0ox2` FOREIGN KEY (`shAp_id`) REFERENCES `shapartment` (`id`),
  ADD CONSTRAINT `FK_g1cm54tw64enk4a8tykfqgl6q` FOREIGN KEY (`bookMarker_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_r57suaxbkxfsdqjbytwjq11r6` FOREIGN KEY (`ap_id`) REFERENCES `apartment` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_5tmqapy93pqsx79pvd6hde0b2` FOREIGN KEY (`shAp_id`) REFERENCES `shapartment` (`id`),
  ADD CONSTRAINT `FK_g8bx8mm7c3r5wdu9bdjq21bdr` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_rku810o614p561fx8plnhymj1` FOREIGN KEY (`ap_id`) REFERENCES `apartment` (`id`),
  ADD CONSTRAINT `FK_tbto6hemu447oixxk730el2vx` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `roommate`
--
ALTER TABLE `roommate`
  ADD CONSTRAINT `FK_ikfei1qiw7qegt0hi0oyri86n` FOREIGN KEY (`shApartment_id`) REFERENCES `shapartment` (`id`),
  ADD CONSTRAINT `FK_bi2kx23bkshu368clqi9uhmwe` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK_rokbo9cfm7bag0krs096l4ej2` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);

--
-- Constraints for table `shapartment`
--
ALTER TABLE `shapartment`
  ADD CONSTRAINT `FK_pd390j3o6mei7of1i28a9few` FOREIGN KEY (`tags_id`) REFERENCES `shapartmenttags` (`id`),
  ADD CONSTRAINT `FK_hv8uhkdsbrdbfnh8w777e4aep` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_jfjp5uxlc6dmyeepeb5wqigfu` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `timeslot`
--
ALTER TABLE `timeslot`
  ADD CONSTRAINT `FK_pbs7rxdyyb8c3uvyfw9nmlxya` FOREIGN KEY (`shAp_id`) REFERENCES `shapartment` (`id`),
  ADD CONSTRAINT `FK_rnp686ppgscw263i0brxji4mq` FOREIGN KEY (`ap_id`) REFERENCES `apartment` (`id`);

--
-- Constraints for table `timeslot_user`
--
ALTER TABLE `timeslot_user`
  ADD CONSTRAINT `FK_1enygaetr9ay5qkpyefdpeju1` FOREIGN KEY (`registeredTimeSlots_id`) REFERENCES `timeslot` (`id`),
  ADD CONSTRAINT `FK_jq9hj39tkj97rvrtpp3vsydlj` FOREIGN KEY (`visitors_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_2mt2vvhqq9j7gcyotuy1j29oi` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK_25yqck53dyy0k1q261ncjxmw3` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD CONSTRAINT `FK_6yei1bmvdwkqgfn4hw53bvqus` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
