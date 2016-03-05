create table Roles (
id integer identity(1,1) primary key,
role varchar(30) not null unique
);

create table Addresses (
id integer identity(1,1) primary key,
street varchar(50) not null,
number varchar(10) not null,
zipCode varchar(6) not null,
city varchar(30) not null,
);

create table Users (
id integer identity(1,1) primary key,
name varchar(30) not null,
surname varchar(30) not null,
login varchar(30) not null,
password varchar(100) not null,
role integer not null foreign key references Roles(id),
address integer not null foreign key references Addresses(id)
);


create table Orders (
id integer identity(1,1) primary key,
date varchar(10) not null,
users integer not null foreign key references Users(id),
address integer foreign key references Addresses(id)
);

create table Products (
id integer identity(1,1) primary key,
name varchar(30) not null,
description varchar(max),
price varchar(30) not null,
type varchar(30) not null,
color varchar(30) not null,
foto image
);

create table Products2Orders (
id integer identity(1,1) primary key,
product integer not null foreign key references Products(id),
orders integer not null foreign key references Orders(id),
);

drop table Products2Orders;
drop table Products;
drop table Orders;
drop table Users;
drop table Addresses;
drop table Roles;


INSERT INTO Roles VALUES('Admin');
INSERT INTO Roles VALUES('User');
INSERT INTO Roles VALUES('Other');

INSERT INTO Addresses VALUES('Grunwaldzka', 15, '80-300', 'Gdańsk');
INSERT INTO Addresses VALUES('podwale Grodzkie', 100, '80-200', 'Gdańsk');
INSERT INTO Addresses VALUES('Grunwaldzka', 15, '80-200', 'Sopot');


INSERT INTO Users VALUES('Jan', 'Kowalski', 'janek', '123', 1, 1);
INSERT INTO Users VALUES('Adam', 'Nowak', 'adamo', '123', 2, 2);
INSERT INTO Users VALUES('Krzysztof', 'Kowalski', 'krzys', '123', 3, 1);

INSERT INTO Orders VALUES('20-02-2016', 1, 1);
INSERT INTO Orders VALUES('20-02-2016', 2, 1);
INSERT INTO Orders VALUES('20-02-2016', 3, 3);

INSERT INTO Products VALUES('rose', '30 roses with bow', '200', 'bouquet', 'red', null);
INSERT INTO Products VALUES('rose', '6 roses with bow', '2.99', 'bouquet', 'pink', null);
INSERT INTO Products(name, price, type, color, foto) VALUES('rose', '5', 'single', 'red', null);
INSERT INTO Products VALUES('tulip', '12 tulips with ribbon and pearls', '50', 'bouquet', 'red', null);
INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'pink', null);
INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'white', null);
INSERT INTO Products(name, price, type, color, foto) VALUES('tulip', '6', 'single', 'red', null);
INSERT INTO Products VALUES('violet', 'violet composition', '120', 'potted', 'multicolour', null);
INSERT INTO Products VALUES('hyacinth', 'hyacith composition', '107', 'potted', 'multicolour', null);
INSERT INTO Products VALUES('orchid', 'orchid in a pot', '139', 'potted', 'white', null);


INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(1,2);
INSERT INTO Products2Orders VALUES(2,2);
INSERT INTO Products2Orders VALUES(2,2);
INSERT INTO Products2Orders VALUES(2,2);
INSERT INTO Products2Orders VALUES(2,2);
INSERT INTO Products2Orders VALUES(2,2);
INSERT INTO Products2Orders VALUES(3,2);
INSERT INTO Products2Orders VALUES(3,2);
INSERT INTO Products2Orders VALUES(3,2);
INSERT INTO Products2Orders VALUES(1,3);
INSERT INTO Products2Orders VALUES(1,3);
INSERT INTO Products2Orders VALUES(1,3);
INSERT INTO Products2Orders VALUES(1,3);
