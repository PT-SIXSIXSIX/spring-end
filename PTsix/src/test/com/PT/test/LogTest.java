package com.PT.test;

import com.PT.dao.LogMapper;
import com.PT.service.LogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogTest extends BaseTest{
    @Autowired
    LogService logService;
    @Test public void insertLog() {
        int userId = 41;
        String optType = "删除";
        String optDesc = "删除订单";
        logService.insertLog(userId,optType, optDesc);
    }
    @Test public void deleteByIds() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        logService.deleteLogById(list);
    }
    @Test public void selectBetweenTm() {
        long currentTime = System.currentTimeMillis() - 60 * 60 * 1000L;
        Date start = new Date(currentTime);
        Date end = new Date();
        List list = logService.selectLogBetweenTime(start, end);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    @Test public void deleteBetweenTm() {
        long currentTime = System.currentTimeMillis() - 60 * 60 * 1000L;
        Date start = new Date(currentTime);
        Date end = new Date();
        Boolean yes = logService.deleteLogBetweenTm(start, end);
        System.out.println(yes);
    }
}
