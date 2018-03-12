package com.PT.dao;

import com.PT.bean.Staff.StaffInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface StaffMapper {
    List<Map> selectByManagerId (Integer id);
    List<Map> selectByFactor(@Param("map")Map map);
    int countByManagerId(Integer id);
    int countByFactor(@Param("map")Map map);
    int deleteRelationByUserId(Integer id);
}
