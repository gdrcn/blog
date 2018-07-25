package com.rdc.dao;

import com.rdc.entity.Blog;

import java.util.ArrayList;


public interface BlogDao {

	int add(Blog blog);

	int findUserId(int blogId);

	int delete(int blogId);

	int modify(Blog blog);

	Blog findBlogById(int blogId);

	ArrayList<Blog> searchPoint(String input);
}
