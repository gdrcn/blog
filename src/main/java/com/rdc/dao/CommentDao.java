package com.rdc.dao;

import com.rdc.bean.NewsBean;
import com.rdc.entity.Comment;
import com.rdc.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


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

    int getBlogFirstCommentCount(int blogId);

    int getBlogSecondCommentCount(int blogId);

    int addBlogComment(Comment comment);

    int addCommentReply(Reply reply);

    int getFirstReplyUserId(int CommentId);

    int getOtherReplyUserId(int replyId);

    int findReplyUserId(int replyId);

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
}







