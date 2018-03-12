package com.PT.dao;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;
import com.PT.entity.OrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    void getGeneratedOrderId(Map map);

    List<Map<String,Object> > selectOrderFromViewByOrderID(String orderId);

    List<Map> selectOrderInfoByFactor(@Param("map") Map map);

    int countOrderInfoByFactor(@Param("map") Map map);
}