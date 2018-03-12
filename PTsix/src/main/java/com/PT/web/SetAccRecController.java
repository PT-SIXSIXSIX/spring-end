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
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "ipp", required = false, defaultValue = "5") int ipp,
                                 @RequestParam(value = "q", required = false, defaultValue = "") String q,
                                 HttpServletResponse response) {
        System.out.println(q);
        Map factors = QueryToMap.stringToMap(q);
        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        Map result = null;
        try {
            result = setAccRecService.listSetAccRec(page, ipp, userId, factors);
        } catch (Exception e) {
            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/settleAccountRecords", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> deleteSetAccRecInIds(@PathVariable("userId") int userId,
                                             @RequestBody Map mp, HttpServletResponse response) {
        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        if(setAccRecService.deleteSetAccRec((List<String>) mp.get("setAccIds"), userId) == false) {
            response.setStatus(400);
            responseData.setError(1, "删除失败");
        }
        return responseData.getBody();
    }

    @RequestMapping(value = "/sa", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> updateSetAccRecInIds(@PathVariable("userId") int userId,
                                                 @RequestParam("state") int state,
                                                 @RequestBody Map mp,
                                                 HttpServletResponse response) {
        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        if(setAccRecService.updateSetAccState((List<String>) mp.get("setAccIds"), state, userId) == false) {
            response.setStatus(400);
            responseData.setError(1, "更新失败");
        }
        return responseData.getBody();
    }
}
