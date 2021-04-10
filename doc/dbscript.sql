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
