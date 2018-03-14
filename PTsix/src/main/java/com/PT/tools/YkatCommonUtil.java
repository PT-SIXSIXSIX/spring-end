package com.PT.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class YkatCommonUtil {

    private static Calendar calendar = Calendar.getInstance();
    /**
     *
     * @param map
     * @param timePeriod eg. 2018-3-6日-2018年3月7日
     * @throws Exception
     */
    public static void putFromAndToDate(Map map, String timePeriod) throws Exception{
        if(timePeriod==null || "".equals(timePeriod))
            return;

        String[] factors = timePeriod.split("-");
        try {

            Date fromDate = getDateFromMillis(factors[0]);
            Date toDate = getDateFromMillis(factors[1]);
            map.put("fromDate", fromDate);
            map.put("toDate", toDate);
        }catch (Exception e){
            throw new Exception("日期解析错误");
        }
    }

    public static Date getDateFromMillis(String millis) throws Exception{
        calendar.clear();
        calendar.setTimeInMillis(Long.valueOf(millis));
        return calendar.getTime();
    }
}
