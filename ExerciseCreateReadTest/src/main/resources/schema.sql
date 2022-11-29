CREATE TABLE Players 
(
	id int auto_increment primary key, 
	firstName varchar(255), 
	lastName varchar(255), 
	team varchar(255), 
	number int
);

CREATE TABLE Teams
(
	id varchar(255) primary key,
	city varchar(255),
	name varchar(255)
);
