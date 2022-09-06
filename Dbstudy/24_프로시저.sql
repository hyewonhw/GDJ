/*
    프로시저
    
    1. PROCEDURE 
    2. 여러 개의 쿼리문을 한 번에 실행       --> 쿼리문 연달아 빨리 실행하기 위해서 사용
       ((EX)이체 : UPDATE문 2개 필요)
    3. 작성된 프로시저는 EXECUTE문으로 실행
       EXCUTE 프로시저()    --> 메소드랑 비슷한 느낌
    4. 형식
        CREATE [OR REPLACE] 프로시저_이름(매개변수)         --> DROP TABLE후 CRATE TABLE하는 것과 같은 느낌
        IS -- AS 가능                                       --> DECLARE대신 IS나 AS 쓰면 된다는 것
            변수선언                
        BEGIN
            프로시저본문
        [EXCEPTION
            예외처리]
        END [프로시저_이름];
*/

-- 프로시저 PROC1 정의 (만들기)
CREATE OR REPLACE PROCEDURE PROC1
IS
    NAME VARCHAR2(10 BYTE);
BEGIN
    NAME := '민경태';
    DBMS_OUTPUT.PUT_LINE(NAME);
END PROC1;

-- 프로시저 PROC1 호출(실행)
EXECUTE PROC1();

SET SERVEROUTPUT ON;



-- 프로시저 PROC2 정의
-- 사원번호=100인 사원의 FIRST_NAME 서버메시지로 출력하기
CREATE OR REPLACE PROCEDURE PROC2
IS 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;    --> 참조타입변수선언
BEGIN 
    SELECT FIRST_NAME       --> FIRST_NAME 칼럼 값을
      INTO FNAME            --> FNAME 변수에 저장
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;   
    DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC2;

-- 프로시저 PROC2 호출
EXECUTE PROC2();



-- 프로시저 PROC3 정의
-- 사원번호를 전달하면 해당 사원의 FRIST_NAME을 서버메시지로 출력하기

-- //입력 파라미터//
-- 1. 프로시저로 전달하는 값을 저장할 변수
-- 2. 형식 : 변수명 IN 타입

CREATE OR REPLACE PROCEDURE PROC3(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE)  --> 100이 IN 뒤로 들어와서 EMP_ID에 저장
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
    DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC3;

-- 프로시저 PROC3 호출
EXECUTE PROC3(100);  --> 사원번호를 외부에서 전달
EXECUTE PROC3(101);
EXECUTE PROC3(500);  --> 없는 번호 전달하면 NO DATA FOUND 오류 뜸



-- 프로시저 PROC4 정의
-- 사원번호=100인 사원의 FIRST_NAME을 출력 파라미터 FNAME에 저장하기

