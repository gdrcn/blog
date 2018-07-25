package com.rdc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtil {
    /**
     * Asce 2018/7/25
     * MD5加密
     * @param str
     * @return
     */
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
    /**
     * Asce 2018/7/25
     * 将数据库中时间转为：年-月-天     时：分
     * @param timeMs
     * @return
     */
    public static String msecToMinutes(String timeMs) {
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date time=null;
        try {
            time=date.parse(timeMs);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat date2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return date2.format(time);
    }

}
