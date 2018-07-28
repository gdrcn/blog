package com.rdc.service;

import com.rdc.bean.UserBean;
import com.rdc.dao.BlogDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UpDao;
import com.rdc.dao.UserDao;
import com.rdc.entity.Comment;
import com.rdc.entity.Reply;
import com.rdc.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentService {

	private final int COMMENT_SIZE=10;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UpDao upDao;
	@Autowired
	private UserDao userDao;

	/**
	 * Asce 2018/7/25
	 * 获取回复
	 * @param commentId
	 * @param userId
	 * @return
	 */
	public ArrayList<Reply> getReply(int commentId,int userId){
		ArrayList<Reply> replies = commentDao.getReplyByComment(commentId);	//评论下的回复
		for (int j=0;j<replies.size();j++){
			Map<String,Integer> map2 = new HashMap<>();
			map2.put("replyId",replies.get(j).getId());
			map2.put("userId",userId);
			if(null!=upDao.isReplyUp(map2)){
				replies.get(j).setIsUp(true);
			}else{
				replies.get(j).setIsUp(false);
			}
			replies.get(j).setUpCount(upDao.getReplyUpCount(replies.get(j).getId()));	//获取回复点赞数

			if(replies.get(j).getFromReplyId()!=0) {
				replies.get(j).setToUserBean(userDao.getUserBean(replies.get(j).getFromReplyId()));        //获取回复对象信息
			}else{
				replies.get(j).setToUserBean(userDao.getUserBean(commentDao.findCommentUserId(replies.get(j).getFromCommentId())));		//第一条回复
			}
			replies.get(j).setTime(ConvertUtil.msecToMinutes(replies.get(j).getTime()));	//格式化时间
		}
		return replies;
	}
	/**
	 * Asce 2018/7/25
	 * 获取评论
	 * @param blogId
	 * @param userId
	 * @param page
	 * @return
	 */
	public ArrayList<Comment> getComment(int blogId,int userId,int page){
		int begin = page*COMMENT_SIZE;
		Map<String,Integer> map= new HashMap<>();
		map.put("blogId",blogId);
		map.put("begin",begin);
		ArrayList<Comment> comments = commentDao.getCommentByBlog(map);

		for(int i=0;i<comments.size();i++){
			Map<String,Integer> map1 = new HashMap<>();
			map1.put("commentId",comments.get(i).getId());
			map1.put("userId",userId);
			if(null!=upDao.isCommentUp(map1)){	//用户有无点赞
				comments.get(i).setIsUp(true);
			}else {
				comments.get(i).setIsUp(false);
			}
			//有无回复
			Integer replyCount = commentDao.getCommentReplyCount(comments.get(i).getId());
			if(null!=replyCount){
				comments.get(i).setReplyCount(replyCount);
			}else{
				comments.get(i).setReplyCount(0);
			}
			comments.get(i).setUpCount(upDao.getCommentUpCount(comments.get(i).getId()));	//获取评论点赞数
			comments.get(i).setTime(ConvertUtil.msecToMinutes(comments.get(i).getTime()));
		}
		return comments;
	}

	/**
	 * Asce 2018/7/25
	 * 获取评论（前端）
	 * @param blogId
	 * @param userId
	 * @param page
	 * @return
	 */
	public ArrayList<Comment> getCommentWithoutReply(int blogId,int userId,int page) throws ParseException {
		return getComment(blogId,userId,page);
	}
	/**
	 * Asce 2018/7/25
	 * 获取评论（回复在评论下）
	 * @param blogId
	 * @param userId
	 * @param page
	 * @return
	 */
	public ArrayList<Comment> getCommentWithReply(int blogId,int userId,int page) throws ParseException {

		ArrayList<Comment> comments = getComment(blogId,userId,page);

		for(int i=0;i<comments.size();i++){
			ArrayList<Reply> replies = getReply(comments.get(i).getId(),userId);	//添加评论
			comments.get(i).setReplies(replies);
		}
		return comments;
	}
	/**
	 * Asce 2018/7/25
	 * 发表博客
	 * @param userId
	 * @param reply
	 * @return
	 */
	public int addCommentReply(int userId, Reply reply){
		//判断是否为自己回复自己
		if(reply.getFromReplyId()==0){
			if(userId==commentDao.getFirstReplyUserId(reply.getFromCommentId())){
				return 0;
			}
		}else if(userId==commentDao.getOtherReplyUserId(reply.getFromReplyId())){
			return 0;
		}

		reply = replyConvert(reply);
		if(reply==null)
			return 0;
		UserBean userBean=new UserBean();
		userBean.setId(userId);
		reply.setFromUserBean(userBean);

		int result = commentDao.addCommentReply(reply);

		if(result==1){
		return reply.getId();
		}
		return 0;
	}
	/**
	 * Asce 2018-07-23
	 * 发表评论
	 * @param userId
	 * @param comment
	 * @return
	 */
	public int addBlogComment(int userId,Comment comment){

		if(userId==blogDao.findUserId(comment.getFromId())){
			return 0;
		}
		comment = commentConvert(comment);
		if(comment==null){
			return 0;
		}
		UserBean userBean = new UserBean();
		userBean.setId(userId);
		comment.setFromUserBean(userBean);
		int result = commentDao.addBlogComment(comment);

		if(result==1){
			return comment.getId();
		}
		return 0;
	}
	/**
	 * Asce 2018-07-22
	 * 得到评论数
	 * @param blogId
	 * @return
	 */
	public int getBlogCommentCount(int blogId){
		Integer firstCount = commentDao.getBlogFirstCommentCount(blogId);
		Integer secondCount = commentDao.getBlogSecondCommentCount(blogId);
		if(firstCount!=null){
			if(secondCount!=null){
				return firstCount+secondCount;
			}else {
				return firstCount;
			}
		}
		return 0;
	}

	/**
	 * Asce 2018/7/25
	 * 校验评论
	 * @param comment
	 * @return
	 */
	public Comment commentConvert(Comment comment){

		if(comment.getComments().length()>200||comment.getComments().length()==0){
			return null;
		}

		comment.setComments(HtmlUtils.htmlEscape(comment.getComments()));

		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		comment.setTime(date.format(cal.getTime()));

		return comment;
	}

	/**
	 * Asce 2018/7/25
	 * 校验回复
	 * @param reply
	 * @return
	 */
	public Reply replyConvert(Reply reply){
		if(reply.getComments().length()>200||reply.getComments().length()==0){
			return null;
		}
		reply.setComments(HtmlUtils.htmlEscape(reply.getComments()));

		Calendar cal=Calendar.getInstance();
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		reply.setTime(date.format(cal.getTime()));

		return reply;
	}
}
