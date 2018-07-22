package com.rdc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertUtil {

    public static String encryptMd5(String str) {
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //加密后的字符串
        byte[] newstr = null;
        try {
            newstr = md5.digest(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < newstr.length; i++) {
            int val = ((int) newstr[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
