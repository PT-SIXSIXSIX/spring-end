package com.PT.service;

import java.util.List;
import java.util.Map;

public interface DepositService {

    Map<String,Object> listDepositRecord(int userId, int page, int ipp, String queryCondition) throws Exception;
    void deleteDepositRecord(int userId, List<Integer> ids) throws Exception;
}
