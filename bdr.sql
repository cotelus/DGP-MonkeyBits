-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-11-2018 a las 12:39:16
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
-- Base de datos: `bdr`
--

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
('admin2@hotmail.com'),
('dgp.monkeybits@gmail.com');

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
(1, 8, 1),
(1, 9, 1),
(2, 1, 1),
(2, 8, 2),
(2, 9, 2),
(16, 9, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoriteplaces`
--

CREATE TABLE `favoriteplaces` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `favoriteplaces`
--

INSERT INTO `favoriteplaces` (`IdPlace`, `Email`) VALUES
(1111, 'usuario1@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoriteroutes`
--

CREATE TABLE `favoriteroutes` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `favoriteroutes`
--

INSERT INTO `favoriteroutes` (`IdRoute`, `Email`) VALUES
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
  `Image` text COLLATE utf8_spanish2_ci NOT NULL,
  `RedMovility` tinyint(1) NOT NULL,
  `RedVision` tinyint(1) NOT NULL,
  `ColourBlind` tinyint(1) NOT NULL,
  `Deaf` tinyint(1) NOT NULL,
  `Foreigner` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `place`
--

INSERT INTO `place` (`IdPlace`, `Email`, `MadeBy`, `Name`, `Description`, `Localitation`, `Image`, `RedMovility`, `RedVision`, `ColourBlind`, `Deaf`, `Foreigner`) VALUES
(1, 'admin1@gmail.com', 'usuario1@gmail.com', 'Lugar bonito', 'fasefas', 'asefas', 'afsesa', 0, 0, 0, 0, 0),
(2, 'admin2@hotmail.com', 'usuario1@gmail.com', 'Cansado del nombre asfas', 'asfeasef', 'afsesa', 'afsef', 0, 0, 0, 0, 0),
(16, 'dgp.monkeybits@gmail.com', NULL, 'Parque Almunia', 'Delimitado por los viales Avda. de Andalucía, Calle Periodista Eugenio Selles y Calle Periodista José Mezcua Ruiz se extiende este bonito parque.\r\n\r\nTiene una distribución triangular rodeado por una valla y muro, además de existir distintas pantallas vegetales en cada uno de los lados: ciprés, pruno, espino de fuego y laurel. Se desarrolla a partir de diversos paseos que se complementan y relacionan a lo largo de todo su recorrido, delimitando numerosas zonas de estancia con elementos característicos como el quiosco de música o la fuente con pérgola de glicinas en su parte posterior. Uno de los elementos principales del parque es la acequia, atravesada por puentecillos de piedra que dan paso a cuadros de aromáticas, hacia un lado, y a una paseo de arces, al otro.\r\n\r\nDistrito: Chana\r\nSuperficie: 32.142 metros cuadrados\r\nTipo de parque: Cerrado', 'Delimitado por los viales Avda. de Andalucía. Granada.', 'http://www.besarsengranada.es/wp-content/uploads/2015/05/besarse-en-granada-flores-parque-almunia-5_21390.jpg', 0, 0, 0, 0, 0),
(17, '', NULL, 'Parque Federico García Lorca', 'El Parque García Lorca, con cuatro entradas , se desarrolla alrededor de la Huerta de San Vicente, en los terrenos que antiguamente pertenecían a la familia de Federico García Lorca. El parque está constituido por diversos paseos , destacando dos avenidas: el paseo de la alameda , el cual se desarrolla paralelamente al río y se dirige al estanque , y el paseo de tilos, desde la entrada principal hasta las zonas de servicios Delimitados por estos paseos y caminos, existen diversos sectores que terminan de configurar este parque: los jardines neoplasticistas, las acequias, la fuente cibernética, el bosque de ribera, la rosaleda y las huertas.\r\n\r\nDistrito: Ronda\r\nSuperficie: 70.000 metros cuadrados\r\nTipo de parque: Cerrado', 'Entre las calles Arabial , Virgen Blanca y el Camino de Purchil.', 'https://www.conmishijos.com/assets/planes/4000/4120-parque-federico-garcia-lorca-granada.jpg', 0, 0, 0, 0, 0);

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
(3, 'admin1@gmail.com', 'usuario1@gmail.com', 'afsefsa', 'asefas', 'asfes'),
(4, 'admin1@gmail.com', 'usuariobloqueado@gmail.com', 'afsse', 'asfeeas', 'fses'),
(5, 'admin1@gmail.com', 'usuario1@gmail.com', 'fasef', 'afes', 'asefs'),
(8, 'dgp.monkeybits@gmail.com', NULL, 'fasefasfesasefsa', 'asefasfsea', 'fsafease'),
(9, 'dgp.monkeybits@gmail.com', NULL, 'Parques y Jardines de Granada', 'Muchos jardines, florecicas, alguna que otra farola, y de vez en cuando te cae agua de las fuentes.', 'https://guiasgranada.com/advisor/wp-content/uploads/2017/06/garcia1.jpg');

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
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Localitation` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL,
  `RedMovility` tinyint(1) NOT NULL,
  `RedVision` tinyint(1) NOT NULL,
  `ColourBlind` tinyint(1) NOT NULL,
  `Deaf` tinyint(1) NOT NULL,
  `Foreigner` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedplace`
--

INSERT INTO `suggestedplace` (`IdPlace`, `MadeBy`, `Description`, `Localitation`, `Name`, `Image`, `RedMovility`, `RedVision`, `ColourBlind`, `Deaf`, `Foreigner`) VALUES
(3, 'usuario1@gmail.com', 'faef', 'asfe', 'fase', 'afsfs', 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suggestedroute`
--

CREATE TABLE `suggestedroute` (
  `IdRoute` int(8) NOT NULL,
  `MadeBy` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Description` text COLLATE utf8_spanish2_ci NOT NULL,
  `Image` text COLLATE utf8_spanish2_ci NOT NULL,
  `Accesibility` text COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedroute`
--

INSERT INTO `suggestedroute` (`IdRoute`, `MadeBy`, `Name`, `Description`, `Image`, `Accesibility`) VALUES
(1240, 'usuariobloqueado@gmail.com', 'fasef', 'fasef', 'asfes', ''),
(1241, 'usuariobloqueado@gmail.com', 'fsfsefa', 'asfsae', 'fsefs', ''),
(1242, 'usuario1@gmail.com', 'fsfa', 'fasef', 'sasfes', ''),
(1243, 'usuario1@gmail.com', 'afsfe', 'asef', 'asfes', ''),
(1244, 'usuario1@gmail.com', 'fasfe', 'asfeasf', 'afses', ''),
(1245, 'usuariobloqueado@gmail.com', 'afsefas', 'asfas', 'asfesf', ''),
(1246, 'usuario1@gmail.com', 'fasef', 'asefas', 'fasfesf', ''),
(1247, 'usuario1@gmail.com', 'fasef', 'afsef', 'fasfe', ''),
(1248, 'usuariobloqueado@gmail.com', 'fsfa', 'afsfe', 'asfe', '');

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
-- Indices de la tabla `favoriteplaces`
--
ALTER TABLE `favoriteplaces`
  ADD PRIMARY KEY (`IdPlace`,`Email`),
  ADD KEY `favoritesplaces_ibfk_1` (`Email`);

--
-- Indices de la tabla `favoriteroutes`
--
ALTER TABLE `favoriteroutes`
  ADD PRIMARY KEY (`IdRoute`,`Email`),
  ADD KEY `favoritesroutes_ibfk_1` (`Email`);

--
-- Indices de la tabla `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`IdPlace`),
  ADD UNIQUE KEY `Localitation` (`Localitation`),
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
  ADD UNIQUE KEY `Name` (`Name`),
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
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1249;

--
-- Restricciones para tablas volcadas
--

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
-- Filtros para la tabla `favoriteroutes`
--
ALTER TABLE `favoriteroutes`
  ADD CONSTRAINT `favoriteroutes_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favoriteroutes_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
