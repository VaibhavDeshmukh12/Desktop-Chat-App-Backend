package com.social.respose;

public class ApiResponse {

	private String msg;
	
	private boolean type;

	public ApiResponse() {
		super();
	}

	public ApiResponse(String msg, boolean type) {
		super();
		this.msg = msg;
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
}
