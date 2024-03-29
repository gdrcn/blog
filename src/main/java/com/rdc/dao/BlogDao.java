package com.rdc.dao;

import com.rdc.entity.Blog;

import java.util.ArrayList;
import java.util.Map;


public interface BlogDao {

	//发表博客
	int add(Blog blog);
	//找到博客作者
	int findUserId(int blogId);
	//删除博客
	int delete(int blogId);
	//修改博客
	int modify(Blog blog);
	//根据博客id找博客
	Blog findBlogById(int blogId);
	//搜索提示
	ArrayList<Blog> searchPoint(String input);
	//搜索结果
	ArrayList<Blog> search(Map<String,Object> map);
	//查看用户博客
	ArrayList<Blog> findBlogByUser(Map<String,Integer> map);
	//分类查看博客
	ArrayList<Blog> findBlogByCategory(Map<String,Object> map);
	//最新博客
	ArrayList<Blog> findNewBlog(int begin);
	//最热博客
	ArrayList<Blog> findHotBlog();
	//得到某类博客总数
	int getCategoryCount(String category);
	//得到用户发表的博客数
	int getUserBlogCount(int userId);
	//得到搜索结果总数
	int getSearchCount(String input);
	//得到用户收藏的博客数
	int getCollectBlogCount(int userId);
	//得到用户收藏列表
	ArrayList<Blog> findBlogByCollect(Map<String,Integer> map);
}
