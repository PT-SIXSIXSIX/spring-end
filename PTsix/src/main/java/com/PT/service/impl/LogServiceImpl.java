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
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;
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
