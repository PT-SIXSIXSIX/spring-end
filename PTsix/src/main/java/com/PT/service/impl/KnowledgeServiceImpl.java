package com.PT.service.impl;

import com.PT.service.KnowledgeService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    public Map<String,Object> listKnowledge(int userId, String type, int page, int ipp, String queryCondition) throws Exception
    {

        PageHelper.startPage(page,ipp);

        return null;
    }

    public void deleteKnowledge(int userId, List<String> knowledgeIds) throws Exception{


    }

    public void addKnowledge(int userId,String type) throws Exception
    {

    }
}
