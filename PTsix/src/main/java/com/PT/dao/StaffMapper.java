package com.PT.dao;

import com.PT.bean.Staff.StaffInfoBean;
import com.PT.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface StaffMapper {
    List<Map> selectByManagerId (Integer id);
    List<Map> selectByFactor(@Param("map")Map map);
    int countByManagerId(Integer id);
    int countByFactor(@Param("map")Map map);
    int deleteRelationByUserId(Integer id);
    int insertStaffSelective(User record);
    void insertRelation(@Param("map") Map record);
}
