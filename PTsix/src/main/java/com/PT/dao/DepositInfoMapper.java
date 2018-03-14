package com.PT.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DepositInfoMapper {

    List< Map<String, Object> > selectByUserId(Integer userId);
    int countByUserId(Integer userId);
    List< Map<String, Object> > selectByFactor(@Param("map") Map map);
    int countByFactor(@Param("map") Map map);

}
