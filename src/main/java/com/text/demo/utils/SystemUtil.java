package com.text.demo.utils;

import java.util.UUID;

public class SystemUtil {
    public static String generateUUIDNoWhiffletree(){
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
    public static String generateUUID(){
        return UUID.randomUUID().toString().toLowerCase();
    }
}
