-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 06-04-2026 a las 18:54:04
-- Versión del servidor: 10.6.23-MariaDB-cll-lve
-- Versión de PHP: 8.3.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `granalla_ProyectoHirata`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Camiones`
--

CREATE TABLE `Camiones` (
  `idCamion` varchar(20) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `anio` int(11) NOT NULL,
  `kilometrajeActual` int(11) DEFAULT NULL,
  `conductorAsignado` varchar(100) DEFAULT NULL,
  `estadoMantenimiento` varchar(20) DEFAULT 'OPERATIVO'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `Camiones`
--

INSERT INTO `Camiones` (`idCamion`, `marca`, `modelo`, `anio`, `kilometrajeActual`, `conductorAsignado`, `estadoMantenimiento`) VALUES
('AE-CF-34', 'KENWORTH', 'T680 NEXT GEN', 2024, 0, NULL, 'OPERATIVO'),
('XA-CR-89', 'SCANIA', '770 S V8', 2025, 20000, NULL, 'OPERATIVO'),
('XD-CD-89', 'SCANIA', 'R500', 2026, 0, NULL, 'OPERATIVO'),
('XH-66-40', 'VOLVO', 'FH16', 2022, 5000, NULL, 'OPERATIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

CREATE TABLE `equipos` (
  `idEquipo` int(11) NOT NULL,
  `mac_equipo` varchar(50) NOT NULL,
  `fechaBan` date DEFAULT NULL,
  `horaBan` time DEFAULT NULL,
  `estado` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`idEquipo`, `mac_equipo`, `fechaBan`, `horaBan`, `estado`) VALUES
(1, '0A:00:27:00:00:12', '2026-03-24', '03:05:33', 0),
(2, '0A:00:27:00:00:03', NULL, NULL, 0),
(3, '00:FF:CA:66:52:7C', NULL, NULL, 0),
(4, 'AE:D5:98:7C:0B:EE', '2026-03-27', '19:42:32', 1),
(5, '8A:EF:9E:F3:CB:0E', NULL, NULL, 0),
(6, '50:EB:F6:E6:C5:F8', NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `HistorialMantenimiento`
--

CREATE TABLE `HistorialMantenimiento` (
  `idMantenimiento` int(11) NOT NULL,
  `idCamion` varchar(20) DEFAULT NULL,
  `fechaMantenimiento` date NOT NULL,
  `tipoMantenimiento` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `kilometrajeAlMomento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LoginEmpleados`
--

CREATE TABLE `LoginEmpleados` (
  `Rut` varchar(12) NOT NULL,
  `Contrasena` varchar(50) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Apellido` varchar(100) NOT NULL,
  `idCamion` varchar(20) DEFAULT NULL,
  `Cargo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `LoginEmpleados`
--

INSERT INTO `LoginEmpleados` (`Rut`, `Contrasena`, `Nombre`, `Apellido`, `idCamion`, `Cargo`) VALUES
('22.103.840-1', 'Jluis22', 'Jose', 'Luis', NULL, 'Administrador'),
('22.103.840-7', 'Aardilla27', 'Alvin', 'Ardilla', 'XH-66-40', 'Mecanico'),
('22.103.840-8', 'Ccamboya22', 'Capitan', 'Camboya', 'XH-66-40', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sensores`
--

CREATE TABLE `Sensores` (
  `idLectura` int(11) NOT NULL,
  `idCamion` varchar(20) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `temperaturaMotorCamion` float(5,2) DEFAULT NULL,
  `consumoCombustible` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Camiones`
--
ALTER TABLE `Camiones`
  ADD PRIMARY KEY (`idCamion`);

--
-- Indices de la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`idEquipo`),
  ADD UNIQUE KEY `mac_equipo` (`mac_equipo`);

--
-- Indices de la tabla `HistorialMantenimiento`
--
ALTER TABLE `HistorialMantenimiento`
  ADD PRIMARY KEY (`idMantenimiento`),
  ADD KEY `idCamion` (`idCamion`);

--
-- Indices de la tabla `LoginEmpleados`
--
ALTER TABLE `LoginEmpleados`
  ADD PRIMARY KEY (`Rut`),
  ADD KEY `idCamion` (`idCamion`);

--
-- Indices de la tabla `Sensores`
--
ALTER TABLE `Sensores`
  ADD PRIMARY KEY (`idLectura`),
  ADD KEY `idCamion` (`idCamion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `idEquipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `HistorialMantenimiento`
--
ALTER TABLE `HistorialMantenimiento`
  MODIFY `idMantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Sensores`
--
ALTER TABLE `Sensores`
  MODIFY `idLectura` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `HistorialMantenimiento`
--
ALTER TABLE `HistorialMantenimiento`
  ADD CONSTRAINT `HistorialMantenimiento_ibfk_1` FOREIGN KEY (`idCamion`) REFERENCES `Camiones` (`idCamion`);

--
-- Filtros para la tabla `LoginEmpleados`
--
ALTER TABLE `LoginEmpleados`
  ADD CONSTRAINT `LoginEmpleados_ibfk_1` FOREIGN KEY (`idCamion`) REFERENCES `Camiones` (`idCamion`);

--
-- Filtros para la tabla `Sensores`
--
ALTER TABLE `Sensores`
  ADD CONSTRAINT `Sensores_ibfk_1` FOREIGN KEY (`idCamion`) REFERENCES `Camiones` (`idCamion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
