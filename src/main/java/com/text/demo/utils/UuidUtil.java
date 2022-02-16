package com.text.demo.utils;

import java.util.UUID;

public class UuidUtil {
    public static String createUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
}
