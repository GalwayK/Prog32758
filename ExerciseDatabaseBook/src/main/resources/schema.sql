create table books
(
	isbn int primary key, 
	title varchar(255), 
	author varchar(255),
	price double,
	genre varchar(255)
);

create table genres 
(
	id int primary key auto_increment, 
	genre varchar(255)
);