package com.rdc.service;

import com.rdc.dao.BlogDao;
import com.rdc.entity.Blog;
import com.rdc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public void add(){
		User user=new User();
		user.setId(3);
		Blog blog=new Blog();
		blog.setUser(user);
		blog.setTitle("this is a test");
		blog.setArticle("有一天，程序没bug了");
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		blog.setFinishTime(date.format(cal.getTime()));
		blogDao.add(blog);
	}
}
