package com.PT.test;

import com.PT.service.OrderService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class OrderTest extends BaseTest{

    @Autowired
    private OrderService orderService;

    @Test
    public void listOrder(){
        int page=1,ipp=2,userId=1;String queryCondition="",type="1";
        try {
            Map<String, Object> resultMap = orderService.listOrder(type, page, ipp, userId, queryCondition);
            OutputMessage.outputMap(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
