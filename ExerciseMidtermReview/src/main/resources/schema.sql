create table videogames 
(
id int primary key auto_increment, 
title varchar(255) not null, 
developer varchar(255) not null, 
genre varchar(255),
releaseDate date not null
);

create table genres 
(
	id int primary key auto_increment, 
	genre varchar(255)
);
