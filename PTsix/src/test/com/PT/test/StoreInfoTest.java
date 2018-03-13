package com.PT.test;

import com.PT.service.StoreService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class StoreInfoTest extends BaseTest {

    @Autowired
    StoreService storeService;

    @Test
    public void selectTest() {
        int userId = 59;
        Map<String, Object> map = storeService.getStoreByUserId(userId);
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println(entry.getKey()+"  :  "+entry.getValue()+"\n");
        }
    }
    @Test
    public void updateTest() {
        int userId = 59;
        Map<String, Object> map = new HashMap<>();
//        map.put("serviceType", "修理厂");
//        map.put("picHeadUrl", "修理厂");
        map.put("phone", "233333");
//        map.put("idCard", "修理厂");
        map.put("companyName", "修理厂sdddd");
//        map.put("picTailUrl", "修理厂");
//        map.put("name", "修理厂");
//        map.put("reservePhone", "修理厂");
//        map.put("location", "修理厂");

        Map<String, Object> result = storeService.updateStoreByUserId(map, userId);
        for(Map.Entry entry: result.entrySet()) {
            System.out.println(entry.getKey()+"  :  "+entry.getValue()+"\n");
        }
    }
}
