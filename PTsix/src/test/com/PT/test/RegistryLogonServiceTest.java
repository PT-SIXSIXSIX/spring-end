package com.PT.test;

import com.PT.bean.Storekeeper.StorekeeperInfoBean;
import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.service.RegistryLogonService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
public class RegistryLogonServiceTest extends BaseTest {
    @Autowired
    RegistryLogonService service;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StoreMapper storeMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 注册时，联合插入测试
     */
    @Test
    public void regist() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        // role=1（员工），role=0（店长）
        User user = new User();
        user.setRole(0);
        user.setCreatedAt(new Date());
        user.setName("官家猪");
        user.setPhone("13390046270");
        user.setPassword("123456");

        Store store = new Store();
        store.setIdCard("510252362346");
        store.setCompanyName("朝天门公司");
        User result = service.regist(user, store);
        OutputMessage.outputBean(result);
    }
    /**
     *
     * 登录，测试
     */
    @Test
    public void login() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        User user = service.login("13390046270", "123456");
        if(null != user) {
            OutputMessage.outputBean(user);
        } else {
            System.out.println("找不到用户");
        }
    }

    @Test
    public void verifyPhone() {
        String phone1 = "13390046270";
        String phone2 = "123456543234";
        Boolean yes1 = service.verifyPhone(phone1);
        Boolean yes2 = service.verifyPhone(phone2);
        System.out.println(phone1+" : "+yes1+"\n"+phone2+" : "+yes2);
    }
    /**
     * 密码查询修改测试
     */
    @Test
    public void newPassword() {
        StorekeeperInfoBean info = new StorekeeperInfoBean();
        info.setName("hyx");
        info.setPhone("13390046270");
        info.setIdCard("32562463");
        String newPwd = "123456";
        service.changePassword(info, newPwd);
    }
    @Test
    public void changePassword() {
        int userId = 66;
        String oldPwd = "123456";
        String newPwd = "1339004627145";
        service.changePassword(userId, oldPwd, newPwd);
    }
}
