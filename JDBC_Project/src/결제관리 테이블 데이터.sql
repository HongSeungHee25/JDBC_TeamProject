-- 결제관리 데이터 
insert into payment
values (payment_id_seq.nextval, '김지수', 1,  
'2021-12-28', null, '신용카드', '124호 5713');
insert into payment
values (payment_id_seq.nextval, '김규태', 2,  
'2023-04-21', null, '신용카드', '142하 3431');
insert into payment
values (payment_id_seq.nextval, '박길동', 3,  
'2022-02-22', null, '신용카드', '142하 3432');
insert into payment
values (payment_id_seq.nextval, '정지우', 4,  
'2023-03-25', null, '계좌이체', '120호 2111');
insert into payment
values (payment_id_seq.nextval, '임윤주', 5,  
'2023-01-27', null, '신용카드', '101허 1011');
insert into payment
values (payment_id_seq.nextval, '이나현', 6,  
'2023-01-03', null, '신용카드', '101허 1012');
insert into payment
values (payment_id_seq.nextval, '김예나', 7,  
'2023-02-05', null, '계좌이체', '101하 1001');
insert into payment
values (payment_id_seq.nextval, '이용희', 8,  
'2023-03-18', null, '신용카드', '120호 2112');
insert into payment
values (payment_id_seq.nextval, '서동우', 9,  
'2023-04-01', null, '계좌이체', '142하 3431');
insert into payment
values (payment_id_seq.nextval, '한빈', 10,  
'2023-02-21', null, '신용카드', '142허 4312');


-- 결제관리 id 시퀀스
DROP SEQUENCE payment_id_seq;
CREATE SEQUENCE payment_id_seq
	START WITH 1;
