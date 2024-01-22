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
CREATE TABLE order(
                         oid varchar(10),
                         date date ,
                         cusID varchar(10),
                         CONSTRAINT PRIMARY KEY (oid),
                         CONSTRAINT FOREIGN KEY (cusID) REFERENCES customer(cusID)
                         ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE orderDetails(
                         oid varchar(10),
                         itmCode varchar(10),
                         itmQTY int(10),
                         itmPrice decimal(10,2),
                         CONSTRAINT FOREIGN KEY (oid) REFERENCES order(oid)
                         ON UPDATE CASCADE ON DELETE CASCADE,
                         CONSTRAINT FOREIGN KEY (itmCode) REFERENCES item(itmCode)
                         ON UPDATE CASCADE ON DELETE CASCADE
);