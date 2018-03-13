package com.PT.web;

import com.PT.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/users/{userId}")
public class StoreManageController {
    @Autowired
    StoreService storeService;

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getStoreInfoById(@PathVariable("userId") int userId
            , HttpServletResponse response) {
        Map result = storeService.getStoreByUserId(userId);
        return result;
    }

    @RequestMapping(value = "/store", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> updateStoreById(@PathVariable("userId") int userId
            , @RequestBody Map map, HttpServletResponse response) {
        Map result = storeService.updateStoreByUserId(map, userId);
        return result;
    }
}
