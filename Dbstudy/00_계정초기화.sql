-- SCOTT 계정 초기화

-- 1. 
DROP USER SCOTT CASCADE;

-- 2. 
CREATE USER SCOTT IDENTIFIED BY TIGER;

-- 3.
GRANT CONNECT, RESOURCE TO SCOTT;
GRANT DBA TO SCOTT;     -- 뷰 생성 등 모든 권한을 가짐