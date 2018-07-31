package com.rdc.service;

import com.rdc.bean.NewsBean;
import com.rdc.dao.CollectDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UpDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Comment;
import com.rdc.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    @Autowired
    private UpDao upDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CollectDao collectDao;

    /**
     * 消息提醒
     *
     * @param user_id
     * @return
     * @author chen
     */
    public String getNews(int user_id) {

        List<NewsBean> allNewsList = new ArrayList<>();
        List<NewsBean> blogUpNewsList = new ArrayList<>();
        blogUpNewsList = upDao.blogUpNews(user_id);
        for (NewsBean blogUpNews : blogUpNewsList) {
            blogUpNews.setType("up");
            blogUpNews.setNews(blogUpNews.getUsername() + "点赞了《" + blogUpNews.getContent() + "》");
        }
        List<NewsBean> albumUpNewsList = new ArrayList<>();
        albumUpNewsList = upDao.albumUpNews(user_id);
        for (NewsBean albumUpNews : albumUpNewsList) {
            albumUpNews.setType("up");
            albumUpNews.setNews(albumUpNews.getUsername() + "点赞了《" + albumUpNews.getContent() + "》");
        }
        List<NewsBean> photoUpNewsList = new ArrayList<>();
        photoUpNewsList = upDao.photoUpNews(user_id);
        for (NewsBean photoUpNews : photoUpNewsList) {
            photoUpNews.setType("up");
            photoUpNews.setNews(photoUpNews.getUsername() + "点赞了你的照片");
        }
        List<NewsBean> commentsUpNewsList = new ArrayList<>();
        commentsUpNewsList = upDao.commentsUpNews(user_id);
        for (NewsBean commentsUpNews : commentsUpNewsList) {
            commentsUpNews.setType("up");
            commentsUpNews.setNews(commentsUpNews.getUsername() + "点赞了你的评论");
        }
        List<NewsBean> userLikeNewsList = new ArrayList<>();
        userLikeNewsList = userDao.userLikeNews(user_id);
        for (NewsBean userLikeNews : userLikeNewsList) {
            userLikeNews.setType("like");
            userLikeNews.setNews(userLikeNews.getUsername() + "关注了你");
        }
        List<NewsBean> blogCommentsNewsList = new ArrayList<>();
        blogCommentsNewsList = commentDao.blogCommentsNews(user_id);
        for (NewsBean blogCommentsNews : blogCommentsNewsList) {
            blogCommentsNews.setType("firstComments");
            blogCommentsNews.setNews(blogCommentsNews.getUsername() + "评论了《" + blogCommentsNews.getContent() + "》");
        }
        List<NewsBean> albumCommentsNewsList = new ArrayList<>();
        albumCommentsNewsList = commentDao.albumCommentsNews(user_id);
        for (NewsBean albumCommentsNews : albumCommentsNewsList) {
            albumCommentsNews.setType("albumComments");
            albumCommentsNews.setNews(albumCommentsNews.getUsername() + "评论了《" + albumCommentsNews.getContent() + "》");
        }
        List<NewsBean> photoCommentsNewsList = new ArrayList<>();
        photoCommentsNewsList = commentDao.photoCommentsNews(user_id);
        for (NewsBean photoCommentsNews : photoCommentsNewsList) {
            photoCommentsNews.setType("photoComments");
            photoCommentsNews.setNews(photoCommentsNews.getUsername() + "评论了你的相片");
        }
        List<NewsBean> commentCommentsNewsList = new ArrayList<>();
        commentCommentsNewsList = commentDao.commentCommentsNews(user_id);
        for (NewsBean commentCommentsNews : commentCommentsNewsList) {
            commentCommentsNews.setType("secondComments");
            commentCommentsNews.setNews(commentCommentsNews.getUsername() + "评论了你的评论");
        }

        allNewsList.addAll(blogUpNewsList);
        allNewsList.addAll(albumUpNewsList);
        allNewsList.addAll(photoUpNewsList);
        allNewsList.addAll(commentsUpNewsList);
        allNewsList.addAll(userLikeNewsList);
        allNewsList.addAll(blogCommentsNewsList);
        allNewsList.addAll(albumCommentsNewsList);
        allNewsList.addAll(commentCommentsNewsList);
        allNewsList.addAll(photoCommentsNewsList);
        return GsonUtil.getSuccessJson(allNewsList);
    }

    /**
     * 读新消息
     * @param id
     * @param type
     * @author chen
     * @return
     */
    public boolean readNews(int id, String type) {
        switch (type){
            case "up":upDao.upNewsRead(id);break;
            case "like":userDao.userLikeNewsRead(id);break;
            case "firstComments":commentDao.blogCommentsNewsRead(id);break;
            case "secondComments":commentDao.commentCommentsNewsRead(id);break;
            case "albumComments":commentDao.albumCommentsNewsRead(id);break;
            case "photoComments":commentDao.photoCommentsNewsRead(id);break;
            case "collection":collectDao.collectNewsRead(id);break;
            default:return false;
        }
        return true;
    }

    /**
     * 读关注新消息
     * @param userId
     * @author chen
     * @return
     */
    public List getLikeNews(int userId) {
        List<NewsBean> userLikeNewsList = new ArrayList<>();
        userLikeNewsList = userDao.userLikeNews(userId);
        for (NewsBean userLikeNews : userLikeNewsList) {
            userLikeNews.setType("like");
            userLikeNews.setNews(userLikeNews.getUsername() + "关注了你");
        }
        return userLikeNewsList;
    }

    /**
     * 读点赞新消息
     * @param userId
     * @author chen
     * @return
     */
    public List getUpNews(int userId){
        List<NewsBean> allNewsList = new ArrayList<>();
        List<NewsBean> blogUpNewsList = new ArrayList<>();
        blogUpNewsList = upDao.blogUpNews(userId);
        for (NewsBean blogUpNews : blogUpNewsList) {
            blogUpNews.setType("up");
            blogUpNews.setNews(blogUpNews.getUsername() + "点赞了你的博客《" + blogUpNews.getContent() + "》");
        }
        List<NewsBean> albumUpNewsList = new ArrayList<>();
        albumUpNewsList = upDao.albumUpNews(userId);
        for (NewsBean albumUpNews : albumUpNewsList) {
            albumUpNews.setType("up");
            albumUpNews.setNews(albumUpNews.getUsername() + "点赞了你的相册《" + albumUpNews.getContent() + "》");
        }
        List<NewsBean> photoUpNewsList = new ArrayList<>();
        photoUpNewsList = upDao.photoUpNews(userId);
        for (NewsBean photoUpNews : photoUpNewsList) {
            photoUpNews.setType("up");
            photoUpNews.setNews(photoUpNews.getUsername() + "点赞了你的照片");
        }
        List<NewsBean> commentsUpNewsList = new ArrayList<>();
        commentsUpNewsList = upDao.commentsUpNews(userId);
        for (NewsBean commentsUpNews : commentsUpNewsList) {
            commentsUpNews.setType("up");
            commentsUpNews.setNews(commentsUpNews.getUsername() + "点赞了你的评论");
        }
        allNewsList.addAll(blogUpNewsList);
        allNewsList.addAll(albumUpNewsList);
        allNewsList.addAll(photoUpNewsList);
        allNewsList.addAll(commentsUpNewsList);
        return allNewsList;
    }

    /**
     * 读评论新消息
     * @param userId
     * @author chen
     * @return
     */
    public List getCommentNews(int userId){
        List<NewsBean> allNewsList = new ArrayList<>();
        List<NewsBean> blogCommentsNewsList = new ArrayList<>();
        blogCommentsNewsList = commentDao.blogCommentsNews(userId);
        for (NewsBean blogCommentsNews : blogCommentsNewsList) {
            blogCommentsNews.setType("firstComments");
            blogCommentsNews.setNews(blogCommentsNews.getUsername() + "评论了你的博客《" + blogCommentsNews.getContent() + "》");
        }
        List<NewsBean> albumCommentsNewsList = new ArrayList<>();
        albumCommentsNewsList = commentDao.albumCommentsNews(userId);
        for (NewsBean albumCommentsNews : albumCommentsNewsList) {
            albumCommentsNews.setType("albumComments");
            albumCommentsNews.setNews(albumCommentsNews.getUsername() + "评论了你的相册《" + albumCommentsNews.getContent() + "》");
        }
        List<NewsBean> photoCommentsNewsList = new ArrayList<>();
        photoCommentsNewsList = commentDao.photoCommentsNews(userId);
        for (NewsBean photoCommentsNews : photoCommentsNewsList) {
            photoCommentsNews.setType("photoComments");
            photoCommentsNews.setNews(photoCommentsNews.getUsername() + "评论了你的相片");
        }
        List<NewsBean> commentCommentsNewsList = new ArrayList<>();
        commentCommentsNewsList = commentDao.commentCommentsNews(userId);
        for (NewsBean commentCommentsNews : commentCommentsNewsList) {
            commentCommentsNews.setType("secondComments");
            commentCommentsNews.setNews(commentCommentsNews.getUsername() + "评论了你的评论");
        }

        allNewsList.addAll(blogCommentsNewsList);
        allNewsList.addAll(albumCommentsNewsList);
        allNewsList.addAll(commentCommentsNewsList);
        allNewsList.addAll(photoCommentsNewsList);
        return allNewsList;
    }

    /**
     * 读收藏新消息
     * @param userId
     * @author chen
     * @return
     */
    public List getCollectNews(int userId){
        List<NewsBean> collectNewsList = new ArrayList<>();
        collectNewsList = collectDao.getCollectNews(userId);
        for (NewsBean collectNews : collectNewsList) {
            collectNews.setType("collection");
            collectNews.setNews(collectNews.getUsername() + "收藏了你的博客《" + collectNews.getContent() + "》");
        }
        return collectNewsList;
    }
}