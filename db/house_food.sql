-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2022 at 10:30 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `house_food`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_user`
--

CREATE TABLE `data_user` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `kedudukan` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_user`
--

INSERT INTO `data_user` (`username`, `password`, `nama_user`, `kedudukan`) VALUES
('haji1', '123', 'andi', 'ADMIN'),
('hanjime', 'guamaulogin', 'haris', 'ADMIN'),
('yuyun2', 'root', 'tio', 'KARYAWAN');

-- --------------------------------------------------------

--
-- Table structure for table `detail_menu`
--

CREATE TABLE `detail_menu` (
  `id_jenis` varchar(15) NOT NULL,
  `jenis_menu` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_menu`
--

INSERT INTO `detail_menu` (`id_jenis`, `jenis_menu`) VALUES
('MK01', 'Makanan'),
('MN01', 'Minuman');

-- --------------------------------------------------------

--
-- Table structure for table `detail_pemesanan`
--

CREATE TABLE `detail_pemesanan` (
  `nota_pemesanan` varchar(15) DEFAULT NULL,
  `id_menu` varchar(15) DEFAULT NULL,
  `sub_total` float DEFAULT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_pemesanan`
--

INSERT INTO `detail_pemesanan` (`nota_pemesanan`, `id_menu`, `sub_total`, `qty`) VALUES
('P0001', '123456789102', 200000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `nota_penjualan` varchar(15) NOT NULL,
  `id_menu` varchar(15) NOT NULL,
  `sub_total` float DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`nota_penjualan`, `id_menu`, `sub_total`, `jumlah`) VALUES
('P0010', '123456789102', 40000, 2),
('P0011', '123456789102', 40000, 2),
('P0012', '123456789102', 40000, 2),
('P0018', '123456789102', 20000, 1),
('P0022', '123456789102', 760000, 38),
('P0022', '123456789102', 20000, 1),
('P0025', '123456789102', 820000, 41),
('P0066', '123456789102', 20000, 1);

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `stok_edit` AFTER UPDATE ON `detail_penjualan` FOR EACH ROW BEGIN
UPDATE menu SET stok = stok + OLD.jumlah, 
stok = stok - NEW.jumlah
WHERE id_menu=OLD.id_menu;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_kurang` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
UPDATE menu set stok = stok-new.jumlah
WHERE id_menu = new.id_menu; END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_tambah` AFTER DELETE ON `detail_penjualan` FOR EACH ROW BEGIN
UPDATE menu SET stok = stok + OLD.jumlah
WHERE id_menu=OLD.id_menu;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id_menu` varchar(15) NOT NULL,
  `nama_menu` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `id_jenis` varchar(15) DEFAULT NULL,
  `Barcode` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_menu`, `nama_menu`, `harga`, `stok`, `id_jenis`, `Barcode`) VALUES
('123456789102', 'Ayam Bakar', 20000, 0, 'MK01', 'C://drivers//Ayam Bakar.png'),
('135264738935', 'ayam penyet', 15000, 8, 'MK01', 'C://drivers//ayam penyet.png'),
('161400530574', 'teh sosro', 7000, 47, 'MN01', 'C://drivers//teh sosro.png'),
('322633277134', 'tempe penyet', 5000, 21, 'MK01', 'C://drivers//tempe penyet.png'),
('8991002121010', 'good day', 5000, 5, 'MN01', 'C://drivers//good day.png');

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `id_pembeli` varchar(15) NOT NULL,
  `nama_pembeli` varchar(50) DEFAULT NULL,
  `alamat_pembeli` varchar(50) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`id_pembeli`, `nama_pembeli`, `alamat_pembeli`, `no_telp`) VALUES
('R0001', 'haji', 'mekah', '9886786983'),
('R0002', 'jujun', 'bali', '0897767'),
('R0003', 'jajang', 'nujum', '6487689'),
('R0004', 'dad', 'ree', '5434'),
('R0005', 'haha', 'iuyy', '46578687'),
('R0006', 'jiji', 'fhjgjs', '546587'),
('R0007', 'gugun', 'hfkde', '234235'),
('R0008', 'nunung', 'gskjds', '67487'),
('R0009', 'jajang', 'hongaria', '75875678'),
('R0010', 'huhu', 'jember', '65876844'),
('R0011', 'ajeng', 'kalimantan', '665876'),
('R0012', 'anyong', 'korea', '587687'),
('R0013', 'gabi', 'gujarat', '34234324'),
('R0014', 'dewangga', 'penge beli truck', '74873248932'),
('R0015', 'juju', 'surga', '984934');

