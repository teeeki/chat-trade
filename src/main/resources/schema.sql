DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS tmp;
-- アカウントテーブル
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username TEXT,
	password TEXT,
	is_verify boolean DEFAULT false
);
CREATE TABLE items (
	id SERIAL PRIMARY KEY,
	user_id INTEGER,
	name TEXT,
	price INTEGER,
	abstract TEXT,
	description TEXT,
	img_path TEXT
);
CREATE TABLE tmp (id SERIAL PRIMARY KEY, name TEXT);