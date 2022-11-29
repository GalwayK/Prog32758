CREATE TABLE Books (
	isbn bigint auto_increment primary key, 
	title varchar(255) not null, 
	author varchar(255), 
	price decimal(6,2), 
	genre int not null
);