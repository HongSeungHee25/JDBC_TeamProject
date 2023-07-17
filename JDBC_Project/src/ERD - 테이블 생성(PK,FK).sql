
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
	car_grade varchar2(30) NOT NULL,
	car_type varchar2(20) NOT NULL,
	rent_Type varchar2(10),
	price number NOT NULL,
	insurance number NOT NULL,
	PL varchar2(10) NOT NULL,
	PRIMARY KEY (car_no)
);

-- 자동차 관리 테이블 예시 데이터
INSERT INTO CAR c VALUES ('123허1234','준중형','소나타',NULL,45000,10000,'기름');
INSERT INTO CAR c VALUES ('124허1254','준중형','소나타',NULL,56000,10000,'기름');
INSERT INTO CAR c VALUES ('145허1254','준중형','소나타',NULL,68400,15000,'기름');

-- 자동차 검사 테이블
CREATE TABLE car_inspection
(
	car_no varchar2(30) NOT NULL,
	Inspection_type varchar2(30) NOT NULL,
	Last_date DATE,  
	Next_date date,
	PRIMARY KEY (car_no),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);

-- 자동차 검사 테이블 예시 데이터
INSERT INTO CAR_INSPECTION ci VALUES (1,'김모모',sysdate, NULL );
INSERT INTO CAR_INSPECTION ci VALUES (1,'김모모',NULL, NULL );

-- 자동차 렌트 예약 테이블
CREATE TABLE car_rent
(
	rent_no number NOT NULL,
	name varchar2(20) NOT NULL,
	car_no varchar2(30) NOT NULL,
	rent_start date,
	rent_end date,
	PRIMARY KEY (rent_no),
	FOREIGN KEY (name) REFERENCES customer (name),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);

-- 자동차 렌트 예약 테이블 예시 데이터
INSERT INTO car_rent VALUES (1,'김모모','123허1234',NULL,NULL);
INSERT INTO car_rent VALUES (1,'김모모','123허1234','2023-07-01','2023-07-03');
INSERT INTO car_rent VALUES (2,'김모모','124허1254','2023-06-01','2023-06-03');
INSERT INTO car_rent VALUES (3,'김모모','145허1254','2023-05-01','2023-05-03');
INSERT INTO car_rent VALUES (4,'김사나','145허1254','2023-05-01','2023-05-03');

-- 회원 관리 테이블
CREATE TABLE customer
(
	name varchar2(20) NOT NULL,
	customer_id varchar2(20) NOT NULL,
	pw varchar2(20) NOT NULL UNIQUE,
	phone varchar2(20) NOT NULL,
	license char(1),
	PRIMARY KEY (name)
);

-- 회원 관리 테이블 예시 데이터
INSERT INTO CUSTOMER c VALUES ('김모모','momo1234','154a','010-1234-1234',null);
INSERT INTO CUSTOMER c VALUES ('김사나','sana','as154','010-4567-1548',null);

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
	name varchar2(20) NOT NULL,
	rent_no number NOT NULL,
	payment_day  date,
	money number,
	payment_method varchar2(30),
	car_no varchar2(30) NOT NULL,
	PRIMARY KEY (payment_id),
	FOREIGN KEY (name) REFERENCES customer (name),
	FOREIGN KEY (rent_no) REFERENCES car_rent (rent_no),
	FOREIGN KEY (car_no) REFERENCES car (car_no)
);

-- 결제 정보관리 테이블 예시 데이터 
INSERT INTO PAYMENT p VALUES (1001,'김모모',1,sysdate,NULL,'신용카드','123허1234');
INSERT INTO PAYMENT p VALUES (1001,'김모모',1,NULL ,NULL,'신용카드','123허1234');

-- 고객센터 테이블
CREATE TABLE CustomerServiceCenter
(
	service_no NUMBER NOT NULL,
    name varchar2(20) NOT NULL,
    customer_id varchar2(20) NOT NULL,
    phone varchar2(20) NOT NULL,
    service varchar2(1000),
    PRIMARY KEY (service_no), 
    FOREIGN KEY (name) REFERENCES customer (name)
);

CREATE SEQUENCE service_seq START WITH 1;

SELECT SERVICE_NO,SERVICE FROM CUSTOMERSERVICECENTER c WHERE name = '홍승희';






