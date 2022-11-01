DROP SEQUENCE BOARD1_SEQ;
CREATE SEQUENCE BOARD1_SEQ NOCACHE;

DROP TABLE BOARD1;
CREATE TABLE BOARD1(
	BOARD_NO    NUMBER NOT NULL,
	WRITER      VARCHAR2(100 BYTE),
	TITLE       VARCHAR2(100 BYTE) NOT NULL,
	CONTENT     VARCHAR2(500 BYTE),
	CREATE_DATE DATE,
	CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);