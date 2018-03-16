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

    /**
     * 根据 用户唯一标识符 获得 用户角色
     * @param userId
     * @return
     */
    Integer getRoleByUserId(int userId);

    /**
     * 根据银行卡号 返回银行卡的数据库主键
     * @param cardId
     * @return
     */
    Integer getBankcardPrimaryKeyByCardId(String cardId);

}
