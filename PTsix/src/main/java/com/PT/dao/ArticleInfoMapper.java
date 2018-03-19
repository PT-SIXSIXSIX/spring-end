package com.PT.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleInfoMapper {

    List<Map > selectArticleInfoByFactor(@Param("map") Map map);

    int countArticleInfoByFactor(@Param("map") Map map);

    List<Map> selectArticleDetailByArticleId(String articleId);

}
