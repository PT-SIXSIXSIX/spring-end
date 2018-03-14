package com.PT.tools;

import java.util.List;

/**
 * created by yxhuang
 *
 */
public class ToStrings {

    /**
     * 把String列表根据ch分割返回长字符串
     * @param list
     * @param ch
     * @return
     */
    public static String listToStrings(List<String> list, Character ch) {
        StringBuilder buff = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            if(i > 0)
                buff.append(ch);
            buff.append(list.get(i).toString());
        }
        return buff.toString();
    }

    /**
     * 把Integer列表根据ch分割返回长字符串
     * @param list
     * @param ch
     * @return
     */
    public static String integerListToStrings(List<Integer> list, Character ch) {
        StringBuilder buff = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            if(i > 0)
                buff.append(ch);
            buff.append(list.get(i).toString());
        }
        return buff.toString();
    }
}
