package com.PT.dao;

import com.PT.entity.KnowledgeType;
import com.PT.entity.KnowledgeTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgeTypeMapper {
    long countByExample(KnowledgeTypeExample example);

    int deleteByExample(KnowledgeTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KnowledgeType record);

    int insertSelective(KnowledgeType record);

    List<KnowledgeType> selectByExample(KnowledgeTypeExample example);

    KnowledgeType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KnowledgeType record, @Param("example") KnowledgeTypeExample example);

    int updateByExample(@Param("record") KnowledgeType record, @Param("example") KnowledgeTypeExample example);

    int updateByPrimaryKeySelective(KnowledgeType record);

    int updateByPrimaryKey(KnowledgeType record);
}