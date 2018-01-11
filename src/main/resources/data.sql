-- Authorities
INSERT INTO authority (city,county, state, url, agency) VALUES ('', 'Dekalb', 'GA', 'https://www.delakb.ga.gov', 'DFACS');
INSERT INTO authority (city,county, state, url, agency) VALUES ('Cumming', 'Forsythe', 'GA', 'https://www.forsyth.ga.gov', 'DFACS');
INSERT INTO authority (city,county, state, url, agency) VALUES ('', 'Fulton', 'GA', 'https://www.fulton.ga.gov', 'DFACS');
INSERT INTO authority (city,county, state, url, agency) VALUES ('', 'Cannon', 'TN', 'https://www.cannon.tn.gov', 'DFACS');
INSERT INTO authority (city,county, state, url, agency) VALUES ('New Orleans', 'County', 'LA', 'https://www.nola.la.gov', 'DFACS');


-- Users
INSERT INTO users (user_id, first_name, last_name, user_type, user_status) VALUES ('br549', 'Joe', 'Cool', 'ADMIN', 'ACTIVE');
INSERT INTO users (user_id, first_name, last_name, user_type, user_status) VALUES ('xxyzzy', 'Jill', 'Jilly', 'ADMIN', 'ACTIVE');
INSERT INTO users (user_id, first_name, last_name, user_type, user_status) VALUES ('hithere', 'Bob', 'Roberts', 'CUSTOMER','REMOVED');
INSERT INTO users (user_id, first_name, last_name, user_type, user_status) VALUES ('lalala', 'Shelia', 'Square','CUSTOMER', 'ACTIVE');