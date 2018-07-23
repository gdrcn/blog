package com.rdc.dao;

import java.util.Map;

public interface CollectDao {

	int add(Map<String,Integer> map);

	int delete(Map<String,Integer> map);

	Integer getCollectionId(Map<String,Integer> map);

	Integer getCollectionCount(int blogId);
}
