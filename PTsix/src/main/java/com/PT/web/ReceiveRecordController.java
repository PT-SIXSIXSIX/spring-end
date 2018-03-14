package com.PT.web;

import com.PT.service.ReceiveRecordService;
import com.PT.tools.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/users/{user_id}")
public class ReceiveRecordController {

    @Autowired
    private ReceiveRecordService receiveRecordService;

    @RequestMapping(value = "receive_money_records",method = RequestMethod.GET)
    private Map listStaffs(@PathVariable("user_id") int userId,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "ipp",required = false, defaultValue = "5") int ipp,
                           @RequestParam(value = "q", required = false, defaultValue = "") String queryCondition,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{

            Map<String, Object> map = receiveRecordService.listReceiveRecord(userId,page,ipp,queryCondition);
            response.setStatus(responseData.getCode());
            return map;
        }catch(Exception e){
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
            response.setStatus(responseData.getCode());
        }
        return responseData.getBody();
    }


}
