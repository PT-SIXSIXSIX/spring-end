package com.PT.web;


import com.PT.service.SetAccRecService;
import com.PT.tools.QueryToMap;
import com.PT.tools.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * created by yxhuang
 * 登录和注册控制
 */
@Controller
@RequestMapping("/api/v1/users/{userId}")
public class SetAccRecController {
    @Autowired
    SetAccRecService setAccRecService;

    @RequestMapping(value = "/settleAccountRecords", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> selectSetAccRecByFactors(@PathVariable("userId") int userId,
                                 @RequestParam("page") int page,
                                 @RequestParam("ipp") int ipp,
                                 @RequestParam("q") String q,
                                 HttpServletResponse response) {
        Map factors = QueryToMap.stringToMap(q);
        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        Map result = null;
        try {
            result = setAccRecService.listSetAccRec(page, ipp, userId, factors);
        } catch (Exception e) {
            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", "内部错误，获取resultMap失败");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/settleAccountRecords", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteSetAccRecInIds(@RequestBody Map mp, HttpServletResponse response) {


        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        if(setAccRecService.deleteSetAccRec((List<String>) mp.get("setAccIds")) == false) {
            response.setStatus(400);

        }
        return responseData.getBody();
    }
}
