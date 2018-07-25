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

	/**
	 * 博客点赞新消息
	 * @param user_id
	 * @return
	 */
	List<NewsBean> blogUpNews(int user_id);

	/**
	 * 相册点赞新消息
	 * @param user_id
	 * @return
	 */
	List<NewsBean> albumUpNews(int user_id);

	/**
	 * 图片点赞新消息
	 * @param user_id
	 * @return
	 */
	List<NewsBean> photoUpNews(int user_id);

	/**
	 * 评论点赞新消息
	 * @param user_id
	 * @return
	 */
	List<NewsBean> commentsUpNews(int user_id);

	/**
	 * 读点赞消息
	 * @param id
	 * @return
	 */
	int upNewsRead(int id);

	Integer getCommentUpCount(int blogId);

	Integer getReplyUpCount(int replyId);

}
