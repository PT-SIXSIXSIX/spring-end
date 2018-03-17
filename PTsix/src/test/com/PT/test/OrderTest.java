package com.PT.test;

import com.PT.service.OrderService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderTest extends BaseTest{

    @Autowired
    private OrderService orderService;

    @Test
    public void listOrder() {
        int page=1,ipp=4,userId=1;String queryCondition="",type="1";
        try {
            Map<String, Object> resultMap = orderService.listOrder(type, page, ipp, userId, queryCondition);
            List<Map<String,Object> > orders =(List<Map<String, Object> >) resultMap.get("records");
            OutputMessage.outputListOfMap(orders);

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
        for(int i = 0; i < 10; i++) {
            int id = Math.abs(random.nextInt());
            //orderService.addOrder(4, 1, Integer.toString(id%3), list.get(id % 3), list.get(id % 3));
        }
    }

    /**
     * 添加订单的测试
     * 删除订单只是 更爱状态到2
     */
    @Test
    public void deleteOrders(){
        int userId = 1;
        List<String> orderIdsToDelete = new ArrayList<String>();
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        String orderId = formater.format(date);
        orderId += "000001";
        orderIdsToDelete.add(orderId);
        System.out.println(orderId);
        try{
            orderService.deleteOrder(userId,0,orderIdsToDelete);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void acceptOrder(){
        int userId = 1,status=1;
        String orderId = "20180312000001";
        try{
            orderService.updateOrderState(orderId,userId,status);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
