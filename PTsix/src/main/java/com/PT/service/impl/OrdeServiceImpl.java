package com.PT.service.impl;

import com.PT.bean.order.OrderInfoBean;
import com.PT.dao.OrderMapper;
import com.PT.dao.YkatCommonUtilMapper;
import com.PT.entity.Order;
import com.PT.entity.OrderExample;

import com.PT.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdeServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private YkatCommonUtilMapper ykatCommonUtilMapper;
    @Override
    public Map<String,Object> listOrder(int type, int page, int ipp, int userId){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(userId);// 商店id主键

//      criteria.andTypeEqualTo(String.valueOf(type));//订单类型
        criteria.andTypeEqualToWithTableName("oder",String.valueOf(type));
        example.setOrderByClause("oder.id desc");//按照id号降序排列
        //指定页数，ipp条内容
        PageHelper.startPage(page,ipp);

        List<OrderInfoBean> orders = orderMapper.selectOrderInfoByExample(example);
        //PageHelper.clearPage();
        //查询总条数
        example.clear();
        example.createCriteria().andTypeEqualTo(String.valueOf(type)).andStoreIdEqualTo(userId);
        int maxPage = (orderMapper.countByExample(example)-1)/ipp + 1;
        Map<String,Object> map = new HashMap<>();
        map.put("max_page",maxPage);
        map.put("records",orders);
        return map;
    }

    @Override
    public void addOrder(Order order) {
        Map<String,String> map = new HashMap();
        map.put("tableName","ykat_orders");
        map.put("colName","order_id");
        ykatCommonUtilMapper.generateAutoIncrementId(map);
        String generatedOrderId = map.get("billsNoResult");
        order.setOrderId(generatedOrderId);
        orderMapper.insertSelective(order);
    }

    @Override
    public void deleteOrder(int userId, int type, List<String> orderIds){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIds);
        criteria.andStoreIdEqualTo(userId);
        criteria.andTypeEqualTo(String.valueOf(type));

        orderMapper.deleteByExample(example);
    }

    @Override
    public void updateOrderState(String orderId, int storeId, int status) {

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andStoreIdEqualTo(storeId);

        Order order = new Order();
        order.setStatus(status);

        orderMapper.updateByExampleSelective(order, example);
    }

    @Override
    public boolean isOrderIdValid(String orderId){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        if( orderMapper.countByExample(example) <= 0 ){
            return false;
        }
        return true;
    }
}
