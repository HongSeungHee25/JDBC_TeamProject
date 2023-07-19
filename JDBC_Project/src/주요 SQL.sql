-- 중복 예약 불가능
SELECT CASE 
WHEN cr.RENT_START > SYSDATE THEN 0
WHEN cr.RENT_START < SYSDATE AND cr.RENT_END > SYSDATE THEN 0
ELSE 1 
END AS IS_AVAILABLE 
FROM CAR c 
LEFT JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO 
WHERE c.CAR_NO = '101하 1204';

-- 최근 예약 정보 1개
SELECT RENT_NO , name, CAR_NO ,TO_CHAR(RENT_START,'yyyy-mm-dd') AS rent_start,TO_CHAR(RENT_END,'yyyy-mm-dd') AS RENT_END  
FROM CAR_RENT cr WHERE name = '홍승희' AND rownum = 1 ORDER BY RENT_NO DESC;

-- 회원 로그인해서 마이페이지에서 예약 내역 조회하기
SELECT car_no , car_type, to_char(rent_start,'yyyy-mm-dd') AS rent_start, TO_CHAR(rent_end, 'yyyy-mm-dd') AS rent_end,
(PRICE + INSURANCE) AS MONEY 
FROM CAR JOIN CAR_RENT 
using(car_no)
WHERE NAME = '홍승희'
ORDER BY rent_start;

-- 관리자페이지에서 회원별 매출 등급 넣어서 확인
SELECT name, total_money, payment_method,
CASE
WHEN total_money >= 500000 THEN 'VIP'
WHEN total_money >= 300000 THEN 'GOLD'
WHEN total_money >= 200000 THEN 'SILVER'
ELSE 'FAMILY'
END AS grade
FROM (
SELECT p.name, SUM(money) AS total_money, p.payment_method
FROM PAYMENT p
JOIN CAR c ON p.car_no = c.car_no
GROUP BY p.name, p.payment_method
) subquery
ORDER BY name;

-- 차량 대여가능 여부 조회
SELECT c.CAR_NO, c.CAR_GRADE, c.CAR_TYPE,
(CASE
WHEN cr.RENT_START > SYSDATE THEN '예약 중'
WHEN cr.RENT_START < SYSDATE AND cr.RENT_END > SYSDATE THEN '대여 중'
ELSE '대여 가능'
END) AS RENT_TYPE, c.PRICE , c.INSURANCE ,c.PL
FROM CAR c
LEFT JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO;

-- 월별 토탈 매출 조회
SELECT 
to_char(payment_day,'yyyy-mm') AS months, payment_method, money AS total
FROM ( SELECT p.payment_id, p.name, p.payment_day, p.money, p.payment_method, p.car_no
FROM PAYMENT p 
JOIN CAR c
ON p.car_no = c.car_no)
GROUP BY to_char(payment_day,'yyyy-mm'), payment_method, money
ORDER BY months DESC;



