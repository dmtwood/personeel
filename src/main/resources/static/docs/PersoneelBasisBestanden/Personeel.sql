set names utf8mb4;
set charset utf8mb4;
drop database if exists personeel;
create database personeel charset utf8mb4;
use personeel;

CREATE TABLE jobtitels (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  naam varchar(50) NOT NULL unique,
  versie int unsigned NOT NULL DEFAULT 0
);

INSERT INTO jobtitels(naam) VALUES
('President'),
('Sale Manager (EMEA)'),
('Sales Manager (APAC)'),
('Sales Manager (NA)'),
('Sales Rep'),
('VP Marketing'),
('VP Sales');

CREATE TABLE werknemers (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  familienaam varchar(50) NOT NULL,
  voornaam varchar(50) NOT NULL,
  email varchar(100),
  chefid int unsigned,
  jobtitelid int unsigned NOT NULL,
  salaris decimal(10,2) NOT NULL,
  paswoord varchar(100) NOT NULL default '{bcrypt}$2a$10$i4MlDK9l7YM.cpwCY68j4OJP7CEin5.wFDJCptUP7CQWHnNPh6xjy',
  geboorte date NOT NULL,
  versie int unsigned NOT NULL DEFAULT 0,
  CONSTRAINT werknemersJobtitels FOREIGN KEY (jobtitelid) REFERENCES jobtitels(id),
  CONSTRAINT werknemersWerknemers FOREIGN KEY (chefid) REFERENCES werknemers(id)
);

INSERT INTO werknemers(familienaam,voornaam,chefid,jobtitelid,salaris,geboorte) VALUES
 ('Murphy','Diane',NULL,1,3020,'1966-08-01'),
 ('Patterson','Mary',1,7,2000,'1950-01-31'),
 ('Firrelli','Jeff',1,6,2100,'1961-08-01'),
 ('Patterson','William',2,3,1950,'1952-12-31'),
 ('Bondur','Gerard',2,2,1970,'1953-03-03'),
 ('Bow','Anthony',2,4,1920,'1955-08-29'),
 ('Jennings','Leslie',6,5,1910,'1960-01-01'),
 ('Thompson','Leslie',6,5,1905,'1960-06-13'),
 ('Firrelli','Julie',6,5,1900,'1966-01-08'),
 ('Patterson','Steve',6,5,1910,'1969-12-31'),
 ('Tseng','Foon Yue',6,5,1904,'1970-06-14'),
 ('Vanauf','George',6,5,1905,'1955-02-02'),
 ('Bondur','Loui',5,5,1909,'1954-08-08'),
 ('Hernandez','Gerard',5,5,1908,'1952-03-14'),
 ('Castillo','Pamela',5,5,1903,'1966-03-03'),
 ('Bott','Larry',5,5,1902,'1963-08-08'),
 ('Jones','Barry',5,5,1903,'1964-11-11'),
 ('Fixter','Andy',4,5,1907,'1972-05-07'),
 ('Marsh','Peter',4,5,1905,'1967-11-29'),
 ('King','Tom',4,5,1904,'1973-09-01'),
 ('Nishi','Mami',2,5,1900,'1974-01-31'),
 ('Kato','Yoshimi',19,5,1901,'1975-02-15'),
 ('Gerard','Martin',5,5,1902,'1962-09-17');
 
 update werknemers set email = concat(voornaam, '.', familienaam, '@toysforboys.com');
 alter table werknemers change email email varchar(100) NOT NULL unique;

create user if not exists cursist identified by 'cursist';
grant select on jobtitels to cursist;
grant select,update on werknemers to cursist;