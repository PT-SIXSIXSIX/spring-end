package com.PT.service;

import com.PT.entity.Bankcard;

import java.util.List;

/**
 * created by yxhuang
 * 银行卡服务业务逻辑实现
 */
public interface BankcardService {
    /**
     * 根据userId查询用户绑定的银行卡
     * @param userId
     * @return 银行卡bean的列表
     */
    List <Bankcard> getBankcardByUserId(int userId);

    /**
     * 根据userId绑定银行卡信息，最多绑定3张
     * @param bankcard
     * @param userId
     * @return 绑定的结果
     */
    Boolean addBankcard(Bankcard bankcard, int userId) throws Exception;

    /**
     * 根据userId和ids删除银行卡
     * @param cardIds
     * @param userId
     * @return 银行卡删除结果
     */
    Boolean deleteBankcard(List<String> cardIds, int userId);
}
