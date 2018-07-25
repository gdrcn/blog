package com.rdc.dao;

import com.rdc.bean.NewsBean;
import com.rdc.entity.Comment;
import com.rdc.entity.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface CommentDao {

    /**
     * 统计未读相册评论
     * @param userId
     * @return
     */
    int countNotReadAlbum(int userId);

    /**
     * 统计未读一级评论
     * @param userId
     * @return
     */
    int countNotReadFirst(int userId);

    /**
     * 统计未读二级评论
     * @param userId
     * @return
     */
    int countNotReadSecond(int userId);
    //一级评论总数
    int getBlogFirstCommentCount(int blogId);
    //回复总数
    int getBlogSecondCommentCount(int blogId);
    //发表评论
    int addBlogComment(Comment comment);
    //发表回复
    int addCommentReply(Reply reply);
    //得到评论者id，防止自己回复自己
    int getFirstReplyUserId(int CommentId);
    //得到回复者id，防止自己回复自己
    int getOtherReplyUserId(int replyId);
    //找到回复对象id
    int findReplyUserId(int replyId);
    //找到一级评论用户id（评论的第一条回复）
    int findCommentUserId(int commentId);

    /**
     * 博客评论新消息
     * @param user_id
     * @return
     */
    List<NewsBean> blogCommentsNews(int user_id);

    /**
     * 图片评论新消息
     * @param user_id
     * @return
     */
    List<NewsBean> photoCommentsNews(int user_id);

    /**
     * 相册评论新消息
     * @param user_id
     * @return
     */
    List<NewsBean> albumCommentsNews(int user_id);

    /**
     * 二级评论新消息
     * @param user_id
     * @return
     */
    List<NewsBean> commentCommentsNews(int user_id);


    /**
     * 读博客评论新消息
     * @param id
     * @return
     */
    int blogCommentsNewsRead(int id);

    /**
     * 读二级评论新消息
     * @param id
     * @return
     */
    int commentCommentsNewsRead(int id);

    /**
     * 读相册评论新消息
     * @param id
     * @return
     */
    int albumCommentsNewsRead(int id);

    /**
     * 读照片评论新消息
     * @param id
     * @return
     */
    int photoCommentsNewsRead(int id);
    //得到博客评论
    ArrayList<Comment> getCommentByBlog(Map<String,Integer> map);
    //得到评论回复
    ArrayList<Reply> getReplyByComment(int CommentId);
    //得到评论回复数
    Integer getCommentReplyCount(int CommentId);
}







