-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2021 a las 17:34:43
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `findeciclo_dgm`
--

-- --------------------------------------------------------

CREATE DATABASE findeciclo_dgm;
USE findeciclo_dgm;

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `isbn` bigint(20) NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `paginas` int(11) DEFAULT NULL,
  `portada` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `tapa` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`isbn`, `autor`, `editorial`, `genero`, `paginas`, `portada`, `precio`, `tapa`, `titulo`) VALUES
(9788417347017, 'Cixin Liu', 'Nova', 'Ciencia ficción', 736, 'portadas\\9788417347017_Elfindelamuerte.png', 20.8, 'Tapa blanda', 'El fin de la muerte'),
(9788417347291, 'Brandon Sanderson', 'Nova', 'Fantasía', 672, 'portadas\\9788417347291_Elimperiofinal.png', 20.9, 'Tapa dura', 'El imperio final'),
(9788466658904, 'Brandon Sanderson', 'Nova', 'Fantasía', 784, 'portadas\\9788466658904_ElPozodelaAscensión.png', 20.9, 'Tapa dura', 'El Pozo de la Ascensión'),
(9788466658911, 'Brandon Sanderson', 'Nova', 'Fantasía', 760, 'portadas\\9788466658911_ElHéroedelasEras.png', 20.9, 'Tapa dura', 'El Héroe de las Eras'),
(9788466659734, 'Cixin Lui', 'Nova', 'Ciencia ficción', 416, 'portadas\\9788466659734_Elproblemadelostrescuerpos.png', 19.85, 'Tapa blanda', 'El problema de los tres cuerpos'),
(9788466660921, 'Cixin Lui', 'Nova', 'Ciencia ficción', 576, 'portadas\\9788466660921_Elbosqueoscuro.png', 20.8, 'Tapa blanda', 'El bosque oscuro'),
(9788466664417, 'Juan Gómez-Jurado', 'Ediciones B', 'Realismo', 568, 'portadas\\9788466664417_Reinaroja.png', 20.9, 'Tapa dura', 'Reina roja'),
(9788490628751, 'José Saramago', 'Debolsillo', 'Realismo', 384, 'portadas\\9788490628751_Elhombreduplicado.png', 9.95, 'Tapa blanda', 'El hombre duplicado'),
(9788490665640, 'Haruki Murakami', 'TusQuets Editores', 'Realismo', 480, 'portadas\\9788490665640_Lamuertedelcomendador(Libro1).png', 20.8, 'Tapa blanda', 'La muerte del comendador (Libro 1)'),
(9788490668139, 'Haruki Murakami', 'TusQuets Editores', 'Realismo', 496, 'portadas\\9788490668139_Lamuertedelcomendador(Libro2).png', 20.8, 'Tapa blanda', 'La muerte del comendador (Libro 2)');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `musica`
--

