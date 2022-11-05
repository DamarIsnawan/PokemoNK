-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2021 at 02:21 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pokemonk`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `PokemonId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`PokemonId`, `UserId`, `Quantity`) VALUES
(14, 3, 0),
(10, 3, 0),
(1, 2, 20),
(5, 2, 12);

-- --------------------------------------------------------

--
-- Table structure for table `detailtransaction`
--

CREATE TABLE `detailtransaction` (
  `TransactionId` int(11) NOT NULL,
  `PokemonId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailtransaction`
--

INSERT INTO `detailtransaction` (`TransactionId`, `PokemonId`, `Quantity`) VALUES
(1, 1, 2),
(1, 15, 3),
(1, 1, 2),
(1, 16, 2),
(1, 12, 2),
(1, 13, 2),
(1, 24, 2),
(1, 26, 1),
(1, 25, 1),
(1, 27, 1),
(1, 28, 1),
(1, 11, 2),
(1, 14, 1),
(1, 20, 1),
(1, 21, 1),
(1, 22, 1),
(1, 23, 1),
(1, 4, 1),
(1, 5, 1),
(1, 6, 1),
(1, 7, 1),
(1, 8, 1),
(1, 9, 1),
(1, 10, 1),
(2, 1, 1),
(3, 25, 5),
(3, 22, 2),
(4, 12, 55),
(5, 25, 22),
(6, 10, 22),
(7, 20, 15),
(7, 1, 545),
(8, 25, 22),
(9, 11, 111),
(10, 12, 31),
(11, 11, 321),
(12, 12, 32);

-- --------------------------------------------------------

--
-- Table structure for table `headertransaction`
--

CREATE TABLE `headertransaction` (
  `TransactionId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `headertransaction`
--

INSERT INTO `headertransaction` (`TransactionId`, `UserId`, `Time`) VALUES
(1, 1, '2021-05-29 10:50:05'),
(2, 1, '2021-05-29 10:56:26'),
(3, 1, '2021-05-29 11:00:06'),
(4, 1, '2021-05-29 11:00:36'),
(5, 1, '2021-05-29 11:00:46'),
(6, 1, '2021-05-29 11:00:55'),
(7, 1, '2021-05-29 11:01:13'),
(8, 1, '2021-05-29 11:01:19'),
(9, 1, '2021-05-29 11:01:28'),
(10, 1, '2021-05-29 11:01:38'),
(11, 1, '2021-05-29 11:01:53'),
(12, 1, '2021-05-29 11:02:04');

-- --------------------------------------------------------

--
-- Table structure for table `pokemon`
--

CREATE TABLE `pokemon` (
  `PokemonId` int(11) NOT NULL,
  `PokemonName` varchar(20) NOT NULL,
  `PokemonLevel` int(11) NOT NULL,
  `PokemonType` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pokemon`
--

INSERT INTO `pokemon` (`PokemonId`, `PokemonName`, `PokemonLevel`, `PokemonType`) VALUES
(3, 'Miltank', 10, 'Normal'),
(4, 'Golduck', 20, 'Water'),
(5, 'Butterfree', 20, 'Bug'),
(6, 'Seal', 15, 'Ice'),
(7, 'Dragonair', 45, 'Dragon'),
(8, 'Dragonite', 60, 'Fairy'),
(9, 'Bulbasaur', 5, 'Grass'),
(10, 'Gengar', 55, 'Ghost'),
(11, 'Gyarados', 70, 'Dragon'),
(12, 'Charizard', 55, 'Fire'),
(13, 'Machamp', 55, 'Fighting'),
(14, 'Lucario', 45, 'Fighting'),
(15, 'Blaziken', 20, 'Fire'),
(16, 'Ho-oh', 75, 'Legendary'),
(20, 'Rayquaza', 70, 'Dragon'),
(21, 'Fearow', 34, 'Flying'),
(22, 'Zubat', 15, 'Poison'),
(23, 'Dugtrio', 65, 'Ground'),
(24, 'Poliwag', 21, 'Water'),
(25, 'Tentacool', 32, 'Water'),
(26, 'Slowpoke', 28, 'Water'),
(27, 'Doduo', 13, 'Normal'),
(28, 'Shellder', 23, 'Water'),
(29, 'Pikachu', 15, 'Thunder'),
(30, 'Raichu', 34, 'Thunder'),
(31, 'Nidoking', 43, 'Poison'),
(32, 'Golem', 55, 'Ground'),
(33, 'Grimer', 25, 'Poison'),
(34, 'Cubone', 15, 'Ground'),
(35, 'Weezing', 67, 'Poison');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserId` int(11) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Age` int(11) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserId`, `Username`, `Name`, `Age`, `Email`, `Gender`, `Password`) VALUES
(1, 'alpha2121', 'adewirya', 22, 'adewirya6@gmail.com', 'male', 'tes'),
(3, 'danielfujiono', 'daniel fuji', 24, 'daniel@gmail.com', 'Male', 'daniel'),
(5, 'adewirya', 'ade wirya', 20, 'adewirya6@gmail.com', 'Male', 'tes123'),
(6, 'tes12345', 'ade wirya', 20, 'adewirya6@gmail.com', 'Male', 'tes123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `headertransaction`
--
ALTER TABLE `headertransaction`
  ADD PRIMARY KEY (`TransactionId`);

--
-- Indexes for table `pokemon`
--
ALTER TABLE `pokemon`
  ADD PRIMARY KEY (`PokemonId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `headertransaction`
--
ALTER TABLE `headertransaction`
  MODIFY `TransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `pokemon`
--
ALTER TABLE `pokemon`
  MODIFY `PokemonId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
