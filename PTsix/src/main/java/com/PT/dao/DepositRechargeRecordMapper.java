package com.PT.dao;

import com.PT.entity.DepositRechargeRecord;
import com.PT.entity.DepositRechargeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositRechargeRecordMapper {
    int countByExample(DepositRechargeRecordExample example);

    int deleteByExample(DepositRechargeRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepositRechargeRecord record);

    int insertSelective(DepositRechargeRecord record);

    List<DepositRechargeRecord> selectByExample(DepositRechargeRecordExample example);

    DepositRechargeRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepositRechargeRecord record, @Param("example") DepositRechargeRecordExample example);

    int updateByExample(@Param("record") DepositRechargeRecord record, @Param("example") DepositRechargeRecordExample example);

    int updateByPrimaryKeySelective(DepositRechargeRecord record);

    int updateByPrimaryKey(DepositRechargeRecord record);
}