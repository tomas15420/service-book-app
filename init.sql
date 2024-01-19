-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Počítač: 192.168.1.102
-- Vytvořeno: Pát 19. led 2024, 20:58
-- Verze serveru: 10.11.4-MariaDB-log
-- Verze PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `service-app`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `cars`
--

CREATE TABLE `cars` (
  `id` bigint(20) NOT NULL,
  `model` varchar(64) NOT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `spz` varchar(7) DEFAULT NULL,
  `carbrand_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `car_brands`
--

CREATE TABLE `car_brands` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Vypisuji data pro tabulku `car_brands`
--

INSERT INTO `car_brands` (`id`, `image`, `name`) VALUES
(1, 'abbott-detroit.png', 'Abbott-Detroit'),
(2, 'abt.png', 'ABT'),
(3, 'ac.png', 'AC'),
(4, 'abarth.png', 'Abarth'),
(5, 'abadal.png', 'Abadal'),
(6, 'acura.png', 'Acura'),
(7, 'aixam.png', 'Aixam'),
(8, 'alpine.png', 'Alpine'),
(9, 'alpina.png', 'Alpina'),
(10, 'alfa-romeo.png', 'Alfa Romeo'),
(11, 'alta.png', 'Alta'),
(12, 'alvis.png', 'Alvis'),
(13, 'amc.png', 'AMC'),
(14, 'apollo.png', 'Apollo'),
(15, 'arcfox.png', 'Arcfox'),
(16, 'arash.png', 'Arash'),
(17, 'aro.png', 'ARO'),
(18, 'ariel.png', 'Ariel'),
(19, 'arrival.png', 'Arrival'),
(20, 'arrinera.png', 'Arrinera'),
(21, 'ascari.png', 'Ascari'),
(22, 'artega.png', 'Artega'),
(23, 'askam.png', 'Askam'),
(24, 'aspark.png', 'Aspark'),
(25, 'aston-martin.png', 'Aston Martin'),
(26, 'atalanta.png', 'Atalanta'),
(27, 'auburn.png', 'Auburn'),
(28, 'audi-sport.png', 'Audi Sport'),
(29, 'audi.png', 'Audi'),
(30, 'austin.png', 'Austin'),
(31, 'autobianchi.png', 'Autobianchi'),
(32, 'autobacs.png', 'Autobacs'),
(33, 'bac.png', 'BAC'),
(34, 'axon.png', 'Axon'),
(35, 'baic-motor.png', 'BAIC Motor'),
(36, 'baojun.png', 'Baojun'),
(37, 'beiben.png', 'BeiBen'),
(38, 'berkeley.png', 'Berkeley'),
(39, 'bentley.png', 'Bentley'),
(40, 'berliet.png', 'Berliet'),
(41, 'bertone.png', 'Bertone'),
(42, 'bitter.png', 'Bitter'),
(43, 'bharatbenz.png', 'BharatBenz'),
(44, 'bestune.png', 'Bestune'),
(45, 'bmw.png', 'BMW'),
(46, 'bmw-m.png', 'BMW M'),
(47, 'bowler.png', 'Bowler'),
(48, 'borgward.png', 'Borgward'),
(49, 'bizzarrini.png', 'Bizzarrini'),
(50, 'brabus.png', 'Brabus'),
(51, 'brammo.png', 'Brammo'),
(52, 'brooke.png', 'Brooke'),
(53, 'brilliance.png', 'Brilliance'),
(54, 'bristol.png', 'Bristol'),
(55, 'bufori.png', 'Bufori'),
(56, 'buick.png', 'Buick'),
(57, 'bugatti.png', 'Bugatti'),
(58, 'byton.png', 'Byton'),
(59, 'byd.png', 'BYD'),
(60, 'canoo.png', 'Canoo'),
(61, 'cadillac.png', 'Cadillac'),
(62, 'caparo.png', 'Caparo'),
(63, 'carlsson.png', 'Carlsson'),
(64, 'camc.png', 'CAMC'),
(65, 'changan.png', 'Changan'),
(66, 'changfeng.png', 'Changfeng'),
(67, 'chery.png', 'Chery'),
(68, 'caterham.png', 'Caterham'),
(69, 'chevrolet-corvette.png', 'Chevrolet Corvette'),
(70, 'chevrolet.png', 'Chevrolet'),
(71, 'chrysler.png', 'Chrysler'),
(72, 'cisitalia.png', 'Cisitalia'),
(73, 'cizeta.png', 'Cizeta'),
(74, 'citroen.png', 'Citroën'),
(75, 'cole.png', 'Cole'),
(76, 'corre-la-licorne.png', 'Corre La Licorne'),
(77, 'daf.png', 'DAF'),
(78, 'daewoo.png', 'Daewoo'),
(79, 'dacia.png', 'Dacia'),
(80, 'daimler.png', 'Daimler'),
(81, 'daihatsu.png', 'Daihatsu'),
(82, 'david-brown.png', 'David Brown'),
(83, 'datsun.png', 'Datsun'),
(84, 'dartz.png', 'Dartz'),
(85, 'de-tomaso.png', 'De Tomaso'),
(86, 'dayun.png', 'Dayun'),
(87, 'delage.png', 'Delage'),
(88, 'devel-sixteen.png', 'Devel Sixteen'),
(89, 'detroit-electric.png', 'Detroit Electric'),
(90, 'diatto.png', 'Diatto'),
(91, 'dina.png', 'DINA'),
(92, 'desoto.png', 'DeSoto'),
(93, 'dkw.png', 'DKW'),
(94, 'dodge.png', 'Dodge'),
(95, 'dongfeng.png', 'Dongfeng'),
(96, 'dodge-viper.png', 'Dodge Viper'),
(97, 'drako.png', 'Drako'),
(98, 'donkervoort.png', 'Donkervoort'),
(99, 'ds.png', 'DS'),
(100, 'edag.png', 'EDAG'),
(101, 'eagle.png', 'Eagle'),
(102, 'eicher.png', 'Eicher'),
(103, 'edsel.png', 'Edsel'),
(104, 'duesenberg.png', 'Duesenberg'),
(105, 'elemental.png', 'Elemental'),
(106, 'elfin.png', 'Elfin'),
(107, 'elva.png', 'Elva'),
(108, 'eterniti.png', 'Eterniti'),
(109, 'erf.png', 'ERF'),
(110, 'englon.png', 'Englon'),
(111, 'exeed.png', 'Exeed'),
(112, '9ff.png', '9ff'),
(113, 'faraday-future.png', 'Faraday Future'),
(114, 'facel-vega.png', 'Facel Vega'),
(115, 'faw-jiefang.png', 'FAW Jiefang'),
(116, 'faw.png', 'FAW'),
(117, 'ferrari.png', 'Ferrari'),
(118, 'fioravanti.png', 'Fioravanti'),
(119, 'fiat.png', 'Fiat'),
(120, 'fisker.png', 'Fisker'),
(121, 'force-motors.png', 'Force Motors'),
(122, 'foden.png', 'Foden'),
(123, 'foton.png', 'Foton'),
(124, 'ford-mustang.png', 'Ford Mustang'),
(125, 'ford.png', 'Ford'),
(126, 'franklin.png', 'Franklin'),
(127, 'fpv.png', 'FPV'),
(128, 'freightliner.png', 'Freightliner'),
(129, 'fso.png', 'FSO'),
(130, 'gardner-douglas.png', 'Gardner Douglas'),
(131, 'gac-group.png', 'GAC Group'),
(132, 'gaz.png', 'GAZ'),
(133, 'geely.png', 'Geely'),
(134, 'geo.png', 'Geo'),
(135, 'genesis.png', 'Genesis'),
(136, 'geometry.png', 'Geometry'),
(137, 'gillet.png', 'Gillet'),
(138, 'gilbern.png', 'Gilbern'),
(139, 'gmc.png', 'GMC'),
(140, 'ginetta.png', 'Ginetta'),
(141, 'gonow.png', 'Gonow'),
(142, 'golden-dragon.png', 'Golden Dragon'),
(143, 'grinnall.png', 'Grinnall'),
(144, 'great-wall.png', 'Great Wall'),
(145, 'general-motors.png', 'General Motors'),
(146, 'gumpert.png', 'Gumpert'),
(147, 'hafei.png', 'Hafei'),
(148, 'haval.png', 'Haval'),
(149, 'haima.png', 'Haima'),
(150, 'hawtai.png', 'Hawtai'),
(151, 'hennessey.png', 'Hennessey'),
(152, 'hillman.png', 'Hillman'),
(153, 'higer.png', 'Higer'),
(154, 'hino.png', 'Hino'),
(155, 'hispano-suiza.png', 'Hispano-Suiza'),
(156, 'holden.png', 'Holden'),
(157, 'hommell.png', 'Hommell'),
(158, 'honda.png', 'Honda'),
(159, 'hongyan.png', 'Hongyan'),
(160, 'horch.png', 'Horch'),
(161, 'hongqi.png', 'Hongqi'),
(162, 'hsv.png', 'HSV'),
(163, 'hudson.png', 'Hudson'),
(164, 'hummer.png', 'Hummer'),
(165, 'hupmobile.png', 'Hupmobile'),
(166, 'hyundai.png', 'Hyundai'),
(167, 'ic-bus.png', 'IC Bus'),
(168, 'infiniti.png', 'Infiniti'),
(169, 'intermeccanica.png', 'Intermeccanica'),
(170, 'innocenti.png', 'Innocenti'),
(171, 'ih.png', 'IH'),
(172, 'international.png', 'International'),
(173, 'ikco.png', 'IKCO'),
(174, 'irizar.png', 'Irizar'),
(175, 'isdera.png', 'Isdera'),
(176, 'iso.png', 'Iso'),
(177, 'isuzu.png', 'Isuzu'),
(178, 'jac.png', 'JAC'),
(179, 'jaguar.png', 'Jaguar'),
(180, 'iveco.png', 'Iveco'),
(181, 'jawa.png', 'Jawa'),
(182, 'jba-motors.png', 'JBA Motors'),
(183, 'jeep.png', 'Jeep'),
(184, 'jetta.png', 'Jetta'),
(185, 'jensen.png', 'Jensen'),
(186, 'jmc.png', 'JMC'),
(187, 'kaiser.png', 'Kaiser'),
(188, 'kamaz.png', 'Kamaz'),
(189, 'karma.png', 'Karma'),
(190, 'karlmann-king.png', 'Karlmann King'),
(191, 'keating.png', 'Keating'),
(192, 'kenworth.png', 'Kenworth'),
(193, 'king-long.png', 'King Long'),
(194, 'kia.png', 'Kia'),
(195, 'koenigsegg.png', 'Koenigsegg'),
(196, 'ktm.png', 'KTM'),
(197, 'lada.png', 'Lada'),
(198, 'lagonda.png', 'Lagonda'),
(199, 'lamborghini.png', 'Lamborghini'),
(200, 'lancia.png', 'Lancia'),
(201, 'land-rover.png', 'Land Rover'),
(202, 'landwind.png', 'Landwind'),
(203, 'laraki.png', 'Laraki'),
(204, 'lexus.png', 'Lexus'),
(205, 'li-auto.png', 'Li Auto'),
(206, 'leyland.png', 'Leyland'),
(207, 'lifan.png', 'Lifan'),
(208, 'ligier.png', 'Ligier'),
(209, 'lincoln.png', 'Lincoln'),
(210, 'lister.png', 'Lister'),
(211, 'lloyd.png', 'Lloyd'),
(212, 'lobini.png', 'Lobini'),
(213, 'levc.png', 'LEVC'),
(214, 'lordstown.png', 'Lordstown'),
(215, 'lucid.png', 'Lucid'),
(216, 'luxgen.png', 'Luxgen'),
(217, 'lotus.png', 'Lotus'),
(218, 'lynk-and-co.png', 'Lynk & Co'),
(219, 'mack.png', 'Mack'),
(220, 'mahindra.png', 'Mahindra'),
(221, 'mansory.png', 'Mansory'),
(222, 'man.png', 'MAN'),
(223, 'marcos.png', 'Marcos'),
(224, 'marlin.png', 'Marlin'),
(225, 'maserati.png', 'Maserati'),
(226, 'mastretta.png', 'Mastretta'),
(227, 'maxus.png', 'Maxus'),
(228, 'maybach.png', 'Maybach'),
(229, 'maz.png', 'MAZ'),
(230, 'mazda.png', 'Mazda'),
(231, 'mclaren.png', 'McLaren'),
(232, 'melkus.png', 'Melkus'),
(233, 'mercedes-amg.png', 'Mercedes-AMG'),
(234, 'mazzanti.png', 'Mazzanti'),
(235, 'mercedes-benz.png', 'Mercedes-Benz'),
(236, 'mercury.png', 'Mercury'),
(237, 'mev.png', 'MEV'),
(238, 'merkur.png', 'Merkur'),
(239, 'mg.png', 'MG'),
(240, 'microcar.png', 'Microcar'),
(241, 'mini.png', 'Mini'),
(242, 'mitsubishi.png', 'Mitsubishi'),
(243, 'mitsuoka.png', 'Mitsuoka'),
(244, 'mk.png', 'MK'),
(245, 'morgan.png', 'Morgan'),
(246, 'morris.png', 'Morris'),
(247, 'mosler.png', 'Mosler'),
(248, 'navistar.png', 'Navistar'),
(249, 'nevs.png', 'NEVS'),
(250, 'nio.png', 'NIO'),
(251, 'nikola.png', 'Nikola'),
(252, 'nissan-nismo.png', 'Nissan Nismo'),
(253, 'nissan.png', 'Nissan'),
(254, 'nissan-gt-r.png', 'Nissan GT-R'),
(255, 'oldsmobile.png', 'Oldsmobile'),
(256, 'oltcit.png', 'Oltcit'),
(257, 'noble.png', 'Noble'),
(258, 'osca.png', 'OSCA'),
(259, 'opel.png', 'Opel'),
(260, 'paccar.png', 'Paccar'),
(261, 'pagani.png', 'Pagani'),
(262, 'panhard.png', 'Panhard'),
(263, 'panoz.png', 'Panoz'),
(264, 'packard.png', 'Packard'),
(265, 'pegaso.png', 'Pegaso'),
(266, 'perodua.png', 'Perodua'),
(267, 'peterbilt.png', 'Peterbilt'),
(268, 'peugeot.png', 'Peugeot'),
(269, 'pgo.png', 'PGO'),
(270, 'pierce-arrow.png', 'Pierce-Arrow'),
(271, 'pininfarina.png', 'Pininfarina'),
(272, 'polestar.png', 'Polestar'),
(273, 'plymouth.png', 'Plymouth'),
(274, 'pontiac.png', 'Pontiac'),
(275, 'porsche.png', 'Porsche'),
(276, 'premier.png', 'Premier'),
(277, 'praga.png', 'Praga'),
(278, 'prodrive.png', 'Prodrive'),
(279, 'proton.png', 'Proton'),
(280, 'qoros.png', 'Qoros'),
(281, 'radical.png', 'Radical'),
(282, 'ram.png', 'RAM'),
(283, 'rambler.png', 'Rambler'),
(284, 'ranz.png', 'Ranz'),
(285, 'renault.png', 'Renault'),
(286, 'renault-samsung.png', 'Renault Samsung'),
(287, 'rezvani.png', 'Rezvani'),
(288, 'riley.png', 'Riley'),
(289, 'rimac.png', 'Rimac'),
(290, 'rinspeed.png', 'Rinspeed'),
(291, 'rivian.png', 'Rivian'),
(292, 'rolls-royce.png', 'Rolls-Royce'),
(293, 'roewe.png', 'Roewe'),
(294, 'ronart.png', 'Ronart'),
(295, 'rossion.png', 'Rossion'),
(296, 'rover.png', 'Rover'),
(297, 'ruf.png', 'RUF'),
(298, 'saab.png', 'Saab'),
(299, 'saipa.png', 'Saipa'),
(300, 'saic-motor.png', 'SAIC Motor'),
(301, 'saleen.png', 'Saleen'),
(302, 'saturn.png', 'Saturn'),
(303, 'scania.png', 'Scania'),
(304, 'scion.png', 'Scion'),
(305, 'seat.png', 'SEAT'),
(306, 'setra.png', 'Setra'),
(307, 'shacman.png', 'Shacman'),
(308, 'simca.png', 'Simca'),
(309, 'singer.png', 'Singer'),
(310, 'singulato.png', 'Singulato'),
(311, 'sisu.png', 'Sisu'),
(312, 'sinotruk.png', 'Sinotruk'),
(313, 'skoda.png', 'Škoda'),
(314, 'smart.png', 'Smart'),
(315, 'soueast.png', 'Soueast'),
(316, 'spania-gta.png', 'Spania GTA'),
(317, 'spirra.png', 'Spirra'),
(318, 'spyker.png', 'Spyker'),
(319, 'ssangyong.png', 'SsangYong'),
(320, 'sterling.png', 'Sterling'),
(321, 'ssc.png', 'SSC'),
(322, 'studebaker.png', 'Studebaker'),
(323, 'subaru.png', 'Subaru'),
(324, 'suzuki.png', 'Suzuki'),
(325, 'suffolk.png', 'Suffolk'),
(326, 'stutz.png', 'Stutz'),
(327, 'talbot.png', 'Talbot'),
(328, 'tata.png', 'Tata'),
(329, 'tesla.png', 'Tesla'),
(330, 'tatra.png', 'Tatra'),
(331, 'techart.png', 'TechArt'),
(332, 'toyota-alphard.png', 'Toyota Alphard'),
(333, 'toyota.png', 'Toyota'),
(334, 'toyota-crown.png', 'Toyota Crown'),
(335, 'tramontana.png', 'Tramontana'),
(336, 'toyota-century.png', 'Toyota Century'),
(337, 'trion.png', 'Trion'),
(338, 'troller.png', 'Troller'),
(339, 'triumph.png', 'Triumph'),
(340, 'tvr.png', 'TVR'),
(341, 'uaz.png', 'UAZ'),
(342, 'tucker.png', 'Tucker'),
(343, 'ultima.png', 'Ultima'),
(344, 'ud.png', 'UD'),
(345, 'vandenbrink.png', 'Vandenbrink'),
(346, 'vauxhall.png', 'Vauxhall'),
(347, 'vector.png', 'Vector'),
(348, 'venturi.png', 'Venturi'),
(349, 'vencer.png', 'Vencer'),
(350, 'venucia.png', 'Venucia'),
(351, 'volkswagen.png', 'Volkswagen'),
(352, 'vinfast.png', 'VinFast'),
(353, 'volvo.png', 'Volvo'),
(354, 'wanderer.png', 'Wanderer'),
(355, 'w-motors.png', 'W Motors'),
(356, 'wartburg.png', 'Wartburg'),
(357, 'weltmeister.png', 'Weltmeister'),
(358, 'wey.png', 'WEY'),
(359, 'western-star.png', 'Western Star'),
(360, 'westfield.png', 'Westfield'),
(361, 'willys-overland.png', 'Willys-Overland'),
(362, 'wiesmann.png', 'Wiesmann'),
(363, 'workhorse.png', 'Workhorse'),
(364, 'xpeng.png', 'XPeng'),
(365, 'wuling.png', 'Wuling'),
(366, 'yulon.png', 'Yulon'),
(367, 'zastava.png', 'Zastava'),
(368, 'yutong.png', 'Yutong'),
(369, 'zaz.png', 'ZAZ'),
(370, 'zenos.png', 'Zenos'),
(371, 'zhongtong.png', 'Zhongtong'),
(372, 'zenvo.png', 'Zenvo'),
(373, 'zotye.png', 'Zotye'),
(374, 'zinoro.png', 'Zinoro');

-- --------------------------------------------------------

--
-- Struktura tabulky `operations`
--

CREATE TABLE `operations` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `service-records`
--

CREATE TABLE `service-records` (
  `id` bigint(20) NOT NULL,
  `cost` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `car_id` bigint(20) NOT NULL,
  `operation_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabulky `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg5byi70smy6o0fdh4f7df7olr` (`carbrand_id`),
  ADD KEY `FKqw4c8e6nqrvy3ti1xj8w8wyc9` (`user_id`);

--
-- Indexy pro tabulku `car_brands`
--
ALTER TABLE `car_brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pro tabulku `operations`
--
ALTER TABLE `operations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6s2v80e9ppot2sb1bba5w6fx3` (`user_id`);

--
-- Indexy pro tabulku `service-records`
--
ALTER TABLE `service-records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtj7pe57nv5204nqka10jlnlo5` (`car_id`),
  ADD KEY `FK9uof8ag25c8x62log9xdayanr` (`operation_id`);

--
-- Indexy pro tabulku `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `cars`
--
ALTER TABLE `cars`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pro tabulku `car_brands`
--
ALTER TABLE `car_brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=375;

--
-- AUTO_INCREMENT pro tabulku `operations`
--
ALTER TABLE `operations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pro tabulku `service-records`
--
ALTER TABLE `service-records`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pro tabulku `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `FKg5byi70smy6o0fdh4f7df7olr` FOREIGN KEY (`carbrand_id`) REFERENCES `car_brands` (`id`),
  ADD CONSTRAINT `FKqw4c8e6nqrvy3ti1xj8w8wyc9` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Omezení pro tabulku `operations`
--
ALTER TABLE `operations`
  ADD CONSTRAINT `FK6s2v80e9ppot2sb1bba5w6fx3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Omezení pro tabulku `service-records`
--
ALTER TABLE `service-records`
  ADD CONSTRAINT `FK9uof8ag25c8x62log9xdayanr` FOREIGN KEY (`operation_id`) REFERENCES `operations` (`id`),
  ADD CONSTRAINT `FKtj7pe57nv5204nqka10jlnlo5` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
