package com.PT.service.impl;

import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.entity.UserExample;
import com.PT.service.RegistryLogonService;
import com.PT.tools.JWT;
import com.PT.tools.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistryLogonServiceImpl implements RegistryLogonService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StoreMapper storeMapper;

    static String ENCODE = "utf-8";
    @Override
    public User regist(User user, Store store) {
        try {
            String password = PasswordUtil.MD5Encode(user.getPassword(), ENCODE, false);
            user.setPassword(password);
            userMapper.insert(user);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(user.getPhone());
            List list = userMapper.selectByExample(userExample);
            if(list.isEmpty()) {
                System.out.println("无法查到此店长信息");
                return null;
            }
            User temp = (User) list.get(0);
            store.setUserId(temp.getId());
            storeMapper.insert(store);

            Map<String, Object> mp = new HashMap<String, Object>();
            mp.put("userId", user.getId());
            String token = JWT.createJavaWebToken(mp);
            System.out.println(token);

            return temp;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * 根据电话和密码登录用户
     * @param phone
     * @param password
     * @return
     */
    @Override
    public User login(String phone, String password) {
        String pwd = PasswordUtil.MD5Encode(password, ENCODE, false);
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(phone).andPasswordEqualTo(pwd);
            List list = userMapper.selectByExample(userExample);
            if(list.isEmpty()) {
                System.out.println("密码或账户错误");
                return null;
            }
            User user = (User) list.get(0);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
