package com.PT.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达 工具类
 */
public class RegexUtil {

    private static final String telePhoneRegex = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
    private static final String phoneRegex =  "^(\\d{3,4}-)?\\d{6,8}$";

    /**
     *  正则匹配
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
