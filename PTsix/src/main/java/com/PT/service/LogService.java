package com.PT.service;


import com.PT.entity.Log;

import java.util.Date;
import java.util.List;

/**
 * created by yxhuang
 * 用户操作的日志记录
 */
public interface LogService {
    /**
     * 根据userId，执行类型，执行内容插入操作日志
     * @param userId
     * @param optType
     * @param optDesc
     * @return 插入结果
     */
    boolean insertLog(int userId, String optType, String optDesc);

    /**
     * 根据ids（日志key键）批量删除操作日志
     * @param ids
     * @return 删除结果
     */
    boolean deleteLogById(List<Integer> ids);

    /**
     * 根据时间查日志，传入起始时间和结束时间
     * @param start
     * @param end
     * @return 删除结果
     */
    boolean deleteLogBetweenTm(Date start, Date end);

    /**
     * 根据起始时间和结束时间查询操作日志
     * @param start
     * @param end
     * @return
     */
    List <Log> selectLogBetweenTime(Date start, Date end);
}
