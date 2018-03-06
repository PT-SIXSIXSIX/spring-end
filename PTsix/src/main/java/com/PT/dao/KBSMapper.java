package com.PT.dao;

import com.PT.entity.KBS;
import com.PT.entity.KBSExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KBSMapper {
    int countByExample(KBSExample example);

    int deleteByExample(KBSExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KBS record);

    int insertSelective(KBS record);

    List<KBS> selectByExample(KBSExample example);

    KBS selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KBS record, @Param("example") KBSExample example);

    int updateByExample(@Param("record") KBS record, @Param("example") KBSExample example);

    int updateByPrimaryKeySelective(KBS record);

    int updateByPrimaryKey(KBS record);
}