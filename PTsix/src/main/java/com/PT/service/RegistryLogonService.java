package com.PT.service;

import com.PT.bean.StorekeeperInfoBean;
import com.PT.entity.Store;
import com.PT.entity.User;

public interface RegistryLogonService {


    /**
     * 注册门店信息，同时插入user和store的信息
     * 先插user，再查user_id且更新store外键，再插store
     * @param user
     * @param store
     * @return
     */
    public User regist(User user, Store store);

    /**
     * 根据电话号，密码或者token来登录
     * @param phone
     * @param password
     * @return
     */
    public User login(String phone, String password);

    /**
     * 验证电话号是否存在
     * @param phone
     * @return
     */
    public boolean verifyPhone(String phone);

    public boolean changePassword(int id, String old, String pwd);
    public boolean changePassword(StorekeeperInfoBean storekeeperInfoBean, String pwd);
}
