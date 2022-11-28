DROP TABLE "ANI_BRD_GALL_LIKE";
DROP TABLE "ANI_BRD_GALL_COMMENT";
DROP TABLE "ANI_BRD_GALL";

DROP TABLE "ANI_BRD_UD_ATTACH";
DROP TABLE "ANI_BRD_UD";

DROP TABLE "ANI_BRD_FREE_COMMENT";
DROP TABLE "ANI_BRD_FREE";

DROP TABLE "ANI_USER_RETIRE";
DROP TABLE "ANI_USER_ACCESS";
DROP TABLE "ANI_USER_SLEEP";
DROP TABLE "ANI_USER";

DROP SEQUENCE ANI_USER_SEQ;
CREATE SEQUENCE ANI_USER_SEQ NOCACHE;
DROP SEQUENCE ANI_USER_RETIRE_SEQ;
CREATE SEQUENCE ANI_USER_RETIRE_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_FREE_SEQ;
CREATE SEQUENCE ANI_BRD_FREE_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_FREE_CMT_SEQ;
CREATE SEQUENCE ANI_BRD_FREE_CMT_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_GALL_SEQ;
CREATE SEQUENCE ANI_BRD_GALL_SEQ.DESC NOCACHE;
DROP SEQUENCE ANI_BRD_FREE_CMT_SEQ;
CREATE SEQUENCE ANI_BRD_FREE_CMT_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_GALL_SEQ;
CREATE SEQUENCE ANI_BRD_GALL_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_GALL_CMT_SEQ;
CREATE SEQUENCE ANI_BRD_GALL_CMT_SEQ NOCACHE;
DROP SEQUENCE ANI_BRD_UD_SEQ;
CREATE SEQUENCE ANI_BRD_UD_SEQ NOCACHE;

CREATE TABLE "ANI_USER" (
	"USER_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL        UNIQUE,
	"PW"	                VARCHAR2(64 BYTE)		NOT NULL,
	"NAME"	                VARCHAR2(50 BYTE)		NOT NULL,
	"GENDER"	            VARCHAR2(2 BYTE)		NOT NULL,
	"EMAIL"	                VARCHAR2(100 BYTE)		NOT NULL        UNIQUE,
	"MOBILE"	            VARCHAR2(11 BYTE)		NOT NULL,
	"BIRTH_YEAR"	        VARCHAR2(4 BYTE)		NULL,
	"BIRTH_DAY" 	        VARCHAR2(4 BYTE)		NULL,
	"POSTCODE"  	        VARCHAR2(5 BYTE)		NULL,
	"ROAD_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"JIBUN_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"DETAIL_ADDRESS"	    VARCHAR2(100 BYTE)		NULL,
	"EXTRA_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"PROFILE_PICTURE"	    VARCHAR2(255 BYTE)		NULL,
	"NICKNAME"	            VARCHAR2(100 BYTE)		NULL            UNIQUE,
	"AGE"	                NUMBER		            NULL,
	"AGREE_CODE"	        NUMBER		            NOT NULL,
	"SNS_TYPE"	            VARCHAR2(10 BYTE)		NOT NULL,
	"JOIN_DATE"	            DATE		            NOT NULL,
	"PW_MODIFY_DATE"	    DATE		            NOT NULL,
	"INFO_MODIFY_DATE"	    DATE		            NULL,
	"SESSION_ID"	        VARCHAR2(32 BYTE)		NULL,
	"SESSION_LIMIT_DATE"	DATE		            NULL,
	"POINT"	                NUMBER		            NOT NULL
);



CREATE TABLE "ANI_USER_SLEEP" (
	"USER_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"PW"	                VARCHAR2(64 BYTE)		NOT NULL,
	"NAME"	                VARCHAR2(50 BYTE)		NOT NULL,
	"GENDER"	            VARCHAR2(2 BYTE)		NOT NULL,
	"EMAIL"	                VARCHAR2(50 BYTE)		NULL,
	"MOBILE"	            VARCHAR2(11 BYTE)		NOT NULL,
	"BIRTH_YEAR"	        VARCHAR2(4 BYTE)		NULL,
	"BIRTH_DAY"	            VARCHAR2(4 BYTE)		NULL,
	"POSTCODE"	            VARCHAR2(5 BYTE)		NULL,
	"ROAD_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"JIBUN_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"DETAIL_ADDRESS"	    VARCHAR2(100 BYTE)		NULL,
	"EXTRA_ADDRESS"	        VARCHAR2(100 BYTE)		NULL,
	"PROFILE_PICTURE"	    VARCHAR2(255 BYTE)		NULL,
	"NICKNAME"	            VARCHAR2(100 BYTE)		NULL,
	"AGE"	                NUMBER		            NULL,
	"AGREE_CODE"	        NUMBER		            NULL,
	"SNS_TYPE"	            VARCHAR2(10 BYTE)		NOT NULL,
	"JOIN_DATE"	            DATE		            NOT NULL,
	"LAST_LOGIN_DATE"	    DATE		            NULL,
	"SLEEP_DATE"	        DATE		            NULL,
	"POINT"	                NUMBER		            NOT NULL
);

