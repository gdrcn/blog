package com.rdc.service;

import com.rdc.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CollectionService {

	@Autowired
	public CollectDao collectDao;

	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public boolean addCollection(int userId,int blogId){
		Map<String,Integer> map=new HashMap<>();
		map.put("blogId",blogId);
		map.put("userId",userId);
		//判断是否已收藏
		if(collectDao.getCollectionId(map)!=null){
			return false;
		}
		if(collectDao.add(map)==1){
			return true;
		}
		return false;
	}

	/**
	 * Asce 2018-07-22
	 * @param userId
	 * @param blogId
	 * @return
	 */
	public boolean deleteCollection(int userId,int blogId){
		Map<String,Integer> map=new HashMap<>();
		map.put("blogId",blogId);
		map.put("userId",userId);
		if(collectDao.delete(map)==1){
			return true;
		}
		return false;
	}

	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @return
	 */
	public int getCollectionCount(int blogId){
		Integer count=collectDao.getCollectionCount(blogId);
		if(count!=null){
			return count;
		}
		return 0;
	}

	/**
	 * Asce 2018-07-22
	 * @param blogId
	 * @param userId
	 * @return
	 */
	public Boolean isCollect(int blogId,int userId){
		Map<String,Integer> map=new HashMap<>();
		map.put("blogId",blogId);
		map.put("userId",userId);
		if(collectDao.getCollectionId(map)!=null){
			return true;
		}
		return false;
	}
}
