package com.PT.test;

import com.PT.bean.SetAccRecInfoBean;
import com.PT.dao.SetAccRecInfoMapper;
import com.PT.service.SetAccRecService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetAccInfoTest extends BaseTest {
    @Autowired
    SetAccRecInfoMapper setAccRecInfoMapper;
    @Autowired
    SetAccRecService setAccRecService;
    @Test
    public void selectTest() {
        Map map = new HashMap<String, Object>();
//        map.put("userId", 10);
//        System.out.println(map.size());
//        List list = setAccRecInfoMapper.selectByFactors(map);
//        for(int i = 0; i < list.size(); i++) {
//            SetAccRecInfoBean bean = (SetAccRecInfoBean) list.get(i);
//            bean.print();
//        }
        String info = "hyx";
        Map result = setAccRecService.listSetAccRec(1, 1, 41, info);
//        System.out.println(result.size());
        OutputMessage.outputMap(result);
    }
}
