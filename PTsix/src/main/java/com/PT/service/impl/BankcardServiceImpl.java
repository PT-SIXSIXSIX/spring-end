package com.PT.service.impl;

import com.PT.dao.BankcardMapper;
import com.PT.entity.Bankcard;
import com.PT.entity.BankcardExample;
import com.PT.service.BankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankcardServiceImpl implements BankcardService {
    @Autowired
    BankcardMapper bankcardMapper;
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

    @Override
    public Boolean addBankcard(Bankcard bankcard, int userId) {
        try {
            bankcard.setUserId(userId);
            bankcardMapper.insert(bankcard);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
