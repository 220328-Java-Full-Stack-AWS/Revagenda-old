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



/*
 * DML - insert test data
 */

INSERT INTO users (first_name, last_name, username, password, email) VALUES ('Kyle', 'Plummer', 'kplummer', 'P4ssw0rd!', 'kyle.plummer@revature.com');
INSERT INTO users (first_name, last_name, username, password, email) VALUES ('Stan', 'Savelev', 'ssavelev', 'P4ssw0rd!', 'stan.savelev@revature.net');

INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('bring car to jiffylube', '2022-05-01', false, 1);
INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('get car inspected', '2022-05-01', false, 1);
INSERT INTO to_do_items (task, due, completed, user_id) VALUES ('pay rent', '2022-04-01', true, 1);







/*
 * DQL - query and check the test data
 */

select * from to_do_items;
select * from users;


select u.first_name, u.last_name, u.email, tdi.task, tdi.due, tdi.completed
from users u
join to_do_items tdi on tdi.user_id = u.user_id
where u.user_id = 1


CREATE VIEW myView AS 
SELECT u.username, concat(first_name, ' ', last_name) AS name, t.task, t.due
FROM users u 
JOIN to_do_items t ON t.user_id = u.user_id
WHERE t.completed = FALSE;

--DROP VIEW myView

SELECT * FROM myView


CREATE TABLE test.test_table (
	id serial PRIMARY KEY,
	string VARCHAR(200)
);




INSERT INTO test.test_table (string) VALUES (null);

SELECT * FROM test.test_table;

ALTER TABLE test.test_table ALTER COLUMN string SET NOT NULL;





SELECT * FROM users
SELECT * FROM to_do_items tdi 

SELECT *
FROM users u 
JOIN to_do_items t ON t.user_id = u.user_id


/*
 * Joins
 */
CREATE TABLE test.left_table (
	left_id serial PRIMARY KEY,
	string VARCHAR(200),
	fkey int
);


CREATE TABLE test.right_table (
	right_id serial PRIMARY KEY,
	string VARCHAR(500),
	fkey int
);



INSERT INTO test.left_table (string, fkey) VALUES ('test', 4);
INSERT INTO test.right_table (string, fkey) VALUES ('test', 3);
SELECT * FROM test.left_table
DELETE FROM test.left_table WHERE left_id = 1


SELECT * FROM test.left_table
SELECT * FROM test.right_table

SELECT l.left_id, l.string AS left_string, r.string AS right_string, r.right_id
FROM test.left_table l
full JOIN test.right_table r ON l.left_id = r.right_id



/*
 * Set operations (Unions)
 */

CREATE TABLE union_top (
	top_id int,
	string VARCHAR(500),
	decimal decimal
	
);

CREATE TABLE union_bottom (
	bottom_id int,
	string VARCHAR(500),
	decimal decimal
	
);

DROP TABLE union_broken

CREATE TABLE union_broken (
	broken_id int,
	num int,
	decimal decimal
);



INSERT INTO union_top (top_id, string, decimal) VALUES (1, 'test', 5.5);
INSERT INTO union_top (top_id, string, decimal) VALUES (2, 'test', 2.5);
INSERT INTO union_top (top_id, string, decimal) VALUES (3, 'test', 9.9);

INSERT INTO union_bottom (bottom_id, string, decimal) VALUES (1, 'test', 5.5);
INSERT INTO union_bottom (bottom_id, string, decimal) VALUES (2, 'test', 2.5);
INSERT INTO union_bottom (bottom_id, string, decimal) VALUES (3, 'test', 9.9);

INSERT INTO union_broken (broken_id, string) VALUES (1, 'test');
INSERT INTO union_broken (broken_id, string) VALUES (2, 'test');
INSERT INTO union_broken (broken_id, string) VALUES (3, 'test');


SELECT * FROM union_top;
SELECT * FROM union_bottom;
SELECT * FROM union_broken;


SELECT * FROM union_top 
UNION ALL
SELECT * FROM union_broken


/*
 * Multiplicity - one-to-one, one-=to-many, many-to-many relations
 */

DROP TABLE teachers
CREATE TABLE teachers (
	teacher_id serial PRIMARY KEY,
	teacher_name VARCHAR(500)
);

DROP TABLE students
CREATE TABLE students (
	student_id serial PRIMARY KEY,
	student_name VARCHAR(500),
	student_grade decimal
);

DROP TABLE teachers_students
CREATE TABLE teachers_students (
	students_teachers_id serial PRIMARY KEY,
	student_id int,
	teacher_id int,
	CONSTRAINT teachers_students_students_fkey FOREIGN KEY (student_id) REFERENCES students (student_id),
	CONSTRAINT teachers_students_teachers_fkey FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

/*
 * This is another way to implement the junction table with a composite primary key made up of the two columns. This is hopw Hibernate would do it.

DROP TABLE teachers_students
CREATE TABLE teachers_students (
	student_id int,
	teacher_id int,
	PRIMARY KEY (student_id, teacher_id),
	CONSTRAINT teachers_students_students_fkey FOREIGN KEY (student_id) REFERENCES students (student_id),
	CONSTRAINT teachers_students_teachers_fkey FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);
*/


INSERT INTO teachers (teacher_name) VALUES ('Chenxi Zhu');
INSERT INTO teachers (teacher_name) VALUES ('William Bjerke');

INSERT INTO students (student_name) VALUES ('Kenneth Strohm');
INSERT INTO students (student_name) VALUES ('Anthony Pilletti');
INSERT INTO students (student_name) VALUES ('Khalid Ettaleb');
INSERT INTO students (student_name) VALUES ('Diego Leon');



SELECT * FROM students;
SELECT * FROM teachers;
SELECT * FROM teachers_students;

-- Databases 101 with Prof Zhu
INSERT INTO teachers_students (student_id, teacher_id) VALUES (1,1);
INSERT INTO teachers_students (student_id, teacher_id) VALUES (2,1);
INSERT INTO teachers_students (student_id, teacher_id) VALUES (3,1);

-- road safety with William Bjerke
INSERT INTO teachers_students (student_id, teacher_id) VALUES (2,2);
INSERT INTO teachers_students (student_id, teacher_id) VALUES (3,2);
INSERT INTO teachers_students (student_id, teacher_id) VALUES (4,2);



SELECT s.student_name, s.student_grade
FROM teachers t
JOIN teachers_students ts ON t.teacher_id = ts.teacher_id
JOIN students s ON ts.student_id = s.student_id
--WHERE teacher_name = 'Chenxi Zhu';
WHERE teacher_name = 'William Bjerke'

SELECT * FROM teachers_students


DELETE FROM teachers WHERE teacher_name = 'Chenxi Zhu'
DELETE FROM teachers_students WHERE teacher_id = (SELECT teacher_id FROM teachers WHERE teacher_name = 'Chenxi Zhu');

