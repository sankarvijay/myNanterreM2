-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 03 mars 2020 à 18:54
-- Version du serveur :  10.2.30-MariaDB
-- Version de PHP :  7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `u749839367_m1`
--

-- --------------------------------------------------------

--
-- Structure de la table `batiments`
--

CREATE TABLE `batiments` (
  `idBatiment` int(11) NOT NULL,
  `nomBatiment` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `batiments`
--

INSERT INTO `batiments` (`idBatiment`, `nomBatiment`) VALUES
(1, 'VEIL (F)'),
(2, 'ALLAIS (G)'),
(3, 'DELBO (BSL)'),
(4, 'OMNISPORT (H)'),
(5, 'EPHEMERE1 (M)'),
(6, 'GYMNASE (I)'),
(7, 'RICOEUR (L)'),
(8, 'RESTO U'),
(9, 'BIBLIOTHÈQUE UNIVERSITAIRE'),
(10, 'MAISON DE L\'ETUDIANT (MDE)'),
(11, 'CENTRE SPORTIF '),
(12, 'ÉPHÉMÈRE 2 (N)'),
(13, 'MILLIAT (S)'),
(14, 'MAIER (V)'),
(15, 'TERRASSE CROUS'),
(16, 'WEBER (W)'),
(17, 'GINOUVES (MAE)'),
(18, 'REMOND (A)'),
(19, 'GRAPPIN (B)'),
(20, 'ZAZZO (C)'),
(21, 'LEFEBVRE (D)'),
(22, 'ROUCH (DD)'),
(23, 'RAMNOUX (E)');

-- --------------------------------------------------------

--
-- Structure de la table `bibliotheque`
--

CREATE TABLE `bibliotheque` (
  `id` int(11) NOT NULL,
  `nomSalle` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `bibliotheque`
--

INSERT INTO `bibliotheque` (`id`, `nomSalle`) VALUES
(1, 'Droit et Science politique'),
(2, 'Sciences économiques'),
(3, 'Sciences Humaines'),
(4, 'Langues et Littératures'),
(5, 'Sciences sociales');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_sport`
--

CREATE TABLE `categorie_sport` (
  `id_categorie` int(11) NOT NULL,
  `categorie` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categorie_sport`
--

INSERT INTO `categorie_sport` (`id_categorie`, `categorie`) VALUES
(1, 'Activités Aquatiques'),
(2, 'Activités Détente'),
(3, 'Education Corporelle de base et remise en forme'),
(4, 'Sports Collectifs'),
(5, 'Sports de Combats'),
(6, 'Sports de Raquettes'),
(7, 'Sports Individuels');

-- --------------------------------------------------------

--
-- Structure de la table `Crous`
--

CREATE TABLE `Crous` (
  `id_bat` int(11) NOT NULL,
  `batiment` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lieu` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `horaire` varchar(70) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ventes` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `frequentation` int(11) DEFAULT NULL,
  `ouverture` int(6) DEFAULT NULL,
  `fermeture` int(6) DEFAULT NULL,
  `burger` int(1) DEFAULT NULL,
  `vote` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Crous`
--

INSERT INTO `Crous` (`id_bat`, `batiment`, `lieu`, `horaire`, `ventes`, `frequentation`, `ouverture`, `fermeture`, `burger`, `vote`) VALUES
(1, 'La Croissanterie', 'RDC Batiment G    ', '8h30 - 15h', ' Wraps, sandwichs, paninis, viennoiserie, tartes, boissons chaudes, jus de fruits frais', 1, 83000, 150000, 1, 'Sois le premier à voter !'),
(3, 'La Terrasse', '8 allée de l\'université', '11h - 14h30', ' Spécialités asiatiques, grillades, tartes et salades', 1, 110000, 143000, 2, 'Sois le premier à voter !'),
(4, 'La B.U', 'RDC de la BU', '8h30 - 18h30 en semaine // 10h - 16h les samedis', 'Ciabatta chauds, sandwichs, viennoiserie, tartes, boissons chaudes', 1, 83000, 183000, 1, 'Sois le premier à voter !'),
(5, 'La sandwicherie', 'RDC du Batiment F', '8h30 - 18h en semaine', ' Sandwichs, paninis, viennoiserie, tartes, boissons chaudes, jus de fruits frais', 1, 83000, 220000, 1, 'Sois le premier à voter !'),
(6, 'L Asiatique', 'RDC du Batiment F', '8h - 15h en semaine', ' Pizza, soupes de légumes, pasta box, kebabs, salades composées, jus pressés', 1, 80000, 220000, 1, 'Sois le premier à voter !'),
(7, 'Les Food trucks', 'Allée devant le bâtiment DD', '11h30 - 14h en semaine', ' Burgers et frites de fabrication artisanale, kebabs, desserts maison', 1, 113000, 140000, 2, 'Sois le premier à voter !'),
(8, 'L AnK', 'RDC  \r\nBatiment DD', '8h - 17h en semaine ', ' Grandes salades, sandwichs, paninis, viennoiserie, tartes, boissons chaudes, jus pressé', 1, 80000, 170000, 1, 'Sois le premier à voter !'),
(9, 'Le Tex-Mex', '1er étage du RU', '11h30 - 14h30 en semaine ', ' Spécialités de tortillas, « Krous Fried Chicken »', 1, 113000, 220000, 2, 'Sois le premier à voter !'),
(10, 'L Espace Gourmand', 'RDC du RU', '11h45 - 14h en semaine ', ' Restaurant bistronomique', 2, 114500, 220000, 2, '15:16'),
(11, 'Le Millenium', 'RDC du RU', '8h - 21h en semaine ', ' Bagels, clubs, bruschetta, sandwiches, cheesecake, boissons gourmandes de l’après-midi', 1, 80000, 220000, 1, 'Sois le premier à voter !'),
(12, 'Le Restaurant Universitaire', '1 allée de l\'université ', '11h30-14h en semaine', 'Plats mijotés, poissons, grillades, pizzas, pâtes, saveurs du monde,', 1, 113000, 220000, 2, 'Sois le premier à voter !');

-- --------------------------------------------------------

--
-- Structure de la table `frequentation_bu`
--

CREATE TABLE `frequentation_bu` (
  `id` int(11) NOT NULL,
  `proportion` int(3) NOT NULL,
  `id_bu` int(11) NOT NULL,
  `heure` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `frequentation_bu`
--

INSERT INTO `frequentation_bu` (`id`, `proportion`, `id_bu`, `heure`) VALUES
(1, 30, 4, 15),
(2, 30, 4, 16),
(3, 30, 2, 18),
(4, 100, 3, 19),
(5, 100, 4, 18),
(6, 30, 3, 17),
(7, 100, 4, 16),
(8, 30, 4, 16),
(9, 100, 3, 12),
(10, 30, 1, 7),
(11, 60, 2, 8),
(12, 100, 1, 9),
(13, 100, 2, 10),
(14, 60, 4, 11),
(15, 30, 2, 14),
(16, 60, 2, 17),
(17, 60, 3, 16),
(18, 60, 3, 16),
(19, 60, 3, 17),
(20, 30, 1, 14),
(21, 30, 1, 13),
(22, 60, 1, 14),
(23, 100, 1, 17),
(24, 100, 1, 17),
(25, 60, 1, 14),
(26, 60, 2, 15),
(27, 100, 2, 15),
(28, 100, 2, 17),
(29, 100, 5, 18),
(30, 60, 4, 18),
(31, 30, 2, 21),
(32, 100, 1, 21),
(33, 100, 2, 21),
(34, 100, 4, 21),
(35, 0, 5, 21),
(36, 30, 2, 23),
(37, 30, 1, 11),
(39, 30, 3, 11),
(40, 10, 5, 10),
(41, 60, 1, 11),
(42, 85, 5, 19),
(43, 50, 5, 20),
(44, 40, 5, 11),
(45, 60, 5, 12),
(51, 60, 5, 13),
(52, 20, 5, 14),
(53, 30, 5, 15),
(54, 45, 5, 16),
(55, 75, 5, 17),
(56, 60, 5, 22),
(57, 100, 4, 22),
(58, 100, 5, 22),
(59, 30, 2, 22),
(60, 30, 1, 22),
(61, 30, 2, 22),
(62, 30, 1, 22),
(63, 100, 1, 22),
(64, 30, 1, 23),
(65, 30, 1, 23),
(66, 100, 1, 23),
(67, 30, 4, 23),
(68, 100, 5, 23),
(69, 30, 3, 23);

-- --------------------------------------------------------

--
-- Structure de la table `frequentation_cafet`
--

CREATE TABLE `frequentation_cafet` (
  `id` int(11) NOT NULL,
  `heure` int(2) NOT NULL,
  `proportion` int(3) NOT NULL,
  `id_cafet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `frequentation_cafet`
--

INSERT INTO `frequentation_cafet` (`id`, `heure`, `proportion`, `id_cafet`) VALUES
(11, 8, 15, 1),
(12, 9, 30, 1),
(13, 10, 75, 1),
(14, 11, 60, 1),
(15, 12, 100, 1),
(16, 13, 100, 1),
(17, 14, 40, 1),
(18, 15, 60, 1),
(19, 16, 30, 1),
(20, 8, 5, 3),
(21, 9, 10, 3),
(22, 10, 10, 3),
(23, 11, 15, 3),
(24, 12, 80, 3),
(25, 13, 100, 3),
(26, 14, 70, 3),
(27, 15, 20, 3),
(28, 16, 10, 3),
(29, 8, 20, 4),
(30, 9, 30, 4),
(31, 10, 35, 4),
(32, 11, 50, 4),
(33, 12, 100, 4),
(34, 13, 100, 4),
(35, 14, 60, 4),
(36, 15, 20, 4),
(37, 16, 20, 4),
(38, 8, 20, 5),
(39, 16, 15, 5),
(40, 15, 35, 5),
(41, 14, 40, 5),
(42, 13, 80, 5),
(43, 12, 100, 5),
(44, 11, 60, 5),
(45, 10, 80, 5),
(46, 9, 10, 5),
(47, 8, 0, 6),
(48, 16, 0, 6),
(49, 15, 0, 6),
(50, 14, 40, 6),
(51, 13, 90, 6),
(52, 12, 70, 6),
(53, 11, 30, 6),
(54, 10, 0, 6),
(55, 9, 0, 6),
(56, 8, 0, 7),
(57, 16, 0, 7),
(58, 15, 0, 7),
(59, 14, 30, 7),
(60, 13, 100, 7),
(61, 12, 90, 7),
(62, 11, 0, 7),
(63, 10, 0, 7),
(64, 9, 0, 7),
(65, 8, 5, 8),
(66, 16, 15, 8),
(67, 15, 40, 8),
(68, 14, 60, 8),
(69, 13, 80, 8),
(70, 12, 100, 8),
(71, 11, 60, 8),
(72, 10, 40, 8),
(73, 9, 10, 8),
(74, 8, 0, 9),
(75, 16, 0, 9),
(76, 15, 0, 9),
(77, 14, 40, 9),
(78, 13, 100, 9),
(79, 12, 100, 9),
(80, 11, 60, 9),
(81, 10, 0, 9),
(82, 9, 0, 9),
(83, 8, 0, 10),
(84, 16, 10, 10),
(85, 15, 20, 10),
(86, 14, 40, 10),
(87, 13, 70, 10),
(88, 12, 100, 10),
(89, 11, 60, 10),
(90, 10, 50, 10),
(91, 9, 20, 10),
(92, 8, 0, 11),
(93, 16, 10, 11),
(94, 15, 30, 11),
(95, 14, 40, 11),
(96, 13, 70, 11),
(97, 12, 100, 11),
(98, 11, 60, 11),
(99, 10, 60, 11),
(100, 9, 30, 11),
(101, 8, 0, 12),
(102, 16, 0, 12),
(103, 15, 0, 12),
(104, 14, 50, 12),
(105, 13, 100, 12),
(106, 12, 100, 12),
(107, 11, 60, 12),
(108, 10, 0, 12),
(109, 9, 0, 12);

-- --------------------------------------------------------

--
-- Structure de la table `plannification_sport`
--

CREATE TABLE `plannification_sport` (
  `id_rdv` int(11) NOT NULL,
  `heured` time DEFAULT NULL,
  `heuref` time DEFAULT NULL,
  `sport` varchar(150) DEFAULT NULL,
  `lieu` varchar(150) DEFAULT NULL,
  `numero` int(5) DEFAULT NULL,
  `dateRdv` varchar(10) NOT NULL,
  `categorie` int(11) DEFAULT NULL,
  `nbInscrit` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `plannification_sport`
--

INSERT INTO `plannification_sport` (`id_rdv`, `heured`, `heuref`, `sport`, `lieu`, `numero`, `dateRdv`, `categorie`, `nbInscrit`) VALUES
(77, '18:00:00', '19:15:00', 'Football', 'GYMNASE (I)', 1234, '2020-02-20', 4, 10),
(78, '16:00:00', '17:00:00', 'BasketBall', 'CENTRE SPORTIF ', 5487, '2020-02-24', 4, 3),
(79, '13:30:00', '14:30:00', 'Football', 'CENTRE SPORTIF ', 9870, '2020-02-25', 4, 1),
(80, '18:46:00', '18:46:00', 'Rugby', 'VEIL (F)', 11111, '2020-02-20', 4, 0);

-- --------------------------------------------------------

--
-- Structure de la table `sports`
--

CREATE TABLE `sports` (
  `id_sport` int(11) NOT NULL,
  `categorie` int(11) DEFAULT NULL,
  `sport` varchar(60) DEFAULT NULL,
  `horaire` varchar(1000) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sports`
--

INSERT INTO `sports` (`id_sport`, `categorie`, `sport`, `horaire`, `image`) VALUES
(1, 3, 'Fitness', 'Lundi  18h00-19h00  CSU   \r\nMardi  20h00-21h00 CSU ', 'fitness.png'),
(2, 3, 'Musculation', 'Mardi  11h00-12h00  CSU \r\nMardi  12h00-13h00  CSU \r\nMardi  16h00-17h30  CSU ', 'muscu.png'),
(3, 3, 'Salle Cardio', 'Du lundi au vendredi  08h00-21h00 CSU \r\nSamedi 09h00-12h30 CSU \r\n', 'salle_cardio.png'),
(4, 5, 'Kick Boxing', 'Lundi  11h-12h  Centre sportif  \r\nVendredi  11h-12h  Centre sportif', 'kickb.png'),
(5, 5, 'Boxe Anglaise', 'Lundi  18h30-19h30  Batiment H   \r\nMardi  12h-13h30  Batiment H  \r\nMercredi  18h30-19h30  Batiment H', 'boxea.png'),
(6, 5, 'Boxe Francaise', 'Lundi  17h30-18h30  Batiment H \r\nMardi  12h-13h  Batiment H   \r\nMercredi  17h30-18h30  Batiment H', 'boxea.png'),
(8, 2, 'Yoga', 'Mardi 18h00-19h00 DEB/CSU \r\nMardi 19h15-20h45 CONF/CSU \r\nVendredi 12h00-13h30 Reservé au Personnel/CSU', 'yoga.png'),
(9, 2, 'Taichi Qi Qong', 'Mercredi 17h30-19h00 CSU \n Vendredi 17h15-18h45 CSU', 'taichi.png'),
(10, 2, 'Relaxation', 'Mercredi 12h00-13h00 CSU ', 'relaxation.png'),
(11, 2, 'Méditation', 'Mercredi 17h00-18h30 CSU ', 'meditation.png'),
(12, 7, 'Athlétisme', 'Mardi 12h15-13h45 Piste \n Vendredi 12h15-13h45 Piste', 'athletisme.png'),
(13, 7, 'Escalade', 'Lundi 12h00-13h30 Bat H \r\n Mardi 18h00-19h30 Bat H \r\n Mercredi 12h00-14h00 Bat H/Reservé au \r\nPersonnel \r\n Mercredi 16h30-18h00 Bat H', 'escalade.png'),
(14, 7, 'Tir a l\'Arc', ' Mardi 12h00-13h30 CSU \n Jeudi 12h00-13h30 CSU ', 'tiralarc.png'),
(15, 6, 'Badminton', ' Lundi 12h00-13h30 CSU \n Lundi 17h30-19h00 CSU \n Mardi 18h00-19h30 Bat H 1X1 \n Mercredi 16h00-17h30 CSU \n Jeudi 19h00-20h30 Bat H 1X1 \n Vendredi 17h00-18h30 CSU', 'badminton.png'),
(16, 6, 'Tennis', ' Lundi 13h30-15h00 DEB-EXTERIEUR \n Mercredi 14h30-16h00 DEB-EXTERIEUR  ', 'tennis.png'),
(17, 6, 'Tennis de Table', ' Lundi 16h00-17h30 CSU', 'tennisdetable.png'),
(18, 4, 'Rugby', 'Lundi 18h30-20h30   Hommes -> Stade\r\n Lundi 19h00-20h30 Femmes -> Stade Jean Moulin 131 boulevard Washington 92150 Suresnes avec M.JOURDAN', 'rugby.png'),
(19, 4, 'Volley Ball', 'Lundi 18h00-20h00 PERF-BAT H \n Mercredi 18h00-19h30 BAT H \n Vendredi 18h30-20h00 CSU', 'volley.png'),
(20, 4, 'HandBall', 'Mardi 19h00-20h30 HOMMES-CSU \r\n Mercredi 18h00-19h30 FEMMES-CSU ', 'handball.png'),
(21, 4, 'Football', 'Mercredi 14h00-16h00 CSU  \n Jeudi 15h00-16h30 BatH-CSU \n Vendredi 15h30-17h00 CSU', 'futsal.png'),
(22, 4, 'BasketBall', 'Lundi 19h00-20h30 Mixte-CSU  \n Lundi 20h30-21h30 Compet-CSU \n Mardi 19h30-21h00 Débutant-BatH \n Mercredi 19h30-21h00 BatH \n Vendredi 20h00-21h30 CSU', 'basket.png'),
(23, 1, 'Aviron', 'Jeudi 13h00-15h00 Cercle Nautique de France Complexe sportif de l\'île du pont 92200 Neuilly sur Seine', 'aviron.png'),
(24, 1, 'Natation Competition', 'Jeudi 12h00-13h30 Piscine', 'natation.png'),
(25, 1, 'Natation Intermédiaire', 'Vendredi 12h50-13h30 Piscine', 'natation2.png'),
(26, 1, 'Plongée', 'Mardi 17h30-19h30 Piscine \r\nJeudi 12h00-13h30 Piscine/Rservé au Personnel', 'plongee.png'),
(27, 1, 'Natation Apprentissage', 'Lundi 12h50-13h30 Piscine \n Mardi 16h00-17h00 Piscine \n Mercredi 17h30-18h30 Piscine \n Vendredi 16h00-17h00 Piscine', 'natation3.jpg'),
(28, 1, 'Brevet national de sécurité et de sauvetage aquatique', 'Lundi 16h00-17h20 Piscine \n Mercredi 12h00-13h30 Piscine ', 'sauvetage.png'),
(29, 1, 'Natation Perfectionnement', 'Lundi 16h00-17h30 Piscine \n Mercredi 12h00-13h30 Piscine ', 'natation4.png'),
(30, 1, 'Aquagym', 'Mardi 12h10-12h50 Piscine \n Mardi 12h50-13h30 Piscine \n Vendredi 12h10-12h50 Piscine ', 'aquagym.jpg'),
(31, 1, 'Aquabike', 'Lundi 17h20-18h00 Piscine \n Mardi 17h10-17h50 Piscine \n Mardi 18h00-18h40 Piscine/Reservé au Personnel \n Mercredi 12h10-12h50 Piscine \n Mardi 12h50-13h30 Piscine ', 'aquabike.png'),
(32, 1, 'Circuit Training', 'Lundi 12h10-12h50 Piscine \r\nLundi 12h50-13h30 (libre) Piscine \r\nMercredi 17h10-17h50 Piscine \r\nJeudi 12h10-12h50 Piscine/Reservé au Personnel\r\nJeudi 12h50-13h30 Piscine \r\nVendredi 17h10-17h50 Piscine ', 'training.png');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE `vente` (
  `id_p` int(11) NOT NULL,
  `id_bat` int(6) DEFAULT NULL,
  `produit` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dispo` int(1) DEFAULT NULL,
  `vote` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `produitquantite` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id_p`, `id_bat`, `produit`, `dispo`, `vote`, `produitquantite`) VALUES
(11, 1, 'Sandwich', 1, 'Sois le premier à voter !', 10),
(12, 1, 'Paninis', 1, 'Sois le premier à voter !', 44),
(13, 1, 'Desserts', 1, 'Sois le premier à voter !', 0),
(14, 1, 'Viennoiseries', 1, 'Sois le premier à voter !', 0),
(15, 1, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(16, 1, 'Salades', 1, 'Sois le premier à voter !', 0),
(17, 5, 'Sandwich', 1, 'Sois le premier à voter !', 0),
(18, 5, 'Paninis', 1, 'Sois le premier à voter !', 12),
(19, 5, 'Desserts', 1, 'Sois le premier à voter !', 0),
(20, 5, 'Viennoiseries', 1, 'Sois le premier à voter !', 0),
(21, 5, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(22, 5, 'Salades', 2, '18:47', 0),
(23, 8, 'Sandwich', 1, 'Sois le premier à voter !', 0),
(24, 8, 'Paninis', 1, 'Sois le premier à voter !', 0),
(25, 8, 'Desserts', 1, 'Sois le premier à voter !', 0),
(26, 8, 'Viennoiseries', 1, 'Sois le premier à voter !', 0),
(27, 8, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(28, 8, 'Salades', 1, 'Sois le premier à voter !', 0),
(29, 11, 'Sandwich', 1, 'Sois le premier à voter !', 0),
(30, 11, 'Paninis', 1, 'Sois le premier à voter !', 0),
(31, 11, 'Desserts', 1, 'Sois le premier à voter !', 0),
(32, 11, 'Viennoiseries', 1, 'Sois le premier à voter !', 0),
(33, 11, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(34, 11, 'Salades', 1, 'Sois le premier à voter !', 0),
(35, 4, 'Sandwich', 1, 'Sois le premier à voter !', 10),
(36, 4, 'Paninis', 1, 'Sois le premier à voter !', 0),
(37, 4, 'Desserts', 1, 'Sois le premier à voter !', 2506),
(38, 4, 'Viennoiseries', 1, 'Sois le premier à voter !', 25),
(39, 4, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(40, 4, 'Salades', 1, 'Sois le premier à voter !', 5555),
(41, 6, 'Sandwich', 1, 'Sois le premier à voter !', 0),
(42, 6, 'Paninis', 1, 'Sois le premier à voter !', 0),
(43, 6, 'Desserts', 1, 'Sois le premier à voter !', 0),
(44, 6, 'Viennoiseries', 1, 'Sois le premier à voter !', 0),
(45, 6, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(46, 6, 'Salades', 1, 'Sois le premier à voter !', 0),
(55, 3, 'Sandwich', 1, 'Sois le premier à voter !', 0),
(56, 3, 'PaniniS', 1, 'Sois le premier à voter !', 107),
(57, 3, 'Desserts', 1, 'Sois le premier à voter !', 0),
(58, 3, 'Viennoisseries', 1, 'Sois le premier à voter !', 0),
(59, 3, 'Fruits frais', 1, 'Sois le premier à voter !', 0),
(60, 3, 'Salades', 1, 'Sois le premier à voter !', 25);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `batiments`
--
ALTER TABLE `batiments`
  ADD PRIMARY KEY (`idBatiment`);

--
-- Index pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categorie_sport`
--
ALTER TABLE `categorie_sport`
  ADD PRIMARY KEY (`id_categorie`);

--
-- Index pour la table `Crous`
--
ALTER TABLE `Crous`
  ADD PRIMARY KEY (`id_bat`);

--
-- Index pour la table `frequentation_bu`
--
ALTER TABLE `frequentation_bu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_bu` (`id_bu`);

--
-- Index pour la table `frequentation_cafet`
--
ALTER TABLE `frequentation_cafet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_frequentation_crous_batiment` (`id_cafet`);

--
-- Index pour la table `plannification_sport`
--
ALTER TABLE `plannification_sport`
  ADD PRIMARY KEY (`id_rdv`);

--
-- Index pour la table `sports`
--
ALTER TABLE `sports`
  ADD PRIMARY KEY (`id_sport`),
  ADD KEY `fk_categorie_sport` (`categorie`);

--
-- Index pour la table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`id_p`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `batiments`
--
ALTER TABLE `batiments`
  MODIFY `idBatiment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `bibliotheque`
--
ALTER TABLE `bibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `categorie_sport`
--
ALTER TABLE `categorie_sport`
  MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `Crous`
--
ALTER TABLE `Crous`
  MODIFY `id_bat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `frequentation_bu`
--
ALTER TABLE `frequentation_bu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT pour la table `frequentation_cafet`
--
ALTER TABLE `frequentation_cafet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT pour la table `plannification_sport`
--
ALTER TABLE `plannification_sport`
  MODIFY `id_rdv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT pour la table `sports`
--
ALTER TABLE `sports`
  MODIFY `id_sport` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `vente`
--
ALTER TABLE `vente`
  MODIFY `id_p` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `frequentation_bu`
--
ALTER TABLE `frequentation_bu`
  ADD CONSTRAINT `frequentation_bu_ibfk_1` FOREIGN KEY (`id_bu`) REFERENCES `bibliotheque` (`id`);

--
-- Contraintes pour la table `frequentation_cafet`
--
ALTER TABLE `frequentation_cafet`
  ADD CONSTRAINT `fk_frequentation_crous_batiment` FOREIGN KEY (`id_cafet`) REFERENCES `Crous` (`id_bat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
