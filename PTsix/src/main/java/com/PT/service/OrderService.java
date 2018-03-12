package com.PT.service;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {

    Map<String,Object> listOrder(int type, int page, int ipp, int userId,String queryCondition) throws Exception;
    void addOrder(int userID,int driverId,String projectType) throws Exception;
    void deleteOrder(int userId, int type, List<String> orderIds) throws Exception;
    void updateOrderState(String orderId,int storeId,int state) throws Exception;
    boolean isOrderIdValid(String orderId);
}
