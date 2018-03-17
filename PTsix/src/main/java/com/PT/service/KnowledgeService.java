package com.PT.service;

import java.util.List;
import java.util.Map;

public interface KnowledgeService {

    Map<String,Object> listKnowledge(int userId, String type, int page, int ipp, String queryCondition) throws Exception;

    void deleteKnowledge(int userId, List<String> knowledgeIds) throws Exception;

    void addKnowledge(int userId,String type) throws Exception;

}
