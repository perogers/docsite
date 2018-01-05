-- Authorities
INSERT INTO authority (city,county, state) VALUES ('', 'Dekalb', 'GA');
INSERT INTO authority (city,county, state) VALUES ('Cumming', 'Forsythe', 'GA');
INSERT INTO authority (city,county, state) VALUES ('', 'Fulton', 'GA');
INSERT INTO authority (city,county, state) VALUES ('', 'Cannon', 'TN');
INSERT INTO authority (city,county, state) VALUES ('New Orleans', 'County', 'LA');


-- Users
INSERT INTO user (user_id, first_name, last_name, user_type, user_status) VALUES ('br549', 'Joe', 'Cool', 'ADMIN', 'ACTIVE');
INSERT INTO user (user_id, first_name, last_name, user_type, user_status) VALUES ('xxyzzy', 'Jill', 'Jilly', 'ADMIN', 'ACTIVE');
INSERT INTO user (user_id, first_name, last_name, user_type, user_status) VALUES ('hithere', 'Bob', 'Roberts', 'CUSTOMER','REMOVED');
INSERT INTO user (user_id, first_name, last_name, user_type, user_status) VALUES ('lalala', 'Shelia', 'Square','CUSTOMER', 'ACTIVE');