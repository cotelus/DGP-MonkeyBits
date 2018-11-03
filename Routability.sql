-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 03-11-2018 a las 17:18:06
-- Versión del servidor: 5.7.24-0ubuntu0.16.04.1
-- Versión de PHP: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Routability`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Accesibility`
--

CREATE TABLE `Accesibility` (
  `IdPlace` int(8) NOT NULL,
  `RedMovility` tinyint(1) NOT NULL,
  `RedVision` tinyint(1) NOT NULL,
  `Foreigner` tinyint(1) NOT NULL,
  `Deaf` tinyint(1) NOT NULL,
  `ColourBlind` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `AdminUser`
--

CREATE TABLE `AdminUser` (
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Appear`
--

CREATE TABLE `Appear` (
  `IdPlace` int(8) NOT NULL,
  `IdRoute` int(8) NOT NULL,
  `Sequence` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CommonUser`
--

CREATE TABLE `CommonUser` (
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CreateAdmin`
--

CREATE TABLE `CreateAdmin` (
  `EmailAdmin` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `EmailManager` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ManagePlace`
--

CREATE TABLE `ManagePlace` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ManageRoute`
--

CREATE TABLE `ManageRoute` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ManagerUser`
--

CREATE TABLE `ManagerUser` (
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Place`
--

CREATE TABLE `Place` (
  `IdPlace` int(8) NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Route`
--

CREATE TABLE `Route` (
  `IdRoute` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SuggestedPlace`
--

CREATE TABLE `SuggestedPlace` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SuggestedRoute`
--

CREATE TABLE `SuggestedRoute` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `User`
--

CREATE TABLE `User` (
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `Password` varchar(16) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(12) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `VerifiedPlace`
--

CREATE TABLE `VerifiedPlace` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `VerifiedRoute`
--

CREATE TABLE `VerifiedRoute` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Visit`
--

CREATE TABLE `Visit` (
  `IdRoute` int(8) NOT NULL,
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Rating` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Accesibility`
--
ALTER TABLE `Accesibility`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `AdminUser`
--
ALTER TABLE `AdminUser`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `Appear`
--
ALTER TABLE `Appear`
  ADD PRIMARY KEY (`IdPlace`,`IdRoute`);

--
-- Indices de la tabla `CommonUser`
--
ALTER TABLE `CommonUser`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `CreateAdmin`
--
ALTER TABLE `CreateAdmin`
  ADD PRIMARY KEY (`EmailAdmin`);

--
-- Indices de la tabla `ManagePlace`
--
ALTER TABLE `ManagePlace`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `ManageRoute`
--
ALTER TABLE `ManageRoute`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `ManagerUser`
--
ALTER TABLE `ManagerUser`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `Place`
--
ALTER TABLE `Place`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `Route`
--
ALTER TABLE `Route`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `SuggestedPlace`
--
ALTER TABLE `SuggestedPlace`
  ADD PRIMARY KEY (`IdPlace`);

--
-- Indices de la tabla `SuggestedRoute`
--
ALTER TABLE `SuggestedRoute`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `VerifiedPlace`
--
ALTER TABLE `VerifiedPlace`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `VerifiedRoute`
--
ALTER TABLE `VerifiedRoute`
  ADD PRIMARY KEY (`IdRoute`);

--
-- Indices de la tabla `Visit`
--
ALTER TABLE `Visit`
  ADD PRIMARY KEY (`IdRoute`,`IdPlace`,`Email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
