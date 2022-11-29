CREATE TABLE Message 
(
	messageId int primary key auto_increment, 
	message varchar(255) not null
);

Create Table UserAccount
(
	accountId int primary key auto_increment, 
	username varchar(255) not null unique, 
	password varchar(128) not null, 
	enabled boolean not null
);

Create Table Role
(
	roleId int primary key auto_increment, 
	roleName varchar(255) not null unique
);

Create Table UserAccount_Role 
(
	id int primary key auto_increment, 
	accountId int not null, 
	foreign key (accountId) references UserAccount(accountId), 
	roleId int not null, 
	foreign key (roleId) references Role(roleId)
);

