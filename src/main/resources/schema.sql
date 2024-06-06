-- 各種テーブル削除
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS depertment;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_details;

-- 会員テーブル
CREATE TABLE account
(
   id SERIAL PRIMARY KEY,
   name TEXT,
   grade INTEGER,
   department TEXT,
   email TEXT,
   address TEXT,
   password TEXT
);
-- 教科書テーブル
CREATE TABLE item
(
   id INTEGER PRIMARY KEY,
   name TEXT,
   lecture TEXT,
   professor TEXT,
   price INTEGER
);

-- 注文テーブル
CREATE TABLE orders
(
   id SERIAL PRIMARY KEY,
   account_id INTEGER,
   ordered_on DATE,
   total_price INTEGER
   
);
-- 注文明細テーブル
CREATE TABLE order_details
(
   id SERIAL PRIMARY KEY,
   account_id INTEGER, 
   order_id INTEGER,
   item_id INTEGER,
   quantity INTEGER
   
);