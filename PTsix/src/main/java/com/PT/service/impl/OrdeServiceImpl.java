package com.PT.service.impl;

import com.PT.bean.order.OrderInfoBean;
import com.PT.dao.OrderMapper;
import com.PT.entity.Order;
import com.PT.entity.OrderExample;

import com.PT.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdeServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderInfoBean> listOrder(int type, int page, int ipp, int userId){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(userId);// 商店id主键

//        criteria.andTypeEqualTo(String.valueOf(type));//订单类型
        criteria.andTypeEqualToWithTableName("oder",String.valueOf(type));
        example.setOrderByClause("oder.id desc");//按照id号降序排列
        //指定页数，ipp条内容
        PageHelper.startPage(page,ipp);

        List<OrderInfoBean> orders = orderMapper.selectOrderInfoByExample(example);


        return orders;
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.insertSelective(order);

    }
}
