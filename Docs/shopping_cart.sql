-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.41 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for shopping_cart
DROP DATABASE IF EXISTS `shopping_cart`;
CREATE DATABASE IF NOT EXISTS `shopping_cart` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shopping_cart`;

-- Dumping structure for table shopping_cart.cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`) USING BTREE,
  CONSTRAINT `fk_customer_id_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table shopping_cart.cart: ~2 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table shopping_cart.cart_products
DROP TABLE IF EXISTS `cart_products`;
CREATE TABLE IF NOT EXISTS `cart_products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cart_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `product_price` decimal(10,2) DEFAULT '0.00',
  `product_vat` decimal(10,2) DEFAULT '0.00',
  `product_shipping_fee` decimal(10,2) DEFAULT '0.00',
  `created_by` varchar(50) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_id_product_id` (`product_id`),
  KEY `fk_cart_id_cart_id` (`cart_id`),
  CONSTRAINT `fk_cart_id_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `fk_product_id_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table shopping_cart.cart_products: ~3 rows (approximately)
/*!40000 ALTER TABLE `cart_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_products` ENABLE KEYS */;

-- Dumping structure for table shopping_cart.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_first_name` varchar(255) DEFAULT NULL,
  `customer_last_name` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table shopping_cart.customer: ~2 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table shopping_cart.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_price` decimal(19,2) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_title` bigint(20) DEFAULT NULL,
  `product_tax` decimal(19,2) DEFAULT NULL,
  `product_quantity` decimal(19,2) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_title_product_title_id` (`product_title`),
  CONSTRAINT `fk_product_title_product_title_id` FOREIGN KEY (`product_title`) REFERENCES `product_title` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table shopping_cart.product: ~7 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `product_price`, `product_name`, `product_title`, `product_tax`, `product_quantity`, `created_by`, `created_date_time`, `modified_by`, `modified_date_time`) VALUES
	(1, 100.50, 'KITKAT', 1, 1.55, 100.00, 'SYSTEM', '2021-06-20 01:57:52', 'SYSTEM', '2021-06-27 18:01:12'),
	(2, 150.55, 'OREO', 2, 1.35, 100.00, 'SYSTEM', '2021-06-20 04:36:08', 'SYSTEM', '2021-06-27 18:01:12'),
	(3, 300.50, 'ANCHOR', 2, 1.55, 100.00, 'SYSTEM', '2021-06-20 01:57:52', 'SYSTEM', '2021-06-27 17:59:53'),
	(4, 150.55, 'MILO', 2, 1.35, 100.00, 'SYSTEM', '2021-06-20 04:39:52', 'SYSTEM', '2021-06-26 15:49:08'),
	(5, 125.36, 'BURBON', 2, 1.55, 100.00, 'SYSTEM', '2021-06-20 10:12:02', 'SYSTEM', '2021-06-26 15:49:08'),
	(6, 75.00, 'KANDOS', 1, 1.33, 100.00, 'SYSTEM', '2021-06-20 11:01:34', 'SYSTEM', '2021-06-26 13:57:49'),
	(7, 100.00, 'CREAMY', 2, 3.20, 100.00, 'SYSTEM', '2021-06-27 02:29:52', NULL, NULL),
	(8, 125.00, 'NEWDALE', 2, 1.20, 100.00, 'SYSTEM', '2021-06-27 18:03:11', NULL, NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Dumping structure for table shopping_cart.product_title
DROP TABLE IF EXISTS `product_title`;
CREATE TABLE IF NOT EXISTS `product_title` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_title_name` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_category_name` (`product_title_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table shopping_cart.product_title: ~3 rows (approximately)
/*!40000 ALTER TABLE `product_title` DISABLE KEYS */;
INSERT INTO `product_title` (`id`, `product_title_name`, `created_by`, `created_date_time`, `modified_by`, `modified_date_time`) VALUES
	(1, 'CHOCOLATE', 'SYSTEM', '2021-06-20 01:32:01', NULL, NULL),
	(2, 'MILK', 'SYSTEM', '2021-06-20 01:32:01', NULL, NULL),
	(3, 'BISCUIT', 'SYSTEM', '2021-06-20 01:32:02', NULL, NULL);
/*!40000 ALTER TABLE `product_title` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
