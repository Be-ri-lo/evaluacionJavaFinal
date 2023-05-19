CREATE DATABASE “EvaluacionJava”;

\c “evaluacionJava;

CREATE TABLE usuario(
 id SERIAL,
 name VARCHAR(25) NOT NULL,
 email VARCHAR(50) NOT NULL,
 password VARCHAR(25) NOT NULL,
 active boolean DEFAULT TRUE,
 day_of_login DATE,
 created_at DATE,
 updated_at DATE,
 PRIMARY KEY (id),
 UNIQUE (email)
 );

CREATE TABLE phone(
id SERIAL,
id_user INT NOT NULL,
number VARCHAR(15),
cityCode VARCHAR(3),
countryCode VARCHAR(3),
created_at DATE,
updated_at DATE ,
PRIMARY KEY (id),
FOREIGN KEY ( id_user) REFERENCES usuario(id)
);
