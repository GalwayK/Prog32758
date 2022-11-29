CREATE TABLE Users
(
	id int primary key auto_increment, 
	firstName varchar(255) not null, 
	lastName varChar(255) not null, 
	role varChar(255) not null,
	creationDateTime datetime not null, 
	updateDateTime datetime not null
);
