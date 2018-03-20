package com.PT.web;

import com.PT.service.DepositService;
import com.PT.tools.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users/{user_id}")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @RequestMapping(value = "/deposits",method = RequestMethod.GET)
    private Map listDeposits(@PathVariable("user_id") int userId,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "ipp",required = false, defaultValue = "5") int ipp,
                           @RequestParam(value = "q", required = false, defaultValue = "") String queryCondition,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{
            Map<String,Object> res = depositService.listDepositRecord(userId, page, ipp, queryCondition);
            response.setStatus(200);
            return res;
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
            return responseData.getBody();
        }



    }

    @RequestMapping(value = "/deposits",method = RequestMethod.DELETE)
    private Map deleteDepositRecord(@PathVariable("user_id") int userId,
                                    @RequestBody Map<String, Object> requestMap, HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{
            if(!requestMap.containsKey("recordIds")){
                throw new Exception("缺少结算单号参数");
            }

            List<Integer> ids = (List<Integer>) requestMap.get("recordIds");
            depositService.deleteDepositRecord(userId, ids);
            response.setStatus(200);
            responseData.putDataValue("success",true);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }

    @RequestMapping(value = "/deposits",method = RequestMethod.POST)
    private Map addDeposit(@PathVariable("user_id") int userId,
                           @RequestBody Map<String, Object> map,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{
            depositService.addDepositRecord(userId,map);
            response.setStatus(200);
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(400);
        }

        return responseData.getBody();
    }
}
