package com.PT.dao;

import java.util.Map;

public interface YkatCommonUtilMapper {

    /**
     * 某些单号字段'colName'需要自动增加，使用这个，传入tableName 和 colName进 Map，返回下一个id
     * @param map
     */
    void generateAutoIncrementId(Map map);

    /**
     * 根据店主账号唯一标识 返回4s店当前保证金
     * @param userId
     * @return
     */
    Integer getCurrentDepositByUserId(int userId);
    Integer getStoreIdByUserId(int userId);

}
