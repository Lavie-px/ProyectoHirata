-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 21-06-2026 a las 00:36:06
-- Versión del servidor: 10.6.25-MariaDB-cll-lve
-- Versión de PHP: 8.3.31

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
-- Estructura de tabla para la tabla `HistorialRecorridos`
--

CREATE TABLE `HistorialRecorridos` (
  `IdRecorrido` int(11) NOT NULL,
  `IdCamion` varchar(10) NOT NULL,
  `Fecha` date DEFAULT curdate(),
  `DetalleRecorrido` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Inventario`
--

CREATE TABLE `Inventario` (
  `idComponente` varchar(50) NOT NULL,
  `tipoComponente` varchar(50) DEFAULT NULL,
  `capacidad` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `estado` varchar(30) DEFAULT NULL
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MantenimientoEquipos`
--

CREATE TABLE `MantenimientoEquipos` (
  `idEquipo` varchar(20) NOT NULL,
  `detalles` text DEFAULT NULL,
  `fecha` date DEFAULT curdate(),
  `hora` time DEFAULT curtime()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sucursales`
--

CREATE TABLE `Sucursales` (
  `idSucursal` int(11) NOT NULL,
  `Direccion` varchar(100) DEFAULT NULL
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
-- Indices de la tabla `HistorialRecorridos`
--
ALTER TABLE `HistorialRecorridos`
  ADD PRIMARY KEY (`IdRecorrido`),
  ADD KEY `FK_HistorialRecorridos_Camiones` (`IdCamion`);

--
-- Indices de la tabla `Inventario`
--
ALTER TABLE `Inventario`
  ADD PRIMARY KEY (`idComponente`);

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
-- Indices de la tabla `Sucursales`
--
ALTER TABLE `Sucursales`
  ADD PRIMARY KEY (`idSucursal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `idEquipo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `HistorialMantenimiento`
--
ALTER TABLE `HistorialMantenimiento`
  MODIFY `idMantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `HistorialRecorridos`
--
ALTER TABLE `HistorialRecorridos`
  MODIFY `IdRecorrido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Sensores`
--
ALTER TABLE `Sensores`
  MODIFY `idLectura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Sucursales`
--
ALTER TABLE `Sucursales`
  MODIFY `idSucursal` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `HistorialMantenimiento`
--
ALTER TABLE `HistorialMantenimiento`
  ADD CONSTRAINT `HistorialMantenimiento_ibfk_1` FOREIGN KEY (`idCamion`) REFERENCES `Camiones` (`idCamion`);

--
-- Filtros para la tabla `HistorialRecorridos`
--
ALTER TABLE `HistorialRecorridos`
  ADD CONSTRAINT `FK_HistorialRecorridos_Camiones` FOREIGN KEY (`IdCamion`) REFERENCES `Camiones` (`idCamion`);

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
