CREATE TABLE FW_SRT (
OPT varchar(250) PRIMARY KEY
, MODE varchar(250)
, ID varchar(250)
, IV varchar(250)
);


INSERT INTO FW_SRT VALUES ('S01','Aria_ECB_ZERO','E231C123B7512A8A9027E9EE99C0C684','');
INSERT INTO FW_SRT VALUES ('S02','Aria_CBC_PKCS7','B426E1A441F6DBFC2B2D2412D0066D20','52A9A4CC4FB1EF00A72FF87583D44E5C');
INSERT INTO FW_SRT VALUES ('S03','Aes_CBC_PKCS7','BF210BE9E2ED4620B442D5AF8D000E40','CF80492ACF3166C7CC039818619E4859');
INSERT INTO FW_SRT VALUES ('SDB','Aria_CBC_PKCS7','B426E1A441F6DBFC2B2D2412D0066D20','52A9A4CC4FB1EF00A72FF87583D44E5C');

DELETE FROM FW_SQL WHERE [ID] = 'DATACRYPT_01';
INSERT INTO FW_SQL VALUES ('DATACRYPT_01','SELECT * FROM FW_SRT WHERE OPT = [OPT]');

update BZ_ID SET PIN ='424ab5a6448f7b6aca9cd65c361b672c3d853622bd29001ee15bc5c50bcfa169' WHERE USR_ID = 'id_A0';
update BZ_ID SET PIN ='337c1456c9b72fd82583e974ac3885295373b1968210cfc0cb5418c554935f4f' WHERE USR_ID = 'id_U0';