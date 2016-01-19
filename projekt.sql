create table Roles (
id integer identity(1,1) primary key,
name varchar(30) not null unique
);

create table Addresses (
id integer identity(1,1) primary key,
street varchar(50) not null,
number varchar(10) not null,
zip_code varchar(6) not null,
city varchar(30) not null,
);

create table Users (
id integer identity(1,1) primary key,
name varchar(30) not null,
surname varchar(30) not null,
role integer not null foreign key references Roles(id),
address integer not null foreign key references Addresses(id)
);


create table Orders (
id integer identity(1,1) primary key,
users integer not null foreign key references Users(id),
address integer not null foreign key references Addresses(id)
);

create table Products (
id integer identity(1,1) primary key,
name varchar(30) not null unique,
type varchar(30)
);

create table Products2Orders (
products integer not null foreign key references Products(id),
orders integer not null foreign key references Orders(id),
primary key(products, orders)
);

drop table Products2Orders;
drop table Products;
drop table Orders;
drop table Users;
drop table Addresses;
drop table Roles;