package com.rdc.bean;

import com.rdc.entity.Blog;

public class BlogBean {
	private Blog blog;
	private int upCount;
	private int collectionCount;
	private int commentCount;
	private Boolean isCollect;
	private Boolean isUp;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getUpCount() {
		return upCount;
	}

	public void setUpCount(int upCount) {
		this.upCount = upCount;
	}

	public int getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Boolean getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}

	public Boolean getIsUp() {
		return isUp;
	}

	public void setIsUp(Boolean isUp) {
		this.isUp = isUp;
	}
}
