package com.twcoding.TestProject.bean;

import java.io.Serializable;

public class ResultCode implements Serializable{
	private static final long serialVersionUID = -1981728244909306873L;
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
