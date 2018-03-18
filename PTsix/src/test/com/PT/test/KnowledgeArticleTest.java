package com.PT.test;

import com.PT.service.KnowledgeService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeArticleTest extends BaseTest {

    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void listKnowledgeTest(){
        int userId = 1,page=1,ipp=3;
        String type="1";

        try{
            Map<String, Object> resultMap = knowledgeService.listKnowledge(userId,type,page,ipp,"");
            List<Map<String,Object> > articles =(List<Map<String, Object> >) resultMap.get("articles");
            OutputMessage.outputListOfMap(articles);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addArticleTest(){

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("title","青春修炼手册");
        param.put("summary","这是概要");
        param.put("content","这是内容");
        param.put("author","tfboys");
        param.put("bannerUrl","https://avatars0.githubusercontent.com/u/13377302?s=400&u=52647425a5e596ca438aa1001205530950d4315c&v=4");
        param.put("type","1");

        int userId = 1;String type="1";
        try{
            knowledgeService.addKnowledge(userId,type,param);
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void updateArticleTest(){

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("title","青春修炼手册更新");
        param.put("summary","这是概要更新");
        param.put("content","这是内容更新");
        param.put("author","tfboys更新");
        param.put("bannerUrl","https://avatars0.githubusercontent.com/u/13377302?s=400&u=52647425a5e596ca438aa1001205530950d4315c&v=4");
        param.put("type","1");

        int userId = 1;String type="1",articleId = "20180318000001";
        try{
            knowledgeService.updateKnowledge(userId,"1",articleId,param);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest(){
        int userId = 1;
        List<String> articleIds = new ArrayList<String >();
        articleIds.add("20180318000001");
        try{
            knowledgeService.deleteKnowledge(userId,articleIds);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
