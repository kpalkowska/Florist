create table Roles (
id integer identity(1,1) primary key,
role varchar(30) not null
);

create table Addresses (
id integer identity(1,1) primary key,
street varchar(50) not null,
number varchar(10) not null,
zipKode varchar(6) not null,
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
description varchar(30),
price varchar(30) not null
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
