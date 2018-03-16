package com.PT.test;

import com.PT.service.DepositService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class DepositTest extends  BaseTest{
    @Autowired
    private DepositService depositService;

    @Test
    public  void listTest(){
        try{

            Map<String,Object> res = depositService.listDepositRecord(1, 1, 3, "");
            OutputMessage.outputMap(res);
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

    }
}
