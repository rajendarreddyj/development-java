DROP TABLE USER IF EXISTS;

DROP TABLE USER_STATS IF EXISTS;

CREATE TABLE USER(
firstName varchar(30),
middleName varchar(35),
lastName varchar(30),
city varchar(20),
id integer
);

CREATE TABLE USER_STATS(
firstName varchar(30),
lastName varchar(30),
city varchar(20),
id integer,
createTime timestamp default 'now'
); 

INSERT INTO USER VALUES('test1','user', 'one', 'Richfield', 22);
INSERT INTO USER VALUES('test2','user', 'two', 'Minneapolis', 84);
INSERT INTO USER VALUES('test3','user', 'three', 'Delhi', 92);