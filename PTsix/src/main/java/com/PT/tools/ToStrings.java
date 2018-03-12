package com.PT.tools;

import java.util.List;

public class ToStrings {
    public static String listToStrings(List<String> list, Character ch) {
        StringBuilder buff = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            if(i > 0)
                buff.append(ch);
            buff.append(list.get(i).toString());
        }
        return buff.toString();
    }
}
