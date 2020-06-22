package com.wowoniu.fendian.utils;

import java.util.UUID;

public class StringUtils {

    /**
     * 生成uuid
     * @return
     */
    public static String getUuid(){
        String id = null;
        UUID uuid = UUID.randomUUID();
        id = uuid.toString().replace("-","");

        return id;
    }

    /**
     * 字符串验证
     * @param str
     * @return
     */
    public static Boolean isEmpity(String str){
        if (str == null || str.trim().equals("")){
            return true;
        }else {
            return false;
        }

    }
}
