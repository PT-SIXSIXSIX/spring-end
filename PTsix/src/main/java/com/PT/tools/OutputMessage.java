package com.PT.tools;

import java.util.Map;


/**
 * created by yxhuang
 */
public class OutputMessage {
    /**
     * 快速输出map内容
     * @param map
     */
    public static void outputMap(Map <String, Object> map) {
        System.out.println("map is : \n");
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue()+"\n\n");
        }
    }
}
