package com.PT.web;

import com.PT.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getDrivers() {
        Map result = new HashMap<String, Object>();
        try {
            result = driverService.getDriverMessage();
        } catch (Exception e) {
            result.put("statusCode", 1);
            result.put("errorDesc", e.getMessage());
        }
        return result;
    }

}
