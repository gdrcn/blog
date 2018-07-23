package com.rdc.dao;

import java.util.Map;

public interface UpDao {

	int blogUp(Map<String,Integer> map);

	int blogDown(Map<String,Integer> map);

	Integer isBlogUp(Map<String,Integer> map);

	int blogUpCount(int blogId);

	int replyUp(Map<String,Integer> map);

	int replyDown(Map<String,Integer> map);

	int commentUp(Map<String,Integer> map);

	int commentDown(Map<String,Integer> map);

	Integer isCommentUp(Map<String,Integer> map);

	Integer isReplyUp(Map<String,Integer> map);

}
