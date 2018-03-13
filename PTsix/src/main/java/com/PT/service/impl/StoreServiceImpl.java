package com.PT.service.impl;

import com.PT.dao.StoreInfoMapper;
import com.PT.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreInfoMapper storeInfoMapper;
    @Override
    public Map<String, Object> getStoreByUserId(int userId) {
        Map map = new HashMap<String, Object>();
        try {
            map = storeInfoMapper.selectByUserId(userId);
        } catch (Exception e) {
            map.put("statusCode", 1);
            map.put("errorDesc", "内部错误: "+e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, Object> updateStoreByUserId(Map map, int userId) {
        map.put("userId", userId);
        int upd = storeInfoMapper.updateStoreByUserId(map) + storeInfoMapper.updateUserByUserId(map);
        Map result = new HashMap<String, Object>();
        if(upd > 0) {
            result = storeInfoMapper.selectByUserId(userId);
        } else {
            result.put("statusCode", 1);
            result.put("errorDesc", "更新失败");
        }
        return result;
    }
}
