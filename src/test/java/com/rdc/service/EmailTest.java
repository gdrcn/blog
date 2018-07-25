package com.rdc.service;

import com.rdc.entity.User;
import com.rdc.util.SendEmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config/spring-mybatis.xml"})
public class EmailTest {

	@Autowired
	private JavaMailSender mailSender;
	@Test
	public void testA(){

		String a="abc";
		String b="abc";
		System.out.println(a!=b);
		System.out.println(a.equals(b));

	}

}
