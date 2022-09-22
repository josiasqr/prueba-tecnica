-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2022 a las 18:04:01
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `client`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `password` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `person_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`password`, `registration_date`, `status`, `person_id`) VALUES
('1234', '2022-09-22 10:54:21', b'1', 1),
('5678', '2022-09-22 10:55:14', b'1', 2),
('1245', '2022-09-22 10:56:20', b'1', 3),
('mypassword', '2022-09-22 10:57:16', b'1', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `direction` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `identification` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`id`, `age`, `direction`, `gender`, `identification`, `name`, `phone`) VALUES
(1, 23, 'Otavalo sn y principal', 'Masculino', '78906523', 'Jose Lema', '098254785'),
(2, 21, 'Amazonas y  NNUU', 'Femenino', '76598321', 'Marianela Montalvo', '097548965'),
(3, 19, '13 junio y Equinoccial', 'Masculino', '46578982', 'Juan Osorio', '098874587'),
(4, 32, 'Lima Peru', 'Masculino', '78907341', 'Pedro Suarez', '987654321');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`person_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKkxflpsue6s9kscgmuwt7ob1f3` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
