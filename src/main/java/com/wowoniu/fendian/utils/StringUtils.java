package com.wowoniu.fendian.utils;

import java.util.UUID;

public class StringUtils {

    public static String getUuid(){
        String id = null;
        UUID uuid = UUID.randomUUID();
        id = uuid.toString().replace("-","");

        return id;
    }
}
