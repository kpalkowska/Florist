create table Roles (
id integer identity(1,1) primary key,
name varchar(30) not null unique
);

create table Users (
id integer identity(1,1) primary key,
name varchar(30) not null,
surname varchar(30) not null,
roles_id integer not null references Roles(id)
);

create table Addresses (
id integer identity(1,1) primary key,
street varchar(50) not null,
number varchar(10) not null,
zip_code varchar(6) not null,
city varchar(30) not null,
users_id integer not null references Users(id)
);

create table Orders (
id integer identity(1,1) primary key,
users_id integer not null references Users(id),
addresses_id integer not null references Addresses(id)
);

create table Products (
id integer identity(1,1) primary key,
name varchar(30) not null unique,
type varchar(30)
);

create table Products2Orders (
products_id integer not null references Products(id),
orders_id integer not null references Orders(id),
primary key(products_id, orders_id)
);

drop table Products2Orders;
drop table Products;
drop table Orders;
drop table Addresses;
drop table Users;
drop table Roles;