-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2022 a las 18:04:09
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
  `amount` double DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `number_account` bigint(20) NOT NULL,
  `registration_date` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `movement`
--

INSERT INTO `movement` (`id`, `amount`, `code`, `number_account`, `registration_date`, `type`) VALUES
(1, 2000, '63832', 1455771940314725937, '2022-09-22 10:57:43', 2),
(2, 100, '170064', 3472248450928574498, '2022-09-22 10:57:57', 2),
(3, 540, '914936', 5950238113080988691, '2022-09-22 10:59:58', 2),
(4, 1000, '370215', 9205970723414603651, '2022-09-22 11:00:56', 2),
(5, -575, '327519', 1455771940314725937, '2022-09-22 11:01:31', 1),
(6, 600, '93975', 3472248450928574498, '2022-09-22 11:02:13', 0),
(7, 150, '464338', 4848421429771643453, '2022-09-22 11:02:29', 0),
(8, -540, '953872', 5950238113080988691, '2022-09-22 11:02:50', 1);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
