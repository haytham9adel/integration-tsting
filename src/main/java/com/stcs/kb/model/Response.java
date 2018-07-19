package com.stcs.kb.model;

public class Response {

	//312
	boolean isOk ;
	String msg ;
	
	
	
	public Response(boolean isOk, String msg) {
		super();
		this.isOk = isOk;
		this.msg = msg;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
