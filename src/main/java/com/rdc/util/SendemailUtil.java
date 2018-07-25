package com.rdc.util;


import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SendEmailUtil {

    public static void sendEmail(JavaMailSender javaMailSender,String email,String code){
        try {
            SimpleMailMessage message = new SimpleMailMessage();//消息构造器
            message.setFrom("gdut0000@163.com");//发件人
            message.setTo(email);//收件人
            message.setSubject("博客注册");//主题
            message.setText("尊敬的用户您好,您本次注册的验证码是:" + code);//正文
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

}