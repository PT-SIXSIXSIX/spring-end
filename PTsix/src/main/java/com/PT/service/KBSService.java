package com.PT.service;


import com.PT.entity.KBS;
import com.PT.entity.KBSExample;

import java.util.List;

/**
 *
 * created by yxhuang
 */
public interface KBSService {
    KBS getKBSById(int id);
    List<KBS> getList(KBSExample example);
    int updateKBS(KBS kbs);
    int addKBS(KBS kbs);
    int deleteKBSById(int id);
}
