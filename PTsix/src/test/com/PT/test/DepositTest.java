package com.PT.test;

import com.PT.service.DepositService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositTest extends  BaseTest{
    @Autowired
    private DepositService depositService;

    @Test
    public  void listTest(){
        try{
            Map<String,Object> res = depositService.listDepositRecord(1, 1, 3, "");
            List<Map<String,Object> > records =(List<Map<String, Object> >) res.get("records");
            OutputMessage.outputListOfMap(records);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试补足 保证金
     *
     */
    @Test
    public void rechargeDepositTest(){
        int userId = 1;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("money",1000);
        paramMap.put("cardId","312035429365234634623");
        try{
            depositService.addDepositRecord(userId,paramMap);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteRecordTest(){
        int userId = 1;
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        try{
            depositService.deleteDepositRecord(userId,ids);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
