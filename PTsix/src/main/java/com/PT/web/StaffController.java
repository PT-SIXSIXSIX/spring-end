package com.PT.web;

import com.PT.service.StaffService;
import com.PT.tools.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/{user_id}")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * 查询店员列表
     * @param userId
     * @param page
     * @param ipp
     * @param queryCondition
     * @return get方法不需要返回错误信息
     */
    @RequestMapping(value = "/staffs",method = RequestMethod.GET)
    private Map listStaffs(@PathVariable("user_id") int userId,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "ipp", required = false, defaultValue = "5") int ipp,
                           @RequestParam(value = "q", required = false, defaultValue = "") String queryCondition)
    {

        Map<String,Object> resultMap = staffService.listStaff(userId,page,ipp,queryCondition);


        return resultMap;
    }

    /**
     * 删除店员
     * @param userId
     * @param staffId
     * @param response
     * @return
     */
    @RequestMapping(value = "/staffs/{staff_id}", method = RequestMethod.DELETE)
    private Map deleteStaffs(@PathVariable("user_id") int userId,
                           @PathVariable(value = "staff_id") int staffId,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();

        try{

            staffService.deleteByStaffId(userId, staffId);
            responseData.putDataValue("success",true);
            response.setStatus(200);
        }catch (Exception e) {
            e.printStackTrace();
            response.setStatus(400);//设置response status码
            responseData = ResponseData.badRequest();
            responseData.putDataValue("statusCode", 1);
            responseData.putDataValue("errorDesc", "删除失败");
        }
        return responseData.getBody();
    }

    /**
     * 添加店员
     * @param userId
     * @param requestMap
     * @param response
     * @return
     */
    @RequestMapping(value = "/staffs/",method = RequestMethod.POST)
    private Map addStaff(@PathVariable("user_id") int userId,
                           @RequestBody Map<String, Object> requestMap,HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();
        try{
            staffService.addStaff(userId,requestMap);
            response.setStatus(200);
            responseData.putDataValue("success",true);
        }catch(Exception e){
            response.setStatus(400);
            responseData = ResponseData.badRequest();
            responseData.setError(1,e.getMessage());
        }



        return responseData.getBody();
    }


    /**
     * 修改店员 ukat_user表的信息
     * @param userId
     * @param staffId
     * @param requestMap
     * @param response
     * @return
     */
    @RequestMapping(value = "/staffs/{staff_id}",method = RequestMethod.PUT)
    private Map updateStaffs(@PathVariable("user_id") int userId,
                           @PathVariable(value = "staff_id") int staffId,
                           @RequestBody Map requestMap,
                           HttpServletResponse response)
    {
        ResponseData responseData = ResponseData.ok();

        try{
            staffService.updateByStaffId(userId, staffId,requestMap);
            responseData.putDataValue("success",true);
        }catch (Exception e){
            response.setStatus(400);
            responseData = ResponseData.badRequest();
            responseData.setError(1,  e.getMessage());
        }
        return responseData.getBody();
    }

}
