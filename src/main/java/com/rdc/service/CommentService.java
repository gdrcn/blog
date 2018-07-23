package com.rdc.service;

import com.rdc.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @return
	 */
	public int getCommentCount(int blogId){
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

}
