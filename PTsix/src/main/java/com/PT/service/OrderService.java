package com.PT.service;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;

import java.util.List;


public interface OrderService {

    List<OrderInfoBean> listOrder(int type, int page, int ipp, int userId);
    void addOrder(Order order);
}
