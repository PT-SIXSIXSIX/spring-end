package com.PT.service.impl;

import com.PT.dao.DepositInfoMapper;
import com.PT.dao.DepositRechargeRecordMapper;
import com.PT.entity.DepositRechargeRecordExample;
import com.PT.service.DepositService;
import com.PT.service.LogService;
import com.PT.tools.QueryToMap;
import com.PT.tools.ToStrings;
import com.PT.tools.YkatCommonUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositServiceImpl implements DepositService {


    @Autowired
    private DepositRechargeRecordMapper depositRechargeRecordMapper;

    @Autowired
    private DepositInfoMapper depositInfoMapper;

    @Autowired
    private LogService logService;
    /**
     * {
     "max_page": 1,
     "records": [
     {
     "des_rcg_id": 1,
     "recharge_money": 1,
     "recharge_time": "Hello, world!",
     "current_deposit": 1,
     "state": 1
     }
     ]
     }
     * @param userId
     * @param page
     * @param ipp
     * @param queryCondition
     * @return
     * @throws Exception
     */

    public Map<String,Object> listDepositRecord(int userId, int page, int ipp, String queryCondition) throws Exception
    {
        Map factors = new HashMap();
        if(queryCondition!=null && !"".equals(queryCondition)) { //有搜索条件时
            factors = QueryToMap.stringToMap(queryCondition);
            if (factors.containsKey("time")) {
                YkatCommonUtil.putFromAndToDate(factors, (String) factors.get("time"));
            }
        }

        factors.put("userId",userId);

        PageHelper.startPage(page,ipp);
        List<Map<String, Object> > records = depositInfoMapper.selectByFactor(factors);
        int maxPage = (depositInfoMapper.countByFactor(factors)-1)/ipp+1;

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("records",records);
        resultMap.put("maxPage",maxPage);
        return resultMap;
    }

    @Transactional
    @Override
    public void deleteDepositRecord(int userId, List<Integer> ids) throws Exception
    {

        if ( ids==null || ids.size() == 0){
            throw new Exception("缺少参数");
        }

        DepositRechargeRecordExample example = new DepositRechargeRecordExample();
        example.createCriteria().andIdIn(ids);
        String descp = ToStrings.integerListToStrings(ids,'&');
        int deleteResult = depositRechargeRecordMapper.deleteByExample(example);
        logService.insertLog(userId,"delete","on table ykat_deposit_recharge_records " +
                "by ids in ["+descp+"]");
    }
}
