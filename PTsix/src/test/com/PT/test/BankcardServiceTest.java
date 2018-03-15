package com.PT.test;

import com.PT.service.BankcardService;
import com.PT.tools.OutputMessage;
import com.PT.tools.ToStrings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * created by yxhuang
 * 银行卡业务逻辑测试类
 */
public class BankcardServiceTest extends BaseTest {

    @Autowired
    BankcardService service;
    @Test
    public void getBankcardByUserId() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        int userId = 41;
        List list = service.getBankcardByUserId(userId);
        OutputMessage.outputList(list);
    }
}
