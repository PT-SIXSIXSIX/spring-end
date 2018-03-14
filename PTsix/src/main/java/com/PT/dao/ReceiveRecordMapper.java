package com.PT.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReceiveRecordMapper {

    List<Map<String, Object> > selectByFactors(@Param("map") Map map);
    int countByFactors(@Param("map") Map map);

}
