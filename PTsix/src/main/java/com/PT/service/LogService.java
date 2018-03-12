package com.PT.service;


import com.PT.entity.Log;

import java.util.Date;
import java.util.List;

/**
 * created by yxhuang
 * 用户操作的日志记录
 */
public interface LogService {
    boolean insertLog(int userId, String optType, String optDesc);
    boolean deleteLogById(List<Integer> ids);
    boolean deleteLogBetweenTm(Date start, Date end);
    List <Log> selectLogBetweenTime(Date start, Date end);
}
