package com.PT.dao;

import com.PT.bean.SetAccRecInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetAccRecInfoMapper {

    List<SetAccRecInfoBean> selectByFactors(@Param("map") Map map);
    int countByFactors(@Param("map") Map map);
    int updateDriver(@Param("list") List<String> ids);
    int updateSetAccRec(@Param("list") List<String> ids);
}
