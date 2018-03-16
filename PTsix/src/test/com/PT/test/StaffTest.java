package com.PT.test;

import com.PT.service.StaffService;
import com.PT.tools.RegexUtil;
import com.PT.tools.YkatConstant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StaffTest extends BaseTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void insertTest(){
        String name = "孙颖", password = "950911", phone = "15923176013";
        if(RegexUtil.match(YkatConstant.telePhoneRegex,phone)){
            Map factors = new HashMap();
            factors.put("phone",phone);
            factors.put("password",password);
            factors.put("name",name);
            try {
                staffService.addStaff(1,factors);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("电话格式不对");
        }
    }

    @Test
    public  void timeStampTest(){
        Calendar calendar = Calendar.getInstance();
        long millis = Long.valueOf("1520352000000");
        calendar.setTimeInMillis(millis);
        Date date = calendar.getTime();

        System.out.println(date);
    }

    @Test
    public void deleteStaffTest(){
        int userId = 1;
        int staffId = 3;
        try{
            staffService.deleteByStaffId(userId,staffId);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
