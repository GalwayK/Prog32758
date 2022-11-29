create table Students 
(
	id int primary key auto_increment, 
	firstName varchar(255) not null, 
	lastName varchar(255) not null, 
	program varchar(255) not null, 
	schoolYear int not null, 
	coop boolean not null, 
	internship boolean not null
);