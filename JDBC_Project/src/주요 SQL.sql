-- 예약 상태 이름 
SELECT c.CAR_NO, c.CAR_GRADE, c.CAR_TYPE,
  (CASE
    WHEN cr.RENT_START > SYSDATE THEN '예약 중'
    WHEN cr.RENT_START < SYSDATE AND cr.RENT_END > SYSDATE THEN '대여 중'
    ELSE '대여 가능'
  END) AS RENT_TYPE, c.PRICE , c.INSURANCE ,c.PL
FROM CAR c
LEFT JOIN CAR_RENT cr ON c.CAR_NO = cr.CAR_NO;

-- 총 토탈 가격
SELECT p.payment_id, p.name, p.payment_day, c.PRICE + c.INSURANCE AS money, p.PAYMENT_METHOD ,p.car_no
FROM PAYMENT p JOIN CAR c 
ON p.car_no = c.CAR_NO ;

-- 총 토탈 가격 테이블
CREATE TABLE TOTAL_PRICE 
AS 
SELECT p.payment_id, p.name, p.payment_day, c.PRICE + c.INSURANCE AS money, p.PAYMENT_METHOD ,p.car_no
FROM PAYMENT p JOIN CAR c 
ON p.car_no = c.CAR_NO ;

SELECT * FROM TOTAL_PRICE;

-- 월 별 매출 조회
SELECT to_char(payment_day,'yyyy-mm') AS months, sum(money) AS total
FROM (SELECT p.payment_id, p.name, p.payment_day, c.PRICE + c.INSURANCE AS money, p.PAYMENT_METHOD ,p.car_no
		FROM PAYMENT p JOIN CAR c 
		ON p.car_no = c.CAR_NO)
GROUP BY to_char(payment_day,'yyyy-mm')
ORDER BY months;

-- 월별 토탈 가격 테이블
CREATE TABLE month_total 
AS 
SELECT to_char(payment_day,'yyyy-mm') AS months, sum(money) AS total
FROM (SELECT p.payment_id, p.name, p.payment_day, c.PRICE + c.INSURANCE AS money, p.PAYMENT_METHOD ,p.car_no
		FROM PAYMENT p JOIN CAR c 
		ON p.car_no = c.CAR_NO)
GROUP BY to_char(payment_day,'yyyy-mm')
ORDER BY months;

SELECT * FROM month_total;

