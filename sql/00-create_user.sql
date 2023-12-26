DROP USER IF EXISTS 'cruddemo'@'%';
CREATE USER 'cruddemo'@'%' IDENTIFIED BY 'cruddemo';
GRANT ALL PRIVILEGES ON *.* TO 'cruddemo'@'%';