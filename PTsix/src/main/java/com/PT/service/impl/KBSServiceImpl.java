package com.PT.service.impl;

import com.PT.dao.KBSMapper;
import com.PT.entity.KBS;
import com.PT.entity.KBSExample;
import com.PT.service.KBSService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * created by yxhuang
 */
public class KBSServiceImpl implements KBSService{


    @Autowired
    KBSMapper kbsMapper;
    @Override
    public KBS getKBSById(int id) {
        return kbsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KBS> getList(KBSExample example) {
        return kbsMapper.selectByExample(example);
    }

    @Override
    public int updateKBS(KBS kbs) {
        return kbsMapper.updateByPrimaryKey(kbs);
    }

    @Override
    public int addKBS(KBS kbs) {
        return kbsMapper.insert(kbs);
    }

    @Override
    public int deleteKBSById(int id) {
        return kbsMapper.deleteByPrimaryKey(id);
    }
}
