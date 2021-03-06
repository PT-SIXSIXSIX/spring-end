package com.PT.service.impl;

import com.PT.dao.*;
import com.PT.entity.*;

import com.PT.service.OrderService;
import com.PT.service.LogService;
import com.PT.tools.QueryToMap;
import com.PT.tools.ToStrings;
import com.PT.tools.YkatCommonUtil;
import com.PT.tools.YkatConstant;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private YkatCommonUtilMapper ykatCommonUtilMapper;

    @Autowired
    private SettleAccRecordMapper settleAccRecordMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private LogService logService;

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
    @Override
    public Map<String,Object> listOrder(String type, int page, int ipp, int userId, String queryCondition) throws Exception{



        Map factors = new HashMap();
        if(queryCondition!=null && !"".equals(queryCondition)) { //有搜索条件时
            factors = QueryToMap.stringToMap(queryCondition);
            if (factors.containsKey("time")) {
                YkatCommonUtil.putFromAndToDate(factors, (String) factors.get("time"));
            }
        }
        factors.put("userId",userId);
        factors.put("orderType",type);


        //指定页数，ipp条内容
        PageHelper.startPage(page,ipp);

        List<Map > orders = orderInfoMapper.selectOrderInfoByFactor(factors);
        //PageHelper.clearPage();
        //查询总条数
        int maxPage = (orderInfoMapper.countOrderInfoByFactor(factors)-1)/ipp + 1;
        Map<String,Object> map = new HashMap<>();
        map.put("maxPage",maxPage);
        map.put("records",orders);
        return map;
    }

    /**
     * 添加一个订单
     * @param userId
     * @param orderType
     * @param parameterMap
     * @throws Exception
     */

    @Transactional
    @Override
    public void addOrder(int userId, String orderType, Map<String, Object> parameterMap) throws Exception {

        String checkMessage = YkatCommonUtil.checkMapHasNull(parameterMap);
        if(!"success".equals(checkMessage)){
            throw new Exception(checkMessage);
        }

        Date orderedAt = YkatCommonUtil.getDateFromMillis((Long) parameterMap.get("orderedAt"));
        Integer driverId = (Integer) parameterMap.get("driverId");
        Integer projectId = (Integer)parameterMap.get("projectId");



        Order order = new Order();
        order.setCreatedAt(new Date());//创建时间
        order.setOrderedAt(orderedAt);
        order.setDriverId(driverId);//司机ID
        order.setStatus(YkatConstant.ORDER_STATE_IDLE);//订单状态
        order.setType(orderType);
        order.setProjectId(projectId);



        //订单号
        Map<String,String> map = new HashMap();
        map.put("tableName","ykat_orders");
        map.put("colName","order_id");
        ykatCommonUtilMapper.generateAutoIncrementId(map);
        String generatedOrderId = map.get("billsNoResult");

        order.setOrderId(generatedOrderId);//

        //根据userId 查询 storeID;
        Integer storeId = ykatCommonUtilMapper.getStoreIdByUserId(userId);


        order.setStoreId(storeId);

        orderMapper.insertSelective(order);

        logService.insertLog(userId,"insert","into table ykat_oders whose driverId = "+driverId+" and " +
                "orderId = "+generatedOrderId);
    }

    /**
     * 删除订单
     * @param userId 唯一用户标识
     * @param type 订单类型
     * @param orderIds 一组订单号
     * @throws Exception
     */
    @Transactional
    @Override
    public void deleteOrder(int userId, int type, List<String> orderIds) throws Exception{
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIds);
        //criteria.andStoreIdEqualTo(userId);
        criteria.andTypeEqualTo(String.valueOf(type));
        Order updateTo = new Order();
        updateTo.setStatus(YkatConstant.ORDER_STATE_DELETE);//删除状态;


        int res = orderMapper.updateByExampleSelective(updateTo,example);
        if(res<=0){
            throw new Exception("删除失败");
        }else{
            String desc = ToStrings.listToStrings(orderIds, '&');
            logService.insertLog(userId, "delete","on table ykat_orders: by ids in ["+desc+"]");
        }

    }

    /**
     * 处理订单
     * @param orderId 订单号 String
     * @param userId 用户唯一标识
     * @param status 更新订单后的 订单状态
     * @throws Exception
     */
    @Transactional
    @Override
    public void updateOrderState(String orderId, int userId, Integer status) throws Exception {



        Integer orderStatus = orderInfoMapper.getStatusByOrderId(orderId);
        if(!orderStateCheck(orderStatus,status)){
            throw new  Exception("订单状态错误");
        }

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);


        Order order = new Order();
        order.setStatus(status);

        int updateSize = orderMapper.updateByExampleSelective(order, example);//更新状态

        if (updateSize>0){
            logService.insertLog(userId,"update","on table ykat_orders: set status to "+status);
            if(status==1) {//接受订单
                SettleAccRecord settleRecord = new SettleAccRecord();

                Map<String,String> paramMap = new HashMap();
                paramMap.put("tableName","ykat_settle_account_records");
                paramMap.put("colName","set_acc_id");
                ykatCommonUtilMapper.generateAutoIncrementId(paramMap);
                String generatedSettleId = paramMap.get("billsNoResult");//获取结算订单号

                settleRecord.setSetAccId(generatedSettleId);//结算单号
                settleRecord.setCreatedAt(new Date());//记录创建时间
                settleRecord.setStatus(YkatConstant.SETTEL_RECORD_STATE_IDLE);//待结算
                //获取订单 主键id，订单价格
                List<Map<String,Object> > orderInfoMaps = orderInfoMapper.selectOrderFromViewByOrderID(orderId);
                if(null!= orderInfoMaps && orderInfoMaps.size()>0){
                    Map<String,Object> infoMap = orderInfoMaps.get(0);
                    Integer id = (Integer) infoMap.get("id");
                    Integer price = (Integer) infoMap.get("price");
                    settleRecord.setOrderId(id);
                    settleRecord.setTradeMoney(price);
                }else{
                    throw new Exception("添加结算记录失败，缺少与订单关联信息");
                }

                settleAccRecordMapper.insertSelective(settleRecord);
            }
        }


    }

    /**
     * 有没有订单号为orderId的数据
     * @param orderId
     * @return
     */
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

    private void putFromAndToDate(Map map,String timePeriod) throws Exception{
        if(timePeriod==null || "".equals(timePeriod))
            return;

        String[] factors = timePeriod.split("-");
        try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date fromDate = format.parse(factors[0]);
                Date toDate = format.parse(factors[1]);
                map.put("fromDate", fromDate);
                map.put("toDate", toDate);
        }catch (Exception e){
            throw new Exception("日期解析错误");
        }
    }

    /**
     * 订单状态可以转移，但是有限制
     * @return
     */
    public boolean orderStateCheck(Integer fromState, Integer toState)
    {
        if (fromState == YkatConstant.ORDER_STATE_IDLE){
            return  true;
        }else if(fromState == YkatConstant.ORDER_STATE_REFUSE && toState == YkatConstant.ORDER_STATE_ACCEPT){
            return true;
        }
        return false;
    }
}
