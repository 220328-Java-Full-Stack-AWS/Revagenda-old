/*
 * DDL, drop and re-create the database schema
 */

DROP TABLE IF EXISTS to_do_items;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
	user_id serial PRIMARY KEY,
	first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	username VARCHAR(40) NOT NULL UNIQUE,
	password VARCHAR(40) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE
);

CREATE INDEX users_username_index ON users (username);
--DROP INDEX users_username_index

CREATE TABLE IF NOT EXISTS to_do_items (
	item_id SERIAL,
	task VARCHAR(600) NOT NULL,
	due DATE NOT NULL,
	completed BOOL NOT NULL,
	user_id INT NOT NULL,
	PRIMARY KEY (item_id),
	CONSTRAINT fk_to_do_items_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE INDEX to_do_items_fk_index ON to_do_items (user_id);