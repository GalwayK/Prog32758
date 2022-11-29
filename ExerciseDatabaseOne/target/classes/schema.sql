CREATE TABLE teams (
	id VARCHAR(3) NOT NULL PRIMARY KEY,
	city VARCHAR(255),
	name VARCHAR(255)
);

CREATE TABLE players 
(
	id int primary key not null auto_increment, 
	firstname varchar(255) not null, 
	lastname varchar(255) not null, 
	number int, 
	team varchar(3)
);

--CREATE TABLE Books
--(
--	isbn bigint NOT NULL PRIMARY KEY,
--	title varchar(255) NOT NULL, 
--	author varchar(255),
--	price decimal(6,2), 
--	genre int not null
--);

--CREATE TABLE Genres 
--(
--	id int primary key not null auto_increment, 
--	genre varchar(255) not null
--);