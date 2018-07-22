package com.rdc.util;


import org.apache.commons.mail.HtmlEmail;

public class SendemailUtil {


    /**
     * 发送邮箱验证码验证
     * @param emailadress
     * @param code
     * @return
     */
    public static boolean sendEmail(String emailadress, String code) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo(emailadress);// 收件地址
            email.setFrom("gdut0000@163.com", "aa");
            email.setAuthentication("gdut0000@163.com", "123456aaa");
            email.setSubject("博客注册");
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);
            email.send();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }




}