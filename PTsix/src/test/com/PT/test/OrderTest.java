package com.PT.test;

import com.PT.service.OrderService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderTest extends BaseTest{

    @Autowired
    private OrderService orderService;

    @Test
    public void listOrder() {
        int page=1,ipp=2,userId=1;String queryCondition="",type="1";
        try {
            Map<String, Object> resultMap = orderService.listOrder(type, page, ipp, userId, queryCondition);
            OutputMessage.outputMap(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void generateOrders() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        List<String> list = new ArrayList<>();
        list.add("车辆保养");
        list.add("换轮胎");
        list.add("车损修理");
        for(int i = 0; i < 100; i++) {
            int id = Math.abs(random.nextInt());
            System.out.println(id);
            orderService.addOrder(41, 1, Integer.toString(id), list.get(id % 3), list.get(id % 3));
        }
    }
}
