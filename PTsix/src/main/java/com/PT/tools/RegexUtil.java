package com.PT.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达 工具类
 */
public class RegexUtil {


    /**
     *  正则匹配
     */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
