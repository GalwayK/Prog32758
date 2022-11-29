CREATE TABLE Users
(
	userId bigint primary key auto_Increment,
	email varchar(75) not null unique, 
	encrypted_Password varchar(128) not null,
	isEnabled boolean not null
);

CREATE TABLE Roles 
(
	roleId bigInt primary key auto_Increment, 
	roleName varchar(30) not null unique
);

CREATE TABLE Users_Roles
(
	id bigint primary key auto_increment, 
	userid bigint not null, 
	roleid bigint not null, 
	CONSTRAINT userid_fk 
	FOREIGN KEY (userid) references Users(userid),
	CONSTRAINT roleid_fk
	FOREIGN key (roleid) references Roles(roleid)
);
