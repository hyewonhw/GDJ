/*
    PL/SQL 
    
    1. 오라클의 프로그래밍 처리가 가능한 SQL
    2. 프로그래밍 형식
        [DECLARE]
            [변수 선언]
        BEGIN
            수행할 PL/SQL
        END;
    3. PL/SQL의 데이터(변수, 상수)는 서버메시지로 출력가능
    4. 서버메시지 출력을 위해서 최초 1회 설정이 필요(디폴트세팅이 SET SERVEROUTPUT OFF;이기 때문)
        SET SERVEROUTPUT ON;
*/

-- 기초 데이터 준비

-- HR 계정의 EMPLOYEES 테이블을 SCOTT 계정으로 복사해 오기
CREATE TABLE EMPLOYEES 
    AS (SELECT *
          FROM HR.EMPLOYEES);
-- 기본키 다시 생성
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT PK_EMPLOYEES PRIMARY KEY(EMPLOYEE_ID);


-- 서버메시지 출력 가능 상태로 변경
-- 한 번만 실행하면 됨
SET SERVEROUTPUT ON;

-- 서버메시지 출력
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello');  -- Hello 출력 후 줄 바꿈
END;


-- //1. 스칼라 변수 선언//
-- 스칼라 변수 : 직접 타입을 명시하는 변수  (직접변수선언)
-- 대입 연산자 : 콜론등호(:=)
-- 변수 선언은 DECLARE에서 처리
DECLARE
    -- 변수 선언
    NAME VARCHAR2(20 BYTE);
    AGE NUMBER(3);
BEGIN 
    NAME := '조혜원';
    AGE := 25;
    DBMS_OUTPUT.PUT_LINE('내 이름은' || NAME || '입니다.');
    DBMS_OUTPUT.PUT_LINE('내 나이는' || AGE || '입니다');
END;


-- //2. 참조 변수 선언//
-- 참조 변수 : 특정 칼럼의 타입을 그대로 사용하는 변수
-- 선언 방법
-- 테이블명.칼럼%TYPE

DECLARE
    -- 참조 변수 선언
    NAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    NAME :='민경태';
    DBMS_OUTPUT.PUT_LINE('내 이름은 ' || NAME || '입니다.');
END;


-- 참조 변수 활용
-- 테이블의 데이터를 읽어 참조 변수에 저장
-- SELECT 칼럼 INTO 변수 FROM 테이블 WHERE 조건식

-- 문제. EMPLOYEE_ID가 100인 회원의 FIRST_NAME, LAST_NAME, SALARY 정보를 참조 변수에 저장
DECLARE
    F_NAME EMPLOYEES.FIRST_NAME%TYPE;
    L_NAME EMPLOYEES.LAST_NAME%TYPE;
    V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN 
    SELECT
           FIRST_NAME, LAST_NAME, SALARY 
      INTO 
           F_NAME, L_NAME, V_SALARY 
      FROM 
           EMPLOYEES
     WHERE 
           EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(F_NAME || L_NAME || V_SALARY);
END;


-- //3. 레코드 변수 선언//
-- 레코드 : 필드의 모임
-- DB에서 레코드란? 행(ROW)
-- 레코드 변수 : 여러 칼럼을 동시에 저장하는 변수 (자바의 클래스타입같은거임)
-- 레코드 변수 정의와 선언 과정으로 진행

DECLARE
    -- 레코드 변수 타입 정의(타입 만들기)
    TYPE MY_TYPE IS RECORD(
        V_FNAME EMPLOYEES.FIRST_NAME%TYPE,
        V_LNAME EMPLOYEES.LAST_NAME%TYPE,
        V_SALARY EMPLOYEES.SALARY%TYPE
    );
    -- 레코드 변수 선언(변수 만들기)
    V_ROW MY_TYPE;
 BEGIN 
    SELECT
           FIRST_NAME, LAST_NAME, SALARY
      INTO 
           V_ROW
      FROM 
           EMPLOYEES
     WHERE
           EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME || V_ROW.V_LNAME || V_ROW.V_SALARY);
END;


-- //4. 행 변수 선언하기//
-- 행(ROW) 전체를 저장할 수 있는 타입
-- 선언 방법
-- 테이블%ROWTYPE

DECLARE
   -- 행 변수 선언
    V_ROW EMPLOYEES%ROWTYPE;
 BEGIN 
    SELECT *        --> 실무에서는 하나하나 적어야함
      INTO V_ROW
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(V_ROW.FIRST_NAME || V_ROW.LAST_NAME || V_ROW.SALARY);
END;


