package com.PT.service;

import com.PT.bean.order.OrderInfoBean;
import com.PT.entity.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {

    /**
     * 获取 订单信息列表。
     * @param type 订单类型，有保养，维修，抢修，拖车 不同订单类型
     * @param page 第几页
     * @param ipp 每页有多少
     * @param userId 唯一用户标识
     * @param queryCondition 查询语句
     * @return
     * @throws Exception
     */
    Map<String,Object> listOrder(String type, int page, int ipp, int userId,String queryCondition) throws Exception;

    /**
     * 添加订单
     * @param userID
     * @param orderType
     * @param paramMap
     * @throws Exception
     */
    void addOrder(int userID,String orderType,Map<String, Object> paramMap) throws Exception;

    /**
     * 删除订单
     * @param userId 唯一用户标识
     * @param type 订单类型
     * @param orderIds 一组订单号
     * @throws Exception
     */
    void deleteOrder(int userId, int type, List<String> orderIds) throws Exception;

    /**
     * 处理订单
     * @param orderId 订单号 String
     * @param userId 用户唯一标识
     * @param state 更新订单后的 订单状态
     * @throws Exception
     */
    void updateOrderState(String orderId,int userId,int state) throws Exception;

    /**
     * 根据 存在 此订单号 的记录 返回true，没有返回false
     * @param orderId
     * @return
     */
    boolean isOrderIdValid(String orderId);
}
