package com.rdc.dao;

import java.util.Map;

public interface UpDao {

	int blogUp(Map<String,Integer> map);

	int blogDown(Map<String,Integer> map);

	Integer isUp(Map<String,Integer> map);

	int blogUpCount(int blogId);
}