-- //5. IF//
/*
    IF 조건식 THEN
        실행문
    ELSIF 조건식 THEN
        실행문
    ELSE
        실행문
    END IF;
*/
-- 1) 성인/미성년자
DECLARE
    AGE NUMBER(3);
    RESULT VARCHAR2(20 BYTE);
BEGIN
    AGE := 45;
    IF AGE >= 20 THEN
        RESULT := '성인';
    ELSE
        RESULT := '미성년자';
    END IF;
    DBMS_OUTPUT.PUT_LINE(AGE || '살은' || RESULT || '입니다.');
END;


-- 2) 학점(A,B,C,D,F)
DECLARE
    SCORE NUMBER(3);
    GRADE VARCHAR2(1 BYTE);
BEGIN 
    SCORE := 100;
    IF SCORE >= 90 THEN
        GRADE := 'A';
    ELSIF SCORE>= 80 THEN
        GRADE := 'B';
    ELSIF SCORE >= 70 THEN
        GRADE := 'C';
    ELSIF SCORE >= 60 THEN
        GRADE := 'D';
    ELSE
        GRADE := 'F';
    END IF;
    DBMS_OUTPUT.PUT_LINE(SCORE || '점은' || GRADE || '학점입니다');
END;


-- 3) EMPLOYEE_ID가 150인 사원의 연봉을 참조하여
-- 15000 이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 '저연봉'
DECLARE
    SAL EMPLOYEES.SALARY%TYPE;
    RES VARCHAR2(20 BYTE);
BEGIN
    SELECT SALARY
      INTO SAL
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 150;
    IF SAL >= 15000 THEN
        RES := '고연봉';
    ELSIF SAL >= 10000 THEN
        RES := '중연봉';
    ELSE
        RES := '저연봉';
    END IF;
    DBMS_OUTPUT.PUT_LINE('연봉 ' || SAL || '은 ' || RES || '입니다');
END;


-- //6. CASE문// 
/*
    CASE
        WHEN 조건식 THEN
            실행문
        WHEN 조건식 THEN
            실행문
        ELSE
            실행문
    END CASE;
*/

-- 주민번호를 이용해 성별 조회하기
DECLARE
    SNO VARCHAR2(14 BYTE);
    GENDER_NUM VARCHAR2(1 BYTE);
    GENDER VARCHAR2(1 BYTE);
BEGIN
    SNO := '901010-1234567';
    SELECT SUBSTR(SNO, 8, 1)
      INTO GENDER_NUM
      FROM DUAL;
    CASE
        WHEN GENDER_NUM = '1' THEN
            GENDER := 'M';
        WHEN GENDER_NUM = '2' THEN
            GENDER := 'F';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('성별은 ' || GENDER || '입니다.');
END;


-- //7. WHILE문//
/*
    WHILE 조건식 LOOP
        실행문
    END LOOP
*/

-- 1) 1 ~ 5 출력하기
DECLARE
    N NUMBER(1);
BEGIN
    N := 1;     
    WHILE N <= 5 LOOP       --> N은 5까지
        DBMS_OUTPUT.PUT_LINE(N);
        N := N + 1;     --> 출력 후에는 증가
    END LOOP;       --> LOOP 종료
END;    --> BEGIN 종료

-- 2) EMPLOYEES 테이블의 모든 사원의 FIRST_NAME, LAST_NAME 조회
-- FIRST_NAME과 LAST_NAME을 레코드 변수에 저장하고 해당 값을 조회
-- 레코드 변수는 사원 한 명의 정보만 저장할 수 있으므로 한 명씩 저장 후 출력
-- 사원번호는 100 ~ 206 값을 가짐
DECLARE 
    -- 참조 변수 선언
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;  --> 사원번호 저장 할 변수
    -- 레코드 변수 정의
    TYPE NAME_TYPE IS RECORD(
        FNAME EMPLOYEES.FIRST_NAME%TYPE,  --> EMPLOYEES테이블의 FIRST_NAME 저장할 TYPE
        LNAME EMPLOYEES.LAST_NAME%TYPE    --> EMPLOYEES테이블의 LAST_NAME 저장할 TYPE
    );
    -- 레코드 변수 선언
    FULL_NAME NAME_TYPE;
