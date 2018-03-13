package com.PT.service;

import java.util.Map;

public interface StoreService {
    Map <String, Object> getStoreByUserId(int userId);
    Map <String, Object> updateStoreByUserId(Map map, int userId);
}
