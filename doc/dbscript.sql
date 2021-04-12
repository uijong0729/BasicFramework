-- Sql Server Management Studio
-- SQL server ±¸¼º 2019

USE [HUMAN]
GO

INSERT INTO [dbo].[FW_SQL]
           ([SQL]
           ,[KEY])
     VALUES
           ('SELECT * FROM STAFF WHERE NAME = [NAME]', 'STAFF01')
GO

INSERT INTO [dbo].[FW_SQL]
           ([SQL]
           ,[KEY])
     VALUES
           ('UPDATE STAFF SET NAME = [NAME] WHERE STAFF_ID = [STAFF_ID]', 'STAFF02')
GO

INSERT INTO [dbo].[STAFF]
           ([STAFF_ID]
           ,[AGE]
           ,[NAME]
           ,[GENDER])
     VALUES
           ('2', '20', 'BBB', 'M')
GO


INSERT INTO [dbo].[STAFF]
           ([STAFF_ID]
           ,[AGE]
           ,[NAME]
           ,[GENDER])
     VALUES
           ('1', '20', 'AAA', 'M')
GO


--------------------------------------


DROP TABLE EMP ;

CREATE TABLE EMP (
EMP_N varchar(50) PRIMARY KEY
, EMP_NM varchar(50)
, HP_N varchar(50)
, DEPT_N varchar(50)
);

INSERT INTO EMP VALUES ('N01','ABC','','D01');
INSERT INTO EMP VALUES ('N02','ZZZ','','D01');
INSERT INTO EMP VALUES ('N03','ABC','','D02');

CREATE TABLE EMP (
KEY varchar(500) PRIMARY KEY
, SQL varchar(50)
);



INSERT INTO FW_SQL VALUES ('EMP001D_01', 'SELECT EMP_N, EMP_NM, HP_N, DEPT_N FROM EMP WHERE EMP_NM = [EMP_NM] ORDER BY EMP_N');

INSERT INTO FW_SQL VALUES ('EMP001D_02', 'UPDATE EMP SET HP_N = [HP_N] WHERE EMP_N =[EMP_N]' );

