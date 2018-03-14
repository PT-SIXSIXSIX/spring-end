package com.PT.tools;


import java.util.HashMap;
import java.util.Map;

/**
 * created by yxhuang
 * 用于解析条件
 * 把查询条件解析为map方便存取
 */
public class QueryToMap {
    public static Map<String, String> stringToMap(String method) {
        Map map = new HashMap<String, String>();
        if(method == null || method.equals("")) { return map; }
        String[] factors = method.split("[ ,.+]");
        for(int i = 0; i < factors.length; i++) {
            String[] temp = factors[i].split("[:]");
            if(temp.length > 1) {
                map.put(temp[0], temp[1]);
            } else {
                map.put(temp[0], "");
            }
        }
        return map;
    }
}
