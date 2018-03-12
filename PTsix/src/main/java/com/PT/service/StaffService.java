package com.PT.service;

import java.util.Map;

public interface StaffService {
    Map<String , Object> listStaff(int userId,int page,int ipp,String queryCondition);
    void deleteByIStaffId(int staffId);
}
