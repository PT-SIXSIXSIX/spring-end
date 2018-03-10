package com.PT.web;


import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.service.RegistryLogonService;
import com.PT.tools.BeanToMapUtil;
import com.PT.tools.ResponseData;
import com.PT.tools.TokenOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
            String token = UUID.randomUUID().toString();
            TokenOptions.setKey(TokenOptions.TOKEN_PREFIX+user.getId(), token);
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
    public @ResponseBody ResponseData register(@RequestBody Map<String, Object> out) {
        System.out.println("in");
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
//            Map<String, Object> mp = new HashMap<String, Object>();
//            mp.put("userId", user.getId());
//            String token = JWT.createJavaWebToken(mp);
            String token = "1234";
            responseData.putDataValue("id", resultUser.getId());
            responseData.putDataValue("name", resultUser.getName());
            responseData.putDataValue("phone", resultUser.getPhone());
            responseData.putDataValue("role", resultUser.getRole());
            responseData.putDataValue("accessToken", token);
        } else {
            responseData = ResponseData.badRequest();
            responseData.putDataValue("statusCode", 1);
            responseData.putDataValue("errorDesc", "注册失败");
        }
        return responseData;
    }
}
