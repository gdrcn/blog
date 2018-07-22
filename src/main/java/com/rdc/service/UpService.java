package com.rdc.service;

import com.rdc.dao.BlogDao;
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

	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public Boolean isUp(int userId,int blogId){
		Map<String,Integer> map=new HashMap<>();
		map.put("userId",userId);
		map.put("blogId",blogId);

		if(upDao.isUp(map)!=null){
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
		if(userId==blogDao.findUserId(blogId)){
			return false;
		}
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

		if(null!=upDao.isUp(map)){	//是否已经点赞
			return false;
		}
		if(1==upDao.blogUp(map))
			return true;
		return false;
	}
}
