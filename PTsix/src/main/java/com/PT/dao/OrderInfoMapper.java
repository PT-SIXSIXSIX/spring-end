package com.PT.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper {
    void getGeneratedOrderId(Map map);

    List<Map<String,Object> > selectOrderFromViewByOrderID(String orderId);

    List<Map> selectOrderInfoByFactor(@Param("map") Map map);

    int countOrderInfoByFactor(@Param("map") Map map);

    Integer getIdByProjectType(String projectType);
}
