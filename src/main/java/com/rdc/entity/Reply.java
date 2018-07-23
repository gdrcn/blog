package com.rdc.entity;

import com.rdc.bean.UserBean;

public class Reply {

	private int id;
	private String comments;
	private String time;
	private UserBean toUserBean;
	private UserBean fromUserBean;
	private int fromCommentId;
	private int fromReplyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public UserBean getToUserBean() {
		return toUserBean;
	}

	public void setToUserBean(UserBean toUserBean) {
		this.toUserBean = toUserBean;
	}

	public UserBean getFromUserBean() {
		return fromUserBean;
	}

	public void setFromUserBean(UserBean fromUserBean) {
		this.fromUserBean = fromUserBean;
	}

	public int getFromCommentId() {
		return fromCommentId;
	}

	public void setFromCommentId(int fromCommentId) {
		this.fromCommentId = fromCommentId;
	}

	public int getFromReplyId() {
		return fromReplyId;
	}

	public void setFromReplyId(int fromReplyId) {
		this.fromReplyId = fromReplyId;
	}
}
