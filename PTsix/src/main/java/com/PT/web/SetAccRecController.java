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
 * 结算管理信息控制器
 */
@Controller
@RequestMapping("/api/v1/users/{userId}")
public class SetAccRecController {
    @Autowired
    SetAccRecService setAccRecService;
    /**
     * 根据userId和传入信息（可以为空）模糊查询结算信息
     * @param userId
     * @param page
     * @param ipp
     * @param q
     * @param response
     * @return
     */
    @RequestMapping(value = "/settleAccountRecords", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> selectSetAccRecByFactors(@PathVariable("userId") int userId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "ipp", required = false, defaultValue = "5") int ipp,
                                 @RequestParam(value = "q", required = false, defaultValue = "") String q,
                                 HttpServletResponse response) {
        /**
         * q：付款人姓名,或者手机号码,结算单号
         */
        ResponseData responseData = ResponseData.ok();
        response.setStatus(200);
        Map result = null;
        try {
            result = setAccRecService.listSetAccRec(page, ipp, userId, q);
        } catch (Exception e) {
            response.setStatus(400);
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据传入的userId和Ids，批量删除结算记录，只能删除已结算的记录(status:0-联保，1-待结算，2-已结算，3-已删除)
     * @param userId
     * @param mp
     * @param response
     * @return
     */
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

    /**
     * 根据ids批量更新结算表，（改为待结算或者改为结算）
     * @param userId
     * @param state
     * @param mp
     * @param response
     * @return
     */
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