-- --------------------------------------------------------

--
-- Table structure for table `pemesanan`
--

CREATE TABLE `pemesanan` (
  `nota_pemesanan` varchar(15) NOT NULL,
  `tgl_pemesanan` date DEFAULT NULL,
  `tgl_pengambilan_pesanan` date DEFAULT NULL,
  `gtotal` float DEFAULT NULL,
  `bayar` float DEFAULT NULL,
  `keterangan` varchar(15) DEFAULT NULL,
  `kembalian` float DEFAULT NULL,
  `status_pengambilan` varchar(15) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `id_pembeli` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pemesanan`
--

INSERT INTO `pemesanan` (`nota_pemesanan`, `tgl_pemesanan`, `tgl_pengambilan_pesanan`, `gtotal`, `bayar`, `keterangan`, `kembalian`, `status_pengambilan`, `username`, `id_pembeli`) VALUES
('P0001', '2022-06-15', '2022-06-18', 200000, 200000, 'lunas', 0, 'sudah diambil', 'hanjime', 'R0015');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `nota_penjualan` varchar(15) NOT NULL,
  `tgl_penjualan` date DEFAULT NULL,
  `grand_total` float DEFAULT NULL,
  `bayar` float DEFAULT NULL,
  `kembalian` float DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`nota_penjualan`, `tgl_penjualan`, `grand_total`, `bayar`, `kembalian`, `username`) VALUES
('P0001', NULL, NULL, NULL, NULL, NULL),
('P0002', NULL, NULL, NULL, NULL, NULL),
('P0003', NULL, NULL, NULL, NULL, NULL),
('P0004', NULL, NULL, NULL, NULL, NULL),
('P0006', NULL, NULL, NULL, NULL, NULL),
('P0007', NULL, NULL, NULL, NULL, NULL),
('P0008', NULL, NULL, NULL, NULL, NULL),
('P0009', NULL, NULL, NULL, NULL, NULL),
('P0010', NULL, NULL, NULL, NULL, NULL),
('P0011', NULL, NULL, NULL, NULL, NULL),
('P0012', NULL, NULL, NULL, NULL, NULL),
('P0018', NULL, NULL, NULL, NULL, NULL),
('P0021', NULL, NULL, NULL, NULL, NULL),
('P0022', NULL, NULL, NULL, NULL, NULL),
('P0023', NULL, NULL, NULL, NULL, NULL),
('P0024', NULL, NULL, NULL, NULL, NULL),
('P0025', NULL, NULL, NULL, NULL, NULL),
('P0026', NULL, NULL, NULL, NULL, NULL),
('P0027', NULL, NULL, NULL, NULL, NULL),
('P0057', NULL, NULL, NULL, NULL, NULL),
('P0058', NULL, NULL, NULL, NULL, NULL),
('P0066', NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_user`
--
ALTER TABLE `data_user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `detail_menu`
--
ALTER TABLE `detail_menu`
  ADD PRIMARY KEY (`id_jenis`);

--
-- Indexes for table `detail_pemesanan`
--
ALTER TABLE `detail_pemesanan`
  ADD KEY `nota_pemesanan` (`nota_pemesanan`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD KEY `id_menu` (`id_menu`),
  ADD KEY `nota_penjualan` (`nota_penjualan`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`),
  ADD KEY `id_jenis` (`id_jenis`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id_pembeli`);

--
-- Indexes for table `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD PRIMARY KEY (`nota_pemesanan`),
  ADD KEY `username` (`username`),
  ADD KEY `id_pembeli` (`id_pembeli`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`nota_penjualan`),
  ADD KEY `username` (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pemesanan`
--
ALTER TABLE `detail_pemesanan`
  ADD CONSTRAINT `detail_pemesanan_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_pemesanan_ibfk_3` FOREIGN KEY (`nota_pemesanan`) REFERENCES `pemesanan` (`nota_pemesanan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_penjualan_ibfk_3` FOREIGN KEY (`nota_penjualan`) REFERENCES `penjualan` (`nota_penjualan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_jenis`) REFERENCES `detail_menu` (`id_jenis`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pemesanan`
--
ALTER TABLE `pemesanan`
  ADD CONSTRAINT `pemesanan_ibfk_1` FOREIGN KEY (`username`) REFERENCES `data_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pemesanan_ibfk_2` FOREIGN KEY (`id_pembeli`) REFERENCES `pembeli` (`id_pembeli`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`username`) REFERENCES `data_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
