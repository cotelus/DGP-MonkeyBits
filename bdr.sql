-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-12-2018 a las 14:28:12
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
(1, 1, 1),
(1, 2, 1),
(16, 1, 1),
(21, 3, 1),
(22, 3, 2);

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
(2, 9, 3),
(16, 9, 4),
(17, 9, 2),
(21, 17, 1),
(22, 17, 2),
(23, 17, 3),
(34, 17, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `favoriteplaces`
--

CREATE TABLE `favoriteplaces` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

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
  `ImageDescription` text COLLATE utf8_spanish2_ci,
  `RedMovility` tinyint(1) DEFAULT '0',
  `RedVision` tinyint(1) DEFAULT '0',
  `ColourBlind` tinyint(1) DEFAULT '0',
  `Deaf` tinyint(1) DEFAULT '0',
  `Foreigner` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `place`
--

INSERT INTO `place` (`IdPlace`, `Email`, `MadeBy`, `Name`, `Description`, `Localitation`, `Image`, `ImageDescription`, `RedMovility`, `RedVision`, `ColourBlind`, `Deaf`, `Foreigner`) VALUES
(1, 'admin1@gmail.com', 'usuario1@gmail.com', 'Lugar bonito', 'fasefas', 'asefas', 'afsesa', '', 0, 0, 0, 0, 0),
(2, 'admin2@hotmail.com', 'usuario1@gmail.com', 'Cansado del nombre asfas', 'asfeasef', 'afsesa', 'afsef', '', 0, 0, 0, 0, 0),
(16, 'dgp.monkeybits@gmail.com', NULL, 'Parque Almunia', 'Delimitado por los viales Avda. de Andalucia, Calle Periodista Eugenio Selles y Calle Periodista Jose Mezcua Ruiz se extiende este bonito parque.\r\n\r\nTiene una distribucion triangular rodeado por una valla y muro, ademas de existir distintas pantallas vegetales en cada uno de los lados: cipres, pruno, espino de fuego y laurel. Se desarrolla a partir de diversos paseos que se complementan y relacionan a lo largo de todo su recorrido, delimitando numerosas zonas de estancia con elementos caracteristicos como el quiosco de musica o la fuente con pergola de glicinas en su parte posterior. Uno de los elementos principales del parque es la acequia, atravesada por puentecillos de piedra que dan paso a cuadros de aromaticas, hacia un lado, y a una paseo de arces, al otro.\r\n\r\nDistrito: Chana\r\nSuperficie: 32.142 metros cuadrados\r\nTipo de parque: Cerrado', 'Delimitado por los viales Avda. de Andalucia. Granada.', 'http://www.besarsengranada.es/wp-content/uploads/2015/05/besarse-en-granada-flores-parque-almunia-5_21390.jpg', '', 0, 0, 0, 0, 0),
(17, '', NULL, 'Parque Federico Garcia Lorca', 'El Parque Garcia Lorca, con cuatro entradas , se desarrolla alrededor de la Huerta de San Vicente, en los terrenos que antiguamente pertenecian a la familia de Federico Garcia Lorca. El parque esta constituido por diversos paseos , destacando dos avenidas: el paseo de la alameda , el cual se desarrolla paralelamente al rio y se dirige al estanque , y el paseo de tilos, desde la entrada principal hasta las zonas de servicios Delimitados por estos paseos y caminos, existen diversos sectores que terminan de configurar este parque: los jardines neoplasticistas, las acequias, la fuente cibernética, el bosque de ribera, la rosaleda y las huertas.\r\n\r\nDistrito: Ronda\r\nSuperficie: 70.000 metros cuadrados\r\nTipo de parque: Cerrado', 'Entre las calles Arabial , Virgen Blanca y el Camino de Purchil.', 'https://www.conmishijos.com/assets/planes/4000/4120-parque-federico-garcia-lorca-granada.jpg', '', 0, 0, 0, 0, 0),
(18, '', 'usuariobloqueado@gmail.com', 'fasef', 'fasef', '', 'asfes', '', 0, 0, 0, 0, 0),
(19, 'dgp.monkeybits@gmail.com', NULL, 'Jardines del Triunfo', 'Se extiende este bonito parque, que tiene una distribucion triangular rodeado por una valla y muro, ademas de existir distintas pantallas vegetales en cada uno de los lados: cipres, pruno, espino de fuego y laurel. Se desarrolla a partir de diversos paseos que se complementan y relacionan a lo largo de todo su recorrido, delimitando numerosas zonas de estancia con elementos caracteristicos como el quiosco de musica o la fuente con pergola de glicinas en su parte posterior. Uno de los elementos principales del parque es la acequia, atravesada por puentecillos de piedra que dan paso a cuadros de aromaticas, hacia un lado, y a una paseo de arces, al otro.\r\n\r\nDistrito: Chana\r\nSuperficie: 32.142 metros cuadrados\r\nTipo de parque: Cerrado', 'Avda. de Andalucia, c/Periodista Eugenio Selles, c/Periodista Jose Mezcua Ruiz.', 'http://2.bp.blogspot.com/_vf7VA2YFrwU/TP_aQ1j9O1I/AAAAAAAAEDM/9BAgOnll5js/s400/8%2Bde%2Bdiciembre%2B1.jpg', '', 1, 0, 0, 0, 1),
(20, 'dgp.monkeybits@gmail.com', NULL, 'Mirador de San Nicolas', 'El Mirador de San Nicolas es quizas el mas famoso de Granada. Desde que Bill Clinton lo visitara en 1997 y dijera que vio “la puesta de sol más bonita del mundo”, se ha convertido en visita obligada para los turistas.\r\n\r\nLa belleza de este mirador es innegable: la Alhambra y el Generalife frente a frente, la ciudad a sus pies y Sierra Nevada detras.\r\n\r\nEl encanto de su entorno, en pleno barrio del Albaycin, con sus calles empedradas, sus aljibes, sus bares de tapas y sus gentes, aniade sabor a la estampa. \r\n\r\nEsta siempre animado, con foraneos y locales. Abundan los vendedores de artesania y grupos que con sus guitarras improvisan toques flamencos.', 'Albaicin, Granada', 'http://nisnomad.com/wp-content/uploads/2016/08/IMG_20160812_081240.jpg', '', 1, 0, 0, 0, 1),
(21, 'dgp.monkeybits@gmail.com', NULL, 'Alhambra', 'Es una ciudad palatina andalusi situada en Granada, en comunidad autonoma de Andalucia, Espania. Consiste en un conjunto de palacios, jardines y fortaleza que albergaba una verdadera ciudadela dentro de la propia ciudad de Granada, que servia como alojamiento al monarca y a la corte del Reino nazari de Granada. Su verdadero atractivo, como en otras obras musulmanas de la epoca, no solo radica en los interiores, cuya decoracion esta entre las cumbres del arte andalusi, sino tambien en su localizacion y adaptacion, generando un paisaje nuevo pero totalmente integrado con la naturaleza preexistente.', 'Calle Real de la Alhambra', 'https://cdn.getyourguide.com/img/tour_img-989388-148.jpg', '', 1, 1, 0, 0, 1),
(22, 'dgp.monkeybits@gmail.com', NULL, 'Generalife', 'Es la villa con jardines habitada por los reyes musulmanes de Granada como lugar de descanso, situado en la ciudad espaniola de Granada, en Andalucia. Fue concebida como villa rural, donde jardines ornamentales, huertos y arquitectura se integraban, en las cercanias de la Alhambra. El origen del nombre esta discutido. Algunos abogan por Yannat al-Arif como «huerta del arquitecto», o «jardin del arquitecto» aunque pudo significar «el mas excelso jardin». Ese huerto real era comun en las cortes hispano-arabes y es fruto de las reformas y aniadidos que le aportaron los diferentes sultanes.', ' Exterior de las murallas de la Alhambra, al este, en la ladera del Cerro del Sol.', 'https://media-cdn.tripadvisor.com/media/photo-s/11/7e/aa/fb/jardines-del-generalife.jpg', '', 1, 1, 0, 0, 1),
(23, 'dgp.monkeybits@gmail.com', NULL, 'Patio de los leones', 'El Patio de los Leones es el centro del Palacio que lleva este nombre. Es diferente al del Palacio de Comares, debido al eje del jardin anterior, del siglo XIII, sobre el que se disenia y a que el banio de Comares tiene un eje Norte-Sur y el palacio de los Leones tiene un eje Este-Oeste.', 'Calle Real de la Alhambra.', 'http://cadenaser00.epimg.net/emisora/imagenes/2016/08/30/radio_granada/1472561813_422996_1472561946_noticia_normal.jpg', '', 1, 1, 0, 0, 1),
(34, 'dgp.monkeybits@gmail.com', NULL, 'Palacio de Carlos V', 'El palacio de Carlos V es una construccion renacentista situada en la colina de la Alhambra de la ciudad espaniola de Granada, en Andalucia. Desde 1958, es sede del Museo de Bellas Artes de Granada y, desde 1994, también es sede del Museo de la Alhambra.', 'calle de la Alhambra', 'https://www.101viajes.com/sites/default/files/palacio-carlos-v.jpg', 'El palacio de Carlos V es una construccion renacentista situada en la colina de la Alhambra de la ciudad espaniola de Granada.', 1, 1, 0, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `placecomments`
--

CREATE TABLE `placecomments` (
  `IdPlace` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Content` varchar(130) COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Reported` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `placecomments`
--

INSERT INTO `placecomments` (`IdPlace`, `Email`, `Content`, `Date`, `Time`, `Reported`) VALUES
(17, 'usuario1@gmail.com', 'hola', '2018-12-11', '21:00:00', 0);

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
  `Image` text COLLATE utf8_spanish2_ci NOT NULL,
  `ImageDescription` text COLLATE utf8_spanish2_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `route`
--

INSERT INTO `route` (`IdRoute`, `Email`, `MadeBy`, `Name`, `Description`, `Image`, `ImageDescription`) VALUES
(1, 'admin1@gmail.com', 'usuario1@gmail.com', 'Ruta de prueba', 'Prueba', 'Vacia', ''),
(2, 'admin2@hotmail.com', 'usuariobloqueado@gmail.com', 'Ruta interesante', 'asfasef', 'asfeasef', ''),
(3, 'admin1@gmail.com', 'usuario1@gmail.com', 'Ruta de prueba 2', 'Prueba', 'Vacia', ''),
(4, 'admin1@gmail.com', 'usuariobloqueado@gmail.com', 'Ruta de prueba 3', 'Prueba', 'Vacia', ''),
(5, 'admin1@gmail.com', 'usuario1@gmail.com', 'Ruta de prueba 4', 'Prueba', 'Vacia', ''),
(8, 'dgp.monkeybits@gmail.com', NULL, 'Ruta de prueba 5', 'Prueba', 'Vacia', ''),
(9, 'dgp.monkeybits@gmail.com', NULL, 'Parques y Jardines de Granada', 'Muchos jardines, florecicas, alguna que otra farola, y de vez en cuando te cae agua de las fuentes.', 'https://guiasgranada.com/advisor/wp-content/uploads/2017/06/garcia1.jpg', 'Muchos jardines, florecicas, alguna que otra farola, y de vez en cuando te cae agua de las fuentes.'),
(10, '', 'usuariobloqueado@gmail.com', 'Ruta de prueba 6', 'Prueba', 'Vacia', ''),
(11, '', 'usuario1@gmail.com', 'Ruta de prueba 7', 'Prueba', 'Vacia', ''),
(12, '', 'usuariobloqueado@gmail.com', 'Ruta de prueba 8', 'Prueba', 'VAcia', ''),
(16, '', 'usuario1@gmail.com', 'Ruta de prueba 9', 'prueba', 'Vacia', ''),
(17, 'dgp.monkeybits@gmail.com', NULL, 'Tour de la Alhambra', 'Ruta por algunos de los puntos turisticos mas importantes de la Alhambra, recorriendo sus grandes puertas, palaciones y jardines.', 'https://images.placesonline.com/photos/46695_granada_alcazaba.jpg?quality=80&w=710&h=510&mode=crop', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `routecomments`
--

CREATE TABLE `routecomments` (
  `IdRoute` int(8) NOT NULL,
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Content` text COLLATE utf8_spanish2_ci NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Reported` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `routecomments`
--

INSERT INTO `routecomments` (`IdRoute`, `Email`, `Content`, `Date`, `Time`, `Reported`) VALUES
(11, 'micorreojeje@gmail.com', 'fasfeas', '2018-12-25', '02:00:00', 1),
(16, 'usuariobloqueado@gmail.com', 'adios', '2018-12-19', '06:00:00', 0);

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
  `RedMovility` tinyint(1) DEFAULT '0',
  `RedVision` tinyint(1) DEFAULT '0',
  `ColourBlind` tinyint(1) DEFAULT '0',
  `Deaf` tinyint(1) DEFAULT '0',
  `Foreigner` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `suggestedplace`
--

INSERT INTO `suggestedplace` (`IdPlace`, `MadeBy`, `Description`, `Localitation`, `Name`, `Image`, `RedMovility`, `RedVision`, `ColourBlind`, `Deaf`, `Foreigner`) VALUES
(1, 'usuario1@gmail.com', 'Lugar muy bonito para pasear', 'Cerca de mi casa', 'Lugar de prueba', 'VAcia', 1, 1, 1, 0, 0),
(2, 'micorreojeje@gmail.com', 'Lugar caluroso', 'Descampado', 'Lugar de prueba 2', 'Vacia', 0, 0, 0, 1, 1);

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
(1, 'micorreojeje@gmail.com', 'Ruta 1', 'jejejej', 'fasef'),
(2, 'usuariobloqueado@gmail.com', 'Otra ruta', 'fasef', 'fase'),
(3, 'micorreojeje@gmail.com', 'Ruta accesible', 'la ruta más accesible de todas', 'fasef');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `Email` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `Banned` tinyint(1) DEFAULT '0',
  `Reported` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`Email`, `Name`, `Banned`, `Reported`) VALUES
('micorreojeje@gmail.com', 'Me llamo Ralph', 0, 0),
('usuario1@gmail.com', 'Homer Simpson', 0, 1),
('usuariobloqueado@gmail.com', 'Vecino de tu abuela', 1, 1);

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
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  MODIFY `IdPlace` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  MODIFY `IdRoute` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- Filtros para la tabla `favoriteplaces`
--
ALTER TABLE `favoriteplaces`
  ADD CONSTRAINT `favoriteplaces_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favoriteplaces_ibfk_2` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `favoriteroutes`
--
ALTER TABLE `favoriteroutes`
  ADD CONSTRAINT `favoriteroutes_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `favoriteroutes_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `placecomments`
--
ALTER TABLE `placecomments`
  ADD CONSTRAINT `placecomments_ibfk_1` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `placecomments_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `routecomments`
--
ALTER TABLE `routecomments`
  ADD CONSTRAINT `routecomments_ibfk_1` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `routecomments_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suggestedplace`
--
ALTER TABLE `suggestedplace`
  ADD CONSTRAINT `suggestedplace_ibfk_1` FOREIGN KEY (`MadeBy`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suggestedroute`
--
ALTER TABLE `suggestedroute`
  ADD CONSTRAINT `suggestedroute_ibfk_1` FOREIGN KEY (`MadeBy`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `visit_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visit_ibfk_2` FOREIGN KEY (`IdRoute`) REFERENCES `route` (`IdRoute`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `visit_ibfk_3` FOREIGN KEY (`IdPlace`) REFERENCES `place` (`IdPlace`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