BEGIN
    -- 사원번호(100~206) 기준으로 WHILE LOOP
    EMP_NO := 100;                          --> 사원번호는 100부터
    WHILE EMP_NO <= 2O6 LOOP                --> 206번까지 LOOP 돌리겠다
        SELECT FIRST_NAME, LAST_NAME        --> (3) FIRST_NAME과 LAST_NAME을
          INTO FULL_NAME                    --> (4) FULL_NAME에 저장하겠다
          FROM EMPLOYEES                    --> (1) EMPLOYEES 테이블에서
         WHERE EMPLOYEE_ID = EMP_NO;        --> (2) EMPLOYEE_ID와 EMP_NO가 같으면
        DBMS_OUTPUT.PUT_LINE(FULL_NAME.FNAME || ' ' || FULL_NAME.LNAME);
        EMP_NO := EMP_NO + 1;
    END LOOP;
END;


-- //8. FOR문//
/*
    FOR 변수 IN 시작..종료 LOOP
        실행문
    END LOOP;
*/

-- 1) 1 ~ 5
DECLARE
    N NUMBER(1);
BEGIN
    FOR N IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
    END LOOP;
END;


-- 2) 짝수/홀수
DECLARE
    N NUMBER;
    MODULAR NUMBER;                  --> 2로 나눈 나머지 값을 저장 할 변수
    RESULT VARCHAR2(10 BYTE);        --> 결과 저장 할 변수
BEGIN
    FOR N IN 1..10 LOOP
        SELECT MOD(N, 2)             --> MOD함수 N을 2로 나눈 나머지를
          INTO MODULAR               --> MODULAR에 저장하겠다
          FROM DUAL;        
        IF MODULAR = 0 THEN          --> MODULAR가 0이라면
            RESULT := '짝수';        --> 결과에 '짝수'저장
        ELSE                         --> 아니라면
            RESULT := '홀수';        --> 결과에 '홀수'저장
        END IF;                      --> IF문 종료
        DBMS_OUTPUT.PUT_LINE(N || '은' || RESULT || '입니다.');
    END LOOP;                        --> LOOP종료
END;


-- 3) EMPLOYEES 테이블의 모든 사원의 연봉합계/평균 조회하기
-- 사원을 LOOP돌려서 나오는 결과를 특정변수에 넣어주고 사원번호+1
DECLARE
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;  --> 사원번호(EMPLOYEE_ID) 저장 할 변수 EMP_NO
    SAL EMPLOYEES.SALARY%TYPE;          --> 연봉(SALARY) 저장 할 변수 SAL
    TOTAL NUMBER;                       --> 합계 저장 할 변수
    CNT NUMBER;                         --> 사원수 저장 할 변수
    AVERAGE NUMBER;                     --> 평균 저장 할 변수
BEGIN
    TOTAL := 0;                         --> TOTAL 초기화 
    CNT := 0;                           --> CNT 초기화
    FOR EMP_NO IN 100..206 LOOP         --> 사원번호 100~206 LOOP 돌리겠다
        SELECT SALARY                   --> (3) 연봉을
          INTO SAL                      --> (4) 변수SAL에 집어넣어라
          FROM EMPLOYEES                --> (1) EMPLOYEES 테이블에서
         WHERE EMPLOYEE_ID = EMP_NO;    --> (2) EMPLOYEE_ID와 ENP_NO가 같으면
        TOTAL := TOTAL + SAL;           --> (5) TOTAL에 합계 + 연봉을 저장
        CNT := CNT + 1;                 --> (6) CNT 1증가
    END LOOP;                           --> LOOP 종료
    AVERAGE := TOTAL / CNT;             --> AVERAGE에 TOTAL/CNT값을 저장
    DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || TOTAL);
    DBMS_OUTPUT.PUT_LINE('연봉평균 : ' || AVERAGE);
END;

-- 4) DEPARTMENT_ID가 50인 사원정보를 DEPT50 테이블에 복사하기
--  (1) EMPLOYEES와 구조가 동일한 DEPT50 테이블 생성
--  (2) 행 변소를 이용해 EMPLOYEES 정보 읽기
--  (3) 행 변수의 DEPARTMENT_ID가 50 이면 DEPT50에 INSERT
CREATE TABLE DEPT50 
    AS (SELECT * FROM EMPLOYEES WHERE 1 = 2);

DECLARE 
    V_ROW EMPLOYEES%ROWTYPE;
BEGIN
    FOR V_ROW IN(SELECT * FROM EMPLOYEES) LOOP
        IF V_ROW.DEPARTMENT_ID = 50 THEN
            INSERT INTO DEPT50 VALUES V_ROW;
        END IF;
    END LOOP;
    COMMIT; --> INSERT의 완료
END;


-- //9. EXIT문//
-- LOOP 종료 시키는 문장
DECLARE
    N NUMBER;
