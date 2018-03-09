package com.PT.test;

import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.entity.UserExample;
import com.PT.service.RegistryLogonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*"})
public class RegistryLogonTest {
    @Autowired
    RegistryLogonService registryLogonService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StoreMapper storeMapper;
//    StoreMapper storeMapper;


    /**
     * 注册时，联合插入测试
     */
    @Test
    public void regist() {
        // role=1（员工），role=0（店长）
        User user = new User();
        user.setRole(0);
        user.setCreatedAt(new Date());
        user.setName("黄宇霄");
        user.setPhone("13290046270");
        user.setPassword("123456");

        Store store = new Store();
        store.setIdCard("510252362346");
        store.setCompanyName("城帝西公司");
        registryLogonService.regist(user, store);
    }
    /**
     *
     * 登录，测试
     */
    @Test
    public void login() {
        User user = registryLogonService.login("13290046270", "123456");
        if(null != user) {
            System.out.println("登录成功");
            System.out.println(user.getName()+" "+user.getPassword());
        } else {
            System.out.println("找不到用户");
        }
    }
}
