package com.PT.web;


import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.service.RegistryLogonService;
import com.PT.tools.BeanToMapUtil;
import com.PT.tools.JWT;
import com.PT.tools.OutParaToLocal;
import com.PT.tools.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * created by yxhuang
 * 登录和注册控制
 */
@Controller
@RequestMapping("/api/v1")
public class RegistryLogonController {

    static long TM = 60L*1000L*30L;
    @Autowired
    RegistryLogonService registryLogonService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseData login(@RequestBody Map<String, String> map) {
        String phone = map.get("phone");
        String password = map.get("password");
        ResponseData responseData = ResponseData.ok();
        User user = registryLogonService.login(phone, password);
        if(null != user) {
//            Map<String, Object> mp = new HashMap<String, Object>();
//            mp.put("userId", user.getId());
//            String token = JWT.createJavaWebToken(mp);

            String token = "1234";
            responseData.putDataValue("id", user.getId());
            responseData.putDataValue("name", user.getName());
            responseData.putDataValue("phone", user.getPhone());
            responseData.putDataValue("role", user.getRole());
            responseData.putDataValue("accessToken", token);
        } else {
            responseData = ResponseData.badRequest();
            responseData.putDataValue("statusCode", 1);
            responseData.putDataValue("errorDesc", "用户名或密码错误");
        }
        return responseData;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ResponseData register(HttpServletRequest request) {
        Map out = OutParaToLocal.getMapFromRequestMap(request.getParameterMap());
        out.put("name", out.get("bossName"));
        out.put("phone", out.get("bossPhone"));
        ResponseData responseData = ResponseData.createOk();
        User user = null;
        Store store = null;
        try {
            user = (User) BeanToMapUtil.convertMap(User.class, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            store = (Store) BeanToMapUtil.convertMap(Store.class, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User resultUser = registryLogonService.regist(user, store);
        if(null != resultUser) {
            Map<String, Object> mp = new HashMap<String, Object>();
            mp.put("userId", user.getId());
            String token = JWT.createJavaWebToken(mp);
//            String token = "1234";
            responseData.putDataValue("id", resultUser.getId());
            responseData.putDataValue("name", resultUser.getName());
            responseData.putDataValue("phone", resultUser.getPhone());
            responseData.putDataValue("role", resultUser.getRole());
            responseData.putDataValue("accessToken", token);
        } else {
            responseData = ResponseData.badRequest();
            responseData.putDataValue("statusCode", 1);
            responseData.putDataValue("errorDesc", "token过期或者token不正确");
        }
        System.out.println("4");
        return responseData;
    }
}
