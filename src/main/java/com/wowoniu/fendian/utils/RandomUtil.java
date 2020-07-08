package com.wowoniu.fendian.utils;

import java.util.Random;

public class RandomUtil {

    public static String getRandom(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
