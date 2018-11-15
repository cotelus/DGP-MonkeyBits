-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-11-2018 a las 10:49:07
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.3

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

--
-- Volcado de datos para la tabla `accesibility`
--

INSERT INTO `accesibility` (`IdPlace`, `RedMovility`, `RedVision`, `Foreigner`, `Deaf`, `ColourBlind`) VALUES
(1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adminuser`
--

CREATE TABLE `adminuser` (
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `adminuser`
--

INSERT INTO `adminuser` (`Email`) VALUES
('admin1@gmail.com'),
('admin2@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appearsuggested`
--

CREATE TABLE `appearsuggested` (
  `IdPlace` int(4) NOT NULL,
  `IdRoute` int(4) NOT NULL,
  `Sequence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `appearsuggested`
--

INSERT INTO `appearsuggested` (`IdPlace`, `IdRoute`, `Sequence`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appearverified`
--

CREATE TABLE `appearverified` (
  `IdPlace` int(8) NOT NULL,
  `IdRoute` int(8) NOT NULL,
  `Sequence` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `appearverified`
--

INSERT INTO `appearverified` (`IdPlace`, `IdRoute`, `Sequence`) VALUES
(2, 1, 2),
(11, 1250, 1),
(12, 1250, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritesplaces`
--

CREATE TABLE `favoritesplaces` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `favoritesplaces`
--

INSERT INTO `favoritesplaces` (`IdPlace`, `Email`) VALUES
(1111, 'usuario1@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoritesroutes`
--

CREATE TABLE `favoritesroutes` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `favoritesroutes`
--

INSERT INTO `favoritesroutes` (`IdRoute`, `Email`) VALUES
(1, 'usuario1@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `place`
--

CREATE TABLE `place` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `place`
--

INSERT INTO `place` (`IdPlace`, `Email`, `MadeBy`, `Name`, `Description`, `Localitation`, `Image`) VALUES
(1, 'admin1@gmail.com', 'usuario1@gmail.com', 'Lugar bonito', 'fasefas', 'asefas', 'afsesa'),
(2, 'admin2@hotmail.com', 'usuario1@gmail.com', 'asfas', 'asfeasef', 'afsesa', 'afsef'),
(9, 'admin1@gmail.com', 'usuario1@gmail.com', 'holaaa', 'asfeeasf', 'afsefsa', 'afses'),
(10, 'admin1@gmail.com', 'usuario1@gmail.com', 'holaa', 'fasfees', 'fasefasef', 'afsefas'),
(11, 'dgp.monkeybits@gmail.com', NULL, 'Parque Almunia', 'Tiene una distribución triangular rodeado por una valla y muro, además de existir distintas pantallas vegetales en cada uno de los lados: ciprés, pruno, espino de fuego y laurel. Se desarrolla a partir de diversos paseos que se complementan y relacionan a lo largo de todo su recorrido, delimitando numerosas zonas de estancia con elementos característicos como el quiosco de música o la fuente con pérgola de glicinas en su parte posterior. Uno de los elementos principales del parque es la acequia, atravesada por puentecillos de piedra que dan paso a cuadros de aromáticas, hacia un lado, y a una paseo de arces, al otro.', 'Delimitado por los viales Avda. de Andalucía, Calle Periodista Eugenio Selles y Calle Periodista Jos', 'http://www.besarsengranada.es/wp-content/uploads/2015/05/besarse-en-granada-flores-parque-almunia-5_21390.jpg'),
(12, 'dgp.monkeybits@gmail.com', NULL, 'Parque Federico García Lorca', 'El parque está constituido por diversos paseos , destacando dos avenidas: el paseo de la alameda , el cual se desarrolla paralelamente al río y se dirige al estanque , y el paseo de tilos, desde la entrada principal hasta las zonas de servicios Delimitados por estos paseos y caminos, existen diversos sectores que terminan de configurar este parque: los jardines neoplasticistas, las acequias, la fuente cibernética, el bosque de ribera, la rosaleda y las huertas', 'Entre las calles Arabial , Virgen Blanca y el Camino de Purchil, el Parque García Lorca, con cuatro ', 'https://www.conmishijos.com/assets/planes/4000/4120-parque-federico-garcia-lorca-granada.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `placecomments`
--

CREATE TABLE `placecomments` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Content` varchar(130) COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route`
--

CREATE TABLE `route` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `route`
--

INSERT INTO `route` (`IdRoute`, `Email`, `MadeBy`, `Name`, `Description`, `Image`) VALUES
(1, 'admin1@gmail.com', 'usuario1@gmail.com', 'fasefas', 'fasefasef', 'asfasefa'),
(2, 'admin2@hotmail.com', 'usuariobloqueado@gmail.com', 'Ruta interesante', 'asfasef', 'asfeasef'),
(1250, 'dgp.monkeybits@gmail.com', NULL, 'Parques y Jardines', 'Una ruta por los mejores parques y jardines de Granada, pudiendo disfrutar de las vistas de la arquitectura y flora que en estos hay.', 'https://guiasgranada.com/advisor/wp-content/uploads/2017/06/garcia1.jpg');

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

--
-- Volcado de datos para la tabla `routecomments`
--

INSERT INTO `routecomments` (`IdRoute`, `Email`, `Content`, `Date`, `Time`) VALUES
(2, 'usuariobloqueado@gmail.com', 'fasfeasef', '2018-11-14', '20:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suggestedplace`
--

CREATE TABLE `suggestedplace` (
  `IdPlace` int(8) NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suggestedroute`
--

CREATE TABLE `suggestedroute` (
  `IdRoute` int(8) NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedroute`
--

INSERT INTO `suggestedroute` (`IdRoute`, `MadeBy`, `Name`, `Description`, `Image`) VALUES
(1236, 'usuario1@gmail.com', 'afsefsa', 'asefas', 'asfes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Banned` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`Email`, `Banned`) VALUES
('usuario1@gmail.com', 1),
('usuariobloqueado@gmail.com', 0);

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
-- Volcado de datos para la tabla `visit`
--

INSERT INTO `visit` (`IdRoute`, `IdPlace`, `Email`, `Date`, `Rating`) VALUES
(2, 2, 'usuario1@gmail.com', '2018-11-14', 4);

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
-- Indices de la tabla `appearsuggested`
--
ALTER TABLE `appearsuggested`
  ADD PRIMARY KEY (`IdPlace`,`IdRoute`),
  ADD KEY `appearsuggested_ibfk_2` (`IdRoute`);

--
-- Indices de la tabla `appearverified`
--
ALTER TABLE `appearverified`
  ADD PRIMARY KEY (`IdPlace`,`IdRoute`),
  ADD KEY `appearverified_ibfk_2` (`IdRoute`);

--
-- Indices de la tabla `favoritesplaces`
--
ALTER TABLE `favoritesplaces`
  ADD PRIMARY KEY (`IdPlace`,`Email`),
  ADD KEY `favoritesplaces_ibfk_1` (`Email`);

--
-- Indices de la tabla `favoritesroutes`
--
ALTER TABLE `favoritesroutes`
  ADD PRIMARY KEY (`IdRoute`,`Email`),
  ADD KEY `favoritesroutes_ibfk_1` (`Email`);

--
-- Indices de la tabla `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`IdPlace`),
  ADD KEY `Email` (`Email`),
  ADD KEY `place_ibfk_2` (`MadeBy`);

--
-- Indices de la tabla `placecomments`
--
ALTER TABLE `placecomments`
  ADD PRIMARY KEY (`IdPlace`,`Email`,`Date`,`Time`),
  ADD KEY `placecomments_ibfk_1` (`Email`);

--
-- Indices de la tabla `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`IdRoute`),
  ADD KEY `Email` (`Email`),
  ADD KEY `MadeBy` (`MadeBy`);

--
-- Indices de la tabla `routecomments`
--
ALTER TABLE `routecomments`
  ADD PRIMARY KEY (`IdRoute`,`Email`,`Date`,`Time`),
  ADD KEY `routecomments_ibfk_1` (`Email`);

--
-- Indices de la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  ADD PRIMARY KEY (`IdPlace`),
  ADD KEY `suggestedplace_ibfk_1` (`MadeBy`);

--
-- Indices de la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  ADD PRIMARY KEY (`IdRoute`),
  ADD KEY `MadeBy` (`MadeBy`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Email`);

--
-- Indices de la tabla `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`IdRoute`,`IdPlace`,`Email`),
  ADD KEY `visit_ibfk_1` (`IdPlace`),
  ADD KEY `visit_ibfk_3` (`Email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `place`
--
ALTER TABLE `place`
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1251;

--
-- AUTO_INCREMENT de la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1237;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `accesibility`
--
ALTER TABLE `accesibility`
  ADD CONSTRAINT `accesibility_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `appearsuggested`
--
ALTER TABLE `appearsuggested`
  ADD CONSTRAINT `appearsuggested_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appearsuggested_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `appearverified`
--
ALTER TABLE `appearverified`
  ADD CONSTRAINT `appearverified_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `appearverified_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favoritesroutes`
--
ALTER TABLE `favoritesroutes`
  ADD CONSTRAINT `favoritesroutes_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favoritesroutes_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
