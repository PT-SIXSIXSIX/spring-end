package com.PT.dao;

import com.PT.entity.ReceiveMoneyRecord;
import com.PT.entity.ReceiveMoneyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiveMoneyRecordMapper {
    int countByExample(ReceiveMoneyRecordExample example);

    int deleteByExample(ReceiveMoneyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveMoneyRecord record);

    int insertSelective(ReceiveMoneyRecord record);

    List<ReceiveMoneyRecord> selectByExample(ReceiveMoneyRecordExample example);

    ReceiveMoneyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReceiveMoneyRecord record, @Param("example") ReceiveMoneyRecordExample example);

    int updateByExample(@Param("record") ReceiveMoneyRecord record, @Param("example") ReceiveMoneyRecordExample example);

    int updateByPrimaryKeySelective(ReceiveMoneyRecord record);

    int updateByPrimaryKey(ReceiveMoneyRecord record);
}