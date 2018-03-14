package com.PT.test;

import com.PT.bean.Storekeeper.StorekeeperInfoBean;
import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.service.RegistryLogonService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
public class RegistryLogonTest extends BaseTest {
    @Autowired
    RegistryLogonService registryLogonService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StoreMapper storeMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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

    /**
     * 密码查询修改测试
     */
    @Test
    public void password() {
        StorekeeperInfoBean info = new StorekeeperInfoBean();
        info.setName("hyx");
        info.setPhone("26676553437");
        info.setIdCard("32562463");
        String newPwd = "dwfrehtjdwfrehtj";
        registryLogonService.changePassword(info, newPwd);
//        registryLogonService.changePassword(64, "dwfrehtj");
    }
}
