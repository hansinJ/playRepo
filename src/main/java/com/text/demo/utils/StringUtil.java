package com.text.demo.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern emailPattern = Pattern.compile("\\w+@(\\w+\\.){1,3}\\w+");
    private static final Pattern mobilePattern = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");

    public static boolean isMobile(String s){
        return mobilePattern.matcher(s).matches();
    }

    public static boolean isPhone(String s){
        if(!StringUtil.isEmpty(s) && s.startsWith("1") && s.length() == 11){
            return true;
        }
        return false;
    }

    public static boolean isEmail(String s){
        return emailPattern.matcher(s).matches();
    }

    public static boolean isEmpty(String s) {
        return StringUtils.isEmpty(s);
    }

    public static String enableAsterisk (String phoneNumber) {
        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }
}
