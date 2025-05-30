DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items;


-- アカウントテーブル
CREATE TABLE users
(
	id SERIAL PRIMARY KEY,
	username TEXT,
	password TEXT
);


CREATE TABLE items
(
	id SERIAL PRIMARY KEY,
	user_id INTEGER,
	name TEXT,
	price INTEGER,
	abstract TEXT,
	description TEXT,
	img BYTEA
);