-- //출력 파라미터//
-- 1. 프로시저의 결과(반환) 값을 저장하는 변수
-- 2. 형식 : 변수명 OUT 타입
CREATE OR REPLACE PROCEDURE PROC4(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    --> 출력문이 필요없음
END PROC4;

-- 프로시저 PROC4 호출
-- 실행순서 DECLARE -> BEGIN
--> BEGIN PROC4는 프로시저를 통해서 데이터를 받아옴 (OUT이기때문에)
DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;    -- 출력 파라미터로 사용할 변수
BEGIN
    PROC4(FNAME);                       -- PLSQL(프로그래밍) 내부에서 프로시저를 호출할 땐 EXECUTE 생략 
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;


-- 프로시저 PROC5 정의(//EXCEPTION처리하기//)
-- 사원번호가 있으면 FIRST_NAME을 출력 파라미터로 전달, 없으면 'NoName'을 출력 파라미터로 전달
CREATE OR REPLACE PROCEDURE PROC5(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN  
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 500; --> 없는 사람
EXCEPTION
    WHEN OTHERS THEN  -- 모든 예외를 처리함(WHEN NO_DATA_FOUND THEN 가능)
        FNAME := 'NoName';
END PROC5;

-- 프로시저 PROC5 호출
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN   
    PROC5(FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;



-- 연습1. 입력 파라미터에 사원번호 전달, 출력 파라미터에 FIRST_NAME 반환받기
-- 존재하지 않는 사원번호는 출력파라미터에 'NoName' 반환하기
-- //입출력 프로시저//
-- 프로시저 PROC6 정의

CREATE OR REPLACE PROCEDURE PROC6(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE, FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        FNAME := 'NoName';
END PROC6;

-- 프로시저 PROC6 호출
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC6(100, FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
    PROC6(500, FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;



-- //최종 실습 환경//

DROP TABLE BUY;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;

-- 제품 테이블
CREATE TABLE PRODUCT(
    PROD_CODE  NUMBER             NOT NULL,  -- 제품코드
    PROD_NAME  VARCHAR2(10 BYTE),            -- 제품명
    PROD_PRICE NUMBER,                       -- 제품가격
    PROD_STOCK NUMBER                        -- 재고
);
-- 제품 기본키
ALTER TABLE PRODUCT
    ADD CONSTRAINT PK_PRODUCT PRIMARY KEY(PROD_CODE);
-- 제품 입력
INSERT INTO PRODUCT VALUES(1000, '진라면', 500, 100);
INSERT INTO PRODUCT VALUES(1001, '신라면', 600, 100);
COMMIT;


-- 고객 테이블
CREATE TABLE CUSTOMER(
    CUST_NO    NUMBER             NOT NULL,   -- 고객번호
    CUST_NAME  VARCHAR2(10 BYTE),             -- 고객명
    CUST_POINT NUMBER                         -- 고객포인트
);
-- 고객 기본키
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(CUST_NO);
-- 고객 입력
INSERT INTO CUSTOMER VALUES(1, '철수', 0);
INSERT INTO CUSTOMER VALUES(2, '영희', 0);
COMMIT;

-- 구매 테이블
CREATE TABLE BUY(
    BUY_NO     NUMBER NOT NULL,  -- 구매번호
    CUST_NO    NUMBER NOT NULL,  -- 고객번호(FK)
    PROD_CODE  NUMBER NOT NULL,  -- 제품코드(FK)
    BUY_AMOUNT NUMBER            -- 구매수량
);
ALTER TABLE BUY
    ADD CONSTRAINT PK_BUY PRIMARY KEY(BUY_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_CUSTOMER FOREIGN KEY(CUST_NO)
        REFERENCES CUSTOMER(CUST_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_PRODUCT FOREIGN KEY(PROD_CODE)
        REFERENCES PRODUCT(PROD_CODE);

-- 구매 테이블 시퀀스
DROP SEQUENCE BUY_SEQ;
CREATE SEQUENCE BUY_SEQ NOCACHE;



-- 구매 프로시저
-- 1. BUY_PROC(고객번호, 제품코드, 구매수량)
-- 2. 진행해야 할 쿼리
--    1) 구매 테이블에 구매 내역을 INSERT 한다.
--    2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립)
--    3) 제품 테이블의 재고를 UPDATE 한다.

-- 정의
CREATE OR REPLACE PROCEDURE BUY_PROC
(
    C_NO    IN CUSTOMER.CUST_NO%TYPE, 
    P_CODE  IN PRODUCT.PROD_CODE%TYPE, 
    BUY_AMT IN BUY.BUY_AMOUNT%TYPE
)
IS
BEGIN 
    -- 1) 구매 테이블에 구매 내역을 INSERT 한다.
    INSERT INTO BUY(BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT)
    VALUES(BUY_SEQ.NEXTVAL, C_NO, P_CODE, BUY_AMT);  -- 시퀀스로부터 번호빼오기 시퀀스 중요 
    -- 2) 고객 테이블의 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립, 정수로 올림 처리)
    UPDATE CUSTOMER     --> PROD_PRICE를 알아내는 서브쿼리가 필요
       SET CUST_POINT = CUST_POINT + CEIL((SELECT PROD_PRICE  --> 기존포인트 + 새 포인트 
                                        FROM PRODUCT
                                       WHERE PROD_CODE = P_CODE) * BUY_AMT * 0.1) --> 소수점 올림처리함(CEIL)                       
     WHERE CUST_NO = C_NO;
    -- 3) 제품 테이블의 재고를 UPDATE 한다.
    -- 물건팔렸으니 재고가 줄어들어야함
    UPDATE PRODUCT
       SET PROD_STOCK = PROD_STOCK - BUY_AMT
     WHERE PROD_CODE = P_CODE;
    -- 4) DML작업의 완료
    COMMIT;
EXCEPTION
    -- 예외 처리 : 예외 발생 시 아무 일도 없었던 것으로(전부 취소)
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외코드' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('예외메시지' || SQLERRM);
        -- 롤백
        ROLLBACK;
END BUY_PROC;


-- 구매 프로시저 호출
EXECUTE BUY_PROC(1, 1000, 10); --> 1번(철수)이 1000(진라면)제품 10개샀다

-- 확인
SELECT PROD_CODE, PROD_NAME, PROD_PRICE, PROD_STOCK
  FROM PRODUCT;     --> 제품 줄어든거 확인하기
SELECT CUST_NO, CUST_NAME, CUST_POINT
  FROM CUSTOMER;    --> 포인트 확인하기
SELECT BUY_NO, CUST_NO, PROD_CODE, BUY_AMOUNT
  FROM BUY;         --> 구매내역 확인하기
