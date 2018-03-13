package com.PT.service;


import java.util.List;
import java.util.Map;

/**
 * created by yxhuang
 * 用于定义结算管理模块业务层逻辑
 */
public interface SetAccRecService {
    Map<String,Object> listSetAccRec(int page, int ipp, int userId, String q);
    Boolean deleteSetAccRec(List<String> SetAccIds, int userId);
    Boolean updateSetAccState(List<String> SetAccIds, int state, int userId);
}
