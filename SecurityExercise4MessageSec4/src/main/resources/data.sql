INSERT INTO USERACCOUNT 
(username, password, enabled)
VALUES 
('Reader', '$2a$10$OpARYXO2pG2fqEU8H77A/eY5fZuWCaLUuVD.u37ArgpgC7YYCJIIS', true), 
('Writer', '$2a$10$yXffyCCwmKEO74Tok1eiRehnkrkjqerlFdYNLUjwwaRHm5xOub1P.', true);

INSERT INTO ROLE 
(rolename)
VALUES 
('ROLE_WRITER'), 
('ROLE_READER');

INSERT INTO USERACCOUNT_ROLE 
(accountId, roleId)
VALUES 
(1, 2), 
(2, 1), 
(2, 2);

