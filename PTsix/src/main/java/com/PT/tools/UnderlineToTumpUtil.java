package com.PT.tools;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * created by yxhuang
 */
public class UnderlineToTumpUtil {


    /**
     * 下划线String转化为驼峰String
     * @param underline
     * @return tump
     */
    public static String underlineToTump(String underline) {
        StringBuilder result = new StringBuilder();
        if (underline != null && underline.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underline.length(); i++) {
                char ch = underline.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 驼峰转化为下划线
     * @param tump
     * @return underline
     */
    public static String tumpToUnderline(String tump) {
        StringBuilder result = new StringBuilder();
        if (tump != null && tump.length() > 0) {
            result.append(tump.substring(0, 1).toLowerCase());
            for (int i = 1; i < tump.length(); i++) {
                char ch = tump.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }



    public static Map underlineToTump(Map<String, Object> map)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> result = new HashMap<>();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            String newKey = underlineToTump(entry.getKey());
            result.put(newKey, entry.getValue());
        }
        return result;
    }
    public static Map tumpToUnderline(Map<String, Object> map)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> result = new HashMap<>();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            String newKey = tumpToUnderline(entry.getKey());
            result.put(newKey, entry.getValue());
        }
        return result;
    }
}
