package com.PT.test;

import com.PT.service.ReceiveRecordService;
import com.PT.tools.OutputMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ReceiveRecordTest extends BaseTest{

    @Autowired
    private ReceiveRecordService receiveRecordService;

    @Test
    public void testList(){
        try {
            Map<String, Object>  map = receiveRecordService.listReceiveRecord(1,1,2,"");
            OutputMessage.outputMap(map);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    

}
