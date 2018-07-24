package com.rdc.dao;

import com.rdc.bean.NewsBean;

import java.util.List;
import java.util.Map;

public interface UpDao {

	int blogUp(Map<String,Integer> map);

	int blogDown(Map<String,Integer> map);

	Integer isBlogUp(Map<String,Integer> map);

	int blogUpCount(int blogId);

	int replyUp(Map<String,Integer> map);

	int replyDown(Map<String,Integer> map);

	int commentUp(Map<String,Integer> map);

	int commentDown(Map<String,Integer> map);

	Integer isCommentUp(Map<String,Integer> map);

	Integer isReplyUp(Map<String,Integer> map);

	List<NewsBean> blogUpNews(int user_id);

	List<NewsBean> albumUpNews(int user_id);

	List<NewsBean> photoUpNews(int user_id);

	List<NewsBean> commentsUpNews(int user_id);

}