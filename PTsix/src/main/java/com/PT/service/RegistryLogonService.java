package com.PT.service;

import com.PT.entity.Store;
import com.PT.entity.User;

public interface RegistryLogonService {
    public int regist(User user, Store store);
    public int login(String phone, String password);
}
