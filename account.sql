-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-09-2022 a las 18:03:39
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
-- Base de datos: `account`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `identification_client` varchar(255) NOT NULL,
  `initial_balance` double DEFAULT NULL,
  `number_account` bigint(20) NOT NULL,
  `registration_date` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `account`
--

INSERT INTO `account` (`id`, `identification_client`, `initial_balance`, `number_account`, `registration_date`, `status`, `type`) VALUES
(1, '78906523', 1425, 1455771940314725937, '2022-09-22 11:01:31', b'1', 1),
(2, '76598321', 700, 3472248450928574498, '2022-09-22 11:02:13', b'1', 0),
(3, '46578982', 150, 4848421429771643453, '2022-09-22 11:02:29', b'1', 1),
(4, '76598321', 0, 5950238113080988691, '2022-09-22 11:02:50', b'1', 1),
(5, '78906523', 1000, 9205970723414603651, '2022-09-22 11:00:56', b'1', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_je14thmnqiph0626m2f4m0fre` (`number_account`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
