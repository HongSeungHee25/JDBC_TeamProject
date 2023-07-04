-- 자동차 렌트 예약 테이블 데이터
create sequence rent_no_seq;
   start with 1;
insert into car_rent values(rent_no_seq.nextval,'김지수','124호 5713','2022-01-01','2022-06-07');
insert into car_rent values(rent_no_seq.nextval,'김규태','142하 3431','2023-04-30','2023-05-18');
insert into car_rent values(rent_no_seq.nextval,'박길동','142하 3432','2022-03-04','2022-09-08');
insert into car_rent values(rent_no_seq.nextval,'정지우','120호 2111','2023-03-26','2023-05-11');
insert into car_rent values(rent_no_seq.nextval,'임윤주','101허 1011','2023-02-01','2023-08-11');
insert into car_rent values(rent_no_seq.nextval,'이나현','101허 1012','2023-01-07','2023-08-11');
insert into car_rent values(rent_no_seq.nextval,'김예나','101하 1001','2023-02-14','2023-08-11');
insert into car_rent values(rent_no_seq.nextval,'이용희','120호 2112','2023-03-22','2023-08-11');
insert into car_rent values(rent_no_seq.nextval,'서동우','142하 3431','2023-04-11','2023-08-11');
insert into car_rent values(rent_no_seq.nextval,'한빈','142허 4312','2023-03-01','2023-08-11');