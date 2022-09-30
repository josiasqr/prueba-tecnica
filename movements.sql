-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-09-2022 a las 17:01:26
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
-- Base de datos: `movements`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movement`
--

CREATE TABLE `movement` (
  `id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `balance` double NOT NULL,
  `code` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `number_account` bigint(20) NOT NULL,
  `registration_date` datetime DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `movement`
--

INSERT INTO `movement` (`id`, `amount`, `balance`, `code`, `number_account`, `registration_date`, `type`) VALUES
(1, 200, 200, '891630', 6480068923939122419, '2022-09-30 09:57:44', 'INITIAL'),
(2, -10, 190, '55117', 6480068923939122419, '2022-09-30 09:59:49', 'RETIRO'),
(3, 20, 210, '219968', 6480068923939122419, '2022-09-30 09:59:56', 'DEPOSITO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `movement`
--
ALTER TABLE `movement`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_hf3mf4ph180ny4v063f3tah09` (`code`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `movement`
--
ALTER TABLE `movement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
