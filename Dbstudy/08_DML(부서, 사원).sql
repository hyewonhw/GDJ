-- DROP TABLE
DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- DEPARTMENT
CREATE TABLE DEPARTMENT(
    DEPT_NO NUMBER NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE) NOT NULL
);

-- EMPLOYEE
CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER NULL,
    POSITION  VARCHAR2(20 BYTE) NULL,
    GENDER    CHAR(2) NULL,
    HIRE_DATE DATE NULL, 
    SALARY    NUMBER
);

-- ALTER TABLE(PK)
ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

-- ALTER TABLE(FK)
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART)
        REFERENCES DEPARTMENT(DEPT_NO)
            ON DELETE CASCADE;

/*
    DML 
    1. Data Manipulation Language
    2. 데이터 조작어
    3. 행(Rowm Record, Tuple) 단위 삽입, 수정, 삭제
    4. 트랜잭션(작업) 완료를 위해 COMMIT이 필요
    5. 트랜잭션(작업) 취소를 위해 ROLLBACK 사용 가능
    6. 종류
        1) INSERT INTO VALUES
        2) UPDATE SET WHERE
        3) DELETE FROM WHERE
*/

/*
    행 삽입
    1. 지정한 칼럼에 데이터 삽입
       INSERT INTO 테이블(칼럼1,칼럼2) VALUES(값1, 값2)
    2. 모든 칼럼에 데이터 삽입(칼럼 리스트 생략)
       INSERT INTO 테이블 VALUES(값1, 값2)
*/ 

-- 부서 테이블에 행(Row) 삽입
-- 부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 함
-- 4개 삽입한거 취소하면 다 취소됨
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION) 
VALUES
    (1, '영업부', '대구');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (2, '인사부', '서울');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (3, '총무부', '대구');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (4, '기획부', '서울');

-- 작업의 완료
COMMIT;

/*
    오류체크
    INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME) VALUES(5, '개발부');
    --> LOCATION은 NOT NULL임.
    INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '부에노스아이레스');
    --> LOCATION은 15 BYTE 까지만 가능.
    INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(4, '개발부', '인천');
    --> PK의 특징인 UNIQUE위반.
*/

-- 사원 테이블 행(Row) 삽입
-- 자식 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는 데이터만 삽입 가능
-- 부서(부서번호) - 사원(소속부서)
-- PK             - FK
-- 1,2,3,4        - 1,2,3,4중 하나만 가능 
INSERT INTO    
    EMPLOYEE 
VALUES
    (1001, '구창민', 1, '과장', 'M', '95/05/01', 5000000);    -- DATE는 작은따옴표 안에 /나 -로 

INSERT INTO
    EMPLOYEE
VALUES
    (1002, '김민서', 1, '사원', 'F', '17/09/01', 2000000);

INSERT INTO
    EMPLOYEE
VALUES
    (1003, '이은영', 2, '부장', NULL, '90-09-01', 5500000);

INSERT INTO
    EMPLOYEE
VALUES
    (1004, '한성일', 2, '과장', 'M', '93-04-01', 5000000);

COMMIT;

/*
    오류체크
    INSERT INTO EMPLOYEE
        (EMP_NO, NAME, DEPART)
    VALUES
        (1005, '아무개', 5);
    --> EMP_NO와 NAME은 NOT NULL이기 때문에 넣어줘야함, DEPT_NO에 5가 없기때문에 실행 불가능
*/

-- 1. 외부 데이터 import
-- 엑셀 데이터(시트마다 테이블 1개)
-- 테이블 선택 후 마우스 우클릭 - [데이터 임포트]



-- 2. 부서 테이블 수정
-- 부서번호가 1인 부서의 지역을 '인천'으로 수정
UPDATE DEPARTMENT  
   SET LOCATION = '인천'   --> 대입  
 WHERE DEPT_NO = 1 ;       --> 자바의 == 같은 개념

-- 부서번호가 3인 부서명을 '전략부', 지역을 '부산'으로 수정
UPDATE DEPARTMENT
   SET DEPT_NAME = '전략부'
     , LOCATION = '부산'
 WHERE DEPT_NO = 3;


/*
    부서 - 사원
    1    - 1
    2    - 1
    3    - 2
    4    - 2
    5    - 3
*/
-- 3. 부서번호가 3인 부서의 부서번호를 6으로 수정
-- DEPARTMENT의 부서번호를 EMPLOYEE가 참조중이므로 수정이 안 됨
-- 해결책
-- 1. 외래키 일시중지
-- 2. 수정
-- 3. 외래키 재시작

ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;  -- 외래키 일시중지

UPDATE EMPLOYEE
   SET DEPART = 6
 WHERE DEPART = 3;

UPDATE DEPARTMENT 
   SET DEPT_NO = 6
 WHERE DEPT_NO = 3;

ALTER TABLE EMPLOYEE
    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;    -- 외래키 재시작

-- 4. 부서 번호 1인 사원들의 월급을 100000원 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;

-- 5. 직급이 '과장'인 사원들의 월급을 10% 인상
UPDATE EMPLOYEE
   SET SALARY = SALARY + SALARY * 0.1
WHERE POSITION = '과장';
/*
    SALARY * 1 + SALARY * 0.1
    SALARY * (1 + 0.1)
    SALARY * 0.1
*/

-- 테이블 삭제

-- 1. 부서번호가 4인 부서를 삭제
DELETE
  FROM DEPARTMENT 
 WHERE DEPT_NO = 4;

-- 2. 부서번호가 1인 부서를 삭제
-- 외래키 옵션으로 ON DELETE SET NULL 처리를 하였기 때문에 
-- 부서번호가 1인 행(ROW)의 소속부서가 NULL 값으로 함께 변경
DELETE 
  FROM DEPARTMENT
 WHERE DEPT_NO = 1;

COMMIT;

