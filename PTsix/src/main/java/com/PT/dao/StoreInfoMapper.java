package com.PT.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface StoreInfoMapper {
    Map selectByUserId(@Param("userId") Integer userId);
    int updateStoreByUserId(@Param("map") Map map);
    int updateUserByUserId(@Param("map") Map map);
}
