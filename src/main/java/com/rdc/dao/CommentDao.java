package com.rdc.dao;

import com.rdc.entity.Comment;
import com.rdc.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface CommentDao {

    /**
     * 得到相册评论
     * @param userId
     * @return
     */
    List<Comment> getAlbumComment(int userId);

    /**
     * 得到博客评论
     * @param userId
     * @return
     */
    List<Comment> getblogFirstComment(int userId);

    /**
     * 得到二级评论
     * @param userId
     * @return
     */
    List<Comment> getBlogSecondComment(int userId);

    int getBlogFirstCommentCount(int blogId);

    int getBlogSecondCommentCount(int blogId);

    int addBlogComment(Comment comment);

    int addCommentReply(Reply reply);

    int getFirstReplyUserId(int CommentId);

    int getOtherReplyUserId(int replyId);

    int findReplyUserId(int replyId);

    int findCommentUserId(int commentId);
}







