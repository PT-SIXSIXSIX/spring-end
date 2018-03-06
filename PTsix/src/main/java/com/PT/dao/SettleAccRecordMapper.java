package com.PT.dao;

import com.PT.entity.SettleAccRecord;
import com.PT.entity.SettleAccRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettleAccRecordMapper {
    int countByExample(SettleAccRecordExample example);

    int deleteByExample(SettleAccRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SettleAccRecord record);

    int insertSelective(SettleAccRecord record);

    List<SettleAccRecord> selectByExample(SettleAccRecordExample example);

    SettleAccRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SettleAccRecord record, @Param("example") SettleAccRecordExample example);

    int updateByExample(@Param("record") SettleAccRecord record, @Param("example") SettleAccRecordExample example);

    int updateByPrimaryKeySelective(SettleAccRecord record);

    int updateByPrimaryKey(SettleAccRecord record);
}