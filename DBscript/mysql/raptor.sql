-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Ven 20 Juillet 2012 à 14:30
-- Version du serveur: 5.5.24
-- Version de PHP: 5.3.10-1ubuntu3.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `raptor`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `link` text NOT NULL,
  `date_execution` date NOT NULL,
  `taskid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;


-- --------------------------------------------------------

--
-- Structure de la table `condition`
--

CREATE TABLE IF NOT EXISTS `condition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `value` text NOT NULL,
  `positionType` varchar(255) NOT NULL,
  `positionRegex` varchar(255) NOT NULL,
  `signe` varchar(50) NOT NULL,
  `taskid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-----------------------------------------------------

--
-- Structure de la table `scenario`
--

CREATE TABLE IF NOT EXISTS `scenario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `date_creation` date NOT NULL,
  `date_last_execution` date DEFAULT NULL,
  `execution_min` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  `classe` varchar(255) NOT NULL,
  `taskid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


-- --------------------------------------------------------

--
-- Structure de la table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `type` varchar(50) NOT NULL,
  `position` int(11) NOT NULL,
  `input_content` varchar(50) DEFAULT NULL,
  `output_content` varchar(50) DEFAULT NULL,
  `scenarioid` int(11) NOT NULL,
  `crawl_link` text NOT NULL,
  `crawl_multipages` int(11) DEFAULT NULL COMMENT 'condition id',
  `sender_email_emails` text NOT NULL,
  `sender_email_object` varchar(255) NOT NULL,
  `sender_email_content` text NOT NULL,
  `sender_blog_link` varchar(255) DEFAULT NULL,
  `sender_blog_login` varchar(255) NOT NULL,
  `sender_blog_pass` varchar(255) NOT NULL,
  `sender_blog_email` varchar(255) NOT NULL,
  `sender_keep_original_content` int(11) NOT NULL COMMENT 'Add to the sender the original content too (if it was modified as translation or else)',
  `sender_add_source` int(11) NOT NULL COMMENT 'add source link to the sender',
  `action_translate_language_original` varchar(5) NOT NULL COMMENT ' fr(french),en(english),de(german),af(afrikaans),sp(spanish),it(italian),sw(swedish),nl(dutch),hi(hindi),no(norwegian),da(danish)',
  `action_translate_language_needed` varchar(5) NOT NULL COMMENT ' fr(french),en(english),de(german),af(afrikaans),sp(spanish),it(italian),sw(swedish),nl(dutch),hi(hindi),no(norwegian),da(danish)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `active` int(11) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
