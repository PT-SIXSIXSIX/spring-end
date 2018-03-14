package com.PT.web;


import com.PT.service.KBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * created by yxhuang
 */
@Controller
@RequestMapping("/api/v1")
public class KBSController {

//    @Autowired
//    private KBSService kbsService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    private Map add(Model model) {
        Map<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("haha","haha");
        return hashMap;
    }
}
