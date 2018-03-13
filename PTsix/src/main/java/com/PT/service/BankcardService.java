package com.PT.service;

import com.PT.entity.Bankcard;

import java.util.List;

public interface BankcardService {
    List <Bankcard> getBankcardByUserId(int userId);
    Boolean addBankcard(Bankcard bankcard, int userId);
    Boolean deleteBankcard(List<String> cardIds, int userId);
}
