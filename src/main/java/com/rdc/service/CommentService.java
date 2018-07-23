package com.rdc.service;

import com.rdc.bean.UserBean;
import com.rdc.dao.BlogDao;
import com.rdc.dao.CommentDao;
import com.rdc.entity.Comment;
import com.rdc.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BlogDao blogDao;

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
		comment.setFromId(userId);
		int result = commentDao.addBlogComment(comment);

		if(result==1){
			return comment.getId();
		}
		return 0;
	}

	/**
	 * Asce 2018-07-22
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
