
/* Drop Tables */

DROP TABLE car_inspection CASCADE CONSTRAINTS;
DROP TABLE Payment CASCADE CONSTRAINTS;
DROP TABLE car_rent CASCADE CONSTRAINTS;
DROP TABLE car CASCADE CONSTRAINTS;
DROP TABLE customer CASCADE CONSTRAINTS;
DROP TABLE manager CASCADE CONSTRAINTS;



/* Create Tables */

-- 자동차 관리 테이블
CREATE TABLE car
(
	car_no varchar2(30) NOT NULL,
	car_grade varchar2(10) NOT NULL,
	car_type varchar2(20) NOT NULL,
	rent_Type varchar2(10),
	price number NOT NULL,
	insurance number NOT NULL,
	PL varchar2(10) NOT NULL,
	PRIMARY KEY (car_no)
);

-- 자동차 검사 테이블
CREATE TABLE car_inspection
(
	car_no varchar2(30) NOT NULL,
	Inspection_type varchar2(30) NOT NULL,
	Last_date date NOT NULL,
	Next_date date NOT NULL,
	PRIMARY KEY (car_no),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);

-- 자동차 렌트 예약 테이블
CREATE TABLE car_rent
(
	rent_no number NOT NULL,
	customer_id varchar2(20) NOT NULL,
	car_no varchar2(30) NOT NULL,
	rent_start date NOT NULL,
	rent_end date NOT NULL,
	PRIMARY KEY (rent_no),
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);

-- 회원 관리 테이블
CREATE TABLE customer
(
	customer_id varchar2(20) NOT NULL,
	pw varchar2(20) NOT NULL UNIQUE,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	license char(1) NOT NULL,
	PRIMARY KEY (customer_id)
);

-- 관리자 테이블
CREATE TABLE manager
(
	password varchar2(20) NOT NULL,
	PRIMARY KEY (password)
);

-- 결제 정보관리 테이블
CREATE TABLE Payment
(
	payment_id number NOT NULL,
	customer_id varchar2(20) NOT NULL,
	rent_no number NOT NULL,
	payment_day  date NOT NULL,
	money number,
	payment_method varchar2(30) NOT NULL,
	car_no varchar2(30) NOT NULL,
	PRIMARY KEY (payment_id),
	FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
	FOREIGN KEY (rent_no) REFERENCES car_rent (rent_no),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);
