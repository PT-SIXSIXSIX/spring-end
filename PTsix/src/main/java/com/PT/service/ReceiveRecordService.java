package com.PT.service;

import java.util.List;
import java.util.Map;

public interface ReceiveRecordService{

    /**
     * 查询收账记录
     * @param userId 唯一用户请求
     * @param page 页码
     * @param ipp 没有数据量
     * @param queryCondition 查询条件
     * @return
     * @throws Exception
     */
    Map<String, Object> listReceiveRecord(int userId, int page, int ipp, String queryCondition) throws Exception;

    /**
     * 删除收账记录
     * @param userId
     * @param ids
     * @throws Exception
     */
    void deleteReceiveRecord(int userId, List<Integer> ids) throws Exception;

}
