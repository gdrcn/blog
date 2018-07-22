package com.rdc.dao;

import com.rdc.entity.Comment;
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
}







