package com.PT.service;

import java.util.Map;

public interface StaffService {
    Map<String , Object> listStaff(int userId,int page,int ipp,String queryCondition);
    void deleteByStaffId(int userId, int staffId);
    void updateByStaffId(int userId, int staffId, Map<String,String> factor);
    void addStaff(int userId, Map factors) throws Exception;
}
