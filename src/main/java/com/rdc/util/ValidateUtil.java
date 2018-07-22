package com.rdc.util;

import java.util.regex.Pattern;

public class ValidateUtil {

    /**
     * 判断是否为空
     * @param str
     * @return true为空 false不为空
     */
    public static boolean isInvalidString(String str) {

        if (str == null || str.equals(""))
            return true;

        return false;

    }

    /**
     * 判断是否符合邮件格式
     * @param str
     * @return true符合 false不符合
     */
    public static boolean isMatchEmail(String str) {
        if (Pattern.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", str))
            return true;

        return false;
    }

}
