package com.PT.service.impl;

import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.service.RegistryLogonService;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistryLogonServiceImpl implements RegistryLogonService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Override
    public int regist(User user, Store store) {
        // role=1（员工），role=0（店长）
        user.setRole(0);
        System.out.println(user.getId());
        userMapper.insert(user);
//        User temp =
        return 0;
    }

    @Override
    public int login(String phone, String password) {
        return 0;
    }
}
