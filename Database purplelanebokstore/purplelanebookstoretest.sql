-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2021 at 07:29 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `purplelanebookstoretest`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductId` int(11) NOT NULL,
  `ProductName` varchar(100) NOT NULL,
  `ProductAuthor` varchar(100) NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `ProductStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductId`, `ProductName`, `ProductAuthor`, `ProductPrice`, `ProductStock`) VALUES
(1, 'Da Vinci Code,The', 'Brown, Dan', 249000, 30),
(2, 'Harry Potter and the Deathly Hallows', 'Rowling, J.K.', 449000, 10),
(3, 'Harry Potter and the Philosopher\'s Stone', 'Rowling, J.K.', 389000, 15),
(4, 'Harry Potter and the Order of the Phoenix', 'Rowling, J.K.', 550000, 24),
(5, 'Fifty Shades of Grey', 'James, E. L.', 330000, 50),
(6, 'Harry Potter and the Goblet of Fire', 'Rowling, J.K.', 500000, 6),
(7, 'Harry Potter and the Chamber of Secrets', 'Rowling, J.K.', 469000, 12),
(8, 'Harry Potter and the Prisoner of Azkaban', 'Rowling, J.K.', 500000, 21),
(9, 'Angels and Demons', 'Brown, Dan', 670000, 7),
(10, 'Harry Potter and the Half-blood Prince:Children\'s Edition', 'Rowling, J.K.', 570000, 5),
(11, 'Fifty Shades Darker', 'James, E. L.', 300000, 16),
(12, 'Twilight', 'Meyer, Stephenie', 220000, 15),
(13, 'Fifty Shades Freed', 'James, E. L.', 360000, 69),
(14, 'Lost Symbol,The', 'Brown, Dan', 450000, 12),
(15, 'New Moon', 'Meyer, Stephenie', 50000, 22),
(16, 'Deception Point', 'Brown, Dan', 444000, 44),
(17, 'Eclipse', 'Meyer, Stephenie', 600000, 3),
(18, 'Lovely Bones,The', 'Sebold, Alice', 450000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `promos`
--

CREATE TABLE `promos` (
  `PromoId` int(11) NOT NULL,
  `PromoCode` varchar(20) NOT NULL,
  `PromoDiscount` int(11) NOT NULL,
  `PromoNote` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promos`
--

INSERT INTO `promos` (`PromoId`, `PromoCode`, `PromoDiscount`, `PromoNote`) VALUES
(1, 'DISKON50', 50000, 'Minimal spend of 100K to get 50K off'),
(2, 'BUY1GET1', 100000, 'Minimal spend of 250K to get 100K off'),
(3, 'HEMAT30', 30000, '30K off for Gojek and Grab');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `RoleId` int(11) NOT NULL,
  `RoleType` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`RoleId`, `RoleType`) VALUES
(1, 'Manager'),
(2, 'Admin'),
(3, 'PromotionTeam'),
(4, 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `TransactionId` int(11) NOT NULL,
  `ProductQuantity` int(11) NOT NULL,
  `PaymentType` varchar(20) NOT NULL,
  `CardNumber` int(11) NOT NULL,
  `PromoCode` varchar(20) NOT NULL,
  `ProductId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transactionsreport`
--

CREATE TABLE `transactionsreport` (
  `month` date DEFAULT NULL,
  `year` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `RoleId`, `Email`, `Username`, `Password`) VALUES
(1, 1, 'Manager@gmail.com', 'MAN001', 'MAN001'),
(2, 2, 'Admin@gmail.com', 'ADMIN001', 'ADMIN001'),
(3, 3, 'PromoTeam@gmail.com', 'PROMT001', 'PROMT001'),
(4, 4, 'Rosie@gmail.com', 'Rosie', 'Rosie1122'),
(5, 2, 'Admin2@gmail.com', 'ADMIN002', 'ADMIN002'),
(6, 3, 'PromoTeam@gmail.com', 'PROMT001', 'PROMT001');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductId`);

--
-- Indexes for table `promos`
--
ALTER TABLE `promos`
  ADD PRIMARY KEY (`PromoId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RoleId`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TransactionId`),
  ADD KEY `ProductId` (`ProductId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `RoleId` (`RoleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ProductId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `promos`
--
ALTER TABLE `promos`
  MODIFY `PromoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `RoleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `TransactionId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`ProductId`) REFERENCES `products` (`ProductId`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
