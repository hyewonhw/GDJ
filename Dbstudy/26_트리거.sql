/*
    트리거

    1. Trigger
    2. DML(INSERT, UPDATE, DELETE) 수행 후 트리거가 자동으로 수행됨
    3. DML 직전에 수행되는 BEFORE트리거, 직후에 수행되는 AFTER트리거가 있음
    4. 기본적으로 작업이 수행되는 행(ROW) 단위로 트리거가 적용됨
    5. 형식
        CREATE [OR REPLACE] TRIGGER 트리거_이름
        [AFTER | BEFORE]
        [INSERT OR UPDATE OR DELETE]
        [ON 테이블_이름]
        [FOR EACH ROW]
        BEGIN
            트리거 작업
        END [트리거_이름];
*/


-- 트리거 TRIG1 정의
CREATE OR REPLACE TRIGGER TRIG1
    AFTER          -- AFTER, BEFORE
    UPDATE         -- INSERT OR UPDATE OR DELETE
    ON DEPARTMENT  -- DEPARTMENT 테이블을 UPDATE할 때 동작함
    FOR EACH ROW   -- UPDATE되는 행마다 트리거가 동작함 (UPDATE 10번하면 TRIGGER도 10번동작)
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
END TRIG1;

-- 트리거 TRIG1 동작 확인
UPDATE DEPARTMENT 
   SET LOCATION = '인천'
 WHERE DEPT_NO = 1;


-- 트리거 TRIG2 정의
CREATE OR REPLACE TRIGGER TRIG2
    AFTER
    INSERT OR UPDATE OR DELETE -- 작성 순서 없음
    ON DEPARTMENT
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        DBMS_OUTPUT.PUT_LINE('INSERT 이후 동작');
    ELSIF UPDATING THEN
        DBMS_OUTPUT.PUT_LINE('UPDATE 이후 동작');
    ELSIF DELETING THEN
        DBMS_OUTPUT.PUT_LINE('DELETE 이후 동작');
    END IF;
END TRIG2;

-- 트리거 TRIG2 동작 확인
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '제주');
UPDATE DEPARTMENT SET LOCATION = '경주' WHERE DEPT_NO = 2;
DELETE FROM DEPARTMENT WHERE DEPT_NO = 3;

-- DEPARTMENT 테이블의 DML 작업 취소
-- 이전 COMMIT까지 돌아감
ROLLBACK;


-- 트리거 TRIG3 정의

-- OLD 테이블
-- 1. INSERT, UPDATE, DELETE 수행 이전 정보가 저장되는 테이블
-- 2. 오라클에서는 :OLD로 호출
-- 3. AFTER 트리거와 함께 사용 
--    1) INSERT 동작 이전 : NULL
--    2) UPDATE 동작 이전 : 수정 이전 데이터
--    3) DELETE 동작 이전 : 삭제 이전 데이터

CREATE OR REPLACE TRIGGER TRIG3
    AFTER 
    UPDATE OR DELETE
    ON EMPLOYEE
    FOR EACH ROW
BEGIN 
    DBMS_OUTPUT.PUT_LINE(:OLD.NAME);
END TRIG3;

-- 트리거 TIRG3 동작 확인
UPDATE EMPLOYEE SET NAME = '팔창민' WHERE EMP_NO = 1001;
DELETE FROM EMPLOYEE WHERE EMP_NO = '1001';

ROLLBACK;


-- 트리거 최종 실습

-- 목표 : 사원(EMPLOYEES) 테이블에서 삭제된 사원정보를 퇴사자(RETIRES) 테이블

-- 1) 퇴사자 테이블 만들기(EMPLOYEES 테이블과 동일한 구조, 데이터 없이 복사)
CREATE TABLE RETIRES 
    AS (SELECT * FROM EMPLOYEES WHERE 1 = 2);

-- 2) RETIRE_ID, RETIRE_DATE 칼럼 추가하기
ALTER TABLE RETIRES ADD RETIRE_ID NUMBER NOT NULL;
ALTER TABLE RETIRES ADD RETIRE_DATE DATE;

-- 3) RETIRE_ID 기본키 설정하기
ALTER TABLE RETIRES ADD CONSTRAINT PK_RETIRES PRIMARY KEY(RETIRE_ID);

-- 4) RETIRE_SEQ 시퀀스 생성하기
DROP SEQUENCE RETIRE_SEQ;
CREATE SEQUENCE RETIRE_SEQ NOCACHE;

-- 5) RETIRE_TRIG 트리거 생성하기
CREATE OR REPLACE TRIGGER RETIRE_TRIG
    AFTER DELETE
    ON EMPLOYEES
    FOR EACH ROW
BEGIN
    INSERT INTO RETIRES
        (RETIRE_ID, RETIRE_DATE, EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, DEPARTMENT_ID)
    VALUES
        (RETIRE_SEQ.NEXTVAL, SYSDATE, :OLD.EMPLOYEE_ID, :OLD.FIRST_NAME, :OLD.LAST_NAME, :OLD.EMAIL, :OLD.PHONE_NUMBER, :OLD.HIRE_DATE, :OLD.JOB_ID, :OLD.SALARY, :OLD.COMMISSION_PCT, :OLD.DEPARTMENT_ID);
END RETIRE_TRIG;

-- 6) EMPLOYEES 테이블의 데이터 DELETE 수행하기(RETIRE_TRIG동작 확인)
DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = '100';
