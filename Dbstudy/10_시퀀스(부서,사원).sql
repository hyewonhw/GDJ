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
            ON DELETE SET NULL;

/*
    시퀀스
    
    1. SEQUENCE
    2. 일련번호를 생성하는 데이터베이스 객체(번호표뽑아주는거)
    3. 자동으로 증가하는 번호를 생성
    4. 기본키(PK)에서 주로 사용(인공키)                 -----> 인공키 : 원래가지고있던데이터가 아니라 추가로 부여해주는 키
    5. NEXTVAL를 이용하면 새로운 번호가 생성
    6. CURRVAL를 이용하면 현재 번호를 확인(현재 번호표가 몇번까지남았는지 확인하는용도)
*/

/*
    시퀀스 생성 형식   --> 순서없음
    
        CREATE SEQUENCE 시퀀스_이름
        START WITH 시작값           --> 생략하면 1부터 시작값, 생성 이후 수정 불가
        INCREMENT BY 증가값         --> 생략하면 1씩 증가
        MINVALUE 최소값             
        MAXVALUE 최대값
        CACHE사용유무               --> NOCACHE 권장
        CYCLE사용유무               --> 번호 순환, PK(=UNIQUE)에서 사용한다면 NOCYCLE
*/

-- 부서 테이블에서 사용할 부서_시퀀스  (테이블 하나당 시퀀스 하나사용)
DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100
    NOCACHE
    NOCYCLE;

-- 부서 테이블에 행(Row) 삽입
-- 부모 테이블(관계에서 PK를 가진 테이블)에 먼저 삽입을 해야 함
-- 4개 삽입한거 취소하면 다 취소됨
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION) 
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '영업부', '대구');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구');

INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울');

-- 작업의 완료
COMMIT;

-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE;

-- 사원 테이블 행(Row) 삽입
-- 자식 테이블(관계에서 FK를 가진 테이블)은 참조 무결성에 위배되지 않는 데이터만 삽입 가능
-- 부서(부서번호) - 사원(소속부서)
-- PK             - FK
-- 1,2,3,4        - 1,2,3,4중 하나만 가능 
INSERT INTO    
    EMPLOYEE 
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '구창민', 1, '과장', 'M', '95/05/01', 5000000);    -- DATE는 작은따옴표 안에 /나 -로 

INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '김민서', 1, '사원', 'F', '17/09/01', 2000000);

INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '이은영', 2, '부장', NULL, '90-09-01', 5500000);

INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01', 5000000);

-- 정상 데이터 다시 INSERT
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '신현준', 3, '대리', 'M', '98-12-01', 3500000);

COMMIT;

/******************************/

DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NO1 NUMBER,
    NO2 NUMBER
);
DROP SEQUENCE SAMPLE_SEQ;
CREATE SEQUENCE SAMPLE_SEQ NOCACHE;

-- 최초 1번은 NEXTVAL를 사용해야 CURRVAL도 사용 가능함
INSERT INTO SAMPLE(NO1) VALUES(SAMPLE_SEQ.CURRVAL);

-- NEXTVAL, CURRVAL 함께 사용
INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL, SAMPLE_SEQ.CURRVAL);

COMMIT;