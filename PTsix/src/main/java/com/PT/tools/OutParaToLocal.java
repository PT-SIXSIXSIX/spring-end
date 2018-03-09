package com.PT.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OutParaToLocal {
    /**
     * 转换getParameterMap返为java.util.Map对象
     * @param requestPara
     * @return
     */
    public static Map<String, String> getMapFromRequestMap(Map<String, String[]> requestPara) {
        Map<String, String> paraMap = new HashMap<String, String>();
        Iterator<Map.Entry<String, String[]>> it = requestPara.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values != null && values.length >= 1) {
                paraMap.put(key, values[0]);
            }
        }
        return paraMap;
    }
}
