package com.rdc.dao;

import com.rdc.bean.NewsBean;

import java.util.List;
import java.util.Map;

public interface UpDao {
	//点赞博客
	int blogUp(Map<String,Integer> map);
	//取消博客点赞
	int blogDown(Map<String,Integer> map);
	//是否点赞博客
	Integer isBlogUp(Map<String,Integer> map);
	//博客点赞数
	int blogUpCount(int blogId);
	//回复点赞
	int replyUp(Map<String,Integer> map);
	//取消回复点赞
	int replyDown(Map<String,Integer> map);
	//评论点赞
	int commentUp(Map<String,Integer> map);
	//取消评论点赞
	int commentDown(Map<String,Integer> map);
	//是否点赞评论
	Integer isCommentUp(Map<String,Integer> map);
	//是否点赞回复
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
	//评论点赞数
	Integer getCommentUpCount(int blogId);
	//回复点赞数
	Integer getReplyUpCount(int replyId);

}
