package com.PT.web;


import com.PT.tools.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users/{user_id}")
public class KnowledgeBaseController {


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
    @RequestMapping(value = "/knowledge/{type}")
    private Map listKnowledge(@PathVariable("user_id") int userId,
                              @PathVariable("type") String type,
                              @RequestParam(value = "page",required = true, defaultValue = "1") int page,
                              @RequestParam(value = "ipp",required = true, defaultValue = "5") int ipp,
                              @RequestParam(value = "q",required = true, defaultValue = "") String queryString,
                              HttpServletResponse response){
        ResponseData responseData = ResponseData.ok();
        try{


            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }



}