CREATE TABLE `musica` (
  `ean` bigint(20) NOT NULL,
  `artista` varchar(255) DEFAULT NULL,
  `caratula` varchar(255) DEFAULT NULL,
  `discografica` varchar(255) DEFAULT NULL,
  `duracion` int(11) DEFAULT NULL,
  `fechaPublicacion` date DEFAULT NULL,
  `formato` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `musica`
--

INSERT INTO `musica` (`ean`, `artista`, `caratula`, `discografica`, `duracion`, `fechaPublicacion`, `formato`, `genero`, `precio`, `titulo`) VALUES
(61422311601, 'Tool', 'caratulas\\61422311601_Lateralus.png', 'Volcano Entertainment', 79, '2001-05-15', 'Vinilo', 'Metal progresivo', 39.99, 'Lateralus'),
(93624775522, 'Linkin Park', 'caratulas\\093624775522_HybridTheory.png', 'Warner Bros.', 38, '2000-10-24', 'CD', 'Metal alternativo', 19.99, 'Hybrid Theory'),
(190295216160, 'Héroes del Silencio', 'caratulas\\0190295216160_Elespiritudelvino.png', 'EMI', 72, '1993-06-14', 'Vinilo', 'Rock alternativo', 23.99, 'El espíritu del vino'),
(190759505526, 'Tool', 'caratulas\\190759505526_FearInoculum.png', 'Tool Dissectional', 79, '2019-08-30', 'CD', 'Metal progresivo', 79.99, 'Fear Inoculum'),
(602527779089, 'Nirvana', 'caratulas\\602527779089_Nervermind.png', 'DGC Records', 42, '1991-09-24', 'CD', 'Grunge', 9.99, 'Nervermind'),
(602557611755, 'Kendrick Lamar', 'caratulas\\602557611755_DAMN.png', 'Top Dawg', 55, '2017-04-14', 'CD', 'Rap', 11.13, 'DAMN.'),
(652637331516, 'The National', 'caratulas\\652637331516_TroubleWillFindMe.png', '4AD', 55, '2013-05-17', 'Vinilo', 'Rock', 29.99, 'Trouble Will Find Me'),
(828765050425, 'HIM', 'caratulas\\828765050425_LoveMetal.png', 'Sony BMG', 50, '2003-04-14', 'CD', 'Metal gótico', 19.99, 'Love Metal'),
(4988009589060, 'X Japan', 'caratulas\\4988009589060_BlueBlood.png', 'Ki/oon', 65, '1989-04-21', 'CD', 'Heavy Metal', 19.99, 'Blue Blood'),
(9718469531936, 'Korn', 'caratulas\\9718469531936_FollowtheLeader.png', 'Immortal Records', 70, '1998-08-18', 'Vinilo', 'Metal', 29.99, 'Follow the Leader');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombreUsuario` varchar(255) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fechaAlta` date DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rango` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombreUsuario`, `apellidos`, `contrasena`, `email`, `fechaAlta`, `fechaNacimiento`, `nombre`, `rango`) VALUES
('admin', '1', '81dc9bdb52d04dc20036dbd8313ed055', 'admin@miscolecciones.com', '2021-05-23', NULL, 'Administrador', 2),
('Diego', 'García Miño', '81dc9bdb52d04dc20036dbd8313ed055', 'diego.garcia.mino@gmail.com', '2021-05-23', NULL, 'Diego', 1),
('JDoe', 'Doe', '81dc9bdb52d04dc20036dbd8313ed055', 'johndoe@gmail.com', '2021-05-23', NULL, 'John', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_libro`
--

CREATE TABLE `usuario_libro` (
  `Usuario_nombreUsuario` varchar(255) NOT NULL,
  `libros_isbn` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_libro`
--

INSERT INTO `usuario_libro` (`Usuario_nombreUsuario`, `libros_isbn`) VALUES
('JDoe', 9788466659734),
('JDoe', 9788466660921),
('JDoe', 9788417347017),
('JDoe', 9788466664417),
('Diego', 9788490628751),
('Diego', 9788490665640),
('Diego', 9788490668139),
('Diego', 9788417347291),
('Diego', 9788466658904),
('Diego', 9788466658911);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_musica`
--

CREATE TABLE `usuario_musica` (
  `Usuario_nombreUsuario` varchar(255) NOT NULL,
  `musica_ean` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_musica`
--

INSERT INTO `usuario_musica` (`Usuario_nombreUsuario`, `musica_ean`) VALUES
('Diego', 61422311601),
('Diego', 190759505526),
('Diego', 828765050425),
('JDoe', 602527779089),
('JDoe', 4988009589060),
('JDoe', 93624775522);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`isbn`);

--
-- Indices de la tabla `musica`
--
ALTER TABLE `musica`
  ADD PRIMARY KEY (`ean`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`nombreUsuario`);

--
-- Indices de la tabla `usuario_libro`
--
ALTER TABLE `usuario_libro`
  ADD KEY `FK7cvxb7xl9e9sxj5x6bt28lgj7` (`libros_isbn`),
  ADD KEY `FKibthk1wggv97jqkhik9jy6wjq` (`Usuario_nombreUsuario`);

--
-- Indices de la tabla `usuario_musica`
--
ALTER TABLE `usuario_musica`
  ADD KEY `FKclqi1ul2m02iv155iys6j1air` (`musica_ean`),
  ADD KEY `FKqla3bgqvb9gyw78vnfeswvgcl` (`Usuario_nombreUsuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario_libro`
--
ALTER TABLE `usuario_libro`
  ADD CONSTRAINT `FK7cvxb7xl9e9sxj5x6bt28lgj7` FOREIGN KEY (`libros_isbn`) REFERENCES `libro` (`isbn`),
  ADD CONSTRAINT `FKibthk1wggv97jqkhik9jy6wjq` FOREIGN KEY (`Usuario_nombreUsuario`) REFERENCES `usuario` (`nombreUsuario`);

--
-- Filtros para la tabla `usuario_musica`
--
ALTER TABLE `usuario_musica`
  ADD CONSTRAINT `FKclqi1ul2m02iv155iys6j1air` FOREIGN KEY (`musica_ean`) REFERENCES `musica` (`ean`),
  ADD CONSTRAINT `FKqla3bgqvb9gyw78vnfeswvgcl` FOREIGN KEY (`Usuario_nombreUsuario`) REFERENCES `usuario` (`nombreUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
