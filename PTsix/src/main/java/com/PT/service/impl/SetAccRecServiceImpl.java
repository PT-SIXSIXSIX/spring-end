package com.PT.service.impl;

import com.PT.dao.SetAccRecInfoMapper;
import com.PT.dao.SettleAccRecordMapper;
import com.PT.entity.SettleAccRecord;
import com.PT.entity.SettleAccRecordExample;
import com.PT.service.LogService;
import com.PT.service.SetAccRecService;
import com.PT.tools.ToStrings;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public Map<String, Object> listSetAccRec(int page, int ipp, int userId, String query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page, ipp);
        Map factors = new HashMap<String, Object>();
        factors.put("str", query);
        factors.put("userId", userId);
        List list = setAccRecInfoMapper.selectByFactors(factors);
        int maxPage = (setAccRecInfoMapper.countByFactors(factors)-1)/ipp+1;
        map.put("maxPage", maxPage);
        map.put("records", list);
        return map;
    }

    @Override
    public Boolean deleteSetAccRec(List<String> setAccIds, int userId) {
        SettleAccRecordExample example = new SettleAccRecordExample();
        for(int i = 0; i < setAccIds.size(); i++)
            System.out.println(setAccIds.get(i));
        example.createCriteria().andStatusEqualTo(2).andSetAccIdIn(setAccIds);
        try {
            if(settleAccRecordMapper.deleteByExample(example) > 0) {
                String desc = ToStrings.listToStrings(setAccIds, '&');
                logService.insertLog(userId, "delete", "on table ykat_settle_account_records: "
                +"by ids in ["+desc+"]");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateSetAccState(List<String> setAccIds, int state, int userId) {
        SettleAccRecordExample example = new SettleAccRecordExample();
        SettleAccRecordExample.Criteria criteria = example.createCriteria();
        criteria.andSetAccIdIn(setAccIds);
        SettleAccRecord record = new SettleAccRecord();
        record.setStatus(state);
        try {
            String addCondition = new String();
            if(state == 2) {
                record.setTradedAt(new Date());
                criteria.andStatusEqualTo(1);
                addCondition = "and status = " + state;
            }
            if(settleAccRecordMapper.updateByExampleSelective(record, example) > 0) {
                String desc = ToStrings.listToStrings(setAccIds, '&');
                logService.insertLog(userId, "update", "on table ykat_settle_account_records: "
                        +"by ids in ["+desc+"] "+addCondition+". set status to "+state);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