BEGIN
    N := 1;             --> N은 1이고 (=시작점이 1)
    WHILE TRUE LOOP
        IF N > 100 THEN -->100보다 커졌다면
            EXIT;    --> LOOP종료
        END IF;     --> IF 종료
        N := N + 1;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(N);
END;


-- //10. CONTINUE문//
-- LOOP문의 시작부터 다시 시작

-- DEPARTMENT_ID가 50인 사원을 제외하고 연봉합계 조회하기
DECLARE 
    EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE;      --> EMP_ID변수는 EMPLOYEE_ID와 동일한 TYPE
    SAL EMPLOYEES.SALARY%TYPE;              --> SAL변수는 EMPLOYEE테이블의 SALARY와 동일한 TYPE
    DEPT_ID EMPLOYEES.DEPARTMENT_ID%TYPE;
    TOTAL NUMBER;
BEGIN
    TOTAL := 0;
    FOR EMP_NO IN 100..206 LOOP
        SELECT SALARY, DEPARTMENT_ID        --> (3) SALARY와 DEPARTMENT_ID를 
          INTO SAL, DEPT_ID                 --> (4) 변수SAL과 변수 DEPT_ID에 저장
          FROM EMPLOYEES                    --> (1) EMPLOYEES 테이블에서
         WHERE EMPLOYEE_ID = EMP_ID;        --> (2) EMPLOYEE_ID가 EMP_ID와 일치하면
        IF DEPT_ID = 50 THEN                --> 만약 DEPT_ID가 50이라면
            CONTINUE;                       --> FOR LOOP로 돌아가서 다음 번호로 FOR문 재실행/ TOTAL(아래의 누적시키는 문장)로 안감
        END IF;
        TOTAL := TOTAL + SAL;               --> TOTAL 변수에 합계 + 연봉을 누적시키는 문장
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || TOTAL);
END;

SELECT SUM(SALARY) FROM EMPLOYEES WHERE DEPARTMENT_ID != 5O;


-- //11. 배열 선언하기//
-- 테이블 타입 : 특정 칼럼의 모든 데이터를 배열에 저장

DECLARE 

    -- SALARY 칼럼의 모든 값을 저장할 배열(SALARIES) 생성
    
    
    -- 배열의 TYPE 정의
    TYPE SALARY_TYPE IS TABLE OF EMPLOYEES.SALARY%TYPE INDEX BY BINARY_INTEGER;   --> EMPLOYEES의 SALARY타입 배열의 인덱스를 INTEGER(정수)로 쓰겠다

    -- 배열의 선언
    SALARIES SALARY_TYPE;            --> SALARIES배열은 SALARY타입
    
    -- 인덱스 선언
    I BINARY_INTEGER;
    
    -- 행(ROW) 변수 선언
    V_ROW EMPLOYEES%ROWTYPE;      --> 행변수 V_ROW는 EMPLOYEES테이블의 ROWTYPE
    
BEGIN
    I := 0;                                       --> I의 초기화
    FOR V_ROW IN(SELECT * FROM EMPLOYEES) LOOP      --> EMPLOYEES테이블의 전체를 가져와서 하나씩 V_ROW로 넘겨라
        SALARIES(I) := V_ROW.SALARY;                --> 자바에서의 SALARIES[i]와 같은거 := V_ROW의 SALARY
        I := I + 1;
    END LOOP;
    
    FOR I IN 0..106 LOOP
        DBMS_OUTPUT.PUT_LINE(SALARIES(I));
    END LOOP;

END;


-- //12. 예외처리//
-- EXCEPTION
/*
    형식
    
    EXCEPTION
        WHEN 예외 THEN
            예외처리
        WHEN 예외 THEN
            예외처리
        WHEN OTHERS THEN
            예외처리
*/
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 0; --> 존재하지 않는 사원 조회
     --WHERE DEPARMENT_ID = 50;       --> 변수에 저장 할 데이터가 하나만 만들어져야 정상작동하는데 변수에 저장 할 데이터가 너무 많아서 오류
EXCEPTION
    WHEN NO_DATA_FOUND THEN  --> NO_DATA_FOUND예외사유
        DBMS_OUTPUT.PUT_LINE('조회된 데이터가 없음');    -->SQLCODE는 예외코드나오는 약속된코드
    WHEN OTHERS THEN                                     
        DBMS_OUTPUT.PUT_LINE('예외코드' || SQLCODE); 
        DBMS_OUTPUT.PUT_LINE('예외메시지' || SQLERRM);
END;
