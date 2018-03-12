package com.PT.service.impl;

import com.PT.bean.order.OrderInfoBean;
import com.PT.dao.*;
import com.PT.entity.*;

import com.PT.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdeServiceImpl implements OrderService{

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
    @Override
    public Map<String,Object> listOrder(int type, int page, int ipp, int userId, String queryCondition) throws Exception{
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();

        //根据userId 查询 storeID;
        Integer storeID = null;
        StoreExample example1 = new StoreExample();
        example1.createCriteria().andUserIdEqualTo(userId);
        List<Store> stores = storeMapper.selectByExample(example1);
        if(null != stores && stores.size()>0){
            Store store = stores.get(0);
            storeID = store.getId();
        }else{
            throw new Exception("user_id没有对应的门店信息");
        }
        criteria.andStoreIdEqualTo(storeID);// 商店id主键

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
        map.put("maxPage",maxPage);
        map.put("records",orders);
        return map;
    }



    @Override
    public void addOrder(int userId, int driverId, String orderType, String projectType, String projectDescp) throws Exception {
        Order order = new Order();
        order.setCreatedAt(new Date());//创建时间
        order.setDriverId(driverId);//司机ID
        order.setStatus(0);//订单状态
        order.setType(orderType);

        //project Id 外键信息
        Integer projectId = projectMapper.getIdByProjectType(projectType);
        if(projectId!=null){ //有没有这个项目
            order.setProjectId(projectId);
        }else{
            Project project = new Project();
            project.setDescp(projectDescp);
            project.setType(projectType);
            project.setPrice(100);
            projectMapper.insertSelective(project);
            order.setProjectId(project.getId());//会写项目id 主键
        }

        //订单号
        Map<String,String> map = new HashMap();
        map.put("tableName","ykat_orders");
        map.put("colName","order_id");
        ykatCommonUtilMapper.generateAutoIncrementId(map);
        String generatedOrderId = map.get("billsNoResult");

        order.setOrderId(generatedOrderId);//

        //根据userId 查询 storeID;
        Integer storeID = null;
        StoreExample example1 = new StoreExample();
        example1.createCriteria().andUserIdEqualTo(userId);
        List<Store> stores = storeMapper.selectByExample(example1);
        if(null != stores && stores.size()>0){
            Store store = stores.get(0);
            storeID = store.getId();
        }else{
            throw new Exception("userId没有对应的门店信息");
        }

        order.setStoreId(storeID);

        orderMapper.insertSelective(order);


    }
    @Override
    public void deleteOrder(int userId, int type, List<String> orderIds) throws Exception{
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIds);
        //criteria.andStoreIdEqualTo(userId);
        criteria.andTypeEqualTo(String.valueOf(type));

        int res = orderMapper.deleteByExample(example);
        if(res<=0){
            throw new Exception("删除失败");
        }

    }

    @Override
    public void updateOrderState(String orderId, int userID, int status) throws Exception {

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        Order order = new Order();
        order.setStatus(status);

        int updateSize = orderMapper.updateByExampleSelective(order, example);//更新状态


        if( updateSize>0 && status == 1){//更新成功 接受订单，在结算管理增加一个数据

            SettleAccRecord settleRecord = new SettleAccRecord();

            Map<String,String> paramMap = new HashMap();
            paramMap.put("tableName","ykat_settle_account_records");
            paramMap.put("colName","set_acc_id");
            ykatCommonUtilMapper.generateAutoIncrementId(paramMap);
            String generatedSettleId = paramMap.get("billsNoResult");//获取结算订单号

            settleRecord.setSetAccId(generatedSettleId);//结算单号
            settleRecord.setCreatedAt(new Date());//记录创建时间
            settleRecord.setStatus(1);//待结算
            //获取订单 主键id，订单价格
            List<Map<String,Object> > orderInfoMaps = orderMapper.selectOrderFromViewByOrderID(orderId);
            if(null!= orderInfoMaps && orderInfoMaps.size()>0){
                Map<String,Object> infoMap = orderInfoMaps.get(0);
                Integer id = (Integer) infoMap.get("id");
                Integer price = (Integer) infoMap.get("price");
                settleRecord.setOrderId(id);
                settleRecord.setTradeMoney(price);
            }else{
                throw new Exception("添加结算记录失败");
            }

            settleAccRecordMapper.insertSelective(settleRecord);

        }
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
