package com.PT.web;

import com.PT.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * created by yxhuang
 */
@Controller
@RequestMapping("/api/v1/users/{userId}")
public class StoreManageController {
    @Autowired
    StoreService storeService;

    /**
     * 根据userId找店主自己的信息
     * @param userId
     * @param response
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getStoreInfoById(@PathVariable("userId") int userId
            , HttpServletResponse response) {
        Map result = storeService.getStoreByUserId(userId);
        return result;
    }

    /**
     * 根据userId和传入的用户信息，选择性更新用户信息
     * @param userId
     * @param map
     * @param response
     * @return
     */
    @RequestMapping(value = "/store", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> updateStoreById(@PathVariable("userId") int userId
            , @RequestBody Map map, HttpServletResponse response) {
        Map result = storeService.updateStoreByUserId(map, userId);
        return result;
    }
}
