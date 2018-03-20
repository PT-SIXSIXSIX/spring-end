package com.PT.test;

import com.PT.entity.Driver;
import com.PT.service.RegistryLogonService;
import com.PT.tools.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by yxhuang
 */
public class ToolTests extends BaseTest {
    @Autowired
    RegistryLogonService registryLogonService;
    @Test
    public void JavabeanToMap() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Driver driver = new Driver();
        driver.setBalance(1000);
        driver.setCreatedAt(new Date());
        driver.setDriverName("hyx");
        driver.setId(2);
        driver.setDriverPhone("1891582623");
        System.out.println(BeanToMapUtil.convertBean(driver));
    }

    @Test
    public void underlineAndTumpSwich() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 100);
        map1.put("balance", 1000);
        map1.put("driver_name", "hyx");
        map1.put("driver_phone", "189512456");
        map1.put("created_at", new Date());
        Map<String, Object> map2 = UnderlineToTumpUtil.underlineToTump(map1);
        System.out.println(map2);

        Map<String, Object> map3 = UnderlineToTumpUtil.tumpToUnderline(map2);
        System.out.println(map3);
    }
    @Test
    public void MapToJavabean() throws IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 100);
        map.put("balance", 1000);
        map.put("driverName", "hyx");
        map.put("driverPhone", "189512456");
        map.put("createdAt", new Date());
        Driver driver = (Driver) BeanToMapUtil.convertMap(Driver.class, map);
        System.out.println(driver.getBalance()+" "+driver.getId()+" "+driver.getCreatedAt()+" "+driver.getDriverName()+" " +
                ""+driver.getDriverPhone());
    }
    @Test
    public void queryToMapTest() {
        String me = "content:+time:1520870400000-1521043200000";
        Map map = QueryToMap.stringToMap(me);
        OutputMessage.outputMap(map);
    }
    @Test
    public void tryCatchTest() {
        try {
            throw new Exception("this is a test");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
