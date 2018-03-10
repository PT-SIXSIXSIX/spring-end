package com.PT.web;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;
import com.PT.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/{user_id}")
public class OrderController {
    //GET /api/v1/users/user_id/orders/type?page=?ipp=?q=

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表
     * @param userId 管理员唯一表示标识
     * @param type 订单类型
     * @param page 第几页
     * @param ipp 每页项
     * @param queryCondition 查询条件
     * @return 订单列表json
     * {
            "max_page": 1,
            "records": [
                {
                "order_id": 1,
                "driver_name": "Hello, world!",
                "created_at": "Hello, world!",
                "project_type": "Hello, world!",
                "project_descp": "Hello, world!",
                "state": 1
                }
            ]
        }
     */
    @RequestMapping(value = "/orders/{type}", method = RequestMethod.GET)
    private Map listOrders(@PathVariable("user_id") int userId,
                                 @PathVariable("type") int type,
                                 @RequestParam(value = "page",required = true) int page,
                                 @RequestParam(value = "ipp",required = true) int ipp,
                                 @RequestParam(value = "q",required = true) String queryCondition)/*,
                                 @RequestHeader("X-YKAT-USER-ID") String headerUserId,
                                 @RequestHeader("X-YKAT-USER-ID") String accessToken)*/
    {

        List<OrderInfoBean> orders = orderService.listOrder(type,page,ipp,userId);
        Map<String,Object> map = new HashMap<>();
        map.put("max_page",page);
        map.put("records",orders);

        return map;
    }

    /**
     * 添加订单
     * @param userId
     * @param type
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/orders/{type}", method = RequestMethod.POST)
    private Map addOrder(@PathVariable("user_id") int userId,
                         @PathVariable("type") int type,
                         @RequestBody Map<String,Object> requestMap)
    {
        int driverId = (int) requestMap.get("driver_id");
        String projectType = (String) requestMap.get("project_type");

        Order order = new Order();
        order.setCreatedAt(new Date());
        order.setStoreId(userId);
        order.setDriverId(driverId);
        order.setStatus(0);
        order.setType(projectType);
        try {
            orderService.addOrder(order);
        }catch (Exception e){
            Map<String,Object> map = new HashMap();
            map.put("status_code",1);
            map.put("error_desc",e.getMessage());
            return map;
        }

        Map<String,String> map = new HashMap();
        map.put("message","success");
        return map;
    }

    @RequestMapping(value = "/orders/{type}", method = RequestMethod.DELETE)
    private Map deleteOrder(@PathVariable("user_id")int userId,
                            @PathVariable("type") int type,
                            @RequestBody Map<String,Object > requestMap )
    {
        try{
            List<String> list = (List<String>) requestMap.get("order_ids");
            orderService.deleteOrder(userId,type,list);
        }catch (Exception e){
            Map<String,Object> map = new HashMap();
            map.put("status_code",1);
            map.put("error_desc",e.getMessage());
            return map;
        }
        Map<String,String> map = new HashMap();
        map.put("message","success");
        return map;
    }

    @RequestMapping(value="/orders/{type}/{order_id}",method = RequestMethod.PUT)
    private Map updateOrderState(@PathVariable(value = "user_id")int userId,
                            @PathVariable("type") int type,
                            @PathVariable("order_id") String orderId,
                            @RequestBody Map<String,Integer > requestMap){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Integer state =  requestMap.get("state");
        if( !orderService.isOrderIdValid(orderId) )//没有找到对应订单
        {
            saveErrorCodeAndMessage(resultMap,1,"没有对应的订单");
            return resultMap;
        }
        //参数缺失
        if(state==null){
            saveErrorCodeAndMessage(resultMap,1,"参数缺失");
            return requestMap;
        }

        orderService.updateOrderState(orderId,userId,state);

        resultMap.put("success","true");
        return resultMap;
    }

    private void saveErrorCodeAndMessage(Map<String,Object> map,int errCode, String message){
        map.put("status_code",errCode);
        map.put("error_desc",message);
    }

}
