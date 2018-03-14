package com.PT.service.impl;

import com.PT.dao.LogMapper;
import com.PT.entity.Log;
import com.PT.entity.LogExample;
import com.PT.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by yxhuang
 * 操作日志业务实现类
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    /**
     * 插入日志实现
     * @param userId
     * @param optType
     * @param optDesc
     * @return
     */
    @Override
    public boolean insertLog(int userId, String optType, String optDesc) {
        try {
            Log log = new Log();
            log.setOptUsrId(userId);
            log.setOptType(optType);
            log.setOptDescp(optDesc);
            log.setOperationAt(new Date());
            logMapper.insert(log);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量删除日志
     * @param ids
     * @return
     */
    @Override
    public boolean deleteLogById(List<Integer> ids) {
        LogExample logExample = new LogExample();
        logExample.createCriteria().andIdIn(ids);
        try {
            logMapper.deleteByExample(logExample);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 时间范围删除日志
     * @param start
     * @param end
     * @return
     */
    @Override
    public boolean deleteLogBetweenTm(Date start, Date end) {
        LogExample logExample = new LogExample();
        logExample.createCriteria().andOperationAtBetween(start, end);
        try {
            logMapper.deleteByExample(logExample);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 时间范围查找日志
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<Log> selectLogBetweenTime(Date start, Date end) {
        LogExample logExample = new LogExample();
        logExample.createCriteria().andOperationAtBetween(start, end);
        List list = new ArrayList<Log>();
        try {
            list = logMapper.selectByExample(logExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
