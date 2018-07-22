package com.rdc.util;

import java.util.Random;

/**
 * 生成随机字符串
 */
public class CharacterUtil {

    public static String getRandomString(int length) {

        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {

            int number = random.nextInt(62);

            sb.append(str.charAt(number));
        }

        return sb.toString();
    }
}