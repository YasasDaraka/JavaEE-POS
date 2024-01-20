CREATE DATABASE Javaee;
use Javaee;
CREATE TABLE customer(
                         cusID varchar(10),
                         cusName varchar(50),
                         cusAddress varchar(50),
                         CONSTRAINT PRIMARY KEY (cusID)
);