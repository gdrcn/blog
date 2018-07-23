package com.rdc.dao;

import com.rdc.entity.Blog;


public interface BlogDao {

	int add(Blog blog);

	int findUserId(int blogId);

	int delete(int blogId);

	int modify(Blog blog);

	Blog findBlogById(int blogId);


}
