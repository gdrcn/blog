package com.rdc.dao;

import java.util.Map;

public interface CategoryDao {

	int add(Map<String, Map<String, String>> map);

	int delete(int blogId);
}
