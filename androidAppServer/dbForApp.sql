-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 02, 2015 at 08:41 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbForApp`
--

-- --------------------------------------------------------

--
-- Table structure for table `dentInfo`
--

CREATE TABLE IF NOT EXISTS `dentInfo` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='dentists'' information';

-- --------------------------------------------------------

--
-- Table structure for table `userInfo`
--

CREATE TABLE IF NOT EXISTS `userInfo` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first name` varchar(20) NOT NULL,
  `last name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='db for storing user information';

--
-- Dumping data for table `userInfo`
--

INSERT INTO `userInfo` (`email`, `password`, `first name`, `last name`) VALUES
('Chengyu@umich.edu', 'dummy', 'Chengyu', 'Dai'),
('collinli@umich.edu', 'dummy', 'Guanlin', 'Li'),
('gcbo@umich.edu', 'dummy', 'Bo', 'Zhang'),
('haodong@umich.edu', 'dummy', 'Haodong', 'Shen'),
('haotianw@umich.edu', 'dummy', 'Haotian', 'Wang'),
('Honeyman@umich.edu', 'dummy', 'Peter', 'Honeyman'),
('huilq@umich.edu', 'dummy', 'huiling', 'qiao'),
('jflinn@umich.edu', 'dummy', 'Jason', 'Flinn'),
('Kevin@umich.edu', 'dummy', 'Kaiwen', 'Guo'),
('luzheng@austin.edu', 'adobe', 'Lu', 'Zheng'),
('menshuang@umich.edu', 'dummy', 'shuang', 'men'),
('Siyun@umich.edu', 'dummy', 'siyun', 'chen'),
('smllle@umich.edu', 'dummy', 'Xiaowen', 'Zhao'),
('Tongli@umich.edu', 'dummy', 'tong', 'li'),
('wyc25013@gmail.com', 'dummyPass', 'Uchen', 'Wong'),
('Xiaoqiang@ucla.edu', 'dummy', 'Xiaoqiang', 'Lee'),
('XiaoranD@umich.edu', 'dummy', 'Xiaoran', 'Dong'),
('yichwang@umich.edu', 'dummyPass', 'Yicheng', 'Wang'),
('yunboliu@umich.edu', 'dummy', 'Yunbo', 'Liu'),
('yuzhou@umich.edu', 'dummy', 'Yuzhou', 'Li'),
('Zheli@umich.edu', 'dummy', 'zhe', 'li'),
('ZhenXu@umich.edu', 'dummy', 'Zhen', 'Xu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dentInfo`
--
ALTER TABLE `dentInfo`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `userInfo`
--
ALTER TABLE `userInfo`
  ADD PRIMARY KEY (`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
