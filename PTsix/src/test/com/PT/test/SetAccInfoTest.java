package com.PT.test;

import com.PT.bean.SetAccRecInfoBean;
import com.PT.dao.SetAccRecInfoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetAccInfoTest extends BaseTest {
    @Autowired
    SetAccRecInfoMapper setAccRecInfoMapper;
    @Test
    public void selectTest() {
        Map map = new HashMap<String, Object>();
        map.put("userId", 41);
        map.put("state", 0);
        map.put("setAccId", "1451251");
        map.put("driverName", "hyx");
//        map.put("state", Integer.parseInt((String) map.get("state")));
        System.out.println(map.size());
        List list = setAccRecInfoMapper.selectByFactors(map);
        for(int i = 0; i < list.size(); i++) {
            SetAccRecInfoBean bean = (SetAccRecInfoBean) list.get(i);
            bean.print();
        }
    }
}
