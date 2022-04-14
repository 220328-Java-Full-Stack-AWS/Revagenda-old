/*
 * DML - insert test data
 */

INSERT INTO users (first_name, last_name, username, password, email) VALUES ('Kyle', 'Plummer', 'kplummer', 'P4ssw0rd!', 'kyle.plummer@revature.com');
INSERT INTO users (first_name, last_name, username, password, email) VALUES ('Stan', 'Savelev', 'ssavelev', 'P4ssw0rd!', 'stan.savelev@revature.net');

INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('bring car to jiffylube', '2022-05-01', false, 1);
INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('get car inspected', '2022-05-01', false, 1);
INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('pay rent', '2022-04-01', true, 1);