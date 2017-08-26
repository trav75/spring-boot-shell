INSERT INTO app_role (id, role_name, description) VALUES (1, 'ROLE_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ROLE_ADMIN', 'Admin User - Has permission to perform admin tasks');

-- USER (JWT, STATELESS)
-- non-encrypted password: jwtpass
-- SHA 256
-- INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'John', 'Doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe');
-- INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin');
-- BCRYPT (strength: 10)
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (1, 'John', 'Doe', '$2a$10$ZvcuuTEgaxr8mpRij8Syi.ooUsZWZ9xR7IvOeZJ8IX1t9Vch4Dmae', 'john.doe', 'jwtuser@here.com');
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (2, 'Admin', 'Admin', '$2a$10$ZvcuuTEgaxr8mpRij8Syi.ooUsZWZ9xR7IvOeZJ8IX1t9Vch4Dmae', 'admin.admin', 'jwtadmin@here.com');

-- USER (Cookie, SESSION)
-- non-encrypted password: cookiepass
-- BCRYPT (strength: 10)
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (3, 'Jack', 'Black', '$2a$10$bT33mhWZttR4X.s7nOWiCu6Zr/wSTua985VBrTCITNYX7G775BPby', 'user', 'cookieuser@here.com');
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (4, 'Admin2', 'Admin2', '$2a$10$bT33mhWZttR4X.s7nOWiCu6Zr/wSTua985VBrTCITNYX7G775BPby', 'admin', 'cookieadmin@here.com');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

INSERT INTO user_role(user_id, role_id) VALUES(3,1);
INSERT INTO user_role(user_id, role_id) VALUES(4,1);
INSERT INTO user_role(user_id, role_id) VALUES(4,2);

-- Populate random city table

INSERT INTO random_city(id, name) VALUES (1, 'Bamako');
INSERT INTO random_city(id, name) VALUES (2, 'Nonkon');
INSERT INTO random_city(id, name) VALUES (3, 'Houston');
INSERT INTO random_city(id, name) VALUES (4, 'Toronto');
INSERT INTO random_city(id, name) VALUES (5, 'New York');
INSERT INTO random_city(id, name) VALUES (6, 'Mopti');
INSERT INTO random_city(id, name) VALUES (7, 'Koulikoro');
INSERT INTO random_city(id, name) VALUES (8, 'Moscow');