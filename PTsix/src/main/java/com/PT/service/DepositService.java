package com.PT.service;

import java.util.List;
import java.util.Map;

public interface DepositService {

    /**
     * 查询保证金补足记录
     * @param userId 唯一用户标识
     * @param page
     * @param ipp
     * @param queryCondition
     * @return
     * @throws Exception
     */
    Map<String,Object> listDepositRecord(int userId, int page, int ipp, String queryCondition) throws Exception;

    /**
     * 删除保证金补足记录
     * @param userId 唯一用户表示
     * @param ids 一组保证金 id号
     * @throws Exception
     */
    void deleteDepositRecord(int userId, List<Integer> ids) throws Exception;
}
