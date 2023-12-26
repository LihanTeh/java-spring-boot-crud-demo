USE `crud_demo`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_number` int NOT NULL AUTO_INCREMENT,
  `account_type` varchar(45) DEFAULT NULL,
  `account_name` varchar(45) DEFAULT NULL,
  `account_balance` numeric(19,4) DEFAULT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `account` int DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `FK_ACCOUNT` FOREIGN KEY (`account`) REFERENCES `account` (`account_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;