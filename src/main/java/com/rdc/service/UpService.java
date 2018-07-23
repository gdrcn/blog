package com.rdc.service;

import com.rdc.dao.BlogDao;
import com.rdc.dao.CommentDao;
import com.rdc.dao.UpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpService {

	@Autowired
	private UpDao upDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CommentDao commentDao;

	public Boolean commentDown(int userId,int commentId){
		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("commentId",commentId);

		if(1==upDao.commentDown(map))
			return true;
		return false;
	}

	public Boolean commentUp(int userId, int commentId){
		//取得作者id
		int beUserId = commentDao.findCommentUserId(commentId);
		if(userId==beUserId){
			return false;
		}

		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("beUserId",beUserId);
		map.put("commentId",commentId);

		if(null!=upDao.isCommentUp(map)){	//是否已经点赞
			return false;
		}
		if(1==upDao.commentUp(map))
			return true;
		return false;
	}

	public Boolean replyDown(int userId,int replyId){
		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("replyId",replyId);

		if(1==upDao.replyDown(map))
			return true;
		return false;
	}
	/**
	 * Asce 2018-07-23
	 * @param userId
	 * @param	replyId
	 * @return
	 */
	public Boolean replyUp(int userId, int replyId){
		//取得作者id
		int beUserId = commentDao.findReplyUserId(replyId);
		if(userId==beUserId){
			return false;
		}

		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("beUserId",beUserId);
		map.put("replyId",replyId);

		if(null!=upDao.isReplyUp(map)){	//是否已经点赞
			return false;
		}
		if(1==upDao.replyUp(map))
			return true;
		return false;
	}
	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public Boolean isBlogUp(int userId,int blogId){
		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("blogId",blogId);

		if(upDao.isBlogUp(map)!=null){
			return true;
		}
		return false;
	}
	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @return
	 */
	public int blogUpCount(int blogId){
		return upDao.blogUpCount(blogId);
	}
	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public Boolean blogDown(int userId,int blogId){
		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("blogId",blogId);

		if(1==upDao.blogDown(map))
			return true;
		return false;
	}
	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public Boolean blogUp(int userId, int blogId){
		//取得作者id
		int beUserId = blogDao.findUserId(blogId);
		if(userId==beUserId){
			return false;
		}

		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("beUserId",beUserId);
		map.put("blogId",blogId);

		if(null!=upDao.isBlogUp(map)){	//是否已经点赞
			return false;
		}
		if(1==upDao.blogUp(map))
			return true;
		return false;
	}
}
