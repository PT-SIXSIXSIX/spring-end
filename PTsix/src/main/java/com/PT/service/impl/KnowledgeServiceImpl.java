package com.PT.service.impl;

import com.PT.dao.ArticleInfoMapper;
import com.PT.dao.ArticleMapper;
import com.PT.dao.YkatCommonUtilMapper;
import com.PT.entity.Article;
import com.PT.entity.ArticleExample;
import com.PT.service.KnowledgeService;
import com.PT.service.LogService;
import com.PT.tools.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private YkatCommonUtilMapper ykatCommonUtilMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private LogService logService;

    public Map<String,Object> listKnowledge(int userId, String type, int page, int ipp, String queryCondition) throws Exception
    {

        Map factors = new HashMap();
        if(queryCondition!=null && !"".equals(queryCondition)) { //有搜索条件时
            factors = QueryToMap.stringToMap(queryCondition);
            if (factors.containsKey("time")) {
                YkatCommonUtil.putFromAndToDate(factors, (String) factors.get("time"));
            }
        }

        factors.put("userId",userId);
        factors.put("articleType",type);
        PageHelper.startPage(page,ipp);

        List<Map >articles = articleInfoMapper.selectArticleInfoByFactor(factors);
        int maxPage = (articleInfoMapper.countArticleInfoByFactor(factors)-1)/ipp + 1;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("articles",articles);
        resultMap.put("maxPage",maxPage);

        return resultMap;
    }

    public void deleteKnowledge(int userId, List<String> knowledgeIds) throws Exception{
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleIdIn(knowledgeIds);

        Article deleteTo = new Article();
        deleteTo.setStatus(YkatConstant.ARTICLE_STATE_DELETE);

        if( articleMapper.updateByExampleSelective(deleteTo,articleExample)  > 0  ){
            String desc = ToStrings.listToStrings(knowledgeIds, '&');
            logService.insertLog(userId, "delete","on table ykat_articles: by ids in ["+desc+"]");
        }

    }


    public void addKnowledge(int userId,String type, Map parameterMap) throws Exception
    {
        String checkMessage = YkatCommonUtil.checkMapHasNull(parameterMap);
        if(!"success".equals(checkMessage)){
            throw new Exception(checkMessage);
        }

        String title = (String) parameterMap.get("title");
        String summary = (String) parameterMap.get("summary");
        String content = (String) parameterMap.get("content");
        String bannerUrl = (String) parameterMap.get("bannerUrl");
        String author = (String) parameterMap.get("author");


        Date today = new Date();
        Article article = (Article) BeanToMapUtil.convertMap(Article.class,parameterMap);//尝试这样传递

        article.setCreatedAt(today);
        article.setUpdatedAt(today);
        article.setStatus(YkatConstant.ARTICLE_STATE_DEFAULT);
        article.setType(type);

        Integer storeId = ykatCommonUtilMapper.getStoreIdByUserId(userId);
        article.setStoreId(storeId);

        //订单号
        Map<String,String> map = new HashMap();
        map.put("tableName","ykat_articles");
        map.put("colName","article_id");
        ykatCommonUtilMapper.generateAutoIncrementId(map);
        String generatedArticleId = map.get("billsNoResult");

        article.setArticleId(generatedArticleId);
        if(articleMapper.insert(article)>0){
            logService.insertLog(userId,"insert","into table ykat_articles whose"+
                    " orderId = "+generatedArticleId);
        }


    }

    public void updateKnowledge(int userId,String type, String articleId,Map parameterMap) throws Exception
    {
        String checkMessage = YkatCommonUtil.checkMapHasNull(parameterMap);
        if(!"success".equals(checkMessage)){
            throw new Exception(checkMessage);
        }

        Date updateDate = new Date();
        Article updateTo = (Article) BeanToMapUtil.convertMap(Article.class,parameterMap);//尝试这样传递
        updateTo.setUpdatedAt(updateDate);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleIdEqualTo(articleId);

        if(articleMapper.updateByExampleSelective(updateTo,articleExample)>0){
            logService.insertLog(userId,"update","on table ykat_articles whose"+
                    " orderId = "+articleId);
        }

    }


}
