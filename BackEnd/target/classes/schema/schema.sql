CREATE DATABASE Javaee;
use Javaee;
CREATE TABLE customer(
                         cusID varchar(10),
                         cusName varchar(50),
                         cusAddress varchar(50),
                         CONSTRAINT PRIMARY KEY (cusID)
);
CREATE TABLE item(
                         itmCode varchar(10),
                         itmName varchar(50),
                         itmPrice decimal(10,2),
                         itmQTY int(10),
                         CONSTRAINT PRIMARY KEY (itmCode)
);