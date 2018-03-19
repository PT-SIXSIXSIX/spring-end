package com.PT.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoCheckUtil {



    /**
     * 密码为数字+字母形式，无特殊字符，字符长度限制20
     * @param s
     * @return
     */
    public static boolean passwordCheck(String s) {
        if(s.length() < 6 || s.length() > 20) return false;
        return StringUtils.isAlphanumeric(s);
    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean phoneNoCheck(String mobiles){
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
            Matcher m = regex .matcher(mobiles);
            flag = m.matches();
        } catch(Exception e) {
            flag = false;
        }
        return flag;
    }
    /**
     * 校验身份证
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean IDCardCheck(String idCard) {
        String REGEX_ID_CARD = null;
        if(idCard.length() == 15) {
            REGEX_ID_CARD = "^[1-9]\\d{7}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}$";
        } else if(idCard.length() == 18) {
            REGEX_ID_CARD = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}([0-9]||X)$";
        }
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean bankardCheck(String cardId) {
        if(cardId.length() > 19 || cardId.length() < 16) return false;
        if(!StringUtils.isAlphanumeric(cardId)) return false;
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return (cardId.charAt(cardId.length() - 1) == bit);
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
}
