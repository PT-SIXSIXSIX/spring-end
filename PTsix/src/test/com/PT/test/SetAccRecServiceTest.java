package com.PT.test;

import com.PT.dao.SetAccRecInfoMapper;
import com.PT.service.SetAccRecService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetAccRecServiceTest extends BaseTest {
    @Autowired
    SetAccRecService setAccRecService;
    @Test
    public void listSetAccRec() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map map = new HashMap<String, Object>();
        long TM = 1000*3600*3;
        int page = 1;
        int ipp = 5;
        long startTM = (System.currentTimeMillis()-TM);
        long endTM = (System.currentTimeMillis()+TM);
        String q1 = "content:201+"+"time:"+startTM+"-"+endTM;
        String q2 = "content:000+"+"time:";
        int userId = 41;
        Map result = setAccRecService.listSetAccRec(page, ipp, userId, q1);
        List elements = (List) result.get("records");
        OutputMessage.outputList(elements);
        System.out.println("---------------------");
        result = setAccRecService.listSetAccRec(page, ipp, userId, q2);
        elements = (List) result.get("records");
        OutputMessage.outputList(elements);
        System.out.println("---------------------");
    }

    @Test
    public void deleteSetAccRec() {
        List<String> list = new ArrayList<>();
        list.add("2000555556");
        list.add("2000555557");
        Boolean result = setAccRecService.deleteSetAccRec(list, 41);
        System.out.println(result);
    }
    @Test
    public void updateSetAccState() {
        List<String> list = new ArrayList<>();
        list.add("2000555556");
        list.add("2000555557");
        Boolean result = setAccRecService.updateSetAccState(list, 2, 41);
        System.out.println(result);
    }
    @Test
    public void settleAccountTest() {
        List<String> list = new ArrayList<>();
        list.add("2000555556");
        list.add("2000555557");
        Boolean result = setAccRecService.updateSetAccState(list, 2, 41);
        System.out.println(result);
    }
}
