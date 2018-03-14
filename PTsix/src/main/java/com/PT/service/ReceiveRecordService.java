package com.PT.service;

import java.util.List;
import java.util.Map;

public interface ReceiveRecordService{

    Map<String, Object> listReceiveRecord(int userId, int page, int ipp, String queryCondition) throws Exception;
    void deleteReceiveRecord(int userId, List<Integer> ids) throws Exception;

}
