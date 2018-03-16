package com.PT.test;

import com.PT.entity.Bankcard;
import com.PT.service.BankcardService;
import com.PT.tools.OutputMessage;
import com.PT.tools.ToStrings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

    /**
     * 一人最多绑定4张卡已测试，点击4次送屠龙
     */
    @Test
    public void addBankcard() {
        int userId = 1;
        Bankcard bankcard = new Bankcard();
        bankcard.setBankName("中国银行");
        bankcard.setCardId("312035429365234634623");
        bankcard.setOwnerIdCard("45662367623473734");
        bankcard.setOwnerName("官老板");
        bankcard.setType("储蓄卡");
        Boolean yes = service.addBankcard(bankcard, userId);
        System.out.println(yes);
    }

    @Test
    public void deleteBankcard() {
        int userId = 41;
        List<String> list = new ArrayList<>();
        list.add("312035429365234634623");
        service.deleteBankcard(list, 41);
    }
}
