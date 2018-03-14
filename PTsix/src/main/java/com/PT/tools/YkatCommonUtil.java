package com.PT.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class YkatCommonUtil {

    private static Calendar calendar = Calendar.getInstance();

    /**
     *  把 搜索语句2018-3-6日-2018年3月7日 Map类型的数据中去
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

    /**
     * 把用毫秒表示的时间转换为 Date
     * @param millis
     * @return
     * @throws Exception
     */
    public static Date getDateFromMillis(String millis) throws Exception{
        calendar.clear();
        calendar.setTimeInMillis(Long.valueOf(millis));
        return calendar.getTime();
    }


    public static String checkMapHasNull(Map<String, Object> map){

        StringBuffer sb = new StringBuffer();

        for (Map.Entry entry : map.entrySet()){
            if (entry.getValue()==null){
                sb.append("parameter: "+entry.getKey()+"&");
            }
        }
        String result = null;
        if (sb.length() == 0) sb.append("success");
        else {
            result = sb.toString();
            result = result.substring(0,result.length()-1);
            result += " lack value!";
            return result;
        }

        return sb.toString();
    }

    /**
     * 将Date 按照 format的格式解析
     * @param date
     * @param format
     * @return
     */
    public static String dateToStringByFormat(Date date, String format){
        SimpleDateFormat formater = new SimpleDateFormat(format);
        String dateString = formater.format(date);
        return dateString;
    }
}
