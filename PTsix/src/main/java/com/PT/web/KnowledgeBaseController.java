package com.PT.web;


import com.PT.service.KnowledgeService;
import com.PT.tools.ResponseData;
import com.PT.tools.YkatCommonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users/{user_id}")
public class KnowledgeBaseController {

    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 查询 知识库里面的文章
     * @param userId
     * @param type
     * @param page
     * @param ipp
     * @param queryString
     * @param response
     * @return
     */
    @RequestMapping(value = "/articles/{type}",method = RequestMethod.GET)
    private Map listKnowledge(@PathVariable("user_id") int userId,
                              @PathVariable("type") String type,
                              @RequestParam(value = "page",required = true, defaultValue = "1") int page,
                              @RequestParam(value = "ipp",required = true, defaultValue = "5") int ipp,
                              @RequestParam(value = "q",required = true, defaultValue = "") String queryString,
                              HttpServletResponse response){
        ResponseData responseData = ResponseData.ok();
        try{

            Map<String,Object> responseMap = knowledgeService.listKnowledge(userId,type,page,ipp,queryString);
            response.setStatus(200);
            return responseMap;
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }

    /**
     * 添加文章
     * @param userId
     * @param parameterMap
     * @param response
     * @return
     */
    @RequestMapping(value = "/articles/{type}",method = RequestMethod.POST)
    private Map addArticle(@PathVariable("user_id") int userId,
                           @PathVariable("type") String type,
                           @RequestBody Map<String, Object> parameterMap,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{
            knowledgeService.addKnowledge(userId,type,parameterMap);
            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }


    /**
     * 修改文章
     * @param userId
     * @param type
     * @param articleId
     * @param response
     * @return
     */
    @RequestMapping(value = "/articles/{type}/{article_id}",method = RequestMethod.PUT)
    private Map updateArticle(@PathVariable("user_id") int userId,
                              @PathVariable("type") String type,
                              @RequestBody Map<String, Object> parameterMap,
                              @PathVariable("article_id") String articleId,
                              HttpServletResponse response)

    {
        ResponseData responseData = ResponseData.ok();
        try{
            knowledgeService.updateKnowledge(userId,type,articleId,parameterMap);
            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();

    }


    /**
     * 删除文章
     * @param userId
     * @param type
     * @param parameterMap
     * @param response
     * @return
     */
    @RequestMapping(value = "/articles/{type}",method = RequestMethod.DELETE)
    private Map deleteArticle(@PathVariable("user_id") int userId,
                              @PathVariable("type") String type,
                              @RequestBody Map<String, Object> parameterMap,
                              HttpServletResponse response)

    {

        ResponseData responseData = ResponseData.ok();
        try{
            String checkMessage = YkatCommonUtil.checkMapHasNull(parameterMap);
            if(!"success".equals(checkMessage)){
                throw new Exception(checkMessage);
            }

            List<String> articleIds = (List<String>)parameterMap.get("articleIds");
            knowledgeService.deleteKnowledge(userId,articleIds);
            responseData.putDataValue("success",true);
            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }
}
