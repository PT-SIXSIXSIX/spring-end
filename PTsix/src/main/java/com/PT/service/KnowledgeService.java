package com.PT.service;

import com.PT.entity.Article;
import javafx.beans.binding.ObjectExpression;

import java.util.List;
import java.util.Map;

public interface KnowledgeService {

    Map<String,Object> listKnowledge(int userId, String type, int page, int ipp, String queryCondition) throws Exception;

    void deleteKnowledge(int userId, List<String> knowledgeIds) throws Exception;

    void addKnowledge(int userId, Map parameters) throws Exception;
    void updateKnowledge(int userId, String articleId, Map parameters) throws Exception;

    Map<String, Object> viewArticleDetail(int userId, String articleId) throws Exception;
}
