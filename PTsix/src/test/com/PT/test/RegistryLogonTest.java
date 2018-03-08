package com.PT.test;

import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.entity.UserExample;
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
    UserMapper userMapper;

//    StoreMapper storeMapper;

    @Test
    public void regist() {
        // role=1（员工），role=0（店长）
        User user = new User();
        user.setRole(0);
        user.setCreatedAt(new Date());
        user.setName("hyx");
        user.setPhone("18945");
//        user.setId(124);
        user.setPassword("hagh");
        System.out.println("user_id = " + user.getId());

        System.out.println(user.getId());
        userMapper.insert(user);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo("18945");

        User temp = userMapper.selectByExample(userExample).get(0);
        System.out.println(temp.getId()+" "+temp.getRole()+" "+temp.getCreatedAt());
    }
}
