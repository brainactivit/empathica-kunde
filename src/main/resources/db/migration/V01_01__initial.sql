create sequence hibernate_sequence start 1 increment 1;

create table kunde (id int8 not null, anrede varchar(255), cntvisit varchar(255), customer_credit_limit int4, customer_creditable varchar(255), email varchar(255), firstvisit timestamp, geburtstag date, geschlecht varchar(255) not null, lastvisit timestamp, nachname varchar(255) not null, ort varchar(255), plz varchar(255), referrer_id int4, strasse varchar(255), telefon varchar(255), vorname varchar(255) not null, primary key (id));
