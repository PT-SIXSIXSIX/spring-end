package com.PT.dao;

import com.PT.entity.Bankcard;
import com.PT.entity.BankcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankcardMapper {
    int countByExample(BankcardExample example);

    int deleteByExample(BankcardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bankcard record);

    int insertSelective(Bankcard record);

    List<Bankcard> selectByExample(BankcardExample example);

    Bankcard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bankcard record, @Param("example") BankcardExample example);

    int updateByExample(@Param("record") Bankcard record, @Param("example") BankcardExample example);

    int updateByPrimaryKeySelective(Bankcard record);

    int updateByPrimaryKey(Bankcard record);
}