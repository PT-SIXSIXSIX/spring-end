package com.PT.service;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {

    Map<String,Object> listOrder(int type, int page, int ipp, int userId);
    void addOrder(Order order);
    void deleteOrder(int userId, int type, List<String> orderIds);
    void updateOrderState(String orderId,int storeId,int state);
    boolean isOrderIdValid(String orderId);
}