CREATE TABLE "ANI_USER_ACCESS" (
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"LAST_LOGIN_DATE"	    DATE		            NOT NULL
);

CREATE TABLE "ANI_USER_RETIRE" (
	"RETIRE_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"JOIN_DATE"	            DATE		            NULL,
	"RETIRE_DATE"	        DATE		            NULL
);

CREATE TABLE "ANI_BRD_FREE" (
	"FREE_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"FREE_TITLE"	        VARCHAR2(100 BYTE)		NOT NULL,
	"FREE_CONTENT"	        VARCHAR2(4000 BYTE)		NULL,
	"FREE_CREATE_DATE"	    DATE		            NOT NULL,
	"FREE_MODIFY_DATE"	    DATE		            NOT NULL,
	"FREE_IP"	            VARCHAR2(30 BYTE)		NOT NULL,
	"FREE_HIT"	            NUMBER		            NOT NULL
);


CREATE TABLE "ANI_BRD_FREE_COMMENT" (
	"FREE_CMT_NO"	        NUMBER		            NOT NULL,
	"FREE_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"FREE_CMT_CONTENT"	    VARCHAR2(1000 BYTE)		NOT NULL,
	"FREE_CMT_CREATE_DATE"	DATE		            NOT NULL,
	"FREE_CMT_MODIFY_DATE"	DATE		            NOT NULL,
	"FREE_CMT_IP"	        VARCHAR2(30 BYTE)		NOT NULL,
    "STATE"	                NUMBER(1,0)		        NOT NULL,
	"DEPTH"	                NUMBER(2,0)		        NOT NULL,
	"GROUP_NO"	            NUMBER		            NOT NULL,
	"GROUP_ORDER"	        NUMBER		            NOT NULL
);


CREATE TABLE "ANI_BRD_GALL" (
	"GALL_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"GALL_TITLE"	        VARCHAR2(100 BYTE)		NOT NULL,
	"GALL_CONTENT"	        VARCHAR2(4000 BYTE)     NULL,
	"GALL_HIT"	            NUMBER	DEFAULT 0	    NULL,
	"GALL_CREATE_DATE"	    DATE		            NOT NULL,
	"GALL_MODIFY_DATE"	    DATE		            NOT NULL,
	"GALL_IP"	            VARCHAR2(30 BYTE)		NOT NULL
);

CREATE TABLE "ANI_BRD_GALL_COMMENT" (
	"GALL_CMT_NO"	        NUMBER		            NOT NULL,
	"GALL_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"GALL_CMT_CONTENT"	    VARCHAR2(1000 BYTE)		NOT NULL,
	"GALL_CMT_CREATE_DATE"	DATE		            NOT NULL,
	"GALL_CMT_MODIFY_DATE"	DATE		            NOT NULL,
	"GALL_CMT_IP"	        VARCHAR2(30 BYTE)		NOT NULL
);

CREATE TABLE "ANI_BRD_GALL_LIKE" (
	"GALL_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL
);


CREATE TABLE "ANI_BRD_UD" (
	"UPLOAD_NO"	            NUMBER		            NOT NULL,
	"ID"	                VARCHAR2(32 BYTE)		NOT NULL,
	"UPLOAD_TITLE"	        VARCHAR2(100 BYTE)		NOT NULL,
	"UPLOAD_CONTENT"	    VARCHAR2(1000 BYTE)		NOT NULL,
	"UPLOAD_HIT"	        NUMBER		            NULL,
	"UPLOAD_CREATE_DATE"	DATE		            NULL,
	"UPLOAD_MODIFY_DATE"	DATE		            NULL,
	"UPLOAD_IP"	            VARCHAR2(30 BYTE)		NOT NULL
);

CREATE TABLE "ANI_BRD_UD_ATTACH" (
	"ATTACH_NO"	            NUMBER		            NULL,
	"UPLOAD_NO"	            NUMBER		            NOT NULL,
	"PATH"	                VARCHAR2(300 BYTE)		NULL,
	"ORIGIN"	            VARCHAR2(300 BYTE)		NULL,
	"FILESYSTEM"	        VARCHAR2(40 BYTE)		NULL,
	"DOWNLOAD_CNT"	        NUMBER		            NULL
);


