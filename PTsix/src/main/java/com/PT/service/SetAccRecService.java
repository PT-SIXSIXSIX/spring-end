package com.PT.service;


import java.util.List;
import java.util.Map;

/**
 * created by yxhuang
 * 用于定义结算管理模块业务层逻辑
 */
public interface SetAccRecService {
    /**
     * 根据page，ipp分页，根据userId和q来模糊搜索结算信息
     * @param page
     * @param ipp
     * @param userId
     * @param q
     * @return
     */
    Map<String,Object> listSetAccRec(int page, int ipp, int userId, String q);

    /**
     * 根据ids来删除结算记录（设置status=3）
     * @param SetAccIds
     * @param userId
     * @return
     */
    Boolean deleteSetAccRec(List<String> SetAccIds, int userId);

    /**
     * 根据ids来更新结算记录
     * @param SetAccIds
     * @param state
     * @param userId
     * @return
     */
    Boolean updateSetAccState(List<String> SetAccIds, int state, int userId);
}
