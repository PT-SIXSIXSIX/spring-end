package com.PT.service.impl;

import com.PT.bean.Storekeeper.StorekeeperInfoBean;
import com.PT.dao.StoreMapper;
import com.PT.dao.UserMapper;
import com.PT.dao.StorekeeperInfoMapper;
import com.PT.entity.Store;
import com.PT.entity.User;
import com.PT.entity.UserExample;
import com.PT.service.RegistryLogonService;
import com.PT.tools.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 注册业务层实现类
 */
@Service
public class RegistryLogonServiceImpl implements RegistryLogonService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private StorekeeperInfoMapper storekeeperInfoMapper;

    static String ENCODE = "utf-8";

    /**
     * 注册，同时插入两表，需要事务注解
     * @param user
     * @param store
     * @return
     */
    @Transactional
    @Override
    public User register(User user, Store store) {
        try {
//            加密密码
            String password = PasswordUtil.MD5Encode(user.getPassword(), ENCODE, false);
            user.setPassword(password);
            user.setRole(0);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(user.getPhone());
            if(userMapper.selectByExample(userExample).size() > 0) {
                throw new RuntimeException("用户手机已经注册");
            }
            user.setCreatedAt(new Date());
            userMapper.insert(user);
            userExample.clear();
            userExample.createCriteria().andPhoneEqualTo(user.getPhone());
            List list = userMapper.selectByExample(userExample);
            User temp = null;
            try {
                temp = (User) list.get(0);
                store.setUserId(temp.getId());
                store.setDeposit(0);
                storeMapper.insert(store);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            return temp;
        } catch (RuntimeException e){
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

    /**
     * 验证手机是否存在
     * @param phone
     * @return
     */
    @Override
    public boolean verifyPhone(String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List list = userMapper.selectByExample(userExample);
        if(list.size() > 0) return true;
        return false;
    }

    /**
     * 根据userId和旧密码验证更改新密码
     * @param id
     * @param old
     * @param pwd
     * @return
     */
    @Override
    public boolean changePassword(int id, String  old, String pwd) {
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPasswordEqualTo(PasswordUtil.
                    MD5Encode(old, ENCODE, false)).andIdEqualTo(id);
            List list = userMapper.selectByExample(userExample);
            if(list.isEmpty()) { return false; }
            User user = (User) list.get(0);
            user.setPassword(PasswordUtil.MD5Encode(pwd, ENCODE, false));
            userMapper.updateByPrimaryKey(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 忘了密码，根据用户信息找回
     * @param info
     * @param pwd
     * @return
     */
    @Override
    public boolean changePassword(StorekeeperInfoBean info, String pwd) {
        StorekeeperInfoBean storekeeperInfoBean =
                storekeeperInfoMapper.selectByIdCard(info);
        if(null == storekeeperInfoBean) { return false; }
        else {
            User user = new User();
            UserExample userExample = new UserExample();
            userExample.createCriteria().andPhoneEqualTo(info.getPhone());
            user.setPassword(PasswordUtil.MD5Encode(pwd, ENCODE, false));
            userMapper.updateByExampleSelective(user, userExample);
            return true;
        }
    }
}
