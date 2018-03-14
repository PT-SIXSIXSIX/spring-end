package com.PT.service.impl;

import com.PT.dao.BankcardMapper;
import com.PT.entity.Bankcard;
import com.PT.entity.BankcardExample;
import com.PT.service.BankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by yxhuang
 * 银行卡服务业务层实现类
 */
@Service
public class BankcardServiceImpl implements BankcardService {

    @Autowired
    BankcardMapper bankcardMapper;

    /**
     * 获得银行卡实现
     * @param userId
     * @return 银行卡列表
     */
    @Override
    public List<Bankcard> getBankcardByUserId(int userId) {
        List<Bankcard> list = new ArrayList();
        try {
            BankcardExample example = new BankcardExample();
            example.createCriteria().andUserIdEqualTo(userId);
            list = bankcardMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加银行卡信息
     * @param bankcard
     * @param userId
     * @return
     */
    @Override
    public Boolean addBankcard(Bankcard bankcard, int userId) {
        try {
            bankcard.setUserId(userId);
            BankcardExample example = new BankcardExample();
            example.createCriteria().andUserIdEqualTo(userId);
            if(bankcardMapper.countByExample(example) > 3) {
                throw new Exception("银行卡数量超限，一人最多绑定三张");
            }
            bankcardMapper.insert(bankcard);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除银行卡
     * @param cardIds
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteBankcard(List<String> cardIds, int userId) {
        try {
            BankcardExample example = new BankcardExample();
            example.createCriteria().andUserIdEqualTo(userId).andCardIdIn(cardIds);
            bankcardMapper.deleteByExample(example);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
