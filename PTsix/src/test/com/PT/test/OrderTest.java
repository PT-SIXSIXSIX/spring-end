package com.PT.test;

import com.PT.service.OrderService;
import com.PT.tools.YkatConstant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderTest extends BaseTest{

    @Autowired
    private OrderService orderService;

    @Test
    public void updateTest(){
        int userId = 1;
        String orderId = "20180312000003";
        Integer state = YkatConstant.ORDER_STATE_ACCEPT;
        try{
            orderService.updateOrderState(orderId,userId,state);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
