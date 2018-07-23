package com.rdc.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class CharacterUtil {

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
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

    /**
     * 过滤特殊字符
      * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public   static   String StringFilter(String   str)   throws PatternSyntaxException {
        // 清除掉所有特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }
}