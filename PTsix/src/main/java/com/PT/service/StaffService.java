package com.PT.service;

import java.util.Map;

public interface StaffService {
    /**
     * 查找店员
     * @param userId 唯一用户标识
     * @param page 页数
     * @param ipp 每页数据量
     * @param queryCondition 查询语句
     * @return
     */
    Map<String , Object> listStaff(int userId,int page,int ipp,String queryCondition);

    /**
     * 删除员工
     * @param userId 唯一用户表示
     * @param staffId 员工唯一用户标识
     */
    void deleteByStaffId(int userId, int staffId);

    /**
     * 更新员工信息
     * @param userId 唯一用户表示
     * @param staffId 要更新信息的员工标识
     * @param factor 更新参数， 包含电话，姓名
     */
    void updateByStaffId(int userId, int staffId, Map<String,String> factor);

    /**
     * 添加店员
     * @param userId 唯一用户标识
     * @param factors 参数 ，密码，姓名，电话
     * @throws Exception
     */
    void addStaff(int userId, Map factors) throws Exception;
}