ALTER TABLE "ANI_USER" ADD CONSTRAINT "PK_ANI_USER" PRIMARY KEY ("USER_NO");
ALTER TABLE "ANI_USER_RETIRE" ADD CONSTRAINT "PK_USER_RETIRE" PRIMARY KEY ("RETIRE_NO");
ALTER TABLE "ANI_BRD_FREE" ADD CONSTRAINT "PK_BRD_FREE" PRIMARY KEY ("FREE_NO");
ALTER TABLE "ANI_BRD_GALL" ADD CONSTRAINT "PK_BRD_GALL" PRIMARY KEY ("GALL_NO");
ALTER TABLE "ANI_BRD_UD" ADD CONSTRAINT "PK_BRD_UD" PRIMARY KEY ("UPLOAD_NO");
ALTER TABLE "ANI_BRD_UD_ATTACH" ADD CONSTRAINT "PK_BRD_UD_ATTACH" PRIMARY KEY ("ATTACH_NO");
ALTER TABLE "ANI_BRD_GALL_COMMENT" ADD CONSTRAINT "PK_BRD_GALL_COMMENT" PRIMARY KEY ("GALL_CMT_NO");
ALTER TABLE "ANI_BRD_FREE_COMMENT" ADD CONSTRAINT "PK_FREE_COMMENT" PRIMARY KEY ("FREE_CMT_NO");

ALTER TABLE "ANI_USER_ACCESS" 
ADD CONSTRAINT "FK_USER_USER_ACCESS" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_FREE" 
ADD CONSTRAINT "FK_USER_BRD_FREE" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_GALL" 
ADD CONSTRAINT "FK_USER_BRD_GALL" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_UD" 
ADD CONSTRAINT "FK_USER_BRD_UD" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_UD_ATTACH" 
ADD CONSTRAINT "FK_BRD_UD_BRD_UD_ATTA" 
FOREIGN KEY ("UPLOAD_NO") REFERENCES "ANI_BRD_UD" ("UPLOAD_NO");

ALTER TABLE "ANI_BRD_GALL_COMMENT" 
ADD CONSTRAINT "FK_BRD_GALL_BRD_GALL_COMM" 
FOREIGN KEY ("GALL_NO") REFERENCES "ANI_BRD_GALL" ("GALL_NO");

ALTER TABLE "ANI_BRD_GALL_COMMENT" 
ADD CONSTRAINT "FK_USER_BRD_GALL_COMM" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_GALL_LIKE" 
ADD CONSTRAINT "FK_GALL_GALL_LIKE" 
FOREIGN KEY ("GALL_NO") REFERENCES "ANI_BRD_GALL" ("GALL_NO");

ALTER TABLE "ANI_BRD_GALL_LIKE" 
ADD CONSTRAINT "FK_USER_BRD_GALL_LIKE" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");

ALTER TABLE "ANI_BRD_FREE_COMMENT" 
ADD CONSTRAINT "FK_BRD_FREE_BRD_FREE_COMM" 
FOREIGN KEY ("FREE_NO") REFERENCES "ANI_BRD_FREE" ("FREE_NO");

ALTER TABLE "ANI_BRD_FREE_COMMENT" 
ADD CONSTRAINT "FK_USER_BRD_FREE_COMM" 
FOREIGN KEY ("ID") REFERENCES "ANI_USER" ("ID");


-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------- TEST용 쿼리 ---------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------

INSERT INTO ANI_USER
VALUES (1, 'admin', '1234', 'Moon', 'M', 'moom97102002@gmail.com', '010', '1997', '10', '1', 'GUMCHEN-RO', 'JIBUN', 'DETAIL', 'EXTRA', 'NICK', 'NAME', '30'
, 20, 'YES', SYSDATE, SYSDATE, SYSDATE, 'SESSION-ID', SYSDATE, 10);

INSERT INTO ANI_USER
VALUES (2, 'UNIQUE-hyo', '1234', 'HYO', 'FE', 'UNIQUE-HOH12011@NAVER.COM', '010', '1994', '10', '1', 'GUMCHEN-RO', 'JIBUN', 'DETAIL', 'EXTRA', 'NI2CK', 'UNIQUE-NA7ME', '30'
, 20, 'YES', SYSDATE, SYSDATE, SYSDATE, 'SESSION-ID', SYSDATE, 10);

INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');
INSERT INTO ANI_BRD_GALL VALUES (ANI_BRD_GALL_SEQ.NEXTVAL, 'UNIQUE-hyo', 'TITLE', 'CONTENT', 0, SYSDATE, SYSDATE, '0.0.0.0');


COMMIT;