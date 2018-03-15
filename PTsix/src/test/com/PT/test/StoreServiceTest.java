package com.PT.test;

import com.PT.service.StoreService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class StoreServiceTest extends BaseTest {

    @Autowired
    StoreService storeService;

    @Test
    public void getStoreByUserId() {
        int userId = 70;
        Map<String, Object> map = storeService.getStoreByUserId(userId);
        OutputMessage.outputMap(map);
    }
    @Test
    public void updateTest() {
        int userId = 70;
        Map<String, Object> map = new HashMap<>();
        map.put("serviceType", "修理厂");
        map.put("picHeadUrl", "修理厂");
        map.put("phone", "18996012345");
        map.put("idCard", "修理厂");
        map.put("companyName", "修理厂sdddd");
        map.put("picTailUrl", "修理厂");
        map.put("name", "修理厂");
        Map origin = storeService.getStoreByUserId(userId);
        Map<String, Object> result = storeService.updateStoreByUserId(map, userId);
        OutputMessage.outputMap(result);
        storeService.updateStoreByUserId(origin, userId);
    }
}
