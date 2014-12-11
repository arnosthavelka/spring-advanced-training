-- Cities
insert into city(id, country, name, state) values (100, 'Czech Republic', 'Prague', '')
insert into city(id, country, name, state) values (101, 'Spain', 'Madrid', 'Madrid')
insert into city(id, country, name, state) values (102, 'Spain', 'Barcelona', 'Catalunya')
insert into city(id, country, name, state) values (103, 'Switzerland', 'Bern', '')
insert into city(id, country, name, state) values (104, 'UK', 'London', '')
insert into city(id, country, name, state) values (105, 'USA', 'Los Angeles', 'LA')
-- Users
insert into user(id, name, city_id, email) values (1, 'Arny', 100, 'arnost.havelka@gmail.com')
insert into user(id, name, city_id, email) values (2, 'Antonio Banderas', 105, 'antonio.banderas@dummy.com')
insert into user(id, name, city_id, email) values (3, 'Mr. Bean', 104, 'ron.atkinson@dummy.com')