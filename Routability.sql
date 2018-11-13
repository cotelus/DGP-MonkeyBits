-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2018 a las 14:47:27
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `routability`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `accesibility`
--

CREATE TABLE `accesibility` (
  `IdPlace` int(8) NOT NULL,
  `RedMovility` tinyint(1) NOT NULL,
  `RedVision` tinyint(1) NOT NULL,
  `Foreigner` tinyint(1) NOT NULL,
  `Deaf` tinyint(1) NOT NULL,
  `ColourBlind` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adminuser`
--

CREATE TABLE `adminuser` (
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appear`
--

CREATE TABLE `appear` (
  `IdPlace` int(8) NOT NULL,
  `IdRoute` int(8) NOT NULL,
  `Sequence` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `commonuser`
--

CREATE TABLE `commonuser` (
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Banned` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `commonuser`
--

INSERT INTO `commonuser` (`Email`, `Banned`) VALUES
('usuario1@gmail.com', 1),
('usuariobloqueado@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritesplaces`
--

CREATE TABLE `favoritesplaces` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritesroutes`
--

CREATE TABLE `favoritesroutes` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `placecomments`
--

CREATE TABLE `placecomments` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Content` text COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `routecomments`
--

CREATE TABLE `routecomments` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Content` text COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suggestedplace`
--

CREATE TABLE `suggestedplace` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedplace`
--

INSERT INTO `suggestedplace` (`IdPlace`, `Email`, `MadeBy`, `Description`, `Localitation`, `Name`, `Image`) VALUES
(2, 'paquito@hotmail.com', 'admin@gmail.com', '', '', '', ''),
(4, 'paquito@hotmail.com', 'admin@gmail.com', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suggestedroute`
--

CREATE TABLE `suggestedroute` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedroute`
--

INSERT INTO `suggestedroute` (`IdRoute`, `Email`, `MadeBy`, `Name`, `Description`, `Image`) VALUES
(6, 'paquito@hotmail.com', 'admin@gmail.com', '', '', ''),
(10, 'madredepaquito@gmail.com', 'admin@gmail.com', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `verifiedplace`
--

CREATE TABLE `verifiedplace` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `verifiedroute`
--

CREATE TABLE `verifiedroute` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visit`
--

CREATE TABLE `visit` (
  `IdRoute` int(8) NOT NULL,
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `accesibility`
--
ALTER TABLE `accesibility`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `adminuser`
--
ALTER TABLE `adminuser`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `appear`
--
ALTER TABLE `appear`
  ADD PRIMARY KEY (`IdPlace`,`IdRoute`),
  ADD KEY `IdRoute` (`IdRoute`);

--
-- Indices de la tabla `commonuser`
--
ALTER TABLE `commonuser`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `favoritesplaces`
--
ALTER TABLE `favoritesplaces`
  ADD PRIMARY KEY (`IdPlace`,`Email`),
  ADD KEY `Email` (`Email`);

--
-- Indices de la tabla `favoritesroutes`
--
ALTER TABLE `favoritesroutes`
  ADD PRIMARY KEY (`IdRoute`,`Email`),
  ADD KEY `Email` (`Email`);

--
-- Indices de la tabla `placecomments`
--
ALTER TABLE `placecomments`
  ADD PRIMARY KEY (`IdPlace`,`Email`,`Date`,`Time`),
  ADD KEY `Email` (`Email`);

--
-- Indices de la tabla `routecomments`
--
ALTER TABLE `routecomments`
  ADD PRIMARY KEY (`IdRoute`,`Email`,`Date`,`Time`),
  ADD KEY `Email` (`Email`);

--
-- Indices de la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `verifiedplace`
--
ALTER TABLE `verifiedplace`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `verifiedroute`
--
ALTER TABLE `verifiedroute`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`IdRoute`,`IdPlace`,`Email`),
  ADD KEY `IdPlace` (`IdPlace`),
  ADD KEY `Email` (`Email`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `accesibility`
--
ALTER TABLE `accesibility`
  ADD CONSTRAINT `accesibility_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `verifiedplace` (`IdPlace`);

--
-- Filtros para la tabla `appear`
--
ALTER TABLE `appear`
  ADD CONSTRAINT `appear_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `verifiedplace` (`IdPlace`),
  ADD CONSTRAINT `appear_ibfk_2` FOREIGN KEY (`IdPlace`) REFERENCES `suggestedplace` (`IdPlace`),
  ADD CONSTRAINT `appear_ibfk_3` FOREIGN KEY (`IdRoute`) REFERENCES `suggestedroute` (`IdRoute`),
  ADD CONSTRAINT `appear_ibfk_4` FOREIGN KEY (`IdRoute`) REFERENCES `verifiedroute` (`IdRoute`);

--
-- Filtros para la tabla `favoritesplaces`
--
ALTER TABLE `favoritesplaces`
  ADD CONSTRAINT `favoritesplaces_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `commonuser` (`Email`),
  ADD CONSTRAINT `favoritesplaces_ibfk_2` FOREIGN KEY (`IdPlace`) REFERENCES `verifiedplace` (`IdPlace`);

--
-- Filtros para la tabla `favoritesroutes`
--
ALTER TABLE `favoritesroutes`
  ADD CONSTRAINT `favoritesroutes_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `commonuser` (`Email`),
  ADD CONSTRAINT `favoritesroutes_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `verifiedroute` (`IdRoute`);

--
-- Filtros para la tabla `placecomments`
--
ALTER TABLE `placecomments`
  ADD CONSTRAINT `placecomments_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `commonuser` (`Email`),
  ADD CONSTRAINT `placecomments_ibfk_2` FOREIGN KEY (`IdPlace`) REFERENCES `verifiedplace` (`IdPlace`);

--
-- Filtros para la tabla `routecomments`
--
ALTER TABLE `routecomments`
  ADD CONSTRAINT `routecomments_ibfk_1` FOREIGN KEY (`IdRoute`) REFERENCES `verifiedroute` (`IdRoute`),
  ADD CONSTRAINT `routecomments_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `commonuser` (`Email`);

--
-- Filtros para la tabla `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `visit_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `verifiedplace` (`IdPlace`),
  ADD CONSTRAINT `visit_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `verifiedroute` (`IdRoute`),
  ADD CONSTRAINT `visit_ibfk_3` FOREIGN KEY (`Email`) REFERENCES `commonuser` (`Email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
