package com.PT.service;

import java.util.Map;

public interface StoreService {
    /**
     * 根据userId获得门店管理员信息
     * @param userId
     * @return 管理员信息
     */
    Map <String, Object> getStoreByUserId(int userId);

    /**
     * 更新门店管理员信息
     * @param map
     * @param userId
     * @return 管理员信息
     */
    Map <String, Object> updateStoreByUserId(Map map, int userId);
}
