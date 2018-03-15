package com.PT.tools;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
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
    /**
     * 快速输出list类容
     */
    public static <T> void outputList(List<T> list) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        for(int i = 0; i < list.size(); i++) {
            T bean = list.get(i);
            Map map = BeanToMapUtil.convertBean(bean);
            outputMap(map);
        }
    }
    public static <T> void outputBean(T bean) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map map = BeanToMapUtil.convertBean(bean);
        outputMap(map);
    }
}
