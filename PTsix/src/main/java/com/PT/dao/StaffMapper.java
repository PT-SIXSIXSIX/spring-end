package com.PT.dao;

import com.PT.bean.Staff.StaffInfoBean;

import java.util.List;
import java.util.Map;


public interface StaffMapper {
    List<Map> selectByManagerId (Integer id);
    int countByManagerId(Integer id);
    int deleteRelationByUserId(Integer id);
}
