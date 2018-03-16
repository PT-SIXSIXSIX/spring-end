package com.PT.service;

import com.PT.bean.Storekeeper.StorekeeperInfoBean;
import com.PT.entity.Store;
import com.PT.entity.User;

public interface RegistryLogonService {


    /**
     * 注册门店信息，同时插入user和store的信息
     * 先插user，再查user_id且更新store外键，再插store
     * @param user
     * @param store
     * @return 用户信息
     */
    public User register(User user, Store store);

    /**
     * 根据电话号，密码来登录
     * @param phone
     * @param password
     * @return 用户信息
     */
    public User login(String phone, String password);

    /**
     * 验证电话号是否存在
     * @param phone
     * @return 结果
     */
    public boolean verifyPhone(String phone);

    /**
     * 根据userId，验证旧密码，来更新密码
     * @param id
     * @param old
     * @param pwd
     * @return 更新结果
     */
    public boolean changePassword(int id, String old, String pwd);

    /**
     * 忘记密码，根据用户信息，更新密码
     * @param storekeeperInfoBean
     * @param pwd
     * @return
     */
    public boolean changePassword(StorekeeperInfoBean storekeeperInfoBean, String pwd);
}
