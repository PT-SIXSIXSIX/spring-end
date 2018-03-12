package com.PT.tools;

import java.util.Map;

public class OutputMessage {
    public static void outputMap(Map <String, Object> map) {
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue()+"\n\n");
        }
    }
}
