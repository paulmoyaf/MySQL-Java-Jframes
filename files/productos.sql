-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-03-2023 a las 11:50:41
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
-- Base de datos: `productos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instrumentos`
--

CREATE TABLE `instrumentos` (
  `codigo` varchar(15) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `precio` decimal(7,2) NOT NULL,
  `descuento` decimal(5,2) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `color` varchar(30) NOT NULL,
  `teclas` varchar(3) NOT NULL,
  `conector` varchar(15) NOT NULL,
  `envio` varchar(20) GENERATED ALWAYS AS (if(`tipo` = 'PRIME','GRATIS','+15€')) VIRTUAL,
  `pvp` decimal(8,2) GENERATED ALWAYS AS (if(`tipo` = 'PRIME',`precio` - `precio` * `descuento` / 100,`precio` - `precio` * `descuento` / 100 + 15)) VIRTUAL
) ;

--
-- Volcado de datos para la tabla `instrumentos`
--

INSERT INTO `instrumentos` (`codigo`, `marca`, `precio`, `descuento`, `tipo`, `color`, `teclas`, `conector`) VALUES
('t01', 'DOS', '10.00', '10.00', 'PRIME', 'NEGRO', '61', 'USB'),
('t03', 'TES', '10.00', '1.00', 'PRIME', 'NEGRO', '12', 'USB'),
('t04', 'FENDER', '10.00', '1.00', 'PRIME', 'NEGRO', '61', 'USB'),
('t05', 'GIBSON', '500.00', '10.00', 'REGULAR', 'NEGRO', '61', 'USB'),
('t32', 'UNO', '10.00', '10.00', 'REGULAR', 'BLANCO', '61', 'MIDI'),
('t322', 'HELA', '15.00', '100.00', 'REGULAR', 'VERDE', '20', 'USB'),
('t4', 'PERL', '500.00', '10.00', 'REGULAR', 'ROJO', '84', 'MIDI'),
('t55', 'FENDER', '450.00', '10.00', 'PRIME', 'BLANCO', '61', 'USB'),
('t65', 'YAMAHA', '375.00', '25.00', 'REGULAR', 'NEGRO', '61', 'XFIND'),
('t7', 'PACIFIC', '1000.00', '10.00', 'REGULAR', 'ROJO', '81', 'USB'),
('t8', 'FENDERA', '1000.00', '10.00', 'PRIME', 'VERDE', '61', 'USB'),
('t9', 'TECH', '100.00', '10.00', 'PRIME', 'ROJO', '61', 'USB'),
('t93', 'TECH', '100.00', '1.00', 'PRIME', 'ROJO', '61', 'MIDI'),
('tz', 'KING', '500.00', '100.00', 'REGULAR', 'NEGRO', '61', 'MIDI');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `instrumentos`
--
ALTER TABLE `instrumentos`
  ADD PRIMARY KEY (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
