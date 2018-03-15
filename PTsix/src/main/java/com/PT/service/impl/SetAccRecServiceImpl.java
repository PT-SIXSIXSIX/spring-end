package com.PT.service.impl;

import com.PT.bean.SetAccRec.SetAccRecInfoBean;
import com.PT.dao.DriverMapper;
import com.PT.dao.SetAccRecInfoMapper;
import com.PT.dao.SettleAccRecordMapper;
import com.PT.entity.Driver;
import com.PT.entity.DriverExample;
import com.PT.entity.SettleAccRecord;
import com.PT.entity.SettleAccRecordExample;
import com.PT.service.LogService;
import com.PT.service.SetAccRecService;
import com.PT.tools.OutputMessage;
import com.PT.tools.QueryToMap;
import com.PT.tools.ToStrings;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * created by yxhuang
 * 结算管理具体业务逻辑实现
 */
@Service
public class SetAccRecServiceImpl implements SetAccRecService{

    @Autowired
    SetAccRecInfoMapper setAccRecInfoMapper;
    @Autowired
    SettleAccRecordMapper settleAccRecordMapper;
    @Autowired
    LogService logService;
    @Autowired
    DriverMapper driverMapper;

    /**
     * 分局page和ipp分页，根据userId相等，根据query模糊查询
     * @param page
     * @param ipp
     * @param userId
     * @param query
     * @return
     */
    @Override
    public Map<String, Object> listSetAccRec(int page, int ipp, int userId, String query) {
        Map map = QueryToMap.stringToMap(query);
        OutputMessage.outputMap(map);
        String content = (String) map.get("content");
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(page, ipp);
        Map factors = new HashMap<String, Object>();
        if(null != content) factors.put("str", content);
        String date = (String) map.get("time");
        Date start = null, end = null;
        if(date != null && date != "") {
            String[] temp = date.split("[-]");
            for(int i = 0; i < temp.length; i++) {
                System.out.println(temp[i]);
            }
            start = new Date(Long.valueOf(temp[0]));
            end = new Date(Long.valueOf(temp[1]));
            factors.put("st", start);
            factors.put("ed", end);
        }
        factors.put("userId", userId);
        List list = setAccRecInfoMapper.selectByFactors(factors);
        int maxPage = (setAccRecInfoMapper.countByFactors(factors)-1)/ipp+1;
        result.put("maxPage", maxPage);
        result.put("records", list);
        return result;
    }

    /**
     * 根据ids删除结算记录（设置status=3）
     * 注意，事务回滚需要抛出Run的异常
     * @param setAccIds
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public Boolean deleteSetAccRec(List<String> setAccIds, int userId) {
        SettleAccRecordExample example = new SettleAccRecordExample();
        example.createCriteria().andStatusEqualTo(2).andSetAccIdIn(setAccIds);
        SettleAccRecord settleAccRecord = new SettleAccRecord();
        settleAccRecord.setStatus(3);
        try {
            if(settleAccRecordMapper.updateByExampleSelective(settleAccRecord, example) > 0) {
                String desc = ToStrings.listToStrings(setAccIds, '&');
                logService.insertLog(userId, "delete", "on table ykat_settle_account_records: "
                +"by ids in ["+desc+"]");
            }
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据ids来跟新状态，若state=2需要调用结算函数
     * @param setAccIds
     * @param state
     * @param userId
     * @return
     */
    @Transactional
    @Override
    public Boolean updateSetAccState(List<String> setAccIds, int state, int userId) {

        if(state == 1) {
            SettleAccRecordExample example = new SettleAccRecordExample();
            example.createCriteria().andStatusEqualTo(0).andSetAccIdIn(setAccIds);
            SettleAccRecord record = new SettleAccRecord();
            record.setStatus(state);
            if(settleAccRecordMapper.updateByExampleSelective(record, example) > 0) {
                String desc = ToStrings.listToStrings(setAccIds, '&');
                logService.insertLog(userId, "update", "on table ykat_settle_account_records: "
                        +"by ids in ["+desc+"] "+" and status = 0"+". set status to "+state);
            }
            else return false;
        } else {
            return settleAccount(setAccIds, userId);
        }
        return true;
    }

    /**
     * 结算操作，需要扣除司机佣金
     * @param setAccIds
     * @return
     */
    @Transactional
    public Boolean settleAccount(List<String> setAccIds, int userId) {
        try {
            int count = setAccRecInfoMapper.updateDriver(setAccIds);
            count += setAccRecInfoMapper.updateSetAccRec(setAccIds);
            DriverExample example = new DriverExample();
            example.createCriteria().andStatusEqualTo(1);
            Driver driver = new Driver();
            driver.setStatus(0);
            driverMapper.updateByExampleSelective(driver, example);
            if(count <= 0) return false;
            String desc = ToStrings.listToStrings(setAccIds, '&');
            logService.insertLog(userId, "update", "on table ykat_settle_account_records: "
                    +"by ids in ["+desc+"] "+" and status = 1"+". set status to "+2);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return true;
    }

}
