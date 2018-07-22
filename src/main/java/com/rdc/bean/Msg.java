package com.rdc.bean;

public class Msg {

	private String result;

	private Object message;

	public Msg() {
	}

	public Msg(String result, Object message) {
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
