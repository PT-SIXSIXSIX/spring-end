
DELIMITER $$
create  PROCEDURE genBillsNoPROC(IN tableName VARCHAR(30),IN colName VARCHAR(30),OUT billsNoResult VARCHAR(100))
BEGIN   
  
DECLARE sql_2 VARCHAR(1000);   
  
DECLARE sql_4 VARCHAR(1000);   
DECLARE sql_5 VARCHAR(1000);  

  
SET sql_2 = CONCAT("select max(",colName,"),instr(max(",colName,"),DATE_FORMAT(NOW(),'%Y%m%d')) into @tableMaxValue,@sqlResult from ",tableName);  
#执行sql_2SQL语句;  
SET @second_sql=sql_2;   
PREPARE stmt2 FROM @second_sql;   
EXECUTE stmt2;   
  

  
#打印变量的值;  
SELECT @sqlResult;  
  
IF @sqlResult > 0 THEN  
    #根据最大的单号如:SP20140101001 单号前缀SP ，最大单号长度-单号前缀长度 = 数字部分 ->转成数字 +1生成下一个单号  
    SET sql_4 = CONCAT("select CONVERT(",@tableMaxValue,",SIGNED)+1 into @billsNo from dual");  
    #执行sql_4SQL语句;  
    SET @four_sql=sql_4;   
    PREPARE stmt4 FROM @four_sql;   
    EXECUTE stmt4;   
ELSE  
    SET sql_5 = CONCAT("SELECT concat(DATE_FORMAT(NOW(),'%Y%m%d'),'000001') into @billsNo from dual");   
    #执行sql_5SQL语句;  
    SET @five_sql=sql_5;   
    PREPARE stmt5 FROM @five_sql;   
    EXECUTE stmt5;   
END IF;  
  
#设置返回结果(单号前缀+数字部分如:20140410001);  
SET billsNoResult := @billsNo;
END$$   
DELIMITER ;