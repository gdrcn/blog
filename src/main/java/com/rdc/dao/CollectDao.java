package com.rdc.dao;

import java.util.List;
import java.util.Map;

public interface CollectDao {
	//收藏博客
	int add(Map<String,Integer> map);
	//取消收藏
	int delete(Map<String,Integer> map);
	//判断有无收藏
	Integer getCollectionId(Map<String,Integer> map);
	//博客收藏数
	Integer getCollectionCount(int blogId);

	List getCollectNews(int userId);

	void collectNewsRead(int id);
}
