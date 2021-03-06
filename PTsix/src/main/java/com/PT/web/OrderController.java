package com.PT.web;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;
import com.PT.service.OrderService;
import com.PT.tools.PasswordUtil;
import com.PT.tools.ResponseData;
import com.PT.tools.YkatCommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
     *
     */
    @RequestMapping(value = "/orders/{type}", method = RequestMethod.GET)
    private Map listOrders(@PathVariable("user_id") int userId,
                           @PathVariable("type") String type,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "ipp",required = false, defaultValue = "5") int ipp,
                           @RequestParam(value = "q", required = false, defaultValue = "") String queryCondition,
                           HttpServletResponse response)   /*,
                                 @RequestHeader("X-YKAT-USER-ID") String headerUserId,
                                 @RequestHeader("X-YKAT-USER-ID") String accessToken)*/
    {
        ResponseData responseData = ResponseData.ok();
        try {
            Map<String, Object> resultMap = orderService.listOrder(type, page, ipp, userId, queryCondition);
            response.setStatus(200);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            responseData = ResponseData.badRequest();
            responseData.putDataValue("status_code",1);
            responseData.putDataValue("error_desc",e.getMessage());
            return responseData.getBody();
        }
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
                         @PathVariable("type") String type,
                         @RequestBody Map<String,Object> requestMap,
                         HttpServletResponse response)
    {
        try {
            String checkMessage = YkatCommonUtil.checkMapHasNull(requestMap);
            if(!"success".equals(checkMessage)){
                throw new Exception(checkMessage);
            }



            orderService.addOrder(userId,type,requestMap);
        }catch (Exception e){
            Map<String,Object> map = new HashMap();
            map.put("statusCode",1);
            map.put("errorDesc",e.getMessage());
            response.setStatus(400);
            return map;
        }

        Map<String,Object> map = new HashMap();
        map.put("success",true);
        return map;
    }

    /**
     * 删除订单
     * @param userId
     * @param type
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/orders/{type}", method = RequestMethod.DELETE)
    private Map deleteOrder(@PathVariable("user_id")int userId,
                            @PathVariable("type") int type,
                            @RequestBody Map<String,Object > requestMap )
    {
        try{
            List<String> list = (List<String>) requestMap.get("orderIds");
            orderService.deleteOrder(userId,type,list);
        }catch (Exception e){
            Map<String,Object> map = new HashMap();
            map.put("statusCode",1);
            map.put("errorDesc",e.getMessage());
            return map;
        }
        Map<String,Object> map = new HashMap();
        map.put("success",true);
        return map;
    }

    /**
     * 处理订单的接口，接收或者拒绝
     * @param userId
     * @param type
     * @param orderId
     * @param requestMap
     * @param response
     * @return
     */
    @RequestMapping(value="/orders/{type}/{order_id}",method = RequestMethod.PUT)
    private Map updateOrderState(@PathVariable(value = "user_id")int userId,
                            @PathVariable("type") int type,
                            @PathVariable("order_id") String orderId,
                            @RequestBody Map<String,Object > requestMap,
                            HttpServletResponse response ){
        ResponseData responseData = ResponseData.ok();

        try{
            String checkMessage = YkatCommonUtil.checkMapHasNull(requestMap);
            if(!"success".equals(checkMessage)){
                throw new Exception("缺少参数");
            }

            Integer status = (int) requestMap.get("state");
            orderService.updateOrderState(orderId,userId,status);

            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }

    private void saveErrorCodeAndMessage(Map<String,Object> map,int errCode, String message){
        map.put("status_code",errCode);
        map.put("error_desc",message);
    }



}
