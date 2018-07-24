package com.rdc.dao;

import com.rdc.bean.NewsBean;
import com.rdc.bean.UserBean;

import java.util.List;
import java.util.Map;

public interface UpDao {

	int blogUp(Map<String,Integer> map);

	int blogDown(Map<String,Integer> map);

	Integer isBlogUp(Map<String,Integer> map);

	int blogUpCount(int blogId);

	List<NewsBean> blogUpNews(int user_id);

	List<NewsBean> albumUpNews(int user_id);

	List<NewsBean> photoUpNews(int user_id);

	List<NewsBean> commentsUpNews(int user_id);

}
