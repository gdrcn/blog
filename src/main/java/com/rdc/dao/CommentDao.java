package com.rdc.dao;

import com.rdc.entity.Comment;
import com.rdc.entity.Reply;


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
}







